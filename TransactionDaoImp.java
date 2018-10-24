package com.pennypincherbank.Bank;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TransactionDaoImp implements TransactionDao {

//	set up singleton
	private static TransactionDaoImp transactionDaoImp;
	
	private TransactionDaoImp() {
		
	}
	
	public static TransactionDaoImp getInstance() {
		if (transactionDaoImp == null) {
			return new TransactionDaoImp();
		}
		return transactionDaoImp;
	}
	
	@Override
	public List<Transaction> fetchAll() {

		try (Connection connect = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM TRANSACTIONS";
			PreparedStatement stmt = connect.prepareStatement(sql);
			ResultSet results = stmt.executeQuery();
			List<Transaction> transactionList = new ArrayList<>();
			System.out.println("results of get all tx: " + transactionList.toString());
			while (results.next()) {
				transactionList.add(new Transaction(
						results.getInt("ID"),
						results.getInt("USER_ID"),
						results.getInt("ACCOUNT_ID"),
						results.getTimestamp("TIME_STAMP"),
						results.getString("TYPE"),
						results.getInt("AMOUNT")
						));
			}
			return transactionList;
		} catch(SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return new ArrayList<Transaction>();
	}
	public List<Transaction> fetchAllBelongingToUser(int user_id) {
		int statementIndex = 0;
		try (Connection connect = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM TRANSACTIONS WHERE USER_ID = ?";
			PreparedStatement stmt = connect.prepareStatement(sql);
			stmt.setInt(++statementIndex, user_id);
			ResultSet results = stmt.executeQuery();
			List<Transaction> transactionList = new ArrayList<>();
			System.out.println("results of get all tx: " + transactionList.toString());
			while (results.next()) {
				transactionList.add(new Transaction(
						results.getInt("ID"),
						results.getInt("USER_ID"),
						results.getInt("ACCOUNT_ID"),
						results.getTimestamp("TIME_STAMP"),
						results.getString("TYPE"),
						results.getInt("AMOUNT")
						));
			}
			return transactionList;
		} catch(SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return new ArrayList<Transaction>();
	}

	@Override
	public List<Transaction> fetchAllForDate(int user_id, LocalDateTime time) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Transaction fetchLatest() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Transaction> fetchAllDepositsOrWithdrawals(int user_id, String txType) {
		
		int statementIndex = 0;
		try (Connection connect = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM TRANSACTIONS WHERE USER_ID = ? AND TYPE = ?";
			PreparedStatement stmt = connect.prepareStatement(sql);
			stmt.setInt(++statementIndex, user_id);
			stmt.setString(++statementIndex, txType.toUpperCase());
			ResultSet results = stmt.executeQuery();
			List<Transaction> transactionList = new ArrayList<>();
			System.out.println("results of get all tx: " + transactionList.toString());
			while (results.next()) {
				transactionList.add(new Transaction(
						results.getInt("ID"),
						results.getInt("USER_ID"),
						results.getInt("ACCOUNT_ID"),
						results.getTimestamp("TIME_STAMP"),
						results.getString("TYPE"),
						results.getInt("AMOUNT")
						));
			}
			return transactionList;
		} catch(SQLException | ClassNotFoundException e) {
//			TODO: logging
			e.printStackTrace();
		}
		return new ArrayList<Transaction>();
	}
	
}
