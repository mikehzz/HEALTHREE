package com.pcwk.ehr.cmn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionMaker implements PcwkLog {
	/*
	 * 1. DriverManager : 데이터베이스와 연결을 생성하는 클래스 
	 * 2. Connection : 데이터베이스와 연결을 나타내는 인터페이스
	 * 3. Statement : SQL문을 실행하는 인터페이스
	 * 4. ResultSet : SQL문의 결과를 저장하고 조회하는 인터페이스
	 * 5. 연결 종료
	 */
	
	// jdbc:oracle:thin:@IP:PORT:전역DB명칭(SID)
	// Class.forName("oracle.jdbc.driver.OracleDriver");
	final static String DB_URL = "jdbc:oracle:thin:@192.168.0.123:1521:xe";
	final static String DB_USER = "healthree";
	final static String DB_PASSWORD = "1818";
	final static String DB_DRIVER = "oracle.jdbc.driver.OracleDriver";
	
	// DB 연결 정보 return
	public static Connection getConnection() {
		Connection conn = null;
		try {
			LOG.debug("1");
			Class.forName(DB_DRIVER);
			LOG.debug("2");

			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			LOG.debug("3");
			LOG.debug("3.1.conn :" + conn);
		} catch (ClassNotFoundException e) {
			LOG.debug("-----------------");
			LOG.debug("-ClassNotFoundException-" + e.getMessage());
			LOG.debug("-----------------");
		} catch (SQLException e) {
			LOG.debug("-----------------");
			LOG.debug("-SQLException-" + e.getMessage());
			LOG.debug("-----------------");
		}

		return conn;
		
	} // getConnection()
	
} // class end
