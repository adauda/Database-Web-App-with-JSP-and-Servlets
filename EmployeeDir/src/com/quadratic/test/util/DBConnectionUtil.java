package com.quadratic.test.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnectionUtil {
	
	//define the database properties
	private static final String username = "quadri";
	private static final String password = "quadri";
	private static final String url = "jdbc:mysql://localhost:3306/employeeD";
	private static final String driver = "com.mysql.jdbc.Driver";
	
	private static Connection connection = null;
	
	//define static method
	public static Connection openConnection() {
		
		//check the connection
		if(connection != null) {
			return connection;
		}else {
			
			try {
				
				//load the driver
				Class.forName(driver);
				
				//get connection
				connection = DriverManager.getConnection(url, username, password);
				
			}catch(Exception e) {
				e.printStackTrace();
			}
			return connection;
		}
	}

}
