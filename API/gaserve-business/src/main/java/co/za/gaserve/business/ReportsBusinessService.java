package co.za.gaserve.business;

import java.util.Date;
import java.util.List;

import za.co.gaserve.entities.regions.Retailer;
import za.co.gaserve.entities.reports.DailyReport;
import za.co.gaserve.entities.reports.MonthlyReport;
import za.co.gaserve.entities.stock.Product;
import za.co.gaserve.entities.user.User;

public interface ReportsBusinessService {

	DailyReport getDailyReport(Retailer retailer,Date day);
	MonthlyReport getMonthlyReport(Retailer retailer,Date day);
	List<Retailer> getLockedRetailers();

	void closeAllDaysBeforeToday(User user, Retailer retailer);

	void closeDay(List<Product> products, User user, Retailer retailer, Date day);

	void closeMonth(List<Product> products, User user, Retailer retailer, Date day);
	List<DailyReport> getPastDays(List<Product> products, User user, Retailer retailer, int numberOfDaysFromNow);
	List<MonthlyReport> getPastMonths(List<Product> products, User user, Retailer retailer, int numberOfMonthsFromNow);
	
}
