function getText() {
  var text = "";
  var pTags = document.getElementsByTagName("p");
  for (var i = 0; i < pTags.length; i++) {
    var p = pTags[i];
    text += p.innerText || p.textContent;
  }

  return text;
}

var synth = window.speechSynthesis;

function speak() {
  if (synth.speaking) {
    console.error("speechSynthesis.speaking");
    return;
  }

  var text = getText();

  var utterThis = new SpeechSynthesisUtterance(text);
  utterThis.onend = function (event) {
    console.log("SpeechSynthesisUtterance.onend");
  };
  utterThis.onerror = function (event) {
    console.error("SpeechSynthesisUtterance.onerror");
  };

  utterThis.pitch = 1;
  utterThis.rate = 1;
  synth.speak(utterThis);
}

var playButton = document.getElementById("play");
playButton.addEventListener("click", function () {
  speak();
});
