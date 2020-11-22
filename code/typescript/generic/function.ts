/**
 * function
 */

// extends限制了T/U必须是object类型的
function merge<T extends object, U extends object>(obja: T, objb: U) {
    return Object.assign(obja, objb);
}


interface Lengthy {
    length: number;
}

function countAndPrint<T extends Lengthy>(element: T): [T, string] {
    let descriptionText = 'got no element';

    if (element.length > 0) {
        descriptionText = 'got' + element.length + 'characters';
    }

    return [element, descriptionText]
}

/**
 * keyof
 */

// 表明U类型是T类型中的一个key
function extractAndConvert<T extends object, U extends keyof T>(obj: T, key: U) {
    return obj[key];
}

// 只有加了name在object中，才不会报错
extractAndConvert({name: 'hello'}, 'name');

