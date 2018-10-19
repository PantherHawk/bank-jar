package com.pennypincherbank.Bank;

public class UserService {
	
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

}
