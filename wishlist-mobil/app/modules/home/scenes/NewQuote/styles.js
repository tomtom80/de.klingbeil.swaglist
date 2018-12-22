
import { StyleSheet } from 'react-native';
import { theme } from "../../index"
const { padding, normalize, color, fontSize, fontFamily } = theme;

const styles = StyleSheet.create({
    container:{
        flex: 1,
        backgroundColor: '#FFF'
    },

    topContainer:{
        flex: 1,
    },

    textInput: {
        paddingTop:normalize(8 * 3),
        paddingBottom:normalize(8 * 3),
        paddingHorizontal:normalize(8 * 2),
        flex: 1,
        fontSize: normalize(17 + 3),
        lineHeight: normalize(21 + 3),
        color: color.white,
        letterSpacing: .5,
        fontFamily: fontFamily.medium
    },

    button:{
        backgroundColor: "#FF553F",
        height: normalize(55),

    },

    buttonText:{
        color:color.white,
        fontWeight:"700",
        fontSize: fontSize.regular + 2,
    },


    bottomContainer:{
        height: normalize(49)
    },

    color:{
        height: normalize(25),
        width: normalize(25),
        borderRadius: normalize(25/2),
        marginHorizontal: padding
    }
});

export default styles;