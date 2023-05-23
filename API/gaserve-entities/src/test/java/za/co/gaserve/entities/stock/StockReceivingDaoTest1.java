package za.co.gaserve.entities.stock;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import za.co.gaserve.dao.Dao;
import za.co.gaserve.entities.BaseDaoTest;
import za.co.gaserve.entities.MyDateUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;


public class StockReceivingDaoTest1 extends BaseDaoTest{
	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
    @Autowired
    private Dao<Stock> dao;
    
    public Dao<Stock> getDao(){return dao;}
    
    @Test
    public void testDeactivate() {
    	Stock stock = new Stock();
    	Product product = new Product();
    	Date date = new Date();
		Date strDate = MyDateUtil.createDate(2017 , 1, 1);
    	String randomUUID = UUID.randomUUID().toString();
		stock.setId(randomUUID);
    	stock.setProduct(product);
    	stock.setQuantity(1);
    	stock.setActive(true);
    	stock.setCreatedDate(strDate);
    	stock.setLastUpdate(strDate);
    	
    	
    	dao.create(stock);
    	
    	Stock stock2 = dao.findById(randomUUID);
    	
    	Assert.assertEquals("Id mismatch",stock.getId(), stock2.getId());
    	//Assert.assertEquals("product mismatch",stock.getProduct(), stock2.getProduct());
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
	//Object strDate = format.format(date);
	stock.setId(randomUUID);
	stock.setProduct(product);
	stock.setQuantity(1);
	stock.setActive(true);
	stock.setCreatedDate(MyDateUtil.createDate(2018,1,1));
	stock.setLastUpdate(MyDateUtil.createDate(2018,1,1));
	
    	dao.create(stock);
    	
    	Stock stock2 = dao.findById(randomUUID);
    	Assert.assertEquals("Id mismatch",stock.getId(), stock2.getId());
    	//Assert.assertEquals("product mismatch",stock.getProduct(), stock2.getProduct());
    	Assert.assertEquals("quantity mismatch",stock.getQuantity(), stock2.getQuantity());
    	Assert.assertEquals("Active mismatch",stock.getActive(), stock2.getActive());
    	Assert.assertEquals("Create date mismatch",stock.getCreatedDate(), stock2.getCreatedDate());
    	Assert.assertEquals("Last Uodate mismatch",stock.getLastUpdate(), stock2.getLastUpdate());

    	stock2.setProduct(product);
    	
    	dao.update(stock2);
    	
    	Stock stock3 = dao.findById(randomUUID);
    	
    	Assert.assertEquals(stock.getId(),stock3.getId());
    	//Assert.assertEquals(stock.getProduct(),stock3.getProduct());
    	Assert.assertEquals(stock.getQuantity(),stock3.getQuantity());
    	Assert.assertEquals(stock.getCreatedDate(),stock3.getCreatedDate());
    	Assert.assertEquals(stock.getLastUpdate(),stock3.getLastUpdate());
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
    	stock.setCreatedDate(MyDateUtil.createDate(2018,1,1));
    	stock.setLastUpdate(MyDateUtil.createDate(2018,1,1));
    	dao.create(stock);
    	
    	Stock stock2 = dao.findById(randomUUID);
    	
    	Assert.assertEquals("Id mismatch",stock.getId(), stock2.getId());
    	//Assert.assertEquals("Product mismatch", stock.getProduct(), stock2.getProduct());
    	Assert.assertEquals("quantity mismatch",stock.getQuantity(), stock2.getQuantity());
    	Assert.assertEquals("Active mismatch",stock.getActive(), stock2.getActive());
    	Assert.assertEquals("Create date mismatch",stock.getCreatedDate(), stock2.getCreatedDate());
    	Assert.assertEquals("Last Uodate mismatch",stock.getLastUpdate(), stock2.getLastUpdate());
 }
    
}

