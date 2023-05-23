package co.za.gaserve.business;

import za.co.gaserve.entities.lists.UserStatus;
import za.co.gaserve.entities.regions.Region;
import za.co.gaserve.entities.regions.Retailer;

import java.util.List;

public interface AdminBusinessService {
    List<UserStatus> getUserStatuses();
    List<Retailer> getRetailers(Region region);
}
