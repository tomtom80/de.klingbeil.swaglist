import _ from 'lodash';
import { FETCH_WISHES, FETCH_WISH, CREATE_WISH, DELETE_WISH } from '../actions';

export default function (state = {}, action) {
  let result = state;
  switch (action.type) {
    case FETCH_WISHES:
      result = _.mapKeys(action.payload.data, 'id');
      break;
    case CREATE_WISH:
    case FETCH_WISH:
      result = { ...state, [action.payload.data.id]: action.payload.data };
      break;
    case DELETE_WISH:
      result = _.omit(state, action.payload);
      break;
    default:
      break;
  }

  return result;
}
