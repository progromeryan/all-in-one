var stars = document.getElementsByClassName("star");

for (var i = 0; i < stars.length; i++) {
  stars[i].addEventListener("mouseenter", function (event) {
    var srcEleId = event.srcElement.id;
    for (var j = 0; j < stars.length; j++) {
      if (stars[j].id <= srcEleId) {
        stars[j].style.color = "#d4af37";
        stars[j].className = "fas fa-star star";
      } else {
        stars[j].style.color = "black";
        stars[j].className = "far fa-star star";
      }
    }
  });
}

var rating = document.getElementsByClassName("rating")[0];
rating.addEventListener("mouseleave", function (event) {
  for (var i = 0; i < stars.length; i++) {
    stars[i].style.color = "black";
    stars[i].className = "far fa-star star";
  }
});
