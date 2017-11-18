import axios from 'axios';
export const FETCH_WISHES = "fetch_wishes";
export const CREATE_WISH = "create_wish";

const ROOT_URL = "http://localhost:8080";

export function fetchWishes() {
    const url = `${ROOT_URL}/wishlist`;
    const request = axios.get(url);
    console.log(request);
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

