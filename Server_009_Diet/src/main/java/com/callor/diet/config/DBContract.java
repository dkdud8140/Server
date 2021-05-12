package com.callor.diet.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBContract {
	
	private static Connection dbConn ;
	
	static {
		
		//DB 접속을 하기 위한 정보
		String jdbcDriver = "oracle.jdbc.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "myfood";
		String password = "myfood";
		
		//이 프로젝트에서 어딘가에서 이미 dbConn이 만들어져 있다면
		// 다시 만들지 않기 위한 코드
		if(dbConn == null) {
			try {
				Class.forName(jdbcDriver);
				dbConn = DriverManager.getConnection(url, user, password);
				System.out.println("오라클 접속 완료");
			} catch (ClassNotFoundException e) {
				System.out.println("ojdbc6.jar를 확인하세요.");
			} catch (SQLException e) {
				System.out.println("=".repeat(30));
				System.out.println("Driver = " + jdbcDriver);
				System.out.println("Url = " + url);
				System.out.println("User = " + user);
				System.out.println("Password = " + password);
				System.out.println("=".repeat(30));
				System.out.println("Oracle DBMS 접속 오류.");
				System.out.println("접속 정보를 확인하세요.");
			}
		} // if 종료
	} // static 종료

	// 미리 생성해준 dbConn 연결 객체를 가져갈 수 있도록 하는 통로
	public static Connection getdbConnection() {
		return dbConn;
	}
	
	
}
