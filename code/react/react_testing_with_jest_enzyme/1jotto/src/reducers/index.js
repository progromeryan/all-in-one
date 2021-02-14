import { combineReducers } from "redux";
import { successReducer } from "./successReducer";
import { guessedWordsReducer } from "./guessedWordsReducer";
import { secretWordReducer } from "./secretWordReducer";

export default combineReducers({
  success: successReducer,
  guessedWords: guessedWordsReducer,
  secretWord: secretWordReducer,
})
