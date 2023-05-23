package io.swagger.model;

import za.co.gaserve.entities.reports.ReportEntry.EntryType;

public class MockResponse {

    public SalesReportDTO populateMockResponse(){


    	return 
    		new SalesReportDTO().
	    	addDailyReport(//Daily reports
	    			new ReportDTO()        
				        .addEntry(new ReportEntryDTO("Gas",100.02,90.20,EntryType.SOLD))
				        .addEntry(new ReportEntryDTO("Cylinder",100.02,90.20,EntryType.SOLD))
				        .addEntry(new ReportEntryDTO("Stove",100.02,90.20,EntryType.SOLD))
			)
	    	.addDailyReport(
	    			new ReportDTO()        
				        .addEntry(new ReportEntryDTO("Gas",100.02,90.20,EntryType.SOLD))
				        .addEntry(new ReportEntryDTO("Cylinder",100.02,90.20,EntryType.SOLD))
				        .addEntry(new ReportEntryDTO("Stove",100.02,90.20,EntryType.SOLD))
			)
	    	.addDailyReport(
	    			new ReportDTO()        
				        .addEntry(new ReportEntryDTO("Gas",100.02,90.20,EntryType.SOLD))
				        .addEntry(new ReportEntryDTO("Cylinder",100.02,90.20,EntryType.SOLD))
				        .addEntry(new ReportEntryDTO("Stove",100.02,90.20,EntryType.SOLD))
			)
	    	.addMonthlyReport(//Monthly reports
	    			new ReportDTO()        
				        .addEntry(new ReportEntryDTO("Gas",100.02,90.20,EntryType.SOLD))
				        .addEntry(new ReportEntryDTO("Cylinder",100.02,90.20,EntryType.SOLD))
				        .addEntry(new ReportEntryDTO("Stove",100.02,90.20,EntryType.SOLD))
			)
	    	.addMonthlyReport(
	    			new ReportDTO()        
				        .addEntry(new ReportEntryDTO("Gas",100.02,90.20,EntryType.SOLD))
				        .addEntry(new ReportEntryDTO("Cylinder",100.02,90.20,EntryType.SOLD))
				        .addEntry(new ReportEntryDTO("Stove",100.02,90.20,EntryType.SOLD))
			)
	    	.addMonthlyReport(
	    			new ReportDTO()        
				        .addEntry(new ReportEntryDTO("Gas",100.02,90.20,EntryType.SOLD))
				        .addEntry(new ReportEntryDTO("Cylinder",100.02,90.20,EntryType.SOLD))
				        .addEntry(new ReportEntryDTO("Stove",100.02,90.20,EntryType.SOLD))
			)
	    	.addLockedRetailer(new LockedRetailerDTO("user1","retailer1"))
   	    	.addLockedRetailer(new LockedRetailerDTO("user2","retailer2"))
	    	.addLockedRetailer(new LockedRetailerDTO("user3","retailer3"));
    }

}
