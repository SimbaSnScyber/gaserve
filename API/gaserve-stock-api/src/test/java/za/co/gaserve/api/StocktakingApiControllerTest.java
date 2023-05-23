package za.co.gaserve.api;

import io.swagger.model.ProductDTO;
import io.swagger.model.StockDTO;
import io.swagger.model.StocktakingDTO;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import co.za.gaserve.business.BusinessServiceConfig;
import io.swagger.api.StockApiFacade;
import za.co.gaserve.dao.Dao;
import za.co.gaserve.dao.TestDaoConfig;
import za.co.gaserve.entities.lists.UserStatus;
import za.co.gaserve.entities.regions.Retailer;
import za.co.gaserve.entities.stock.Product;
import za.co.gaserve.entities.stock.Stock;
import za.co.gaserve.entities.user.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={TestDaoConfig.class,BusinessServiceConfig.class,StockApiFacade.class})
public class  StocktakingApiControllerTest {

	@Autowired
	Dao<User> userDao;

	@Autowired
	Dao<UserStatus> userStatusDao;

	@Autowired
	Dao<Product> productDao;

	@Autowired
	Dao<Retailer> retailerDao;

	@Autowired
	Dao<Stock> stockDao;

	@Autowired
	StockApiFacade controller;

	StocktakingDTO stocktakingDTO = new StocktakingDTO();
	Retailer retailer;
	StockDTO stockDTO = new StockDTO(); // remove
	UserStatus userStatus;
	String userId = "";
	String userId2 = "";
	String userId3 = "";
	String userId4 = "";

	@Before
	public void setup() throws InterruptedException {

		// User
		userStatusDao.create(new UserStatus("STOCKTAKE_PENDING", "STOCKTAKE_PENDING"));
		userStatus = userStatusDao.findById("STOCKTAKE_PENDING");
		userDao.create(new User("thulani@gmail.com", userStatus));
		User user1 = userDao.findOneByField("email", "thulani@gmail.com");
		userId = user1.getId();

		// User not retail manager
		userStatusDao.create(new UserStatus("STOCKTAKE_PENDING", "STOCKTAKE_PENDING"));
		userStatus = userStatusDao.findById("STOCKTAKE_PENDING");
		userDao.create(new User("bob@burgers.com", userStatus));
		User user2 = userDao.findOneByField("email", "bob@burgers.com");
		userId2 = user2.getId();

		// User unbalancing
		userStatusDao.create(new UserStatus("ACTIVE", "ACTIVE"));
		userStatus = userStatusDao.findById("ACTIVE");
		userDao.create(new User("toolz@active.com", userStatus));
		User user3 = userDao.findOneByField("email", "toolz@active.com");
		userId3 = user3.getId();

		// User locked
		userStatusDao.create(new UserStatus("DISABLED", "DISABLED"));
		userStatus = userStatusDao.findById("DISABLED");
		userDao.create(new User("toolz@locked.com", userStatus));
		User user4 = userDao.findOneByField("email", "toolz@locked.com");
		userId4 = user4.getId();

		// Retailer
		retailer = new Retailer("Thulani", user1);
		retailerDao.create(retailer);

		// Product
		Product product1Plate = new Product("1P_Stove", "1P_Stove", 30);
		productDao.create(product1Plate);

		Product product2Plate = new Product("2P_Stove", "2P_Stove", 50);
		productDao.create(product2Plate);

		Product product9kgCylinder = new Product("9kg_Cylinder", "9kg_Cylinder", 20);
		productDao.create(product9kgCylinder);

		Product product14kgCylinder = new Product("14kg_Cylinder", "14kg_Cylinder", 20);
		productDao.create(product14kgCylinder);

		Product product19kgCylinder = new Product("19kg_Cylinder", "19kg_Cylinder", 20);
		productDao.create(product19kgCylinder);

		Product product48kgCylinder = new Product("48kg_Cylinder", "48kg Cylinder", 20);
		productDao.create(product48kgCylinder);

		Product productGas = new Product("LP_Gas", "LP_Gas", 200);
		productDao.create(productGas);

		// Stock
		Product Gas = productDao.findById("LP_Gas");
		Product Stove1P = productDao.findById("1P_Stove");
		Product Stove2P = productDao.findById("2P_Stove");
		Product kg9_Cylinder = productDao.findById("9kg_Cylinder");
		Product kg14_Cylinder = productDao.findById("14kg_Cylinder");
		Product kg19_Cylinder = productDao.findById("19kg_Cylinder");
		Product kg48_Cylinder = productDao.findById("48kg_Cylinder");

		Retailer retailer1 = retailerDao.findOneByField("retailerName", "DZ GLAUDINA");

		stockDao.create(new Stock(Gas, 1000, retailerDao.findById(retailer.getId())));
		stockDao.create(new Stock(Stove1P, 1000, retailerDao.findById(retailer.getId())));
		stockDao.create(new Stock(Stove2P, 1000, retailerDao.findById(retailer.getId())));
		stockDao.create(new Stock(kg9_Cylinder, 1000, retailerDao.findById(retailer.getId())));
		stockDao.create(new Stock(kg14_Cylinder, 1000, retailerDao.findById(retailer.getId())));
		stockDao.create(new Stock(kg19_Cylinder, 1000, retailerDao.findById(retailer.getId())));
		stockDao.create(new Stock(kg48_Cylinder, 1000, retailerDao.findById(retailer.getId())));

		List<Stock> stockList = new ArrayList<>();
		stockList.add(new Stock(Gas, 1000, retailer));

		List<StockDTO> stockDTOList = new ArrayList<>();

		ProductDTO productLPGas = new ProductDTO();
		productLPGas.setId("LP_Gas");
		StockDTO stockLPGas = new StockDTO();
		stockLPGas.setProduct(productLPGas);
		stockLPGas.setQuantity(1000.0);

		ProductDTO productDTO1PStove = new ProductDTO();
		productDTO1PStove.setId("1P_Stove");
		StockDTO stockDTO1PStove = new StockDTO();
		stockDTO1PStove.setProduct(productDTO1PStove);
		stockDTO1PStove.setQuantity(1000.0);

		ProductDTO productDTO2PStove = new ProductDTO();
		productDTO2PStove.setId("2P_Stove");
		StockDTO stockDTO2PStove = new StockDTO();
		stockDTO2PStove.setProduct(productDTO2PStove);
		stockDTO2PStove.setQuantity(1000.0);

		ProductDTO productDTO9kgCylinder = new ProductDTO();
		productDTO9kgCylinder.setId("9kg_Cylinder");
		StockDTO stockDTO9kgCylinder = new StockDTO();
		stockDTO9kgCylinder.setProduct(productDTO9kgCylinder);
		stockDTO9kgCylinder.setQuantity(1000.0);

		ProductDTO productDTO14kgCylinder = new ProductDTO();
		productDTO14kgCylinder.setId("14kg_Cylinder");
		StockDTO stockDTO14kgCylinder = new StockDTO();
		stockDTO14kgCylinder.setProduct(productDTO14kgCylinder);
		stockDTO14kgCylinder.setQuantity(1000.0);

		ProductDTO productDTO19kgCylinder = new ProductDTO();
		productDTO19kgCylinder.setId("19kg_Cylinder");
		StockDTO stockDTO19kgCylinder = new StockDTO();
		stockDTO19kgCylinder.setProduct(productDTO19kgCylinder);
		stockDTO19kgCylinder.setQuantity(1000.0);

		ProductDTO productDTO48kgCylinder = new ProductDTO();
		productDTO48kgCylinder.setId("48kg_Cylinder");
		StockDTO stockDTO48kgCylinder = new StockDTO();
		stockDTO48kgCylinder.setProduct(productDTO48kgCylinder);
		stockDTO48kgCylinder.setQuantity(1000.0);

		stockDTOList.add(stockLPGas);
		stockDTOList.add(stockDTO1PStove);
		stockDTOList.add(stockDTO2PStove);
		stockDTOList.add(stockDTO9kgCylinder);
		stockDTOList.add(stockDTO14kgCylinder);
		stockDTOList.add(stockDTO19kgCylinder);
		stockDTOList.add(stockDTO48kgCylinder);

		stocktakingDTO.setStockItems(stockDTOList);
		stocktakingDTO.setEntryDate(new Date(2018/12/11));
		stocktakingDTO.setUserId(userId);
		stocktakingDTO.setId("");
	}

