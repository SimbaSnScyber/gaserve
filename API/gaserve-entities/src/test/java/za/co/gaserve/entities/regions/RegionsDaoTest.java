package za.co.gaserve.entities.regions;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import za.co.gaserve.dao.Dao;
import za.co.gaserve.entities.BaseDaoTest;


public class RegionsDaoTest extends BaseDaoTest{
 
    @Autowired
    private Dao<Region> dao;
    
    public Dao<Region> getDao(){return dao;}

    @Test
    public void testDeactivate() {

    	Region region = new Region();
		List<Retailer> retailer = new ArrayList<Retailer>();

    	String randomUUID = UUID.randomUUID().toString();
		region.setId(randomUUID);
    	region.setName("REGION_DESC");
    	region.setRetailers(retailer);
    	
    	dao.create(region);
    	
    	Region product2 = dao.findById(randomUUID);

    	Assert.assertEquals("Id mismatch",region.getId(), product2.getId());

    	product2.deactivate();
    	
    	dao.update(product2);
    	
    	Region product3 = dao.findById(randomUUID);
    	
    	Assert.assertFalse(product3.getActive());
    	
    }

    @Test
    public void testUpdate() {
  	Region region = new Region();
	String randomUUID = UUID.randomUUID().toString();
		region.setId(randomUUID);
    	region.setName("REGION_DESC");

    	dao.create(region);
    	
    	Region region1 = dao.findById(randomUUID);
    	
    	Assert.assertEquals("Id mismatch",region.getId(), region1.getId());

    	region1.setName("REGION_DESC");
    	
    	dao.update(region1);
    	
    	Region product3 = dao.findById(randomUUID);
    	
    	Assert.assertEquals(product3.getName(),"REGION_DESC");
    }

    @Test
    public void testCreate() {
    	Region region = new Region();
    	String randomUUID = UUID.randomUUID().toString();
	
    	region.setId(randomUUID);
    	region.setName("REGION_DESC");

    	dao.create(region);
    	
    	Region region1 = dao.findById(randomUUID);
    	
    	Assert.assertEquals("Id mismatch",region.getId(), region1.getId());
    	Assert.assertEquals("Description mismatch",region.getName(), region1.getName());
    }
}

