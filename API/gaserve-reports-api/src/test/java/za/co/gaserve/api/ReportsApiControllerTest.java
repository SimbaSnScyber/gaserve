package za.co.gaserve.api;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import co.za.gaserve.business.BusinessServiceConfig;
import io.swagger.api.ReportsApiFacade;
import io.swagger.model.SalesReportDTO;
import za.co.gaserve.dao.DaoConfig;
import za.co.gaserve.entities.reports.ReportEntry;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={DaoConfig.class,BusinessServiceConfig.class,ReportsApiFacade.class})
@ConfigurationProperties(prefix = "test")

public class ReportsApiControllerTest {

	@Autowired
	ReportsApiFacade controller;
		
	
	
	@Before
	public void setup(){
		//Bootstrapper.
		ResponseEntity<ReportEntry> reportEntryResponseEntity;
	}

	@Test
	public void testSearchReport(){//Pending
		ResponseEntity<?> searchReport = controller.searchReport("user1");
		org.junit.Assert.assertNotNull("Not null",searchReport);
		org.junit.Assert.assertTrue("Not null",searchReport.getBody() != null);
		
	}
	
	public void searchReportDailyOnly(){//Pending

	}
	public void testSearchReportMonthlyOnly(){//Pending

	}
	public void testSearchReportDailyAndMonthly(){//Pending

	}
	public void testSearchReportRunningDayOnly(){//Pending

	}

}
