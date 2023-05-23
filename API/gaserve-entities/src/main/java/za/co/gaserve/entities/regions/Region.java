package za.co.gaserve.entities.regions;

import java.util.Date;
import java.util.List;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTyped;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperFieldModel.DynamoDBAttributeType;

import za.co.gaserve.entities.Entity;

@DynamoDBTable(tableName = "Region")
public class Region extends Entity implements IdName {

	public Region() {
	}

	public Region(String id,String name) {
		this.name = name;
		this.id = id;
	}

	@DynamoDBTyped(DynamoDBAttributeType.L)
	private List<Retailer> retailers;
	
    private String name;
    
    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public List<Retailer> getRetailers () {
		return retailers;
	}

	public void setRetailers(List retailers) {
		this.retailers = retailers;
	}

}
