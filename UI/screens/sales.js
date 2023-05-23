import React, {Component} from 'react';
import {StyleSheet, ImageBackground, Image, TouchableHighlight } from 'react-native';
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
    Col,
    Card, 
    Right,
    CardItem,
    Footer,
    Container,
    ListItem
} from "native-base";

var theProp;

export default class sales extends Component {

  constructor(props){
    super(props);

    this.theProp = props.navigation.state.params;

    //alert(JSON.stringify(props.navigation.state.params.userId));

    this.state={
      slideHeading: "PURCHASE",
      slideImage: require('../img/purchase_white_Sam.png'),
      link:'Sales',
      slideImageStyle: {
        flex: 1,
      width:120,
      marginTop:20,
      marginBottom:25
      }
    }
  }

  linkFunc(){
    if(this.state.link == 'Sales'){
      this.props.navigation.navigate('Purchase',this.theProp);
    }

    if(this.state.link == "Refill"){
      this.props.navigation.navigate('Refill',this.theProp);
    }

    if(this.state.link == "Stove"){
      this.props.navigation.navigate('Stove',this.theProp);
    }
  }

  exchangeSlide(){
    this.setState({slideHeading:"PURCHASE"}),
    this.setState({slideImage:require('../img/purchase_white_Sam.png')}),
    this.setState({slideImageStyle:{
      flex: 1,
      width:120,
      marginTop:20,
      marginBottom:25
    }})
  this.setState({link:'Sales'})
  }

  refillSlide(){
    this.setState({slideHeading:"REFILL"}),
    this.setState({slideImage:require('../img/refill_white_Sam.png')}),
    this.setState({slideImageStyle:{
      flex: 1,
      width:120,
      marginTop:20,
      marginBottom:25
    }})
    this.setState({link:'Refill'})
  }


  stovesSlide(){
    this.setState({slideHeading:"STOVES"}),
    this.setState({slideImage:require('../img/stoveIcon_white_Sam.png')}),
    this.setState({slideImageStyle:{
      flex: 1,
      width:120,
      marginTop:20,
      marginBottom:25
    }})
    this.setState({link:'Stove'})
  }

  render() {
    return (
      <ImageBackground source={require('../img/home_bg1.png')} style={styles.backgroundImage}>
          <Grid>
              <Row style={styles.toprow}>
                <Left style={{marginLeft:15}}>
                    <Icon name="bars" color="white" size={30}/>
                </Left>
                <Body>
                    <Text style={styles.titleHeader}>SALES</Text>
                </Body><Right></Right>
              </Row>
              <Row style={styles.middlerow}>
                <Body style={{marginTop:40}}>
                    <Text style={styles.textH1}>{this.state.slideHeading}</Text>
                    <Image source={this.state.slideImage} style={this.state.slideImageStyle}/> 
                    <Row style={styles.center}>
                      <Button rounded style={{width:280,backgroundColor:"#FD6721"}} onPress={() => {this.linkFunc()} }>
                        <Text style={{marginLeft:92}}>PROCEED</Text>
                      </Button>
                    </Row>
                </Body>
              </Row>
              <Row style={styles.bottomrow}>
              <Content>
          <Card style={{marginLeft:15,marginRight:15, height:160}}>
            <CardItem>
                <Body>
                  <TouchableHighlight underlayColor="white" onPress={() => this.exchangeSlide()} >
                    <Image source={require('../img/purchase_orange.png')} style={styles.logo}/>                 
                  </TouchableHighlight>
                </Body>
                <Body>
                  <TouchableHighlight underlayColor="white" onPress={() => this.refillSlide()}>
                    <Image source={require('../img/refill_orange.png')} style={styles.logo}/>
                  </TouchableHighlight>
                </Body>
                <Body>
                  <TouchableHighlight underlayColor="white" onPress={() => this.stovesSlide()}>
                  <Image source={require('../img/stoveIcon_orange.png')} style={styles.logo}/>
                  </TouchableHighlight>
                </Body>
                </CardItem>
                </Card>
                </Content>
              </Row>
          </Grid>
          
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
  toprow: {
      //backgroundColor:'#FD6721',
      height:90
    },
    middlerow: {
        //backgroundColor:'#FD6721',
        height:370
    },
    bottomrow: {
        height:null,
        //backgroundColor: "#FD6721"
    },
  titleHeader: {
    color: 'white',
    fontSize:36,
  },
  center: {
    textAlign:'center',
    alignSelf:'center'
  },
  slideLogoGas:{
    flex: 0.9,
    width:80,
    marginTop:25,
    marginBottom:30
  },
  slideLogoStove:{
    flex: 0.9,
    width:150,
    marginTop:20,
    marginBottom:25
  },
  textH1: {
    alignSelf: 'center',
    color: 'white',
    fontSize: 40
  },
  textH2: {
    alignSelf: 'center',
    color: 'white',
    fontSize: 20
  },
  iconText: {
    alignSelf: 'center',
    color: '#FD6721',
    fontSize: 20,
    marginTop: -25
  },
  logo: {
    flex: 1,
    width: 80,
    height: 100,
    marginTop: -35,
    marginLeft:17,
    alignSelf:'center',
    resizeMode: 'center'
  }
});
