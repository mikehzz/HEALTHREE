package com.pcwk.ehr.board.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.pcwk.ehr.cmn.DTO;
import com.pcwk.ehr.cmn.WorkDiv;

public class FoodDAO implements WorkDiv<FoodDTO> {
	/*
	 * 1. DriverManager: 데이터베이스와 연결을 생성하는 클래스 2. Connection: 데이터베이스와 연결을 나타내는 인터페이스
	 * 3. Statement: SQL문을 실행하는 인터페이스 입니다. 4. ResultSet: SQL문의 결과를 저장하고 조회하는 인터페이스
	 * 5. 연결종료
	 * 
	 */
	// jdbc:oracle:thin:@IP:PORT:전역DB명칭(SID)
	// Class.forName("oracle.jdbc.driver.OracleDriver");
	final static String DB_URL = "jdbc:oracle:thin:@192.168.0.123:1521:xe";
	final static String DB_USER = "healthree";
	final static String DB_PASSWORD = "1818";
	final static String DB_DRIVER = "oracle.jdbc.driver.OracleDriver";

	// DB연결정보 return
	public Connection getConnection() {
		Connection conn = null;
		try {
			LOG.debug("1");
			Class.forName(DB_DRIVER);
			LOG.debug("2");

			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			LOG.debug("3");
			LOG.debug("3.1.conn:" + conn);
		} catch (ClassNotFoundException e) {
			LOG.debug("-----------------");
			LOG.debug("-ClassNotFoundExceptione-" + e.getMessage());
			LOG.debug("-----------------");
		} catch (SQLException e) {
			LOG.debug("-----------------");
			LOG.debug("-SQLException-" + e.getMessage());
			LOG.debug("-----------------");
		}

		return conn;
	}

//	public int doTotalCount(FoodDTO param) {
//		int totalCount = 0;
//
//		Connection conn = getConnection();
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//		StringBuilder sbWhere = new StringBuilder(200);// 검색
//
//		// 제목+내용
//		if (5 == param.getSearchDiv()) {
//			sbWhere.append(" WHERE title    like ?||'%'                     \n");
//			sbWhere.append(" OR    contents like ?||'%'                     \n");
//			// 제목
//		} else if (10 == param.getSearchDiv()) {
//			sbWhere.append(" WHERE title like ?||'%'                        \n");
//			// 내용
//		} else if (20 == param.getSearchDiv()) {
//			sbWhere.append(" WHERE contents like ?||'%'                     \n");
//			// 글쓴이
//		} else if (30 == param.getSearchDiv()) {
//			sbWhere.append(" WHERE reg_id like ?||'%'                     \n");
//		}
//
//		StringBuilder sb = new StringBuilder(1000);
//		sb.append(" SELECT COUNT(*) total_cnt  \n");
//		sb.append(" FROM board                 \n");
//		// 검색 ---------------------------------------
//		sb.append(sbWhere.toString());
//		// -------------------------------------------
//		try {
//			LOG.debug("1. sql:\n" + sb.toString());
//			LOG.debug("2.conn:" + conn);
//			LOG.debug("3.param:" + param);
//			
//			pstmt = conn.prepareStatement(sb.toString());
//			LOG.debug("4.pstmt:" + pstmt);
//			
//			//param
//			if (10 == param.getSearchDiv() || 20 == param.getSearchDiv() || 30 == param.getSearchDiv()) {
//				pstmt.setString(1, param.getSearchWord());
//			
//			// 제목+내용	
//			}else if (5 == param.getSearchDiv()) {
//				pstmt.setString(1, param.getSearchWord());
//				pstmt.setString(2, param.getSearchWord());				
//				
//			}
//			
//			rs = pstmt.executeQuery();// 조회 결과 ResultSet
//			
//			if(rs.next()) {
//				
//				totalCount = rs.getInt(1);
//				
//			}
//			LOG.debug("totalCount:"+totalCount);
//			
//		} catch (SQLException e) {
//			LOG.debug("-----------------");
//			LOG.debug("-SQLException-" + e.getMessage());
//			LOG.debug("-----------------");
//		} finally {
//
//			if (null != rs) {
//				try {
//					rs.close();
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}
//			}
//
//			if (null != pstmt) {// PreparedStatement
//				try {
//					pstmt.close();
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}
//			}
//
//			if (null != conn) { // Connection
//				try {
//					conn.close();
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}
//			}
//		}
//
//		return totalCount;
//	}

