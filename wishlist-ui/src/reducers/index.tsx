import { AppState } from '../types'
import { combineReducers } from 'redux';
import { reducer as formReducer } from 'redux-form'

import {
  wishlistReducer,
} from './WishlistReducer';


export const rootReducer = combineReducers<AppState>({
  wishlists: wishlistReducer,
  form: formReducer
});