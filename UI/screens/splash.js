import React, {Component} from 'react';
import { StyleSheet, ImageBackground, Image } from 'react-native';
import { Right } from 'native-base';

export default class splash extends Component {

  componentWillMount(){
      // setInterval(()=>{
      //   this.props.navigation.navigate('Login')
      // },3000)
      
      setTimeout(()=>{
           this.props.navigation.navigate('Login')
         },
        2000
    )
  }

  render() {
    return (
      <ImageBackground source={require('../img/splash_bg.png')} style={styles.backgroundImage}> 
          <Image source={require('../img/gs_logo.png')} style={styles.logo}/>
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
  logo: {
    flex: 1,
    alignSelf: 'center',
    width: 160,
    height: 160,
    resizeMode: 'center'
  }
});
