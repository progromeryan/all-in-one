import React from 'react';
import PropTypes from 'prop-types'

const GuessWords = ( props ) => {
  let contents;
  if (props.guessedWords.length === 0) {
    contents = (
      <span data-test="guess-instructions">try to guess</span>
    )
  } else {
    contents = <div data-test="guessed-words">
      {props.guessedWords.map(item => {
        return (
          <div data-test="guessed-word" key={item.guessedWord}>
            {item.guessedWord} {item.letterMatchCount}
          </div>
        )
      })}
    </div>
  }
  return (
    <div data-test="component-guessed-words">
      {contents}
    </div>
  )
}

GuessWords.propTypes = {
  guessedWords: PropTypes.arrayOf(
    PropTypes.shape({
      guessedWord: PropTypes.string.isRequired,
      letterMatchCount: PropTypes.number.isRequired,
    })
  ).isRequired,
}

export default GuessWords;
