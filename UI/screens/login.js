import React, {Component} from 'react';
import { StyleSheet, ImageBackground, Image,ActivityIndicator,Modal } from 'react-native';
import Icon from 'react-native-vector-icons/FontAwesome';
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
    Container,
    View
} from "native-base";
import { GoogleSignin, GoogleSigninButton, statusCodes } from 'react-native-google-signin';

var idTokenChanged,emailChanged;

export default class login extends Component {

  constructor(){
    super(),
    this.state= {
      loading:false,
      alertModal:false,
      errorMessage:"" 
    }
  }


  _signIn = async () => {
    this.setState({loading:true});  
    try {
      await GoogleSignin.hasPlayServices();
      GoogleSignin.configure({
        forceConsentPrompt: true,
        webClientId: '360562068743-e05pqs24u2da217fl32sfceicpd6j9tj.apps.googleusercontent.com',    
      });
      const userInfo = await GoogleSignin.signIn();
      this.setState({ userInfo, error: null });
      //alert(JSON.stringify(userInfo));
      this.signInIn(JSON.stringify(userInfo.idToken),JSON.stringify(userInfo.user.email));
      
    } catch (error) {
      this.setState({loading:false});  
      if (error.code === statusCodes.SIGN_IN_CANCELLED) {
        // sign in was cancelled
        this.setState({errorMessage:'Sign-in process cancelled'})
        this.setState({alertModal:!this.state.alertModal})
     
      } else if (error.code === statusCodes.IN_PROGRESS) {
        // operation in progress already
        this.setState({errorMessage:'Sign-in in progress'})
        this.setState({alertModal:!this.state.alertModal})
    
      } else if (error.code === statusCodes.PLAY_SERVICES_NOT_AVAILABLE) {
        this.setState({errorMessage:'Play services is not available or outdated'})
        this.setState({alertModal:!this.state.alertModal})
      } else {
        this.setState({errorMessage:'Unexpected error: ' + error.toString()})
        this.setState({alertModal:!this.state.alertModal})
        this.setState({
          error,
        });
      }
    }
  };


  getList(loginBody){

    fetch('http://GaserveTempBackend-env.7cvt4abrkt.us-west-2.elasticbeanstalk.com/gaserve/v1/lists')
    .then((response) => { return response.json() })
    .then((responseJson) => {
      //alert(JSON.stringify(loginBody.userId));
      this.setState({loading:false});  
      this.props.navigation.navigate('StockTake',{responseJson,userId:loginBody.userId});
    })
    .catch((error) =>{
      this.setState({loading:false});  
      this.setState({errorMessage:'Unexpected error: ' + error.toString()})
      this.setState({alertModal:!this.state.alertModal})
  });
  }

