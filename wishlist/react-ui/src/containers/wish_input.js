import React, { Component } from 'react';
import { connect } from 'react-redux';
import Spinner from 'react-spinkit';
import { createWish, fetchDetails, fetchWish } from '../actions';

class WishInput extends Component {
  constructor(props) {
    super(props);
    this.state = { term: '' };
    this.onInputChange = this.onInputChange.bind(this);
    this.onFormSubmit = this.onFormSubmit.bind(this);
  }

  onInputChange(event) {
    this.setState({ term: event.target.value });
  }

  onFormSubmit(event) {
    event.preventDefault();
    const { term } = this.state;
    this.props.fetchDetails(term, (details) => {
      let wish = { name: term };
      if (details.successful) {
        wish = { name: details.title, description: details.description };
      }
      this.props.createWish(wish, (id) => {
        this.props.fetchWish(id);
      });
    });
    this.setState({ term: '' });
  }

  renderOptionalSpinner() {
    if (this.props.isLoading) {
      return (
        <div className="spinner">
          <Spinner name="line-scale" />
        </div>
      );
    }
    return <div className="spinner" />;
  }

  render() {
    return (
      <div>
        <form onSubmit={this.onFormSubmit} className="input-group">
          <input
            value={this.state.term}
            onChange={this.onInputChange}
            type="text"
            className="form-control"
            placeholder="Make a wish..."
          />
          <span className="input-group-btn">
            <button className="btn btn-primary" type="submit">
              Wish
            </button>
          </span>
        </form>
        <div className="row">{this.renderOptionalSpinner()}</div>
      </div>
    );
  }
}

function mapStateToProps(state) {
  return {
    isLoading: state.detailsLoading,
  };
}

export default connect(mapStateToProps, { createWish, fetchDetails, fetchWish })(WishInput);