//	public int doReadCnt(FoodDTO param) {
//		int flag = 0; // 저장 건수 check
//		Connection conn = getConnection();
//		PreparedStatement pstmt = null;// SQL+ DATA
//		StringBuilder sb = new StringBuilder(500);
//		sb.append(" UPDATE board                       \n");
//		sb.append(" SET read_cnt = NVL(read_cnt,0) +1  \n");
//		sb.append(" WHERE seq = ?                      \n");
//
//		try {
//			LOG.debug("1. sql:\n" + sb.toString());
//			LOG.debug("2.conn:" + conn);
//			LOG.debug("3.param:" + param);
//
//			pstmt = conn.prepareStatement(sb.toString());
//			LOG.debug("4.pstmt:" + pstmt);
//			pstmt.setString(1, param.getSeq()); // seq
//
//			// DML 실행
//			flag = pstmt.executeUpdate();
//			LOG.debug("5.flag:" + flag);
//
//		} catch (SQLException e) {
//			LOG.debug("-----------------");
//			LOG.debug("-SQLException-" + e.getMessage());
//			LOG.debug("-----------------");
//		} finally {
//			if (null != pstmt) {// PreparedStatement
//				try {
//					pstmt.close();
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}
//			}
//
//			if (null != conn) { // Connection
//				try {
//					conn.close();
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}
//			}
//
//		}
//
//		return flag;
//	}

	@Override
	public int doSave(FoodDTO param) {
		int flag = 0; // 저장 건수 check
		Connection conn = getConnection();
		PreparedStatement pstmt = null;// SQL+ DATA
		StringBuilder sb = new StringBuilder(500);

		sb.append(" INSERT INTO food (  \n");
		sb.append("     f_code,         \n");
		sb.append("     f_name,         \n");
		sb.append("     f_size,         \n");
		sb.append("     f_cal,          \n");
		sb.append("     f_carbo,        \n");
		sb.append("     f_protein,      \n");
		sb.append("     f_fat,          \n");
		sb.append("     f_sugar,        \n");
		sb.append("     f_na,           \n");
		sb.append("     f_cole          \n");
		sb.append(" ) VALUES (          \n");
		sb.append("     ?,              \n");
		sb.append("     ?,              \n");
		sb.append("     ?,              \n");
		sb.append("     ?,              \n");
		sb.append("     ?,              \n");
		sb.append("     ?,              \n");
		sb.append("     ?,              \n");
		sb.append("     ?,              \n");
		sb.append("     ?,              \n");
		sb.append("     ?               \n");
		sb.append(" )                   \n");
		
		
		LOG.debug("1. sql:\n" + sb.toString());
		LOG.debug("2.conn:" + conn);
		LOG.debug("3.param:" + param);

		try {
			pstmt = conn.prepareStatement(sb.toString());
			LOG.debug("4.pstmt:" + pstmt);
			// ?에 값 설정
			pstmt.setString(1, param.getF_code());
			pstmt.setString(2, param.getF_name());
			pstmt.setFloat(3, param.getF_size());
			pstmt.setFloat(4, param.getF_cal());
			pstmt.setFloat(5, param.getF_carbo());
			pstmt.setFloat(6, param.getF_protein());
			pstmt.setFloat(7, param.getF_fat());
			pstmt.setFloat(8, param.getF_sugar());
			pstmt.setFloat(9, param.getF_na());
			pstmt.setFloat(10, param.getF_cole()); 

			// DML 실행
			flag = pstmt.executeUpdate();
			LOG.debug("5.flag:" + flag);

		} catch (SQLException e) {
			LOG.debug("-----------------");
			LOG.debug("-SQLException-" + e.getMessage());
			LOG.debug("-----------------");
		} finally {
			if (null != pstmt) {// PreparedStatement
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			if (null != conn) { // Connection
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}
		return flag;
	}

	@Override
	public ArrayList<FoodDTO> doRetrieve(DTO search) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int doUpdate(FoodDTO param) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int doDelete(FoodDTO param) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public FoodDTO doSelectOne(FoodDTO param) {
		// TODO Auto-generated method stub
		return null;
	}

//	@Override
//	public ArrayList<FoodDTO> doRetrieve(DTO search) {
//		ArrayList<FoodDTO> list = new ArrayList<>();
//		FoodDTO param = (FoodDTO) search;// param
//
//		Connection conn = getConnection();
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//		StringBuilder sbWhere = new StringBuilder(200);// 검색
//
//		// 제목+내용
//		if (5 == param.getSearchDiv()) {
//			sbWhere.append(" WHERE title    like ?||'%'                     \n");
//			sbWhere.append(" OR    contents like ?||'%'                     \n");
//			// 제목
//		} else if (10 == param.getSearchDiv()) {
//			sbWhere.append(" WHERE title like ?||'%'                        \n");
//			// 내용
//		} else if (20 == param.getSearchDiv()) {
//			sbWhere.append(" WHERE contents like ?||'%'                     \n");
//			// 글쓴이
//		} else if (30 == param.getSearchDiv()) {
//			sbWhere.append(" WHERE reg_id like ?||'%'                     \n");
//		}
//
//		StringBuilder sb = new StringBuilder(1000);
//		sb.append(" SELECT TT1.rnum as no,                                  \n");
//		sb.append("        TT1.seq,                                         \n");
//		sb.append("        TT1.title,                                       \n");
//		sb.append("        TT1.mod_id,                                      \n");
//		sb.append("        TT1.read_cnt,                                    \n");
//		sb.append("        TO_CHAR(TT1.MOD_DT,'YY.MM.DD') mod_dt            \n");
//		sb.append(" FROM (                                                  \n");
//		sb.append("     SELECT ROWNUM rnum,T1.*                             \n");
//		sb.append("     FROM (                                              \n");
//		sb.append("         SELECT *                                        \n");
//		sb.append("         FROM board                                      \n");
//		sb.append("         --검색조건                                         \n");
//		// 검색 -------------------------------------------------------------------
//		sb.append(sbWhere.toString());
//		// -----------------------------------------------------------------------
//		sb.append("                                                         \n");
//		sb.append("         ORDER BY mod_dt DESC                            \n");
//		sb.append("     )T1                                                 \n");
//		// sb.append(" WHERE ROWNUM <= :page_size*(:page_no-1)+:page_size \n");
//		sb.append("     WHERE ROWNUM <= ? * ( ? - 1) + ?                    \n");
//		sb.append(" )TT1                                                    \n");
//		// sb.append(" WHERE rnum >= :page_size*(:page_no-1)+1 \n");
//		sb.append(" WHERE rnum >= ? * (? - 1) + 1                           \n");
//		try {
//			LOG.debug("1. sql:\n" + sb.toString());
//			LOG.debug("2.conn:" + conn);
//			LOG.debug("3.param:" + param);
//
//			pstmt = conn.prepareStatement(sb.toString());
//			LOG.debug("4.pstmt:" + pstmt);
//			// param set : :page_size, :page_no, :page_size,
//			// :page_size, :page_no
//
//			// param page_size: defalut는 10
//			if (param.getPageSize() == 0) {
//				param.setPageSize(10);
//			}
//
//			// param page_no: defalut는 1
//			if (param.getPageNo() == 0) {
//				param.setPageNo(1);
//			}
//
//			if (10 == param.getSearchDiv() || 20 == param.getSearchDiv() || 30 == param.getSearchDiv()) {
//				pstmt.setString(1, param.getSearchWord());
//				pstmt.setInt(2, param.getPageSize());
//				pstmt.setInt(3, param.getPageNo());
//				pstmt.setInt(4, param.getPageSize());
//				pstmt.setInt(5, param.getPageSize());
//				pstmt.setInt(6, param.getPageNo());
//				// 제목+내용
//			} else if (5 == param.getSearchDiv()) {
//				pstmt.setString(1, param.getSearchWord());
//				pstmt.setString(2, param.getSearchWord());
//				pstmt.setInt(3, param.getPageSize());
//				pstmt.setInt(4, param.getPageNo());
//				pstmt.setInt(5, param.getPageSize());
//				pstmt.setInt(6, param.getPageSize());
//				pstmt.setInt(7, param.getPageNo());
//			} else {
//				pstmt.setInt(1, param.getPageSize());
//				pstmt.setInt(2, param.getPageNo());
//				pstmt.setInt(3, param.getPageSize());
//				pstmt.setInt(4, param.getPageSize());
//				pstmt.setInt(5, param.getPageNo());
//			}
//
//			rs = pstmt.executeQuery();// 조회 결과 ResultSet
//			while (rs.next()) {
//				FoodDTO outVO = new FoodDTO();
//
//				outVO.setNo(rs.getInt("no"));
//				outVO.setSeq(rs.getString("seq"));
//				outVO.setTitle(rs.getString("title"));
//				outVO.setModId(rs.getString("mod_id"));
//				outVO.setReadCnt(rs.getInt("read_cnt"));
//				outVO.setModDt(rs.getString("mod_dt"));
//				LOG.debug(outVO);
//				list.add(outVO);
//			}
//			LOG.debug("------------------------");
//
//		} catch (SQLException e) {
//			LOG.debug("-----------------");
//			LOG.debug("-SQLException-" + e.getMessage());
//			LOG.debug("-----------------");
//		} finally {
//
//			if (null != rs) {
//				try {
//					rs.close();
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}
//			}
//
//			if (null != pstmt) {// PreparedStatement
//				try {
//					pstmt.close();
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}
//			}
//
//			if (null != conn) { // Connection
//				try {
//					conn.close();
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}
//			}
//		}
//
//		return list;
//	}

