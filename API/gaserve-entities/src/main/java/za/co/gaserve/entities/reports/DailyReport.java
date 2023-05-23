package za.co.gaserve.entities.reports;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperFieldModel.DynamoDBAttributeType;

import za.co.gaserve.entities.Entity;
import za.co.gaserve.entities.regions.Retailed;
import za.co.gaserve.entities.regions.Retailer;
import za.co.gaserve.entities.reports.ReportEntry.EntryType;
import za.co.gaserve.entities.sales.Sale;
import za.co.gaserve.entities.stock.Product;
import za.co.gaserve.entities.stock.Stocktaking;
import za.co.gaserve.entities.user.User;

/**
 * 
 * @author sellotseka
 *
 */
@DynamoDBTable(tableName = "DailyReport")
public class DailyReport extends Entity implements Retailed{

	@DynamoDBAttribute(attributeName = "businessDay")
	private Date businessDay;

	@DynamoDBTyped(DynamoDBAttributeType.M)
    private Stocktaking stocktaking;
	
	@DynamoDBTyped(DynamoDBAttributeType.M)
    private Retailer retailer;
    
	@DynamoDBTyped(DynamoDBAttributeType.M)
    private User user;
    
	@DynamoDBTyped(DynamoDBAttributeType.L)
    private List<ReportEntry> reportEntries;

	public DailyReport(){}

	public DailyReport(List<Product> products,List<Sale> sales,User user,Retailer retailer, Date day){
		this.user = user;
		this.retailer = retailer;

		for(Product product:products){
			double quantity = 0;
			double totalAmount = 0;

			for(Sale sale:sales){
				quantity += sale.getProductTotalQuantity(product);
				totalAmount += sale.getProductTotalAmount(product);
			}
			addReportEntry(new ReportEntry(product,quantity,totalAmount,EntryType.SOLD));
		}

		setBusinessDay(day);
	}
	
	public Date getBusinessDay() {
		return businessDay;
	}

	public void setBusinessDay(Date day) {
		this.businessDay = day;
	}

	public Stocktaking getStocktaking() {
		return stocktaking;
	}

	public void setStocktaking(Stocktaking stocktaking) {
		this.stocktaking = stocktaking;
	}

	public Retailer getRetailer() {
		return retailer;
	}

	public void setRetailer(Retailer retailer) {
		this.retailer = retailer;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<ReportEntry> getReportEntries() {
		return reportEntries;
	}

	public ReportEntry getReportEntry(Product product) {
		if(reportEntries != null){
			for(ReportEntry entry:reportEntries){
				if(entry.getProduct().equals(product)){
					return entry;
				}
			}
		}
		return null;
	}

	public void setReportEntries(List<ReportEntry> reportEntries) {
		this.reportEntries = reportEntries;
	}

	public void addReportEntry(ReportEntry reportEntry) {
		if(null == reportEntries){
			reportEntries = new ArrayList<ReportEntry>();
		}
		reportEntries.add(reportEntry);
	}

	@DynamoDBHashKey(attributeName="id")
    public String getId()
    {
       return id;
    }
  
    @Override
    public Boolean getActive() {
    	return active;
    }
    
    @Override
    public Date getCreatedDate() {
    	return createdDate;
    }
    
    @Override
    public Date getLastUpdate() {
    	return lastUpdate;
    }

}
