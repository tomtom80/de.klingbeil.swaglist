import { createStore, applyMiddleware } from 'redux';
import logger from 'redux-logger';
import promise from 'redux-promise';
import thunk from 'redux-thunk';
import reducers from '../reducers';

const middleWare = [thunk, promise];
if (process.env.NODE_ENV === 'development') {
  middleWare.push(logger);
}
const createStoreWithMiddleware = applyMiddleware(...middleWare)(createStore);

export default function () {
  return createStoreWithMiddleware(reducers);
}
