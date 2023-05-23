package za.co.gaserve.entities.lists;

import java.util.Date;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import za.co.gaserve.entities.Entity;

/**
 * 
 * @author sellotseka
 *
 * The ids are enum style: ACTIVE, STOCKTAKING_BLOCKED, DISABLED,REGISTERED
 */
@DynamoDBTable(tableName = "UserStatus")
public class UserStatus extends Entity implements IdDescription{

    public UserStatus(String id,String description) {
        this.description = description;
        this.id = id;
    }

    public UserStatus() {

    }

    public enum Values {ACTIVE, STOCKTAKE_PENDING,STOCKTAKE_UNBALANCED, DISABLED};
	
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
