当访问 google 时发生了什么

- 在浏览器中输入 URL
- 首先访问最近的 DNS 服务器，它记录和www.google.com所对应的IP地址
- 浏览器向这个 IP 发送 http/https 请求
  - 每台计算机联网都需要一个 IP 地址
  - 通过 IP 地址就能找到这个计算机
- 当服务器(web server)收到请求，则将请求递交给正在 80 端口监听的 server
  - 常用的 HTTP server 有 Apache, Gunicorn, Uwsgi
- HTTP Server 将请求转发给 Web Application
  - 三大 web application framework: Django, Ruby on Rails, NodeJS
- Web Application 处理请求，并生成对应的 html 网页
- Server 返回 HTML 网页给用户的浏览器
