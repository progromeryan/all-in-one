const express = require('express');
const app = express();
const socketio = require('socket.io');

let namespaces = require('./data/namespaces');

app.use(express.static(__dirname + '/public'));
const expressServer = app.listen(9000);
const io = socketio(expressServer);

// 初次链接
io.on('connection', (socket) => {
  let nsData = namespaces.map((ns) => {
    return {
      img: ns.img,
      endpoint: ns.endpoint
    };
  });
  // 发送到nsList
  socket.emit('nsList', nsData);
});

// 监听所有的namespaces
namespaces.forEach((namespace) => {
  io.of(namespace.endpoint).on('connection', (nsSocket) => {
    // 得到当前的user
    const username = nsSocket.handshake.query.username;
    // 发送rooms
    nsSocket.emit('nsRoomLoad', namespace.rooms);

    // 监听joinRoom
    nsSocket.on('joinRoom', (roomToJoin, numberOfUsersCallback) => {
      // 离开之前的room
      const roomToLeave = Object.keys(nsSocket.rooms)[1];
      nsSocket.leave(roomToLeave);
      // 更新user数量
      updateUsersInRoom(namespace, roomToLeave);
      // 加入room
      nsSocket.join(roomToJoin);
      // 处理callback
      // io.of(namespace.endpoint).in(roomToJoin).clients((error, clients) => {
      //   numberOfUsersCallback(clients.length);
      // });

      const nsRoom = namespace.rooms.find((room) => {
        return room.roomTitle === roomToJoin;
      });
      // 当加入时, 发送历史信息
      nsSocket.emit('historyCatchUp', nsRoom.history);
      // 更新user数量
      updateUsersInRoom(namespace, roomToJoin);
    });

    // client发送一个新消息
    nsSocket.on('newMessageToServer', (msg) => {
      const fullMsg = {
        text: msg.text,
        time: Date.now(),
        username: username,
        avatar: 'https://via.placeholder.com/30'
      };

      // array中的第二个是当前的room, 第一个是自己的room
      const roomTitle = Object.keys(nsSocket.rooms)[1];

      const nsRoom = namespace.rooms.find((room) => {
        return room.roomTitle === roomTitle;
      });
      console.log(namespace.rooms);
      // 添加到chat历史
      nsRoom.addMessage(fullMsg);
      // 发送给room中的所有人
      io.of(namespace.endpoint).to(roomTitle).emit('messageToClients', fullMsg);
    });
  });
});

// 更新当前room的聊天人的数量
function updateUsersInRoom(namespace, roomToJoin) {
  io.of(namespace.endpoint).in(roomToJoin).clients((error, clients) => {
    io.of(namespace.endpoint).in(roomToJoin).emit('updateMembers', clients.length);
  });
}
