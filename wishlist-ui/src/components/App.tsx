import React from 'react';
import { useSelector } from 'react-redux'
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
import { makeStyles } from '@material-ui/core/styles';
import Container from '@material-ui/core/Container';
import TextField from '@material-ui/core/TextField'
import theme from "../theme";
import { ThemeProvider } from '@material-ui/styles';
import { AppState } from '../types';


const useStyles = makeStyles(theme => ({
  container: {
    display: 'flex',
    flexWrap: 'wrap',
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
}));

function onFormSubmit(event: any) {
  event.preventDefault();
  console.log("CLICK: " + event.currentTarget.value)
}

export default function SwagList() {
  const classes = useStyles();
  const wishlists = useSelector((state: AppState) => state.wishlists)

  return (
    <ThemeProvider theme={theme}>
      <React.Fragment>
        <CssBaseline />
        <AppBar position="relative">
          <Toolbar>
            <WishListIcon className={classes.icon} />
            <Typography variant="h6" color="inherit" noWrap>
              SwagList
          </Typography>
          </Toolbar>
        </AppBar>
        <main>
          {/* Hero unit */}
          <div className={classes.heroContent}>
            <Container maxWidth="sm">
              <Typography component="h1" variant="h3" align="center" color="textPrimary" gutterBottom>
                SwagList
            </Typography>
              <Typography variant="h5" align="center" color="textSecondary" paragraph>
                Where wishes come true.
            </Typography>
              <div className={classes.heroButtons}>
                <form onSubmit={onFormSubmit} className={classes.container} noValidate autoComplete="off">

                  <TextField
                    id="standard-full-width"
                    label="Name"
                    style={{ margin: 8 }}
                    placeholder='Typ in a name for your new wishlist'
                    fullWidth
                    margin="normal"
                    InputLabelProps={{
                      shrink: true,
                    }}
                  />
                  <Button variant="contained" color="primary" type="submit">
                    Create a Wishlist
                  </Button>
                </form>
              </div>
            </Container>
          </div>
          <Container className={classes.cardGrid} maxWidth="md">
            {/* End hero unit */}
            <Grid container spacing={4}>
              {wishlists.wishlists.map(card => (
                <Grid item key={card.identifier} xs={12} sm={6} md={4}>
                  <Card className={classes.card}>
                    <CardMedia
                      className={classes.cardMedia}
                      image="https://source.unsplash.com/random"
                      title="Image title"
                    />
                    <CardContent className={classes.cardContent}>
                      <Typography gutterBottom variant="h5" component="h2">
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
          <Typography variant="h6" align="center" gutterBottom>
            Footer
        </Typography>
          <Typography variant="subtitle1" align="center" color="textSecondary" component="p">
            Something here to give the footer a purpose!
        </Typography>
        </footer>
        {/* End footer */}
      </React.Fragment>
    </ThemeProvider>
  );
}