var links = document.getElementsByClassName("link");
for (var i = 0; i < links.length; i++) {
  var a = links[i];
  if (a.title !== "") {
    a.addEventListener("mouseover", createTip);
    a.addEventListener("mouseout", cancelTip);
  }
}

function createTip(ev) {
  var title = this.title;
  this.title = "";
  this.setAttribute("tooltip", title);

  var tooltipWrap = document.createElement("div");
  tooltipWrap.className = "tooltip";
  tooltipWrap.appendChild(document.createTextNode(title));

  var firstChild = document.body.firstChild;
  firstChild.parentNode.insertBefore(tooltipWrap, firstChild);

  var padding = 5;
  // 返回元素的大小及其相对于视口的位置
  var linkProps = this.getBoundingClientRect();
  var tooltipProps = tooltipWrap.getBoundingClientRect();
  var topPos = linkProps.top - (tooltipProps.height + padding);
  tooltipWrap.setAttribute(
    "style",
    "top:" + topPos + "px;" + "left:" + linkProps.left + "px;"
  );
}

function cancelTip(ev) {
  var title = this.getAttribute("tooltip");
  this.title = title;
  this.removeAttribute("tooltip");
  document.querySelector(".tooltip").remove();
}
