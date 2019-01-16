import React, {Component} from 'react';
import PropTypes from 'prop-types'
import { View } from 'react-native';
import { FormInput, FormValidationMessage } from 'react-native-elements'

import { isEmpty } from '../../../auth/utils/validate'
import styles from "./styles"

class WishTextInput extends Component {
    render() {
        const {  placeholder, autoFocus, onChangeText, secureTextEntry, placeholderTextColor, keyboardType } = this.props;

        return (
            <View style={styles.container}>
                <FormInput
                    autoCapitalize='none'
                    clearButtonMode='while-editing'
                    underlineColorAndroid={"#fff"}
                    placeholder={placeholder}
                    autoFocus={autoFocus}
                    onChangeText={onChangeText}
                    containerStyle={styles.containerStyle}
                    inputStyle={styles.inputContainer}
                    placeholderTextColor={placeholderTextColor}
                    keyboardType={keyboardType}
                    value={this.props.value}/>
                {
                    (!isEmpty(this.props.error)) &&
                    <FormValidationMessage>
                        {this.props.error}
                    </FormValidationMessage>
                }
            </View>
        );
    }
}

WishTextInput.propTypes = {
    placeholder: PropTypes.string,
    autoFocus: PropTypes.bool,
    onChangeText: PropTypes.func.isRequired,
    value: PropTypes.string,
    error: PropTypes.string,
}

WishTextInput.defaultProps = {
    autoFocus: true,
    placeholderTextColor: "grey",
    keyboardType: "default"
}

export default WishTextInput;