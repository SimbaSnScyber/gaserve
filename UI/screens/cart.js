import React, {Component} from 'react';
import { StyleSheet, Image } from 'react-native';
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
    Footer,
    Container
} from "native-base";

export default class cart extends Component {
  render() {
    return (
        <Container>
        <Content style={styles.box}>
          <Card>
            <CardItem>
              <Body>
                <Image source={require('../img/gascylinder.jpg')} style={styles.image}/>
              </Body>
              <Body>
                <Text style={styles.center}>Liquefied  Petroleum (LP) Gas</Text>
                <Text style={styles.center}>19.0 kg</Text>
              </Body>
              <Body>
                <Button transparent style={styles.close}><Icon name="times-circle" color="#FD6721" size={25} /></Button>
                <Button transparent style={styles.plus}><Icon name="plus" color="black" size={25} /></Button>
                <Text style={styles.quantity}>
                   0
                </Text>        
                <Button transparent style={styles.minus}><Icon name="minus" color="black" size={25} /></Button>
              </Body>
            </CardItem>
          </Card>
          <Card>
            <CardItem>
              <Body>
                <Image source={require('../img/cylinder1.jpg')} style={styles.image}/>
              </Body>
              <Body>
                <Text style={styles.center}>Liquefied  Petroleum (LP) Gas</Text>
                <Text style={styles.center}>9.0 kg</Text>
              </Body>
              <Body>
                <Button transparent style={styles.close}><Icon name="times-circle" color="#FD6721" size={25} /></Button>
                <Button transparent style={styles.plus}><Icon name="plus" color="black" size={25} /></Button>
                <Text style={styles.quantity}>
                   0
                </Text>        
                <Button transparent style={styles.minus}><Icon name="minus" color="black" size={25} /></Button>
              </Body>
            </CardItem>
          </Card>
          <Card>
            <CardItem>
              <Body>
                <Image source={require('../img/gascylinder.jpg')} style={styles.image}/>
              </Body>
              <Body>
                <Text style={styles.center}>Liquefied  Petroleum (LP) Gas</Text>
                <Text style={styles.center}>19.0 kg</Text>
              </Body>
              <Body>
                <Button transparent style={styles.close}><Icon name="times-circle" color="#FD6721" size={25} /></Button>
                <Button transparent style={styles.plus}><Icon name="plus" color="black" size={25} /></Button>
                <Text style={styles.quantity}>
                   0
                </Text>        
                <Button transparent style={styles.minus}><Icon name="minus" color="black" size={25} /></Button>
              </Body>
            </CardItem>
          </Card>
        </Content>
        <Footer style={styles.footer}>
            <Left style={{marginLeft:20}}>
                <Text style={styles.textH2}>Total</Text>
                <Text style={styles.textH1}>$5,000</Text>
            </Left>
            <Right>
                <Button rounded success style={{marginRight:30}}>
                    <Text>CHECKOUT</Text>
                </Button>
            </Right>
        </Footer>
      </Container>
    );
  }
}

const styles = StyleSheet.create({
    image: {
        flex: 1,
        alignSelf: 'center',
        width: 140,
        height: 140,
        resizeMode: 'center'
    },
    box: {
      marginTop:100
    },
    footer: {
      height:150, 
      backgroundColor:'white'
    },
    center: {
        textAlign:'center',
        alignSelf:'center',
        marginTop: 20
    },
    close:{
        marginTop:-25, 
        marginLeft: 82
    },
    plus:{
        alignSelf: 'center', 
        marginLeft: 18
    },
    minus:{
        alignSelf: 'center', 
        marginLeft: 18
    },
    quantity:{
        fontSize:22,
        alignSelf: 'center'
    },
    textH1: {
        color: 'black',
        fontSize: 40
      },
    textH2: {
        color: 'black',
        fontSize: 20
    },
    backgroundImage: {}
});
