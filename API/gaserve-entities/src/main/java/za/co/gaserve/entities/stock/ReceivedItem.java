package za.co.gaserve.entities.stock;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperFieldModel;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTyped;
import za.co.gaserve.entities.Entity;

import java.util.Date;

@DynamoDBTable(tableName = "ReceivedItem")
public class ReceivedItem extends Entity {


    @DynamoDBTyped(DynamoDBMapperFieldModel.DynamoDBAttributeType.M)
    private Product product;
    private double quantity;


    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }


    @DynamoDBHashKey(attributeName="id")
    public String getId() {
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
