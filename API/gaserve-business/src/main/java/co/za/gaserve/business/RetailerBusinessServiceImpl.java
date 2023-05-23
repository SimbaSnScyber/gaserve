package co.za.gaserve.business;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import za.co.gaserve.dao.Dao;
import za.co.gaserve.entities.BusinessException;
import za.co.gaserve.entities.regions.Retailer;
import za.co.gaserve.entities.regions.RetailerDao;
import za.co.gaserve.entities.sales.SalesDao;
import za.co.gaserve.entities.user.User;

import java.util.List;

public class RetailerBusinessServiceImpl implements RetailerBusinessService {

	@Autowired
	private Dao<Retailer> dao;

	@Autowired
	private Dao<User> userDao;

	@Autowired
	private RetailerDao retailerDao;
	
	@Autowired
	private SalesDao salesDto;

	@Override
	public Retailer getRetailerByUser(String userId){
		try {
			User user = userDao.findById(userId);

			List<Retailer> retailers = retailerDao.findAll();

			for (Retailer retailer : retailers) {
				if (user != null && retailer != null) {
					if (retailer.getManager().getId().equals(user.getId())) {
						return retailer;
					}
				} else {
					continue;
				}
			}
		}
		catch (NullPointerException ex){
			ex.printStackTrace();
		}
		return null;

		//return dao.findOneByField("manager", user); //TODO: write query to search objects
	}

	@Override
	public Retailer getRetailerByUser(User user) {

		List<Retailer> retailers = retailerDao.findAll();
		if (retailers.size()!=0) {
			User manager = userDao.findByField("email", user.getEmail()).get(0);
			if (!manager.equals(null)) {
				for (Retailer retailer : retailers) {
					//retailer.setManager(manager);

					if (retailer.getManager().getId().equals(user.getId())) {
						return retailer;
					}
				}
			}
			else {
				throw new BusinessException("No user/manager found", HttpStatus.NOT_FOUND);
			}
		}else
		{
			throw new BusinessException("No retailers found", HttpStatus.NOT_FOUND);
		}
		return null;

		//return retialerDao.findRetailerByManager(user);//TODO: write query to search objects
	}

	@Override
	public User findByField(String fieldName, Object o) {
		return userDao.findByField("email", o).get(0);
	}
}
