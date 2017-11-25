import _ from 'lodash';
import React, { Component } from 'react';
import { connect } from 'react-redux';
import { fetchWishes } from '../actions';

class WishList extends Component {
  static renderItem(wish) {
    return (
      <li key={wish.id} className="list-group-item">
        <h5>{wish.name}</h5>
        {wish.description}
      </li>
    );
  }

  componentWillMount() {
    this.props.fetchWishes();
  }

  renderList() {
    return _.map(this.props.wishes, wish => WishList.renderItem(wish));
  }

  render() {
    return <ul className="list-group">{this.renderList()}</ul>;
  }
}

function mapStateToProps(state) {
  return { wishes: state.wishes };
}

export default connect(mapStateToProps, { fetchWishes })(WishList);
