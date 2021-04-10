function joinNs(endpoint) {
  // 切换room的时候先关闭之前的room
  if (nsSocket) {
    nsSocket.close();
    document.querySelector('#user-input').removeEventListener('submit', formSubmission);
  }

  nsSocket = io(`http://localhost:9000${endpoint}`);

  // 接受所有room的信息
  nsSocket.on('nsRoomLoad', (nsRooms) => {
    let roomList = document.querySelector('.room-list');
    roomList.innerHTML = "";
    nsRooms.forEach((room) => {
      let glyph;
      if (room.privateRoom) {
        glyph = 'lock';
      } else {
        glyph = 'globe';
      }
      roomList.innerHTML += `<li class="room"><span class="glyphicon glyphicon-${glyph}"></span>${room.roomTitle}</li>`;
    });

    // 监听每个room的click来加入room
    let roomNodes = document.getElementsByClassName('room');
    Array.from(roomNodes).forEach((elem) => {
      elem.addEventListener('click', (e) => {
        joinRoom(e.target.innerText);
      });
    });

    // 默认加入的room
    const topRoom = document.querySelector('.room');
    const topRoomName = topRoom.innerText;
    joinRoom(topRoomName);
  });

  // 监听从server传来的信息
  nsSocket.on('messageToClients', (msg) => {
    document.querySelector('#messages').innerHTML += buildHTML(msg);
  });

  document.querySelector('.message-form').addEventListener('submit', formSubmission);
}

function formSubmission(event) {
  event.preventDefault();
  const newMessage = document.querySelector('#user-message').value;
  nsSocket.emit('newMessageToServer', {text: newMessage});
}

// 创建新信息
function buildHTML(msg) {
  const convertedDate = new Date(msg.time).toLocaleString();
  const newHTML = `
    <li>
        <div class="user-image">
            <img src="${msg.avatar}" />
        </div>
        <div class="user-message">
            <div class="user-name-time">${msg.username} <span>${convertedDate}</span></div>
            <div class="message-text">${msg.text}</div>
        </div>
    </li>    
    `;
  return newHTML;
}