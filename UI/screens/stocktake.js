import React, {Component} from 'react';
import { StyleSheet, Image ,TextInput,Modal, TouchableHighlight,ActivityIndicator } from 'react-native';
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
    SwipeRow,
    View,
    Item,
    Input
} from "native-base";
import { GoogleSignin, GoogleSigninButton, statusCodes } from 'react-native-google-signin';

var userIdNavVhanged;
export default class stocktake extends Component {

    constructor(props) {
        super(props);

        
        this.userIdNavVhanged = JSON.stringify(props.navigation.state.params.userId).replace("\"","").replace("\"","");
        //alert(this.userIdNavVhanged);

        this.state = {
            userIdNav:this.userIdNavVhanged,
            count: '0',
            date:'',
            count1: '0',
            count2: '0',
            count3: '0',
            count10:'0',
            count11:'0',
            lpQuantity:'0',
            lpQuantityPrev:'0',
            todaysDate:'bbbbb',
            modalVisible: false,
            modalVisible1: false,
            modalVisible2: false,
            modalVisible3: false,
            modalVisible4: false,
            modalVisible5: false,
            addModal: false,
            showToast: false,
            clearModal: false,
            clearModal1: false,
            clearModal2: false,
            clearModal3: false,
            clearModal4: false,
            clearModal5: false,
            clearModal6: false,
            clearModal7: false,
            loading:false,

            lp_gas_card: "flex",
            one_stove_card:"flex",
            two_card_card:"flex"
        };
        
      }

      getTodaysDate(){
    
        var today = new Date();
        var day = today.getDate();
        var month = today.getMonth()+1; //January is 0!
        var hours=today.getHours();
        var minutes=today.getMinutes();
        var seconds=today.getSeconds();
        var milliseconds=today.getMilliseconds();
        var year = today.getFullYear();
        
        if(day<10) {
            day = '0'+day
        } 
        
        if(month<10) {
            month = '0'+month
        } 
        if(hours<10) {
            hours = '0'+hours
        } 
        if(minutes<10){
    
            minutes='0'+minutes
        }
        if(seconds<10){
    
            seconds='0'+seconds
        }
        
        //2018-11-01T19:44:01.128Z
        today = year+'-'+month+'-' + day+'T'+hours+':'+minutes+':'+seconds+'.'+milliseconds+'Z';
        this.setState({date:today},function() {
            //alert(this.state.date)
        });
      
       
      }

componentWillMount(){
    this.getTodaysDate();
}
     
      _incrementCount() {
        this.setState(prevState => ({ count:(parseInt(prevState.count) + 1).toString()  }));
      }
     
      _incrementCount_0() {
        if(this.state.count>0){
        this.setState(prevState => ({ count: (parseInt(prevState.count) - 1).toString() }));
        }
      }

      _incrementCount1() {
      this.setState(prevState1 => ({ count1: (parseInt(prevState1.count1 )+ 1).toString() }));
      }
      
      _incrementCount_1() {
        if(this.state.count1>0){
        this.setState(prevState1 => ({ count1: (parseInt(prevState1.count1 )- 1).toString() }));
        }
    }
      
      
      _incrementCount2() {
        this.setState(prevState2 => ({ count2: (parseInt(prevState2.count2) + 1).toString() }));
          ;
        }
        
      _incrementCount_2() {
        if(this.state.count2>0){
        this.setState(prevState2 => ({ count2: (parseInt(prevState2.count2) - 1).toString() }));
        }
      }
        
        _incrementCount3() {
            this.setState(prevState3 => ({ count3: (parseInt(prevState3.count3) + 1).toString() })); 
        }

        _incrementCount_3() {
            if(this.state.count3>0){
            this.setState(prevState3 => ({ count3: (parseInt(prevState3.count3) - 1).toString() }));
            }
          }

      _incrementCount10() {
        this.setState(prevState10 => ({ count10: (parseInt(prevState10.count10 )+ 1).toString() }));
      }
      
      _incrementCount_10() {
        if(this.state.count10>0){
        this.setState(prevState10 => ({ count10: (parseInt(prevState10.count10 )- 1 ).toString()}));
        }
      }

      _incrementCount11() {
        this.setState(prevState11 => ({ count11:  (parseInt(prevState11.count11) + 1).toString() }));
      }
      
      _incrementCount_11() {
        if(this.state.count11>0){
        this.setState(prevState11 => ({ count11: (parseInt(prevState11.count11 )- 1 ).toString()}));
        }
      }

      LpSubmit() {
        this.setState(prevState10 => ({ lpQuantity: (parseInt(prevState10.lpQuantity )+ 0).toString() }));
      }
      

 
      setModalVisible(visible) {
        this.setState({modalVisible: visible});
      }

