import React, { Component } from 'react';
import { connect } from 'react-redux';
import { fetchWish, createWish } from '../actions';

class WishEditor extends Component {
  constructor(props) {
    super(props);
    this.state = { name: '', description: '' };
    this.onNameInputChange = this.onNameInputChange.bind(this);
    this.onDescriptionInputChange = this.onDescriptionInputChange.bind(this);
    this.onFormSubmit = this.onFormSubmit.bind(this);
  }

  componentWillReceiveProps(nextProps) {
    if (nextProps.proposalName) {
      this.setState({
        name: nextProps.proposalName,
      });
    }
    if (nextProps.proposalDescription) {
      this.setState({
        description: nextProps.proposalDescription,
      });
    }
  }

  onNameInputChange(event) {
    this.setState({ name: event.target.value });
  }

  onDescriptionInputChange(event) {
    this.setState({ description: event.target.value });
  }

  onFormSubmit(event) {
    event.preventDefault();
    const wish = { name: this.state.name, description: this.state.description };
    this.props.createWish(wish, (id) => {
      this.props.fetchWish(id);
    });
    this.setState({ name: '', description: '' });
  }

  renderNameField() {
    return (
      <div className="form-group row">
        <label className="col-sm-2 col-form-label" htmlFor="wishName">
          Name:
        </label>
        <div className="col-sm-10">
          <input
            value={this.state.name}
            onChange={this.onNameInputChange}
            id="wishName"
            type="text"
            className="form-control"
            placeholder="Enter Name"
          />
        </div>
      </div>
    );
  }
  renderDescriptionField() {
    return (
      <div className="form-group row">
        <label className="col-sm-2 col-form-label" htmlFor="wishDescription">
          Description:
        </label>
        <div className="col-sm-10">
          <input
            value={this.state.description}
            onChange={this.onDescriptionInputChange}
            id="wishDescription"
            type="text"
            className="form-control"
            placeholder="Enter Description"
          />
        </div>
      </div>
    );
  }

  render() {
    return (
      <form onSubmit={this.onFormSubmit}>
        {this.renderNameField()}
        {this.renderDescriptionField()}
        <button type="submit" className="btn btn-primary">
          Add
        </button>
      </form>
    );
  }
}

function mapStateToProps(state) {
  let result = {};
  if (state.details !== {} && state.details.successful) {
    result = {
      proposalName: state.details.title,
      proposalDescription: state.details.description,
    };
  }
  return result;
}

export default connect(mapStateToProps, { createWish, fetchWish })(WishEditor);
