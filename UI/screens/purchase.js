import React, {Component} from 'react';
import { StyleSheet, Image, TextInput, Modal, ActivityIndicator, View } from 'react-native';
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
    Container,
    Col
} from "native-base";


var test1=0,test2=0,test3=0,test4=0;
var gasBody,cylinder9KgBody,cylinder14KgBody,cylinder19KgBody,cylinder48KgBody;
var userIdNavVhanged;
var theProp;

export default class purchse extends Component {

  constructor(props){
    super(props);
    this.userIdNavVhanged = JSON.stringify(props.navigation.state.params.userId).replace("\"","").replace("\"","");
    this.theProp = props.navigation.state.params;
    
    this.state= {
      removeModal:false,
      count: '0',
      count1: '0',
      count2: '0',
      count3: '0',
      total: 0.00,
    
      //salesBody:[],
      price_48Kg: 0,
      price_9Kg: 0,
      price_19Kg: 0,
      price_14Kg: 0,
      gasUnitPrice:0.00,
      grandGasTotal:0.00,
      gasQtyTotal:0.00,
      loading:false,
      alertModal:false,
      errorMessage:''
    }
  }

  componentWillMount(){
    this.purchseMethod();
  }

  purchseMethod(){

    this.setState({loading: true});
    fetch('http://GaserveTempBackend-env.7cvt4abrkt.us-west-2.elasticbeanstalk.com/gaserve/v1/lists',{

     method:'GET',
     headers:{
       'userId': '4586cdae-4349-49ea-9a4f-235cb0180d25',
       'Content-Type':'application/json'

     }
    })
    .then(res => {return res.json()})
  .then(response => 
   {
    
var gasPrice=parseFloat(response.products[0].price)*1.15;

//alert(parseFloat(response.products[0].price))
this.setState({gasUnitPrice:gasPrice});
     
var cylinederPrice48=parseFloat(response.products[3].price)*1.15;
var cylinederPrice19=parseFloat(response.products[5].price)*1.15;
var cylinederPrice14=parseFloat(response.products[6].price)*1.15;
var cylinederPrice9=parseFloat(response.products[4].price)*1.15;
this.setState({loading: false});

 

this.setState({ price_48Kg:cylinederPrice48});
this.setState({ price_9Kg:cylinederPrice9});
this.setState({ price_19Kg:cylinederPrice19});
this.setState({ price_14Kg:cylinederPrice14});



     
    }

   
   )
   .catch(error => {
    this.setState({loading: false});
    this.setState({errorMessage:'Unexpected error: ' + error.toString()})
    this.setState({alertModal:!this.state.alertModal})
  }); 
   }

   _incrementCount() {
    this.setState(prevState => ({ count: (parseInt(prevState.count) + 1).toString() }));
    var totalValue = parseFloat(this.state.total) + parseFloat((this.state.gasUnitPrice * 9)+this.state.price_9Kg);
    var gasTotal = parseFloat(this.state.grandGasTotal) +  parseFloat(this.state.gasUnitPrice * 9);
    this.setState({grandGasTotal:gasTotal});
      this.setState({total:totalValue});

      

      var qtyGas = parseInt(this.state.gasQtyTotal) + 9;
      this.setState({gasQtyTotal:qtyGas});
  }
 
  _incrementCount_0() {
    if(this.state.count>0){
      this.setState(prevState => ({ count:(parseInt(prevState.count) - 1).toString() }));
      var totalValue = parseFloat(this.state.total)- parseFloat((this.state.gasUnitPrice * 9)+this.state.price_9Kg);
      var gasTotal = parseFloat(this.state.grandGasTotal) -  parseFloat(this.state.gasUnitPrice * 9);
    this.setState({grandGasTotal:gasTotal});
      this.setState({total:totalValue});

      var qtyGas = parseInt(this.state.gasQtyTotal) - 9;
      this.setState({gasQtyTotal:qtyGas});
    }
  }

  _incrementCount1() {
    this.setState(prevState => ({ count1:(parseInt(prevState.count1) + 1).toString()  }));
    var totalValue = parseFloat(this.state.total) + parseFloat((this.state.gasUnitPrice * 14)+this.state.price_14Kg);
    var gasTotal = parseFloat(this.state.grandGasTotal) +  parseFloat(this.state.gasUnitPrice * 14);
    this.setState({grandGasTotal:gasTotal});
      this.setState({total:totalValue});

      var qtyGas = parseInt(this.state.gasQtyTotal) + 14;
      this.setState({gasQtyTotal:qtyGas});
  }
 
