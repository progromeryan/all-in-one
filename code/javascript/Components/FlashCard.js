var card = document.getElementsByClassName("card__side")[0];

card.addEventListener("mouseover", function(){
  var front = document.getElementsByClassName("card__side--front")[0];
  front.style.transform = "rotateY(180deg)";

  var back = document.getElementsByClassName("card__side--back")[0];
  back.style.transform = "rotateY(0)";
  back.style.backgroundColor = "yellow";
});

card.addEventListener("mouseout", function(){
  var front = document.getElementsByClassName("card__side--front")[0];
  front.style.transform = "rotateY(0)";
  front.style.backgroundColor = "green";

  var back = document.getElementsByClassName("card__side--back")[0];
  back.style.transform = "rotateY(180deg)";
});