	@Test
	public void testTakeStock() { // Pass
		ResponseEntity<?> responseEntity = controller.takeStock(stocktakingDTO, userId);

		Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	}

	@Test
	public void testTakeStockUsingNullDate() { // Pass
		stocktakingDTO.setEntryDate(null);

		ResponseEntity<?> responseEntity = controller.takeStock(stocktakingDTO, userId);

		Assert.assertEquals("Can't stocktake using a null date", HttpStatus.NOT_ACCEPTABLE, responseEntity.getStatusCode());
	}

	@Test
	public void testStocktakePreviousDay() {
		stocktakingDTO.setEntryDate(new Date(2018/12/05));

		ResponseEntity<?> responseEntity = controller.takeStock(stocktakingDTO, userId);

		Assert.assertEquals("You can't stocktake using previous day", HttpStatus.NOT_ACCEPTABLE, responseEntity.getStatusCode());
	}

	@Test
	public void testStocktakeInvalidUser() { // Pass
		ResponseEntity<?> responseEntity = controller.takeStock(stocktakingDTO, "0384fa99-cf46-453f-8f00-39bb15808ab88");

		Assert.assertEquals("The user is invalid and can't stocktake", HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
	}

	@Test
	public void testStocktakeInvalidProduct() { // Pass
		List<StockDTO> stockList = new ArrayList<>();
		ProductDTO productDTO = new ProductDTO();
		productDTO.setId("A burger");

		stockDTO.setProduct(productDTO);
		stockList.add(stockDTO);
		stocktakingDTO.setStockItems(stockList);

		ResponseEntity<?> responseEntity = controller.takeStock(stocktakingDTO, userId);

		Assert.assertEquals("Invalid product", HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
	}

	@Test
	public void testStocktakeUserNotRetailManager() { // Pass
		ResponseEntity<?> responseEntity = controller.takeStock(stocktakingDTO, userId2);

		Assert.assertEquals("User not retail manager", HttpStatus.UNAUTHORIZED, responseEntity.getStatusCode());
	}

	@Test
	public void testStocktakeStockNotFound() { // Pass
		stocktakingDTO = null;

		ResponseEntity<?> responseEntity = controller.takeStock(stocktakingDTO, userId);

		Assert.assertEquals("Stock not found", HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
	}

	@Test
	public void testStocktakeUnbalancing() { // Pass
		ResponseEntity<?> responseEntity = controller.takeStock(stocktakingDTO, userId3);

		Assert.assertEquals("User is unbalanced", HttpStatus.NOT_ACCEPTABLE, responseEntity.getStatusCode());
	}

	@Test
	public void testStocktakeLockedUser() { // Pass
		ResponseEntity<?> responseEntity = controller.takeStock(stocktakingDTO, userId4);

		Assert.assertEquals("User is locked", HttpStatus.NOT_ACCEPTABLE, responseEntity.getStatusCode());
	}

	@After
	public void cleanup(){
		userDao.deleteAll();
		retailerDao.deleteAll();
		productDao.deleteAll();
		stockDao.deleteAll();
		userStatusDao.deleteAll();
	}
}