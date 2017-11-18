import React, { Component } from 'react';
import { connect } from 'react-redux';
import { createWish } from '../actions';

class WishInput extends Component {

    constructor(props) {
        super(props);

        this.state = { term: '' };
        this.onInputChange = this.onInputChange.bind(this);
        this.onFormSubmit = this.onFormSubmit.bind(this);
    }


    render() {
        return (
            <form onSubmit={this.onFormSubmit} className="input-group">
                <input value={this.state.term} onChange={this.onInputChange} type="text" className="form-control" placeholder="Make a wish..." />
                <span className="input-group-btn">
                    <button className="btn btn-primary" type="submit" >Add</button>
                </span>
            </form>
        )
    }

    onInputChange(event) {
        this.setState({ term: event.target.value })
    }

    onFormSubmit(event) {
        event.preventDefault();
        
        this.props.createWish(this.state.term);
        this.setState({ term: '' })
    }
}
export default connect(null, { createWish })(WishInput);