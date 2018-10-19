package com.pennypincherbank.Bank;

import java.util.List;

public interface UserDao {

	public boolean insert(User user);
	public boolean update(User user);
	public boolean insertProcedure(User user);
	public User select(User user);
	public List<User> selectAll();
	public String getCustomerHash(User user);
}
