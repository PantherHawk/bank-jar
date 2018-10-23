package com.pennypincherbank.Bank;

//import java.io.FileNotFoundException;
//import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
//import java.io.PrintWriter;
import java.util.Scanner;
//import java.util.logging.Logger;

//import org.apache.log4j.BasicConfigurator;

import org.apache.log4j.Logger;

public class Terminal {
//	private static Logger logger = Logger.getLogger("Terminal");

	final static Logger log = Logger.getLogger(Terminal.class);
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
					System.out.println("You have not yet been registered." + "\n Please try again later!");
//					TODO: log out and knock 'em to main menu
				}
				
			} else if (s.startsWith("t")) {
	//			TODO: handle get all transactions for user procedure
				handleTxOptions();
				
			} else if (s.startsWith("h")) {
//				handle help procedure and print out options again
				System.out.println(user.getUser_role() > 0 ? ADMIN_TRANSACTIONS : CUSTOMER_TRANSACTIONS);
//				
			} else if (s.startsWith("q")) {
				System.out.println("Thanks for banking with Penny Pinchers! " + "\n Please visit us again!");
//				handle quit procedure
//				TODO: knock 'em back to main menu;
			}

		}
//		otherwise handle no user found in db.
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
					"(d)eposit, (w)ithdraw, (t)ransactions, (b)alance, (q)uit, (h)elp.";
	private static final String ADMIN_TRANSACTIONS = 
			"	Admin options: " +
					"(v)erify users, view (t)ransacutions, view (a)ll users, (q)uit, (h)elp." + CUSTOMER_TRANSACTIONS;
	public static final String ADMIN_TX_OPTIONS = "  Admin transaction options: " +
					"view (a)ll account transactions, view (y)our transactions, view (w)ithdrawal transactions, view (d)eposit transactions, vi(e)w transactions by date.";
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
//				TODO: knock 'em back to main menu
			}
		}
		sc.close();
	}
	public static Scanner start() {
		log.info("Starting application...");
		System.out.println("Welcome to the penny pincher's bank!");
		System.out.println("Please enter your username.");
		scanner = new Scanner(System.in);
		String username = scanner.nextLine();
		System.out.println("Please enter your unique password.");
		String passhash = scanner.nextLine();	
//		look up in db to find user 
		log.info("Trying to log in with username " + username + " and password " + passhash);
		user = UserService.getInstance().login(new User(username, passhash));
		System.out.println("user is   " + user);
		while (user.getId() == 0) {
			handleNewUser();
		}
//		TODO: logout and log back in.
		
		System.out.println(user.toString());
		System.out.println(user.getUser_role() > 0 ? "Welcome admin!" : "Welcome " + user.getFirstname() + " " + user.getLastname());
		checkAdminStatus(user);
		System.out.println(user.getUser_role() > 0 ? 
				"Press (v) to verify users, (m) to manage your accounts, or (t) to view your transactions." : 
					"Press (m) to manage your accounts, or (t) to view your transactions."
			);
		return scanner;
	}
	public static void manageAccounts(User user) {
//		get list of accounts
		List<Account> allMyAccounts = AccountSerivce.getInstance().getAccount(user);
		if (allMyAccounts.isEmpty()) {
			System.out.println("It doesn't look like you have any accounts yet." + 
									"\n Add your first account!");
//			add an account
			
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
//		TODO: get transaction history
		withdrawOrDeposit();
		
		
	}
	public static void withdrawOrDeposit() {
		System.out.println("You chose account  named: " + account.getName() + 
				"/n make a (w)ithdrawal or a (d)eposit.");
		String accountAction = scanner.nextLine();
		if (accountAction.toLowerCase().startsWith("w")) {
			withdrawalProcedure();
		} else if (accountAction.toLowerCase().startsWith("d")) {
			depositProcedure();
			
		} else if (!accountAction.toLowerCase().startsWith("d") || !accountAction.toLowerCase().startsWith("w")) {
			withdrawOrDeposit();
		}
	}
	public static void depositProcedure() {
		System.out.println("How much do you want to deposit?");
		String depositAmt = scanner.nextLine();
		if (!isNumber(depositAmt)) {
			System.out.println("That's not a number!");
		} else {
			boolean depositSuccess = AccountSerivce.getInstance().deposit(account, Integer.parseInt(depositAmt));
			System.out.println("what is deposit success?   --->" + depositSuccess);
			if (depositSuccess) {
				account.setCurr_balance(account.getCurr_balance() + Integer.parseInt(depositAmt));
				System.out.println("Your current balance in " + account.getName() + " account is $" + account.getCurr_balance() + ".");
//				TODO: knock 'em back to a main menu.
			}
		}
		
	}
	public static void withdrawalProcedure() {
		System.out.println("How much do you want to withdraw?");
		String withdrawalAmt = scanner.nextLine();
		if (!isNumber(withdrawalAmt)) {
			System.out.println("That's not a number!");
			withdrawalProcedure();
		}
		if (account.getCurr_balance() < Integer.parseInt(withdrawalAmt)) {
			System.out.println("You don't have enough money in the account to withdraw $" + withdrawalAmt);
			withdrawalProcedure();
		} else {
			boolean withdrawSuccess = AccountSerivce.getInstance().withdraw(account, Integer.parseInt(withdrawalAmt));
			if (withdrawSuccess) {
				account.setCurr_balance(account.getCurr_balance() - Integer.parseInt(withdrawalAmt));
				System.out.println("Transaction success! Enjoy your $" + withdrawalAmt + "\n Don't spend it all in one place!");
//				TODO: knock 'em back to a main menu.

			}
		}
	}
	
	public static void handleNewUser() {
		System.out.println("It doesn't look you have an account yet. \n" + 
								" Press 0 to log in again or 1 to create a new account.");
		String pressed = scanner.nextLine();
		System.out.println("You chose: " + pressed);
		if (!isNumber(pressed)) {
			System.out.println("That wasn't a 0 or a 1!");
			handleNewUser();
		}
		if (Integer.parseInt(pressed) != 0) {
//			create a new account
			System.out.println("Great! Let's get started. \n Please enter your first name.");
			String firstName = scanner.nextLine();
			System.out.println(" Please enter your last name.");
			String lastName = scanner.nextLine();
			System.out.println(" Please enter a username. ");
			String username = scanner.nextLine();
			System.out.println(" And please enter a password.");
			String password = scanner.nextLine();
			Boolean addUserSuccess = UserService.getInstance().addUserSecure(new User(0, 0, 0, username, password, firstName, lastName));
			System.out.println("what is addUserSuccess ? " + addUserSuccess);
			if (addUserSuccess) {
				System.out.println(" Welcome to the Penny Pinchers family!");
				start();
			} else {
				System.out.println("Something went wrong, we could not add you to the DB...");
			}
		} else {
//			scanner.close();
			start();
		}
	}
	public static void handleTxOptions() {
		System.out.println(ADMIN_TX_OPTIONS);
		String choice = scanner.next();
		if (choice.toLowerCase().equals("a")) {
			System.out.println("clicked a...");
			//a, y, w, d, e
			viewAllTx();
		} else if (choice.toLowerCase().equals("u")) {
			viewAnyoneUsersTxs();
		} else if (choice.toLowerCase().startsWith("y")) {
			viewMyTx();
		} else if (choice.toLowerCase().startsWith("w"))
	}
	public static void viewMyTx() {
		List<Transaction> allTxBelongingToUser = TransactionService.getInstance().getTxsForUser(allUsers.indexOf(user.getId()));
		for (Transaction tx : allTxBelongingToUser) {
			System.out.println("id: " + tx.getAccount_id() + "   user_id: " + tx.getUser_id() + "   " + tx.getType().toUpperCase() + "    $" + tx.getAmount());
		}
	}
	public static void viewAllTx() {
		if (user.getUser_role() < 1) {
			System.out.println("user is not an admin.");
//			TODO: knock 'em to the main menu
		}
		List<Transaction> allTx = TransactionService.getInstance().getAllTxs();
		System.out.println("all transaction: " + allTx.toString());
		for (Transaction tx : allTx) {
			System.out.println("id: " + tx.getAccount_id() + "   user_id: " + tx.getUser_id() + "   " + tx.getType().toUpperCase() + "    $" + tx.getAmount());
		}
	}
	public static void viewAnyoneUsersTxs() {
		if (user.getUser_role() < 1) {
			System.out.println("user is not an admin.");
//			TODO: knock 'em to the main menu
		}
		System.out.println("Whose transactions would you like to view?"); 
		displayAllUsers();
		System.out.println("Select the id of the user for whom you would like to view transactions.");
		Integer select = Integer.parseInt(scanner.next());
		if (select > allUsers.size() || select < 0) {
			System.out.println("Selection out of range.");
			viewAnyoneUsersTxs();
		}
		List<Transaction> allTxBelongingToUser = TransactionService.getInstance().getTxsForUser(allUsers.indexOf(select));
		for (Transaction tx : allTxBelongingToUser) {
			System.out.println("id: " + tx.getAccount_id() + "   user_id: " + tx.getUser_id() + "   " + tx.getType().toUpperCase() + "    $" + tx.getAmount());
		}
	}
	public static void goToMainMenu() {
		System.out.println("Penny Pinchers bank.");
	}
	public static boolean isNumber(String s) {
		try {
			Integer.parseInt(s);
		} catch(NumberFormatException e) {
			return false;
		} catch(Exception e) {
			return false;
		}
		return true;
	}
	public static void displayAllUsers() {
		for (User user : allUsers) {
			System.out.println("id: " + allUsers.indexOf(user) + " " + user.getFirstname() + " " + user.getLastname());
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