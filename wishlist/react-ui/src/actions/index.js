

export const FETCH_WISHES = "fetch_wishes";
export const CREATE_WISH = "create_wish";

export function fetchWishes() {
    const request = [];

    return {
        type: FETCH_WISHES,
        paylaod: request
    }
}

export function createWish(term) {
    return {
        type: CREATE_WISH,
        payload: term
    }
}

