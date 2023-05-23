package za.co.gaserve.entities.stock;

import java.util.UUID;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import za.co.gaserve.dao.Dao;
import za.co.gaserve.entities.BaseDaoTest;

public class StocktakingDaoTest extends BaseDaoTest{
	
	@Autowired
    private Dao<Stocktaking> dao;
    
    public Dao<Stocktaking> getDao(){return dao;}

    @Test
    public void testDeactivate() {
    	Stocktaking Stocktaking = new Stocktaking();
		
    	String randomUUID = UUID.randomUUID().toString();
    	Stocktaking.setId(randomUUID);
    	Stocktaking.setBalance(false);
    	
    	dao.create(Stocktaking);
    	
    	Stocktaking Stocktaking2 = dao.findById(randomUUID);
    	
    	Assert.assertEquals("Id mismatch",Stocktaking.getId(), Stocktaking2.getId());

    	Stocktaking2.deactivate();
    	
    	dao.update(Stocktaking2);
    	
    	Stocktaking Stocktaking3 = dao.findById(randomUUID);
    	
    	Assert.assertFalse(Stocktaking3.getActive());
    	
    }

    @Test
    public void testUpdate() {
    	Stocktaking Stocktaking = new Stocktaking();
	String randomUUID = UUID.randomUUID().toString();

	Stocktaking.setId(randomUUID);
	Stocktaking.setBalance(false);
    	
    	dao.create(Stocktaking);
    	
    	Stocktaking Stocktaking2 = dao.findById(randomUUID);
    	
    	Assert.assertEquals("Id mismatch",Stocktaking.getId(), Stocktaking2.getId());

    	Stocktaking2.setBalance(false);
    	
    	dao.update(Stocktaking2);
    	
    	Stocktaking stocktaking3 = dao.findById(randomUUID);
    	
    	Assert.assertEquals(stocktaking3.getBalances(), false);	
    }

    @Test
    public void testCreate() {
    	Stocktaking stocktaking = new Stocktaking();
    	String randomUUID = UUID.randomUUID().toString();
	
    	stocktaking.setId(randomUUID);
    	stocktaking.setBalance(false);
    	
    	dao.create(stocktaking);
    	
    	Stocktaking Stocktaking2 = dao.findById(randomUUID);
    	
    	Assert.assertEquals("Id mismatch",stocktaking.getId(), Stocktaking2.getId());
    	Assert.assertEquals("Description mismatch",stocktaking.getBalances(), Stocktaking2.getBalances());
    }
}
