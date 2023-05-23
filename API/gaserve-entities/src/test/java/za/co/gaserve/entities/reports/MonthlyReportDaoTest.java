package za.co.gaserve.entities.reports;

import java.awt.List;
import java.util.Date;
import java.util.UUID;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import za.co.gaserve.dao.Dao;
import za.co.gaserve.entities.BaseDaoTest;
import za.co.gaserve.entities.regions.Retailer;
import za.co.gaserve.entities.reports.MonthlyReport;
import za.co.gaserve.entities.stock.Stocktaking;
import za.co.gaserve.entities.user.User;


public class MonthlyReportDaoTest extends BaseDaoTest{
 
    @Autowired
    private Dao<MonthlyReport> dao;
    
    public Dao<MonthlyReport> getDao(){return dao;}

    @Test
    public void testDeactivate() {
    MonthlyReport MonthlyReport = new MonthlyReport();
		
    	String randomUUID = UUID.randomUUID().toString();
    	
	MonthlyReport.setId(randomUUID);
    MonthlyReport.setMonthEnd(new Date());
    MonthlyReport.setStocktaking(new Stocktaking());
    MonthlyReport.setRetailer(new Retailer());
    MonthlyReport.setUser(new User());
   // MonthlyReport.setReportEntries(new List());
    	
    	dao.create(MonthlyReport);
    	
    MonthlyReport MonthlyReport2 = dao.findById(randomUUID);
    	
    	Assert.assertEquals("Id mismatch",MonthlyReport.getId(), MonthlyReport2.getId());

    MonthlyReport2.deactivate();
    	
    	dao.update(MonthlyReport2);
    	
    MonthlyReport MonthlyReport3 = dao.findById(randomUUID);
    	
    	Assert.assertFalse(MonthlyReport3.getActive());
    
    }

    @Test
    public void testUpdate() {
    MonthlyReport MonthlyReport = new MonthlyReport();
	String randomUUID = UUID.randomUUID().toString();

    MonthlyReport.setId(randomUUID);
    MonthlyReport.setMonthEnd(new Date());
    MonthlyReport.setStocktaking(new Stocktaking());
    MonthlyReport.setRetailer(new Retailer());
    MonthlyReport.setUser(new User());
   // MonthlyReport.setReportEntries(new List());
    	
    	dao.create(MonthlyReport);
    	
    MonthlyReport MonthlyReport2 = dao.findById(randomUUID);
    	
    	Assert.assertEquals("Id mismatch",MonthlyReport.getId(), MonthlyReport2.getId());

    	  MonthlyReport2.setMonthEnd(new Date());
    	    MonthlyReport2.setStocktaking(new Stocktaking());
    	    MonthlyReport2.setRetailer(new Retailer());
    	    MonthlyReport2.setUser(new User());
    	   // MonthlyReport2.setReportEntries(new List());
    	
    	dao.update(MonthlyReport2);
    	
    MonthlyReport MonthlyReport3 = dao.findById(randomUUID);
    	
    	Assert.assertEquals(MonthlyReport3.getMonthEnd(), MonthlyReport2.getMonthEnd());
    }

    @Test
    public void testCreate() {
    MonthlyReport MonthlyReport = new MonthlyReport();
    	String randomUUID = UUID.randomUUID().toString();
	
    MonthlyReport.setId(randomUUID);
    MonthlyReport.setMonthEnd(new Date());
    MonthlyReport.setStocktaking(new Stocktaking());
    MonthlyReport.setRetailer(new Retailer());
    MonthlyReport.setUser(new User());
   // MonthlyReport.setReportEntries(new List());
    	
    	dao.create(MonthlyReport);
    	
    MonthlyReport MonthlyReport2 = dao.findById(randomUUID);
    	
    	Assert.assertEquals("Id mismatch",MonthlyReport.getId(), MonthlyReport2.getId());
  //  	Assert.assertEquals("Description mismatch",MonthlyReport.getDay(), MonthlyReport2.getDay());
    }
}

