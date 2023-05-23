package za.co.gaserve.api;

import co.za.gaserve.business.ReceivingBusinessService;
import co.za.gaserve.business.RetailerBusinessService;
import co.za.gaserve.business.StockBusinessService;
import io.swagger.model.HttpResponseBody;
import io.swagger.model.ReceivingsDTO;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import co.za.gaserve.business.BusinessServiceConfig;
import io.swagger.api.ReceivingsApiFacade;
import za.co.gaserve.dao.Dao;
import za.co.gaserve.dao.DaoConfig;
import za.co.gaserve.dao.TestDaoConfig;
import za.co.gaserve.entities.lists.UserStatus;
import za.co.gaserve.entities.regions.Retailer;
import za.co.gaserve.entities.stock.Product;
import za.co.gaserve.entities.stock.ReceivedItem;
import za.co.gaserve.entities.stock.Receiving;
import za.co.gaserve.entities.stock.Stock;
import za.co.gaserve.entities.user.User;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ConfigurationProperties(prefix = "test")
@ContextConfiguration(classes={TestDaoConfig.class,BusinessServiceConfig.class,ReceivingsApiFacade.class})

public class ReceivingsApiControllerTest {


    @Autowired
    ReceivingsApiFacade controller;

    @Autowired
    Dao<Product> productDao;


    @Autowired
    Dao<Receiving> receivingDao;

    @Autowired
    Dao<User> userDao;

    @Autowired
    Dao<Retailer> retailerDao;

    @Autowired
    Dao<UserStatus> userStatusDao;

    @Autowired
    RetailerBusinessService retailerBusinessService;
    @Autowired
    private StockBusinessService stockBusinessService;

    @Autowired
    private ReceivingBusinessService receivingBusinessService;

    @Autowired
    Dao<Stock> stockDao;





    String userId="";
    ReceivingsDTO receivingsDto;
    ReceivingsDTO receivingDTO;
    UserStatus userStatus=null;
    User user1=null;

    @Before
    public void setup()throws Exception{


        // User
        userStatusDao.create(new UserStatus("ACTIVE", "ACTIVE"));
        userStatus = userStatusDao.findById("ACTIVE");
        userDao.create(new User("Adi@gmail.com", userStatus));
        user1 = userDao.findOneByField("email", "Adi@gmail.com");
        userId = user1.getId();

        // Retailer
        Retailer retailer = new Retailer("Maputla_Supermarket", user1);
        retailerDao.create(retailer);

        Product productStove = new Product("1P_Stove", "One Plate Stove", 10);
        productDao.create(productStove);

        // Product

        Product productCylinder = new Product("14kg_Cylinder", "Cylinder", 22);
        productDao.create(productCylinder);
        receivingDao.createTable();




        Product product1PlateStove = new Product("1P_Stove", "One Plate Stove", 10);
        productDao.create(product1PlateStove);
//        Product productCylinder = new Product("14kg_Cylinder", "Cylinder", 22);
//        productDao.create(product1PlateStove);
        Product product2Plate = new Product("1P_Stove", "2 Plate Stove", 50);
        productDao.create(product2Plate);
        Product product9kgCylinder = new Product("9kg_Cylinder", "9kg Cylinder", 20);
        productDao.create(product9kgCylinder);
        Product product14kgCylinder = new Product("14kg_Cylinder", "14kg Cylinder", 20);
        productDao.create(product14kgCylinder);
        Product product19kgCylinder = new Product("19kg_Cylinder", "19kg Cylinder", 20);
        productDao.create(product19kgCylinder);
        Product product48kgCylinder = new Product("48kg_Cylinder", "48kg Cylinder", 20);
        productDao.create(product48kgCylinder);
        Product productGas = new Product("LP_Gas", "Gas", 200);
        productDao.create(productGas);

        // Stock
        Stock stock1Plate = new Stock(product1PlateStove, 50, retailer);
        stockDao.create(stock1Plate);
        Stock stock14kgCylinder = new Stock(product14kgCylinder, 50, retailer);
        stockDao.create(stock14kgCylinder);
        Stock stock2Plate = new Stock(product2Plate, 50, retailer);
        stockDao.create(stock2Plate);
        Stock stock9kgCylinder = new Stock(product9kgCylinder, 50, retailer);
        stockDao.create(stock9kgCylinder);
        Stock stock19kgCylinder = new Stock(product19kgCylinder, 50, retailer);
        stockDao.create(stock19kgCylinder);
        Stock stock48kgCylinder = new Stock(product48kgCylinder, 50, retailer);
        stockDao.create(stock48kgCylinder);
        Stock stockGas = new Stock(productGas, 50, retailer);
        stockDao.create(stockGas);
        stockDao.createTable();



    }
    @Test
    public void testAddReceiving(){


//        //setup scenario

        Product productStove = new Product("1P_Stove", "One Plate Stove", 10);
        ReceivingsDTO receivingsDto=new ReceivingsDTO();
        ReceivedItem receiving=new ReceivedItem();
        receiving.setQuantity(11);
        receiving.setProduct(productStove);
        Retailer retailer = retailerBusinessService.getRetailerByUser(user1.getId());




        List<ReceivedItem> receivings=new ArrayList<>();
        receivings.add(receiving);




        ResponseEntity<?> responseEntity=controller.doReceiving(receivingsDto.recievingDTO(receivings),user1.getId());
        Assert.assertEquals(200,responseEntity.getStatusCodeValue());

    }

