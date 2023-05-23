package za.co.gaserve.entities.lists;

import java.util.Date;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import za.co.gaserve.entities.Entity;

/**
 * 
 * @author sellotseka
 *
 * The ids are enum style: CASH, ECHO_CASH
 */
@DynamoDBTable(tableName = "PaymentType")
public class PaymentMethod extends Entity implements IdDescription{
	
    private String description;

    @Override
    public String toString() {
        return "PaymentMethod{" +
                "description='" + description + '\'' +
                '}';
    }

    public PaymentMethod(String id, String description) {
        this.description = description;
        this.id = id;
    }

    public PaymentMethod() {
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
