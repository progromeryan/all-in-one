// T必须是primary value
class DataStorage<T extends string | number> {
    private data: T[] = [];

    addItem(item: T) {
        this.data.push(item);
    }

    removeItem(item: T) {
        this.data.splice(this.data.indexOf(item), 1);
    }

    getItems() {
        return [...this.data];
    }
}

const textStorage = new DataStorage<string>();
textStorage.addItem('item');


const numStorage = new DataStorage<number>();


/**
 * utility types
 */


/**
 * Partial type
 */

interface CourseGoal {
    title: string;
    description: string;
    completeUntil: Date;
}

function createCourseGoal(title: string, description: string, date: Date): CourseGoal {
    let courseGoal: Partial<CourseGoal> = {}; // 最终将变成CourseGoal，但是现在不是，不要现在报错
    courseGoal.title = title;
    courseGoal.description = description;
    courseGoal.completeUntil = date;
    return courseGoal as CourseGoal;
}


/**
 * Readonly
 */

const names: Readonly<string[]> = ['max', 'min'];
// names.push("hello") 会报错


/**
 * union | 和generic的区别
 * union可同时接受多种类型
 * private data: (string | number | boolean)[] = []
 * data = ["hello", 123, true]
 *
 * generic只能接受其中的一种
 * class DataStorage<T extends string | number>
 * T必须是string或者number之一，而且类中的T类型也必须一致
 */

const data: (string | number | boolean)[] = []
data.push('hello');
data.push(123);
data.push(true);

class DataStorageGeneric<T extends string | number> {
    private data: T[] = [];

    addItem(item: T) {
        this.data.push(item);
    }

    removeItem(item: T) {
        this.data.splice(this.data.indexOf(item), 1);
    }

    getItems() {
        return [...this.data];
    }
}

const dataStorageGeneric = new DataStorageGeneric<string>();
dataStorageGeneric.addItem('hello');
