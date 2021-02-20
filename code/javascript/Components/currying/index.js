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
        // 递归
        return t.apply(undefined, allArgs)
      }
    }
  }
}

// 测试一下
function add(num1, num2, num3){
  return num1 + num2 + num3;
}

var curriedAdd = curry(add);
curriedAdd(2)(3)(4);
