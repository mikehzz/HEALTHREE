package com.pcwk.ehr;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;

public class MealDao {

	private final Logger LOG = LogManager.getLogger(getClass());

	private DataSource dataSource;

	// Spring이 제공하는 jdbcTemplate
	private JdbcTemplate jdbcTemplate;

	// **** default 생성
	public MealDao() {
	}

	// *** applicationContext.xml에서 주입
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		// 수동으로 DI
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	// 삭제
	public int deleteOne(final MealVO meal) throws SQLException {
		int flag = 0;
		// ---------------------------------------

		StringBuilder sb = new StringBuilder(50);
		sb.append(" DELETE FROM meal   \n");
		sb.append(" WHERE              \n");
		sb.append("         m_id   = ? \n");
		sb.append("     AND m_date = ? \n");
		sb.append("     AND m_div  = ? \n");
		sb.append("     AND m_seq  = ? \n");

		LOG.debug("1. sql =  \n" + sb.toString());
		LOG.debug("2. param =\n" + meal.toString());

		// param
		Object[] args = { meal.getId(), meal.getDate(), meal.getDiv(), meal.getSeq() };

		// jdbcTemplate.update DML에 사용
		flag = jdbcTemplate.update(sb.toString(), args);
		LOG.debug("3. flag = " + flag);

		return flag;
	}

	// 추가
	public int add(final MealVO meal) throws ClassNotFoundException, SQLException {
		int flag = 0;// 등록 건수

		StringBuilder sb = new StringBuilder(200);
		sb.append(" INSERT INTO meal ( \n");
		sb.append("     m_id,          \n");
		sb.append("     m_date,        \n");
		sb.append("     m_div,         \n");
		sb.append("     m_seq,         \n");
		sb.append("     f_code         \n");
		sb.append(" ) VALUES (         \n");
		sb.append("     ?,             \n");
		sb.append("     ?,             \n");
		sb.append("     ?,             \n");
		sb.append("     ?,             \n");
		sb.append("     ?              \n");
		sb.append(" )                  \n");

		LOG.debug("1. sql=\n" + sb.toString());
		LOG.debug("2. param=\n" + meal.toString());

		// param
		Object[] args = { meal.getId(), meal.getDate(), meal.getDiv(), meal.getSeq(), meal.getCode() };

		// jdbcTemplate.update DML에 사용
		flag = jdbcTemplate.update(sb.toString(), args);
		LOG.debug("3. flag=" + flag);

		return flag;
	}

}
