
import React from 'react';
import { View, ActivityIndicator } from 'react-native';
import { Button } from 'react-native-elements'
import LinkPreview from 'react-native-link-preview';
import { connect } from 'react-redux';
import WishTextInput from '../../components/WishTextInput'
import styles from "./styles"

class WishHome extends React.Component {
    constructor() {
        super();
        this.state = { wish: "" }
    }

    onChange(text) {
        this.setState({ wish: text })
    }

    onSubmit = () => {
        LinkPreview.getPreview(this.state.wish)
            .then(data => {alert(JSON.stringify(data))});
            this.setState({ wish: "" });
    }

    render() {
        if (this.props.isLoading) {
            return (
                <View style={styles.activityIndicator}>
                    <ActivityIndicator animating={true} />
                </View>
            )
        } else {
            return (
                <View style={styles.container}>
                    <WishTextInput key={"wish"}
                        placeholder={"make a wish"}
                        autoFocus={true}
                        onChangeText={(text) => this.onChange(text)}
                        value={this.state.wish}
                    />
                     <Button
                        raised
                        title={"wish"}
                        borderRadius={0}
                        containerViewStyle={styles.containerView}
                        buttonStyle={styles.button}
                        textStyle={styles.buttonText}
                        onPress={this.onSubmit} />
                </View>
            );
        }
    }
}

function mapStateToProps(state, props) {
    return {
        isLoading: state.homeReducer.isLoading,
    }
}

export default connect(mapStateToProps)(WishHome);