var button = document.getElementById("myButton");

var currNum = 0;

button.addEventListener("click", function (event) {
  var newNode = document.createElement("button");
  newNode.innerText = ++currNum;
  this.parentNode.appendChild(newNode);
});
