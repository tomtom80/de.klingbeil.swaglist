import { combineReducers } from 'redux';
import WishesReducer from './reducer_wishes';
import DetailsReducer from './reducer_details';

const rootReducer = combineReducers({
  wishes: WishesReducer,
  details: DetailsReducer,
});

export default rootReducer;
