import React, {Component} from 'react';
import { StyleSheet, ImageBackground, Image,ActivityIndicator,Modal } from 'react-native';
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
    View,
    Container
} from "native-base";
import { GoogleSignin, GoogleSigninButton, statusCodes } from 'react-native-google-signin';

var theProp;

export default class sideNav extends Component {

  constructor(props) {
    super(props);

    //alert(JSON.stringify(props.navigation.state.params.userId));
    
    this.theProp = props.navigation.state.params;
    this.state={
      loading:false,
      alertModal:false,
      errorMessage:"" 
    }
  }



  signOut(){
    this.setState({loading:true});  
    try {
       GoogleSignin.revokeAccess();
       GoogleSignin.signOut();
      this.setState({ userInfo: null }); // Remember to remove the user from your app's state as well
      this.setState({loading:false});  
      this.props.navigation.navigate('Login');
    } catch (error) {
      //console.error(error);
      this.setState({loading:false});  
      this.setState({errorMessage:"Unable to sign out"})
      this.setState({alertModal:!this.state.alertModal})
    }
  }

  render() {
    return (
      <ImageBackground source={require('../img/headerimage.png')} style={styles.backgroundImage}> 

<Modal
          animationType="fade"
          transparent={true}
          visible={this.state.alertModal}
          onRequestClose={() => { }}>
          <View style={{backgroundColor:"rgba(0, 0, 0, 0.8)",flex:1,flexDirection:'row',alignItems:'center',justifyContent:'center'}}>
          <Content>
          <Card style={{marginLeft:15,marginRight:15,flex: 1}}>
            <CardItem>
              <Grid>
                  <Row>
                  </Row>
                  <Row>
                      <Body style={{marginTop:15,alignSelf:'center', justifyContext: 'center'}}>
                    <Text style={styles.textH2}>{this.state.errorMessage}</Text>
                    </Body>
                  </Row>
                  <Row>
                  </Row>
                  <Row style={{marginTop:15,marginBottom:15}}>
                      <Body style={{alignSelf:'center', justifyContext: 'center'}}>
                            <Button full rounded success style={{alignSelf:'center', justifyContext: 'center',width:100}} onPress={() => {this.setState({alertModal:!this.state.alertModal})}}>
                          <Text >OK</Text>
                          </Button>
                      </Body>
                  </Row>
              </Grid>
            </CardItem>
          </Card>
          </Content>
          </View>
        </Modal>

        <Modal
          animationType="fade"
          transparent={true}
          visible={this.state.loading}
          onRequestClose={() => {
          }}>
          <View style={{backgroundColor:"rgba(0, 0, 0, 0.8)",flex:1,flexDirection:'row',alignItems:'center',justifyContent:'center' }}>
          <Content>
              <Grid>
              <Row>
                      <Body style={{justifyContent: 'center',alignSelf:'center'}}>
                      <ActivityIndicator style={{justifyContent: 'center',alignSelf:'center',marginTop:30}} size={80} color="#FD6721" />
                    </Body>
                  </Row>
                  <Row>
                      <Body style={{marginTop:30}}>
                    <Text style={{alignSelf:'center',color:'white'}}>Signing out. . .</Text>
                    </Body>
                  </Row>
              </Grid>
          </Content>
          </View>
        </Modal>

          <Header transparent>
          <Right>
            {/* <Button iconLeft transparent onPress={() => this.props.navigation.navigate('Home')}>
              <Icon name="angle-left" color="white" size={40} />
            </Button> */}
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
                {/*<Text style={styles.textH2}>gaserve@gaserve.com</Text>*/}
            </Row>
            <Row style={styles.rowHeight}>
                <Button full transparent onPress={() => this.props.navigation.navigate('Home',this.theProp)}>
                    <Text style={styles.btnText}><Icon name="home" size={20} />   Home</Text>
                </Button>
            </Row>
            <Row style={styles.rowHeight}>
                <Button full transparent onPress={() => this.props.navigation.navigate('Sales',this.theProp)}>
                    <Text style={styles.btnText}><Icon name="receipt" size={20} />     Sales</Text>
                </Button>
            </Row>
            {/* <Row style={styles.rowHeight}>
                <Button full transparent>
                    <Text style={styles.btnText}><Icon name="shopping-cart" size={20} />    Sales Cart</Text>
                </Button>
            </Row> */}
            <Row style={styles.rowHeight}>
                <Button full transparent onPress={() => this.props.navigation.navigate('Receiving1',this.theProp)}>
                    <Text style={styles.btnText}><Icon name="archive" size={20} />    Receiving</Text>
                </Button>
            </Row>
            <Row style={styles.rowHeight}>
            <Button full transparent onPress={() => this.signOut()}>
              <Text style={styles.btnText}><Icon name="sign-out-alt" size={20} /> Sign out</Text>
            </Button>
            </Row>
        </Grid>
          
      </ImageBackground>


    );
  }
}

const styles = StyleSheet.create({
  backgroundImage: {
    flex: 0.5,
    width: null,
    height: null,
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
