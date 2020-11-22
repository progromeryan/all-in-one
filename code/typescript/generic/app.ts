/**
 * example
 */
// <>中的是存的数据的类型
const names: Array<string> = ['max', 'min'];

// <>中的事返回的类型
// 好处：editor知道返回的是一个string，可以提醒string的对应方法
const promise: Promise<string> = new Promise((resolve, reject) => {
    setTimeout(() => {
        resolve("hello");
    }, 2000);
})

promise.then(data => {
    data.split(" ");
})




