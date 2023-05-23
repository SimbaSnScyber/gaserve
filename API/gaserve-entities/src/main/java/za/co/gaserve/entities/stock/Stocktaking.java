package za.co.gaserve.entities.stock;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperFieldModel.DynamoDBAttributeType;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTyped;

import za.co.gaserve.entities.BusinessException;
import za.co.gaserve.entities.Entity;
import za.co.gaserve.entities.corrective.Corrective;
import za.co.gaserve.entities.regions.Retailer;
import za.co.gaserve.entities.user.User;

@DynamoDBTable(tableName = "Stocktaking")
public class Stocktaking extends Entity{
    	
    @DynamoDBTyped(DynamoDBAttributeType.L)
    private List<StocktakingEntry> stockCountedBySystem;
    
    @DynamoDBTyped(DynamoDBAttributeType.L)
    private List<StocktakingEntry> stockCountedByRetailer;

    @DynamoDBTyped(DynamoDBAttributeType.L)
    private List<StocktakingEntry> correctionEntries;

    private Boolean balances = false;

    private String reasonBalanced;
    private Date dateBalanced;
   
    private Date stocktakeDate;

    @DynamoDBTyped(DynamoDBAttributeType.M)
    private User balancedBy;

    @DynamoDBTyped(DynamoDBAttributeType.M)
	private User user;
    
    @DynamoDBTyped(DynamoDBAttributeType.M)
	private Retailer retailer;

    @DynamoDBTyped(DynamoDBAttributeType.L)
    private List<String> errorMessages;

	@DynamoDBTyped(DynamoDBAttributeType.M)
	private HashMap<String,Double> differences;

	public boolean compare(List<Stock> stocks){

    	//--Check quantities are equal between stock on hand and what he counted
    	if(stocks.size() != stockCountedByRetailer.size()){
    		addErrorMessage("Stock on hand are not equal.");
    	}

    	HashMap<String,Double> stocktakeDiffereences = new HashMap<>();
    	//--Check if the contain same products. Quantities can match but products differ
    	//--You hit an imbalance throw. Or maybe continue comparing so that its a once off.
		for(Stock stock:stocks){
    		StocktakingEntry entry = getEntry(stock.getProduct());
    		if(entry == null){
    			addErrorMessage("Product on hand missing from counted stock. Product["+stock.getProduct().getId()+"] on hand["+stock.getQuantity()+"]");
    		}
    		if(entry.getQuantity() != stock.getQuantity()){
    			addErrorMessage("Stock on hand mismatches the countet stock. Product["+stock.getProduct().getId()+"] counted["+entry.getQuantity()+"] on hand ["+stock.getQuantity()+"]");
				stocktakeDiffereences.put(stock.getProduct().getId(),entry.getQuantity()-stock.getQuantity());

    		}
    	}

    	setDifferences(stocktakeDiffereences);
		//--we balance else we are exiting via a throw and leaving it unbalanced
		if (getErrorMessages() == null){
			balances = true;
		}

		return balances;

    }

    private StocktakingEntry getEntry(Product product){
    	for(StocktakingEntry entry:stockCountedByRetailer){
    		if(entry.getProduct().getId().equals(product.getId())){
    			return entry;
    		}
    	}
    	return null;
    }

	public void addErrorMessage(String errorMessage) {
		if(this.errorMessages == null ){
			this.errorMessages = new ArrayList<String>();
		}
		errorMessages.add(errorMessage);
	}

	public List<String> getErrorMessages() {
		return errorMessages;
	}

	public void setErrorMessages(List<String> errorMessages) {
		this.errorMessages = errorMessages;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Retailer getRetailer() {
		return retailer;
	}

	public void setRetailer(Retailer retailer) {
		this.retailer = retailer;
	}

	public Date getStocktakeDate() {
		return stocktakeDate;
	}

	public void setStocktakeDate(Date date) {
		this.stocktakeDate = date;
	}

	public List<StocktakingEntry> getStockCountedBySystem() {
		return stockCountedBySystem;
	}

	public void addCorrectionEntry(StocktakingEntry stocktakingEntry) {
		
		if(correctionEntries == null){
			correctionEntries = new ArrayList<StocktakingEntry>();
		}
		
		this.correctionEntries.add(stocktakingEntry);
	}

	public void addStockCountedBySystem(StocktakingEntry stocktakingEntry) {
		
		if(stockCountedBySystem == null){
			stockCountedBySystem = new ArrayList<StocktakingEntry>();
		}
		
		this.stockCountedBySystem.add(stocktakingEntry);
	}

	public void addStockCountedByRetailer(StocktakingEntry stocktakingEntry) {
		
		if(stockCountedByRetailer == null){
			stockCountedByRetailer = new ArrayList<StocktakingEntry>();
		}
		
		this.stockCountedByRetailer.add(stocktakingEntry);
	}

	public List<StocktakingEntry> getCorrectionEntries() {
		return correctionEntries;
	}

	public void setCorrectionEntries(List<StocktakingEntry> correctionEntries) {
		this.correctionEntries = correctionEntries;
	}

	public void setStockCountedBySystem(List<StocktakingEntry> stockCountedBySystem) {
		this.stockCountedBySystem = stockCountedBySystem;
	}

	public List<StocktakingEntry> getStockCountedByRetailer() {
		return stockCountedByRetailer;
	}

	public void setStockCountedByRetailer(List<StocktakingEntry> stockCountedByRetailer) {
		this.stockCountedByRetailer = stockCountedByRetailer;
	}

	public String getReasonBalanced() {
		return reasonBalanced;
	}

	public void setReasonBalanced(String reasonBalanced) {
		this.reasonBalanced = reasonBalanced;
	}

	public Date getDateBalanced() {
		return dateBalanced;
	}

	public void setDateBalanced(Date dateBalanced) {
		this.dateBalanced = dateBalanced;
	}

	public User getBalancedBy() {
		return balancedBy;
	}

	public void setBalancedBy(User balancedBy) {
		this.balancedBy = balancedBy;
	}

	public void setBalances(Boolean balances) {
		this.balances = balances;
	}

    public Boolean getBalances() {
        return balances;
    }

    public void setBalance(Boolean balances) {
        this.balances = balances;
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

	public HashMap<String, Double> getDifferences() {
		return differences;
	}

	public void setDifferences(HashMap<String, Double> differences) {
		this.differences = differences;
	}
    
    public void balanceOut(User user,String reason){

    	//TODO: Generate Balancing entries.
    	if(stockCountedByRetailer != stockCountedBySystem) {

		}
    	//--Check if Gas balances if 
    	//---not create entries to correct
    	
    	//--Check if Stove balances if 
    	//---not create entries to correct

    	//--Check if Cylinders balances if 
    	//---not create entries to correct

    	
    	balances = true;
    	reasonBalanced = reason;
    	balancedBy = user;
    	dateBalanced = new Date();
    }

}
