package za.co.gaserve.entities.user;

import java.util.Date;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTyped;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperFieldModel.DynamoDBAttributeType;

import za.co.gaserve.entities.Entity;
import za.co.gaserve.entities.regions.Retailer;

/**
 * 
 * @author sellotseka
 *
 * The ids are enum style: ADMIN, RETAIL_MANAGER, SALES_PERSON
 */
@DynamoDBTable(tableName = "Role")
public class RetailerRole extends Entity{

    @DynamoDBTyped(DynamoDBAttributeType.M)
	private Retailer retailer;
    
    @DynamoDBTyped(DynamoDBAttributeType.M)
    private User user;
    
    @DynamoDBTyped(DynamoDBAttributeType.M)
    private Role role;

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

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
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
