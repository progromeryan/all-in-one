interface ValidatorConfig {
    [property: string]: {
        [validatableProp: string]: string[] // ['required'....]
    }
}


const registeredValidator: ValidatorConfig = {}

function Required(target: any, propName: string) {
    registeredValidator[target.constructor] = {
        ...registeredValidator[target.constructor.name],
        [propName]: ['required']
    }
}


function PositiveNumber(target: any, propName: string) {
    registeredValidator[target.constructor] = {
        ...registeredValidator[target.constructor.name],
        [propName]: ['positive']
    }
}

function validate(obj: any) {
    const objValidatorConfig = registeredValidator[obj.constructor.name];
    if (!objValidatorConfig) {
        return true;
    }

    let isValid = true;
    for (const prop in objValidatorConfig) {
        for (const validator of objValidatorConfig[prop]) {
            switch (validator) {
                case 'required':
                    isValid = isValid && !!obj[prop];
                    break;
                case 'positive':
                    isValid = isValid && obj[prop] > 0;
                    break;
            }
        }
    }

    return isValid;
}


class Course {
    @Required
    title: string;

    @PositiveNumber
    price: number;

    constructor(t: string, p: number) {
        this.title = t;
        this.price = p;
    }
}

const courseForm = document.querySelector('form')!;
courseForm.addEventListener('submit', event => {
    event.preventDefault();
    const titleElement = document.getElementById('title') as HTMLInputElement;
    const priceElement = document.getElementById('price') as HTMLInputElement;

    const title = titleElement.value;
    const price = +priceElement.value;

    const createdCourse = new Course(title, price); // 用户输入有可能是空，所以要验证

    if (!validate((createdCourse))) {
        alert('not valid');
        return;
    }
})
