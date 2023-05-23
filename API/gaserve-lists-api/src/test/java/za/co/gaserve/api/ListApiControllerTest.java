package za.co.gaserve.api;

import co.za.gaserve.business.ListBusinessService;
import io.swagger.model.CodeDescriptionDTO;
import io.swagger.model.StaticListsDTO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import co.za.gaserve.business.BusinessServiceConfig;
import io.swagger.api.ListsApiController;
import za.co.gaserve.dao.DaoConfig;
import za.co.gaserve.dao.TestDaoConfig;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={TestDaoConfig.class,BusinessServiceConfig.class})
public class
ListApiControllerTest {

	ListsApiController controller = new ListsApiController();

	@Autowired
	ListBusinessService listBusinessService;

		
	@Before
	public void setup(){
		controller.setListBusinessService(listBusinessService);

	}

	@Test
	public void testGetLists(){

		ResponseEntity list = controller.getLists();
		Assert.assertEquals(200, list.getStatusCodeValue());
	}

	@Test
	public void testGetListByInvalidId(){
		//setup scenario
		String id = "BANANAS";
		ResponseEntity<List<CodeDescriptionDTO>> list = controller.getList(id);

		Assert.assertNotNull(list);
		Assert.assertNull(list.getBody());
		//Assert.assertTrue(!list.getBody().isEmpty());
		Assert.assertEquals("Asserting",404,list.getStatusCodeValue());

	}

	@Test
	public void testGetListByValidId(){
		//setup scenario

		String id = "PAYMENT_METHODS";//provide valid ID
		ResponseEntity<List<CodeDescriptionDTO>> list = controller.getList(id);

		Assert.assertNotNull(list);
		Assert.assertNotNull(list.getBody());
		Assert.assertTrue(!list.getBody().isEmpty());//return list with valid provided id
	}

	/*@Test
	public void testGetListsByValidPaymentMethod(){

	}

	@Test
	public void testGetListsByInValidPaymentMethod(){

	}

	@Test
	public void testGetListsByValidUserStatus(){

	}

	@Test
	public void testGetListsByInValidUserStatus(){

	}

	@Test
	public void testGetListsByValidProduct(){

	}

	@Test
	public void testGetListsByInValidProduct(){

	}*/






}
