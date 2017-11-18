import _ from 'lodash';
import React, { Component } from 'react';
import { connect } from 'react-redux';

class WishList extends Component {

    componentWillMount() {
        console.log(this.props.wishes);
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
            <li key={wish.id} className="list-group-item"><h5>{wish.name}</h5>{wish.url}</li>
        )
    }
}

function mapStateToProps(state) {
    return { wishes: state.wishes }
}

export default connect(mapStateToProps, null)(WishList);