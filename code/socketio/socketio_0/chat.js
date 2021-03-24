const express = require("express");
const app = express();
const socketio = require("socket.io");

// server files in the public folder
// check it on localhost:9000/char.html
app.use(express.static(__dirname + "/public"));

const expressServer = app.listen(9000);

const io = socketio(expressServer, {
  path: "/socket.io",
  serveClient: true, // chat.html可以用socketio
});

io.on("connection", (socket) => {
  socket.emit("messageFromServer", { data: "Welcome to the socketio server" });
  socket.on("messageToServer", (dataFromClient) => {
    console.log(dataFromClient);
  });
  socket.on("newMessageToServer", (msg) => {
    io.emit("messageToClients", { text: msg.text });
  });
});
