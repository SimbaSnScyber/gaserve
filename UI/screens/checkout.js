import React, {Component} from 'react';
import {StyleSheet, ImageBackground, Image ,Modal,ActivityIndicator,View} from 'react-native';
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

var userIdNavVhanged;
var thisTotal = 0;
var theProp;

export default class checkout extends Component {

    constructor(props){
        super(props);
    
        this.userIdNavVhanged = JSON.stringify(props.navigation.state.params.userId.userId).replace("\"","").replace("\"","");
        //alert(this.userIdNavVhanged);
        this.theProp = props.navigation.state.params;
        
    
        this.state={
            gasTotal: 10,
            loading:false,
            cylinderTotal: 10,
            stoveTotal: 20,
            purchaseDisplay: "flex",
            refillDisplay: "flex",
            stoveDisplay: "flex",
            refillitems:[],  
            grandTotal:0.00,
               
            removeModal: false, 
            removeModal1: false, 
            removeModal2: false,   
            refill:this.props.navigation.state.params.salesBody,
            alertModal:false,
      errorMessage:''
        }
    }
   
componentWillMount(){
    this.filter();
}

filter(){
    var gsTotal=0.00;
    //alert(JSON.stringify(this.props.navigation.state.params.salesBody))
  for(var i=0;i<this.state.refill.length;i++){
       if(this.state.refill[i] != null){
            
            var prod=this.state.refill[i].productId
            var quant=this.state.refill[i].quantity
            var pricerefill=this.state.refill[i].price
        
            if(quant>0){
  
            thisTotal = thisTotal + pricerefill;
          
            gsTotal=gsTotal+thisTotal
            var refillData =  {
                "productId":prod,
                "quantity":quant,
                "price":pricerefill
                }
                this.state.refillitems.push(refillData);
        };
        if(prod.includes("ylinder")){
            this.setState({exchangeTotal:thisTotal})
            this.setState({gasTotal:gsTotal})
            this.setState({purchaseDisplay:"flex"})
            this.setState({refillDisplay:"none"})
            this.setState({stoveDisplay:"none"})
            
        }else if(prod.includes("tove")){
            this.setState({stoveTotal:thisTotal})
            this.setState({refillDisplay:"none"})
            this.setState({purchaseDisplay:"none"})
        }else{
            this.setState({refillTotal:thisTotal})
            this.setState({purchaseDisplay:"none"})
            this.setState({stoveDisplay:"none"})
        }
        
        }

        }
}
            
      postChectOut(){

      this.setState({loading:true});

     thisTotal = 0.00;

    var gsTotal=this.state.refill[0].price

        //use this total to calculate the vat inc. and exc. etc...
        this.setState({grandTotal:thisTotal})

//use this grandTotal state to calculate the vat inc. and exc. etc...
var viewData={
    "consumer":"0825757823",
  items:this.state.refillitems,

            "totalExVat":thisTotal/1.15,
            "totalInclVat":thisTotal,
            "vat":thisTotal - (thisTotal/1.15),
            "price":0,
            "vatRate":15,
            "paymentMethod":"CASH"

}

  if(viewData.items.length>0){

    fetch('http://gaservebackendservices-dev-sales.eu-west-2.elasticbeanstalk.com/gaserve/v1/headerSales',{    
        method:'POST',
        headers:{
          'userId': this.userIdNavVhanged,
          'Content-Type':'application/json'
   
        },
        body:JSON.stringify(viewData)
   
       })
         .then(res => {return res.json()})
   .then(res => {
      this.setState({loading:false});
      //************************************************
      //link to home screen
      //************************************************
      this.setState({removeModal:true})

 }

   )
   .catch(error =>
    this.setState({removeModal2:true})
    );
       
    
   
  }
  else{
      alert('Cannot purchase with zero inputs')
  }

          
      }

