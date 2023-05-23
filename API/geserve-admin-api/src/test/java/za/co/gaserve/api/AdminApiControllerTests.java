package za.co.gaserve.api;


import co.za.gaserve.business.AdminBusinessService;
import co.za.gaserve.business.BusinessServiceConfig;
import io.swagger.api.AdminApiController;
import io.swagger.model.RetailerDTO;
import io.swagger.model.UserDTO;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import za.co.gaserve.dao.Dao;
import za.co.gaserve.dao.TestDaoConfig;
import za.co.gaserve.entities.lists.UserStatus;
import za.co.gaserve.entities.regions.Region;
import za.co.gaserve.entities.regions.Retailer;
import za.co.gaserve.entities.user.User;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestDaoConfig.class, BusinessServiceConfig.class, AdminApiController.class})
@ConfigurationProperties(prefix = "test")

public class AdminApiControllerTests {

    @Autowired
    AdminApiController controller;

    @Autowired
    AdminBusinessService adminBusinessService;

    @Autowired
    Dao<User> userDao;
    @Autowired
    Dao<UserStatus> userStatusDao;

    @Autowired
    Dao<Region> regionDao;

    @Autowired
    Dao<Retailer> retailerDao;

    private UserDTO userDTO;
    private RetailerDTO retailerDTO;

    @Before
    public void setup(){
        //bootstrap

        //UserStatusBootstrap
        userStatusDao.create(new UserStatus("ACTIVE", "ACTIVE"));
        userStatusDao.create(new UserStatus("STOCKTAKE_PENDING", "STOCKTAKE_PENDING"));
        userStatusDao.create(new UserStatus("DISABLED", "DISABLED"));
        userStatusDao.create(new UserStatus("REGISTERED", "REGISTERED"));
        userStatusDao.create(new UserStatus("STOCKTAKE_UNBALANCED", "STOCKTAKE_UNBALANCED"));

        //Region Bootstrap
        regionDao.create(new Region("NORTHERN","NORTHERN"));
        regionDao.create(new Region("NORTHERN","SOUTHERN"));

        //userBootstrap
        userDao.create(new User("sandilephensulo@gmail.com",userStatusDao.findById("STOCKTAKE_PENDING")));
        userDao.create(new User("kpiroddi@gmail.com",userStatusDao.findById("ACTIVE")));
        userDao.create(new User("mikaliika@gmail.com",userStatusDao.findById("DISABLED")));
        userDao.create(new User("Wvmatsa@gmail.com",userStatusDao.findById("ACTIVE")));
        userDao.create(new User("glen.ngwako@gmail.com", userStatusDao.findById("REGISTERED")));
        userDao.create(new User("elitewildfire@gmail.com",userStatusDao.findById("STOCKTAKE_UNBALANCED")));
        userDao.create(new User("agmuofhe@gmail.com",userStatusDao.findById("ACTIVE")));

        //RetailerBootstrap(north region)
        retailerDao.create(new Retailer("DZ GLAUDINA", userDao.findOneByField("email", "gaservedev@gmail.com")));
        retailerDao.create(new Retailer("Whitehouse", userDao.findOneByField("email", "kpiroddi@gmail.com")));
        retailerDao.create(new Retailer("Pamuzinda", userDao.findOneByField("email", "lotharsessentials@gmail.com")));
        retailerDao.create(new Retailer("Solomiyo", userDao.findOneByField("email", "agmuofhe@gmail.com")));
        retailerDao.create(new Retailer("Timire Tencraft", userDao.findOneByField("email", "glen.ngwako@gmail.com")));

        List<Retailer> retailers = retailerDao.findAll();

        Region region = regionDao.findById("NORTHERN");
        region.setRetailers(retailers);
        regionDao.update(region);

        //RetailerBootstrap(South region)


//        List<Retailer> retailers2 = retailerDao.findAll();
//
//        Region region2 = regionDao.findById("SOUTHERN");
//        region2.setRetailers(retailers2);
//        regionDao.update(region2);
//        retailers2.clear();

    }
///Creating Users With Valid Status
    @Test
    public void newUserName(){

        userDTO=new UserDTO();
        userDTO.setUserEmail("agmuofhe1@gmail.com");
        userDTO.setUserStatus("ACTIVE");

        ResponseEntity<?> responseEntity=controller.createUser(userDTO);
        Assert.assertEquals(201,responseEntity.getStatusCodeValue());

    }

    @Test
    public void existingUserName(){
        userDTO=new UserDTO();
        userDTO.setUserEmail("agmuofhe@gmail.com");
        userDTO.setUserStatus("ACTIVE");

        ResponseEntity<?> responseEntity=controller.createUser(userDTO);
        Assert.assertEquals(412,responseEntity.getStatusCodeValue());

    }

