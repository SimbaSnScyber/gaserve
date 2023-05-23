package za.co.gaserve.entities.stock;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import za.co.gaserve.dao.Dao;
import za.co.gaserve.entities.BaseDaoTest;


public class StockDaoTest extends BaseDaoTest{
	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
    @Autowired
    private Dao<Stock> dao;

    @Autowired
    private Dao<Product> productDao;

    public Dao<Stock> getDao(){return dao;}
    
    @Before
    public void setup() throws Exception {
    	productDao.createTable();
    	productDao.deleteAll();
    }

    @Test
    public void testDeactivate() {
    	Product product = new Product();
		
    	String randomUUID = UUID.randomUUID().toString();
		product.setId(randomUUID);
    	product.setDescription("RETRAIL_MANAGER_DESC");
    	
    	productDao.create(product);
    	
    	product = productDao.findById(randomUUID);

    	Stock stock = new Stock();
    	Date date = new Date();
    	String randomUUID2 = UUID.randomUUID().toString();
		stock.setId(randomUUID2);
    	stock.setProduct(product);
    	stock.setQuantity(1);
    	stock.setActive(true);
    	stock.setCreatedDate( date);
    	stock.setLastUpdate( date);
    	
    	
    	dao.create(stock);
    	
    	Stock stock2 = dao.findById(randomUUID2);
    	
    	Assert.assertEquals("Id mismatch",stock.getId(), stock2.getId());
    	Assert.assertEquals("product mismatch",stock.getProduct(), stock2.getProduct());
    	Assert.assertEquals("quantity mismatch",stock.getQuantity(), stock2.getQuantity());
    	Assert.assertEquals("Active mismatch",stock.getActive(), stock2.getActive());
    	Assert.assertEquals("Create date mismatch",stock.getCreatedDate(), stock2.getCreatedDate());
    	Assert.assertEquals("Last Uodate mismatch",stock.getLastUpdate(), stock2.getLastUpdate());

    	stock2.deactivate();
    	
    	dao.update(stock2);
    	
    	Stock stock3 = dao.findById(randomUUID2);
    	
    	Assert.assertFalse(stock3.getActive());
    	
    }
    

    @Test
    public void testUpdate() {
    	Product product = new Product();

    	String randomUUID = UUID.randomUUID().toString();
		product.setId(randomUUID);
    	product.setDescription("RETRAIL_MANAGER_DESC");
    	
    	product = productDao.create(product);
	
    Stock stock = new Stock();
	String randomUUID2 = UUID.randomUUID().toString();
	
	Date date = new Date();
	stock.setId(randomUUID2);
	stock.setProduct(product);
	stock.setQuantity(1);
	stock.setActive(true);
	stock.setCreatedDate( date);
	stock.setLastUpdate( date);
	
    	dao.create(stock);
    	
    	Stock stock2 = dao.findById(randomUUID2);
    	Assert.assertEquals("Id mismatch",stock.getId(), stock2.getId());
    	Assert.assertEquals("product mismatch",stock.getProduct(), stock2.getProduct());
    	Assert.assertEquals("quantity mismatch",stock.getQuantity(), stock2.getQuantity());
    	Assert.assertEquals("Active mismatch",stock.getActive(), stock2.getActive());
    	Assert.assertEquals("Create date mismatch",stock.getCreatedDate(), stock2.getCreatedDate());
    	Assert.assertEquals("Last Uodate mismatch",stock.getLastUpdate(), stock2.getLastUpdate());

    	stock2.setProduct(product);
    	
    	dao.update(stock2);
    	
    	Stock stock3 = dao.findById(randomUUID2);
    	
    	Assert.assertEquals(stock.getProduct(),stock3.getId());
    	Assert.assertEquals(stock.getProduct(),stock3.getProduct());
    	Assert.assertEquals(stock.getProduct(),stock3.getQuantity());
    	Assert.assertEquals(stock.getProduct(),stock3.getCreatedDate());
    	Assert.assertEquals(stock.getProduct(),stock3.getLastUpdate());
    }

    @Test
    public void testCreate() {

   	Product product = new Product();
		
    	String randomUUID = UUID.randomUUID().toString();
		product.setId(randomUUID);
    	product.setDescription("RETRAIL_MANAGER_DESC");
    	
    	product = productDao.create(product);

    	
    	Stock stock = new Stock();
    	
    	Date date = new Date();
    	String randomUUID2 = UUID.randomUUID().toString();
		stock.setId(randomUUID2);
    	stock.setProduct(product);
    	stock.setQuantity(1);
    	stock.setActive(true);
    	stock.setCreatedDate( date);
    	stock.setLastUpdate( date);
    	dao.create(stock);
    	
    	Stock stock2 = dao.findById(randomUUID2);
    	
    	Assert.assertEquals("Id mismatch",stock.getId(), stock2.getId());
    	Assert.assertEquals("product mismatch",stock.getProduct(), stock2.getProduct());
    	Assert.assertEquals("quantity mismatch",stock.getQuantity(), stock2.getQuantity());
    	Assert.assertEquals("Active mismatch",stock.getActive(), stock2.getActive());
    	Assert.assertEquals("Create date mismatch",stock.getCreatedDate(), stock2.getCreatedDate());
    	Assert.assertEquals("Last Uodate mismatch",stock.getLastUpdate(), stock2.getLastUpdate());
 }
    
}

