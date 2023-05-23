package za.co.gaserve.api;

import java.util.ArrayList;
import java.util.List;

import io.swagger.model.SaleDTO;
import io.swagger.model.SaleItemDTO;
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
import io.swagger.api.SalesApiController;

import za.co.gaserve.dao.Dao;
import za.co.gaserve.dao.TestDaoConfig;
import za.co.gaserve.entities.lists.UserStatus;
import za.co.gaserve.entities.regions.Retailer;
import za.co.gaserve.entities.stock.Product;
import za.co.gaserve.entities.stock.Receiving;
import za.co.gaserve.entities.stock.Stock;
import za.co.gaserve.entities.user.Customer;
import za.co.gaserve.entities.user.User;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestDaoConfig.class, BusinessServiceConfig.class, SalesApiController.class})
@ConfigurationProperties(prefix = "test")

public class SalesApiControllerTest {

    @Autowired
    SalesApiController controller;

    @Autowired
    Dao<User> userDao;

    @Autowired
    Dao<UserStatus> userStatusDao;

    @Autowired
    Dao<Product> productDao;


    @Autowired
    Dao<Receiving> receivingDao;

    @Autowired
    Dao<Retailer> retailerDao;

    @Autowired
    Dao<Stock> stockDao;

    @Autowired
    Dao<Customer> customerDao;

    private List<SaleItemDTO> noSaleItem = null;



    private SaleDTO saleDTO;

    private SaleItemDTO saleItemDTO;
    private SaleItemDTO saleItemDTO2;

    String headers;

    UserStatus userStatus=null;
    User user1=null;
    User user2=null;
    String userId="";
    String userId2="";

