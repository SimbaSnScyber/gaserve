package co.za.gaserve.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import za.co.gaserve.dao.Dao;
import za.co.gaserve.entities.lists.PaymentMethod;
import za.co.gaserve.entities.lists.UserStatus;
import za.co.gaserve.entities.regions.Region;
import za.co.gaserve.entities.regions.Retailer;
import za.co.gaserve.entities.stock.Product;

public class ListBusinessServiceImpl implements ListBusinessService {
	
	@Autowired
	private Dao<PaymentMethod> paymentMethodDao;
	
	@Autowired
	private Dao<Retailer> retailerDao;

	@Autowired
	private Dao<Product> productDao;

	@Autowired
	private Dao<Region> regionDao;

	@Autowired
	private Dao<UserStatus> userStatusesDao;

	public List<PaymentMethod> getPaymentMethods(){
		return paymentMethodDao.findAll();
	}
	
	public List<UserStatus> getUserStatuses(){
		return userStatusesDao.findAll();
	}
	
	public List<Product> getProducts(){
		return productDao.findAll();
	}
	
	public List<Region> getRegions(){
		return regionDao.findAll();
	}
	
	public List<Retailer> getRetailers(Region region){
		
		retailerDao.findByField("region", region);
		return null;
	}
	
	@Override
	public Product findProductById(String id) {
		return productDao.findById(id);
	}
}
