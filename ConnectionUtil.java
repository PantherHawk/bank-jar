package com.pennypincherbank.Bank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class ConnectionUtil {

	public static Connection getConnection() throws SQLException {
		String url = "jdbc:oracle:thin:@revature.cvmfh0fqyy8w.us-east-2.rds.amazonaws.com:1521:ORCL";
		String user = "root";
		String pass= "garyRosenthal12";
		
		return DriverManager.getConnection(url, user, pass);
	}
}
