package com.pcwk.ehr.cmn;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JDBCUtil {
	
	public static void close(PreparedStatement pstmt, Connection conn) {
		
		if (null != pstmt) {   // PreparedStatement
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} // if

		if (null != conn) {   // Connection
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} // if
		
	} // close()

} // class end
