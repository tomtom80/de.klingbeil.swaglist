import _ from 'lodash';
import React, { Component } from 'react';
import { connect } from 'react-redux';
import { fetchWishes, deleteWish } from '../actions';

class WishList extends Component {
  componentWillMount() {
    this.props.fetchWishes();
  }

  delete(id) {
    this.props.deleteWish(id);
  }

  renderItem(wish) {
    return (
      <li
        key={wish.id}
        className="list-group-item list-group-item-action flex-column align-items-start wishItem"
      >
        <div className="d-flex w-100 justify-content-between">
          <h5 className="mb-1">{wish.name}</h5>
          <i
            className="fa fa-times fa-lg"
            aria-hidden="true"
            onClick={() => {
              this.delete(wish.id);
            }}
          />
        </div>
        <p className="mb-1">{wish.description}</p>
      </li>
    );
  }

  renderList() {
    return _.map(this.props.wishes, wish => this.renderItem(wish));
  }

  render() {
    return <ul className="list-group">{this.renderList()}</ul>;
  }
}

function mapStateToProps(state) {
  return { wishes: state.wishes };
}

export default connect(mapStateToProps, { fetchWishes, deleteWish })(WishList);
