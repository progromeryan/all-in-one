import { actionTypes } from "../actions";
import { successReducer } from './successReducer';


test('return default initial state of false when no action is passed', () => {
  const newState = successReducer(undefined, {});
  expect(newState).toBe(false);
});

test('return state of true upon receiving an action of type correct guess', () => {
  const newState = successReducer(undefined, {type: actionTypes.CORRECT_GUESS});
  expect(newState).toBe(true);
});
