package com.pennypincherbank.Bank;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
//import java.util.Currency;

public class AccountDaoImp implements AccountDao {

	@Override
	public boolean withdraw(Account account, int amount) {
		try (Connection conn = ConnectionUtil.getConnection()) {
			int statementIndex = 0;
			
			String sql = "UPDATE user_accounts SET BALANCE=BALANCE - ? WHERE USER_ID= ?";
			
			CallableStatement cs = conn.prepareCall(sql);
			cs.setInt(++statementIndex, amount);
			cs.setInt(++statementIndex, account.getUser_id());
			
			if (cs.execute()) {
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
			
			String sql = "UPDATE user_accounts SET BALANCE=BALANCE + ? WHERE USER_ID= ?";
			
			CallableStatement cs = conn.prepareCall(sql);
			cs.setInt(++statementIndex, amount);
			cs.setInt(++statementIndex, account.getUser_id());
			
			if (cs.execute()) {
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
	public Account select(Account account) {
		// TODO Auto-generated method stub
		return null;
	}

}
