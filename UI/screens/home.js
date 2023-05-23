import React, {Component} from 'react';
import { View,StyleSheet, ImageBackground,Modal, Image, ActivityIndicator } from 'react-native';
import Icon from 'react-native-vector-icons/FontAwesome';
import PureChart from 'react-native-pure-chart';
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
    Tab,
    Tabs,
    Col,
    Container
} from "native-base";

var userIdNavVhanged;
var theProp;

export default class home extends Component {

   componentWillMount() {
     this.getReport()  
   }

  constructor(props) {
    super(props);

    this.theProp = props.navigation.state.params;

   //alert(JSON.stringify(props.navigation.state.params.userId));
    this.userIdNavVhanged = JSON.stringify(props.navigation.state.params.userId).replace("\"","").replace("\"","");

    this.state = {
      removeModal:false,
      removeModal1:false,
      image:require('../img/gas.png'),
      btn1:{backgroundColor:"white"},
      btn2:{backgroundColor:"#C9C7C7",},
      btn3:{backgroundColor:"#C9C7C7"},

      pd:0, yd:0, td:0, tm:0, lm:0,
      sold_td:0,
      opening_stck:0,
      recevied_td:0,
      avail:0,

      gasSoldToday:0,cylinderSoldToday:0,stovesSoldToday:0,

      gasPreviousday:0,gasYesterday:0,gasToday:0,gasThisMonth:0,gasLastMonth:0,
      cylinderPreviousday:0,cylinderYesterday:0,cylinderToday:0,cylinderThisMonth:0,cylinderLastMonth:0,
      stovesPreviousday:0,stovesYesterday:0,stovesToday:0,stovesThisMonth:0,stovesLastMonth:0,

      gasOpening:0,gasReceived:0,gasAvailable:0,
      cylinderOpening:0,cylinderReceived:0,cylinderAvailable:0,
      stovesOpening:0,stovesReceived:0,stovesAvailable:0,

    };
    //this.getReport();// = this.getReport().bind(this);
  }

