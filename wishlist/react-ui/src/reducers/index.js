import { combineReducers } from 'redux';
import WishesReducer from './reducer_wishes'

const rootReducer = combineReducers({
    wishes : WishesReducer
});

export default rootReducer;
