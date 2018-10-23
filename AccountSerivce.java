package com.pennypincherbank.Bank;

import java.util.ArrayList;
import java.util.List;

public class AccountSerivce {

	private static AccountSerivce accountService;
//	final static Logger logger = Logger.getLogger(UserDaoImp.class);
//	set up the singleton
	public static AccountSerivce getInstance() {
		if (accountService == null) {
			accountService = new AccountSerivce();
		}
		return accountService;
	}
	
	private AccountSerivce() {
		
	}	
	public List<Account> getAccount(User user) {
		return AccountDaoImp.getInstance().selectAll(user);
	}
	public boolean withdraw(Account account, int amount) {
//		currently testing,
		return AccountDaoImp.getInstance().withdraw(account, amount);

		
	}
	public boolean deposit(Account account, int amount) {
		return AccountDaoImp.getInstance().deposit(account, amount);
	}
	public List<Account> viewAllAccounts(User user) {
		return new ArrayList<>();
	}
	
}
