// Import redux types
import { ActionCreator, Dispatch } from 'redux';
import { ThunkAction } from 'redux-thunk';
import axios from 'axios';

import { Wishlist, WishlistState } from '../types';


// Create Action Constants
export enum WishlistActionTypes {
    GET_ALL = 'GET_ALL',
}

// Interface for Get All Action Type
export interface WishlistGetAllAction {
    type: WishlistActionTypes.GET_ALL;
    wishlists: Wishlist[];
}

/* 
Combine the action types with a union (we assume there are more)
example: export type CharacterActions = IGetAllAction | IGetOneAction ... 
*/
export type WishlistActions = WishlistGetAllAction;

/* Get All Action
<Promise<Return Type>, State Interface, Type of Param, Type of Action> */
export const getAllWishlists: ActionCreator<
    ThunkAction<Promise<any>, WishlistState, null, WishlistGetAllAction>
> = () => {
    return async (dispatch: Dispatch) => {
        try {
            const url = `/wishlist`;
            const response = await axios.get(url);
            dispatch({
                type: WishlistActionTypes.GET_ALL,
                wishlists: response.data,
            });
        } catch (err) {
            console.error(err);
        }
    };
};