package co.za.gaserve.business;

import za.co.gaserve.entities.regions.Retailer;
import za.co.gaserve.entities.user.User;

public interface RetailerBusinessService {
	Retailer getRetailerByUser(String user);
	Retailer getRetailerByUser(User user);
	User findByField(String fieldName, Object o);

}
