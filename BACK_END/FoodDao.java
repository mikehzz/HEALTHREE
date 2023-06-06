package com.pcwk.ehr;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class FoodDao {

	private final Logger LOG = LogManager.getLogger(getClass());

	private DataSource dataSource;

	// Spring이 제공하는 jdbcTemplate
	private JdbcTemplate jdbcTemplate;

	// **** default 생성
	public FoodDao() {
	}

	// *** applicationContext.xml에서 주입
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		// 수동으로 DI
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	// 저번 주 칼로리 (꺾은 선 그래프)
	public List<FoodVO> getCalLastWeek(MealVO meal, FoodVO food) throws ClassNotFoundException, SQLException {
		List<FoodVO> outList = new ArrayList<>();

		StringBuilder sb = new StringBuilder(200);
		sb.append(" SELECT                                       \n");
		sb.append("     t1.m_date,                               \n");
		sb.append("     SUM(t2.f_cal) AS total_calories          \n");
		sb.append(" FROM meal t1                                 \n");
		sb.append(" JOIN food t2 ON t1.f_code = t2.f_code        \n");
		sb.append(" JOIN member t3 ON t1.m_id = t3.m_id          \n");
		sb.append(" WHERE t1.m_date >= TRUNC(SYSDATE, 'IW') - 7  \n");
		sb.append("     AND t1.m_date < TRUNC(SYSDATE, 'IW')     \n");
		sb.append("     AND t3.m_id = ?                          \n");
		sb.append(" GROUP BY t1.m_date                           \n");
		sb.append(" ORDER BY t1.m_date                           \n");

		LOG.debug("1. sql=\n" + sb.toString());
		LOG.debug("2. param=" + food);

		Object[] args = { meal.getId() };

		outList = this.jdbcTemplate.query(sb.toString(), new RowMapper<FoodVO>() {

			@Override
			public FoodVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				FoodVO out = new FoodVO();

				out.setDate(rs.getString("m_date"));
				out.setF_cal(rs.getDouble("total_calories"));

				return out;
			}

		}, args);

		LOG.debug("3.outVO:" + outList);

		return outList;
	}

	// 이번 주 칼로리 (꺾은 선 그래프)
	public List<FoodVO> getCalThisWeek(MealVO meal, FoodVO food) throws ClassNotFoundException, SQLException {
		List<FoodVO> outList = new ArrayList<>();

		StringBuilder sb = new StringBuilder(200);
		sb.append(" SELECT                                       \n");
		sb.append("     t1.m_date,                               \n");
		sb.append("     SUM(t2.f_cal) AS total_calories          \n");
		sb.append(" FROM meal t1                                 \n");
		sb.append(" JOIN food t2 ON t1.f_code = t2.f_code        \n");
		sb.append(" JOIN member t3 ON t1.m_id = t3.m_id          \n");
		sb.append(" WHERE t1.m_date >= TRUNC(SYSDATE, 'IW')         \n");
		sb.append("     AND t1.m_date < TRUNC(SYSDATE, 'IW') +7    \n");
		sb.append("     AND t3.m_id = ?                          \n");
		sb.append(" GROUP BY t1.m_date                           \n");
		sb.append(" ORDER BY t1.m_date                          \n");

		LOG.debug("1. sql=\n" + sb.toString());
		LOG.debug("2. param=" + food);

		Object[] args = { meal.getId() };
		outList = this.jdbcTemplate.query(sb.toString(), new RowMapper<FoodVO>() {

			@Override
			public FoodVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				FoodVO out = new FoodVO();

				out.setDate(rs.getString("m_date"));
				out.setF_cal(rs.getDouble("total_calories"));

				return out;
			}

		}, args);

		LOG.debug("3.outVO:" + outList);

		return outList;
	}

	// 저번 주 탄/단/지/당/나/콜 (표)
	public FoodVO getNutrientLastWeek(FoodVO food, MealVO meal) throws ClassNotFoundException, SQLException {
		FoodVO outVO = null;

		StringBuilder sb = new StringBuilder(200);
		sb.append(" SELECT                                         \n");
		sb.append("     SUM(t2.f_carbo) AS carbon_last_week,       \n");
		sb.append("     SUM(t2.f_protein) AS protein_last_week,    \n");
		sb.append("     SUM(t2.f_fat) AS fat_last_week,            \n");
		sb.append("     SUM(t2.f_sugar) AS sugar_last_week,        \n");
		sb.append("     SUM(t2.f_na) AS na_last_week,              \n");
		sb.append("     SUM(t2.f_cole) AS cole_last_week           \n");
		sb.append(" FROM meal t1                                   \n");
		sb.append(" JOIN food t2 ON t1.f_code = t2.f_code          \n");
		sb.append(" JOIN member t3 ON t1.m_id = t3.m_id            \n");
		sb.append(" WHERE t1.m_id = ?                              \n");
		sb.append("     AND t1.m_date >= trunc(sysdate, 'IW') - 7  \n");
		sb.append("     AND t1.m_date <= trunc(sysdate, 'IW') - 1  \n");

		LOG.debug("1. sql=\n" + sb.toString());
		LOG.debug("2. param=" + food);

		Object[] args = { meal.getId() };
		outVO = this.jdbcTemplate.queryForObject(sb.toString(), new RowMapper<FoodVO>() {

			@Override
			public FoodVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				FoodVO out = new FoodVO();

				out.setF_carbo(rs.getDouble("carbon_last_week"));
				out.setF_protein(rs.getDouble("protein_last_week"));
				out.setF_fat(rs.getDouble("fat_last_week"));
				out.setF_sugar(rs.getDouble("sugar_last_week"));
				out.setF_na(rs.getDouble("na_last_week"));
				out.setF_cole(rs.getDouble("cole_last_week"));

				return out;
			}

		}, args);

		LOG.debug("3.outVO:" + outVO);

		return outVO;
	}

	// 이번 주 탄/단/지/당/나/콜 (표)
	public FoodVO getNutrientThisWeek(FoodVO food, MealVO meal) throws ClassNotFoundException, SQLException {
		FoodVO outVO = null;

		StringBuilder sb = new StringBuilder(200);
		sb.append(" SELECT                                         \n");
		sb.append("     SUM(t2.f_carbo) AS carbon_this_week,       \n");
		sb.append("     SUM(t2.f_protein) AS protein_this_week,    \n");
		sb.append("     SUM(t2.f_fat) AS fat_this_week,            \n");
		sb.append("     SUM(t2.f_sugar) AS sugar_this_week,        \n");
		sb.append("     SUM(t2.f_na) AS na_this_week,              \n");
		sb.append("     SUM(t2.f_cole) AS cole_this_week           \n");
		sb.append(" FROM meal t1                                   \n");
		sb.append(" JOIN food t2 ON t1.f_code = t2.f_code          \n");
		sb.append(" JOIN member t3 ON t1.m_id = t3.m_id            \n");
		sb.append(" WHERE t1.m_id = ?                              \n");
		sb.append("     AND t1.m_date >= trunc(sysdate, 'IW')      \n");
		sb.append("     AND t1.m_date <= trunc(sysdate, 'IW') + 6  \n");

		LOG.debug("1. sql=\n" + sb.toString());
		LOG.debug("2. param=" + food);

		Object[] args = { meal.getId() };
		outVO = this.jdbcTemplate.queryForObject(sb.toString(), new RowMapper<FoodVO>() {

			@Override
			public FoodVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				FoodVO out = new FoodVO();

				out.setF_carbo(rs.getDouble("carbon_this_week"));
				out.setF_protein(rs.getDouble("protein_this_week"));
				out.setF_fat(rs.getDouble("fat_this_week"));
				out.setF_sugar(rs.getDouble("sugar_this_week"));
				out.setF_na(rs.getDouble("na_this_week"));
				out.setF_cole(rs.getDouble("cole_this_week"));

				return out;
			}

		}, args);

		LOG.debug("3.outVO:" + outVO);

		return outVO;
	}

	// 저번 주 탄/단/지 (막대 그래프)
	public List<FoodVO> getThreeLastWeek(FoodVO food, MealVO meal) throws ClassNotFoundException, SQLException {
		List<FoodVO> outList = new ArrayList<>();

		StringBuilder sb = new StringBuilder(200);
		sb.append("  SELECT                                         \n");
		sb.append("      t1.m_date,                                 \n");
		sb.append("      SUM(t2.f_carbo) AS carbon_last_week,       \n");
		sb.append("      SUM(t2.f_protein) AS protein_last_week,    \n");
		sb.append("      SUM(t2.f_fat) AS fat_last_week             \n");
		sb.append("  FROM meal t1                                   \n");
		sb.append("  JOIN food t2 ON t1.f_code = t2.f_code          \n");
		sb.append("  JOIN member t3 ON t1.m_id = t3.m_id            \n");
		sb.append("  WHERE t1.m_id = :V0                            \n");
		sb.append("      AND t1.m_date >= trunc(sysdate, 'IW') - 7  \n");
		sb.append("      AND t1.m_date <= trunc(sysdate, 'IW') - 1  \n");
		sb.append("  GROUP BY t1.m_date                             \n");
		sb.append("  ORDER BY t1.m_date                             \n");

		LOG.debug("1. sql=\n" + sb.toString());
		LOG.debug("2. param=" + food);

		Object[] args = { meal.getId() };
		outList = this.jdbcTemplate.query(sb.toString(), new RowMapper<FoodVO>() {

			@Override
			public FoodVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				FoodVO out = new FoodVO();

				out.setDate(rs.getString("m_date"));
				out.setF_carbo(rs.getDouble("carbon_last_week"));
				out.setF_protein(rs.getDouble("protein_last_week"));
				out.setF_fat(rs.getDouble("fat_last_week"));

				return out;
			}

		}, args);

		LOG.debug("3.outVO:" + outList);

		return outList;
	}

	// 이번 주 탄/단/지 (막대 그래프)
	public List<FoodVO> getThreeThisWeek(FoodVO food, MealVO meal) throws ClassNotFoundException, SQLException {
		List<FoodVO> outList = new ArrayList<>();

		StringBuilder sb = new StringBuilder(200);
		sb.append(" SELECT                                         \n");
		sb.append("     t1.m_date,                                 \n");
		sb.append("     SUM(t2.f_carbo) AS carbon_this_week,       \n");
		sb.append("     SUM(t2.f_protein) AS protein_this_week,    \n");
		sb.append("     SUM(t2.f_fat) AS fat_this_week             \n");
		sb.append(" FROM meal t1                                   \n");
		sb.append(" JOIN food t2 ON t1.f_code = t2.f_code          \n");
		sb.append(" JOIN member t3 ON t1.m_id = t3.m_id            \n");
		sb.append(" WHERE t1.m_id = ?                              \n");
		sb.append("     AND t1.m_date >= trunc(sysdate, 'IW')      \n");
		sb.append("     AND t1.m_date <= trunc(sysdate, 'IW') + 6  \n");
		sb.append(" GROUP BY t1.m_date                             \n");
		sb.append(" ORDER BY t1.m_date                             \n");

		LOG.debug("1. sql=\n" + sb.toString());
		LOG.debug("2. param=" + food);

		Object[] args = { meal.getId() };
		outList = this.jdbcTemplate.query(sb.toString(), new RowMapper<FoodVO>() {

			@Override
			public FoodVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				FoodVO out = new FoodVO();

				out.setDate(rs.getString("m_date"));
				out.setF_carbo(rs.getDouble("carbon_this_week"));
				out.setF_protein(rs.getDouble("protein_this_week"));
				out.setF_fat(rs.getDouble("fat_this_week"));

				return out;
			}

		}, args);

		LOG.debug("3.outVO:" + outList);

		return outList;
	}
	
	//일일  섭취한 총 칼로리/탄/단/지 계산
	public List<FoodVO> getsumThisDay(FoodVO food, MealVO meal) throws ClassNotFoundException, SQLException {
		List<FoodVO> outList = new ArrayList<>();

		StringBuilder sb = new StringBuilder(200);
		sb.append(" SELECT                                 \n");    
		sb.append("     t1.m_date,                         \n");
		sb.append("     SUM(t2.f_cal) AS avg_calories,     \n");    
		sb.append("     SUM(t2.f_carbo) AS avg_carbo,      \n");   
		sb.append("     SUM(t2.f_protein) AS avg_protein,  \n");       
		sb.append("     SUM(t2.f_fat) AS avg_fat           \n");
		sb.append(" FROM meal t1                           \n");    
		sb.append(" JOIN food t2 ON t1.f_code = t2.f_code  \n");    
		sb.append(" WHERE t1.m_id = ?             \n");
		sb.append(" GROUP BY t1.m_date                     \n");    
		sb.append(" ORDER BY t1.m_date                     \n");

		LOG.debug("1. sql=\n" + sb.toString());
		LOG.debug("2. param=" + food);

		Object[] args = { meal.getId() };
		outList = this.jdbcTemplate.query(sb.toString(), new RowMapper<FoodVO>() {

			@Override
			public FoodVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				FoodVO out = new FoodVO();
				
				out.setF_cal(rs.getDouble("calories_this_day"));
				out.setF_carbo(rs.getDouble("carbon_this_day"));
				out.setF_protein(rs.getDouble("protein_this_day"));
				out.setF_fat(rs.getDouble("fat_this_day"));
				
				
				return out;
			}

		}, args);

		LOG.debug("3.outVO:" + outList);

		return outList;
	}	
	
	
	//일일 평균 섭취한 칼로리/탄/단/지 계산
	public List<FoodVO> getavgThisDay(FoodVO food, MealVO meal) throws ClassNotFoundException, SQLException {
		List<FoodVO> outList = new ArrayList<>();

		StringBuilder sb = new StringBuilder(200);
		sb.append(" SELECT                                 \n");    
		sb.append("     t1.m_date,                         \n");
		sb.append("     AVG(t2.f_cal) AS avg_calories,     \n");    
		sb.append("     AVG(t2.f_carbo) AS avg_carbo,      \n");   
		sb.append("     AVG(t2.f_protein) AS avg_protein,  \n");       
		sb.append("     AVG(t2.f_fat) AS avg_fat           \n");
		sb.append(" FROM meal t1                           \n");    
		sb.append(" JOIN food t2 ON t1.f_code = t2.f_code  \n");    
		sb.append(" WHERE t1.m_id = ?            		   \n");
		sb.append(" GROUP BY t1.m_date                     \n");    
		sb.append(" ORDER BY t1.m_date                     \n");

		LOG.debug("1. sql=\n" + sb.toString());
		LOG.debug("2. param=" + food);

		Object[] args = { meal.getId() };
		outList = this.jdbcTemplate.query(sb.toString(), new RowMapper<FoodVO>() {

			@Override
			public FoodVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				FoodVO out = new FoodVO();
				
				out.setF_cal(rs.getDouble("calories_this_day"));
				out.setF_carbo(rs.getDouble("carbon_this_day"));
				out.setF_protein(rs.getDouble("protein_this_day"));
				out.setF_fat(rs.getDouble("fat_this_day"));
				
				
				return out;
			}

		}, args);

		LOG.debug("3.outVO:" + outList);

		return outList;
	}		
	
	
	//월별 섭취한 총 칼로리/탄/단/지 계산
	public List<FoodVO> getsumThisMonth(FoodVO food, MealVO meal) throws ClassNotFoundException, SQLException {
		List<FoodVO> outList = new ArrayList<>();

		StringBuilder sb = new StringBuilder(200);
		sb.append(" SELECT                                        \n");
		sb.append("     substr(t1.m_date,1,6),                    \n");
		sb.append("     SUM(t2.f_cal) AS calories_this_month,     \n");                      
		sb.append("     SUM(t2.f_carbo) AS carbon_this_month,     \n"); 
		sb.append("     SUM(t2.f_protein) AS protein_this_month,  \n"); 
		sb.append("     SUM(t2.f_fat) AS fat_this_month           \n"); 
		sb.append(" FROM meal t1                                  \n");
		sb.append(" JOIN food t2 ON t1.f_code = t2.f_code         \n");
		sb.append(" WHERE t1.m_id = ?                    \n");         
		sb.append(" GROUP BY SUBSTR(t1.m_date,1,6)                \n");

		LOG.debug("1. sql=\n" + sb.toString());
		LOG.debug("2. param=" + food);

		Object[] args = { meal.getId() };
		outList = this.jdbcTemplate.query(sb.toString(), new RowMapper<FoodVO>() {

			@Override
			public FoodVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				FoodVO out = new FoodVO();
				
				out.setF_cal(rs.getDouble("calories_this_day"));
				out.setF_carbo(rs.getDouble("carbon_this_day"));
				out.setF_protein(rs.getDouble("protein_this_day"));
				out.setF_fat(rs.getDouble("fat_this_day"));
				
				
				return out;
			}

		}, args);

		LOG.debug("3.outVO:" + outList);

		return outList;
	}		
	
	
	// 저장
	public int doSave(final FoodVO food) throws ClassNotFoundException, SQLException {
		int flag = 0; // 저장 건수 check

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

		LOG.debug("1. sql=\n" + sb.toString());
		LOG.debug("2. param=\n" + food.toString());

		// param
		Object[] args = { food.getF_code(), food.getF_name(), food.getF_size(), food.getF_cal(), food.getF_carbo(),
				food.getF_protein(), food.getF_fat(), food.getF_sugar(), food.getF_na(), food.getF_cole() };

		// jdbcTemplate.update DML에 사용
		flag = jdbcTemplate.update(sb.toString(), args);
		LOG.debug("3. flag=" + flag);

		return flag;
	}

}
