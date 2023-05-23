package za.co.gaserve.entities.stock;

import java.util.UUID;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import za.co.gaserve.dao.Dao;
import za.co.gaserve.entities.BaseDaoTest;


public class ProductDaoTest1 extends BaseDaoTest{
 
    @Autowired
    private Dao<Product> dao;//banana
    
    public Dao<Product> getDao(){return dao;}
    
//banna
    @Test
    public void testDeactivate() {
    	Product product = new Product();
		
    	String randomUUID = UUID.randomUUID().toString();
		product.setId(randomUUID);
    	product.setDescription("RETRAIL_MANAGER_DESC");
    	
    	dao.create(product);
    	
    	Product product2 = dao.findById(randomUUID);
    	
    	Assert.assertEquals("Id mismatch",product.getId(), product2.getId());

    	product2.deactivate();
    	
    	dao.update(product2);
    	
    	Product product3 = dao.findById(randomUUID);
    	
    	Assert.assertFalse(product3.getActive());
    	
    }
    

    @Test
    public void testUpdate() {
  	Product product = new Product();
	String randomUUID = UUID.randomUUID().toString();

    	product.setId(randomUUID);
    	product.setDescription("RETRAIL_MANAGER_DESC");
    	
    	dao.create(product);
    	
    	Product product2 = dao.findById(randomUUID);
    	
    	Assert.assertEquals("Id mismatch",product.getId(), product2.getId());

    	product2.setDescription("RETRAIL_MANAGER_DESC2");
    	
    	dao.update(product2);
    	
    	Product product3 = dao.findById(randomUUID);
    	
    	Assert.assertEquals(product3.getDescription(),"RETRAIL_MANAGER_DESC2");	
    }

    @Test
    public void testCreate() {
    	Product product = new Product();
    	String randomUUID = UUID.randomUUID().toString();
	
    	product.setId(randomUUID);
    	product.setDescription("RETRAIL_MANAGER_DESC");
    	
    	dao.create(product);
    	
    	Product product2 = dao.findById(randomUUID);
    	
    	Assert.assertEquals("Id mismatch",product.getId(), product2.getId());
    	Assert.assertEquals("Description mismatch",product.getDescription(), product2.getDescription());
    }
    
}

