import React, {Component} from 'react';
import { StyleSheet, Image } from 'react-native';
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
    ListItem,
    CheckBox
} from "native-base";

export default class salesCylinder extends Component {

    constructor() {
        super();
    
        this.state = {
          checked: false
        };
      }

  render() {
    const { tag } = this.props;
    const { checked1,checked2,checked3,checked4,checked5,checked6,checked7,checked8,checkedDA1,checkedMA1,checkedRI1,checkedET1 } = this.state;

    return (
        <Container>
           <View style={styles.viewHeader}>
            <Icon style={styles.iconbackG} active name='chevron-left' color='white' size={30}
            onPress={()=>alert('Pressed the back button')}
            /> 
             <Text style={styles.txtStyle}>Sales</Text>         
               </View>
               <Image
         style={styles.imageStyle}
         source={require('../img/headerimage.png')}
         />
        <Content style={{marginTop:-40}}>
          <Card>
            <Body style={{justifyContext: 'center'}}>
                <Text style={styles.cardTitle}>Liquefied Petroleum (LP)</Text>
            </Body>
            <CardItem>
                        <Body>
                            <Grid>
                            <Col style={{width:100}}>
                                <Image source={require('../img/gascylinder.jpg')} style={styles.image}/>
                            </Col>
                            <Col>
                            <Content>
                                <ListItem>
                                    <CheckBox key="LP1" title="LP1" checked={checked1} onPress={() => this.setState({checked1: !checked1})} color="green"/>
                                    <Body>
                                    <Text>9.0 kg ( Unit: $100.00 )</Text>
                                    </Body>
                                </ListItem>
                                <ListItem>
                                    <CheckBox key="LP2" title="LP2" checked={checked2} onPress={() => this.setState({checked2: !checked2})} color="green"/>
                                    <Body>
                                    <Text>14.0 kg ( Unit: $100.00 )</Text>
                                    </Body>
                                </ListItem>
                                <ListItem>
                                    <CheckBox key="LP3" title="LP3" checked={checked3} onPress={() => this.setState({checked3: !checked3})} color="green"/>
                                    <Body>
                                    <Text>19.0 kg ( Unit: $100.00 )</Text>
                                    </Body>
                                </ListItem>
                                <ListItem>
                                    <CheckBox key="LP4" title="LP4" checked={checked4} onPress={() => this.setState({checked4: !checked4})} color="green"/>
                                    <Body>
                                    <Text>48.0 kg ( Unit: $100.00 )</Text>
                                    </Body>
                                </ListItem>
                                </Content>
                            </Col>
                            </Grid>
                        </Body>
            </CardItem>
          </Card>
          <Card transparent style={styles.btncard}>
          <Button rounded success style={styles.submitbtn}>
                    <Text style={{marginLeft:92}}>PROCEED</Text>
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
        width: 80,
        height: 80,
        resizeMode: 'center'
    },
    txtInput:{
      borderTopColor:'black'       
    },
    iconbackG:{
        backgroundColor:'#E05900',
        top:11.2,
        left:10   
    },
    image2: {
        flex:1,
        alignSelf: 'center',
        width: 80,
        height: 100,    
    },
    viewHeader:{
        flexDirection:'row',
        backgroundColor:'#E05900', 
    },
    btncard: {
        marginTop: 40,
        marginBottom: 50
    },
    submitbtn: {
      width:280,
      alignSelf:'center'
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
        marginLeft: 5
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
      marginRight: 15,
      marginLeft: 25,
      width: 40
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
    imageStyle:{
      width:null,
      height:120
  },
  txtStyle:{
      fontSize:36,
      color:'#FFFFFF',
      textAlign:'center',
      backgroundColor:'#E05900',
      left:140,
      top:8     
  }
});
