package za.co.gaserve.entities.user;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import za.co.gaserve.dao.Dao;
import za.co.gaserve.entities.BaseDaoTest;
import za.co.gaserve.entities.BaseDaoTest;
import za.co.gaserve.entities.BaseDaoTest;

public class GroupDaoTest extends BaseDaoTest{
 
    @Autowired
    private Dao<Role> dao;
    
    public Dao<Role> getDao(){return dao;}

    @Test
    public void testDeactivate() {
    	Role role = new Role();
		
    	role.setId("RETRAIL_MANAGER");
    	role.setDescription("RETRAIL_MANAGER_DESC");
    	
    	dao.create(role);
    	
    	Role role2 = dao.findById("RETRAIL_MANAGER");
    	
    	Assert.assertEquals("Id mismatch",role.getId(), role2.getId());

    	role2.deactivate();
    	
    	dao.update(role2);
    	
    	Role role3 = dao.findById("RETRAIL_MANAGER");
    	
    	Assert.assertFalse(role3.getActive());
    	
    }

    @Test
    public void testUpdate() {
  	Role role = new Role();
		
    	role.setId("RETRAIL_MANAGER");
    	role.setDescription("RETRAIL_MANAGER_DESC");
    	
    	dao.create(role);
    	
    	Role role2 = dao.findById("RETRAIL_MANAGER");
    	
    	Assert.assertEquals("Id mismatch",role.getId(), role2.getId());

    	role2.setDescription("RETRAIL_MANAGER_DESC2");
    	
    	dao.update(role2);
    	
    	Role role3 = dao.findById("RETRAIL_MANAGER");
    	
    	Assert.assertEquals(role3.getDescription(),"RETRAIL_MANAGER_DESC2");	
    }

    @Test
    public void testCreate() {
    	Role role = new Role();
    		
    	role.setId("RETRAIL_MANAGER");
    	role.setDescription("RETRAIL_MANAGER_DESC");
    	
    	dao.create(role);
    	
    	Role role2 = dao.findById("RETRAIL_MANAGER");
    	
    	Assert.assertEquals("Id mismatch",role.getId(), role2.getId());
    }
}

