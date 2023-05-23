package za.co.gaserve.entities.lists;

import java.util.UUID;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import za.co.gaserve.dao.Dao;
import za.co.gaserve.entities.BaseDaoTest;


public class UserStatusDaoTest extends BaseDaoTest{
 
    @Autowired
    private Dao<UserStatus> dao;
    
    public Dao<UserStatus> getDao(){return dao;}

    @Test
    public void testDeactivate() {
    	UserStatus userStatus = new UserStatus();
		
    	String randomUUID = UUID.randomUUID().toString();
    	userStatus.setId(randomUUID);
    	userStatus.setDescription("USER_STATUS_DESC");
    	
    	dao.create(userStatus);
    	
    	UserStatus userStatus2 = dao.findById(randomUUID);
    	
    	Assert.assertEquals("Id mismatch",userStatus.getId(), userStatus2.getId());

    	userStatus2.deactivate();
    	
    	dao.update(userStatus2);
    	
    	UserStatus userStatus3 = dao.findById(randomUUID);
    	
    	Assert.assertFalse(userStatus3.getActive());
    	
    }

    @Test
    public void testUpdate() {
    	UserStatus userStatus = new UserStatus();
	String randomUUID = UUID.randomUUID().toString();

	userStatus.setId(randomUUID);
	userStatus.setDescription("USER_STATUS_DESC");
    	
    	dao.create(userStatus);
    	
    	UserStatus userStatus2 = dao.findById(randomUUID);
    	
    	Assert.assertEquals("Id mismatch",userStatus.getId(), userStatus2.getId());

    	userStatus2.setDescription("USER_STATUS_DESC2");
    	
    	dao.update(userStatus2);
    	
    	UserStatus product3 = dao.findById(randomUUID);
    	
    	Assert.assertEquals(product3.getDescription(),"USER_STATUS_DESC2");	
    }

    @Test
    public void testCreate() {
    	UserStatus product = new UserStatus();
    	String randomUUID = UUID.randomUUID().toString();
	
    	product.setId(randomUUID);
    	product.setDescription("USER_STATUS_DESC");
    	
    	dao.create(product);
    	
    	UserStatus userStatus2 = dao.findById(randomUUID);
    	
    	Assert.assertEquals("Id mismatch",product.getId(), userStatus2.getId());
    	Assert.assertEquals("Description mismatch",product.getDescription(), userStatus2.getDescription());
    }
}