import _ from 'lodash';
import React, { Component } from 'react';
import WishList from '../containers/wish_list';
import WishInput from '../containers/wish_input';

export default class App extends Component {


  addWish(term) {
    console.log(term);
  }

  render() {
    return (
      <div>
        <WishInput />
        <WishList />
      </div>
    );
  }
}
