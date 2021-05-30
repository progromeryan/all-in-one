# Design a notification system

Three types of notifications:

- mobile push notification
- SMS message
- Email

![img](assets/10-1.png)

# Step 1: Understand the problem and establish design scope

## 可能问的问题

- Candidate: What types of notifications does the system support?
- Interviewer: Push notification, SMS message, and email.
- Candidate: Is it a real-time system?
- Interviewer: Let us say it is a soft real-time system. We want a user to receive notifications as soon as possible. However, if the system is under a high workload, a slight delay is acceptable.
- Candidate: What are the supported devices?
- Interviewer: iOS devices, android devices, and laptop/desktop.
- Candidate: What triggers notifications?
- Interviewer: Notifications can be triggered by client applications. They can also be scheduled on the server-side.
- Candidate: Will users be able to opt-out?
- Interviewer: Yes, users who choose to opt-out will no longer receive notifications.
- Candidate: How many notifications are sent out each day?
- Interviewer: 10 million mobile push notifications, 1 million SMS messages, and 5 million emails.

# Step 2: Propose high-level design and get buy-in

- different types if notifications
- contact info gathering flow
- notification sending/receiving flow

## Different types of notifications

### IOS push notification

![img](assets/10-2.png)

- provider
  - build, send notification request to Apple Push Notification Service (APNS)
  - 发送
    - device token: unique ID for sending push notifications
    - payload: push title, content....
- APNS
  - Apple service to push notification to IOS devices
- IOS
  - end client to receive notifications

### android push notification

![img](assets/10-3.png)

与 ios 类似, 但是使用 FCM

### SMS message

![img](assets/10-4.png)

SMS messages 通常使用第三方服务, 比如 Twilio, Nexmo 等

### Email

可以选择一些第三方的 email service, 比如 sendgrid, Mailchimp. 提供 delivery rate 和 data analytics

![img](assets/10-5.png)

Design including all third-party service

![img](assets/10-6.png)

## Contact info gathering flow

需要获取 mobile device tokens, phone number, email address... 当用户第一次安装并且注册 app 的时候, API servers 手机用户的信息并存在 db 中

![img](assets/10-7.png)

db tables

![img](assets/10-8.png)

## Notification sending/receiving flow

### high-level design

![img](assets/10-9.png)

- service 1 to N
  - service 可以是 microservice, cron job, distributed system...
- notification system
  - provides API for service 1 to N
  - build notification payload for third-party services
- third-party services
  - deliver notification to users
  - 注意 extensibility
  - 注意某些 third-party service 在某些地区可能不能用
- IOS, Android, SMS, Email
  - users receive notification on devices

存在的问题:

- single point of failure, 只有一个 server
- hard to scale
- performance bottleneck

### high-level design - improved

- move db and cache out of the notification server
- add more servers and set up automatic horizontal scaling
- introduce message queue to decouple the system components

![img](assets/10-10.png)

- service 1 to N: different services send notifications via APIs provided by notification servers
- Notification servers
  - provide APIs for services to send notifications
  - basic validations for emails, phone numbers...
  - query db or cache to get needed data to render a notification
  - put notification data to message queues for parallel processing
- cache
  - user info, device info, notification templates are cached
- DB
  - stores data about users, notification, settings...
- message queue
  - remove dependencies between components
  - server as buffers when high volumes of notifications
  - each type of notification is assigned to a distinct message queue
- workers
  - pull notification events from message queues
  - send message to third-party services
- third-party service
- IOS, Android, SMS, Email

API 举例

POST https://api.example.com/v/sms/send

request body

```json
{
  "to": [
    {
      "user_id": 123456
    }
  ],
  "from": {
    "email": "from_address@example.com"
  },
  "subject": "hello world",
  "content": {
    "type": "text/plain",
    "value": "hello world"
  }
}
```

工作流程

- a service call APIs provided by notification servers
- notification servers fetch metadata, like user info, device token, and notification setting from cache or db
- notification event is sent to corresponding queue for processing
- workers pull notification events from message queues
- workers send notifications to third party services
- third-party service send notifications to user devices

# Step 3: Design deep dive

## Reliability

### how to prevent data loss?

![img](assets/10-11.png)

- save notification data in db
- implement retry mechanism
- notification log database for data persistence

### will recipients receive a notification exactly once?

有可能导致重复推送. 为了防止重复推送发生

- 当收到一个 notification event 的时候, 先查看 event ID
- 如果已经存在这个 event ID, 就放弃当前推送
- 如果不存在这个 event ID, 就进行推送

## Additional components and consideration

### notification template

- 很多 notification 都很类似, 所以需要一个 template
- 提高效率, 避免错误, 节省时间

### notification setting

- 把用户对 notification 的设置存在 setting table 中
- 如果有些用户设置不接受 notification, 我们把设置存在 db 中, 每次发送之前都要检查这个 db

### rate limiting

- limit the number of notifications a user can receive, 防止用户因为收到太多的 notification 而彻底关闭接收

### retry mechanism

- 当 third-party 没有推送成功,notification 需要被重新放到 queue 中, 然后 retry
- 如果多次重试都未成功, 需要发送 alert 给开发人员

### security in push notification

- IOS 和 Android 有自己的 security 方法, 只有授权的 client 才能发送推送

### monitor queued notification

- total number of queued notifications
- 如果数量过大, 那么需要增加 worker 的数量

![img](assets/10-12.png)

### events tracking

notification metrics

- open rate
- click rate
- engagement

![img](assets/10-13.png)

## Update design

![img](assets/10-14.png)

- notification servers
  - authentication
  - rate limit
- add retry mechanism to handle notification failures
- notification template
- monitoring and tracking systems

# Step 4: wrap up

- Reliability: retry mechanism to minimize failure rate
- security: third-party server verify clients to send notification
- tracking and monitoring
- user setting: don't send notifications if user opt-out
- rate limiting: don't send notifications to a user too frequently
