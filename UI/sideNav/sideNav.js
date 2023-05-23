import React, {Component} from 'react';
import { StyleSheet, ImageBackground, Image } from 'react-native';
import Icon from 'react-native-vector-icons/FontAwesome5';
import {
    Header, 
    Title, 
    Content, 
    Button,  
    Left, 
    Body, 
    Text,
    Grid, 
    Row, 
    Card, 
    Right,
    CardItem,
    Footer
} from "native-base";

export default class sideNav extends Component {
  render() {
    return (
      <ImageBackground style={styles.backgroundImage}> 
          <Header transparent>
          <Right>
            <Button iconLeft transparent>
              <Icon name="angle-left" color="white" size={40} />
            </Button>
          </Right>
        </Header>
        <Grid>
            <Row style={{height:150}}>
                <Image source={require('../img/profile_pic.png')} style={styles.displayPic}/>
            </Row>
            <Row style={styles.rowHeight1}>
                <Text style={{fontSize:25}}>Retailer</Text>
            </Row>
            <Row style={styles.rowHeight2}>
                <Text style={styles.textH2}>gaserve@gaserve.com</Text>
            </Row>
            <Row style={styles.rowHeight}>
                <Button full transparent>
                    <Text style={styles.btnText}><Icon name="home" size={20} />   Home</Text>
                </Button>
            </Row>
            <Row style={styles.rowHeight}>
                <Button full transparent>
                    <Text style={styles.btnText}><Icon name="receipt" size={20} />     Sales</Text>
                </Button>
            </Row>
            <Row style={styles.rowHeight}>
                <Button full transparent>
                    <Text style={styles.btnText}><Icon name="shopping-cart" size={20} />    Sales Cart</Text>
                </Button>
            </Row>
            <Row style={styles.rowHeight}>
                <Button full transparent>
                    <Text style={styles.btnText}><Icon name="archive" size={20} />    Receiving</Text>
                </Button>
            </Row>
        </Grid>
        <Footer style={{backgroundColor: 'white'}}>
            <Button full transparent>
              <Text style={styles.btnText}><Icon name="sign-out-alt" size={20} /> Sign out</Text>
            </Button>
        </Footer>
          
      </ImageBackground>


    );
  }
}

const styles = StyleSheet.create({
  backgroundImage: {
    flex: 1,
    width: null,
    height: null,
    resizeMode: 'cover'
  },
  displayPic: {
    flex: 1,
    alignSelf: 'center',
    width: 160,
    height: 160,
    resizeMode: 'center'
  },
  btnText: {
    alignSelf: 'center',
    fontSize: 20,
    color: 'black',
  },
  rowHeight: {
    alignSelf: 'center',
    height: 60
  },
  rowHeight2: {
    alignSelf: 'center',
    height: 60,
  },
  rowHeight1: {
    alignSelf: 'center',
    height: 40
  },
  textH2: {
    color: 'black',
    fontSize: 20
  }
});
