/**
 * interface: 用来描述一个object的样子
 */
interface Named {
    // 可以加readonly，但是没有public, private...
    readonly name: string;
}

// interface可以继承
interface Greetable extends Named {
    age: number;

    greet(phrase: string): void;

    male?: string; // optional field
}

let user1: Greetable;

user1 = {
    name: 'max',
    age: 10,
    greet(phrase: string) {
        console.log(phrase)
    }
}

/**
 * 为什么需要interface而不用type
 * type Person... 也管用
 *
 * 可以用于class，使得class必须实现对应的field在interface中
 */

class Person implements Greetable {
    age: number;
    name: string;
    height?: string; // 可选field

    greet(phrase: string): void {
    }

    // 可选参数
    constructor(n?: string) {
        if (n) {
            this.name = n;
        }
    }
}


/**
 * interface 用于function
 */

interface addFn {
    (a: number, b: number): number;
}

let add: addFn;

add = (n1: number, n2: number) => {
    return n1 + n2;
}