//	@Override
//	public int doUpdate(FoodDTO param) {
//		int flag = 0;
//		Connection conn = getConnection();
//		PreparedStatement pstmt = null;// SQL+ DATA
//		StringBuilder sb = new StringBuilder(500);
//		sb.append(" UPDATE board            \n");
//		sb.append(" SET title    = ?,       \n");
//		sb.append("     contents = ?,       \n");
//		sb.append("     mod_dt   = SYSDATE, \n");
//		sb.append("     mod_id   = ?        \n");
//		sb.append(" WHERE seq = ?           \n");
//		try {
//			LOG.debug("1. sql:\n" + sb.toString());
//			LOG.debug("2.conn:" + conn);
//			LOG.debug("3.param:" + param);
//
//			pstmt = conn.prepareStatement(sb.toString());
//			LOG.debug("4.pstmt:" + pstmt);
//			// ?에 값 설정
//			pstmt.setString(1, param.getTitle());// title
//			pstmt.setString(2, param.getContents());// contents
//			pstmt.setString(3, param.getModId());
//			pstmt.setString(4, param.getSeq()); // seq
//
//			// DML 실행
//			flag = pstmt.executeUpdate();
//			LOG.debug("5.flag:" + flag);
//
//		} catch (SQLException e) {
//			LOG.debug("-----------------");
//			LOG.debug("-SQLException-" + e.getMessage());
//			LOG.debug("-----------------");
//		} finally {
//			if (null != pstmt) {// PreparedStatement
//				try {
//					pstmt.close();
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}
//			}
//
//			if (null != conn) { // Connection
//				try {
//					conn.close();
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}
//			}
//
//		}
//
//		return flag;
//	}

