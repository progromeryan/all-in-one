import React, { Component } from 'react';
import { connect } from 'react-redux';
import GuessWords from "./GuessWords";
import Congrats from "./Congrats";
import { getSecretWord } from './actions';
import Input from './Input';

export class UnconnectedApp extends Component {
  componentDidMount(){
    // get the secret word
    this.props.getSecretWord();
  }

  render(){
    return (
      <div>
        <Congrats success={this.props.success}/>
        <Input/>
        <GuessWords guessedWords={this.props.guessedWords}/>
      </div>
    );
  }
}

const mapStateToProps = ( state ) => {
  const { success, guessedWords, secretWord } = state;
  return { success, guessedWords, secretWord };
}

export default connect(mapStateToProps, { getSecretWord })(UnconnectedApp);

