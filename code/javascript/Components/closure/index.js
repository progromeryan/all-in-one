function test(count){
  for (let i = 0; i < count; i++) {
    setTimeout(function(){
      console.log("test1", i);
    }, 2000)
  }
}

test(10);

function test2(){
  for (var i = 0; i < 10; i++) {
    (function(j){//闭包
      setTimeout(function(){
        console.log("test2", j);//分别输出i的值
      }, 2000 * j)
    })(i);//闭包
  }
}

test2();
