package co.za.gaserve.business;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import za.co.gaserve.dao.Dao;
import za.co.gaserve.entities.BusinessException;
import za.co.gaserve.entities.lists.UserStatus;
import za.co.gaserve.entities.regions.Retailer;
import za.co.gaserve.entities.user.Login;
import za.co.gaserve.entities.user.RetailerRole;
import za.co.gaserve.entities.user.Role;
import za.co.gaserve.entities.user.User;

public class UserBusinessServiceImpl implements UserBusinessService {

	@Autowired
	private Dao<UserStatus> userStatusDao;

	@Autowired
	private Dao<User> userDao;

	@Autowired
	private Dao<Login> loginDao;

	@Autowired
	private Dao<Role> roleDao;

	@Autowired
	private Dao<Retailer> retailerDao;

	@Autowired
	private Dao<RetailerRole> retailerRoleDao;

	@Override
	public User login(Login login){

		UserStatus status;
		User user;


		user = login.getUser();
		 if (user == null){
			 throw new BusinessException("User not registered, Please contact your administrator",HttpStatus.NOT_FOUND);
		 }

		 try{
			if (user.getStatus().getId().equals(UserStatus.Values.ACTIVE.name())){
				user = checkLastLogin(user);
			}
		 }catch (NullPointerException ne){
				throw new BusinessException("User has null status", HttpStatus.NOT_FOUND);
		 }

		 status = user.getStatus();

		 if(UserStatus.Values.DISABLED.name().equals(status.getId())){
		 	login.setSuccessful(true);
		 	login.setFailureReason("User has been disabled, Please contact your administrator");
			loginDao.create(login);
			throw new BusinessException("User has been disabled, Please contact your administrator", HttpStatus.UNAUTHORIZED);

		}else if (UserStatus.Values.STOCKTAKE_UNBALANCED.name().equals(status.getId())){
		 	login.setSuccessful(true);
		 	login.setFailureReason("Users stock is Unbalanced, Please contact your administrator");
			loginDao.create(login);
			throw new BusinessException("Users stock is Unbalanced, Please contact your administrator",HttpStatus.CONFLICT);


		}else if (UserStatus.Values.STOCKTAKE_PENDING.name().equals(status.getId())){
		 	login.setSuccessful(true);
		 	loginDao.create(login);
			throw new BusinessException("Requires stocktaking",HttpStatus.PRECONDITION_REQUIRED);
		}

		login.setSuccessful(true);
		loginDao.create(login);
		return user;
}

    @Override
    public User findByEmail(String email){
		List<User> user = userDao.findByField("email",email);

		if (user!=null && !user.isEmpty()){
			return user.get(0);
		}
		return null;

	}

	@Override
    public void register(User user,String retailerId,String roleId){

    	List<User> existingUser = userDao.findByField("email", user.getEmail());

    	if(existingUser != null && !existingUser.isEmpty()){
    		throw new RuntimeException("Email already registered.");
    	}

    	Retailer retailer = retailerDao.findById(retailerId);

    	Role role = roleDao.findById(roleId);

    	user = userDao.create(user);

    	RetailerRole retailerRole = new RetailerRole();

    	retailerRole.setRetailer(retailer);
    	retailerRole.setRole(role);
    	retailerRole.setUser(user);

    	retailerRoleDao.create(retailerRole);

    }

	@Override
	public User findById(String id) {
		return userDao.findById(id);
	}

    @Override
    public void lockUserUnbalanced(String userId){
    	User user = userDao.findById(userId);

    	UserStatus userStatus = userStatusDao.findById(UserStatus.Values.STOCKTAKE_UNBALANCED.name());

    	user.setStatus(userStatus);

    	userDao.update(user);

    }

	@Override
	public void updateUserStatus(String userId) {
		User user = userDao.findById(userId);

		UserStatus userStatus = userStatusDao.findById(UserStatus.Values.ACTIVE.name());

		user.setStatus(userStatus);

		userDao.update(user);
	}

	@Override
    public void enable(String userId){
    	User user = userDao.findById(userId);

    	enable(user);

    }

    @Override
	public void enable(User user) {
		UserStatus userStatus = userStatusDao.findById(UserStatus.Values.ACTIVE.name());

    	user.setStatus(userStatus);

    	userDao.update(user);
	}

	@Override
	public User hasBalancedStockTake() {
		return null;
	}

	@Override
	public List<User> findUserById(String id) {
		List<User> users = new ArrayList<User>();
		users.add(userDao.findById(id));
		return users;
	}

	public User checkLastLogin(User user){

		Long time = new Date().getTime();
		Date midnight = new Date(time - time % (24 * 60 * 60 * 1000));
		List<Login> loginsToday = loginDao.findAfterDate(midnight);

		boolean loginToday = false;

		for (Login login:loginsToday){

			if (login.getUser().getEmail().equals(user.getEmail()) && user.getStatus().getId().equals(UserStatus.Values.ACTIVE.name())){
				loginToday = true;
				break;
			}
		}
		if (loginToday == false){
			user.setStatus(userStatusDao.findById(UserStatus.Values.STOCKTAKE_PENDING.name()));
		}
		userDao.update(user);
		return user;
	}
}