  getReport(){
    this.setState({removeModal:true}),
    this.setState({removeModal1:false})

    try {
      fetch('http://gaservebackendservices-dev-reports-1.eu-west-2.elasticbeanstalk.com/gaserve/v1/reports', {
          method: 'GET',
          headers: {
            'userId': this.userIdNavVhanged,
            'Content-Type':'application/json'
          }
        })
        .then((response) => {
          return response.json()
        })
        .catch((error) => {
          this.setState({removeModal:false});
          this.setState({removeModal1:true});
          //alert("Houston, we have a problem. It seem like there was an issue retrieving data. Let's try refresh the screen.")
        })
        .then((response) => {

          if(JSON.stringify(response.errorMessage)!=null){
            this.setState({removeModal:false});
            alert(JSON.stringify(response.errorMessage))
          }else{

          var report = response;
            var gasSoldToday1=0,cylinderSoldToday1=0,stovesSoldToday1=0;
    var gasPreviousday1=0,gasYesterday1=0,gasToday1=0,gasThisMonth1=0,gasLastMonth1=0;
    var gasOpening1 = 0,gasReceived1 = 0,gasAvailable1=0;
    var cylinderPreviousday1=0,cylinderYesterday1=0,cylinderToday1=0,cylinderThisMonth1=0,cylinderLastMonth1=0;
    var cylinderOpening1 = 0,cylinderReceived1 = 0,cylinderAvailable1=0;
    var stovesPreviousday1=0,stovesYesterday1=0,stovesToday1=0,stovesThisMonth1=0,stovesLastMonth1=0;
    var stovesOpening1 = 0,stovesReceived1 = 0,stovesAvailable1=0;

    var todayDate = new Date();
    var day = todayDate.getDate();
    var month = todayDate.getMonth() + 1;


    if(JSON.stringify(report.dailyReports[0]) != null){
      var firstDateMonth = parseInt(JSON.stringify(report.dailyReports[0].date).substring(6,8));
      var firstDateDay = parseInt(JSON.stringify(report.dailyReports[0].date).substring(9,11));
      
      
      if((month - firstDateMonth) == 0 ){
       
          if( (day - firstDateDay) == 0){
           
              gasSoldToday1 = parseFloat(report.dailyReports[0].reportEntries[0].quantity);
              cylinderSoldToday1 = parseFloat(report.dailyReports[0].reportEntries[3].quantity) + parseFloat(report.dailyReports[0].reportEntries[4].quantity) + parseFloat(report.dailyReports[0].reportEntries[5].quantity) + + parseFloat(report.dailyReports[0].reportEntries[6].quantity);
              stovesSoldToday1 = parseFloat(report.dailyReports[0].reportEntries[1].quantity) + parseFloat(report.dailyReports[0].reportEntries[2].quantity);
      
              gasToday1 = parseFloat(report.dailyReports[0].reportEntries[0].quantity);
              cylinderToday1 = parseFloat(report.dailyReports[0].reportEntries[3].quantity) + parseFloat(report.dailyReports[0].reportEntries[4].quantity) + parseFloat(report.dailyReports[0].reportEntries[5].quantity) + parseFloat(report.dailyReports[0].reportEntries[6].quantity);
              stovesToday1 = parseFloat(report.dailyReports[0].reportEntries[1].quantity) + parseFloat(report.dailyReports[0].reportEntries[2].quantity);
      
          }else if((day - firstDateDay) == 1){
              gasYesterday1 = parseFloat(report.dailyReports[0].reportEntries[0].quantity);
              cylinderYesterday1 = parseFloat(report.dailyReports[0].reportEntries[3].quantity) + parseFloat(report.dailyReports[0].reportEntries[4].quantity) + parseFloat(report.dailyReports[0].reportEntries[5].quantity) + parseFloat(report.dailyReports[0].reportEntries[6].quantity);
              stovesYesterday1 = parseFloat(report.dailyReports[0].reportEntries[1].quantity) + parseFloat(report.dailyReports[0].reportEntries[2].quantity);
      
          }else if((day - firstDateDay) == 2){
      
              gasPreviousday1 = parseFloat(report.dailyReports[0].reportEntries[0].quantity);
              cylinderPreviousday1 = parseFloat(report.dailyReports[0].reportEntries[3].quantity) + parseFloat(report.dailyReports[0].reportEntries[4].quantity) + parseFloat(report.dailyReports[0].reportEntries[5].quantity) + parseFloat(report.dailyReports[0].reportEntries[6].quantity);
              stovesPreviousday1 = parseFloat(report.dailyReports[0].reportEntries[1].quantity) + parseFloat(report.dailyReports[0].reportEntries[2].quantity);
      
          }else{}
      
      }else if( (month - firstDateMonth) == 1 ){
      if(firstDateMonth == 2){
      if(firstDateDay == 29){
      
        if( (firstDateDay - day) == 28){
      
          gasYesterday1 = parseFloat(report.dailyReports[0].reportEntries[0].quantity);
          cylinderYesterday1 = parseFloat(report.dailyReports[0].reportEntries[3].quantity) + parseFloat(report.dailyReports[0].reportEntries[4].quantity) + parseFloat(report.dailyReports[0].reportEntries[5].quantity) + parseFloat(report.dailyReports[0].reportEntries[6].quantity);
          stovesYesterday1 = parseFloat(report.dailyReports[0].reportEntries[1].quantity) + parseFloat(report.dailyReports[0].reportEntries[2].quantity);
      
        }else if((firstDateDay - day) == 27){
      
          gasPreviousday1 = parseFloat(report.dailyReports[0].reportEntries[0].quantity);
          cylinderPreviousday1 = parseFloat(report.dailyReports[0].reportEntries[3].quantity) + parseFloat(report.dailyReports[0].reportEntries[4].quantity) + parseFloat(report.dailyReports[0].reportEntries[5].quantity) + parseFloat(report.dailyReports[0].reportEntries[6].quantity);
          stovesPreviousday1 = parseFloat(report.dailyReports[0].reportEntries[1].quantity) + parseFloat(report.dailyReports[0].reportEntries[2].quantity);
      
        }else{}
      
      }else{
      
        if( (firstDateDay - day) == 27){
      
          gasYesterday1 = parseFloat(report.dailyReports[0].reportEntries[0].quantity);
          cylinderYesterday1 = parseFloat(report.dailyReports[0].reportEntries[3].quantity) + parseFloat(report.dailyReports[0].reportEntries[4].quantity) + parseFloat(report.dailyReports[0].reportEntries[5].quantity) + parseFloat(report.dailyReports[0].reportEntries[6].quantity);
          stovesYesterday1 = parseFloat(report.dailyReports[0].reportEntries[1].quantity) + parseFloat(report.dailyReports[0].reportEntries[2].quantity);
      
        }else if((firstDateDay - day) == 26){
      
          gasPreviousday1 = parseFloat(report.dailyReports[0].reportEntries[0].quantity);
          cylinderPreviousday1 = parseFloat(report.dailyReports[0].reportEntries[3].quantity) + parseFloat(report.dailyReports[0].reportEntries[4].quantity) + parseFloat(report.dailyReports[0].reportEntries[5].quantity) + parseFloat(report.dailyReports[0].reportEntries[6].quantity);
          stovesPreviousday1 = parseFloat(report.dailyReports[0].reportEntries[1].quantity) + parseFloat(report.dailyReports[0].reportEntries[2].quantity);
      
        }else{}
      
      }
      
      }else{
      
      if(firstDateDay == 30){
        
        if( (firstDateDay - day) == 29){
      
          gasYesterday1 = parseFloat(report.dailyReports[0].reportEntries[0].quantity);
          cylinderYesterday1 = parseFloat(report.dailyReports[0].reportEntries[3].quantity) + parseFloat(report.dailyReports[0].reportEntries[4].quantity) + parseFloat(report.dailyReports[0].reportEntries[5].quantity) + parseFloat(report.dailyReports[0].reportEntries[6].quantity);
          stovesYesterday1 = parseFloat(report.dailyReports[0].reportEntries[1].quantity) + parseFloat(report.dailyReports[0].reportEntries[2].quantity);
      
        }else if((firstDateDay - day) == 28){
      
          gasPreviousday1 = parseFloat(report.dailyReports[0].reportEntries[0].quantity);
          cylinderPreviousday1 = parseFloat(report.dailyReports[0].reportEntries[3].quantity) + parseFloat(report.dailyReports[0].reportEntries[4].quantity) + parseFloat(report.dailyReports[0].reportEntries[5].quantity) + parseFloat(report.dailyReports[0].reportEntries[6].quantity);
          stovesPreviousday1 = parseFloat(report.dailyReports[0].reportEntries[1].quantity) + parseFloat(report.dailyReports[0].reportEntries[2].quantity);
      
        }else{}
      
      }else{
      
        if( (firstDateDay - day) == 30){
      
          gasYesterday1 = parseFloat(report.dailyReports[0].reportEntries[0].quantity);
          cylinderYesterday1 = parseFloat(report.dailyReports[0].reportEntries[3].quantity) + parseFloat(report.dailyReports[0].reportEntries[4].quantity) + parseFloat(report.dailyReports[0].reportEntries[5].quantity) + parseFloat(report.dailyReports[0].reportEntries[6].quantity);
          stovesYesterday1 = parseFloat(report.dailyReports[0].reportEntries[1].quantity) + parseFloat(report.dailyReports[0].reportEntries[2].quantity);
      
        }else if((firstDateDay - day) == 29){
      
          gasPreviousday1 = parseFloat(report.dailyReports[0].reportEntries[0].quantity);
          cylinderPreviousday1 = parseFloat(report.dailyReports[0].reportEntries[3].quantity) + parseFloat(report.dailyReports[0].reportEntries[4].quantity) + parseFloat(report.dailyReports[0].reportEntries[5].quantity) + parseFloat(report.dailyReports[0].reportEntries[6].quantity);
          stovesPreviousday1 = parseFloat(report.dailyReports[0].reportEntries[1].quantity) + parseFloat(report.dailyReports[0].reportEntries[2].quantity);
      
        }else{}
      
      }
      
      }
      
      }else{}
      
      }
      
      if(JSON.stringify(report.dailyReports[1]) != null){
      var secondDateMonth = parseInt(JSON.stringify(report.dailyReports[1].date).substring(6,8));
      var secondDateDay = parseInt(JSON.stringify(report.dailyReports[1].date).substring(9,11));
      
      if((month - secondDateMonth) == 0 ){
      if( (day - secondDateDay) == 0){
      
      gasSoldToday1 = parseFloat(report.dailyReports[1].reportEntries[0].quantity);
      cylinderSoldToday1 = parseFloat(report.dailyReports[1].reportEntries[3].quantity) + parseFloat(report.dailyReports[1].reportEntries[4].quantity) + parseFloat(report.dailyReports[1].reportEntries[5].quantity) + parseFloat(report.dailyReports[1].reportEntries[6].quantity);
      stovesSoldToday1 = parseFloat(report.dailyReports[1].reportEntries[1].quantity) + parseFloat(report.dailyReports[1].reportEntries[2].quantity);
      
      gasToday1 = parseFloat(report.dailyReports[1].reportEntries[0].quantity);
      cylinderToday1 = parseFloat(report.dailyReports[1].reportEntries[3].quantity) + parseFloat(report.dailyReports[1].reportEntries[4].quantity) + parseFloat(report.dailyReports[1].reportEntries[5].quantity) + parseFloat(report.dailyReports[1].reportEntries[6].quantity);
      stovesToday1 = parseFloat(report.dailyReports[1].reportEntries[1].quantity) + parseFloat(report.dailyReports[1].reportEntries[2].quantity);
      
      }else if((day - secondDateDay) == 1){
      
      gasYesterday1 = parseFloat(report.dailyReports[1].reportEntries[0].quantity);
      cylinderYesterday1 = parseFloat(report.dailyReports[1].reportEntries[3].quantity) + parseFloat(report.dailyReports[1].reportEntries[4].quantity) + parseFloat(report.dailyReports[1].reportEntries[5].quantity) + parseFloat(report.dailyReports[1].reportEntries[6].quantity);
      stovesYesterday1 = parseFloat(report.dailyReports[1].reportEntries[1].quantity) + parseFloat(report.dailyReports[1].reportEntries[2].quantity);
      
      }else if((day - secondDateDay) == 2){
      
      gasPreviousday1 = parseFloat(report.dailyReports[1].reportEntries[0].quantity);
      cylinderPreviousday1 = parseFloat(report.dailyReports[1].reportEntries[3].quantity) + parseFloat(report.dailyReports[1].reportEntries[4].quantity) + parseFloat(report.dailyReports[1].reportEntries[5].quantity) + parseFloat(report.dailyReports[1].reportEntries[6].quantity);
      stovesPreviousday1 = parseFloat(report.dailyReports[1].reportEntries[1].quantity) + parseFloat(report.dailyReports[1].reportEntries[2].quantity);
      
      }else{}
      
      }else if( (month - secondDateMonth) == 1 ){
      if(secondDateMonth == 2){
      if(secondDateDay == 29){
      
        if( (secondDateDay - day) == 28){
      
          gasYesterday1 = parseFloat(report.dailyReports[1].reportEntries[0].quantity);
          cylinderYesterday1 = parseFloat(report.dailyReports[1].reportEntries[3].quantity) + parseFloat(report.dailyReports[1].reportEntries[4].quantity) + parseFloat(report.dailyReports[1].reportEntries[5].quantity) + parseFloat(report.dailyReports[1].reportEntries[6].quantity);
          stovesYesterday1 = parseFloat(report.dailyReports[1].reportEntries[1].quantity) + parseFloat(report.dailyReports[1].reportEntries[2].quantity);
      
        }else if((secondDateDay - day) == 27){
      
          gasPreviousday1 = parseFloat(report.dailyReports[1].reportEntries[0].quantity);
          cylinderPreviousday1 = parseFloat(report.dailyReports[1].reportEntries[3].quantity) + parseFloat(report.dailyReports[1].reportEntries[4].quantity) + parseFloat(report.dailyReports[1].reportEntries[5].quantity) + parseFloat(report.dailyReports[1].reportEntries[6].quantity);
          stovesPreviousday1 = parseFloat(report.dailyReports[1].reportEntries[1].quantity) + parseFloat(report.dailyReports[1].reportEntries[2].quantity);
      
        }else{}
      
      }else{
      
        if( (secondDateDay - day) == 27){
      
          gasYesterday1 = parseFloat(report.dailyReports[1].reportEntries[0].quantity);
          cylinderYesterday1 = parseFloat(report.dailyReports[1].reportEntries[3].quantity) + parseFloat(report.dailyReports[1].reportEntries[4].quantity) + parseFloat(report.dailyReports[1].reportEntries[5].quantity) + parseFloat(report.dailyReports[1].reportEntries[6].quantity);
          stovesYesterday1 = parseFloat(report.dailyReports[1].reportEntries[1].quantity) + parseFloat(report.dailyReports[1].reportEntries[2].quantity);
      
        }else if((secondDateDay - day) == 26){
      
          gasPreviousday1 = parseFloat(report.dailyReports[1].reportEntries[0].quantity);
          cylinderPreviousday1 = parseFloat(report.dailyReports[1].reportEntries[3].quantity) + parseFloat(report.dailyReports[1].reportEntries[4].quantity) + parseFloat(report.dailyReports[1].reportEntries[5].quantity) + parseFloat(report.dailyReports[1].reportEntries[6].quantity);
          stovesPreviousday1 = parseFloat(report.dailyReports[1].reportEntries[1].quantity) + parseFloat(report.dailyReports[1].reportEntries[2].quantity);
      
        }else{}
      
      }
      
      }else{
      
      if(secondDateDay == 30){
      
        if( (secondDateDay - day) == 29){
      
          if( gasYesterday1 == 0 ){
            gasYesterday1 = parseFloat(report.dailyReports[1].reportEntries[0].quantity);
            cylinderYesterday1 = parseFloat(report.dailyReports[1].reportEntries[3].quantity) + parseFloat(report.dailyReports[1].reportEntries[4].quantity) + parseFloat(report.dailyReports[1].reportEntries[5].quantity) + parseFloat(report.dailyReports[1].reportEntries[6].quantity);
            stovesYesterday1 = parseFloat(report.dailyReports[1].reportEntries[1].quantity) + parseFloat(report.dailyReports[1].reportEntries[2].quantity);
          }
      
        }else if((secondDateDay - day) == 28){
      
          gasPreviousday1 = parseFloat(report.dailyReports[1].reportEntries[0].quantity);
          cylinderPreviousday1 = parseFloat(report.dailyReports[1].reportEntries[3].quantity) + parseFloat(report.dailyReports[1].reportEntries[4].quantity) + parseFloat(report.dailyReports[1].reportEntries[5].quantity) + parseFloat(report.dailyReports[1].reportEntries[6].quantity);
          stovesPreviousday1 = parseFloat(report.dailyReports[1].reportEntries[1].quantity) + parseFloat(report.dailyReports[1].reportEntries[2].quantity);
      
        }else{}
      
      }else{
        alert(secondDateDay - day)
        if( (secondDateDay - day) == 28){
          gasPreviousday1 = parseFloat(report.dailyReports[1].reportEntries[0].quantity);
          cylinderPreviousday1 = parseFloat(report.dailyReports[1].reportEntries[3].quantity) + parseFloat(report.dailyReports[1].reportEntries[4].quantity) + parseFloat(report.dailyReports[1].reportEntries[5].quantity) + parseFloat(report.dailyReports[1].reportEntries[6].quantity);
          stovesPreviousday1 = parseFloat(report.dailyReports[1].reportEntries[1].quantity) + parseFloat(report.dailyReports[1].reportEntries[2].quantity);
      
        }else{}
      
      }
      
      }
      
      }else{}
      
      }
      
      if(JSON.stringify(report.dailyReports[2]) != null){
      var thirdDateMonth = parseInt(JSON.stringify(report.dailyReports[2].date).substring(6,8));
      var thirdDateDay = parseInt(JSON.stringify(report.dailyReports[2].date).substring(9,11));
      
      if((month - thirdDateMonth) == 0 ){
      
      if( (day - thirdDateDay) == 0){
      
      gasSoldToday1 = parseFloat(report.dailyReports[2].reportEntries[0].quantity);
      cylinderSoldToday1 = parseFloat(report.dailyReports[2].reportEntries[3].quantity) + parseFloat(report.dailyReports[2].reportEntries[4].quantity) + parseFloat(report.dailyReports[2].reportEntries[5].quantity) + parseFloat(report.dailyReports[2].reportEntries[6].quantity);
      stovesSoldToday1 = parseFloat(report.dailyReports[2].reportEntries[1].quantity) + parseFloat(report.dailyReports[2].reportEntries[2].quantity);
      
      gasToday1 = parseFloat(report.dailyReports[2].reportEntries[0].quantity);
      cylinderToday1 = parseFloat(report.dailyReports[2].reportEntries[3].quantity) + parseFloat(report.dailyReports[2].reportEntries[4].quantity) + parseFloat(report.dailyReports[2].reportEntries[5].quantity) + parseFloat(report.dailyReports[2].reportEntries[6].quantity);
      stovesToday1 = parseFloat(report.dailyReports[2].reportEntries[1].quantity) + parseFloat(report.dailyReports[2].reportEntries[2].quantity);
      
      }else if((day - thirdDateDay) == 1){
      
      gasYesterday1 = parseFloat(report.dailyReports[2].reportEntries[0].quantity);
      cylinderYesterday1 = parseFloat(report.dailyReports[2].reportEntries[3].quantity) + parseFloat(report.dailyReports[2].reportEntries[4].quantity) + parseFloat(report.dailyReports[2].reportEntries[5].quantity) + parseFloat(report.dailyReports[2].reportEntries[6].quantity);
      stovesYesterday1 = parseFloat(report.dailyReports[2].reportEntries[1].quantity) + parseFloat(report.dailyReports[2].reportEntries[2].quantity);
      
      }else if((day - thirdDateDay) == 2){
      
      gasPreviousday1 = parseFloat(report.dailyReports[2].reportEntries[0].quantity);
      cylinderPreviousday1 = parseFloat(report.dailyReports[2].reportEntries[3].quantity) + parseFloat(report.dailyReports[2].reportEntries[4].quantity) + parseFloat(report.dailyReports[2].reportEntries[5].quantity) + parseFloat(report.dailyReports[2].reportEntries[6].quantity);
      stovesPreviousday1 = parseFloat(report.dailyReports[2].reportEntries[1].quantity) + parseFloat(report.dailyReports[2].reportEntries[2].quantity);
      
      }else{}
      
      }else if( (month - thirdDateMonth) == 1 ){
      if(thirdDateMonth == 2){
      if(thirdDateDay == 29){
      
        if( (thirdDateDay - day) == 28){
      
          gasYesterday1 = parseFloat(report.dailyReports[2].reportEntries[0].quantity);
          cylinderYesterday1 = parseFloat(report.dailyReports[2].reportEntries[3].quantity) + parseFloat(report.dailyReports[2].reportEntries[4].quantity) + parseFloat(report.dailyReports[2].reportEntries[5].quantity) + parseFloat(report.dailyReports[2].reportEntries[6].quantity);
          stovesYesterday1 = parseFloat(report.dailyReports[2].reportEntries[1].quantity) + parseFloat(report.dailyReports[2].reportEntries[2].quantity);
      
        }else if((thirdDateDay - day) == 27){
      
          gasPreviousday1 = parseFloat(report.dailyReports[2].reportEntries[0].quantity);
          cylinderPreviousday1 = parseFloat(report.dailyReports[2].reportEntries[3].quantity) + parseFloat(report.dailyReports[2].reportEntries[4].quantity) + parseFloat(report.dailyReports[2].reportEntries[5].quantity) + parseFloat(report.dailyReports[2].reportEntries[6].quantity);
          stovesPreviousday1 = parseFloat(report.dailyReports[2].reportEntries[1].quantity) + parseFloat(report.dailyReports[2].reportEntries[2].quantity);
      
        }else{}
      
      }else{
      
        if( (thirdDateDay - day) == 27){
      
          gasYesterday1 = parseFloat(report.dailyReports[2].reportEntries[0].quantity);
          cylinderYesterday1 = parseFloat(report.dailyReports[2].reportEntries[3].quantity) + parseFloat(report.dailyReports[2].reportEntries[4].quantity) + parseFloat(report.dailyReports[2].reportEntries[5].quantity) + parseFloat(report.dailyReports[2].reportEntries[6].quantity);
          stovesYesterday1 = parseFloat(report.dailyReports[2].reportEntries[1].quantity) + parseFloat(report.dailyReports[2].reportEntries[2].quantity);
      
        }else if((thirdDateDay - day) == 26){
      
          gasPreviousday1 = parseFloat(report.dailyReports[2].reportEntries[0].quantity);
          cylinderPreviousday1 = parseFloat(report.dailyReports[2].reportEntries[3].quantity) + parseFloat(report.dailyReports[2].reportEntries[4].quantity) + parseFloat(report.dailyReports[2].reportEntries[5].quantity) + parseFloat(report.dailyReports[2].reportEntries[6].quantity);
          stovesPreviousday1 = parseFloat(report.dailyReports[2].reportEntries[1].quantity) + parseFloat(report.dailyReports[2].reportEntries[2].quantity);
      
        }else{}
      
      }
      
      }else{
      
      if(thirdDateDay == 30){
        
        if( (thirdDateDay - day) == 29){
      
          gasYesterday1 = parseFloat(report.dailyReports[2].reportEntries[0].quantity);
          cylinderYesterday1 = parseFloat(report.dailyReports[2].reportEntries[3].quantity) + parseFloat(report.dailyReports[2].reportEntries[4].quantity) + parseFloat(report.dailyReports[2].reportEntries[5].quantity) + parseFloat(report.dailyReports[2].reportEntries[6].quantity);
          stovesYesterday1 = parseFloat(report.dailyReports[2].reportEntries[1].quantity) + parseFloat(report.dailyReports[2].reportEntries[2].quantity);
      
        }else if((thirdDateDay - day) == 28){
      
          gasPreviousday1 = parseFloat(report.dailyReports[2].reportEntries[0].quantity);
          cylinderPreviousday1 = parseFloat(report.dailyReports[2].reportEntries[3].quantity) + parseFloat(report.dailyReports[2].reportEntries[4].quantity) + parseFloat(report.dailyReports[2].reportEntries[5].quantity) + parseFloat(report.dailyReports[2].reportEntries[6].quantity);
          stovesPreviousday1 = parseFloat(report.dailyReports[2].reportEntries[1].quantity) + parseFloat(report.dailyReports[2].reportEntries[2].quantity);
      
        }else{}
      
      }else{
        
        if( (thirdDateDay - day) == 30){
          
          gasYesterday1 = parseFloat(report.dailyReports[2].reportEntries[0].quantity);
          cylinderYesterday1 = parseFloat(report.dailyReports[2].reportEntries[3].quantity) + parseFloat(report.dailyReports[2].reportEntries[4].quantity) + parseFloat(report.dailyReports[2].reportEntries[5].quantity) + parseFloat(report.dailyReports[2].reportEntries[6].quantity);
          stovesYesterday1 = parseFloat(report.dailyReports[2].reportEntries[1].quantity) + parseFloat(report.dailyReports[2].reportEntries[2].quantity);
      
        }else if((thirdDateDay - day) == 28){
          
          gasPreviousday1 = parseFloat(report.dailyReports[2].reportEntries[0].quantity);
          cylinderPreviousday1 = parseFloat(report.dailyReports[2].reportEntries[3].quantity) + parseFloat(report.dailyReports[2].reportEntries[4].quantity) + parseFloat(report.dailyReports[2].reportEntries[5].quantity) + parseFloat(report.dailyReports[2].reportEntries[6].quantity);
          stovesPreviousday1 = parseFloat(report.dailyReports[2].reportEntries[1].quantity) + parseFloat(report.dailyReports[2].reportEntries[2].quantity);
      
        }else{}
      
      }
      
      }
      
      }else{}
      
      }
      
      if(JSON.stringify(report.monthlyReports[0]) != null){
        gasThisMonth1 = parseFloat(report.monthlyReports[0].reportEntries[0].quantity);
        cylinderThisMonth1 = parseFloat(report.monthlyReports[0].reportEntries[3].quantity) + parseFloat(report.monthlyReports[0].reportEntries[4].quantity) + parseFloat(report.monthlyReports[0].reportEntries[5].quantity) + parseFloat(report.monthlyReports[0].reportEntries[6].quantity);
        stovesThisMonth1 = parseFloat(report.monthlyReports[0].reportEntries[1].quantity) + parseFloat(report.monthlyReports[0].reportEntries[2].quantity);
      }
      
      if(JSON.stringify(report.monthlyReports[1]) != null){
         gasLastMonth1= parseFloat(report.monthlyReports[0].reportEntries[0].quantity);
         cylinderLastMonth1= parseFloat(report.monthlyReports[0].reportEntries[3].quantity) + parseFloat(report.monthlyReports[0].reportEntries[4].quantity) + parseFloat(report.monthlyReports[0].reportEntries[5].quantity) + parseFloat(report.monthlyReports[0].reportEntries[6].quantity);
         stovesLastMonth1= parseFloat(report.monthlyReports[0].reportEntries[1].quantity) + parseFloat(report.monthlyReports[0].reportEntries[2].quantity);
      
        gasThisMonth1 = parseFloat(report.monthlyReports[1].reportEntries[0].quantity);
        cylinderThisMonth1 = parseFloat(report.monthlyReports[1].reportEntries[3].quantity) + parseFloat(report.monthlyReports[1].reportEntries[4].quantity) + parseFloat(report.monthlyReports[1].reportEntries[5].quantity) + parseFloat(report.monthlyReports[1].reportEntries[6].quantity);
        stovesThisMonth1 = parseFloat(report.monthlyReports[1].reportEntries[1].quantity) + parseFloat(report.monthlyReports[1].reportEntries[2].quantity);
      }
      
      
      var gasOpening1 = 0;
      var gasReceived1 = 0;
      
      var cylinderOpening1 = 0;
      var cylinderReceived1 = 0;
      
      var stovesOpening1 = 0;
      var stovesReceived1 = 0;
      
      if(report.receivedToday != null){
        for(var x = 0; x < 7; x++){
          if(JSON.stringify(report.receivedToday[x]) != null){
          if(JSON.stringify(report.receivedToday[x].product).includes("ylinder")){
          
            cylinderReceived1 = cylinderReceived1 + parseFloat(report.receivedToday[x].quantity);
          }else if(JSON.stringify(report.receivedToday[x].product).includes("tove")){
          
            stovesReceived1 = stovesReceived1 + parseFloat(report.receivedToday[x].quantity);
          }else{
          
            gasReceived1 = gasReceived1 + parseFloat(report.receivedToday[x].quantity);
          }
        }
          
        }
    }
      
    if(report.openingStock != null){
      for(var x = 0; x < 7; x++){
      if(JSON.stringify(report.openingStock[x]) != null){
        
          if(JSON.stringify(report.openingStock[x].product).includes("ylinder")){
            cylinderOpening1 = cylinderOpening1 + parseFloat(report.openingStock[x].quantity);
          }else if(JSON.stringify(report.openingStock[x].product).includes("tove")){
            stovesOpening1 = stovesOpening1 + parseFloat(report.openingStock[x].quantity);
          }else{
            gasOpening1 = gasOpening1 + parseFloat(report.openingStock[x].quantity);
          }
        }
      }
    }
      
    if(report.availableStock != null){
      for(var x = 0; x < 7; x++){
      if(JSON.stringify(report.availableStock[x]) != null){
        
          if(JSON.stringify(report.availableStock[x].product).includes("ylinder")){
            cylinderAvailable1 = cylinderAvailable1 + parseFloat(report.availableStock[x].quantity);
          }else if(JSON.stringify(report.availableStock[x].product).includes("tove")){
            stovesAvailable1 = stovesAvailable1 + parseFloat(report.availableStock[x].quantity);
          }else{
            gasAvailable1 = gasAvailable1 + parseFloat(report.availableStock[x].quantity);
          }
        }
      }
    }
      
      this.setState({ gasSoldToday:gasSoldToday1});
      this.setState({ cylinderSoldToday:cylinderSoldToday1});
      this.setState({ stovesSoldToday:stovesSoldToday1});
      
      this.setState({ gasOpening:gasOpening1});
      this.setState({ gasReceived:gasReceived1});
      this.setState({ gasAvailable:gasAvailable1});
      
      this.setState({ cylinderOpening:cylinderOpening1});
      this.setState({ cylinderReceived:cylinderReceived1});
      this.setState({ cylinderAvailable:cylinderAvailable1});
      
      this.setState({ stovesOpening:stovesOpening1});
      this.setState({ stovesReceived:stovesReceived1});
      this.setState({ stovesAvailable:stovesAvailable1});
      
      this.setState({ gasPreviousday:gasPreviousday1});
      this.setState({ gasYesterday:gasYesterday1});
      this.setState({ gasToday:gasToday1});
      this.setState({ gasThisMonth:gasThisMonth1});
      this.setState({ gasLastMonth:gasLastMonth1});
      
      this.setState({ cylinderPreviousday:cylinderPreviousday1});
      this.setState({ cylinderYesterday:cylinderYesterday1});
      this.setState({ cylinderToday:cylinderToday1});
      this.setState({ cylinderThisMonth:cylinderThisMonth1});
      this.setState({ cylinderLastMonth:cylinderLastMonth1});
      
      this.setState({ stovesPreviousday:stovesPreviousday1});
      this.setState({ stovesYesterday:stovesYesterday1});
      this.setState({ stovesToday:stovesToday1});
      this.setState({ stovesThisMonth:stovesThisMonth1});
      this.setState({ stovesLastMonth:stovesLastMonth1});
      
      this.setState({ pd:gasPreviousday1});
      this.setState({ yd:gasYesterday1});
      this.setState({ td:gasToday1});
      this.setState({ tm:gasThisMonth1});
      this.setState({ lm:gasLastMonth1});
      
      this.setState({ sold_td:gasSoldToday1});
      this.setState({ opening_stck:gasOpening1});
      this.setState({ recevied_td:gasReceived1});
      this.setState({ avail:gasAvailable1});

    this.setState({removeModal:false});
    }
         });
      }catch (e) {
        return {
          error: true
        };
      }


    }

