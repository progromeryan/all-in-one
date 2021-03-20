const http = require("http");
const socketio = require("socket.io");

const server = http.createServer((req, res) => {
  res.end("I am connected!");
});

const io = socketio(server);

io.on("connection", (socket, req) => {
  // send welcome event
  socket.emit("welcome", "Welcome to the websocket server!!");
  // listen message event
  socket.on("message", (msg) => {
    console.log(msg);
  });
});

server.listen(8000);
