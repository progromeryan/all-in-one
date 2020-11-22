function add(n1: number, n2: number, showRes: boolean) {
    if (showRes) {
        console.log(n1 + n2);
    }
    return n1 + n2;
}


const num1 = 5;
const num2 = 2.8;
let num3: number; // 如果没有初始化，可以加一个type，直接初始化就不用加了，因为初始化的值的type会被当做type

const res = add(num1, num2, true);

/**
 * object, array, tuple, enum
 */

enum Role { ADMIN, READ_ONLY, AUTHOR }

const person: {
    name: string;
    age: number;
    hobbies: string[];
    role: [number, string]; // tuple, 只能有两个元素
    roleName: Role;
} = {
    name: 'Jacob',
    age: 30,
    hobbies: ["Sports", "Cooking"],
    role: [2, 'author'], // 假设只能有换一个role, 所以长度和type是固定的
    roleName: Role.ADMIN,
}

console.log(person);

/**
 * union: 可接受不同的type
 * type aliases: 自定义类型
 */

type Combinable = number | string;
type ConversionDescriptor = 'as-number' | 'as-text';

function combine(
    input1: Combinable,
    input2: Combinable,
    resultConversion: ConversionDescriptor
) {
    let res;
    if (typeof input1 === 'number' && typeof input2 === 'number' || resultConversion === 'as-number') {
        res = +input1 + +input2;
    } else {
        res = input1.toString() + input2.toString()
    }

    return res;
}

combine(20, 20, 'as-number');
combine('20', '20', 'as-number');
combine("jacob", "huang", 'as-text');


/**
 * function
 * function as type
 */

function add2(n1: number, n2: number): number {
    return n1 + n2;
}

function printRes(num: number): void {
    console.log('res: ' + num);
}


// let combineValue: Function;
// combineValue = add;

let combineValue: (a: number, b: number) => number;

function addAndHandle(n1: number, n2: number, cb: (num: number) => void) {
    const res = n1 + n2;
    cb(res);
}

/**
 * unknown
 */

let userInput: unknown;
userInput = 5;
userInput = 'hello';