    @Before
    public void setup() throws Exception{
        //bootstrap


        //user
        userStatusDao.create(new UserStatus("ACTIVE", "ACTIVE"));
        userStatus = userStatusDao.findById("ACTIVE");
        userDao.create(new User("Adi@gmail.com", userStatus));
        user1 = userDao.findOneByField("email", "Adi@gmail.com");
        userId = user1.getId();

        userDao.create(new User("Adivhaho@gmail.com", userStatus));
        user2 = userDao.findOneByField("email", "Adi@gmail.com");
        userId2 = user2.getId();


        // Retailer
        Retailer retailer = new Retailer("Maputla_Supermarket", user1);
        retailerDao.create(retailer);

        Product productStove = new Product("1P_Stove", "One Plate Stove", 10);
        productDao.create(productStove);

        // Product

        Product productCylinder = new Product("14kg_Cylinder", "Cylinder", 22);
        productDao.create(productCylinder);
        receivingDao.createTable();

        customerDao.create(new Customer("0727431752"));




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
    public void testAddSaleOneItemOneProduct() {
        //setup scenario

        saleItemDTO = new SaleItemDTO();

        saleItemDTO.setProductId("9kg_Cylinder");
        saleItemDTO.setQuantity(1);
        saleItemDTO.setPrice(50.0);


        List<SaleItemDTO> items = new ArrayList<>();
        items.add(saleItemDTO);

        saleDTO = new SaleDTO();
        saleDTO.setItems(items);

        saleDTO.setConsumer("0727431752");
        saleDTO.setTotalExVat(100.0);
        saleDTO.setTotalInclVat(110.0);
        saleDTO.setVat(12.0);
        saleDTO.setPrice(200.0);
        saleDTO.setPaymentMethod("CASH");


        ResponseEntity<?> responseEntity = controller.addSales(saleDTO, user1.getId());
        Assert.assertEquals(201, responseEntity.getStatusCodeValue());


    }

    @Test//This test still needs to be investigated
    public void testAddSale2Items2Products() {
        //setup scenario

        saleItemDTO = new SaleItemDTO();
        saleItemDTO.setProductId("9kg_Cylinder");
        saleItemDTO.setQuantity(2);
        saleItemDTO.setPrice(60.0);

        SaleItemDTO saleItemDTO2=new SaleItemDTO();
        saleItemDTO2.setProductId("9kg_Cylinder");
        saleItemDTO2.setQuantity(2);
        saleItemDTO2.setPrice(65.0);


        List<SaleItemDTO> items = new ArrayList<>();
        items.add(saleItemDTO);
        items.add(saleItemDTO2);

        saleDTO = new SaleDTO();
        saleDTO.setItems(items);

        saleDTO.setConsumer("0727431752");
        saleDTO.setTotalExVat(100.0);
        saleDTO.setTotalInclVat(110.0);
        saleDTO.setVat(12.0);
        saleDTO.setPrice(200.0);
        saleDTO.setPaymentMethod("CASH");


        ResponseEntity<?> responseEntity = controller.addSales(saleDTO, user1.getId());
        Assert.assertEquals(201, responseEntity.getStatusCodeValue());


    }

    @Test
    public void testSaleNoUser() {

        String user=null;
        saleItemDTO = new SaleItemDTO();
        saleItemDTO.setProductId("9kg_Cylinder");
        saleItemDTO.setQuantity(2);
        saleItemDTO.setPrice(60.0);

        List<SaleItemDTO> items = new ArrayList<>();
        items.add(saleItemDTO);

        saleDTO = new SaleDTO();
        saleDTO.setItems(items);

        saleDTO.setConsumer("0727431752");
        saleDTO.setTotalExVat(100.0);
        saleDTO.setTotalInclVat(110.0);
        saleDTO.setVat(12.0);
        saleDTO.setPrice(200.0);
        saleDTO.setPaymentMethod("CASH");

        ResponseEntity<?> responseEntity = controller.addSales(saleDTO, user);
        Assert.assertEquals(500, responseEntity.getStatusCodeValue());
    }

    @Test
    public void testSaleNoRetailer() {
        //same as no user as we find retailer by user


        saleItemDTO = new SaleItemDTO();
        saleItemDTO.setProductId("9kg_Cylinder");
        saleItemDTO.setQuantity(2);
        saleItemDTO.setPrice(60.0);

        List<SaleItemDTO> items = new ArrayList<>();
        items.add(saleItemDTO);

        saleDTO = new SaleDTO();
        saleDTO.setItems(items);

        saleDTO.setConsumer("0727431752");
        saleDTO.setTotalExVat(100.0);
        saleDTO.setTotalInclVat(110.0);
        saleDTO.setVat(12.0);
        saleDTO.setPrice(200.0);
        saleDTO.setPaymentMethod("CASH");

        ResponseEntity<?> responseEntity = controller.addSales(saleDTO, user2.getId());
        Assert.assertEquals(201, responseEntity.getStatusCodeValue());
    }

    @Test
    public void testSaleNoItems() {
        saleItemDTO = new SaleItemDTO();


        List<SaleItemDTO> items = new ArrayList<>();


        saleDTO = new SaleDTO();
        saleDTO.setItems(items);

        saleDTO.setConsumer("0727431752");
        saleDTO.setTotalExVat(100.0);
        saleDTO.setTotalInclVat(110.0);
        saleDTO.setVat(12.0);
        saleDTO.setPrice(200.0);
        saleDTO.setPaymentMethod("CASH");

        ResponseEntity<?> responseEntity = controller.addSales(saleDTO, user1.getId());
        Assert.assertEquals(404, responseEntity.getStatusCodeValue());
    }

    @Test
    public void testSaleNotEnoughStock(){


        saleItemDTO = new SaleItemDTO();

        saleItemDTO.setProductId("9kg_Cylinder");
        saleItemDTO.setQuantity(100);
        saleItemDTO.setPrice(50.0);


        List<SaleItemDTO> items = new ArrayList<>();
        items.add(saleItemDTO);

        saleDTO = new SaleDTO();
        saleDTO.setItems(items);

        saleDTO.setConsumer("0727431752");
        saleDTO.setTotalExVat(100.0);
        saleDTO.setTotalInclVat(110.0);
        saleDTO.setVat(12.0);
        saleDTO.setPrice(200.0);
        saleDTO.setPaymentMethod("CASH");


        ResponseEntity<?> responseEntity = controller.addSales(saleDTO, user1.getId());
        Assert.assertEquals(412, responseEntity.getStatusCodeValue());


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
