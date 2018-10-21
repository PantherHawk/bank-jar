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
	public User login(User user) {
//		get user info w/o validation
		User attempt = UserDaoImp.getInstance().select(user);
		System.out.println("attempt: " + attempt);
//		login protocol: we hash the user input, which returns a hash, 
//		and we match that to the one stored in the db
		System.out.println("user passhash ====>" + attempt.getPasshash());
		if (UserDaoImp.getInstance().getUserHash(attempt).equals(UserDaoImp.getInstance().getUserHash(user))) {
			System.out.println("login success");
			return user;
		}
		System.out.println("login failed.");
		return new User();
	}

}
