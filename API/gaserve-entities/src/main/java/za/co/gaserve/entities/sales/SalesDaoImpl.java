package za.co.gaserve.entities.sales;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;

import za.co.gaserve.dao.DaoImpl;
import za.co.gaserve.entities.MyDateUtil;
import za.co.gaserve.entities.regions.Retailer;
import za.co.gaserve.entities.regions.RetailerFilter;

public class SalesDaoImpl extends DaoImpl<Sale> implements SalesDao{

	public SalesDaoImpl(AmazonDynamoDB db){
	     super(Sale.class,db);
	}
	
	@Override
	public List<Sale> getSalesForDay(Retailer retailer,Date date){
		Date from = MyDateUtil.setStartOfDay(date);

		Date to = MyDateUtil.setEndOfDay(date);

		return getSalesByDateRange(retailer, from, to);
	}

	@Override
	public List<Sale> getSalesForMonth(Retailer retailer,Date date){
		Date from = MyDateUtil.setStartOfMonth(date);
		Date to = MyDateUtil.setEndOfDay(date);
				
       return getSalesByDateRange(retailer, from, to);
	}

	@Override
	public List<Sale> getSalesByDateRange(Retailer retailer, Date from, Date to) {
		SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		   dateFormatter.setTimeZone(TimeZone.getTimeZone("UTC"));
		   String fromStr = dateFormatter.format(from);
		   String toStr = dateFormatter.format(to);

		   Map<String,String> expressionAttributeName = new HashMap<>();
		   expressionAttributeName.put("#createdDate","createdDate");

		   Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
		   eav.put(":from", new AttributeValue().withS(fromStr));
		   eav.put(":to", new AttributeValue().withS(toStr));
		   //eav.put(":retailer", new AttributeValue().withS(retailer.toString()));//TODO: Test this. I do not think it works

		    DynamoDBScanExpression queryExpression = new DynamoDBScanExpression()

					.withFilterExpression("#createdDate BETWEEN :from AND :to")
					.withExpressionAttributeNames(expressionAttributeName)
					.withExpressionAttributeValues(eav);
		//.withKeyConditionExpression("retailer = :retailer and createdDate between :from and :to").withExpressionAttributeValues(eav);

		return RetailerFilter.filterByRetailer(retailer, findByScan(queryExpression));
	}


	@Override
	public List<Sale> findAllInActive() {
		return null;
	}
}
