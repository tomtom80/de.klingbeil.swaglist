import axios from 'axios';

export const FETCH_WISHES = 'fetch_wishes';
export const CREATE_WISH = 'create_wish';
export const FETCH_WISH = 'fetch_wish';
export const SCRAPE_DETAILS = 'scrape_details';

export function fetchWishes() {
  const url = '/wishlist';
  const request = axios.get(url);
  return {
    type: FETCH_WISHES,
    payload: request,
  };
}

export function scrapeDetails(term) {
  const url = '/content';
  const request = axios.post(url, { url: term });
  return {
    type: SCRAPE_DETAILS,
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

export function createWish(wish, callback) {
  const url = '/wishlist';
  axios.post(url, wish).then((response) => {
    /* eslint prefer-destructuring: */
    const location = response.headers.location;
    const id = location.substring(response.headers.location.lastIndexOf('/') + 1);
    callback(id);
  });
  return {
    type: CREATE_WISH,
    payload: wish,
  };
}
