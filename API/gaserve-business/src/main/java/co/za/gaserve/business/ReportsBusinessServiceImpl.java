package co.za.gaserve.business;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.time.LocalDate;


import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import za.co.gaserve.dao.Dao;
import za.co.gaserve.entities.BusinessException;
import za.co.gaserve.entities.MyDateUtil;
import za.co.gaserve.entities.regions.Retailer;
import za.co.gaserve.entities.regions.RetailerFilter;
import za.co.gaserve.entities.reports.DailyReport;
import za.co.gaserve.entities.reports.MonthlyReport;
import za.co.gaserve.entities.reports.ReportEntry;
import za.co.gaserve.entities.sales.Sale;
import za.co.gaserve.entities.sales.SalesDao;
import za.co.gaserve.entities.stock.Product;
import za.co.gaserve.entities.stock.Stocktaking;
import za.co.gaserve.entities.user.User;

public class ReportsBusinessServiceImpl implements ReportsBusinessService {

	@Autowired
	private Dao<Stocktaking> stockTakingDao;

	@Autowired
	private Dao<MonthlyReport> monthlyReportsDao;
	
	@Autowired
	private Dao<DailyReport> dailyReportsDao;
	
	@Autowired
	private Dao<User> userDao;

	@Autowired
	private Dao<Retailer> retailerDao;

	@Autowired
	private Dao<Product> productDao;

	@Autowired
	private SalesDao salesDao;

	@Autowired
    private ReportsBusinessService reportsBusinessService;
	
	@Override
	public void closeDay(List<Product> products,User user,Retailer retailer, Date day)throws BusinessException{

		Date today = new Date();

		if(day.compareTo(today) >= 0){
			throw new BusinessException("Future Date", HttpStatus.NOT_FOUND);
		}

		day = MyDateUtil.setEndOfDay(day);//Just make sure you're at the end of the day

		List<Sale> salesForDate = salesDao.getSalesForDay(retailer,day);//findall sales for the day by retailer

		//total up by product
		//set totals to report
		//save day
		dailyReportsDao.create(new DailyReport(products, salesForDate, user, retailer,day));


		//start going backwards
		Date dayBeforeDate = MyDateUtil.setEndOfDay(DateUtils.addDays(day, -1));

		String dayBefore = dayBeforeDate.toInstant().toString();

		dayBefore = dayBefore.replaceFirst("Z",".000Z");

		//This stops infinite recursion
		if(!dayBeforeDate.before(MyDateUtil.createDate(2018, 11, 1))){//TODO: Make me config. System is seeded on 1/10/2018

 			    List<DailyReport> dayBeforeReport = dailyReportsDao.findByField("businessDay", dayBefore);

 			    boolean reportForYesterday = false;

				for (DailyReport dailyReport: dayBeforeReport){
					if(dailyReport.getRetailer().getId().equals(retailer.getId())){
						reportForYesterday = true;
						break;
					}
				}

				if(dayBeforeReport == null || !reportForYesterday){
					closeDay(products,user,retailer, dayBeforeDate);
				}
			}


		Date dayAfter = DateUtils.addDays(day, 1);
		//If the day you just closed is the last day of that month close the month
		if(!MyDateUtil.isSameMonth(day, dayAfter)){
			closeMonth(products,user,retailer, day);
		}

	}

	@Override
	public void closeMonth(List<Product> products,User user,Retailer retailer, Date day){//Assume all days for month are closed

		day = MyDateUtil.setEndOfDay(day);//Just make sure you're at the end of the day

		List<Sale> salesForDate = salesDao.getSalesForMonth(retailer,day);//findall sales for the month by retailer

		//total up by product
		//set totals to report
		//save month
		//TODO: An assertion of the sum of closed days == this closed month

		MonthlyReport monthlyReport =new MonthlyReport(products, salesForDate, user, retailer);
		monthlyReport.setMonthEnd(day);
		monthlyReportsDao.create(monthlyReport);
	}
	
	@Override
	public List<DailyReport> getPastDays(List<Product> products,User user,Retailer retailer,int numberOfDaysFromNow) throws BusinessException{

		if(retailer == null) throw new BusinessException("Null Retailer", HttpStatus.NOT_FOUND);

		if(user == null) throw new BusinessException("Null User", HttpStatus.NOT_FOUND);

		Date today = MyDateUtil.setEndOfDay(new Date());
		Date from = DateUtils.addDays(today, (-1)*(numberOfDaysFromNow));//Time travel back
		SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        dateFormatter.setTimeZone(TimeZone.getTimeZone("UTC"));
        String fromStr = dateFormatter.format(from);
        String toStr = dateFormatter.format(today);

       Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
       eav.put(":from", new AttributeValue().withS(fromStr));
       eav.put(":to", new AttributeValue().withS(toStr));
       //eav.put(":retailer", new AttributeValue().withS(retailer.toString()));//TODO: Test this. I do not think it works

		Map<String,String> expressionAttributeName = new HashMap<>();
		expressionAttributeName.put("#businessDay","businessDay");

	   DynamoDBScanExpression queryExpression = new DynamoDBScanExpression()
			   .withFilterExpression("#businessDay BETWEEN :from AND :to")
			   .withExpressionAttributeNames(expressionAttributeName)
			   .withExpressionAttributeValues(eav);

	    List<DailyReport> closedDays = RetailerFilter.filterByRetailer(retailer, dailyReportsDao.findByScan(queryExpression));

		Date day = new Date();

	    List<Sale> salesForDate = salesDao.getSalesForDay(retailer,day);//findall sales for the day by retailer

		DailyReport runningDay = new DailyReport(products, salesForDate, user, retailer,today);

		closedDays.add(runningDay);
		return closedDays;
	}
	