      yesOpt(){
        this.setState({removeModal:!this.state.removeModal}); 
        this.props.navigation.navigate('Sales',this.theProp);
      }

      noOpt(){
        this.setState({removeModal:!this.state.removeModal});
        this.props.navigation.navigate('Home',this.theProp);
    }



  render() {

    return (
      <Container>

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
          animationType="slide"
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
                    <Text style={{alignSelf:'center',color:'white'}}>Processing transaction. . .</Text>
                    </Body>
                  </Row>
              </Grid>
          </Content>
          </View>
        </Modal>

      <Modal
          animationType="slide"
          transparent={true}
          visible={this.state.removeModal}
          onRequestClose={() => { }}>
          <View style={{backgroundColor:"rgba(0, 0, 0, 0.8)",flex:1,flexDirection:'row',alignItems:'center',justifyContent:'center' }}>
          <Content>
          <Card style={{marginLeft:15,marginRight:15}}>
            <CardItem>
              <Grid>
                  <Row>
                  </Row>
                  <Row>
                      <Body style={{marginTop:30}}>
                    <Text style={styles.textH4}>Transaction successful. Would you like to make another transaction?</Text>
                    </Body>
                  </Row>
                  <Row>
                  </Row>
                  <Row style={{marginTop:15,marginBottom:15}}>
                      <Col>
                      <Body style={{ alignSelf:'center', justifyContext: 'center'}}>
                      <Button full rounded success style={styles.submitbtn} style={{width:120}} onPress={() => {this.yesOpt()}}>
                    <Text >YES</Text>
                    </Button>
                </Body>
                      </Col>
                      <Col>
                      <Body style={{ alignSelf:'center', justifyContext: 'center'}}>
                      <Button full rounded danger style={styles.submitbtn} style={{width:120}} onPress={() => {this.noOpt()}}>
                    <Text >NO</Text>
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
                    <Text style={styles.textH4}>You are about to cancel this transaction. Proceed to cancel?</Text>
                    </Body>
                  </Row>
                  <Row>
                  </Row>
                  <Row style={{marginTop:15,marginBottom:15}}>
                      <Col>
                      <Body style={{ alignSelf:'center', justifyContext: 'center'}}>
                      <Button full rounded success style={styles.submitbtn} style={{width:120}} onPress={() => {this.props.navigation.navigate('Home',this.theProp)}}>
                    <Text >YES</Text>
                    </Button>
                </Body>
                      </Col>
                      <Col>
                      <Body style={{ alignSelf:'center', justifyContext: 'center'}}>
                      <Button full rounded danger style={styles.submitbtn} style={{width:120}} onPress={() => {this.setState({removeModal1:!this.state.removeModal1}) }}>
                    <Text >NO</Text>
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

        <Modal
          animationType="slide"
          transparent={true}
          visible={this.state.removeModal2}
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
                    <Text style={styles.textH4}>Transaction unsuccessful. Retry?</Text>
                    </Body>
                  </Row>
                  <Row>
                  </Row>
                  <Row style={{marginTop:15,marginBottom:15}}>
                      <Col>
                      <Body style={{ alignSelf:'center', justifyContext: 'center'}}>
                      <Button full rounded success style={styles.submitbtn} style={{width:120}} onPress={() => {this.postChectOut()}}>
                    <Text >YES</Text>
                    </Button>
                </Body>
                      </Col>
                      <Col>
                      <Body style={{ alignSelf:'center', justifyContext: 'center'}}>
                      <Button full rounded danger style={styles.submitbtn} style={{width:120}} onPress={() => {this.props.navigation.navigate('Home',this.theProp)}}>
                    <Text >NO</Text>
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
          <Grid>
              <Row style={styles.toprow}>
                {/* <Left style={{marginLeft:15}}>
                    <Icon name="angle-left" color="white" size={30} />
                </Left> */}
                <Body style={{marginLeft:150}}>
                    <Text style={styles.titleHeader}>SUMMARY</Text>
                </Body><Right></Right>
              </Row>
              <Row style={styles.middlerow}>
                <Body style={{marginTop:-50}}>
                    <Text style={styles.textH1}>${parseFloat(thisTotal).toFixed(2)}</Text>
                    <Text style={styles.textH2}>TOTAL AMOUNT (inc. VAT)</Text>
                </Body>
              </Row>
              <Row style={styles.bottomrow}>
                <Content>
                    <ListItem itemDivider>
                        <Body>
                            <Text style={styles.center}>TODAY</Text>
                        </Body>
                    </ListItem>                    
                    <ListItem style={{display:this.state.purchaseDisplay}}>
                        <Body>
                            <Image source={require('../img/purchase_orange.png')} style={styles.logo}/>
                        </Body>
                        <Body>
                            <Text style={[styles.center,{fontSize: 19,color:'#FD6721'}]}>PURCHASE</Text>
                        </Body>
                        <Body>
                            <Text style={styles.center}>${parseFloat(thisTotal/1.15).toFixed(2)}</Text>
                            <Text style={styles.center}>Amount</Text>
                        </Body>
                    </ListItem>
                    <ListItem style={{display:this.state.refillDisplay}}>
                        <Body>
                            <Image source={require('../img/refill_orange.png')} style={styles.logo}/>
                        </Body>
                        <Body>
                            <Text style={[styles.center,{fontSize: 19,color:'#FD6721'}]}>REFILL</Text>
                        </Body>
                        <Body>
                            <Text style={styles.center}>${parseFloat(thisTotal/1.15).toFixed(2)}</Text>
                            <Text style={styles.center}>Amount</Text>
                        </Body>
                    </ListItem>
                    <ListItem style={{display:this.state.stoveDisplay}}>
                        <Body>
                            <Image source={require('../img/stoveIcon_orange.png')} style={styles.logo}/>
                        </Body>
                        <Body>
                            <Text style={[styles.center,{fontSize: 19,color:'#FD6721'}]}>STOVE</Text>
                        </Body>
                        <Body>
                            <Text style={styles.center}>${parseFloat(thisTotal/1.15).toFixed(2)}</Text>
                            <Text style={styles.center}>Amount</Text>
                        </Body>
                    </ListItem>
                </Content>
              </Row>
              <Row style={styles.center}>
            <Body  style={{ alignSelf:'center', justifyContext: 'center'}}>
                <Button rounded success style={{width:120,alignSelf:'center', justifyContext: 'center'}} onPress={()=>{this.postChectOut()}}>
                    <Text style={{left:10}}>CHECKOUT</Text>
                </Button>
            </Body>
            <Body  style={{ alignSelf:'center', justifyContext: 'center'}}>
                <Button rounded danger style={{width:120,alignSelf:'center', justifyContext: 'center'}} onPress={()=>{this.setState({removeModal1:!this.state.removeModal1})}}>
                    <Text style={{ left:20}}>CANCEL</Text>
                </Button>
            </Body>
              </Row>
          </Grid>
          
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
  toprow: {
      backgroundColor:'#FD6721',
      height:90
    },
    middlerow: {
        backgroundColor:'#FD6721',
        height:160
    },
    bottomrow: {
        height:340
    },
  titleHeader: {
    color: 'white',
    fontSize: 25,
    textAlign:'center',
    alignSelf:'center'
  },
  center: {
    textAlign:'center',
    alignSelf:'center'
  },
  logo: {
    flex: 1,
    alignSelf: 'center',
    width: 160,
    height: 160,
    resizeMode: 'center'
  },
  textH1: {
    alignSelf: 'center',
    color: 'white',
    fontSize: 60
  },
  textH2: {
    alignSelf: 'center',
    color: 'white',
    fontSize: 20
  },
  logo: {
    flex: 1,
    width: 60,
    height: 60,
    resizeMode: 'center'
  }
});
