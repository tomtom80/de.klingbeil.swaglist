import { SCRAPE_DETAILS_REQUEST, SCRAPE_DETAILS_SUCCESS, SCRAPE_DETAILS_FAILURE } from '../actions';

export function DetailsReducer(state = {}, action) {
  switch (action.type) {
    case SCRAPE_DETAILS_SUCCESS:
      return action.payload;
    default:
      return state;
  }
}

export function DetailsIsLoadingReducer(state = false, action) {
  switch (action.type) {
    case SCRAPE_DETAILS_REQUEST:
      return true;
    case SCRAPE_DETAILS_SUCCESS:
    case SCRAPE_DETAILS_FAILURE:
      return false;
    default:
      return state;
  }
}