	@Override
	public List<MonthlyReport> getPastMonths(List<Product> products,User user,Retailer retailer,int numberOfMonthsFromNow){

		long before3 = System.currentTimeMillis();

		Date today = MyDateUtil.setEndOfDay(new Date());
		Date from = DateUtils.addMonths(today, (-1)*(numberOfMonthsFromNow));//Time travel back
		
		
       SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
       dateFormatter.setTimeZone(TimeZone.getTimeZone("UTC"));
       String fromStr = dateFormatter.format(from);
       String toStr = dateFormatter.format(today);

       Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
       eav.put(":from", new AttributeValue().withS(fromStr));
       eav.put(":to", new AttributeValue().withS(toStr));

		Map<String,String> expressionAttributeName = new HashMap<>();
		expressionAttributeName.put("#monthEnd","monthEnd");

        DynamoDBScanExpression queryExpression = new DynamoDBScanExpression()
				.withFilterExpression("#monthEnd BETWEEN :from AND :to")
				.withExpressionAttributeNames(expressionAttributeName)
				.withExpressionAttributeValues(eav);

        List<MonthlyReport> closedMonths = RetailerFilter.filterByRetailer(retailer,monthlyReportsDao.findByScan(queryExpression));

        Date firstOfMonth = MyDateUtil.setStartOfMonth(today);
		Date day = new Date();

		List<Sale> salesForRunningMonth = new ArrayList<>();//findall sales for the day by retailer

		while (day.after(firstOfMonth)){

			salesForRunningMonth.addAll(RetailerFilter.filterByRetailer(retailer,salesDao.getSalesForDay(retailer,day)));
			day = DateUtils.addDays(day,-1);
		}


		MonthlyReport runningMonth = new MonthlyReport(products, salesForRunningMonth, user, retailer);
		runningMonth.setMonthEnd(today);


		closedMonths.add(runningMonth);
		return closedMonths;
	}

	@Override
	public DailyReport getDailyReport(Retailer retailer,Date day){
		return dailyReportsDao.findOneByField("businessDay", MyDateUtil.setEndOfDay(day));
	}
	
	@Override
	public MonthlyReport getMonthlyReport(Retailer retailer,Date day){
		return monthlyReportsDao.findOneByField("monthEnd", MyDateUtil.setEndOfDay(day));
	}
	
	@Override
	public List<Retailer> getLockedRetailers(){
		List<Stocktaking> unbalanced = stockTakingDao.findByField("balances", false);
		
		List<Retailer> retailers = new ArrayList<Retailer>();

		List<Retailer> lockedRetailers = new ArrayList<Retailer>();
		
		for(Stocktaking unbalance :unbalanced){
				retailers.add(unbalance.getRetailer());
		}

		for(Retailer retailer :retailers){
			retailer.setActive(false);
			lockedRetailers.add(retailer);
		}
		
		return lockedRetailers;
	}
	
	@Override
	public void closeAllDaysBeforeToday(User user, Retailer retailer) throws BusinessException{

		if(user == null) throw new BusinessException("Null User/Retailer",HttpStatus.NOT_FOUND);
		if(retailer == null) throw new BusinessException("Null User/Retailer",HttpStatus.NOT_FOUND);

		Date today = new Date();

		Date dayToClose = MyDateUtil.setEndOfDay(DateUtils.addDays(today, -1));

		String dayClose = dayToClose.toInstant().toString();

		dayClose = dayClose.replaceFirst("Z",".000Z");

		List<DailyReport> yesterdaysReport = dailyReportsDao.findByField("businessDay",dayClose);

		boolean reportForYesterday = false;

		for (DailyReport dailyReport: yesterdaysReport){
			if(dailyReport.getRetailer().getId().equals(retailer.getId())){
				reportForYesterday = true;
				break;
			}
		}


		if(yesterdaysReport == null || !reportForYesterday){
			List<Product> allProducts = productDao.findAll();
			closeDay(allProducts,user,retailer, dayToClose);
		}
	}




	
}
