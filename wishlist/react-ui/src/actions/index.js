import axios from 'axios';

export const FETCH_WISHES = 'fetch_wishes';
export const CREATE_WISH = 'create_wish';
export const FETCH_WISH = 'fetch_wish';
export const UPDATE_WISH = 'update_wish';
export const DELETE_WISH = 'delete_wish';
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

export function fetchWish(id) {
  const url = `/wishlist/${id}`;
  const request = axios.get(url);
  return {
    type: FETCH_WISH,
    payload: request,
  };
}

export function createWish(wish) {
  const url = '/wishlist';
  const request = axios.post(url, wish);
  return {
    type: CREATE_WISH,
    payload: request,
  };
}

export function deleteWish(id) {
  const url = `/wishlist/${id}`;
  axios.delete(url);
  return {
    type: DELETE_WISH,
    payload: id,
  };
}

export function updateWish(id, wish) {
  const url = `/wishlist/${id}`;
  const request = axios.put(url, wish);
  return {
    type: UPDATE_WISH,
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

function fetchDetails(term) {
  return (dispatch) => {
    dispatch(requestDetails(term));
    const url = '/content';
    return axios
      .post(url, { url: term })
      .then((response) => {
        throwErrorIfStatusNotOK(response);
        return response;
      })
      .then(response => response.data)
      .then((details) => {
        dispatch(successDetails(details));
      })
      .catch(error => dispatch(failureDetails(error.message)));
  };
}

export function fetchDetailsAndCreateWish(term) {
  return (dispatch, getState) =>
    dispatch(fetchDetails(term)).then(() => {
      const { details } = getState();
      let wish = { name: term };
      if (details && details.successful) {
        wish = { name: details.title, description: details.description };
      }
      return dispatch(createWish(wish));
    });
}
