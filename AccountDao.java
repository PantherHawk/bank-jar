package com.pennypincherbank.Bank;

import java.util.Currency;

public interface AccountDao {
	public boolean addAccount(Account account);
	public Account select(Account account);
	public boolean withdraw(Account account, int amount);
	public boolean deposit(Account account, int amount);
	public boolean moveMoney(Account from_acc, Account to_acc);
	public boolean remove(Account account);
	
}
