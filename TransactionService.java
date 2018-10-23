package com.pennypincherbank.Bank;

import java.util.List;

public class TransactionService {
	
//	set up singleton
	private static TransactionService transactionService;
	public static TransactionService getInstance() {
		if (transactionService == null) {
			transactionService = new TransactionService();
		}
		return transactionService;
	}
	private TransactionService() {
		
	}
	
	public List<Transaction> getAllTxs() {
		return TransactionDaoImp.getInstance().fetchAll();
	}
	public List<Transaction> getTxsForUser(int user_id) {
		return TransactionDaoImp.getInstance().fetchAllBelongingToUser(user_id);
	}
	public List<Transaction> getTxsByType(int user_id, String withdrawOrDeposit) {
		return TransactionDaoImp.getInstance().fetchAllDepositsOrWithdrawals(user_id, withdrawOrDeposit);
	}
}
