function joinRoom(roomName) {
  // 发送roomName给server
  // emit可以传入第三个参数
  nsSocket.emit('joinRoom', roomName, (newNumberOfMembers) => {
    // we want to update the room member total now that we have joined!
    document.querySelector('.curr-room-num-users').innerHTML = `${newNumberOfMembers} <span class="glyphicon glyphicon-user"></span>`;
  });

  // 接受chat的历史记录
  nsSocket.on('historyCatchUp', (history) => {
    const messagesUl = document.querySelector('#messages');
    messagesUl.innerHTML = "";
    history.forEach((msg) => {
      const newMsg = buildHTML(msg);
      messagesUl.innerHTML += newMsg;
    });
    // scrollTo从底部开始, 也就是最新的信息
    messagesUl.scrollTo(0, messagesUl.scrollHeight);
  });

  // 更新当前room的user数量
  nsSocket.on('updateMembers', (numMembers) => {
    document.querySelector('.curr-room-num-users').innerHTML = `${numMembers} <span class="glyphicon glyphicon-user"></span>`;
    document.querySelector('.curr-room-text').innerText = `${roomName}`;
  });

  // 搜索功能
  let searchBox = document.querySelector('#search-box');
  searchBox.addEventListener('input', (e) => {
    let messages = Array.from(document.getElementsByClassName('message-text'));
    messages.forEach((msg) => {
      if (msg.innerText.toLowerCase().indexOf(e.target.value.toLowerCase()) === -1) {
        msg.style.display = "none";
      } else {
        msg.style.display = "block";
      }
    });
  });
}