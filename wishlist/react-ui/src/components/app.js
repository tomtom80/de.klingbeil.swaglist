import React from 'react';
import WishList from '../containers/wish_list';
import WishInput from '../containers/wish_input';

export default function App() {
  return (
    <div>
      <div className="jumbotron">
        <div className="container">
          <h1 className="display-4">Welcome to Swag List</h1>
          <p className="lead">Where wishes come true.</p>
          <hr className="my-4" />
          <p className="lead">
            <WishInput />
          </p>
        </div>
      </div>
      <div className="row">
        <div className="col-12">
          <WishList />
        </div>
      </div>
    </div>
  );
}
