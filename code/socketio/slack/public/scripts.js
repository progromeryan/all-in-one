const username = prompt("What is your username?");
const socket = io('http://localhost:9000', {
  query: {
    username
  }
});

// global
let nsSocket;

// 监听nsList
socket.on('nsList', (nsData) => {
  // 用namespace data更新DOM
  let namespacesDiv = document.querySelector('.namespaces');
  namespacesDiv.innerHTML = "";
  nsData.forEach((ns) => {
    namespacesDiv.innerHTML += `<div class="namespace" ns=${ns.endpoint}><img src="${ns.img}" /></div>`;
  });

  // 监听每个namespace的click, 然后加入namespace
  // document.getElementsByClassName('namespace')返回HTML collection需要转化为array
  Array.from(document.getElementsByClassName('namespace')).forEach((elem) => {
    elem.addEventListener('click', (e) => {
      const nsEndpoint = elem.getAttribute('ns');
      joinNs(nsEndpoint);
    });
  });
  joinNs('/wiki');
});