    @Test
    public void testGetRecieving(){//getReceiving Pending

    }

    @Test
    public void testReceivingsReceivingIdGet(){
        //receivingsReceivingIdGet Pending
    }


    @Test
    public void testAddReceivingInvalidProducts(){
//        setup scenario

        Product productStove = new Product("1P-Stove", "One Plate Stove", 10);
        ReceivingsDTO receivingsDto=new ReceivingsDTO();
        ReceivedItem receiving=new ReceivedItem();
        receiving.setQuantity(10);
        receiving.setProduct(productStove);
        Retailer retailer = retailerBusinessService.getRetailerByUser(user1.getId());




        List<ReceivedItem> receivings=new ArrayList<>();
        receivings.add(receiving);




        ResponseEntity<?> responseEntity=controller.doReceiving(receivingsDto.recievingDTO(receivings),user1.getId());
        Assert.assertEquals(404,responseEntity.getStatusCodeValue());
    }

    @Test
    public void testAddReceivingZeroQuantity(){
        //setup scenario
        Product productCylinder = new Product("14kg_Cylinder", "Cylinder", 22);
        ReceivingsDTO receivingsDto=new ReceivingsDTO();
        ReceivedItem receiving=new ReceivedItem();
        receiving.setQuantity(0);
        receiving.setProduct(productCylinder);
        Retailer retailer = retailerBusinessService.getRetailerByUser(user1.getId());




        List<ReceivedItem> receivings=new ArrayList<>();
        receivings.add(receiving);




        ResponseEntity<?> responseEntity=controller.doReceiving(receivingsDto.recievingDTO(receivings),user1.getId());
        Assert.assertEquals(412,responseEntity.getStatusCodeValue());
    }

    @Test
    public void testAddReceivingOneItem(){
        //setup scenario
        Product productCylinder = new Product("14kg_Cylinder", "Cylinder", 22);
        ReceivingsDTO receivingsDto=new ReceivingsDTO();
        ReceivedItem receiving=new ReceivedItem();
        receiving.setQuantity(10);
        receiving.setProduct(productCylinder);
        Retailer retailer = retailerBusinessService.getRetailerByUser(user1.getId());




        List<ReceivedItem> receivings=new ArrayList<>();
        receivings.add(receiving);




        ResponseEntity<?> responseEntity=controller.doReceiving(receivingsDto.recievingDTO(receivings),user1.getId());
        Assert.assertEquals(200,responseEntity.getStatusCodeValue());
    }

    @Test
    public void testAddReceivingTenItems(){

        //setup scenario


        Product product1PlateStove = new Product("1P_Stove", "One Plate Stove", 10);
        Product product2Plate = new Product("1P_Stove", "2 Plate Stove", 50);
        Product product9kgCylinder = new Product("9kg_Cylinder", "9kg Cylinder", 20);
        Product product14kgCylinder = new Product("14kg_Cylinder", "14kg Cylinder", 20);
        Product product19kgCylinder = new Product("19kg_Cylinder", "19kg Cylinder", 20);
        Product product48kgCylinder = new Product("48kg_Cylinder", "48kg Cylinder", 20);
        Product productGas = new Product("LP_Gas", "Gas", 200);
        Product product19kgCylinder2 = new Product("19kg_Cylinder", "19kg Cylinder", 20);
        Product product48kgCylinder2 = new Product("48kg_Cylinder", "48kg Cylinder", 20);
        Product productGas2 = new Product("LP_Gas", "Gas", 200);


        List<Product> productsList=new ArrayList<>();
        productsList.add(product1PlateStove);
        productsList.add(product14kgCylinder);
        productsList.add(product2Plate);
        productsList.add(product9kgCylinder);
        productsList.add(product19kgCylinder);
        productsList.add(product48kgCylinder);
        productsList.add(productGas);
        productsList.add(product19kgCylinder2);
        productsList.add(product48kgCylinder2);
        productsList.add(productGas2);

        List<ReceivedItem> receivings=new ArrayList<>();
        ReceivingsDTO receivingsDto=new ReceivingsDTO();

        for(Product product:productsList){

            ReceivedItem receiving=new ReceivedItem();
            receiving.setQuantity(19);
            receiving.setProduct(product);

            receivings.add(receiving);
        }

        ResponseEntity<?> responseEntity=controller.doReceiving(receivingsDto.recievingDTO(receivings),user1.getId());
        Assert.assertEquals(200,responseEntity.getStatusCodeValue());


    }

    @After
    public void cleanUp(){
        userStatusDao.deleteAll();
        userDao.deleteAll();
        retailerDao.deleteAll();
        productDao.deleteAll();
        stockDao.deleteAll();
        receivingDao.deleteAll();

    }
}
