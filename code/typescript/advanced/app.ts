// intersection
type Admin = {
    name: string;
    privileges: string[];
};

type Employee = {
    name: string;
    startDate: Date;
}

type ElevateEmployee = Admin & Employee;

const e1: ElevateEmployee = {
    name: 'Jacob',
    privileges: ['create-server'],
    startDate: new Date(),
}

type Combinable = string | number;
type Numeric = number | boolean;

type Universal = Combinable | Numeric;

// discriminated union
interface Bird {
    type: 'bird', // literal type
    flyingSpeed: number;
}

interface Horse {
    type: 'horse',
    runningSpeed: number;
}

type Animal = Bird | Horse;

function moveAnimal(animal: Animal) {
    let speed = 0;
    switch (animal.type) {
        case 'bird':
            speed = animal.flyingSpeed;
            break;
        case 'horse':
            speed = animal.runningSpeed;
            break;
    }
}

moveAnimal({type: 'bird', flyingSpeed: 20})

// type casting
// 两种写法
const input1 = <HTMLInputElement>document.getElementById('user-input');

// !表示永远不会产生null
const input2 = document.getElementById('user-input')! as HTMLInputElement;

// index type - 动态添加属性
interface ErrorContainer { // {email: 'not an email', username: 'not a good name'}}
    id: string,

    [prop: string]: string; // 可以动态的添加别的fields
}

const errorBag: ErrorContainer = {
    id: '123',
    email: 'not an email',
    username: 'not a good name',
}


// function overload - 写在函数上边，表明类型
function add(a: number, b: number): number; // 如果a和b都是数字，那么一定返回数字
function add(a: string, b: string): string; // 如果a和b都是字符串，那么一定返回字符串
function add(a: Combinable, b: Combinable) {
    if (typeof a === 'string' || typeof b === 'string') {
        return a.toString() + b.toString();
    }

    return a + b;
}

let res = add('max', 'min');


// optional chaining

const fetchedUserData = {
    id: '123',
    name: 'max',
    job: {
        title: 'manager',
        description: 'good',
    },
}

res = fetchedUserData.job && fetchedUserData.job.title; // JS的方法来访问不确定的属性

res = fetchedUserData?.job?.title; // optional chaining TS的方法来访问不确定的属性

// nullish coalescing
const userInput = '';

let storedData = userInput || 'default';

// 如果只想在null或undefined的时候用default，而在空字符创的时候不用，可以使用nullish
storedData = userInput ?? 'default';





