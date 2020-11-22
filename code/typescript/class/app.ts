class Department {
    public name: string;
    private employees: string[];

    constructor(n: string) {
        this.name = n;
    }

    describe() {
        console.log('Department: ' + this.name);
    }

    addEmployee(employee: string) {
        this.employees.push(employee);
    }

    addEmployeeInfo() {
        console.log(this.employees.length);
    }
}

const accounting = new Department('Accounting');
accounting.describe(); // 正常

const accountingCopy = {describe: accounting.describe}
accountingCopy.describe(); // 打印出undefined，因为this指向了accountingCopy，它没有name，如何避免？

/**
 class Department {
    name: string;

    constructor(n: string) {
        this.name = n;
    }

    describe(this: Department) { // 添加this可以避免上面的问题，当运行accountingCopy.describe();时会报错
        console.log('Department: ' + this.name);
    }
}
 */


accounting.addEmployee('max');
accounting.addEmployee('min');


// 简捷的写法
abstract class DepartmentShort {
    // 会直接赋值给class中的fields
    // readonly表示只能初始化的时候赋值一次，并且不能更高
    constructor(private readonly id: string, public name: string, protected employees: string[]) {
    }

    addEmployee(name: string) {
        if (name === 'max') {
            return;
        }

        this.employees.push(name);
    }

    // abstract
    // 在父类声明一个方法
    // 子类必须实现这个方法
    abstract deleteEmployee(name: string): void;
}

// inherit
class ITDepartment extends DepartmentShort {
    private lastReport: string;

    // getter and setter
    get mostRecentReport() {
        return this.lastReport;
    }

    set mostRecentReport(value: string) {
        this.lastReport = value;
    }


    constructor(id: string, public admins: string[]) {
        super(id, 'it', []); // 继承父类，并初始化
    }

    // override
    addEmployee(name: string) {
        if (name === 'min') {
            return;
        }
    }

    // static函数
    static createEmployee(name: string) {
        console.log(name);
    }

    deleteEmployee(name: string) {
        console.log("delete");
    }
}

const it = new ITDepartment("123", []);
// 使用getter和setter
const report = it.mostRecentReport;
it.mostRecentReport = 'hello';

ITDepartment.createEmployee("JACOB");

// singleton
class DepartmentSingleton {
    public name: string;
    private static instance: DepartmentSingleton;

    private constructor(n: string) {
        this.name = n;
    }

    static getInstance() {
        if (DepartmentSingleton.instance) {
            return this.instance;
        }

        this.instance = new DepartmentSingleton("jacob");
    }
}
