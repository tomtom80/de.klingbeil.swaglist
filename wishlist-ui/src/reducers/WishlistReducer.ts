// Import Reducer type
import { Reducer } from 'redux';
import {
    WishlistActions,
    WishlistActionTypes,
} from '../actions/WishlistActions';
import { WishlistState } from '../types';



// Define the initial state
const initialWishlistState: WishlistState = {
    wishlists: [],
};

export const wishlistReducer: Reducer<WishlistState, WishlistActions> = (
    state = initialWishlistState,
    action
) => {
    switch (action.type) {
        case WishlistActionTypes.GET_ALL: {
            return {
                ...state,
                wishlists: action.wishlists,
            };
        }
        default:
            return state;
    }
};