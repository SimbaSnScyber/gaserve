package za.co.gaserve.entities.stock;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTyped;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperFieldModel.DynamoDBAttributeType;

import za.co.gaserve.entities.Entity;
import za.co.gaserve.entities.regions.Retailer;
import za.co.gaserve.entities.sales.SaleItem;
import za.co.gaserve.entities.user.User;

@DynamoDBTable(tableName = "Receiving")
public class Receiving extends Entity{



    @DynamoDBTyped(DynamoDBAttributeType.L)
    private List<ReceivedItem> items;


    @DynamoDBTyped(DynamoDBAttributeType.M)
    private Retailer retailer;


    @DynamoDBTyped(DynamoDBAttributeType.M)
    private User user;

    public Retailer getRetailer() {
        return retailer;
    }

    public void setRetailer(Retailer retailer) {
        this.retailer = retailer;
    }

    public List<ReceivedItem> getItems() {
        return items;
    }

    public void addItem(ReceivedItem item) {
        if(items == null){
            items = new ArrayList<ReceivedItem>();
        }
        this.items.add(item);
    }

    public void setItems(List<ReceivedItem> items) {
        this.items = items;
    }

    @Override
    public void prePersist() {

        for(ReceivedItem item:items){
            item.prePersist();

        }

        super.prePersist();
    }

    public User getUser() { return user;
    }

    public void setUser(User user) { this.user = user;
    }

    public Receiving(){ }




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
