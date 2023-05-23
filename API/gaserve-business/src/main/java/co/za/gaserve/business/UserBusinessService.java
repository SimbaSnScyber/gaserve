package co.za.gaserve.business;

import za.co.gaserve.entities.user.Login;
import za.co.gaserve.entities.user.User;

import java.util.List;

public interface UserBusinessService {
    User login(Login user);

    User findByEmail(String email);
    User findById(String id);
    
    void register(User user, String retailerId, String roleId);
    void lockUserUnbalanced(String userId);
    void updateUserStatus(String userId);
    void enable(String userId);
	void enable(User user);

	User hasBalancedStockTake();
    List<User> findUserById(String id);



}
