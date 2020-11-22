// 可以得到class的constructor
// function Logger(constructor: Function) {
//     console.log('logger...');
//     console.log(constructor);
// }
//
//
// @Logger
// class Person {
//     name= 'max';
//
//     constructor() {
//         console.log('constructor');
//     }
// }
//
// const person = new Person();


import construct = Reflect.construct;

/**
 * 使用function就可以接收参数了
 */
function Logger(logString: string) {
    return function (constructor: Function) {
        console.log(logString);
        console.log(constructor);
    }
}

// 类似于angular的component decorator
function WithTemplate(template: string, hookId: string) {
    // 可以使用_表示一个参数，但是不会使用
    return function (originalConstructor: any) {
        const element = document.getElementById(hookId);
        const p = new originalConstructor();

        if (element) {
            element.innerHTML = template;
            element.querySelector('h1')!.textContent = p.name;
        }
    }
}

// 带返回值的decorator
function WithTemplateReturn(template: string, hookId: string) {
    // 可以返回一个新的constructor
    return function <T extends { new(...args: any[]): { name: string } }>(originalConstructor: T) {
        return class extends originalConstructor {
            constructor(..._: any[]) {
                super();
                const element = document.getElementById(hookId);
                const p = new originalConstructor();

                if (element) {
                    element.innerHTML = template;
                    element.querySelector('h1')!.textContent = p.name;
                }
            }
        }
    }
}


@Logger('Logging person')
@WithTemplate('<h1>person</h1>', 'app')
class Person {
    name = 'max';

    constructor() {
        console.log('constructor');
    }
}

const person = new Person();


