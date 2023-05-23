package za.co.gaserve.entities.regions;

import java.util.UUID;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import za.co.gaserve.dao.Dao;
import za.co.gaserve.entities.BaseDaoTest;
import za.co.gaserve.entities.Bootstrapper;
import za.co.gaserve.entities.UserBootstrapper;
import za.co.gaserve.entities.lists.UserStatus;
import za.co.gaserve.entities.user.User;


public class RetailerDaoTest extends BaseDaoTest{

    @Autowired
    private RetailerDao retialerDao;

    @Autowired
    private Dao<Retailer> dao;
    
    @Autowired
    private Dao<User> userDao;
  
    @Autowired
    private Dao<UserStatus> userStatusDao;
    
    public Dao<Retailer> getDao(){return dao;}

    @Test
    public void testDeactivate() {
    	Retailer retailer = new Retailer();
		
    	String randomUUID = UUID.randomUUID().toString();
		retailer.setId(randomUUID);
    	retailer.setName("RETRAIL_MANAGER_NAME");
    	
    	dao.create(retailer);
    	
    	Retailer retailer2 = dao.findById(randomUUID);
    	
    	Assert.assertEquals("Id mismatch",retailer.getId(), retailer2.getId());

    	retailer2.deactivate();
    	
    	dao.update(retailer2);
    	
    	Retailer retailer3 = dao.findById(randomUUID);
    	
    	Assert.assertFalse(retailer3.getActive());
    	
    }

    @Test
    public void testUpdate() {
  	Retailer retailer = new Retailer();
	String randomUUID = UUID.randomUUID().toString();

    	retailer.setId(randomUUID);
    	retailer.setName("RETRAIL_MANAGER_NAME");
    	
    	dao.create(retailer);
    	
    	Retailer retailer2 = dao.findById(randomUUID);
    	
    	Assert.assertEquals("Id mismatch",retailer.getId(), retailer2.getId());

    	retailer2.setName("RETRAIL_MANAGER_NAME2");
    	
    	dao.update(retailer2);
    	
    	Retailer retailer3 = dao.findById(randomUUID);
    	
    	Assert.assertEquals(retailer3.getName(),"RETRAIL_MANAGER_NAME2");	
    }

    @Test
    public void testCreate() {
    	Retailer retailer = new Retailer();
    	String randomUUID = UUID.randomUUID().toString();
	
    	retailer.setId(randomUUID);
    	retailer.setName("RETRAIL_MANAGER_NAME");
    	
    	dao.create(retailer);
    	
    	Retailer retailer2 = dao.findById(randomUUID);
    	
    	Assert.assertEquals("Id mismatch",retailer.getId(), retailer2.getId());
    	Assert.assertEquals("Manager Name mismatch",retailer.getName(), retailer2.getName());
    }
    

    @Test
    public void testFindByUser() {
    	
    	UserBootstrapper.bootstrapUser(userDao, userStatusDao);
    	
    	Retailer retailer = new Retailer();
    	String randomUUID = UUID.randomUUID().toString();
	
    	retailer.setId(randomUUID);
    	retailer.setName("RETRAIL_MANAGER_NAME");
    	User user = userDao.findOneByField("email", "gaservetest1@gmail.com");
		
    	Assert.assertNotNull(user);
    	
    	retailer.setManager(user);
    	
    	dao.create(retailer);

    	Assert.assertNotNull(retailer.getManager());

    	Retailer retailer2 = dao.findById(randomUUID);
    	
    	Assert.assertEquals("Id mismatch",retailer.getId(), retailer2.getId());
    	Assert.assertEquals("Retailer Name mismatch",retailer.getName(), retailer2.getName());
    	
    	retailer2 = retialerDao.findRetailerByManager(user);

    	Assert.assertNotNull(retailer2);

    	Assert.assertEquals("Id mismatch",retailer.getId(), retailer2.getId());
    	Assert.assertEquals("Retailer Name mismatch",retailer.getName(), retailer2.getName());

    }

}

