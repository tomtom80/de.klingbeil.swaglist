import * as React from 'react';
import * as ReactDOM from 'react-dom';

/* Make the store available to all container 
components in the application without passing it explicitly */
import { Provider } from 'react-redux';

// Store type from Redux
import { Store } from 'redux';

// Import the store function and state
import { AppState } from './types';
import configureStore from './store/Store'

import App from './components/App';
import * as serviceWorker from './serviceWorker';
import { getAllWishlists } from './actions/WishlistActions';

interface IProps {
    store: Store<AppState>;
}

const Root: React.SFC<IProps> = props => {
    return (
      <Provider store={props.store}>
        <App />
      </Provider>
    );
  };
  
  // Generate the store
  const store = configureStore();
  store.dispatch(getAllWishlists());

  ReactDOM.render(<Root store={store} />, document.getElementById(
    'root'
  ) as HTMLElement);

// If you want your app to work offline and load faster, you can change
// unregister() to register() below. Note this comes with some pitfalls.
// Learn more about service workers: https://bit.ly/CRA-PWA
serviceWorker.unregister();
