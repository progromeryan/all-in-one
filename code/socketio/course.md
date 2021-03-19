# UDP - User Datagram Protocol

- light weight
  - 8 byte header 非常小
- 非常快
  - 可适用于类似游戏等
- connectionless
- consistency 有问题
  - send data no matter what
  - 可以直接传输数据，不需要先进行连接验证
  - 会有很多问题，比如 packet loss 或者 packet out of order
  - 不可靠！

# TCP - Transmission Control Protocol

- connection-based
- 传输之前会进行连接验证, three-way handshake
  - 可靠的
  - delivery ack
  - right order packet
- congesstion control 网络出问题时也会尽量保证 packet 传输正确

example:
发送 hello, workd

- TCP: hello, world
- UDP: whlleo, rdlo

TCP + IP -> 创造一个机器可以交互的环境

# Socket vs Websocket

Socket 是传输控制层接口，WebSocket 是应用层协议

# Websocket vs HTTP

## 相同

1. 都是一样基于 TCP 的，都是可靠性传输协议。
2. 都是应用层协议。

## 不同

1. WebSocket 是双向通信协议，模拟 Socket 协议，可以双向发送或接受信息。HTTP 是单向的。
2. WebSocket 是需要浏览器和服务器握手进行建立连接的。而 HTTP 是浏览器发起向服务器的连接，服务器预先并不知道这个连接。
3. WebSocket 在建立握手时，数据是通过 HTTP 传输的。但是建立之后，在真正传输时候是不需要 HTTP 协议的。

在 WebSocket 中，只需要服务器和浏览器通过 HTTP 协议进行一个握手的动作，然后单独建立一条 TCP 的通信通道进行数据的传送。
WebSocket 连接的过程是:

1. 客户端发起 http 请求, 经过 3 次握手后, 建立起 TCP 连接; http 请求里存放 WebSocket 支持的版本号等信息, 如: Upgrade, Connection, WebSocket-Version 等
2. 服务器收到客户端的握手请求后, 同样采用 HTTP 协议回馈数据
3. 客户端收到连接成功的消息后, 开始借助于 TCP 传输信道进行全双工通信

# Websocket API in JS

WebSockets 是一种先进的技术。它可以在用户的浏览器和服务器之间打开交互式通信会话。使用此 API，您可以向服务器发送消息并接收事件驱动的响应，而无需通过轮询服务器的方式以获得响应。

# Socketio

Socket.IO enables real-time, bidirectional and event-based communication.
It works on every platform, browser or device, focusing equally on reliability and speed.