  handleGasClick () {
    this.setState({ image: require('../img/gas.png') });
    this.setState({ btn1:{backgroundColor:"white"}});
    this.setState({ btn2:{backgroundColor:"#C9C7C7"}});
    this.setState({ btn3:{backgroundColor:"#C9C7C7"}});

    this.setState({ pd:this.state.gasPreviousday});
    this.setState({ yd:this.state.gasYesterday});
    this.setState({ td:this.state.gasToday});
    this.setState({ tm:this.state.gasThisMonth});
    this.setState({ lm:this.state.gasLastMonth});

    this.setState({ sold_td:this.state.gasSoldToday});
    this.setState({ opening_stck:this.state.gasOpening});
    this.setState({ recevied_td:this.state.gasReceived});
    this.setState({ avail:this.state.gasAvailable});
  }

  handleCylinderClick () {
    this.setState({ image: require('../img/gastank.png') });
    this.setState({ btn1:{backgroundColor:"#C9C7C7"}});
    this.setState({ btn2:{backgroundColor:"white"}});
    this.setState({ btn3:{backgroundColor:"#C9C7C7"}});

    this.setState({ pd:this.state.cylinderPreviousday});
    this.setState({ yd:this.state.cylinderYesterday});
    this.setState({ td:this.state.cylinderToday});
    this.setState({ tm:this.state.cylinderThisMonth});
    this.setState({ lm:this.state.cylinderLastMonth});

    this.setState({ sold_td:this.state.cylinderSoldToday});
    this.setState({ opening_stck:this.state.cylinderOpening});
    this.setState({ recevied_td:this.state.cylinderReceived});
    this.setState({ avail:this.state.cylinderAvailable});
  }

