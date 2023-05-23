package za.co.gaserve.entities.corrective;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperFieldModel;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTyped;
import za.co.gaserve.entities.Entity;
import za.co.gaserve.entities.regions.Retailer;
import za.co.gaserve.entities.stock.Product;
import za.co.gaserve.entities.stock.Stock;
import za.co.gaserve.entities.user.User;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

@DynamoDBTable(tableName = "Corrective")
public class Corrective extends Entity {

    public Corrective(User user, Retailer retailer, String reason, HashMap<String,Double> differences) {
        this.user = user;
        this.reason = reason;
        this.retailer = retailer;
        this.differences = differences;
    }

    @DynamoDBHashKey(attributeName="id")
    public String getId() {
        return id;
    }

    private String stockTakeId;

    private boolean beenCorrected;

    String reason;

    @DynamoDBTyped(DynamoDBMapperFieldModel.DynamoDBAttributeType.M)
    private HashMap<String,Double> differences;

    @DynamoDBTyped(DynamoDBMapperFieldModel.DynamoDBAttributeType.M)
    private User user;

    @DynamoDBTyped(DynamoDBMapperFieldModel.DynamoDBAttributeType.M)
    private Retailer retailer;


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

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }


    public void setRetailer(Retailer retailer) {
        this.retailer = retailer;
    }

    public Retailer getRetailer() {
        return retailer;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getReason() {
        return reason;
    }

    public HashMap<String, Double> getDifferences() { return differences; }

    public void setDifferences(HashMap<String, Double> differences) { this.differences = differences; }

    public String getStockTakeId() {
        return stockTakeId;
    }

    public void setStockTakeId(String stockTakeId) {
        this.stockTakeId = stockTakeId;
    }

    public boolean isBeenCorrected() {
        return beenCorrected;
    }

    public void setBeenCorrected(boolean beenCorrected) {
        this.beenCorrected = beenCorrected;
    }
}