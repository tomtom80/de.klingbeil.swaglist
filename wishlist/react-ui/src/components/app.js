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
        <div className="row">
          <div className="col-12 mt-3">
            <WishInput />
          </div>
        </div >
        <div className="row mt-3">
          <div className="col-12">
            <WishList />
          </div>
        </div >
      </div >
    );
  }
}
