class Car {
  constructor() {
    this.speed = 0;
  }

  increaseSpeed = (num) => {
    this.speed += num;
    console.log("current", this.speed);
  };

  decreaseSpeed = (num) => {
    this.speed -= num;

    if (this.speed < 0) {
      this.speed = 0;
    }
    console.log("current", this.speed);
  };
}

const myCar = new Car();

myCar.increaseSpeed(20);
myCar.decreaseSpeed(10);

class SuperCar extends Car {
  constructor() {
    super();
    this.name = "super car";
  }
}

const mySuperCar = new SuperCar();

mySuperCar.increaseSpeed(30);

// compare two objects
function deepEqual(object1, object2) {
  const keys1 = Object.keys(object1);
  const keys2 = Object.keys(object2);

  if (keys1.length !== keys2.length) {
    return false;
  }

  for (const key of keys1) {
    const val1 = object1[key];
    const val2 = object2[key];
    const areObjects = isObject(val1) && isObject(val2);
    if (
      (areObjects && !deepEqual(val1, val2)) ||
      (!areObjects && val1 !== val2)
    ) {
      return false;
    }
  }

  return true;
}

function isObject(object) {
  return object != null && typeof object === "object";
}

// lodash set
const isObject = (input) =>
  null !== input &&
  typeof input === "object" &&
  Object.getPrototypeOf(input).isPrototypeOf(Object);

const setByString = (obj, path, value) => {
  const pList = Array.isArray(path) ? path : path.split(".");
  const len = pList.length;
  // changes second last key to {}
  for (let i = 0; i < len - 1; i++) {
    const elem = pList[i];
    if (!obj[elem] || !isObject(obj[elem])) {
      obj[elem] = {};
    }
    obj = obj[elem];
  }

  // set value to second last key
  obj[pList[len - 1]] = value;
};
