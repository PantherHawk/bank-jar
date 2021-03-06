package com.pennypincherbank.Bank;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//import com.revature.goodbye.ConnectionUtil;

import org.apache.log4j.Logger;

public class UserDaoImp implements UserDao {
	final static Logger log = Logger.getLogger(UserDaoImp.class);

	private static UserDaoImp userDaoImp;
//	final static Logger logger = Logger.getLogger(UserDaoImp.class);
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
			log.error("Erred with  -----> " + e);
			e.printStackTrace();
		} catch (ClassNotFoundException e1) {
			log.error("Erred with  -----> " + e1);
			e1.printStackTrace();
		}
		return false;
	}
	
	@Override
	public boolean insert(User user) {
		int statementIndex = 0;
		try (Connection connect = ConnectionUtil.getConnection()) {
//			read from db
			String sql = "CALL ADD_USER (0, 0, ?, ?, ?, ?)";
			CallableStatement cs = connect.prepareCall(sql);
			cs.setString(++statementIndex, user.getFirstname());
			cs.setString(++statementIndex, user.getLastname());
			cs.setString(++statementIndex, user.getUsername());
			cs.setString(++statementIndex, user.getPasshash());
			int i = cs.executeUpdate();
			if (i > 0) {
				log.info("success: " + user.getUsername() + " added user to db.");
				return true;
			}
		} catch(SQLException e) {
			log.error("Erred with  -----> " + e);
			e.printStackTrace();
		} catch (ClassNotFoundException e1) {
			log.error("Erred with  -----> " + e1);
			e1.printStackTrace();
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
			int statementIndex = 0;
			String sql = "CALL ADD_USER (0, 0, ?, ?, ?, ?)";
			CallableStatement cs = connect.prepareCall(sql);
			cs.setString(++statementIndex, user.getFirstname());
			cs.setString(++statementIndex, user.getLastname());
			cs.setString(++statementIndex, user.getUsername());
			cs.setString(++statementIndex, user.getPasshash());
			
			int i = cs.executeUpdate();
//			System.out.println("what is i in userdaoim?" + i);
			if (i == 0) {
				return true;
			}
		} catch(SQLException | ClassNotFoundException e) {
			log.error("Erred with  -----> " + e);
			e.printStackTrace();
		}	return false;
	}

	@Override
	public User select(User user) {
		try(Connection conn = ConnectionUtil.getConnection()) {
			int statementIndx = 0;
//			System.out.println(user.toString());
			log.info("Preparing DQL ...");
			String sql = "SELECT * FROM USERS WHERE USERNAME = ?";
			
			PreparedStatement p = conn.prepareStatement(sql);
			p.setString(++statementIndx, user.getUsername());
//			System.out.println("username is " + user.getUsername());
			ResultSet result = p.executeQuery();
//			System.out.println("result from select query: " + result.getString(1));
			while (result.next()) {
//				logger.info("from the logger...");
				return new User(
						result.getInt("USER_ID"),
						result.getInt("ROLE_ID"),
						result.getInt("PENDING"),
						result.getString("USERNAME"),
						result.getString("PASSWORD"),
						result.getString("FIRST_NAME"),
						result.getString("LAST_NAME")
						);
			}
		} catch(SQLException | ClassNotFoundException e) {
			log.error("Erred with  -----> " + e);
			e.printStackTrace();
		}
		return new User();
	}

	@Override
	public List<User> selectAll() {
		try(Connection conn = ConnectionUtil.getConnection()) {
			log.info("Preparing DQL statement ...");
			String sql = "SELECT * FROM USERS";
			PreparedStatement p = conn.prepareStatement(sql);
			ResultSet result = p.executeQuery();
			
			List<User> userList = new ArrayList<>();
			while(result.next()) {
//				System.out.println("Building list of users.");
				userList.add(new User(
						result.getInt("USER_ID"),
						result.getInt("ROLE_ID"),
						result.getInt("PENDING"),
						result.getString("USERNAME"),
						result.getString("PASSWORD"),
						result.getString("FIRST_NAME"),
						result.getString("LAST_NAME")
						));
			}
			return userList;
		} catch(SQLException | ClassNotFoundException e) {
			e.printStackTrace() ;
		}
		return new ArrayList<>();
	}
	
	public boolean verifyUser(User user) {
		try (Connection conn = ConnectionUtil.getConnection()) {
			int statementIndex = 0;
			String updateSql = "UPDATE USERS SET PENDING = 1 WHERE USERNAME = ?";
			PreparedStatement ps = conn.prepareStatement(updateSql);
			ps.setString(++statementIndex, user.getUsername());
			if (ps.executeUpdate() > 0) {
				return true;
			}
			System.out.println(user.getFirstname() + " " + user.getLastname() + " updated and verified.");
		} catch (SQLException | ClassNotFoundException e) {
			log.error("Erred with ------->" + e);
			e.printStackTrace();
		}
	
		return false;
	}

	@Override
	public String getUserHash(User user) {
		try(Connection conn = ConnectionUtil.getConnection()) {
			int statementIndx = 0;
			String command = "SELECT GET_CUSTOMER_HASH(?, ?) AS HASH FROM DUAL"; // WHY CALL THIS COMMAND AND NOT SQL ?
			PreparedStatement p = conn.prepareStatement(command);
			p.setString(++statementIndx, user.getUsername());
			p.setString(++statementIndx, user.getPasshash());
			
			ResultSet result = p.executeQuery();
			
			if (result.next()) {
//				System.out.println("Result is -------> " + result.getString(1));
				return result.getString(1);
			}
		} catch (SQLException | ClassNotFoundException e) {
			log.error("Erred with ------->" + e);
			e.printStackTrace();
		}
	return new String();
  }
	
}
