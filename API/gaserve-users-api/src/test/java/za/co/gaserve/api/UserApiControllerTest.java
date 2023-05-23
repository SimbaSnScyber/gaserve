package za.co.gaserve.api;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import co.za.gaserve.business.BusinessServiceConfig;
import io.swagger.Swagger2SpringBoot;
import io.swagger.api.UsersApiController;
import za.co.gaserve.dao.DaoConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={DaoConfig.class,BusinessServiceConfig.class,UsersApiController.class})
@ConfigurationProperties(prefix = "test")

public class UserApiControllerTest {

	@Autowired
    UsersApiController controller;
		
	@Before
	public void setup(){
		//bootstrap
	}
	
	@Test
	public void testAddUser(){
		controller.addUser(null);

	}


	public void testGetUserById(){
		controller.getUserById("");

	}


	public void testGetUsers(){
		controller.getUsers();

	}




}
