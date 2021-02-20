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
const set = (obj, path, value) => {
  if (Object(obj) !== obj) return obj; // When obj is not an object
  // If not yet an array, get the keys from the string-path
  if (!Array.isArray(path)) path = path.toString().match(/[^.[\]]+/g) || [];
  path.slice(0, -1).reduce(
    (
      a,
      c,
      i // Iterate all of them except the last one
    ) =>
      Object(a[c]) === a[c] // Does the key exist and is its value an object?
        ? // Yes: then follow that path
          a[c]
        : // No: create the key. Is the next key a potential array-index?
          (a[c] =
            Math.abs(path[i + 1]) >> 0 === +path[i + 1]
              ? [] // Yes: assign a new array object
              : {}), // No: assign a new plain object
    obj
  )[path[path.length - 1]] = value; // Finally assign the value to the last key
  return obj; // Return the top-level object to allow chaining
};

// Demo
var obj = { test: true };
set(obj, "test.1.it", "hello");
console.log(obj); // includes an intentional undefined value
