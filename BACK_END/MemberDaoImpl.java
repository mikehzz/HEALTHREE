package com.pcwk.ehr;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class MemberDaoImpl implements MemberDao {

	private final Logger LOG = LogManager.getLogger(getClass());

	private DataSource dataSource;

	// Spring이 제공하는 jdbcTemplate
	private JdbcTemplate jdbcTemplate;

	// **** default 생성
	public MemberDaoImpl() {
	}

	// *** applicationContext.xml에서 주입
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		// 수동으로 DI
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	// 건수 조회
	@Override
	@SuppressWarnings("deprecation") // queryForObject에 대한 메시지 제거
	public int getCount(MemberVO member) throws SQLException {
		int cnt = 0;

		StringBuilder sb = new StringBuilder(200);
		sb.append(" SELECT COUNT(*) cnt       \n ");
		sb.append(" FROM member               \n ");
		sb.append(" WHERE m_id LIKE ? || '%'  \n ");

		LOG.debug("1. sql = \n" + sb.toString());
		LOG.debug("2. param = \n" + member);

		// param
		Object[] args = { member.getId() };

		// jdbcTemplate
		cnt = this.jdbcTemplate.queryForObject(sb.toString(), args, Integer.class);
		LOG.debug("3. cnt = " + cnt);

		return cnt;
	}

	// 회원정보 조회
	@Override
	public MemberVO get(MemberVO member) throws ClassNotFoundException, SQLException {
		MemberVO outVO = null;

		StringBuilder sb = new StringBuilder(200);
		sb.append(" SELECT                  \n ");
		sb.append("     t1.m_id,            \n ");
		sb.append("     t1.m_pw,            \n ");
		sb.append("     t1.m_name,          \n ");
		sb.append("     t1.m_email,         \n ");
		sb.append("     t1.m_gender,        \n ");
		sb.append("     TO_CHAR(t1.m_birthday, 'YYYY-MM-DD') m_birthday,  \n");
		sb.append("     t1.m_height,        \n ");
		sb.append("     t1.m_weight,        \n ");
		sb.append("     t1.m_target_weight, \n ");
		sb.append("     t1.m_act_lv,        \n ");
		sb.append("     t1.m_diet_goal      \n ");
		sb.append(" FROM                    \n ");
		sb.append("     member t1           \n ");
		sb.append(" WHERE                   \n ");
		sb.append("     m_id = ?            \n ");

		LOG.debug("1. sql = \n" + sb.toString());
		LOG.debug("2. param = \n" + member);

		// param
		Object[] args = { member.getId() };

		outVO = this.jdbcTemplate.queryForObject(sb.toString(), new RowMapper<MemberVO>() {

			public MemberVO mapRow(ResultSet rs, int rowNum) throws SQLException {

				MemberVO out = new MemberVO();

				out.setId(rs.getString("m_id"));
				out.setPw(rs.getString("m_pw"));
				out.setName(rs.getString("m_name"));
				out.setEmail(rs.getString("m_email"));
				out.setGender(rs.getString("m_gender"));
				out.setBirthday(rs.getString("m_birthday"));
				out.setHeight(rs.getInt("m_height"));
				out.setWeight(rs.getInt("m_weight"));
				out.setTargetWeight(rs.getInt("m_target_weight"));
				out.setActLevel(rs.getInt("m_act_lv"));
				out.setDietGoal(rs.getInt("m_diet_goal"));

				return out;
			}

		}, args);

		LOG.debug("3. outVO = " + outVO);

		return outVO;
	}

	// 회원정보 수정
	@Override
	public int update(MemberVO member) throws SQLException {
		int flag = 0;
		StringBuilder sb = new StringBuilder(100);
		sb.append(" UPDATE member            \n ");
		sb.append(" SET                      \n ");
		sb.append(" 	 m_pw            = ? \n ");
		sb.append(" 	,m_name          = ? \n ");
		sb.append(" 	,m_email         = ? \n ");
		sb.append(" 	,m_gender        = ? \n ");
		sb.append(" 	,m_birthday      = ? \n ");
		sb.append(" 	,m_height        = ? \n ");
		sb.append(" 	,m_weight        = ? \n ");
		sb.append(" 	,m_target_weight = ? \n ");
		sb.append(" 	,m_act_lv        = ? \n ");
		sb.append(" 	,m_diet_goal     = ? \n ");
		sb.append(" WHERE                    \n ");
		sb.append("     m_id             = ? \n ");

		LOG.debug("1. sql =  \n" + sb.toString());
		LOG.debug("2. param =\n" + member.toString());

		// param
		Object[] args = { member.getPw(), member.getName(), member.getEmail(), member.getGender(), member.getBirthday(),
				member.getHeight(), member.getWeight(), member.getTargetWeight(), member.getActLevel(),
				member.getDietGoal(), member.getId() };

		// jdbcTemplate.update DML에 사용
		flag = jdbcTemplate.update(sb.toString(), args);
		LOG.debug("3. flag = " + flag);

		return flag;
	}

	// 회원 탈퇴
	@Override
	public int deleteOne(final MemberVO member) throws SQLException {
		int flag = 0;
		// ---------------------------------------

		StringBuilder sb = new StringBuilder(50);
		sb.append(" DELETE FROM member \n ");
		sb.append(" WHERE              \n ");
		sb.append("         m_id = ?   \n ");

		LOG.debug("1. sql =  \n" + sb.toString());
		LOG.debug("2. param =\n" + member.toString());

		// param
		Object[] args = { member.getId() };

		// jdbcTemplate.update DML에 사용
		flag = jdbcTemplate.update(sb.toString(), args);
		LOG.debug("3. flag = " + flag);

		return flag;
	}

	// 회원가입
	@Override
	public int add(final MemberVO member) throws ClassNotFoundException, SQLException {
		int flag = 0;  // 등록 건수

		StringBuilder sb = new StringBuilder(200);
		sb.append(" INSERT INTO member ( \n ");
		sb.append("     m_id,            \n ");
		sb.append("     m_pw,            \n ");
		sb.append("     m_name,          \n ");
		sb.append("     m_email,         \n ");
		sb.append("     m_gender,        \n ");
		sb.append("     m_birthday,      \n ");
		sb.append("     m_height,        \n ");
		sb.append("     m_weight,        \n ");
		sb.append("     m_target_weight, \n ");
		sb.append("     m_act_lv,        \n ");
		sb.append("     m_diet_goal      \n ");
		sb.append(" ) VALUES (           \n ");
		sb.append("     ?,               \n ");
		sb.append("     ?,               \n ");
		sb.append("     ?,               \n ");
		sb.append("     ?,               \n ");
		sb.append("     ?,               \n ");
		sb.append("     ?,               \n ");
		sb.append("     ?,               \n ");
		sb.append("     ?,               \n ");
		sb.append("     ?,               \n ");
		sb.append("     ?,               \n ");
		sb.append("     ?                \n ");
		sb.append(" )                    \n ");

		LOG.debug("1. sql = \n" + sb.toString());
		LOG.debug("2. param = \n" + member.toString());

		// param
		Object[] args = { member.getId(), member.getPw(), member.getName(), member.getEmail(), member.getGender(),
				member.getBirthday(), member.getHeight(), member.getWeight(), member.getTargetWeight(),
				member.getActLevel(), member.getDietGoal() };

		// jdbcTemplate.update DML에 사용
		flag = jdbcTemplate.update(sb.toString(), args);
		LOG.debug("3. flag = " + flag);

		return flag;
	}

}
