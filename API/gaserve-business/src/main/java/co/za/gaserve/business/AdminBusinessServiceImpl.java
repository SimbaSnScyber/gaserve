package co.za.gaserve.business;

import org.springframework.beans.factory.annotation.Autowired;
import za.co.gaserve.dao.Dao;
import za.co.gaserve.entities.lists.UserStatus;
import za.co.gaserve.entities.regions.Region;
import za.co.gaserve.entities.regions.Retailer;

import java.util.List;


public class AdminBusinessServiceImpl implements AdminBusinessService {

    @Autowired
    private Dao<Retailer> retailerDao;

    @Autowired
    private Dao<UserStatus> userStatusesDao;

    public List<UserStatus> getUserStatuses(){
        return userStatusesDao.findAll();
    }

    public List<Retailer> getRetailers(Region region){

        retailerDao.findByField("region", region);
        return null;
    }
}
