package za.co.gaserve.entities.reports;

import java.util.Date;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTyped;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperFieldModel.DynamoDBAttributeType;

import za.co.gaserve.entities.Entity;
import za.co.gaserve.entities.stock.Product;

/**
 * 
 * @author sellotseka
 *
 */
@DynamoDBTable(tableName = "ReportEntry")
public class ReportEntry extends Entity{

	public ReportEntry(){}

	public ReportEntry(Product product,double quantity,double totalAmount,EntryType type){
		this.product = product;
		this.quantity = quantity;
		this.totalAmount = totalAmount;
		this.type = type;
	}

	public enum EntryType {SOLD,RECIEVED,OPENED_WITH}
	
	@DynamoDBTyped(DynamoDBAttributeType.M)
	private Product product;
	private double quantity;
	private double totalAmount;
	
	@DynamoDBTyped(DynamoDBAttributeType.S)
	private EntryType type;

	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public double getQuantity() {
		return quantity;
	}
	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}
	public double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	} 
	

	public EntryType getType() {
		return type;
	}
	public void setType(EntryType type) {
		this.type = type;
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
