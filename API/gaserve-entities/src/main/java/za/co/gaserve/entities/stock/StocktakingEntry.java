package za.co.gaserve.entities.stock;

import java.util.Date;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperFieldModel.DynamoDBAttributeType;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTyped;

import za.co.gaserve.entities.Entity;

@DynamoDBTable(tableName = "Stocktaking")
public class StocktakingEntry extends Entity{

	public StocktakingEntry (){}
	
	public StocktakingEntry(double quantity,Product product){
		this.quantity = quantity;
		this.product = product;
		prePersist();
	}

	private double quantity;
	
    @DynamoDBTyped(DynamoDBAttributeType.M)
	private Product product;
    
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
