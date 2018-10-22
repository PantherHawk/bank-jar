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
	
		start();
//		welcome user to the bank
		while( scanner.hasNextLine() ) {
			String s = scanner.nextLine();
			if (s.startsWith("v") && user.getUser_role() > 0) {
	//			handle verify users procedure
				verifyUsers(allUsers);
			} else if (s.startsWith("m")) {
	//			handle get all accounts procedure
				if (user.getIsRegistered() > 0) {
					manageAccounts(user);
				} else {
					System.out.println("You have not yet been registered.");
				}
				
			} else if (s.startsWith("t")) {
	//			handle get all transactions for user procedure
			} else if (s.startsWith("h")) {
//				handle help procedure and print out options again
				System.out.println(user.getUser_role() > 0 ? ADMIN_TRANSACTIONS : CUSTOMER_TRANSACTIONS);
//				TO DO
				if (user.getUser_role() > 0) {
					System.out.println(ADMIN_TRANSACTIONS);
				} else {
					System.out.println(CUSTOMER_TRANSACTIONS);
				}
			} else if (s.startsWith("q")) {
//				handle quit procedure
//				TO DO: 
			}
//		otherwise handle no user found in db.
		}
		if (user.getId() < 1) {
			System.out.println("It looks liket this is your first time.  Type (j) to get started!");
//			add new user program
		}
//		ask for credentials
//		make new user 
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
	public static Scanner start() {
		scanner = new Scanner(System.in);
		System.out.println("Welcome to the penny pincher's bank!");
		System.out.println("Please enter your username.");
		String username = scanner.nextLine();
		System.out.println("Please enter your unique password.");
		String passhash = scanner.nextLine();	
//		look up in db to find user 
		System.out.println("Trying to log in ...");
		user = UserService.getInstance().login(new User(username, passhash));
		System.out.println("user is   " + user);
		while (user.getId() == 0) {
			handleNewUser();
		}
		
		
		System.out.println(user.toString());
		System.out.println(user.getUser_role() > 0 ? "Welcome admin!" : "Welcome " + user.getFirstname());
		checkAdminStatus(user);
		System.out.println(user.getUser_role() > 0 ? 
				"Press (v) to verify users, (m) to manage your accounts, or (t) to view your transactions." : 
					"Press (m) to manage your accounts, or (t) to view your transactions."
			);
//		if user in db, return user info.
		return scanner;
	}
	public static void manageAccounts(User user) {
//		get list of accounts
		List<Account> allMyAccounts = AccountSerivce.getInstance().getAccount(user);
		if (allMyAccounts.isEmpty()) {
			System.out.println("It doesn't look like you have any accounts yet. \n Add your first account!");
//			add an account
//			
		}
		for (int i=0; i < allMyAccounts.size(); i++) {
			Account myAccount = allMyAccounts.get(i);
			System.out.println(" \n Press " + i + " to deposit or withdraw from your " + myAccount.getName() + 
					" account " + "\n current balance: " + myAccount.getCurr_balance());
		}
		String chose = scanner.nextLine();
//		handle not in range case, like chose 10 and there are only 2 accounts
		if (Integer.parseInt(chose) > allMyAccounts.size() || Integer.parseInt(chose) < 0) {
			System.out.println("Sorry, that account doesn't exist. \n");
			manageAccounts(user);
		}
		account = allMyAccounts.get(Integer.parseInt(chose));
		System.out.println("You chose account  named: " + account.getName());
	}
	public static void handleNewUser() {
		System.out.println("It doesn't look you have an account yet. \n" + 
								" Press 0 to log in again or 1 to create a new account.");
		if (Integer.parseInt(scanner.nextLine()) != 0) {
//			create a new account
			System.out.println("Great! Let's get started. \n Please enter your first name.");
			String firstName = scanner.nextLine();
			System.out.println(" Please enter your last name.");
			String lastName = scanner.nextLine();
			System.out.println(" Please enter a username. ");
			String username = scanner.nextLine();
			System.out.println(" And please enter a password.");
			String password = scanner.nextLine();
			Boolean addUserSuccess = UserService.getInstance().addUser(new User(0, 0, 0, username, password, firstName, lastName));
			if (addUserSuccess) {
				System.out.println(" Welcome to the Penny Pinchers family!");
			} else {
				System.out.println("Something went wrong, we could not add you to the DB...");
			}
		} else {
//			scanner.close();
			start();
		}
	}
	public static Scanner scanner = null;
	public static User user = null;
	public static Account account = null;
}

//while ((line = scanner.nextLine()) != null) {
//	store = store + line;
//	if (line.trim().equalsIgnoreCase("exit")) {
//		scanner.close();
//		System.out.println("you wrote: " + store);
//		out.println(store);
//		System.exit(0);
//	}