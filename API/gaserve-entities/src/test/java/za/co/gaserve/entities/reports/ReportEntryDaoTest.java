package za.co.gaserve.entities.reports;

import java.util.UUID;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import za.co.gaserve.dao.Dao;
import za.co.gaserve.entities.BaseDaoTest;
import za.co.gaserve.entities.reports.ReportEntry.EntryType;


public class ReportEntryDaoTest extends BaseDaoTest{
	
	@Autowired
    private Dao<ReportEntry> dao;
    
    public Dao<ReportEntry> getDao(){return dao;}

    @Test
    public void testDeactivate() {
    	ReportEntry reportEntry = new ReportEntry();
		
    	String randomUUID = UUID.randomUUID().toString();
    	reportEntry.setId(randomUUID);
    	reportEntry.setQuantity(9);
    	reportEntry.setTotalAmount(100);
    	reportEntry.setType(EntryType.OPENED_WITH);
    	
    	
    	dao.create(reportEntry);
    	
    	ReportEntry ReportEntry2 = dao.findById(randomUUID);
    	
    	Assert.assertEquals("Id mismatch",reportEntry.getId(), ReportEntry2.getId());

    	ReportEntry2.deactivate();
    	
    	dao.update(ReportEntry2);
    	
    	ReportEntry ReportEntry3 = dao.findById(randomUUID);
    	
    	Assert.assertFalse(ReportEntry3.getActive());
    	
    }

    @Test
    public void testUpdate() {
    	ReportEntry ReportEntry = new ReportEntry();
	String randomUUID = UUID.randomUUID().toString();

	ReportEntry.setId(randomUUID);
	ReportEntry.setQuantity(9);
	ReportEntry.setTotalAmount(100);
    	
    	dao.create(ReportEntry);
    	
    	ReportEntry ReportEntry2 = dao.findById(randomUUID);
    	
    	Assert.assertEquals("Id mismatch",ReportEntry.getId(), ReportEntry2.getId());

    	ReportEntry2.setQuantity(9);
    	
    	dao.update(ReportEntry2);
    	
    	ReportEntry reportEntry3 = dao.findById(randomUUID);
    	
    Assert.assertTrue(reportEntry3.getQuantity() == ReportEntry2.getQuantity());
    }

    @Test
    public void testCreate() {
    	ReportEntry reportEntry = new ReportEntry();
    	String randomUUID = UUID.randomUUID().toString();
	
    	reportEntry.setId(randomUUID);
    	reportEntry.setQuantity(9);
    	
    	dao.create(reportEntry);
    	
    	ReportEntry ReportEntry2 = dao.findById(randomUUID);
    	
    	Assert.assertEquals("Id mismatch",reportEntry.getId(), ReportEntry2.getId());
  	    Assert.assertTrue("Description mismatch",reportEntry.getQuantity() == ReportEntry2.getQuantity());
    }

}