//	@Override
//	public int doDelete(FoodDTO param) {
//		int flag = 0; // 삭제 건수
//		Connection conn = getConnection();
//		PreparedStatement pstmt = null;// SQL + DATA
//		StringBuilder sb = new StringBuilder(200);
//		sb.append(" DELETE FROM board \n");
//		sb.append(" WHERE seq = ?     \n");
//
//		try {
//			LOG.debug("1. sql:\n" + sb.toString());
//			LOG.debug("2. conn:" + conn);
//			LOG.debug("3. param:" + param);
//
//			pstmt = conn.prepareStatement(sb.toString());
//			LOG.debug("4. pstmt:" + pstmt);
//
//			// param setting
//			pstmt.setString(1, param.getSeq());
//
//			// DML실행
//			flag = pstmt.executeUpdate();
//			LOG.debug("5. flag:" + flag);
//
//		} catch (SQLException e) {
//			LOG.debug("-----------------");
//			LOG.debug("-SQLException-" + e.getMessage());
//			LOG.debug("-----------------");
//		} finally {
//			if (null != pstmt) {
//				try {
//					pstmt.close();
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}
//			}
//
//			if (null != conn) {
//				try {
//					conn.close();
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}
//			}
//
//		}
//
//		return flag;
//	}

//	@Override
//	public FoodDTO doSelectOne(FoodDTO param) {
////		1. DriverManager: 데이터베이스와 연결을 생성하는 클래스											
////		2. Connection: 데이터베이스와 연결을 나타내는 인터페이스											
////		3. Statement: SQL문을 실행하는 인터페이스 입니다.											
////		4. ResultSet: SQL문의 결과를 저장하고 조회하는 인터페이스											
//
//		FoodDTO outVO = null;
//		Connection conn = getConnection();
//		PreparedStatement pstmt = null;// SQL+ DATA
//		ResultSet rs = null; // 쿼리 수행결과 return
//		StringBuilder sb = new StringBuilder(300);
//		sb.append(" SELECT                                               \n");
//		sb.append("     seq,                                             \n");
//		sb.append("     title,                                           \n");
//		sb.append("     contents,                                        \n");
//		sb.append("     read_cnt,                                        \n");
//		sb.append("     TO_CHAR(reg_dt,'YYYY-MM-DD HH24:MI:SS')  reg_dt, \n");
//		sb.append("     reg_id,                                          \n");
//		sb.append("     TO_CHAR(mod_dt,'YYYY-MM-DD HH24:MI:SS')  mod_dt, \n");
//		sb.append("     mod_id                                           \n");
//		sb.append(" FROM board                                           \n");
//		sb.append(" WHERE seq = ?                                        \n");
//
//		LOG.debug("1. sql:\n" + sb.toString());
//		LOG.debug("2.conn:" + conn);
//		LOG.debug("3.param:" + param);
//
//		try {
//			pstmt = conn.prepareStatement(sb.toString());
//			LOG.debug("4.pstmt:" + pstmt);
//			pstmt.setString(1, param.getSeq());
//
//			// SELECT 실행
//			rs = pstmt.executeQuery();
//			LOG.debug("5.rs:" + rs);
//
//			if (rs.next()) {
//				outVO = new BoardDTO();
//
//				outVO.setSeq(rs.getString("seq"));
//				outVO.setTitle(rs.getString("title"));
//				outVO.setContents(rs.getString("contents"));
//				outVO.setReadCnt(rs.getInt("read_cnt"));
//
//				outVO.setRegDt(rs.getString("reg_dt"));
//				outVO.setRegId(rs.getString("reg_id"));
//
//				outVO.setModDt(rs.getString("mod_dt"));
//				outVO.setModId(rs.getString("mod_id"));
//
//				LOG.debug("6.outVO:" + outVO);
//			}
//
//		} catch (SQLException e) {
//			LOG.debug("-----------------");
//			LOG.debug("-SQLException-" + e.getMessage());
//			LOG.debug("-----------------");
//		} finally {
//
//			if (null != rs) {
//				try {
//					rs.close();
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}
//			}
//
//			if (null != pstmt) {// PreparedStatement
//				try {
//					pstmt.close();
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}
//			}
//
//			if (null != conn) { // Connection
//				try {
//					conn.close();
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}
//			}
//
//		}
//
//		return outVO;
//	}
//
}
