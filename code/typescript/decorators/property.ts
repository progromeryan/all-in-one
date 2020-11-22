function Log(target: any, propertyName: string) {
    console.log(target, propertyName);
}

// 用在get/set
function Log2(target: any, name: string, descriptor: PropertyDescriptor) {
    console.log(target, name, descriptor);
}

// 用在function
function Log3(target: any, name: string | Symbol, descriptor: PropertyDescriptor) {
    console.log(target, name, descriptor);
}

// 用在parameters
// position表示第几个参数
function Log4(target: any, name: string | Symbol, position: number) {
    console.log(target, name, position);
}

class Product {
    @Log
    title: string;
    _price: number;

    @Log2
    set price(val: number) {
        this._price = val;
    }

    constructor(t: string, p: number) {
        this.title = t;
        this._price = p;
    }

    @Log3
    getPriceWithTax(@Log4 tax: number) {
        return this._price * (1 + tax);
    }
}

// autobind decorator
function autoBind(_: any, _2: string, descriptor: PropertyDescriptor) {
    const originalMethod = descriptor.value;
    const adjDescriptor: PropertyDescriptor = {
        configurable: true,
        enumerable: false,
        // 在函数运行之前运行
        get() {
            return originalMethod.bind(this); // this是get的object，所以永远指向原来的object
        },
    }

    return adjDescriptor;
}


class Printer {
    message = "this works";

    @autoBind
    showMessage() {
        console.log(this.message);
    }
}

const p = new Printer();

const button = document.querySelector('button')!;
button.addEventListener('click', p.showMessage)


