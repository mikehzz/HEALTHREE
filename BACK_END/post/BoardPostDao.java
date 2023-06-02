package com.pcwk.ehr.post;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.pcwk.ehr.cmn.ConnectionMaker;
import com.pcwk.ehr.cmn.DTO;
import com.pcwk.ehr.cmn.JDBCUtil;
import com.pcwk.ehr.cmn.PostDTO;
import com.pcwk.ehr.cmn.WorkDiv;
import com.pcwk.ehr.post.board.dao.BoardDTO;
import com.pcwk.ehr.task.dao.TaskDTO;

public class BoardPostDao implements WorkDiv<BoardPostDTO> {
	@Override
	//리스트
	public ArrayList<BoardPostDTO> doRetrieve(PostDTO search) {
		ArrayList<BoardPostDTO> list = new ArrayList<>();
		
		BoardPostDTO param =(BoardPostDTO) search;
		Connection conn = ConnectionMaker.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		StringBuilder sb = new StringBuilder(1000);
		
		sb.append("SELECT                                              \n");
		sb.append("    tt1.rnum no,                                    \n");
		sb.append("	tt1.p_seq,                                         \n");
		sb.append("    tt1.m_id,                                       \n");
		sb.append("    tt1.p_title,                                    \n");
		sb.append("    tt1.p_read_cnt,                                 \n");
		sb.append("    tt1.p_atch_file_id,                             \n");
		sb.append("    tt1.p_div,                                      \n");
		sb.append("    tt1.p_contents,                                 \n");
		sb.append("    tt1.TO_CHAR(p_reg_dt,'YYYY-MM-DD HH24:MI:SS'),  \n");
		sb.append("    tt1.TO_CHAR(p_mod_dt,'YYYY-MM-DD HH24:MI:SS'),  \n");
		sb.append("    tt1.p_like_cnt                                  \n");
		sb.append("                                                    \n");
		sb.append("FROM(                                               \n");
		sb.append("      SELECT ROWNUM rnum,t1.*                       \n");
		sb.append("      FROM(                                         \n");
		sb.append("        SELECT *                                    \n");
		sb.append("        FROM post                                   \n");
		sb.append("        --WHERE                                     \n");
		sb.append("        ORDER BY p_reg_dt DESC                      \n");
		sb.append("    )t1                                             \n");
		sb.append("    WHERE ROWNUM <=10                               \n");
		sb.append(")tt1                                                \n");
		sb.append("WHERE rnum >=1                                      \n");
		
		LOG.debug("1. sql:\n" + sb.toString());
		LOG.debug("2.conn:" + conn);
		LOG.debug("3.param:" + param);
		
		try {
			pstmt = conn.prepareStatement(sb.toString());
			LOG.debug("4.pstmt:" + pstmt);
			
			rs = pstmt.executeQuery();
			while (rs.next()) {
				BoardPostDTO outVO = new BoardPostDTO();
				
				outVO.setNo(rs.getInt("no"));
				outVO.setSeq;(rs.getString("p_seq"));
				outVO.set(rs.getString("m_id"));
				outVO.set(rs.getString("p_title"));
				outVO.set(rs.getInt("p_read_cnt"));
				outVO.set(rs.getString("p_atch_file_id"));
				outVO.set(rs.getInt("p_div"));
				outVO.set(rs.getString("p_contents "));
				outVO.set(rs.getString("p_reg_dt"));
				outVO.set(rs.getString("p_mod_dt"));
				outVO.set(rs.getInt("p_like_cnt"));
				
				LOG.debug("outVO:" + outVO);
				list.add(outVO);
			}
			} catch (SQLException e) {
			LOG.debug("-----------------");
			LOG.debug("-SQLException-" + e.getMessage());
			LOG.debug("-----------------");
		}  finally {
			JDBCUtil.close(rs, pstmt, conn);
		}
		
		
		return list;
	}

//------------------------------------------------------------------------

	@Override
	public int doSave(BoardPostDTO param) {
		// TODO Auto-generated method stub
		return 0;
	}
	
//------------------------------------------------------------------------

	@Override
	public int doUpdate(BoardPostDTO param) {
		// TODO Auto-generated method stub
		return 0;
	}

//------------------------------------------------------------------------

	@Override
	public int doDelete(BoardPostDTO param) {
		// TODO Auto-generated method stub
		return 0;
	}
	
//------------------------------------------------------------------------

	@Override
	public BoardPostDTO doSelectOne(BoardPostDTO param) {
		// TODO Auto-generated method stub
		return null;
	}

}
