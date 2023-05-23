package za.co.gaserve.entities.user;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import za.co.gaserve.dao.Dao;
import za.co.gaserve.entities.*;
import za.co.gaserve.entities.BaseDaoTest;
import za.co.gaserve.entities.BaseDaoTest;
import za.co.gaserve.entities.lists.UserStatus;

import static za.co.gaserve.entities.Bootstrapper.prepare;


public class LoginDaoTest extends BaseDaoTest{
 
    @Autowired
    private Dao<Login> dao;
    
    public Dao<Login> getDao(){return dao;}

    @Autowired
	private  Dao<User> userDao;

    @Autowired
	private  Dao<UserStatus> userStatusDao;
    @Before
    public void setUp()throws Exception{

		prepare(userDao);
		prepare(userStatusDao);
		UserBootstrapper.bootstrapUser(userDao,userStatusDao);
		Bootstrapper.bootstrapUserStatus(userStatusDao);
	}

    @Test
    public void testDeactivate() {
    	Login login = new Login();


		User user = userDao.findAll().get(0);
		login.setUser(user);

		login=dao.create(login);
    	
    	Login login2 = dao.findById(login.getId());
    	
    	Assert.assertEquals("Id mismatch",login.getId(), login2.getId());

    	login2.deactivate();
    	
    	dao.update(login2);
    	
    	Login login3 = dao.findById(login.getId());
    	
    	Assert.assertFalse(login3.getActive());
    	
    }

    @Test
    public void testUpdate() {
		Login login = new Login();


		User user = userDao.findAll().get(0);
		login.setUser(user);

		login=dao.create(login);

		Login login2 = dao.findById(login.getId());

		Assert.assertEquals("Id mismatch",login.getId(), login2.getId());

		login2.setIp("RETRAIL_MANAGER_DESC2");
    	
    	dao.update(login2);
    	
    	Login login3 = dao.findById(login2.getId());
    	
    	Assert.assertEquals(login3.getIp(),"RETRAIL_MANAGER_DESC2");
    }

    @Test
    public void testCreate() {
		Login login = new Login();


		User user = userDao.findAll().get(0);
		login.setUser(user);

		login=dao.create(login);

		Login login2 = dao.findById(login.getId());

		Assert.assertEquals("Id mismatch",login.getId(), login2.getId());
    }
}

