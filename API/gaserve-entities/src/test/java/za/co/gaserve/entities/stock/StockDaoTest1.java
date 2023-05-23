package za.co.gaserve.entities.stock;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import za.co.gaserve.dao.Dao;
import za.co.gaserve.entities.BaseDaoTest;


public class StockDaoTest1 extends BaseDaoTest{
	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
    @Autowired
    private Dao<Stock> dao;//Banana
    
    public Dao<Stock> getDao(){return dao;}
    
    @Test
    public void testDeactivate() {
    	Stock stock = new Stock();
    	Product product = new Product();
    	Date date = new Date();
    	Object strDate = format.format(date);
    	String randomUUID = UUID.randomUUID().toString();
		stock.setId(randomUUID);
    	stock.setProduct(product);
    	stock.setQuantity(1);
    	stock.setActive(true);
    	stock.setCreatedDate((Date) strDate);
    	stock.setLastUpdate((Date) strDate);
    	
    	
    	dao.create(stock);
    	
    	Stock stock2 = dao.findById(randomUUID);
    	
    	Assert.assertEquals("Id mismatch",stock.getId(), stock2.getId());
    	Assert.assertEquals("product mismatch",stock.getProduct(), stock2.getProduct());
    	Assert.assertEquals("quantity mismatch",stock.getQuantity(), stock2.getQuantity());
    	Assert.assertEquals("Active mismatch",stock.getActive(), stock2.getActive());
    	Assert.assertEquals("Create date mismatch",stock.getCreatedDate(), stock2.getCreatedDate());
    	Assert.assertEquals("Last Uodate mismatch",stock.getLastUpdate(), stock2.getLastUpdate());

    	stock2.deactivate();
    	
    	dao.update(stock2);
    	
    	Stock stock3 = dao.findById(randomUUID);
    	
    	Assert.assertFalse(stock3.getActive());
    	
    }
    

    @Test
    public void testUpdate() {
  	Stock stock = new Stock();
	String randomUUID = UUID.randomUUID().toString();
	Product product = new Product();
	
	Date date = new Date();
	Object strDate = format.format(date);
	stock.setId(randomUUID);
	stock.setProduct(product);
	stock.setQuantity(1);
	stock.setActive(true);
	stock.setCreatedDate((Date) strDate);
	stock.setLastUpdate((Date) strDate);
	
    	dao.create(stock);
    	
    	Stock stock2 = dao.findById(randomUUID);
    	Assert.assertEquals("Id mismatch",stock.getId(), stock2.getId());
    	Assert.assertEquals("product mismatch",stock.getProduct(), stock2.getProduct());
    	Assert.assertEquals("quantity mismatch",stock.getQuantity(), stock2.getQuantity());
    	Assert.assertEquals("Active mismatch",stock.getActive(), stock2.getActive());
    	Assert.assertEquals("Create date mismatch",stock.getCreatedDate(), stock2.getCreatedDate());
    	Assert.assertEquals("Last Uodate mismatch",stock.getLastUpdate(), stock2.getLastUpdate());

    	stock2.setProduct(product);
    	
    	dao.update(stock2);
    	
    	Stock stock3 = dao.findById(randomUUID);
    	
    	Assert.assertEquals(stock.getProduct(),stock3.getId());
    	Assert.assertEquals(stock.getProduct(),stock3.getProduct());
    	Assert.assertEquals(stock.getProduct(),stock3.getQuantity());
    	Assert.assertEquals(stock.getProduct(),stock3.getCreatedDate());
    	Assert.assertEquals(stock.getProduct(),stock3.getLastUpdate());
    }

    @Test
    public void testCreate() {
    	Stock stock = new Stock();
    	Product product = new Product();
    	
    	Date date = new Date();
    	Object strDate = format.format(date);
    	String randomUUID = UUID.randomUUID().toString();
		stock.setId(randomUUID);
    	stock.setProduct(product);
    	stock.setQuantity(1);
    	stock.setActive(true);
    	stock.setCreatedDate((Date) strDate);
    	stock.setLastUpdate((Date) strDate);
    	dao.create(stock);
    	
    	Stock stock2 = dao.findById(randomUUID);
    	
    	Assert.assertEquals("Id mismatch",stock.getId(), stock2.getId());
    	Assert.assertEquals("product mismatch",stock.getProduct(), stock2.getProduct());
    	Assert.assertEquals("quantity mismatch",stock.getQuantity(), stock2.getQuantity());
    	Assert.assertEquals("Active mismatch",stock.getActive(), stock2.getActive());
    	Assert.assertEquals("Create date mismatch",stock.getCreatedDate(), stock2.getCreatedDate());
    	Assert.assertEquals("Last Uodate mismatch",stock.getLastUpdate(), stock2.getLastUpdate());
 }
    
}

