import React from 'react';
import AppBar from '@material-ui/core/AppBar';
import Button from '@material-ui/core/Button';
import WishListIcon from '@material-ui/icons/ListAlt';
import Card from '@material-ui/core/Card';
import CardActions from '@material-ui/core/CardActions';
import CardContent from '@material-ui/core/CardContent';
import CardMedia from '@material-ui/core/CardMedia';
import CssBaseline from '@material-ui/core/CssBaseline';
import Grid from '@material-ui/core/Grid';
import Toolbar from '@material-ui/core/Toolbar';
import Typography from '@material-ui/core/Typography';
import Container from '@material-ui/core/Container';
import { StyleRules, Theme, withStyles } from '@material-ui/core/styles';
import { Wishlist } from '../types';
import { reduxForm, InjectedFormProps, Field } from 'redux-form';
import { compose } from "recompose";


import Paper from '@material-ui/core/Paper';
import InputBase from '@material-ui/core/InputBase';
import Divider from '@material-ui/core/Divider';
import IconButton from '@material-ui/core/IconButton';
import AddIcon from '@material-ui/icons/Add';
import ClearIcon from '@material-ui/icons/Clear';


const styles = (theme: Theme): StyleRules => ({
    container: {
        display: 'flex',
        flexWrap: 'wrap',
    },
    fab: {
        margin: theme.spacing(1),
    },
    button: {
        margin: theme.spacing(1),
    },
    icon: {
        marginRight: theme.spacing(2),
    },
    heroContent: {
        backgroundColor: theme.palette.background.paper,
        padding: theme.spacing(8, 0, 6),
    },
    heroButtons: {
        marginTop: theme.spacing(4),
    },
    cardGrid: {
        paddingTop: theme.spacing(8),
        paddingBottom: theme.spacing(8),
    },
    card: {
        height: '100%',
        display: 'flex',
        flexDirection: 'column',
    },
    cardMedia: {
        paddingTop: '56.25%', // 16:9
    },
    cardContent: {
        flexGrow: 1,
    },
    footer: {
        backgroundColor: theme.palette.background.paper,
        padding: theme.spacing(6),
    },



    root: {
        padding: '2px 4px',
        display: 'flex',
        alignItems: 'center',
        width: 400,
    },
    input: {
        marginLeft: 8,
        flex: 1,
    },
    iconButton: {
        padding: 10,
    },
    divider: {
        width: 1,
        height: 28,
        margin: 4,
    },
});



const renderTextField = ({
    label,
    input,
    meta: { touched, invalid, error },
    ...custom
}) => (
        <InputBase
            placeholder="Typ in a wishlist name"
            inputProps={{ 'aria-label': 'wishlist name' }}
            error={touched && invalid}
            {...input}
            {...custom}
        />
    )

interface Props {
    classes?: any;
    wishlists: Wishlist[];
}

@compose(
    withStyles(styles),
)
class WishlistForm extends React.Component<InjectedFormProps<Wishlist, Props> & Props> {

    render() {
        const { pristine, submitting, reset, handleSubmit, classes, wishlists } = this.props;
        return (
            <React.Fragment>
                <CssBaseline />
                <AppBar position="relative">
                    <Toolbar>
                        <WishListIcon className={classes.icon} />
                        <Typography variant="h6" color="inherit" noWrap={true}>
                            SwagList
                            </Typography>
                    </Toolbar>
                </AppBar>
                <main>
                    <div className={classes.heroContent}>
                        <Container maxWidth="sm">
                            <Typography component="h1" variant="h3" align="center" color="textPrimary" gutterBottom={true}>
                                SwagList
                                </Typography>
                            <Typography variant="h5" align="center" color="textSecondary" paragraph={true}>
                                Where wishes come true.
                                </Typography>
                            <div className={classes.heroButtons}>
                                <form onSubmit={handleSubmit}>
                                    <Grid container={true} spacing={2} justify="center">
                                        <Paper className={classes.root}>
                                            <Field
                                                className={classes.input}
                                                name="Name"
                                                component={renderTextField}
                                                placeholder="Typ in a wishlist name"
                                                inputProps={{ 'aria-label': 'wishlist name' }}
                                            />
                                            <IconButton className={classes.iconButton} type="submit" disabled={pristine || submitting} aria-label="Add">
                                                <AddIcon />
                                            </IconButton>
                                            <Divider className={classes.divider} />
                                            <IconButton color="primary" className={classes.iconButton} disabled={pristine || submitting} onClick={reset} aria-label="Clear">
                                                <ClearIcon />
                                            </IconButton>
                                        </Paper>
                                    </Grid>
                                </form>
                            </div>
                        </Container>
                    </div>

                    <Container className={classes.cardGrid} maxWidth="md">
                        <Grid container={true} spacing={4}>
                            {wishlists.map(card => (
                                <Grid item={true}
                                    key={card.identifier}
                                    xs={12}
                                    sm={6}
                                    md={4}>
                                    <Card className={classes.card}>
                                        <CardMedia
                                            className={classes.cardMedia}
                                            image="https://source.unsplash.com/random"
                                            title="Image title"
                                        />
                                        <CardContent className={classes.cardContent}>
                                            <Typography gutterBottom={true} variant="h5" component="h2">
                                                {card.wishlistName}
                                            </Typography>
                                            <Typography>
                                                This is a media card. You can use this section to describe the content.
                                                </Typography>
                                        </CardContent>
                                        <CardActions>
                                            <Button size="small" color="primary">
                                                View
                                                </Button>
                                            <Button size="small" color="primary">
                                                Edit
                                                </Button>
                                        </CardActions>
                                    </Card>
                                </Grid>
                            ))}
                        </Grid>
                    </Container>
                </main>
                {/* Footer */}
                <footer className={classes.footer}>
                    <Typography variant="h6" align="center" gutterBottom={true}>
                        Footer
        </Typography>
                    <Typography variant="subtitle1" align="center" color="textSecondary" component="p">
                        Something here to give the footer a purpose!
        </Typography>
                </footer>
                {/* End footer */}
            </React.Fragment>
        );
    }
}


export default reduxForm<Wishlist, Props>({ form: "wishlistForm" })(WishlistForm);