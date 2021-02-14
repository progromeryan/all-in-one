import React from 'react';
import { shallow } from 'enzyme';
import { findByTestAttr, storeFactory } from "./test/testUtils";
import Input, { UnconnectedInput } from './Input';

const setup = ( initialState = {} ) => {
  const store = storeFactory(initialState);
  // 找到真正的input
  return shallow(<Input store={store}/>).dive().dive();
}

describe('render', () => {
  describe('word has no been guessed', () => {
    let wrapper;
    beforeEach(() => {
      const initialState = { success: false };
      wrapper = setup(initialState);
    })

    test('renders component without error', () => {
      const component = findByTestAttr(wrapper, "component-input");
      expect(component.length).toBe(1);
    });

    test('render input box', () => {
      const component = findByTestAttr(wrapper, "input-box");
      expect(component.length).toBe(1);
    });

    test('render submit button', () => {
      const component = findByTestAttr(wrapper, "submit-button");
      expect(component.length).toBe(1);
    });
  });

  describe('word has been guessed', () => {
    let wrapper;
    beforeEach(() => {
      const initialState = { success: true };
      wrapper = setup(initialState);
    })

    test('renders component without error', () => {
      const component = findByTestAttr(wrapper, "component-input");
      expect(component.length).toBe(1);
    });

    test('render not input box', () => {
      const component = findByTestAttr(wrapper, "input-box");
      expect(component.length).toBe(0);
    });

    test('render not submit button', () => {
      const component = findByTestAttr(wrapper, "submit-button");
      expect(component.length).toBe(0);
    });
  });
});

describe('redux props', () => {
  test('has success piece of state as prop', () => {
    const success = true;
    const wrapper = setup({ success });
    const successProp = wrapper.instance().props.success;
    expect(successProp).toBe(success);
  });

  test('`guessWord` action creator is a function prop', () => {
    const wrapper = setup();
    const guessWordProp = wrapper.instance().props.guessWord;
    expect(guessWordProp).toBeInstanceOf(Function);
  });
});

describe('`guessWord` action creator', () => {
  let guessWordMock;
  let wrapper;
  const guessedWord = "train";
  beforeEach(() => {
    // create a mock function for `getSecretWord`
    guessWordMock = jest.fn();

    // set up Input, with guessWordMock as a prop
    wrapper = shallow(<UnconnectedInput guessWord={guessWordMock}/>);

    // add value to input box
    wrapper.setState({ currentGuess: guessedWord });

    // simulate click on submit button
    const submit = findByTestAttr(wrapper, 'submit-button');
    submit.simulate('click', {
      preventDefault(){
      }
    });
  });
  test('`guessWord` was called once', () => {
    const guessWordCallCount = guessWordMock.mock.calls.length;
    expect(guessWordCallCount).toBe(1);
  });

  test('calls `guessWord with input value as argument`', () => {
    const guessWordArg = guessWordMock.mock.calls[0][0];
    expect(guessWordArg).toBe(guessedWord);
  });

  test('input box clears on submit', () => {
    expect(wrapper.state('currentGuess')).toBe('');
  })
});



