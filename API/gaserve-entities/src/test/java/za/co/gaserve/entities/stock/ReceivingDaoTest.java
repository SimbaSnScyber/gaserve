package za.co.gaserve.entities.stock;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import za.co.gaserve.dao.Dao;
import za.co.gaserve.entities.BaseDaoTest;
import za.co.gaserve.entities.regions.Retailer;
import za.co.gaserve.entities.user.User;

import java.util.ArrayList;
import java.util.List;

public class ReceivingDaoTest extends BaseDaoTest {

    @Autowired
    private Dao<Receiving>receivingDao;

    List<Receiving> test = new ArrayList<>();
    @Override
    public  Dao<Receiving> getDao() {
        return receivingDao;
    }

    @Test
    public void testTable()throws Exception{

        receivingDao.createTable();
        Receiving receiving = new Receiving();
        User user = new User();


        Retailer retailer = new Retailer();


      //  receiving.setQuantity(12);

        Product product = new Product();
     //   receiving.setProduct(product);

        test.add(receiving);
        receivingDao.create(receiving);
        //receivingDao.create(test);
    }
}
