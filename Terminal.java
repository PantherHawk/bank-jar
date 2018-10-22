package com.pennypincherbank.Bank;

//import java.io.FileNotFoundException;
//import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
//import java.io.PrintWriter;
import java.util.Scanner;
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
		System.out.println("Press (v) to verify users, (m) to manage your accounts, or (t) to view your transactions.");
//		if user in db, return user info.
		while( sc.hasNextLine() ) {
			String s = sc.nextLine();
			if (s.startsWith("v")) {
	//			handle verify users procedure
				verifyUsers(allUsers);
			} else if (s.startsWith("m")) {
	//			handle get all accounts procedure
			} else if (s.startsWith("t")) {
	//			handle get all transactions for user procedure
			} else if (s.startsWith("h")) {
//				handle help procedure and print out options again
				if (user.getUser_role() > 0) {
					System.out.println(ADMIN_TRANSACTIONS);
				} else {
					System.out.println(CUSTOMER_TRANSACTIONS);
				}
			} else if (s.startsWith("q")) {
//				handle quit procedure
			}
//		otherwise handle no user found in db.
		}
		if (user.getId() < 1) {
			System.out.println("It looks liket this is your first time.  Type (j) to get started!");
//			add new user program
		}
//		ask for credentials
//		make new user 
		sc.close();
		System.exit(0);
		  
	}
	private static final String CUSTOMER_TRANSACTIONS = 
			"	Customer transactions: " +
					"(d)eposit, (w)ithdraw, (t)ransfer, (b)alance, (q)uit, (h)elp.";
	private static final String ADMIN_TRANSACTIONS = 
			"	Admin options: " +
					"(v)erify users, view (t)ransactions, view (a)ll users, (q)uit, (h)elp." + CUSTOMER_TRANSACTIONS;
	
	public static List<User> allUsers = UserService.getInstance().getAllUsers();
	
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
	public static void verifyUsers(List<User> users) {
		Scanner sc = new Scanner(System.in);

		for (User user : users) {
			System.out.println("id: " + users.indexOf(user) + " " + user.getFirstname() + " " + user.getLastname());
		}
		System.out.println(" Type the id of the user you want to verify!");
		String s = sc.nextLine();
		String[] ids = s.split("");
		for (String id : ids) {
			User toAdd = users.get(Integer.parseInt(id));
			System.out.println("Adding user: " + users.get(Integer.parseInt(id)));
			if (UserService.getInstance().verifyUser(toAdd)) { 
				
				System.out.println("Verified " + toAdd.getFirstname() + " " + toAdd.getLastname() + " .");
			}
		}
		sc.close();
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