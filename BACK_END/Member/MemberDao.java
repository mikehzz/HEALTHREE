package com.pcwk.ehr;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;

public class MemberDao {

	private final Logger LOG = LogManager.getLogger(getClass());

	private DataSource dataSource;

	// Spring이 제공하는 jdbcTemplate
	private JdbcTemplate jdbcTemplate;

	// **** default 생성
	public MemberDao() {
	}

	// *** applicationContext.xml에서 주입
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		// 수동으로 DI
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	// 수정
	public int update(MemberVO member) throws SQLException {
		int flag = 0;
		StringBuilder sb = new StringBuilder(100);
		sb.append(" UPDATE member            \n");
		sb.append(" SET                      \n");
		sb.append(" 	 m_pw            = ? \n");
		sb.append(" 	,m_name          = ? \n");
		sb.append(" 	,m_email         = ? \n");
		sb.append(" 	,m_gender        = ? \n");
		sb.append(" 	,m_birthday      = ? \n");
		sb.append(" 	,m_height        = ? \n");
		sb.append(" 	,m_weight        = ? \n");
		sb.append(" 	,m_target_weight = ? \n");
		sb.append(" 	,m_act_lv        = ? \n");
		sb.append(" 	,m_diet_goal     = ? \n");
		sb.append(" WHERE                    \n");
		sb.append("     m_id             = ? \n");

		LOG.debug("1. sql=  \n" + sb.toString());
		LOG.debug("2. param=\n" + member.toString());

		// param
		Object[] args = { member.getPw(), member.getName(), member.getEmail(), member.getGender(), member.getBirthday(),
				member.getHeight(), member.getWeight(), member.getTargetWeight(), member.getActLevel(),
				member.getDietGoal(), member.getId() };

		// jdbcTemplate.update DML에 사용
		flag = jdbcTemplate.update(sb.toString(), args);
		LOG.debug("3. flag=" + flag);

		return flag;
	}

	// 삭제
	public int deleteOne(final MemberVO member) throws SQLException {
		int flag = 0;
		// ---------------------------------------

		StringBuilder sb = new StringBuilder(50);
		sb.append(" DELETE FROM member \n");
		sb.append(" WHERE              \n");
		sb.append("         m_id = ?   \n");

		LOG.debug("1. sql=\n" + sb.toString());
		LOG.debug("2. param=\n" + member.toString());

		// param
		Object[] args = { member.getId() };

		// jdbcTemplate.update DML에 사용
		flag = jdbcTemplate.update(sb.toString(), args);
		LOG.debug("3. flag=" + flag);

		return flag;
	}

	// 추가
	public int add(final MemberVO member) throws ClassNotFoundException, SQLException {
		int flag = 0;// 등록 건수

		StringBuilder sb = new StringBuilder(200);
		sb.append(" INSERT INTO member ( \n");
		sb.append("     m_id,            \n");
		sb.append("     m_pw,            \n");
		sb.append("     m_name,          \n");
		sb.append("     m_email,         \n");
		sb.append("     m_gender,        \n");
		sb.append("     m_birthday,      \n");
		sb.append("     m_height,        \n");
		sb.append("     m_weight,        \n");
		sb.append("     m_target_weight, \n");
		sb.append("     m_act_lv,        \n");
		sb.append("     m_diet_goal      \n");
		sb.append(" ) VALUES (           \n");
		sb.append("     ?,               \n");
		sb.append("     ?,               \n");
		sb.append("     ?,               \n");
		sb.append("     ?,               \n");
		sb.append("     ?,               \n");
		sb.append("     ?,               \n");
		sb.append("     ?,               \n");
		sb.append("     ?,               \n");
		sb.append("     ?,               \n");
		sb.append("     ?,               \n");
		sb.append("     ?                \n");
		sb.append(" )                    \n");

		LOG.debug("1. sql=\n" + sb.toString());
		LOG.debug("2. param=\n" + member.toString());

		// param
		Object[] args = { member.getId(), member.getPw(), member.getName(), member.getEmail(), member.getGender(),
				member.getBirthday(), member.getHeight(), member.getWeight(), member.getTargetWeight(),
				member.getActLevel(), member.getDietGoal() };

		// jdbcTemplate.update DML에 사용
		flag = jdbcTemplate.update(sb.toString(), args);
		LOG.debug("3. flag=" + flag);

		return flag;
	}

}
