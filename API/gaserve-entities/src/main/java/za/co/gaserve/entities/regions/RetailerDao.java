package za.co.gaserve.entities.regions;

import za.co.gaserve.dao.Dao;
import za.co.gaserve.entities.user.User;

public interface RetailerDao extends Dao<Retailer>{

	public Retailer findRetailerByManager(User manager);
	
}
