package co.za.gaserve.business;

import java.util.List;

import za.co.gaserve.entities.lists.PaymentMethod;
import za.co.gaserve.entities.lists.UserStatus;
import za.co.gaserve.entities.regions.Region;
import za.co.gaserve.entities.regions.Retailer;
import za.co.gaserve.entities.stock.Product;

public interface ListBusinessService {
	List<PaymentMethod> getPaymentMethods();
	List<UserStatus> getUserStatuses();
	List<Product> getProducts();
	List<Region> getRegions();
	List<Retailer> getRetailers(Region region);
	Product findProductById(String id);
}
