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

	// 일일 섭취한 총 칼로리/탄/단/지
	public FoodVO getsumThisDay(FoodVO food, MealVO meal) throws ClassNotFoundException, SQLException {
		FoodVO outVO = null;

		StringBuilder sb = new StringBuilder(200);
		sb.append(" SELECT                     		      \n");
		sb.append("     t1.m_date,                        \n");
		sb.append("     SUM(t2.f_cal) AS sum_calories,    \n");
		sb.append("     SUM(t2.f_carbo) AS sum_carbo,     \n");
		sb.append("     SUM(t2.f_protein) AS sum_protein, \n");
		sb.append("     SUM(t2.f_fat) AS sum_fat          \n");
		sb.append(" FROM meal t1                          \n");
		sb.append(" JOIN food t2 ON t1.f_code = t2.f_code \n");
		sb.append(" WHERE t1.m_id = ?                     \n");
		sb.append(" AND t1.m_date = ?                     \n");
		sb.append(" GROUP BY t1.m_date                    \n");
		sb.append(" ORDER BY t1.m_date                    \n");

		LOG.debug("1. sql=\n" + sb.toString());
		LOG.debug("2. param=" + food);

		Object[] args = { meal.getId(), meal.getDate() };
		outVO = this.jdbcTemplate.queryForObject(sb.toString(), new RowMapper<FoodVO>() {

			@Override
			public FoodVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				FoodVO out = new FoodVO();

				out.setDate(rs.getString("m_date"));
				out.setSum_calories(rs.getDouble("sum_calories"));
				out.setSum_carbo(rs.getDouble("sum_carbo"));
				out.setSum_protein(rs.getDouble("sum_protein"));
				out.setSum_fat(rs.getDouble("sum_fat"));

				return out;
			}

		}, args);

		LOG.debug("3.outVO:" + outVO);

		return outVO;
	}

	// 월 평균 섭취한 칼로리/탄/단/지
	public FoodVO getavgThisMonth(FoodVO food, MealVO meal) throws ClassNotFoundException, SQLException {
		FoodVO outVO = null;

		StringBuilder sb = new StringBuilder(200);
		sb.append(" SELECT                                          \n");
		sb.append("     SUBSTR(t1.m_date, 1, 6) AS month,           \n");
		sb.append("     TRUNC(AVG(t2.f_cal), 2) AS avg_calories,    \n");
		sb.append("     TRUNC(AVG(t2.f_carbo), 2) AS avg_carbo,     \n");
		sb.append("     TRUNC(AVG(t2.f_protein), 2) AS avg_protein, \n");
		sb.append("     TRUNC(AVG(t2.f_fat), 2) AS avg_fat          \n");
		sb.append(" FROM meal t1                                    \n");
		sb.append(" JOIN food t2 ON t1.f_code = t2.f_code           \n");
		sb.append(" WHERE t1.m_id = ?                               \n");
		sb.append(" AND SUBSTR(t1.m_date, 1, 6) = SUBSTR(?, 1, 6)   \n");
		sb.append(" GROUP BY                                        \n");
		sb.append(" 	SUBSTR(t1.m_date, 1, 6)	                    \n");
		sb.append(" ORDER BY                                        \n");
		sb.append(" 	SUBSTR(t1.m_date, 1, 6)                     \n");

		LOG.debug("1. sql=\n" + sb.toString());
		LOG.debug("2. param=" + food);

		Object[] args = { meal.getId(), meal.getDate() };
		outVO = this.jdbcTemplate.queryForObject(sb.toString(), new RowMapper<FoodVO>() {

			@Override
			public FoodVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				FoodVO out = new FoodVO();

				out.setDate(rs.getString("month"));
				out.setAvg_calories(rs.getDouble("avg_calories"));
				out.setAvg_carbo(rs.getDouble("avg_carbo"));
				out.setAvg_protein(rs.getDouble("avg_protein"));
				out.setAvg_fat(rs.getDouble("avg_fat"));

				return out;
			}

		}, args);

		LOG.debug("3.outVO:" + outVO);

		return outVO;
	}

	// 월 총합 칼로리/탄/단/지
	public FoodVO getMonthStats(FoodVO food, MealVO meal) throws SQLException {
		FoodVO outVO = null;

		StringBuilder sb = new StringBuilder(200);
		sb.append(" SELECT                                       \n");
		sb.append("     SUBSTR(M.M_DATE, 1, 6) AS month,         \n");
		sb.append("     SUM(F.F_CAL) AS total_Calories,			 \n");
		sb.append("     SUM(F.F_CARBO) AS total_Carbohydrates,	 \n");
		sb.append("     SUM(F.F_PROTEIN) AS total_Protein,		 \n");
		sb.append("     SUM(F.F_FAT) AS total_Fat				 \n");
		sb.append(" FROM										 \n");
		sb.append("     MEAL M									 \n");
		sb.append("     JOIN FOOD F ON M.F_CODE = F.F_CODE		 \n");
		sb.append(" WHERE M.M_ID = ?                             \n");
		sb.append(" AND SUBSTR(M.M_DATE, 1, 6) = SUBSTR(?, 1, 6) \n");
		sb.append(" GROUP BY									 \n");
		sb.append("     SUBSTR(M.M_DATE, 1, 6)	                 \n");
		sb.append(" ORDER BY								     \n");
		sb.append("     SUBSTR(M.M_DATE, 1, 6)                   \n");

		LOG.debug("1. sql=\n" + sb.toString());
		LOG.debug("2. param=" + food);

		// param
		Object[] args = { meal.getId(), meal.getDate() };
		outVO = this.jdbcTemplate.queryForObject(sb.toString(), new RowMapper<FoodVO>() {

			@Override
			public FoodVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				FoodVO out = new FoodVO();

				out.setMonth(rs.getString("month"));
				out.setTotalCalories(rs.getDouble("total_Calories"));
				out.setTotalCarbohydrates(rs.getDouble("total_Carbohydrates"));
				out.setTotalProtein(rs.getDouble("total_Protein"));
				out.setTotalFat(rs.getDouble("total_Fat"));

				return out;
			}
		}, args);

		LOG.debug("3.outVO:" + outVO);

		return outVO;
	}

	// 저번 주 칼로리 (꺾은 선 그래프)
	public List<FoodVO> getCalLastWeek(MealVO meal, FoodVO food) throws ClassNotFoundException, SQLException {
		List<FoodVO> outList = new ArrayList<>();

		StringBuilder sb = new StringBuilder(200);
		sb.append(" SELECT						                \n");
		sb.append("     t1.m_date,								\n");
		sb.append("     SUM(t2.f_cal) AS total_calories			\n");
		sb.append(" FROM meal t1								\n");
		sb.append(" JOIN food t2 ON t1.f_code = t2.f_code		\n");
		sb.append(" JOIN member t3 ON t1.m_id = t3.m_id			\n");
		sb.append(" WHERE t1.m_date >= TRUNC(SYSDATE, 'IW') - 7	\n");
		sb.append("     AND t1.m_date < TRUNC(SYSDATE, 'IW')	\n");
		sb.append("     AND t3.m_id = ?							\n");
		sb.append(" GROUP BY t1.m_date							\n");
		sb.append(" ORDER BY t1.m_date							\n");

		LOG.debug("1. sql=\n" + sb.toString());
		LOG.debug("2. param=" + food);

		Object[] args = { meal.getId() };
		outList = this.jdbcTemplate.query(sb.toString(), new RowMapper<FoodVO>() {

			@Override
			public FoodVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				FoodVO out = new FoodVO();

				out.setDate(rs.getString("m_date"));
				out.setTotalCalories(rs.getDouble("total_calories"));

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
				out.setTotalCalories(rs.getDouble("total_calories"));

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

				out.setCarbo_last_week(rs.getDouble("carbon_last_week"));
				out.setProtein_last_week(rs.getDouble("protein_last_week"));
				out.setFat_last_week(rs.getDouble("fat_last_week"));
				out.setSugar_last_week(rs.getDouble("sugar_last_week"));
				out.setNa_last_week(rs.getDouble("na_last_week"));
				out.setCole_last_week(rs.getDouble("cole_last_week"));

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

				out.setCarbo_this_week(rs.getDouble("carbon_this_week"));
				out.setProtein_this_week(rs.getDouble("protein_this_week"));
				out.setFat_this_week(rs.getDouble("fat_this_week"));
				out.setSugar_this_week(rs.getDouble("sugar_this_week"));
				out.setNa_this_week(rs.getDouble("na_this_week"));
				out.setCole_this_week(rs.getDouble("cole_this_week"));

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
