package com.pcwk.ehr;

import java.sql.SQLException;

import javax.sql.DataSource;

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

	// 게시글 등록
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
		Object[] args = { post.getSeq(), post.getId(), post.getTitle(), post.getReadCnt(), post.getAtchFileId(),
				post.getDiv(), post.getContents(), post.getRegDt(), post.getModDt(), post.getLikeCnt() };

		// jdbcTemplate.update DML에 사용
		flag = jdbcTemplate.update(sb.toString(), args);
		LOG.debug("3. flag=" + flag);

		return flag;
	}

}
