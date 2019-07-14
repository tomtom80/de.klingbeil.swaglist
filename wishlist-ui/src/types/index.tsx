import { FormReducer, FormState, FormStateMap } from "redux-form";
import { ReducersMapObject, AnyAction, Reducer } from "redux";

// wishlist type
export interface Wishlist {
    identifier: string,
    wishlistName: string,
    wishlistType: string
}

// wishlist state
export interface WishlistState {
    readonly wishlists: Wishlist[];
}

// application state
export interface AppState {
    wishlists: WishlistState;
    form: any;
}