package za.co.gaserve.entities.sales;

import java.util.Date;
import java.util.List;

import za.co.gaserve.dao.Dao;
import za.co.gaserve.entities.regions.Retailer;

public interface SalesDao extends Dao<Sale>{

	public List<Sale> getSalesForDay(Retailer retailer,Date date);
	public List<Sale> getSalesForMonth(Retailer retailer,Date date);
	public List<Sale> getSalesByDateRange(Retailer retailer, Date from, Date to);
	
}
