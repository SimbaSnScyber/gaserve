package za.co.gaserve.entities.stock;

import java.util.Date;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import za.co.gaserve.entities.Entity;

@DynamoDBTable(tableName = "Product")
@Component
@Scope("prototype")
public class Product extends Entity{
    private String description;

    private double price;

    public Product(String id,String description, double price) {
        this.description = description;
        this.id = id;
        this.price = price;
    }

    public Product() {
    }
    
    
    public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
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
