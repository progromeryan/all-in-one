Currying：因为是美国数理逻辑学家哈斯凯尔·加里(Haskell Curry)发明了这种函数使用技巧。

所以这样用法就以他的名字命名为 Currying，中文翻译为“柯里化”。

Currying is a transformation of functions that translates a function from callable as f(a, b, c) into callable as f(a)(b)(c).

Currying doesn’t call a function. It just transforms it.

```javascript
function curry(f){ // curry(f) does the currying transform
  return function(a){
    return function(b){
      return f(a, b);
    };
  };
}

// usage
function sum(a, b){
  return a + b;
}

let curriedSum = curry(sum);

alert(curriedSum(1)(2)); // 3
```

```javascript
function sum(a, b){
  return a + b;
}

let curriedSum = _.curry(sum); // 使用来自 lodash 库的 _.curry

alert(curriedSum(1, 2)); // 3，仍可正常调用
alert(curriedSum(1)(2)); // 3，以偏函数的方式调用
```

# 例子

要了解它的好处，我们需要一个实际中的例子。

例如，我们有一个用于格式化和输出信息的日志（logging）函数 `log(date, importance, message)`。

在实际项目中，此类函数具有很多有用的功能，例如通过网络发送日志（log），在这儿我们仅使用 alert：

```javascript
function log(date, importance, message){
  alert(`[${date.getHours()}:${date.getMinutes()}] [${importance}] ${message}`);
}
```

让我们将它柯里化！

`log = _.curry(log);` 柯里化之后，log 仍正常运行：

```javascript
log(new Date(), "DEBUG", "some debug"); // log(a, b, c)
```

但是也可以以柯里化形式运行：

```javascript
log(new Date())("DEBUG")("some debug"); // log(a)(b)(c)
```

现在，我们可以轻松地为当前日志创建便捷函数：

```javascript
// logNow 会是带有固定第一个参数的日志的偏函数
let logNow = log(new Date());
// 使用它 
logNow("INFO", "message"); // [HH:mm] INFO message 
```

现在，logNow 是具有固定第一个参数的 log，换句话说，就是更简短的“偏应用函数（partially applied function）”或“偏函数（partial）”。

偏函数(Partial Function)解决这样的问题：*如果我们有函数是多个参数的，我们希望能固定其中某几个参数的值*。

我们可以更进一步，为当前的调试日志（debug log）提供便捷函数：

```javascript
let debugNow = logNow("DEBUG");

debugNow("message"); // [HH:mm] DEBUG message 所以：
```

柯里化之后，我们没有丢失任何东西：log 依然可以被正常调用。

我们可以轻松地生成偏函数，例如用于生成今天的日志的偏函数。

# 未知参数个数的currying

```javascript
var curry = function(f){
  var len = f.length;

  return function t(){
    var innerLength = arguments.length;
    var args = Array.prototype.slice.call(arguments);

    // 递归出口，f.length
    if (innerLength >= len) {
      return f.apply(undefined, args)
    } else {
      return function(){
        var innerArgs = Array.prototype.slice.call(arguments);
        var allArgs = args.concat(innerArgs);

        return t.apply(undefined, allArgs)
      }
    }
  }
}

// 测试一下
function add(num1, num2){
  return num1 + num2;
}

var curriedAdd = curry(add);
curriedAdd(2)(3);     //5

// 一个参数
function identity(value){
  return value;
}

var curriedIdentify = curry(identify);
curriedIdentify(4) // 4
```
