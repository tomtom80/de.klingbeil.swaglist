import React from 'react';
import { connect } from 'react-redux';
import { AppState, Wishlist } from '../types';
import WishlistForm from './WishlistForm';

interface Props {
  wishlists: Wishlist[]
}

class App extends React.Component<Props> {
  submit = (values: any) => {
    // print the form values to the console
    console.log(values)
  }
  render() {
    const { wishlists } = this.props;
    return (
      <WishlistForm wishlists={wishlists} onSubmit={this.submit} />
    )
  }
}

const mapStateToProps = (state: AppState) => ({
  wishlists: state.wishlists.wishlists,
});

export default connect(
  mapStateToProps
)(App);