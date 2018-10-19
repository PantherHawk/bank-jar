package com.pennypincherbank.Bank;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

public class UserDaoImp implements UserDao {
	
	private static UserDaoImp userDaoImp;
	final static Logger logger = Logger.getLogger(UserDaoImp.class);
//	set up the singleton
	public static UserDaoImp getInstance() {
		if (userDaoImp == null) {
			userDaoImp = new UserDaoImp();
		}
		return userDaoImp;
	}
	
	private UserDaoImp() {
		
	}
	
	public boolean helloWorldProcedure() {
		try (Connection connect = ConnectionUtil.getConnection()) {
			String storedProc = "CALL add_monkey(?, ?)";
			int statementIndex = 0;
			String firstName = "test";
			String lastName = "test";
			CallableStatement cs = connect.prepareCall(storedProc);
			
			cs.setString(++statementIndex, firstName.toUpperCase());
			cs.setString(++statementIndex, lastName.toUpperCase());
			if (cs.execute()) {
				return true;
			}
		} catch(SQLException e) {
			logger.debug("(╯°□°）╯︵ ┻━┻\r\n" + 
					": ", e);
		}
		return false;
	}
	
	@Override
	public boolean insert(User user) {
		int statementIndex;
		try (Connection connect = ConnectionUtil.getConnection()) {
//			read from db
			String sql = "INSERT INTO USERS VALUES (1, 'test', 'test', 1, 1)";
			
		} catch(SQLException e) {
			logger.debug(e);
		}
		return false;
	}

	@Override
	public boolean update(User user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean insertProcedure(User user) {
		try (Connection connect = ConnectionUtil.getConnection()) {
			String storedProc = "CALL add_user (?, ?)";
			int statementIndex = 0;
			String firstName = "test";
			String lastName = "test";
			CallableStatement cs = connect.prepareCall(storedProc);
			
			cs.setString(++statementIndex, user.getName().toUpperCase());
			cs.setString(++statementIndex, user.getPasshash().toUpperCase());
			if (cs.execute()) {
				return true;
			}
		} catch(SQLException e) {
			logger.debug("(╯°□°）╯︵ ┻━┻\r\n" + 
					": ", e);
		}	return false;
	}

	@Override
	public User select(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getCustomerHash(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
