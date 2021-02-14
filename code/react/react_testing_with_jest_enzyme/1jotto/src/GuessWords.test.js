import React from 'react';
import { shallow } from 'enzyme';
import { findByTestAttr, checkProps } from "./test/testUtils";
import GuessWords from "./GuessWords";

const defaultProps = {
  guessedWords: [
    {guessedWord: 'train', letterMatchCount: 3}
  ]
}

const setup = ( props = {} ) => {
  const setupProps = {...defaultProps, ...props};
  return shallow(<GuessWords {...setupProps}/>)
};

test('does not throw warning with expected props', () => {
  checkProps(GuessWords, defaultProps);
});


// grouping tests
describe('if there are no words guessed', () => {
  let wrapper;
  beforeEach(() => {
    wrapper = setup({guessedWords: []});
  })

  test('render without error', () => {

    const component = findByTestAttr(wrapper, 'component-guessed-words');
    expect(component.length).toBe(1);
  });

  test('render instructions to guess a word', () => {
    const instructions = findByTestAttr(wrapper, 'guess-instructions');
    expect(instructions.length).not.toBe(0);
  })
})

describe('if there are words guessed', () => {
  const guessedWords = [
    {guessedWord: 'train', letterMatchCount: 3},
    {guessedWord: 'train2', letterMatchCount: 2},
    {guessedWord: 'train3', letterMatchCount: 3},
  ]
  let wrapper;
  beforeEach(() => {
    wrapper = setup({guessedWords: guessedWords});
  })

  test('render without error', () => {
    const component = findByTestAttr(wrapper, 'component-guessed-words');
    expect(component.length).toBe(1);
  });

  test('render guessed words section', () => {
    const component = findByTestAttr(wrapper, 'guessed-words');
    expect(component.length).toBe(1);
  })

  test('render correct number of guessed words', () => {
    const component = findByTestAttr(wrapper, 'guessed-word');
    expect(component.length).toBe(guessedWords.length);
  })
})


