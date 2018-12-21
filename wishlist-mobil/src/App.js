import React from 'react';
import { registerRootComponent } from 'expo'
import { Provider } from 'react-redux';
import store from './store';
import Home from './components/home'

class App extends React.Component {
  render() {
    return (
      <Provider store={store}>
        <Home />
      </Provider>
    );
  }
}
export default registerRootComponent(App);
