package za.co.gaserve.entities.user;

import java.util.Date;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import za.co.gaserve.entities.Entity;

/**
 * 
 * @author sellotseka
 *
 * The ids are enum style: ADMIN, RETAIL_MANAGER, SALES_PERSON
 */
@DynamoDBTable(tableName = "Role")
public class Role extends Entity{
    private String description;

    public String getDescription() {
		return description;
	}

	public void setDescription(String desciption) {
		this.description = desciption;
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
