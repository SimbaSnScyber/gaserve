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

export default class sales1 extends Component {

  constructor(props){
    super(props),
    this.state={
      slideHeading: "EXCHANGE",
      slideImage: require('../img/exchange_white.png'),
      link:0,
      slideImageStyle: {
        flex: 0.9,
      width:160,
      marginTop:20,
      marginBottom:25
      }
    }
  }
  
  componentWillMount(){
    this.getList();
  }

  linkFunc(){
    if(this.state.link = "SalesCylinder"){
      this.props.navigation.navigate('SalesCylinder');
    }

    if(this.state.link = "Sale2"){
      this.props.navigation.navigate('"Sale2"');
    }

    if(this.state.link = "Sale2"){
      this.props.navigation.navigate('"Sale2"');
    }
  }


  getList(){

    fetch('http://GaserveTempBackend-env.7cvt4abrkt.us-west-2.elasticbeanstalk.com/gaserve/v1/lists')
    .then((response) => { return response.json() })
    .then((responseJson) => {
      alert(JSON.stringify(responseJson));
      //this.getListData = JSON.stringify(responseJson);
    })
    .catch((error) => alert(error));
  }

  exchangeSlide(){
    this.setState({slideHeading:"EXCHANGE"}),
    this.setState({slideImage:require('../img/exchange_white.png')}),
    this.setState({slideImageStyle:{
      flex: 0.9,
      width:160,
      marginTop:20,
      marginBottom:25
    }});
    this.setState({link:'SalesCylinder'})
  }

  refillSlide(){
    this.setState({slideHeading:"REFILL"}),
    this.setState({slideImage:require('../img/refill_white.png')}),
    this.setState({slideImageStyle:{
      flex: 0.9,
      width:120,
      marginTop:20,
      marginBottom:25
    }}),
    this.setState({link:'"SalesCylinder"'})
  }


  stovesSlide(){
    this.setState({slideHeading:"STOVES"}),
    this.setState({slideImage:require('../img/stove.png')}),
    this.setState({slideImageStyle:{
      flex: 0.9,
      width:150,
      marginTop:20,
      marginBottom:25
    }}),
    this.setState({link:"SalesCylinder"})
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
                    <Text style={styles.titleHeader}>Sales</Text>
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
                    <Image source={require('../img/exchange_orange.png')} style={styles.logo}/>                 
                  </TouchableHighlight>
                  <Text style={styles.iconText}>EXCHANGE</Text>
                </Body>
                <Body>
                  <TouchableHighlight underlayColor="white" onPress={() => this.refillSlide()}>
                    <Image source={require('../img/refill_orange.png')} style={styles.logo}/>
                  </TouchableHighlight>
                  <Text style={styles.iconText}>REFILL</Text>
                </Body>
                <Body>
                  <TouchableHighlight underlayColor="white" onPress={() => this.stovesSlide()}>
                  <Image source={require('../img/stove_orange.png')} style={styles.logo}/>
                  </TouchableHighlight>
                  <Text style={styles.iconText}>STOVES</Text>
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
