package za.co.gaserve.api;

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
import za.co.gaserve.common.ErrorDTO;
import za.co.gaserve.common.LoginDTO;
import za.co.gaserve.common.UserDTO;
import za.co.gaserve.dao.Dao;
import za.co.gaserve.dao.TestDaoConfig;
import za.co.gaserve.entities.BusinessException;
import za.co.gaserve.entities.lists.UserStatus;
import za.co.gaserve.entities.user.Login;
import za.co.gaserve.entities.user.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={BusinessServiceConfig.class,LoginApiController.class, TestDaoConfig.class})
public class  LoginApiControllerTest {

	@Autowired
	LoginApiController controller;

	@Autowired
	private Dao<User> userDao;

	@Autowired
	private Dao<UserStatus> userStatusDao;

	@Autowired
	private Dao<Login> loginDao;


	@Before
	public void setup(){


		userStatusDao.create(new UserStatus("ACTIVE","ACTIVE"));
		userStatusDao.create(new UserStatus("DISABLED","DISABLED"));
		userStatusDao.create(new UserStatus("STOCKTAKE_UNBALANCED","STOCKTAKE_UNBALANCED"));
		userStatusDao.create(new UserStatus("STOCKTAKE_PENDING","STOCKTAKE_PENDING"));

		userDao.create(new User("testScenario1@gmail.com",userStatusDao.findById("ACTIVE")));
		userDao.create(new User("testScenario2@gmail.com",userStatusDao.findById("DISABLED")));
		userDao.create(new User("testScenario3@gmail.com",userStatusDao.findById("STOCKTAKE_UNBALANCED")));
		userDao.create(new User("testScenario4@gmail.com",userStatusDao.findById("STOCKTAKE_PENDING")));
		userDao.create(new User("testScenario5@gmail.com",null));

	}

	@After
	public void cleanup(){

		userStatusDao.deleteAll();
		userDao.deleteAll();
		loginDao.deleteAll();
	}

	@Test
	public void testLoginUserNotFound(){

		UserDTO userDTO = new UserDTO();

		userDTO.setEmail("noUser@gmail.com");
		userDTO.setActive(false);
		userDTO.setUserLocked(true);

		LoginDTO loginDTO = new LoginDTO();
		loginDTO.setEmail("noUser@gmail.com");


			ResponseEntity<ErrorDTO> actual = controller.authoriseUser(loginDTO);

			Assert.assertEquals("Testing error status code",HttpStatus.NOT_FOUND,actual.getStatusCode());
			Assert.assertEquals("Testing error message", "User not registered, Please contact your administrator",actual.getBody().getMessage());


	}

	@Test
	public void testLoginUserDisabled(){

		LoginDTO loginDTO = new LoginDTO();
		loginDTO.setEmail("testScenario2@gmail.com");

		ResponseEntity<ErrorDTO> actual = controller.authoriseUser(loginDTO);

		Assert.assertEquals("Assert status code is 404", HttpStatus.UNAUTHORIZED, actual.getStatusCode());
		Assert.assertEquals("Assert message says User is disabled", "User has been disabled, Please contact your administrator", actual.getBody().getMessage());
	}

	@Test
	public void testLoginUserStockTakeImbalanced(){

		LoginDTO loginDTO = new LoginDTO();
		loginDTO.setEmail("testScenario3@gmail.com");

		ResponseEntity<ErrorDTO> actual = controller.authoriseUser(loginDTO);

		Assert.assertEquals("Assert status is 409", HttpStatus.CONFLICT, actual.getStatusCode());
		Assert.assertEquals("Assert error messages", "Users stock is Unbalanced, Please contact your administrator", actual.getBody().getMessage());
	}

	@Test
	public void testLoginUserStockTakePending(){

		LoginDTO loginDTO = new LoginDTO();
		loginDTO.setEmail("testScenario4@gmail.com");

		ResponseEntity<ErrorDTO> actual = controller.authoriseUser(loginDTO);

		Assert.assertEquals("Assert status is 428", HttpStatus.PRECONDITION_REQUIRED, actual.getStatusCode());
		Assert.assertEquals("Assert error messages", "Requires stocktaking", actual.getBody().getMessage());

	}

	@Test
	public void nullUserStatus(){

		LoginDTO loginDTO = new LoginDTO();
		loginDTO.setEmail("testScenario5@gmail.com");

		ResponseEntity<ErrorDTO> actual = controller.authoriseUser(loginDTO);

		Assert.assertEquals("Assert status is 404", HttpStatus.NOT_FOUND, actual.getStatusCode());
		Assert.assertEquals("Assert error message", "User has null status",actual.getBody().getMessage());

	}

	/**
	 * The first time a active user login in for the day, their status must
	 * change to stocktaking_pending, to enforce a stocktake everyday
	 */
	@Test
	public void firstLoginChangesStatus(){

		LoginDTO loginDTO = new LoginDTO();
		loginDTO.setEmail("testScenario1@gmail.com");

		ResponseEntity<ErrorDTO> actual = controller.authoriseUser(loginDTO);

		Assert.assertEquals("Assert status is 428", HttpStatus.PRECONDITION_REQUIRED, actual.getStatusCode());
		Assert.assertEquals("Assert error messages", "Requires stocktaking", actual.getBody().getMessage());

	}

	/**
	 * If a active user has already logged in today there status should say active
	 */
	@Test
	public void succesfulLoginAfterBalancedStocktake(){

		User user = userDao.findOneByField("email","testScenario1@gmail.com");

		Login login = new Login();
		login.setUser(user);

		loginDao.create(login);

		LoginDTO loginDTO = new LoginDTO();
		loginDTO.setEmail("testScenario1@gmail.com");

		ResponseEntity<UserDTO> actual = controller.authoriseUser(loginDTO);

		Assert.assertEquals("Assert status is 200", HttpStatus.OK, actual.getStatusCode());
		Assert.assertEquals("Assert body returns user Id", user.getId(),actual.getBody().getId());
		Assert.assertEquals("Assert body returns active as true", true, actual.getBody().isActive());
		Assert.assertEquals("Assert body returns lockuser as false",false,actual.getBody().isUserLocked());
		Assert.assertEquals("Assert body returns stockTakingDone as true",true,actual.getBody().getStockTakingDone());
	}
}
