import _ from 'lodash';
import { FETCH_WISHES, CREATE_WISH } from '../actions';

export default function (state = {}, action) {
    let result = state;
    switch (action.type) {
        case FETCH_WISHES:
            result = _.mapKeys(action.payload.data, 'id')
            break;
        case CREATE_WISH:
            const id = _.uniqueId();
            action.payload
            const wish = { id: id, name: action.payload };
            result = { ...state, [id]: wish }
            break;
    }
    return result;
}