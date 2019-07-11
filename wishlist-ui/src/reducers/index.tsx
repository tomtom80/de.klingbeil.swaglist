import { AppState } from '../types'
import { combineReducers } from 'redux';

import {
  wishlistReducer,
} from './WishlistReducer';


export const rootReducer = combineReducers<AppState>({
  wishlists: wishlistReducer,
});