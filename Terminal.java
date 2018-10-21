package com.pennypincherbank.Bank;

//import java.io.FileNotFoundException;
//import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
//import java.io.PrintWriter;
import java.util.Scanner;
import java.util.concurrent.CopyOnWriteArrayList;
//import java.util.logging.Logger;

//import org.apache.log4j.BasicConfigurator;

//import org.apache.log4j.Logger;

public class Terminal {
//	private static Logger logger = Logger.getLogger("Terminal");

	
	public static void main(String[] args) throws IOException {
//		BasicConfigurator.configure();
		Scanner sc = new Scanner(System.in);
		
//		welcome user to the bank
		System.out.println("Welcome to the penny pincher's bank!");
		System.out.println("Please enter your username.");
		String username = sc.nextLine();
		System.out.println("Please enter your unique password.");
		String passhash = sc.nextLine();	
//		look up in db to find user 
		System.out.println("Trying to log in ...");
		User user = UserService.getInstance().login(new User(username, passhash));
		System.out.println(user.toString());
		System.out.println(user.getUser_role() > 0 ? "Welcome admin!" : "Welcome " + user.getFirstname());
		checkAdminStatus(user);
		
//		if user in db, return user info.
		
//		otherwise handle no user found in db.
//		ask for credentials
//		make new user 
		sc.close();
		System.exit(0);
		  
	}
	public static void checkAdminStatus(User user) {
		System.out.println("user role id: " + user.getUser_role());
		if (user.getUser_role() > 0) {
			System.out.println("Welcome admin privelaged user!");
			System.out.println(" Here are your pending users.");
			final List<User> userList = UserService.getInstance().getAllUsers();
			final List<User> verifiedUsers = new ArrayList<User>();
			Iterator<User> iter = userList.iterator();
			
			while(iter.hasNext()) {
				User u = iter.next();
				
				if (u.getIsRegistered() > 0) {
					verifiedUsers.add(u);
					iter.remove();
				}
			}
			System.out.println("verified users: " + verifiedUsers);
			System.out.println("pending users: " + userList);
			
		}
	}
}

//while ((line = scanner.nextLine()) != null) {
//	store = store + line;
//	if (line.trim().equalsIgnoreCase("exit")) {
//		scanner.close();
//		System.out.println("you wrote: " + store);
//		out.println(store);
//		System.exit(0);
//	}