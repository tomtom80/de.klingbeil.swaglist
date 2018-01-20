import _ from 'lodash';
import ReactPlaceholder from 'react-placeholder';
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
        className="list-group-item list-group-item-action flex-column align-items-start"
      >
        <div className="row">
          <div className="col-3">
            <ReactPlaceholder type="rect" ready={false} color="#E0E0E0">
              <p>Placeholder Replacement goes here</p>
            </ReactPlaceholder>
          </div>
          <div className="col-8">
            <h5 className="mb-1">{wish.name}</h5>
            <p className="mb-1">{wish.description}</p>
          </div>
          <div className="col-1">
            <i
              className="fa fa-times fa-lg"
              aria-hidden="true"
              onClick={() => {
                this.delete(wish.id);
              }}
            />
          </div>
        </div>
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
