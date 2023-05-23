package za.co.gaserve.entities;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import za.co.gaserve.dao.Dao;
import za.co.gaserve.dao.DaoConfig;
import za.co.gaserve.dao.LiveDaoConfig;
import za.co.gaserve.entities.lists.UserStatus;
import za.co.gaserve.entities.user.User;

import static za.co.gaserve.entities.Bootstrapper.bootstrapUserStatus;
import static za.co.gaserve.entities.Bootstrapper.prepare;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= LiveDaoConfig.class)
@PropertySource("classpath:dynamo.properties")
public class UserBootstrapper {

    @Autowired
    private Dao<User> userDao;
    @Autowired
    private Dao<UserStatus> userStatusDao;

    @Before
    public void setup() throws Exception {


    }

    @Test
    public void bootstrapAll() {
        bootstrapUser(userDao, userStatusDao);
    }

    public static void bootstrapUser(Dao<User> userDao,Dao<UserStatus> userStatusDao){
//        userDao.create(new User("sandilephensulo@gmail.com",userStatusDao.findById("ACTIVE")));
//        userDao.create(new User("kpiroddi@gmail.com",userStatusDao.findById("ACTIVE")));
//        userDao.create(new User("mikaliika@gmail.com",userStatusDao.findById("ACTIVE")));
//        userDao.create(new User("Wvmatsa@gmail.com",userStatusDao.findById("ACTIVE")));
//        userDao.create(new User("glen.ngwako@gmail.com", userStatusDao.findById("ACTIVE")));
//        userDao.create(new User("elitewildfire@gmail.com",userStatusDao.findById("ACTIVE")));
    }


    @Test
    public void updateUser(){

        User user = userDao.findByField("email","glen.ngwako@gmail.com").get(0);

        user.setStatus(userStatusDao.findById("ACTIVE"));
        userDao.update(user);

    }

}
