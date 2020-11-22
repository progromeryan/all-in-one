var button = document.querySelector("button");
var input1 = document.getElementById("num1"); // ! 表示不会是空
var input2 = document.getElementById("num2");
function add(num1, num2) {
    return num1 + num2;
}
button.addEventListener("click", function () {
    // "2" + "3" => "23" cannot check the type, so get wrong result
    console.log(add(+input1.value, +input2.value)); // +可以把字符串变成数字
});
