import react,{ Component } from 'react';
import {AppRegistry} from 'react-native';
import splash from './screens/splash';
import login from './screens/login';
import stocktake from './screens/stocktake';
import home from './screens/home';
import sales from './screens/sales';
import refill from './screens/refill';
import purchase from './screens/purchase';
import stove from './screens/stove';
import checkout from './screens/checkout';
import receiving1 from './screens/receiving1';
import sideNav from './screens/sideNav';
import {name as appName} from './app.json';
import {createStackNavigator, createAppContainer  } from 'react-navigation';
import { DrawerNavigator } from 'react-navigation';


const HomeScreenRouter = createStackNavigator(
  {
    Splash: {screen: splash},
    Login: { screen: login },
    StockTake: { screen: stocktake },
    Home: { screen: home },
    SideNav: { screen: sideNav},
    Sales: {screen: sales},
    Refill: {screen: refill},
    Purchase: {screen: purchase},
    Stove: {screen: stove},
    Receiving1: {screen: receiving1},
    Checkout: {screen: checkout}
  },
  {
    headerMode: 'none'
  }
);


AppRegistry.registerComponent(appName, () => createAppContainer(HomeScreenRouter));
//AppRegistry.registerComponent(appName, () => login);