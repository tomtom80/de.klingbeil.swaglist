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
      <div className="col-lg-4 d-flex align-items-stretch">
        <div key={wish.id} className="card mb-3">
          <img className="card-img-top" src="..." alt="thumbnail" />
          <div className="card-body">
            <h5 className="card-title">{wish.name}</h5>
            <p className="card-text">{wish.description}</p>
          </div>
          <footer className="card-footer">
            <div className="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups">
              <button
                type="button"
                className="close"
                aria-label="Close"
                onClick={() => {
                  this.delete(wish.id);
                }}
              >
                <span aria-hidden="true">&times;</span>
              </button>
            </div>
          </footer>
        </div>
      </div>
    );
  }

  renderList() {
    return _.map(this.props.wishes, wish => this.renderItem(wish));
  }

  render() {
    return (
      <div className="container">
        <div className="row">{this.renderList()}</div>{' '}
      </div>
    );
  }
}

function mapStateToProps(state) {
  return { wishes: state.wishes };
}

export default connect(mapStateToProps, { fetchWishes, deleteWish })(WishList);
