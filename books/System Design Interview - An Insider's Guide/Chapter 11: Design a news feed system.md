> News feed is the constantly updating list of stories in the middle of your home page. News Feed includes status updates, photos, videos, links, app activity, and likes from people, pages, and groups that you follow on Facebook

类似的问题:

- facebook news feed
- instagram feed
- twitter timeline

![img](assets/11-1.png)

# Step 1: Understand the problem and establish design scope

- Candidate: Is this a mobile app? Or a web app? Or both?
- Interviewer: Both
- Candidate: What are the important features?
- Interview: A user can publish a post and see her friends’ posts on the news feed page.
- Candidate: Is the news feed sorted by reverse chronological order or any particular order such as topic scores? For instance, posts from your close friends have higher scores.
- Interviewer: To keep things simple, let us assume the feed is sorted by reverse chronological order.
- Candidate: How many friends can a user have?
- Interviewer: 5000
- Candidate: What is the traffic volume?
- Interviewer: 10 million DAU
- Candidate: Can feed contain images, videos, or just text?
- Interviewer: It can contain media files, including both images and videos.

# Step 2: Propose high-level design and get buy-in

主要 workflow

- feed publishing
  - write data to cache and db
  - a post is populated to her friends' news feed
- newsfeed building (显示 feeds)
  - aggregate friends' posts in reverse chronological order

## Newsfeed APIs

### feed publishing API

POST /v1/me/feed

- content: post text
- auth_token: authenticate API requests

### newsfeed retrieval API

GET /v1/me/feed

- auth_token: authenticate API requests

## Feed publishing

![img](assets/11-2.png)

- user
  - view new feeds on browser or mobile app
  - post through API /v1/me/feed?content=Hello&auth_token={auth_token}
- load balancer: distribute traffic to web servers
- post service: save post to db and cache
- fanout service
  - push new content to friends' news feed
  - newsfeed data is stored in the cache for fast retrieval
- notification service
  - inform friends new content is available
  - send out push notification

## newsfeed building

![img](assets/11-3.png)

- user
  - send request to retrieve her news feed
  - /v1/me/feed
- load balancer: redirect traffic to web servers
- web servers: route requests to newsfeed service
- newsfeed service: fetch news feed from cache
- newsfeed cache: store new feed IDS needed to render the news feed

# Step 3: Design deep dive

## Feed publishing deep dive

![img](assets/11-4.png)

### web server

- authentication: only authenticated users can post
- rate limiting: 不能无限制的 post

### fanout service

Fanout is the process of delivering a post to all friends. Two types of fanout models:

- fanout on write (push model)
- fanout on read (pull model)

最佳实践是采用 hybrid 方法.

- fetching news feed 速度很重要, push model 适用于大多数用户
- celebrities/have many friends/followers, followers pull news content on-demand

#### fanout on write (push model)

- news feed is **pre-computed** during write time
- a new post is delivered to friends' cache immediately after it is published
- 优点
  - news feed is generated in real-time and can be pushed to friends immediately
  - fetching news feed is fast because the news feed is pre-computed during write time
- 缺点
  - if a user has many friends, fetching the friend list and generating news feeds for all of them are slow. It is called hotkey problem.
  - for inactive users, pre-computing news feeds waster computing resources

#### fanout on read (pull model)

- news feed is generated during read time.
- an on-demand model
- recent posts are pulled when a user loads her home page
- 优点
  - for inactive users, it will not waste computing resources
  - no hotkey problem
- 缺点
  - fetching the news feed is slow because the news feed is not pre-computed

![img](assets/11-5.png)

1. fetch friend IDs from graph db
2. get friends info from user cache, like filtering out muted friends...
3. send friend list and new post ID to message queue
4. workers fetch data from message queue and save news feed data in the cache
   1. news feed cache: <post_id, user_id> mapping table ![img](assets/11-6.png)
   2. when new post made, append it to the mapping table
   3. only save post_id and user_id to save memory
5. store <post_id, user_id> in cache

## newsfeed retrieval deep dive

![img](assets/11-7.png)

workflow

> media content are saved in CDN for fast retrieval

1. user sends a request /v1/me/feed
2. load balancer redistributes request to web server
3. web servers call news feed service to fetch news feeds
4. news feed service get a list of post IDs from the news feed cache
5. news feed service get complete user and post objects from users cache and post cache
6. 返回结果

## cache architecture

Divide cache tier into 5 layers

![img](assets/11-8.png)

- news feed: save IDs of new feeds
- content: save every post data. popular content is saved in hot cache
- social graph: save user relationship data
- action: save actions(like a post, reply a post...)
- counters: save counters for like, reply, follower, following....

# Step 4: Wrap up

还可以讨论分话题

## Scale db

- vertical scaling vs horizontal scaling
- sdl vs nosql
- master-salve replication
- read replicas
- consistency models
- db sharding

## other topic

- keep web tier stateless
- cache data
- support multiple data centers
- use message queue to lose couple components
- monitor key metrics, like QPS, latency...
