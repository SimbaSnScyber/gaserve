import React, {Component} from 'react';
import { StyleSheet, Image, TextInput, Modal,ActivityIndicator, View } from 'react-native';
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
var test = 0,test2=0;

var userIdNavVhanged;
var theProp;

export default class stove extends Component {

  constructor(props){
    super(props);

    this.userIdNavVhanged = JSON.stringify(props.navigation.state.params.userId).replace("\"","").replace("\"","");
    this.theProp = props.navigation.state.params;

    this.state= {
      removeModal:false,
      count: '0',
      count1: '0',
      one_plate_stove:0.00,
      two_plate_stove:0.00,
      stove1Total: 0.00,
      stove2Total: 0.00,
      total:0.00,
      total2:0.00,
      loading:false,
      alertModal:false,
      errorMessage:''
      
    }
  }


  componentWillMount(){
    this.stoveMethod();
  }

  // componentDidUpdate(userIdNavVhanged, prevState) {
  //   if (userIdNavVhanged != null) {
  //     this.stoveMethod();
  //   }
  // }

 

  stoveMethod(){

    this.setState({loading: true});
    fetch('http://GaserveTempBackend-env.7cvt4abrkt.us-west-2.elasticbeanstalk.com/gaserve/v1/lists',{

     method:'GET',
     headers:{
       'userId': this.userIdNavVhanged,
       'Content-Type':'application/json'

     }
    })
    .then(res => {return res.json()})
  .then(response => 
   {
    var oneplateprice=(parseFloat(response.products[2].price)*1.15).toFixed(2);
    var twoplateprice=(parseFloat(response.products[1].price)*1.15).toFixed(2);
    this.test = oneplateprice;
    this.test2=twoplateprice;
    this.setState({ one_plate_stove: oneplateprice});
    this.setState({ two_plate_stove: twoplateprice});   
    this.setState({loading: false});
    } 
   )
   .catch(error => {
    this.setState({loading: false});
    this.setState({errorMessage:'Unexpected error: ' + error.toString()})
    this.setState({alertModal:!this.state.alertModal})
   }); 

  }
     

  _incrementCount() {
    this.setState(prevState => ({ count:(parseInt(prevState.count) + 1).toString() }));
    var totalValue = parseFloat(this.state.total) + parseFloat(this.state.one_plate_stove)
    this.setState({total:totalValue});
  }
 
  _incrementCount_0() {
    if(this.state.count>0){
      this.setState(prevState => ({ count: (parseInt(prevState.count) - 1).toString() }));
      var totalValue = parseFloat(this.state.total).toFixed(2) - parseFloat(this.state.one_plate_stove).toFixed(2);
      this.setState({total:totalValue});
    }
  }

  _incrementCount1() {
    this.setState(prevState => ({ count1: (parseInt(prevState.count1) + 1).toString()})); 
    var totalValue = parseFloat(this.state.total) + parseFloat(this.state.two_plate_stove)
    this.setState({total:totalValue});
  }
 
  _incrementCount_1() {
    if(this.state.count1>0){
    this.setState(prevState => ({ count1: (parseInt(prevState.count1) - 1).toString()  }));
    var totalValue = parseFloat(this.state.total).toFixed(2)- parseFloat(this.state.two_plate_stove).toFixed(2);
      this.setState({total:totalValue});
    }
  }

  
 
  render() {
  

   
      var salesBody=[
            {
              "productId":"1P_Stove",
              "quantity":this.state.count,
              "price":(this.test * this.state.count)
              
            },
              { 
                "productId":"2P_Stove",
                 "quantity":this.state.count1,
                 "price":(this.test2 * this.state.count1)
            }
    ] 


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
          <View style={{backgroundColor:"rgba(0, 0, 0, 0.8)",flex:1,flexDirection:'row',alignItems:'center',justifyContent:'center' }}>
          <Content>
          <Card style={{marginLeft:15,marginRight:15}}>
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
          
        <View style={styles.viewHeader}>
            <Icon style={styles.iconbackG} active name='chevron-left' color='white' size={30}
            onPress={()=>this.props.navigation.navigate('Sales',this.theProp)}
            /> 

             <Text style={styles.txtStyle}>STOVE</Text>         
               </View>
               <Image
         style={styles.imageStyle}
         source={require('../img/headerimage.png')}
         />

        <Content style={styles.box}>
          <Card>
            <CardItem>
              <Body>
                <Image source={require('../img/plate1.jpg')} style={styles.image}/>
              </Body>
              <Body>
                <Text style={styles.center}>1 Plate</Text>
                <Text style={styles.center}>${this.state.one_plate_stove}</Text>
              </Body>
              <Body>
                <Button transparent style={styles.plus}><Icon name="plus" color="black" size={25} onPress={ () => this._incrementCount() } /></Button>
                <TextInput  keyboardType='numeric'
                                 onChangeText={(count)=>{this.setState({count,total:((parseInt(this.state.count1) * this.test2)+(parseInt(count) * this.test)).toString()})}}
                                 value={ this.state.count} style={styles.quantity}></TextInput>     
                
                <Button transparent style={styles.minus}><Icon name="minus" color="black" size={25} onPress={ () => this._incrementCount_0() } /></Button>
              
              </Body>
            </CardItem>
          </Card>

          <Card>
            <CardItem>
              <Body>
                <Image source={require('../img/2stove.jpeg')} style={styles.image}/>
              </Body>
              <Body>
                <Text style={styles.center}>2 Plate</Text>
                <Text style={styles.center}>${this.state.two_plate_stove}</Text>
              </Body>
              <Body>
                <Button transparent style={styles.plus}><Icon name="plus" color="black" size={25} onPress={ () => this._incrementCount1() } /></Button>
                <TextInput  keyboardType='numeric'
                                  onChangeText={(count1)=>{this.setState({count1,total:((parseInt(this.state.count) * this.test)+(parseInt(count1) * this.test2)).toString()})}}
                                 value={this.state.count1}style={styles.quantity}></TextInput>     
                <Button transparent style={styles.minus}><Icon name="minus" color="black" size={25} onPress={ () => this._incrementCount_1() } /></Button>
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
                //onPress={() => {this.setState({removeModal:!this.state.removeModal})}}>
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
      height:120
  },
  txtStyle:{
      fontSize:36,
      color:'#FFFFFF',
      textAlign:'center',
      backgroundColor:'#E05900',
      left:130,
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
},
});
