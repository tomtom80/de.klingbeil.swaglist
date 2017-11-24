import axios from 'axios';
export const FETCH_WISHES = "fetch_wishes";
export const CREATE_WISH = "create_wish";


export function fetchWishes() {
    const url = `/wishlist`;
    const request = axios.get(url);
    return {
        type: FETCH_WISHES,
        payload: request
    }
}

export function createWish(term) {
    return {
        type: CREATE_WISH,
        payload: term
    }
}

