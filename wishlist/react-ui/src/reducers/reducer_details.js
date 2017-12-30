import { SCRAPE_DETAILS } from '../actions';

export default function (state = {}, action) {
  let result = state;
  switch (action.type) {
    case SCRAPE_DETAILS:
      result = action.payload.data;
      break;
    default:
      break;
  }

  return result;
}
