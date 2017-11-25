import _ from 'lodash';
import { FETCH_WISHES, FETCH_WISH } from '../actions';

export default function (state = {}, action) {
  let result = state;
  switch (action.type) {
    case FETCH_WISHES:
      result = _.mapKeys(action.payload.data, 'id');
      break;
    case FETCH_WISH:
      result = { ...state, [action.payload.data.id]: action.payload.data };
      break;
    default:
      break;
  }

  return result;
}