  _incrementCount_1() {
    if(this.state.count1>0){
    this.setState(prevState => ({ count1:(parseInt(prevState.count1) - 1).toString() }));
    var totalValue =parseFloat(this.state.total) - parseFloat((this.state.gasUnitPrice * 14)+this.state.price_14Kg);
 var gasTotal = parseFloat(this.state.grandGasTotal) - parseFloat(this.state.gasUnitPrice * 14);
    this.setState({grandGasTotal:gasTotal});
      this.setState({total:totalValue});

      var qtyGas = parseInt(this.state.gasQtyTotal) - 14;
      this.setState({gasQtyTotal:qtyGas});
    }
  }

  _incrementCount2() {
    this.setState(prevState => ({ count2:(parseInt(prevState.count2) + 1).toString() }));
    var totalValue = parseFloat(this.state.total) + parseFloat((this.state.gasUnitPrice * 19)+this.state.price_19Kg);
    var gasTotal = parseFloat(this.state.grandGasTotal) +  parseFloat(this.state.gasUnitPrice * 19);
    this.setState({grandGasTotal:gasTotal});
      this.setState({total:totalValue});

      var qtyGas = parseInt(this.state.gasQtyTotal) + 19;
      this.setState({gasQtyTotal:qtyGas});
  }
 
  _incrementCount_2() {
    if(this.state.count2>0){
    this.setState(prevState => ({ count2:(parseInt(prevState.count2) - 1).toString()}));
    var totalValue = parseFloat(this.state.total) - parseFloat((this.state.gasUnitPrice * 19)+this.state.price_19Kg);
    var gasTotal = parseFloat(this.state.grandGasTotal) -  parseFloat(this.state.gasUnitPrice * 19);
    this.setState({grandGasTotal:gasTotal});
      this.setState({total:totalValue});

      var qtyGas = parseInt(this.state.gasQtyTotal) - 19;
      this.setState({gasQtyTotal:qtyGas});
    }
  }

  _incrementCount3() {
    this.setState(prevState => ({ count3:(parseInt(prevState.count3) + 1).toString()}));
    var totalValue = parseFloat(this.state.total) + parseFloat((this.state.gasUnitPrice * 48)+this.state.price_48Kg);
    var gasTotal = parseFloat(this.state.grandGasTotal) +  parseFloat(this.state.gasUnitPrice * 48);
    this.setState({grandGasTotal:gasTotal});
      this.setState({total:totalValue});

      var qtyGas = parseInt(this.state.gasQtyTotal) + 48;
      this.setState({gasQtyTotal:qtyGas});
  }
 
  _incrementCount_3() {
    if(this.state.count3>0){
    this.setState(prevState => ({ count3:  (parseInt(prevState.count3) - 1).toString() }));
    var totalValue = parseFloat(this.state.total)- parseFloat((this.state.gasUnitPrice * 48)+this.state.price_48Kg);
    var gasTotal = parseFloat(this.state.grandGasTotal) -  parseFloat(this.state.gasUnitPrice * 48);
    this.setState({grandGasTotal:gasTotal});
      this.setState({total:totalValue});

      var qtyGas = parseInt(this.state.gasQtyTotal) - 48;
      this.setState({gasQtyTotal:qtyGas});
    }
  }

  
sendData(){
  if(this.state.salesBody.length>0){
     //this.props.navigation.navigate('checkout',{salesBody:this.state.salesBody})
  }else{
    //alert('fields cant be left empty')
  }
}

