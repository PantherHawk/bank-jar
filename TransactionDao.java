package com.pennypincherbank.Bank;

import java.time.LocalDateTime;
import java.util.List;

public interface TransactionDao {

	public List<Transaction> fetchAllBelongingToUser(int user_id);
	public List<Transaction> fetchAllForDate(int user_id, LocalDateTime time);
	public Transaction fetchLatest();
	public List<Transaction> fetchAllDepositsOrWithdrawals(int user_id, String txType);
	public List<Transaction> fetchAll();
}