  getListHome(loginBody){

    fetch('http://GaserveTempBackend-env.7cvt4abrkt.us-west-2.elasticbeanstalk.com/gaserve/v1/lists')
    .then((response) => { return response.json() })
    .then((responseJson) => {
      //alert(loginBody.id);
      this.setState({loading:false});  
      this.props.navigation.navigate('Home',{responseJson,userId:loginBody.id});
    })
    .catch((error) =>{
      this.setState({loading:false});  
      this.setState({errorMessage:'Unexpected error: ' + error.toString()})
      this.setState({alertModal:!this.state.alertModal})
  });
  }

  
   
signInIn(tokenID,email){

  idTokenChanged=tokenID.replace("\"","").replace("\"","");
  emailChanged=email.replace("\"","").replace("\"","");
      try {
        fetch('http://gaserve-dev-env.eu-west-2.elasticbeanstalk.com/gaserve/v1/login', {
        method: 'POST',
        headers: {
          'X-ID-TOKEN':idTokenChanged,
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(
            {
              "email": emailChanged,
              "ip": "10.5.4.2",
              "mac": "AC-DA-BB-DA",
              "deviceType": "Android"
              }           
        )
       })
  .then((response) => { return  response.json() } )          
         .then(async (response) => {
           //alert(JSON.stringify(response));
           //alert(email);
           console.log(tokenID);
           if (JSON.stringify(response).includes('"userStatus":"ACTIVE"')) {             
            //alert(JSON.stringify(response.id)); 
            
             this.getListHome(response);
               
           } 
           if(JSON.stringify(response).includes('"userStatus":"STOCKTAKE_UNBALANCED"')){
            this.setState({loading:false});  
            this.setState({errorMessage:"Account Unbalanced and blocked, please contact your support team"})
            this.setState({alertModal:!this.state.alertModal})
            try {
              await GoogleSignin.revokeAccess();
              await GoogleSignin.signOut();
              this.setState({ userInfo: null }); // Remember to remove the user from your app's state as well
            } catch (error) {
              //console.error(error);
              this.setState({errorMessage:"Unable to sign out"})
              this.setState({alertModal:!this.state.alertModal})
            }
          }
          
          if(JSON.stringify(response).includes('"userStatus":"DISABLED"')){
            this.setState({loading:false});  
             this.setState({errorMessage:"Account Disabled, please contact your support team"})
              this.setState({alertModal:!this.state.alertModal})
             try {
              await GoogleSignin.revokeAccess();
              await GoogleSignin.signOut();
              this.setState({ userInfo: null }); // Remember to remove the user from your app's state as well
            } catch (error) {
              //console.error(error);
              
              this.setState({errorMessage:"Unable to sign out"})
              this.setState({alertModal:!this.state.alertModal})
            }
           }
           
           if(JSON.stringify(response).includes('"userStatus":"STOCKTAKE_PENDING"')){
            //alert("pendings");
             this.getList(response);
             //this.props.navigation.navigate('stockTake'); 
          }

          if(JSON.stringify(response).includes('"code":404')){
            this.setState({loading:false});  
            try {
              await GoogleSignin.revokeAccess();
              await GoogleSignin.signOut();
              this.setState({ userInfo: null }); // Remember to remove the user from your app's state as well
            } catch (error) {
              this.setState({errorMessage:"Unable to sign out"})
              this.setState({alertModal:!this.state.alertModal})
            }

            this.setState({errorMessage:"Your email is not registered on the system"})
            this.setState({alertModal:!this.state.alertModal})
            //this._signOut;
          }
          
  })
  .catch((error) => {
    this.setState({loading:false});  
    this.setState({errorMessage:'Unexpected error: ' + error.toString()})
    this.setState({alertModal:!this.state.alertModal})
}
);
    } catch(e) {
      return {error: true};
    }
    
  }

  _signOut = async () => { 
    try {
      await GoogleSignin.revokeAccess();
      await GoogleSignin.signOut();
      this.setState({ user: null }); // Remember to remove the user from your app's state as well
      this.setState({loading:false});  
    } catch (error) {
      this.setState({loading:false});  
      this.setState({errorMessage:"Unable to sign out"})
      this.setState({alertModal:!this.state.alertModal})
    }
  };

  render() {
    return (
      <Container>

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
                    <Text style={{alignSelf:'center',color:'white'}}>Signing in. . .</Text>
                    </Body>
                  </Row>
              </Grid>
          </Content>
          </View>
        </Modal>

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

        <ImageBackground source={require('../img/GaserveUI2_1.png')} style={styles.backgroundImage}>
          <View style={styles.bottom}>
            <Button bordered rounded light style={styles.signInButton} onPress={this._signIn}>
            
              <Text style={{color:'#FD6721'}}><Icon name='google' size={20} />    Sign in with google</Text>
            </Button>


            {/* The following code is for the create button */}
            {/* <Button bordered rounded light style={styles.createButton}>
              <Text>Create Account</Text>
            </Button> */}
            <Text style={styles.rightsCust}>All rights reserved.</Text>
          </View>

        </ImageBackground>

      </Container>
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
  signInButton: {
    borderWidth: 5,
    justifyContent: 'center',
    alignSelf: 'center',
    width: 300,
    backgroundColor:'white'
  },
  createButton: {
    borderWidth: 5,
    justifyContent: 'center',
    alignSelf: 'center',
    marginTop: 10,
    width: 300
  },
  bottom: {
    flex: 1,
    justifyContent: 'flex-end',
    alignItems: 'center',
    marginBottom: 60
  },
  googleIcon: {
    width: null,
    height: 50,
    flex: 1
  },
  rightsCust: {
    color: '#FFFFFF',
    justifyContent: 'flex-end',
    marginTop: 90,
  },
  textH2: {
    alignSelf: 'center',
    color: 'black',
    fontSize: 18,
    marginBottom:30
  },
});
