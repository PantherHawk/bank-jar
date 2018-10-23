package com.pennypincherbank.Bank;

import java.util.List;
import org.apache.log4j.Logger;


public class UserService {
	final static Logger log = Logger.getLogger(UserService.class);

//	set up singleton
	private static UserService userService;
	private UserService() {
		
	}
	public static UserService getInstance( ) {
		if (userService == null) {
			userService = new UserService();
		}
		return userService;
	}
	public boolean addUser(User user) {
		return UserDaoImp.getInstance().insert(user);
	}
	public boolean addUserSecure(User user) {
		return UserDaoImp.getInstance().insertProcedure(user);
	}
	public boolean helloWorld() {
		return UserDaoImp.getInstance().helloWorldProcedure();
	}
	public List<User> getAllUsers() {
		return UserDaoImp.getInstance().selectAll();
	}
	public boolean verifyUser(User user) {
//		get user DAO instance, call verify user
		return UserDaoImp.getInstance().verifyUser(user);
	}
	public User login(User user) {
//		get user info w/o validation
		User attempt = UserDaoImp.getInstance().select(user);
//		login protocol: we hash the user input, which returns a hash, 
//		and we match that to the one stored in the db
		log.info("HASH ====>" + attempt.getPasshash());
		if (UserDaoImp.getInstance().getUserHash(attempt).equals(UserDaoImp.getInstance().getUserHash(user))) {
			log.info("login success");
			return attempt;
		}
		log.info("login failed.");
//		create user program
		return new User();
	}

}
