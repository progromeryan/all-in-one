const button = document.querySelector("button");
const input1 = document.getElementById("num1")! as HTMLInputElement; // ! 表示不会是空
const input2 = document.getElementById("num2")! as HTMLInputElement;

function add( num1: number, num2: number ) {
  return num1 + num2;
}

button.addEventListener("click", function () {
  // "2" + "3" => "23" cannot check the type, so get wrong result
  console.log(add(+input1.value, +input2.value)); // +可以把字符串变成数字
});
