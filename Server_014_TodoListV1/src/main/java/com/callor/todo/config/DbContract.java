package com.callor.todo.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbContract {

	static Connection dbConn ;
	
	static {
		
		String jdbcDriver = "com.mysql.cj.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/mydb";
		String user = "gbUser";
		String password = "12345";
		
		try {
			Class.forName(jdbcDriver);
			
			if(dbConn == null) {
				dbConn = DriverManager.getConnection(url, user, password);			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
	}
	
	public static Connection getDBConnection() {
		return dbConn ;
		
	}
	
}