      setModalVisible1(visible) {
        this.setState({modalVisible1: visible});
      }

      setModalVisible2(visible) {
        this.setState({modalVisible2: visible});
      }

      setModalVisible3(visible) {
        this.setState({modalVisible3: visible});
      }

      setModalVisible4(visible) {
        this.setState({modalVisible4: visible});
      }

      setModalVisible5(visible) {
        this.setState({modalVisible5: visible});
      }


      setAddModel(visible){
        this.setState({addModal:visible})
      }

      setClearModel(visible){
        this.setState({clearModal:visible})
      }

      setClearModel1(visible){
        this.setState({clearModal1:visible})
      }

      setClearModel2(visible){
        this.setState({clearModal2:visible})
      }

      setClearModel3(visible){
        this.setState({clearModal3:visible})
      }

      setClearModel4(visible){
        this.setState({clearModal4:visible})
      }

      setClearModel5(visible){
        this.setState({clearModal5:visible})
      }

      setClearModel6(visible){
        this.setState({clearModal6:visible})
      }

      setClearModel7(visible){
        this.setState({clearModal7:visible})
      }

      clear(gas){
        if(gas == "LP"){
            this.state.count='0';
            this.state.count1='0';
            this.state.count2='0';
            this.state.count3='0';
            this.setClearModel(false);
        }

        else if(gas == "STOVE1"){
            this.state.count10='0';
            this.setClearModel6(false);
        }else{
            this.state.count11='0';
            this.setClearModel7(false);
        }

        
      }

      successQty(){
        
        this.setModalVisible(false);
        this.setModalVisible1(false);
        this.setModalVisible2(false);
        this.setModalVisible3(false);
        this.setModalVisible4(false);
        this.setModalVisible5(false);

      }

     
  stockTake(){

    this.setState({loading:true}); 

var demostockBody = {
      "id": "efwgrbdfv",
      "stockItems": [
        {
          "product": {
            "id": "14kg_Cylinder"
          },
          "quantity": this.state.count1
        },
        {
          "product": {
            "id": "19kg_Cylinder"
          },
          "quantity": this.state.count2
        },
        {
          "product": {
            "id": "1P_Stove"
          },
          "quantity": this.state.count10
        },
        {
          "product": {
            "id": "2P_Stove"
          },
          "quantity": this.state.count11
        },
        {
          "product": {
            "id": "48kg_Cylinder"
          },
          "quantity": this.state.count3
        }, {
          "product": {
            "id": "9kg_Cylinder"
          },
          "quantity": this.state.count
        }, {
          "product": {
            "id": "LP_Gas"
          },
          "quantity": this.state.lpQuantity
        }
      ],
      "entryDate": this.state.date
    }

    var myjson = JSON.stringify(demostockBody)

    var obj = JSON.parse(myjson)




    var stocktakeBody = {
      "id": "efwgrbdfv",
      "stockItems": [],
      "entryDate": this.state.date
    }



    for (var i = 0; i < 7; i++) {
      var prod = obj.stockItems[i].product.id
      var quant = obj.stockItems[i].quantity
     


      if (quant > 0) {

        var jsonData = {

          "product": {
            "id": prod, 

          },
          "quantity": JSON.parse(quant),
          }

        stocktakeBody.stockItems.push(jsonData);

      }
    }
    

      fetch('http://gaservebackendservices-dev-stocktaking.eu-west-2.elasticbeanstalk.com/gaserve/v1/stocktakings', {

        method: 'POST',
        headers: {
          'userId': this.userIdNavVhanged,
          'Content-Type': 'application/json'

        },
        body: JSON.stringify(demostockBody)

      })
        .then(res => { return res.json() })
        .then(async (response) => {
          this.setState({loading:false}); 

          if (JSON.stringify(response).includes('"id":"ACTIVE"')) { 
            this.props.navigation.navigate('Home',{response,"userId":this.userIdNavVhanged});
            }

            if (JSON.stringify(response).includes('"id":"STOCKTAKE_UNBALANCED"')) {
              alert("Stocktaking is unbalanced");
              try {
                await GoogleSignin.revokeAccess();
                await GoogleSignin.signOut();
                this.setState({ userInfo: null }); // Remember to remove the user from your app's state as well
              } catch (error) {
                this.setState({loading:false});  
                console.error(error);
              }
              this.props.navigation.navigate('Login',{response,"userId":this.userIdNavVhanged});
              }


        })
        .catch(error =>{
          this.setState({loading:false}); 
           console.error(error)
  });
   
 
  }


