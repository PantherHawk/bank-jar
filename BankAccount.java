package com.pennypincherbank.Bank;

public class BankAccount {
//	make an integer because we're dealing only with dollars
	private int balance;
	
//	BIP32 access code;
	private int password;
	
	/**
	 * A constructor for creating a bank account
	 * @param initialBalance the opening balance.
	 * */
	
	public BankAccount( int initialBalance ) {
		this.deposit( initialBalance );
	}
	
	/** 
	 * Deposit the amount requested
	 * 
	 * @param amount to be deposited
	 * */
	public String deposit( int deposit ) {
		balance = balance + deposit;
		return Integer.toString(deposit);
	}
	
	/**
	 * Withdraw the amount requested.
	 * 
	 * @param amount to be withdrawn
	 * 
	 * **/
	public String withdraw( int withdrawal ) {
		balance = balance - withdrawal;
		return Integer.toString(withdrawal);
	}
	
	/**
	 * The current account balance
	 * @return the current balance
	 * */
	public int getBalance() {
		return balance;
	}
}
