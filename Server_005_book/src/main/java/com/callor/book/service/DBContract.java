package com.callor.book.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBContract {

	
	/*
	싱글톤(Single Tone) 패턴
	
	프로젝트를 수행하는데 필요한 자원(Resource)를
	만드는 방법으로
	같은 자원을 계속해서 만들지 않고 
	한 번만 만들어 두고 여러곳에서 활용하는 방법
	 */
	
	
	
	private static Connection dbConn ;
	
	/*
	static 자동 생성자
	
	프로젝트가 Run되면 무조건 코드가 실행되는 method
	
	*/
	static {
		String jdbcDriver = "oracle.jdbc.OracleDriver" ;
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String username = "bookuser";
		String password = "bookuser";
		
		try {
			Class.forName(jdbcDriver);
			dbConn = DriverManager.getConnection(url, username, password);
			
			System.out.println("접속완료");
			
			
		} catch (ClassNotFoundException e) {
			System.out.println("접속 Driver 없음");
			
		} catch (SQLException e) {
			System.out.println("Oracle DB 연결 오류");
		}
	} //end static
	
	
	public static Connection getDBConnection() {
		
		return dbConn;
		
	}
	
	
	
	
	
	
}
