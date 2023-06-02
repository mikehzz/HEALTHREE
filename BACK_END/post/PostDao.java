package com.pcwk.ehr.post;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;
import org.springframework.jdbc.core.RowMapper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;


public class PostDao {

	private final Logger LOG = LogManager.getLogger(getClass());

	private DataSource dataSource;

	// Spring이 제공하는 jdbcTemplate
	private JdbcTemplate jdbcTemplate;

	// **** default 생성
	public PostDao() {
	}

	// *** applicationContext.xml에서 주입
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		// 수동으로 DI
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	
	
	// 게시글 등록 = 생성
	public int add(final PostVO post) throws ClassNotFoundException, SQLException {
		int flag = 0;// 등록 건수

		StringBuilder sb = new StringBuilder(200);
		sb.append(" INSERT INTO post (  \n");
		sb.append("     p_seq,          \n");
		sb.append("     m_id,           \n");
		sb.append("     p_title,        \n");
		sb.append("     p_read_cnt,     \n");
		sb.append("     p_atch_file_id, \n");
		sb.append("     p_div,          \n");
		sb.append("     p_contents,     \n");
		sb.append("     p_reg_dt,       \n");
		sb.append("     p_mod_dt,       \n");
		sb.append("     p_like_cnt      \n");
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

		LOG.debug("1. sql=\n" + sb.toString());
		LOG.debug("2. param=\n" + post.toString());

		// param
		Object[] args = { 
				post.getSeq(), 
				post.getId(), 
				post.getTitle(), 
				post.getReadCnt(), 
				post.getAtchFileId(),
				post.getDiv(), 
				post.getContents(), 
				post.getRegDt(), 
				post.getModDt(), 
				post.getLikeCnt()
				};

		// jdbcTemplate.update DML에 사용
		flag = jdbcTemplate.update(sb.toString(), args);
		LOG.debug("3. flag=" + flag);

		return flag;
	}//게시글 등록 END


	//게시글 수정	
	public int update(final PostVO post) throws ClassNotFoundException, SQLException {
		int flag = 0;	
		
	    	StringBuilder sb = new StringBuilder(200);
	    	sb.append(" UPDATE post        \n");
	    	sb.append(" SET p_seq =?,       \n");
	        sb.append(" m_id =?,            \n");
	        sb.append(" p_title =?,         \n");
	        sb.append(" p_read_cnt =?,      \n");
	        sb.append(" p_atch_file_id =?,  \n");
	        sb.append(" p_div =?,           \n");
	        sb.append(" p_contents =?,      \n");
	        sb.append(" p_reg_dt =?,        \n");
	        sb.append(" p_mod_dt =?,        \n");
	        sb.append(" p_like_cnt =?       \n");
	    	sb.append("WHERE p_seq = ?		\n");
	        
			LOG.debug("1. sql=\n" + sb.toString());
			LOG.debug("2. param=\n" + post.toString());

			// param
			Object[] args = { 
					post.getSeq(), 
					post.getId(), 
					post.getTitle(), 
					post.getReadCnt(), 
					post.getAtchFileId(),
					post.getDiv(), 
					post.getContents(), 
					post.getRegDt(), 
					post.getModDt(), 
					post.getLikeCnt() };

			// jdbcTemplate.update DML에 사용
			flag = jdbcTemplate.update(sb.toString(), args);
			LOG.debug("3. flag=" + flag);

			return flag;
	    }//게시글 수정	END
	
