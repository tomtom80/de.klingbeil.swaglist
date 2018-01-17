import { combineReducers } from 'redux';
import WishesReducer from './reducer_wishes';
import { DetailsReducer, DetailsIsLoadingReducer } from './reducer_details';

const rootReducer = combineReducers({
  wishes: WishesReducer,
  details: DetailsReducer,
  detailsLoading: DetailsIsLoadingReducer,
});

export default rootReducer;
