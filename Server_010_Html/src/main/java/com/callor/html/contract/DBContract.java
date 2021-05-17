package com.callor.html.contract;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBContract {

	
	static Connection getDBConnection(PreparedStatement pStr) {
		
		Connection dbConn = null ;
		
		String dbDriver = "oracle.jdbc.OracleDriver";
		String username = "myfood";
		String password = "myfood";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		
		try {
			Class.forName(dbDriver);
			
			dbConn = DriverManager.getConnection(url, username, password);
			System.out.println("DB접속 성공");
			
		} catch (ClassNotFoundException e) {
			System.out.println("dbDriver 주소를 확인하세요.");
		} catch (SQLException e) {
			System.out.println("DB 접속정보를 확인하세요.");
			System.out.println("url : " + url );
			System.out.println("username : " + username);
			System.out.println("password : " + password );
			
		}
		
		return dbConn;
		
	}
	
	
}