  render() {

   //do we have to strify it or parse it ??
    

   var demoBody=[
     {
       "productId":"LP_Gas",
       "quantity":this.state.gasQtyTotal,
       "price":this.state.grandGasTotal
      
     },
    {
      "productId":"9kg_Cylinder",
      "quantity":this.state.count,
      "price":(this.state.price_9Kg * this.state.count)
    },
      {
        "productId":"14kg_Cylinder",
        "quantity":this.state.count1,
        "price":(this.state.price_14Kg * this.state.count1)
         
      },
        {
          "productId":"19kg_Cylinder",
          "quantity":this.state.count2,
          "price":(this.state.price_19Kg * this.state.count2)
          
        },
          { 
            "productId":"48kg_Cylinder",
             "quantity":this.state.count3,
             "price":(this.state.price_48Kg * this.state.count3)
        }
]

 var salesBody=[]

   for(var i=0;i<demoBody.length;i++){
    var prod=demoBody[i].productId
    var quant=demoBody[i].quantity
    var price=demoBody[i].price
  
  
    if(quant>0){
  
      var jsonData =  {
        "productId":prod,
        "quantity":quant,
        "price":price
        
      };
  
      salesBody.push(jsonData);
    
    }
  }

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
                    <Text style={{alignSelf:'center',color:'white'}}>Processing data. . .</Text>
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
          onRequestClose={() => {
          }}>
          <View style={{backgroundColor:"rgba(0, 0, 0, 0.8)",flex: 1 }}>
          <Content>
          <Card style={{marginLeft:15,marginRight:15,marginTop:250}}>
            <CardItem>
              <Grid>
                  <Row>
                  </Row>
                  <Row>
                      <Body style={{marginTop:30}}>
                    <Text style={styles.textH4}>Would you like to add more items to your cart?</Text>
                    </Body>
                  </Row>
                  <Row>
                  </Row>
                  <Row style={{marginTop:15,marginBottom:15}}>
                      <Col>
                      <Body style={{ alignSelf:'center', justifyContext: 'center'}}>
                      <Button full rounded success style={styles.submitbtn} style={{width:120}} onPress={() => {this.setState({removeModal:!this.state.removeModal}) }}>
                    <Text >YES</Text>
                    </Button>
                </Body>
                      </Col>
                      <Col>
                      <Body style={{ alignSelf:'center', justifyContext: 'center'}}>
                      <Button full rounded danger style={styles.submitbtn} style={{width:120}} onPress={() => {this.setState({removeModal:!this.state.removeModal}) }}>
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
         <View>
           <Text style={{backgroundColor:'#E05900'}}></Text>
           </View> 

        <View style={styles.viewHeader}>
        
            <Icon style={styles.iconbackG} active name='chevron-left' color='white' size={30}
            onPress={()=>this.props.navigation.navigate('Sales',this.theProp)}
            /> 

             <Text style={styles.txtStyle}>PURCHASE</Text>         
               </View>
               
               <Image
         style={styles.imageStyle}
         source={require('../img/headerimage.png')}
         />

        <Content style={styles.box}>
          <Card>
            <CardItem>
              <Body>
                <Image source={require('../img/9kg.jpg')} style={styles.image}/>
              </Body>
              <Body>
                <Text style={styles.center}>Liquefied  Petroleum (LP) Gas</Text>
                <Text style={styles.center}>${this.state.gasUnitPrice * 9 + this.state.price_9Kg}</Text>
              </Body>
              <Body>
                <Button transparent style={styles.plus}><Icon name="plus" color="black" size={25} onPress={ () => this._incrementCount() } /></Button>
                <TextInput  keyboardType='numeric'
                onChangeText={(count)=>{this.setState({count,total:((parseInt(count) * this.state.price_9Kg)+(parseInt(this.state.count1) * this.state.price_14Kg)+(parseInt(this.state.count2) * this.state.price_19Kg)+(parseInt(this.state.count3) * this.state.price_48Kg)).toString()})}}
                
                                 value={this.state.count} style={styles.quantity}></TextInput>        
                <Button transparent style={styles.minus}><Icon name="minus" color="black" size={25} onPress={ () => this._incrementCount_0() } /></Button>
              </Body>
            </CardItem>
          </Card>

          <Card>
            <CardItem>
              <Body>
                <Image source={require('../img/14kg.jpg')} style={styles.image}/>
              </Body>
              <Body>
                <Text style={styles.center}>Liquefied  Petroleum (LP) Gas</Text>
                <Text style={styles.center}>${this.state.gasUnitPrice * 14 + this.state.price_14Kg}</Text>
              </Body>
              <Body>
                <Button transparent style={styles.plus}><Icon name="plus" color="black" size={25} onPress={ () => this._incrementCount1() } /></Button>
                <TextInput  keyboardType='numeric'
                onChangeText={(count1)=>{this.setState({count1,total:((parseInt(this.state.count) * this.state.price_9Kg)+(parseInt(count1) * this.state.price_14Kg)+(parseInt(this.state.count2) * this.state.price_19Kg)+(parseInt(this.state.count3) * this.state.price_48Kg)).toString()})}}
                                 value={this.state.count1} style={styles.quantity}></TextInput>  
                <Button transparent style={styles.minus}><Icon name="minus" color="black" size={25} onPress={ () => this._incrementCount_1() } /></Button>
              </Body>
            </CardItem>
          </Card>

          <Card>
            <CardItem>
              <Body>
                <Image source={require('../img/19kg.jpg')} style={styles.image}/>
              </Body>
              <Body>
                <Text style={styles.center}>Liquefied  Petroleum (LP) Gas</Text>
                <Text style={styles.center}>${this.state.gasUnitPrice * 19 + this.state.price_19Kg}</Text>
              </Body>
              <Body>
                <Button transparent style={styles.plus}><Icon name="plus" color="black" size={25} onPress={ () => this._incrementCount2() } /></Button>
                <TextInput keyboardType='numeric'
               onChangeText={(count2)=>{this.setState({count2,total:((parseInt(this.state.count) * this.state.price_9Kg)+(parseInt(count1) * this.state.price_14Kg)+(parseInt(count2) * this.state.price_19Kg)+(parseInt(this.state.count3) * this.state.price_48Kg)).toString()})}}
                                 value={ parseInt(this.state.count2).toString()} style={styles.quantity}></TextInput>        
                <Button transparent style={styles.minus}><Icon name="minus" color="black" size={25} onPress={ () => this._incrementCount_2() } /></Button>
              </Body>
            </CardItem>
          </Card>

          <Card>
            <CardItem>
              <Body>
                <Image source={require('../img/48kg.jpg')} style={styles.image}/>
              </Body>
              <Body>
                <Text style={styles.center}>Liquefied  Petroleum (LP) Gas</Text>
                <Text style={styles.center}>${this.state.gasUnitPrice * 48 + this.state.price_48Kg}</Text>
              </Body>
              <Body>
                <Button transparent style={styles.plus}><Icon name="plus" color="black" size={25} onPress={ () => this._incrementCount3() } /></Button>
                <TextInput  keyboardType='numeric'
                onChangeText={(count3)=>{this.setState({count3,total:((parseInt(this.state.count) * this.state.price_9Kg)+(parseInt(count1) * this.state.price_14Kg)+(parseInt(this.state.count2) * this.state.price_19Kg)+(parseInt(count3) * this.state.price_48Kg)).toString()})}}
                                 value={ parseInt(this.state.count3).toString()} style={styles.quantity}></TextInput>     
                <Button transparent style={styles.minus}><Icon name="minus" color="black" size={25} onPress={ () => this._incrementCount_3() } /></Button>
              </Body>
            </CardItem>
          </Card>

        </Content>
        <Footer style={styles.footer}>
            <Left style={{marginLeft:20}}>
                <Text style={styles.textH2}>Total</Text>
                <Text style={styles.textH1}>${parseFloat(this.state.total).toFixed(2)}</Text>
            </Left>
            <Right>
                <Button rounded success style={{marginRight:30}} 
                   
                  //onPress={()=>this.sendData()}> 
                   onPress={()=>this.props.navigation.navigate('Checkout',{salesBody:salesBody,userId:this.theProp})}>
                   
                 
               
                    <Text>PROCEED</Text>
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
      marginTop:-45
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
    backgroundImage: {},
    submitbtn: {
      width:280,
      alignSelf:'center'
    },
    imageStyle:{
      width:null,
      height:120,
     // top:30
  },
  txtStyle:{
      fontSize:36,
      color:'#FFFFFF',
      textAlign:'center',
      backgroundColor:'#E05900',
      left:88,
      top:8     
  },
  iconbackG:{
    backgroundColor:'#E05900',
    top:11.2,
    left:10   
},
viewHeader:{
  flexDirection:'row',
  backgroundColor:'#E05900', 
  //height:-150,
  
},
});
