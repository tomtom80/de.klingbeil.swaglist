import _ from 'lodash';
import React, { Component } from 'react';
import { connect } from 'react-redux';
import { fetchWishes } from '../actions'

class WishList extends Component {

    componentWillMount() {
        this.props.fetchWishes();
    }

    render() {
        return (
            <ul className="list-group">
                {this.renderList()}
            </ul>)
    }

    renderList() {
        return _.map(this.props.wishes, (wish) => {
            return this.renderItem(wish)
        });
    }

    renderItem(wish) {
        return (
            <li key={wish.id} className="list-group-item"><h5>{wish.name}</h5>{wish.description}</li>
        )
    }
}

function mapStateToProps(state) {
    console.log(state)
    return { wishes: state.wishes }
}

export default connect(mapStateToProps, { fetchWishes })(WishList);