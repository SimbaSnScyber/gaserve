package za.co.gaserve.entities.stock;

import java.util.Date;
import java.util.List;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTyped;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperFieldModel.DynamoDBAttributeType;

import za.co.gaserve.entities.BusinessException;
import za.co.gaserve.entities.Entity;
import za.co.gaserve.entities.regions.Retailed;
import za.co.gaserve.entities.regions.Retailer;

@DynamoDBTable(tableName = "Stock")
public class Stock extends Entity implements Retailed {

    public Stock(Product product, int quantity, Retailer retailer) {
        this.product = product;
        this.quantity = quantity;
        this.retailer = retailer;
    }

    public Stock() {
    }

    @DynamoDBTyped(DynamoDBAttributeType.M)
    private Product product;
    private double quantity;

    @DynamoDBTyped(DynamoDBAttributeType.M)
	private Retailer retailer;
    
    
    public Retailer getRetailer() {
		return retailer;
	}

	public void setRetailer(Retailer retailer) {
		this.retailer = retailer;
	}

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
    
    public void receive(Receiving receiving){
    /*	if(!receiving.getProduct().equals(product)){
    		throw new BusinessException("Not the same product");
    	}

    	quantity += receiving.getQuantity();*/
    }

    public void receiveList(List<ReceivedItem> receiving){
        for(ReceivedItem item: receiving) {
            if (item.getProduct().equals(product)) {
                throw new BusinessException("Not the same product");
            }

            quantity += item.getQuantity();
        }
    }

    public void receiveItem(ReceivedItem receiving){

            if (!receiving.getProduct().equals(product)) {
                throw new BusinessException("Not the same product");
            }

            quantity += receiving.getQuantity();
        }



}
