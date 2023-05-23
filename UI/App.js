import React, {Component} from 'react';
import { StyleSheet, ImageBackground, Image } from 'react-native';
import { View } from 'native-base';
import {createStackNavigator} from 'react-navigation';
import login from './screens/login';


export default class App extends Component{
  render() {
    return (
      <AppNav/>
    );
  }
}

const AppNav = createStackNavigator(
  {
      Login: {screen: login}
  }
)


