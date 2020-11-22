function add(n1, n2, showRes) {
    if (showRes) {
        console.log(n1 + n2);
    }
    return n1 + n2;
}
var num1 = 5;
var num2 = 2.8;
var num3; // 如果没有初始化，可以加一个type，直接初始化就不用加了，因为初始化的值的type会被当做type
var res = add(num1, num2, true);
/**
 * object
 */
var person = {
    name: 'Jacob',
    age: 30
};
console.log(person);
