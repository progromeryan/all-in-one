# Closure

## What is closure?

A closure is the combination of a function bundled together (enclosed) with references to its surrounding state (the lexical environment). In other words, a closure gives you **access to an outer function’s scope from an inner function**.

> 简单来讲就是函数内部用到了外部的变量

```javascript
var outerNum = 3;

var add = function () {
  var innerNum = 1;
  return outerNum + innerNum;
};

console.dir(add);
```

```javascript
var addTo = function (num) {
  var outerNum = 3;

  var add = function () {
    return outerNum + num;
  };

  return add;
};

var addThree = addTo(3);
var addFour = addTo(4);

console.dir(addThree);
console.dir(addFour);
```
