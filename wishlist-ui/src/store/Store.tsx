
import thunk from 'redux-thunk';
import { rootReducer } from '../reducers';
import { applyMiddleware, createStore, Store } from 'redux';

import { AppState } from '../types';

export default function configureStore(): Store<AppState, any> {
    return createStore(rootReducer, undefined, applyMiddleware(thunk));
}