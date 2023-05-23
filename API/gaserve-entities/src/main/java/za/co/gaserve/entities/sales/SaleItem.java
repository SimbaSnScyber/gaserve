package za.co.gaserve.entities.sales;

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
 * The ids are enum style: ACTIVE, STOCKTAKING_BLOCKED, DISABLED
 */
@DynamoDBTable(tableName = "SaleItem")
public class SaleItem extends Entity{

	public SaleItem(){		
	}

	public SaleItem(
		    Product product,
		    double quantity)
	{		
		this.product = product;
		this.quantity = quantity;
		this.totalAmount = product.getPrice() * this.quantity;
		prePersist();
	}

	@DynamoDBTyped(DynamoDBAttributeType.M)
    private Product product;
    private double quantity;
    private double totalAmount;
    
    
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
    	totalAmount = quantity * getProduct().getPrice();
    	super.prePersist();
    }
    
}
