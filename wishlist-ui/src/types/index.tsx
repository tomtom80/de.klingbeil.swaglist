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
}