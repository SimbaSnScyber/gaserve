package za.co.gaserve.entities.user;

import java.util.Date;
import java.util.List;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTyped;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperFieldModel.DynamoDBAttributeType;

import za.co.gaserve.entities.Entity;

/**
 * 
 * @author sellotseka
 *
 * The ids are enum style: ADMINS, RETAIL_MANAGERS, SALES_PEOPLE
 */
@DynamoDBTable(tableName = "Group")
public class Group extends Entity{
	
    private String description;
    
    @DynamoDBTyped(DynamoDBAttributeType.L)
    private List<Role> roles;

    
    
    public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

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
