package za.co.gaserve.entities.regions;

import java.util.Date;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTyped;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperFieldModel.DynamoDBAttributeType;

import za.co.gaserve.entities.Entity;
import za.co.gaserve.entities.user.User;

@DynamoDBTable(tableName = "Retailer")
public class Retailer extends Entity implements IdName{

    @DynamoDBAttribute(attributeName = "retailerName")
    private String name;

    public Retailer(String name, User manager) {
        this.name = name;
        this.manager = manager;
    }

    public Retailer() {
    }

    @Override
    public String toString() {
        return "Retailer{" +
                "name='" + name + '\'' +
                ", manager=" + manager +
                ", id='" + id + '\'' +
                ", active=" + active +
                ", lastUpdate=" + lastUpdate +
                ", createdDate=" + createdDate +
                '}';
    }

    @DynamoDBTyped(DynamoDBAttributeType.M)
    private User manager;
    
    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public User getManager() {
		return manager;
	}

	public void setManager(User manager) {
		this.manager = manager;
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
    public void setActive(Boolean active){
        this.active = active;
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
