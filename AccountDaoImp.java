package com.pennypincherbank.Bank;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
//import java.util.Currency;

public class AccountDaoImp implements AccountDao {
	
	private static AccountDaoImp accountDaoImp;
//	final static Logger logger = Logger.getLogger(UserDaoImp.class);
//	set up the singleton
	public static AccountDaoImp getInstance() {
		if (accountDaoImp == null) {
			accountDaoImp = new AccountDaoImp();
		}
		return accountDaoImp;
	}
	
	private AccountDaoImp() {
		
	}

	@Override
	public boolean withdraw(Account account, int amount) {
		try (Connection conn = ConnectionUtil.getConnection()) {
			int statementIndex = 0;
			
			String sql = "UPDATE ACCOUNT SET BALANCE = BALANCE - ? WHERE USER_ID = ? AND ID = ?";
			
			PreparedStatement stmt  = conn.prepareStatement(sql);
			stmt.setInt(++statementIndex, amount);
			stmt.setInt(++statementIndex, account.getUser_id());
			stmt.setInt(++statementIndex, account.getId());
//			System.out.println(" what does stmt.executeUpdate return? " + stmt.executeUpdate());
			int rowUpdated = stmt.executeUpdate();
			if (rowUpdated > 0) {
				return true;
			}
			
		} catch(SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean deposit(Account account, int amount) {
		try (Connection conn = ConnectionUtil.getConnection()) {
			int statementIndex = 0;
			
			String sql = "UPDATE ACCOUNT SET BALANCE = BALANCE + ? WHERE USER_ID = ? AND ID = ?";
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(++statementIndex, amount);
			stmt.setInt(++statementIndex, account.getUser_id());
			stmt.setInt(++statementIndex, account.getId());
			int rowUpdated = stmt.executeUpdate();
			if (rowUpdated > 0) {
				return true;
			}
			
		} catch(SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean moveMoney(Account from_acc, Account to_acc) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean remove(Account account) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addAccount(Account account) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Account> selectAll(User user) {
		try (Connection connect = ConnectionUtil.getConnection()) {
			int statementIndex = 0;
			String sql = "SELECT * FROM ACCOUNT WHERE USER_ID = ?";
			PreparedStatement ps = connect.prepareStatement(sql);
			System.out.println("user id: " + user.getId());
			ps.setInt(++statementIndex, user.getId());
			ResultSet result = ps.executeQuery();
			System.out.println("result set looks like    " + result
					);
			List<Account> accounts = new ArrayList<Account>();
			while (result.next()) {
				accounts.add(new Account(
						result.getInt("ID"),
						result.getInt("BALANCE"),
						result.getInt("USER_ID"),
						result.getString("ACCOUNT_TYPE")
						));
			}
			return accounts;
		} catch(SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

}
