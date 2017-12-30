import React from 'react';
import WishList from '../containers/wish_list';
import WishInput from '../containers/wish_input';
import WishEditor from '../containers/wish_editor';

export default function App() {
  return (
    <div>
      <div className="row">
        <div className="col-12 mt-3">
          <WishInput />
        </div>
      </div>
      <div className="row">
        <div className="col-12 mt-3">
          <div className="wishEditor">
            <WishEditor />
          </div>
        </div>
      </div>
      <div className="row mt-3">
        <div className="col-12">
          <WishList />
        </div>
      </div>
    </div>
  );
}