  render() {
      
    return (
     <Container >  

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
                    <Text style={{alignSelf:'center',color:'white'}}>Calculating stocktake. . .</Text>
                    </Body>
                  </Row>
              </Grid>
          </Content>
          </View>
        </Modal>   

        <Modal
          animationType="fade"
          transparent={true}
          visible={this.state.clearModal}
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
                    <Text style={styles.textH4}>Are you sure you want to clear this item's details?</Text>
                    </Body>
                  </Row>
                  <Row>
                  </Row>
                  <Row style={{marginTop:15,marginBottom:15}}>
                      <Col>
                      <Body style={{ alignSelf:'center', justifyContext: 'center'}}>
                      <Button full rounded success style={styles.submitbtn} style={{width:120}} onPress={() => {this.clear("LP"); }}>
                    <Text >CLEAR</Text>
                    </Button>
                </Body>
                      </Col>
                      <Col>
                      <Body style={{ alignSelf:'center', justifyContext: 'center'}}>
                      <Button full rounded danger style={styles.submitCancelbtn} style={{width:120}} onPress={() => {this.setClearModel(false); }}>
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

        <Modal
          animationType="fade"
          transparent={true}
          visible={this.state.modalVisible}
          onRequestClose={() => {
          }}>
          <View style={{backgroundColor:"rgba(0, 0, 0, 0.8)",flex:1,flexDirection:'row',alignItems:'center',justifyContent:'center' }}>
          <Content>
          <Card style={{marginLeft:15,marginRight:15}}>
            <CardItem>
              <Grid>
                  <Row>
                      <Body>
                    <Text style={styles.textH4}>Liquefied  Petroleum (LP) Gas</Text>
                    </Body>
                  </Row>
                  <Row>
                      <Body style={{marginTop:30}}>
                    <Text style={styles.textH5}>Enter Quantity</Text>
                    </Body>
                  </Row>
                  <Row>
                  <Content style={{marginBottom:10}}>
          <Item>
            <Input keyboardType='numeric' placeholder="Enter Quantity"
             onChangeText={(lpQuantity)=>this.setState({lpQuantity})}
             value={this.state.lpQuantity}
            
            />
          </Item>
        </Content>
                  </Row>
                  <Row style={{marginTop:15,marginBottom:15}}>
                      <Col>
                      <Body style={{ alignSelf:'center', justifyContext: 'center'}}>
                      <Button full rounded success style={styles.submitbtn} style={{width:120}} onPress={() => {this.successQty(); }}>
                    <Text >SUBMIT</Text>
                    </Button>
                </Body>
                      </Col>
                      <Col>
                      <Body style={{ alignSelf:'center', justifyContext: 'center'}}>
                      <Button full rounded danger style={styles.submitCancelbtn} style={{width:120}} onPress={() => {this.setModalVisible(!this.state.modalVisible); }}>
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

                <Modal
          animationType="fade"
          transparent={true}
          visible={this.state.clearModal6}
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
                    <Text style={styles.textH4}>Are you sure you want to clear this item's details?</Text>
                    </Body>
                  </Row>
                  <Row>
                  </Row>
                  <Row style={{marginTop:15,marginBottom:15}}>
                      <Col>
                      <Body style={{ alignSelf:'center', justifyContext: 'center'}}>
                      <Button full rounded success style={styles.submitbtn} style={{width:120}} onPress={() => {this.clear("STOVE1"); }}>
                    <Text >CLEAR</Text>
                    </Button>
                </Body>
                      </Col>
                      <Col>
                      <Body style={{ alignSelf:'center', justifyContext: 'center'}}>
                      <Button full rounded danger style={styles.submitCancelbtn} style={{width:120}} onPress={() => {this.setClearModel6(false); }}>
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







          <Modal
          animationType="fade"
          transparent={true}
          visible={this.state.clearModal7}
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
                    <Text style={styles.textH4}>Are you sure you want to clear this item's details?</Text>
                    </Body>
                  </Row>
                  <Row>
                  </Row>
                  <Row style={{marginTop:15,marginBottom:15}}>
                      <Col>
                      <Body style={{ alignSelf:'center', justifyContext: 'center'}}>
                      <Button full rounded success style={styles.submitbtn} style={{width:120}} onPress={() => {this.clear("STOVE2"); }}>
                    <Text >CLEAR</Text>
                    </Button>
                </Body>
                      </Col>
                      <Col>
                      <Body style={{ alignSelf:'center', justifyContext: 'center'}}>
                      <Button full rounded danger style={styles.submitCancelbtn} style={{width:120}} onPress={() => {this.setClearModel7(false); }}>
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


           
          <View style={styles.viewHeader}>
            <Icon style={styles.iconbackG} active name='chevron-left' color='white' size={30}
            onPress={()=>this.props.navigation.navigate('Login')}
            /> 

             <Text style={styles.txtStyle}>Stocktake</Text>         
               </View>
               <Image
         style={styles.imageStyle}
         source={require('../img/headerimage.png')}
         />

        <Content style={styles.box} >          
          <Card style={{display:this.state.lp_gas_card}}> 
          
            <Body style={{justifyContext: 'center'}}>
                <Text style={styles.cardTitle}>Liquefied Petroleum (LP) Gas</Text>
            </Body>
            <SwipeRow
                leftOpenValue={80}
                rightOpenValue={-80}
                left={
                <Button success onPress={() => {
                    this.setModalVisible(true)
 }}>
                    <Icon active name="plus" color='white' size={25} />
                </Button>
                }
                body={
                <View>
                        <Body>
                            <Grid>
                            <Col style={{width:120}}>
                                <Image source={require('../img/gascylinder.jpg')} style={styles.image}/>
                            </Col>
                            <Col style={{width:110}}>
                                <Row >

                                <Text style={styles.size}>9.0 kg</Text>

                                <Button transparent style={styles.minus}>


                                <Icon
                                onPress={ () => this._incrementCount_0() }
                                name="minus-circle" color="red" size={25} />


                                </Button>

                                <TextInput 
                                 keyboardType='numeric'
                                 onChangeText={(count)=>this.setState({count})}
                                 value={this.state.count}
                                
                                style={styles.quantity}></TextInput>
                                
                                <Button transparent style={styles.plus}><Icon
                                onPress={ () => this._incrementCount() } name="plus-circle" color="green" size={25} /></Button>
                                </Row>
                                <Row>


                                <Text style={styles.size}>14.0 kg</Text>


                                <Button transparent style={styles.minus}><Icon
                                onPress={ () => this._incrementCount_1() }
                                 name="minus-circle" color="red" size={25} /></Button>


                                <TextInput style={styles.txtInput}
                                 keyboardType='numeric'
                                 onChangeText={(count1)=>this.setState({count1})}
                                 value={this.state.count1}
                                 
                                style={styles.quantity}></TextInput>        
                                <Button transparent style={styles.plus}><Icon 
                                onPress={ () => this._incrementCount1() }name="plus-circle" color="green" size={25} /></Button>
                                </Row>
                                <Row>


                                    

                                <Text style={styles.size}>19.0 kg</Text>
                                <Button transparent style={styles.minus}><Icon 
                                onPress={ () => this._incrementCount_2() }
                                name="minus-circle" color="red" size={25} />
                                
                                </Button>

                                <TextInput 
                                 keyboardType='numeric'
                                 onChangeText={(count2)=>this.setState({count2})}
                                 value={this.state.count2}
                                style={styles.quantity}></TextInput>        
                                <Button transparent style={styles.plus}><Icon
                                onPress={ () => this._incrementCount2() } name="plus-circle" color="green" size={25} />


                                </Button>
                                </Row>
                                <Row>

                                <Text style={styles.size}>48.0 kg</Text>
                                <Button transparent style={styles.minus}><Icon
                                onPress={ () => this._incrementCount_3() }
                                name="minus-circle" color="red" size={25} />
                                </Button>


                                <TextInput 
                                 keyboardType='numeric'
                                 onChangeText={(count3)=>this.setState({count3})}
                                 value={this.state.count3}
                                style={styles.quantity}></TextInput>  
                                <Button transparent style={styles.plus}><Icon 
                                onPress={ () => this._incrementCount3() }name="plus-circle" color="green" size={25} />

                                </Button>
                                </Row>
                            </Col>
                            </Grid>
                        </Body>
                    </View>
                }
                right={
                <Button danger onPress={() => {this.setClearModel(true)}}>
                    <Icon active name="trash" color='white' size={25} />
                </Button>
                }
            />
          </Card>

            <Card style={{display:this.state.two_card_card}}>
            <Body style={{justifyContext: 'center'}}>
                <Text style={styles.cardTitle}>Stove 1 Plate</Text>
            </Body>
            <SwipeRow
                leftOpenValue={0}
                rightOpenValue={-80}
                left={<Text></Text>}
                body={
                <View>
                        <Body>
                            <Grid>
                            <Col style={{width:120}}>
                                <Image source={require('../img/plate1.jpg')} style={styles.image2}/>
                            </Col>
                            <Col style={{width:100}}>
                                <Row>
                                <Text style={styles.size}></Text>
                                <Button transparent style={styles.minus}><Icon 
                                 onPress={ () => this._incrementCount_10() }
                                name="minus-circle" color="red" size={25} /></Button>
                                <TextInput
                                 keyboardType='numeric'
                                 onChangeText={(count10)=>this.setState({count10})}
                                 value={this.state.count10}
                                style={styles.quantity}></TextInput>  
                                <Button transparent style={styles.plus}><Icon
                                 onPress={ () => this._incrementCount10() }
                                name="plus-circle" color="green" size={25} /></Button>      
                               
                                </Row>
                                
                                
                            </Col>
                            </Grid>
                        </Body>
                    </View>
                }
                right={
                <Button danger onPress={() => {this.setClearModel6(true)}}>
                    <Icon active name="trash" color='white' size={25} />
                </Button>
                }
            />
          </Card>
          <Card style={{display:this.state.one_stove_card}}>
            <Body style={{justifyContext: 'center'}}>
                <Text style={styles.cardTitle}>Stove 2 Plate</Text>
            </Body>
            <SwipeRow
                leftOpenValue={0}
                rightOpenValue={-80}
                left={<Text></Text>}
                body={
                <View>
                        <Body>
                            <Grid>
                            <Col style={{width:120}}>
                                <Image source={require('../img/2stove.jpeg')} style={[styles.image3]}/>
                            </Col>
                            <Col style={{width:100}}>
                                <Row>
                                <Text style={styles.size}></Text>
                                <Button transparent style={styles.minus}><Icon 
                                 onPress={ () => this._incrementCount_11() }
                                name="minus-circle" color="red" size={25} /></Button>
                                <TextInput
                                 keyboardType='numeric'
                                 onChangeText={(count11)=>this.setState({count11})}
                                 value={this.state.count11}

                                style={styles.quantity}>
            
                                </TextInput>  
                                <Button transparent style={styles.plus}><Icon
                                 onPress={ () => this._incrementCount11() }
                                name="plus-circle" color="green" size={25} /></Button>      
                               
                                </Row>
                                
                                
                            </Col>
                            </Grid>
                        </Body>
                    </View>
                }
                right={
                <Button danger onPress={() => {this.setClearModel7(true)}}>
                    <Icon active name="trash" color='white' size={25} />
                </Button>
                }
            />
          </Card>
          <Card transparent style={styles.btncard}>
          <Button rounded success style={styles.submitbtn}
          
          onPress={()=>this.stockTake()}
          >
                    <Text style={{marginLeft:92}}>CONFIRM</Text>
                </Button>
                   
          </Card>
        </Content>
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
    }
    ,
    txtInput:{
        width:30,
      borderTopColor:'black'
        
    },
    iconbackG:{
        backgroundColor:'#E05900',
        top:11.2,
        left:10       
    }
    ,
    image2: {
        flex:1,
        alignSelf: 'center',
        width: 80,
        height: 100,       
    },
    image3: {
      flex:1,
      alignSelf: 'center',
      width: 100,
      height: 100,       
  },
    viewHeader:{

        flexDirection:'row',
        backgroundColor:'#E05900', 
    },
    btncard1: {
        flex:0.5,
        marginTop: 40,
        marginBottom: 50,
        backgroundColor:'#808080',
        opacity:0.5
    },

    btncard: {
        marginTop: 40,
        marginBottom: 50
    },
    submitbtn: {
      width:280,
      alignSelf:'center'
    },
    box: {
      marginTop:-65
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
    },
    minus:{
        alignSelf: 'center', 
        marginLeft: -10
    },
    cardTitle:{
        fontSize:22,
        alignSelf: 'center',
        color:'#FD6721',
        marginTop: 10
    },
    size:{
      fontSize:22,
      alignSelf: 'center',
      marginRight: 20,
      marginLeft: 10,
      width: 80
    },
    quantity:{
      fontSize:22,
      alignSelf: 'center',
      marginLeft: 1,
      textAlign:'center',
      width:80,
      height:50,
      
      
    },
    textH4: {
        alignSelf: 'center',
        color: 'black',
        fontSize: 20
      },
      textH5: {
        color: 'black',
        fontSize: 17
      },
    textH1: {
        color: 'black',
        fontSize: 40
      },
    textH2: {
        color: 'black',
        fontSize: 20
    },
    textH: {
        color: 'white',
        fontSize: 20
    },
    backgroundImage: {},
    imageStyle:{
        width:null,
        height:120,
    },
    txtStyle:{
        fontSize:36,
        color:'#FFFFFF',
        textAlign:'center',
        backgroundColor:'#E05900'  ,
        left:90,
        top:8         
    }
});
