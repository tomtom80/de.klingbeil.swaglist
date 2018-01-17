import axios from 'axios';

export const FETCH_WISHES = 'fetch_wishes';
export const CREATE_WISH = 'create_wish';
export const FETCH_WISH = 'fetch_wish';
export const SCRAPE_DETAILS_REQUEST = 'scrape_details_request';
export const SCRAPE_DETAILS_SUCCESS = 'scrape_details_success';
export const SCRAPE_DETAILS_FAILURE = 'scrape_details_failure';

export function fetchWishes() {
  const url = '/wishlist';
  const request = axios.get(url);
  return {
    type: FETCH_WISHES,
    payload: request,
  };
}

function requestDetails(term) {
  return {
    type: SCRAPE_DETAILS_REQUEST,
    payload: term,
  };
}

function successDetails(details) {
  return {
    type: SCRAPE_DETAILS_SUCCESS,
    payload: details,
  };
}

function failureDetails(message) {
  return {
    type: SCRAPE_DETAILS_FAILURE,
    payload: message,
  };
}

function throwErrorIfStatusNotOK(response) {
  if (response.status !== 200) {
    throw new Error(response.statusText);
  }
}

export function fetchDetails(term, callback) {
  return (dispatch) => {
    dispatch(requestDetails(term));
    const url = '/content';
    axios
      .post(url, { url: term })
      .then((response) => {
        throwErrorIfStatusNotOK(response);
        return response;
      })
      .then(response => response.data)
      .then((details) => {
        dispatch(successDetails(details));
        callback(details);
      })
      .catch(error => dispatch(failureDetails(error.message)));
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
    if (callback) {
      /* eslint prefer-destructuring: */
      const location = response.headers.location;
      const id = location.substring(response.headers.location.lastIndexOf('/') + 1);
      callback(id);
    }
  });
  return {
    type: CREATE_WISH,
    payload: wish,
  };
}
