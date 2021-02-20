var cardButtonGoBack = document.getElementById("card-button-go-back");
var cardButtonGoFront = document.getElementById("card-button-go-frontend");

var isCardBack = localStorage.getItem("isCardBack");

if (isCardBack && isCardBack === "true") {
  var front = document.getElementsByClassName("card__side--front")[0];
  front.style.transform = "rotateY(180deg)";

  var back = document.getElementsByClassName("card__side--back")[0];
  back.style.transform = "rotateY(0)";
  back.style.backgroundColor = "yellow";
}

var card = document.getElementsByClassName("card")[0];
card.removeAttribute("hidden");


cardButtonGoBack.addEventListener("click", function(){
  localStorage.setItem("isCardBack", "true");
  var front = document.getElementsByClassName("card__side--front")[0];
  front.style.transform = "rotateY(180deg)";

  var back = document.getElementsByClassName("card__side--back")[0];
  back.style.transform = "rotateY(0)";
  back.style.backgroundColor = "yellow";
});

cardButtonGoFront.addEventListener("click", function(){
  localStorage.setItem("isCardBack", "false");
  var front = document.getElementsByClassName("card__side--front")[0];
  front.style.transform = "rotateY(0)";
  front.style.backgroundColor = "green";

  var back = document.getElementsByClassName("card__side--back")[0];
  back.style.transform = "rotateY(180deg)";
});
