import axios from 'axios';

export const FETCH_WISHES = 'fetch_wishes';
export const CREATE_WISH = 'create_wish';
export const FETCH_WISH = 'fetch_wish';

export function fetchWishes() {
  const url = '/wishlist';
  const request = axios.get(url);
  return {
    type: FETCH_WISHES,
    payload: request,
  };
}

export function fetchWish(id) {
  const url = `/wishlist/${id}`;
  const request = axios.get(url);
  return {
    type: FETCH_WISH,
    payload: request,
  };
}

export function createWish(term, callback) {
  const url = '/wishlist';
  axios.post(url, { name: term }).then((response) => {
    /* eslint prefer-destructuring: */
    const location = response.headers.location;
    const id = location.substring(response.headers.location.lastIndexOf('/') + 1);
    callback(id);
  });
  return {
    type: CREATE_WISH,
    payload: term,
  };
}