    //Creating New User With Invalid Status
    @Test
    public void validUserNameInvalidStatus(){
        userDTO=new UserDTO();
        userDTO.setUserEmail("agmuofhe1@gmail.com");
        userDTO.setUserStatus("NON-ACTIVE");

        ResponseEntity<?> responseEntity=controller.createUser(userDTO);
        Assert.assertEquals(412,responseEntity.getStatusCodeValue());

    }

    //UPDATING INVALID USERNAME
    @Test
    public void invalidUserNameValidStatus(){
        userDTO=new UserDTO();
        userDTO.setUserEmail("agmuofhe1@gmail.com");
        userDTO.setUserStatus("ACTIVE");

        ResponseEntity<?> responseEntity=controller.updateUserStatus(userDTO);
        Assert.assertEquals(412,responseEntity.getStatusCodeValue());

    }
    @Test
    public void invalidUserNameInvalidStatus(){
        userDTO=new UserDTO();
        userDTO.setUserEmail("agmuofhe1@gmail.com");
        userDTO.setUserStatus("NON-ACTIVE");

        ResponseEntity<?> responseEntity=controller.updateUserStatus(userDTO);
        Assert.assertEquals(412,responseEntity.getStatusCodeValue());

    }

    //UPDATING VALID USERNAME
    @Test
    public void UserNameValidStatus(){
        userDTO=new UserDTO();
        userDTO.setUserEmail("agmuofhe@gmail.com");
        userDTO.setUserStatus("ACTIVE");

        ResponseEntity<?> responseEntity=controller.updateUserStatus(userDTO);
        Assert.assertEquals(201,responseEntity.getStatusCodeValue());

    }
    @Test
    public void UserNameInvalidStatus(){
        userDTO=new UserDTO();
        userDTO.setUserEmail("agmuofhe@gmail.com");
        userDTO.setUserStatus("NON-ACTIVE");

        ResponseEntity<?> responseEntity=controller.updateUserStatus(userDTO);
        Assert.assertEquals(412,responseEntity.getStatusCodeValue());


    }


    //CREATING NEW RETAILER WITH EXISTING/VALID MANAGER
    @Test
    public void newRetailerName(){
        retailerDTO=new RetailerDTO();
        retailerDTO.setRetailerName("Buddy Shop");
        retailerDTO.setUserEmail("agmuofhe@gmail.com");
        retailerDTO.setRegion("NORTHERN");

        ResponseEntity<?> responseEntity=controller.createRetailer(retailerDTO);
        Assert.assertEquals(200,responseEntity.getStatusCodeValue());

    }
    @Test
    public void existingRetailerName(){
        retailerDTO=new RetailerDTO();
        retailerDTO.setRetailerName("Timire Tencraft");
        retailerDTO.setUserEmail("glen.ngwako@gmail.com");
        retailerDTO.setRegion("SOUTHERN");

        ResponseEntity<?> responseEntity=controller.createRetailer(retailerDTO);
        Assert.assertEquals(412,responseEntity.getStatusCodeValue());

    }
    //CREATING NEW RETAILER WITH NON-EXISTING/INVALID MANAGER
    @Test
    public void newRetailerNameInvalidManager(){
        retailerDTO=new RetailerDTO();
        retailerDTO.setRetailerName("Buddy Shop");
        retailerDTO.setUserEmail("agmuofhe1@gmail.com");
        retailerDTO.setRegion("SOUTHERN");

        ResponseEntity<?> responseEntity=controller.createRetailer(retailerDTO);
        Assert.assertEquals(412,responseEntity.getStatusCodeValue());

    }
    @Test
    public void existingRetailerNameInvalidManager(){
        retailerDTO=new RetailerDTO();
        retailerDTO.setRetailerName("Timire Tencraft2");
        retailerDTO.setUserEmail("glen.ngwako2@gmail.com");
        retailerDTO.setRegion("SOUTHERN");

        ResponseEntity<?> responseEntity=controller.createRetailer(retailerDTO);
        Assert.assertEquals(200,responseEntity.getStatusCodeValue());

    }

    //Update retail Manager
    @Test
    public void newValidManager(){
        retailerDTO=new RetailerDTO();
        retailerDTO.setRetailerName("Timire Tencraft");
        retailerDTO.setUserEmail("agmuofhe@gmail.com");
        retailerDTO.setRegion("SOUTHERN");

        ResponseEntity<?> responseEntity=controller.createRetailer(retailerDTO);
        Assert.assertEquals(200,responseEntity.getStatusCodeValue());

    }
    @Test
    public void newInvalidManager(){
        retailerDTO=new RetailerDTO();
        retailerDTO.setRetailerName("Timire Tencraft");
        retailerDTO.setUserEmail("agmuofhe1@gmail.com");
        retailerDTO.setRegion("SOUTHERN");

        ResponseEntity<?> responseEntity=controller.createRetailer(retailerDTO);
        Assert.assertEquals(200,responseEntity.getStatusCodeValue());

    }


    @After
    public void cleanUp(){

        userStatusDao.deleteAll();
        userDao.deleteAll();
        regionDao.deleteAll();
        regionDao.deleteAll();

    }
}
