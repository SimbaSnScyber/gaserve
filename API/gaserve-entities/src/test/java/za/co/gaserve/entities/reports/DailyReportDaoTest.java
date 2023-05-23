package za.co.gaserve.entities.reports;

import java.util.Date;
import java.util.UUID;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import za.co.gaserve.dao.Dao;
import za.co.gaserve.entities.BaseDaoTest;
import za.co.gaserve.entities.MyDateUtil;


public class DailyReportDaoTest extends BaseDaoTest{

    @Autowired
    private Dao<DailyReport> dao;

    public Dao<DailyReport> getDao(){return dao;}

    @Test
    public void testDeactivate() {
    	DailyReport dailyReport = new DailyReport();

    	String randomUUID = UUID.randomUUID().toString();
		dailyReport.setId(randomUUID);
    	dailyReport.setBusinessDay(new Date(0));

    	dao.create(dailyReport);

    	DailyReport dailyReport2 = dao.findById(randomUUID);

    	Assert.assertEquals("Id mismatch",dailyReport.getId(), dailyReport2.getId());

    	dailyReport2.deactivate();

    	dao.update(dailyReport2);

    	DailyReport dailyReport3 = dao.findById(randomUUID);

    	Assert.assertFalse(dailyReport3.getActive());

    }

    @Test
    public void testUpdate() {
    	DailyReport dailyReport = new DailyReport();
	String randomUUID = UUID.randomUUID().toString();

    	dailyReport.setId(randomUUID);
    	dailyReport.setBusinessDay( MyDateUtil.createDate(2017,1,1) );

    	dao.create(dailyReport);

    	DailyReport dailyReport2 = dao.findById(randomUUID);

    	Assert.assertEquals("Id mismatch",dailyReport.getId(), dailyReport2.getId());

    	dailyReport2.setBusinessDay( MyDateUtil.createDate(2017,1,2) );

    	dao.update(dailyReport2);

    	DailyReport dailyReport3 = dao.findById(randomUUID);

    	Assert.assertEquals(dailyReport3.getBusinessDay(),dailyReport2.getBusinessDay());
    }

    @Test
    public void testCreate() {
    	DailyReport dailyReport = new DailyReport();
    	String randomUUID = UUID.randomUUID().toString();

    	dailyReport.setId(randomUUID);
    	dailyReport.setBusinessDay(new Date(0));

    	dao.create(dailyReport);

    	DailyReport dailyReport2 = dao.findById(randomUUID);

    	Assert.assertEquals("Id mismatch",dailyReport.getId(), dailyReport2.getId());
    	Assert.assertEquals("Description mismatch",dailyReport.getBusinessDay(), dailyReport2.getBusinessDay());
    }


}