  handleStoveClick () {
    this.setState({ image: require('../img/stove.png') });
    this.setState({ btn1:{backgroundColor:"#C9C7C7"}});
    this.setState({ btn2:{backgroundColor:"#C9C7C7"}});
    this.setState({ btn3:{backgroundColor:"white"}});

    this.setState({ pd:this.state.stovesPreviousday});
    this.setState({ yd:this.state.stovesYesterday});
    this.setState({ td:this.state.stovesToday});
    this.setState({ tm:this.state.stovesThisMonth});
    this.setState({ lm:this.state.stovesLastMonth});

    this.setState({ sold_td:this.state.stovesSoldToday});
    this.setState({ opening_stck:this.state.stovesOpening});
    this.setState({ recevied_td:this.state.stovesReceived});
    this.setState({ avail:this.state.stovesAvailable});

  }
  

  render() {
    const {image} = this.state;
  
  
    let sampleData = [
      {seriesName: '', data: [{x: 'Previous Day', y: this.state.pd}, {x: 'Yesterday', y: this.state.yd}, {x: 'Today', y: this.state.td}, {x: 'This Month', y: this.state.tm}, {x: 'Last Month', y: this.state.lm}], color: '#FD6721'}
  ]
  

    return (
      <ImageBackground source={require('../img/home_bg1.png')} style={styles.backgroundImage}> 


      <Modal
          animationType="slide"
          transparent={true}
          visible={this.state.removeModal}
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
                    <Text style={{alignSelf:'center',color:'white'}}>Retrieving data. . .</Text>
                    </Body>
                  </Row>
              </Grid>
          </Content>
          </View>
        </Modal>

        <Modal
          animationType="slide"
          transparent={true}
          visible={this.state.removeModal1}
          onRequestClose={() => {
          }}>
          <View style={{backgroundColor:"rgba(0, 0, 0, 0.8)",flex:1,flexDirection:'row',alignItems:'center',justifyContent:'center' }}>
          <Content>
          <Card style={{marginLeft:15,marginRight:15}}>
            <CardItem>
              <Grid>
                  <Row>
                  </Row>
                  <Row>
                      <Body style={{marginTop:30}}>
                    <Text style={styles.textH4}>We have a problem. It seem like there was an issue retrieving data. Let's try refresh the screen.</Text>
                    </Body>
                  </Row>
                  <Row>
                  </Row>
                  <Row style={{marginTop:15,marginBottom:15}}>
                      <Col>
                      <Body style={{ alignSelf:'center', justifyContext: 'center'}}>
                      <Button full rounded success style={styles.submitbtn} style={{width:120}} onPress={() => {this.getReport()}}>
                    <Text >RETRY</Text>
                    </Button>
                </Body>
                      </Col>
                      <Col>
                      <Body style={{ alignSelf:'center', justifyContext: 'center'}}>
                      <Button full rounded danger style={styles.submitbtn} style={{width:120}} onPress={() => {this.setState({removeModal1:!this.state.removeModal1}) }}>
                    <Text >CANCEL</Text>
                </Button>
                </Body>
                      </Col>
                  </Row>
              </Grid>
            </CardItem>
          </Card>
          </Content>
          </View>
        </Modal>

          <Header transparent>
          <Left>
            <Button iconLeft transparent onPress={() => this.props.navigation.navigate('SideNav',this.theProp)}>
              <Icon name="bars" color="white" size={25} />
            </Button>
          </Left>
          <Body style={{marginLeft: -150}}>
            <Title style={styles.titleHeader}>HOME</Title>
          </Body>
        </Header>
        <Grid>
            <Row transparent style={{height: 180}}>
                <Left style={{height: 80}}>
                    <Text style={styles.textH1}>{this.state.sold_td}</Text>
                    <Text style={styles.textH2}>SOLD TODAY</Text>
                </Left>
                <Right>
                    <Image source={this.state.image} style={styles.logo}/>
                </Right>
            </Row>
            <Row>
                <Content>
                    <Card  style={{ alignSelf:'center', width: 380}}>
                
                    <CardItem>
                      <Body>
                        <Button full light onPress={() => this.handleGasClick()} style={this.state.btn1} ><Text style={{color: '#FD6721',fontSize:11}}>Gas</Text></Button>
                      </Body>
                      <Body>
                      <Button full light onPress={() => this.handleCylinderClick()}  style={this.state.btn2} ><Text style={{color: '#FD6721',fontSize:11}}>Cylinder</Text></Button>
                      </Body>
                      <Body>
                      <Button full light onPress={() => this.handleStoveClick()} style={this.state.btn3} ><Text style={{color: '#FD6721',fontSize:11}}>Stoves</Text></Button>
                      </Body>
                      <Body>
                      <Button full light onPress={() => this.getReport()} style={{backgroundColor:"white"}} ><Icon name="refresh" color="#FD6721" size={20} /></Button>
                      </Body>
                    </CardItem>

          
                        <CardItem cardBody style={{height: 240, flex: 1}}>
                        <PureChart data={sampleData} type='bar' height={200} />
                           
                        </CardItem>
                        <CardItem>
                            <Body>
                                <Text style={styles.textH4}>Opening Stock</Text>
                                <Text style={styles.textH3}>{this.state.opening_stck}</Text>
                                <Text></Text>
                            </Body>
                            <Body>
                                <Text style={styles.textH4}>Received Today</Text>
                                <Text style={styles.textH3}>{this.state.recevied_td}</Text>
                                <Text></Text>
                            </Body>
                            <Right>
                                <Text style={styles.textH4}>Available</Text>
                                <Text style={styles.textH3}>{this.state.avail}</Text>
                                <Text></Text>
                            </Right>
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
  titleHeader: {
    alignSelf: 'center',
    color: 'white',
    fontSize: 30
  },
  btnText: {
    alignSelf: 'center',
    color: '#FD6721',
    fontSize: 14
  },
  textH1: {
    alignSelf: 'center',
    color: 'white',
    fontSize: 50
  },
  textH2: {
    alignSelf: 'center',
    color: 'white',
    fontSize: 20
  },
  textH3: {
    alignSelf: 'center',
    color: '#FD6721',
    fontSize: 20
  },
  textH4: {
    alignSelf: 'center',
  },
  logo: {
    flex: 1,
    alignSelf: 'center',
    width: 120,
    height: 120,
    resizeMode: 'center'
  }
});
