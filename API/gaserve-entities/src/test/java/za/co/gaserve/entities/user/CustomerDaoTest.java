package za.co.gaserve.entities.user;

import java.util.UUID;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import za.co.gaserve.dao.Dao;
import za.co.gaserve.entities.BaseDaoTest;


public class CustomerDaoTest extends BaseDaoTest{
 
    @Autowired
    private Dao<Customer> dao;
    
    public Dao<Customer> getDao(){return dao;}

    @Test
    public void testDeactivate() {
    	Customer customer = new Customer();
		
    	String randomUUID = UUID.randomUUID().toString();
		customer.setId(randomUUID);
    	customer.setName("RETRAIL_MANAGER_DESC");
    	
    	dao.create(customer);
    	
    	Customer customer2 = dao.findById(randomUUID);
    	
    	Assert.assertEquals("Id mismatch",customer.getId(), customer2.getId());

    	customer2.deactivate();
    	
    	dao.update(customer2);
    	
    	Customer customer3 = dao.findById(randomUUID);
    	
    	Assert.assertFalse(customer3.getActive());
    	
    }

    @Test
    public void testUpdate() {
  	Customer customer = new Customer();
	String randomUUID = UUID.randomUUID().toString();

    	customer.setId(randomUUID);
    	customer.setName("RETRAIL_MANAGER_DESC");
    	
    	dao.create(customer);
    	
    	Customer customer2 = dao.findById(randomUUID);
    	
    	Assert.assertEquals("Id mismatch",customer.getId(), customer2.getId());

    	customer2.setName("RETRAIL_MANAGER_DESC2");
    	
    	dao.update(customer2);
    	
    	Customer customer3 = dao.findById(randomUUID);
    	
    	Assert.assertEquals(customer3.getName(),"RETRAIL_MANAGER_DESC2");	
    }

    @Test
    public void testCreate() {
    	Customer customer = new Customer();
    	String randomUUID = UUID.randomUUID().toString();
	
    	customer.setId(randomUUID);
    	customer.setName("RETRAIL_MANAGER_DESC");
    	
    	dao.create(customer);
    	
    	Customer customer2 = dao.findById(randomUUID);
    	
    	Assert.assertEquals("Id mismatch",customer.getId(), customer2.getId());
    }
}

