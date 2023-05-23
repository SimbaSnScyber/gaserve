package za.co.gaserve.entities;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import za.co.gaserve.dao.Dao;
import za.co.gaserve.dao.DaoConfig;
import za.co.gaserve.dao.LiveDaoConfig;
import za.co.gaserve.dao.TestDaoConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= TestDaoConfig.class)
@ConfigurationProperties(prefix = "test")

@TestPropertySource(properties = {
		   "amazon.aws.dynamodb.endpoint=https://dynamodb.${amazon.aws.region}.amazonaws.com"//http://localhost:8000/
		  ,"amazon.aws.accesskey=AKIAI74EM4LVGYKGXTHA" //
		  ,"amazon.aws.secretkey=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"//
		  ,"amazon.aws.region=us-west-2"
		  })
public abstract class BaseDaoTest {
   
  
    @Before
    public void setup() throws Exception {
     		getDao().createTable();
    		getDao().deleteAll();
    }
    
    public abstract <T> Dao<T> getDao();

 
}