	// 게시글 조회
	public PostVO get(PostVO postVO) throws ClassNotFoundException, SQLException {
		PostVO outVO = null;

		StringBuilder sb = new StringBuilder(200);
		sb.append(" SELECT           	   \n");
		sb.append("		 p_seq ,  		   \n");
	    sb.append("		 m_id ,            \n");
	    sb.append("		 p_title ,         \n");
	    sb.append("		 p_read_cnt ,      \n");
	    sb.append("		 p_atch_file_id ,  \n");
	    sb.append("		 p_div ,           \n");
	    sb.append("		 p_contents ,      \n");
	    sb.append("		 p_reg_dt ,        \n");
	    sb.append("		 p_mod_dt ,        \n");
	    sb.append("		 p_like_cnt        \n");
		sb.append(" FROM           		   \n");
		sb.append("      post    		   \n");
		sb.append(" WHERE 				   \n");
		sb.append("		 p_seq = ?		   \n");
		

		LOG.debug("1. sql =\n" + sb.toString());
		LOG.debug("2. param = " + outVO.toString());

		// param
		Object[] args = { postVO.getSeq() };
		
		// jdbcTemplate
		outVO = (PostVO) this.jdbcTemplate.queryForMap(sb.toString(), new RowMapper<PostVO>() {
			
		//-------------------------------------------------------
			@Override
			public PostVO mapRow(ResultSet rs, int rowNum) throws SQLException {

				PostVO out = new PostVO();

				
				out.setSeq        (rs.getString(" p_seq"));
				out.setId         (rs.getString(" m_id"));
				out.setTitle      (rs.getString(" p_title"));
				out.setReadCnt    (rs.getInt(" p_read_cnt"));
				out.setAtchFileId (rs.getString(" p_atch_file_id"));
				out.setDiv        (rs.getInt(" p_div"));
				out.setContents   (rs.getString(" p_contents "));
				out.setRegDt      (rs.getString(" p_reg_dt"));
				out.setModDt      (rs.getString(" p_mod_dt"));
				out.setLikeCnt    (rs.getInt(" p_like_cnt"));


				return out;
			}
		//-----------------------------------------------------------


		}, args);

		LOG.debug("3. outVO = " + outVO);

		return outVO;
	}// 게시글 조회 END	
	
	// 게시글 리스트
	@SuppressWarnings("deprecation")
	public List<PostVO> getALL(PostVO user) throws SQLException {
		List<PostVO> outList = new ArrayList<PostVO>();

		StringBuilder sb = new StringBuilder(200);
		sb.append(" SELECT           	   \n");
		sb.append("		t1.p_seq ,  		\n");
	    sb.append("		t1.m_id ,          \n");
	    sb.append("		t1.p_title ,       \n");
	    sb.append("		t1.p_read_cnt ,    \n");
	    sb.append("		t1.p_atch_file_id ,  \n");
	    sb.append("		t1.p_div ,           \n");
	    sb.append("		t1.p_contents ,      \n");
	    sb.append("		t1.p_reg_dt ,        \n");
	    sb.append("		t1.p_mod_dt ,        \n");
	    sb.append("		t1.p_like_cnt        \n");
		sb.append(" FROM           		   \n");
		sb.append("      post  t1   		 \n");
		sb.append(" WHERE 				   \n");
		sb.append("		p_seq ? LIKE || '%' \n");
		sb.append(" ORDER BY t1.reg_dt DESC  \n");

		LOG.debug("1. sql =\n" + sb.toString());
		LOG.debug("2. param = " + user);

		// param
		Object[] args = { user.getSeq() };

		// jdbcTemplate
		outList = jdbcTemplate.query(sb.toString(), args, new RowMapper<PostVO>() {

			@Override
			public PostVO mapRow(ResultSet rs, int rowNum) throws SQLException {

				PostVO out = new PostVO();

				out.setSeq        (rs.getString(" p_seq"));
				out.setId         (rs.getString(" m_id"));
				out.setTitle      (rs.getString(" p_title"));
				out.setReadCnt    (rs.getInt(" p_read_cnt"));
				out.setAtchFileId (rs.getString(" p_atch_file_id"));
				out.setDiv        (rs.getInt(" p_div"));
				out.setContents   (rs.getString(" p_contents "));
				out.setRegDt      (rs.getString(" p_reg_dt"));
				out.setModDt      (rs.getString(" p_mod_dt"));
				out.setLikeCnt    (rs.getInt(" p_like_cnt"));

				return out;
			}

		});

		return outList;
	}// 게시글 리스트 END
	


	
		//게시글 삭제
		public int deleteOne(final PostVO post)throws SQLException{
		int flag = 0;

		
		StringBuilder  sb=new StringBuilder(50);
		sb.append(" DELETE FROM post	 \n");
		sb.append(" WHERE p_seq = ?     \n");
		
		LOG.debug("1. sql=\n"+sb.toString());
		LOG.debug("2. param=\n"+post.toString());
		
		//param
		Object[] args = {post.getSeq()};
		
		//jdbcTemplate.update DML에 사용
		flag = jdbcTemplate.update(sb.toString(), args);
		LOG.debug("3. flag="+flag);
		
		
		return flag;
	}//게시글 삭제 END
	    
	 
}

