package za.co.gaserve.entities.sales;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperFieldModel.DynamoDBAttributeType;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTyped;

import za.co.gaserve.entities.Entity;
import za.co.gaserve.entities.lists.PaymentMethod;
import za.co.gaserve.entities.regions.Retailed;
import za.co.gaserve.entities.regions.Retailer;
import za.co.gaserve.entities.stock.Product;
import za.co.gaserve.entities.user.Customer;
import za.co.gaserve.entities.user.User;

/**
 * 
 * @author sellotseka
 *
 * The ids are enum style: ACTIVE, STOCKTAKING_BLOCKED, DISABLED
 */
@DynamoDBTable(tableName = "Sale")
public class  Sale extends Entity implements Retailed, Taggable {
	
	@DynamoDBTyped(DynamoDBAttributeType.M)
    private Customer customer;

	@DynamoDBTyped(DynamoDBAttributeType.M)
	private User user;

	@DynamoDBTyped(DynamoDBAttributeType.M)
	private Retailer retailer;

	@DynamoDBTyped(DynamoDBAttributeType.M)
    private PaymentMethod paymentMethod;
    private double totalExcludingVat;
    private double totalIncludingVat;
    private double vatAmount;
    private double vatPercentage;
    private Map<String, String> tags;
    
	@DynamoDBTyped(DynamoDBAttributeType.L)
    private List<SaleItem> items;
	
	public double getProductTotalQuantity(Product product){
		double productTotalQuantity = 0;
		
		for(SaleItem item:items){
			if(product.equals(item.getProduct())){
				productTotalQuantity += item.getQuantity();
			}
		}

		return productTotalQuantity;
	}
	
	public double getProductTotalAmount(Product product){
		double productTotalAmount = 0;
		
		for(SaleItem item:items){
			if(product.equals(item.getProduct())){
				productTotalAmount += item.getTotalAmount();
			}
		}
		return productTotalAmount;
		
	}

	public User getUser() { return user; }

	public void setUser(User user) { this.user = user; }

	public Retailer getRetailer() { return retailer; }

	public void setRetailer(Retailer retailer) { this.retailer = retailer; }

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public PaymentMethod getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(PaymentMethod paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	
	public double getTotalExcludingVat() {
		return totalExcludingVat;
	}

	public void setTotalExcludingVat(double totalExcludingVat) {
		this.totalExcludingVat = totalExcludingVat;
	}
	
	public double getTotalIncludingVat() {
		return totalIncludingVat;
	}

	public void setTotalIncludingVat(double totalIncludingVat) {
		this.totalIncludingVat = totalIncludingVat;
	}
	
	public double getVatAmount() {
		return vatAmount;
	}

	public void setVatAmount(double vatAmount) {
		this.vatAmount = vatAmount;
	}
	
	public double getVatPercentage() {
		return vatPercentage;
	}

	public void setVatPercentage(double vatPercentage) {
		this.vatPercentage = vatPercentage;
	}
	
	public List<SaleItem> getItems() {
		return items;
	}

	public void setItems(List<SaleItem> items) {
		this.items = items;
	}

	public void addItem(SaleItem item) {
		if(items == null){
			items = new ArrayList<SaleItem>();
		}
		this.items.add(item);
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
    
    @Override
    public void prePersist() {
    	
    	for(SaleItem item:items){
    		item.prePersist();//Works out the total
    		totalExcludingVat += item.getTotalAmount();
    	}
    	
    	totalIncludingVat = totalExcludingVat * vatPercentage;
    	vatAmount = totalExcludingVat - totalExcludingVat;
    	
    	super.prePersist();
    }

	@Override
	public String toString() {
		return "Sale{" +
				"customer=" + customer +
				", user=" + user +
				", retailer=" + retailer +
				", paymentMethod=" + paymentMethod +
				", totalExcludingVat=" + totalExcludingVat +
				", totalIncludingVat=" + totalIncludingVat +
				", vatAmount=" + vatAmount +
				", vatPercentage=" + vatPercentage +
				", items=" + items +
				'}';
	}

	void setAsCorrective() {
		tags.put("Corrective", "true");
	}

	boolean isCorrective() {
		return tags.get("Corrective") != null;
	}

	@Override
	public void setTags(Map<String, String> tags) {
		this.tags = tags;
	}

	@Override
	public Map<String, String> getTags() {
		return tags;
	}
}

interface Taggable {

	void setTags(Map<String, String> tags);
	Map<String, String> getTags();

}
