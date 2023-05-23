package za.co.gaserve.entities.regions;

import java.util.ArrayList;
import java.util.List;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;

import za.co.gaserve.dao.DaoImpl;
import za.co.gaserve.entities.user.User;

public class RetailerDaoImpl extends DaoImpl<Retailer> implements RetailerDao{

	public RetailerDaoImpl(AmazonDynamoDB db){
	     super(Retailer.class,db);
	}
	
	public Retailer findRetailerByManager(User manager){
		
		List<Retailer> all = findAll();
		
		for(Retailer retailer:all){
			if(manager.getId().equals(retailer.getManager().getId())){
				return retailer;
			}
		}
		
		return null;
	}

	@Override
	public List<Retailer> findAllInActive() {

		List<Retailer> all = findAll();
		List<Retailer> notActive = new ArrayList<>();
		for(Retailer retailer:all){
			if(retailer.getActive().equals(false)){
				notActive.add(retailer);
			}
		}
		return notActive;
	}
}
