package com.pennypincherbank.Bank;

import java.util.HashMap;
import java.util.Map;

public class Bank {
	private String bankName = "Barracuda Blockchain Bank";
//	make a dictionary to store the clients' accounts.
	private Terminal atm;
	private int balance = 0;
	private int transactionCount;
	private Map<Long, BankAccount> accountList;
	
	
	private static final String BANKER_COMMANDS = 
			"Banker	 commands: " +
					"exit, open, customer, report, help";
	
//	what the customer can ask of the bank
	
	private static final String CUSTOMER_TRANSACTIONS = 
			"	Customer transactions: " +
					"deposit, withdraw, transfer, balance, quit, help.";
	
	public Bank ( String name, Terminal atm ) {
		this.bankName = name;
		this.atm = new Terminal();
		
	}
	
	public void visit() {
		instructUser();
		
		String command;
		while(!(command = 
				atm.readWord("banker command:	")).equals("exit")) {
			if (command.startsWith( "h" )) {
				help( BANKER_COMMANDS );
			} else if ( command.startsWith( "o" )) {
				openNewAccount();
			} else if (command.startsWith( "r" )) {
				report();
			} else if (command.startsWith( "c" )) {
				BankAccount acct = whichAccount();
				if ( acct != null ) {
					processTransactionsForAccount( acct );
				}
			} else {
//				handle unknown command
				atm.println( "sorry, " + command + " unavailable." );
			}
		}
		report();
		atm.println( "Thank you for visiting " + bankName + ". Come again.");
	}
	
//	Open a new bank account,
//	prompting the user for information.
	
	private void openNewAccount() {
		String accountName = atm.readWord( "Account name: " );
		try {
			int startup = readPosAmt( "Initial deposit: " );
			BankAccount newAccount;
			accountList.put( accountName, newAccount );
			atm.println( "opened new account, " + accountName + " with $" + startup);	
		} catch( NegativeAmountException ne ) {
			atm.errPrintln( "You can't open an account with a negative balance." );
		} catch( InsufficientFundsExceptions e) {
			atm.errPrintln( "Initial depsoit doesn't cover fee." );
		}
	}
	
	private void processTransactionsForAccount( BankAccount acct ) {
		help( CUSTOMER_TRANSACTIONS );
		
		String transaction;
		
		while(!(transaction = atm.readWord("	transaction: ")).equals("quit")) {
			try {	
				if ( transaction.startsWith( "h" ) ) {
					help( CUSTOMER_TRANSACTIONS );
				} else if ( transaction.startsWith( "d" ) ) {
					int amount = readPosAmt("	deposited " + acct.deposit( amount ));
				} else if ( transaction.startsWith( "w" ) ) {
					int amount = readPosAmt(" 	withdrew " + acct.withdraw( amount ));
				} else if ( transaction.startsWith( "b" ) ) {
					atm.println( "	current balance " + acct.getBalance());
				} 
			} catch(InsufficientFundsException ie) {
				atm.errPrintln( "	Insufficient funds " + ie.getMessag() );
			} catch(NegativeAmountException ne) {
				atm.errPrintln("	Can't handle negative amounts.");
				
			}
			atm.println();
		} 
	}
}
