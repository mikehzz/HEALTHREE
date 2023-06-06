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

public class MealDaoImpl implements MealDao {

	private final Logger LOG = LogManager.getLogger(getClass());

	private DataSource dataSource;

	// Spring이 제공하는 jdbcTemplate
	private JdbcTemplate jdbcTemplate;
	
	// **** default 생성
	public MealDaoImpl() {
	}

	// *** applicationContext.xml에서 주입
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		// 수동으로 DI
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	// 월 합
	@Override
	public List<FoodVO> getMonthStats(String memberId, String mealType) throws SQLException {
	    StringBuilder sb = new StringBuilder(200);
	    sb.append(" SELECT \n");
	    sb.append("     TO_CHAR(TO_DATE(M.M_DATE, 'YYYYMMDD'), 'YYYY-MM') AS 월, 		\n");
	    sb.append("     SUM(F.F_CAL) AS 총_칼로리, 																	\n");
	    sb.append("     SUM(F.F_CARBO) AS 총_탄수화물, 															\n");
	    sb.append("     SUM(F.F_PROTEIN) AS 총_단백질, 															\n");
	    sb.append("     SUM(F.F_FAT) AS 총_지방 																		\n");
	    sb.append(" FROM 																									\n");
	    sb.append("     MEAL M 																					\n");
	    sb.append("     JOIN FOOD F ON M.F_CODE = F.F_CODE 									\n");
	    sb.append(" WHERE 																										\n");
	    sb.append("     M.M_ID = ? AND 																						\n");
	    sb.append("     M.M_DIV = ? 																						\n");
	    sb.append(" GROUP BY 																							\n");
	    sb.append("     TO_CHAR(TO_DATE(M.M_DATE, 'YYYYMMDD'), 'YYYY-MM') 		\n");
	    sb.append(" ORDER BY 																							\n");
	    sb.append("     TO_CHAR(TO_DATE(M.M_DATE, 'YYYYMMDD'), 'YYYY-MM') 			\n");

	    // param
	    Object[] args = { memberId, mealType };
	    List<FoodVO> statsList = this.jdbcTemplate.query(sb.toString(), args, new RowMapper<FoodVO>() {
	        @Override
	        public FoodVO mapRow(ResultSet rs, int rowNum) throws SQLException {
	            FoodVO foodVO = new FoodVO();

	            foodVO.setMonth(rs.getString("월"));
	            foodVO.setTotalCalories(rs.getDouble("총_칼로리"));
	            foodVO.setTotalCarbohydrates(rs.getDouble("총_탄수화물"));
	            foodVO.setTotalProtein(rs.getDouble("총_단백질"));
	            foodVO.setTotalFat(rs.getDouble("총_지방"));

	            return foodVO;
	        }
	    });

	    return statsList;
	}
	//월 일일평균
		@Override
		public List<FoodVO> getMonthAvg(String memberId, String mealType) throws SQLException {
		    List<FoodVO> statsList = new ArrayList<>();
		    StringBuilder sb = new StringBuilder(200);
		    sb.append(" SELECT \n");
		    sb.append("     TO_CHAR(TO_DATE(M.M_DATE, 'YYYYMMDD'), 'YYYY-MM') AS 월, 		\n");
		    sb.append("     SUM(F.F_CAL) / COUNT(DISTINCT M.M_DATE) AS 평균_칼로리, 														\n");
		    sb.append("     SUM(F.F_CARBO) / COUNT(DISTINCT M.M_DATE) AS 평균_탄수화물, 													\n");
		    sb.append("     SUM(F.F_PROTEIN) / COUNT(DISTINCT M.M_DATE) AS 평균_단백질, 													\n");
		    sb.append("     SUM(F.F_FAT) / COUNT(DISTINCT M.M_DATE) AS 평균_지방 														\n");
		    sb.append(" FROM 																											\n");
		    sb.append("     MEAL M 																								\n");
		    sb.append("     JOIN FOOD F ON M.F_CODE = F.F_CODE 														\n");
		    sb.append(" WHERE 																												\n");
		    sb.append("     M.M_ID = ? AND 																							\n");
		    sb.append("     M.M_DIV = ? 																							\n");
		    sb.append(" GROUP BY 																									\n");
		    sb.append("     TO_CHAR(TO_DATE(M.M_DATE, 'YYYYMMDD'), 'YYYY-MM') 									\n");
		    sb.append(" ORDER BY 																									\n");
		    sb.append("     TO_CHAR(TO_DATE(M.M_DATE, 'YYYYMMDD'), 'YYYY-MM') 										\n");
		    // param
		    Object[] args = { memberId, mealType };
		    statsList = this.jdbcTemplate.query(sb.toString(), args, new RowMapper<FoodVO>() {
		        @Override
		        public FoodVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		            FoodVO stats = new FoodVO();
		            stats.setDate(rs.getString("월"));
		            stats.setF_cal(rs.getDouble("평균_칼로리"));
		            stats.setF_carbo(rs.getDouble("평균_탄수화물"));
		            stats.setF_protein(rs.getDouble("평균_단백질"));
		            stats.setF_fat(rs.getDouble("평균_지방"));
		            return stats;
		        }
		    });
		    return statsList;
		}

	//일일 총합
		@Override
		public List<FoodVO> getDayStats(String memberId, String mealType) {
		    List<FoodVO> statsList = new ArrayList<>();
		    StringBuilder sb = new StringBuilder(200);
		    sb.append(" SELECT \n");
		    sb.append("     M.M_DATE AS 날짜, \n");
		    sb.append("     SUM(F.F_CAL) AS 총_칼로리, \n");
		    sb.append("     SUM(F.F_CARBO) AS 총_탄수화물, \n");
		    sb.append("     SUM(F.F_PROTEIN) AS 총_단백질, \n");
		    sb.append("     SUM(F.F_FAT) AS 총_지방 \n");
		    sb.append(" FROM \n");
		    sb.append("     MEAL M \n");
		    sb.append("     JOIN FOOD F ON M.F_CODE = F.F_CODE \n");
		    sb.append(" WHERE \n");
		    sb.append("     M.M_ID = ? AND \n");
		    sb.append("     M.M_DIV = ? \n");
		    sb.append(" GROUP BY \n");
		    sb.append("     M.M_DATE \n");
		    sb.append(" ORDER BY \n");
		    sb.append("     M.M_DATE \n");

		    Object[] args = { memberId, mealType };
		    statsList = this.jdbcTemplate.query(sb.toString(), args, new RowMapper<FoodVO>() {
		        @Override
		        public FoodVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		            FoodVO stats = new FoodVO();
		            stats.setDate(rs.getString("날짜"));
		            stats.setTotalCalories(rs.getDouble("총_칼로리"));
		            stats.setTotalCarbohydrates(rs.getDouble("총_탄수화물"));
		            stats.setTotalProtein(rs.getDouble("총_단백질"));
		            stats.setTotalFat(rs.getDouble("총_지방"));
		            return stats;
		        }
		    });

		    return statsList;
		}
	
	// 회원ID, 날짜까지 선택 후 식단 조회
	@Override
	public List<MealVO> getDivSeqFoodCode(MealVO meal) throws ClassNotFoundException, SQLException {
		List<MealVO> outList = new ArrayList<>();
		
		StringBuilder sb = new StringBuilder(200);
		sb.append(" SELECT             \n");
		sb.append("     m_id,          \n");
		sb.append("     m_date,        \n");
		sb.append("     m_div,         \n");
		sb.append("     m_seq,         \n");
		sb.append("     f_code         \n");
		sb.append(" FROM               \n");
		sb.append("     meal           \n");
		sb.append(" WHERE              \n");
		sb.append("         m_id =   ? \n");
		sb.append("     AND m_date = ? \n");

		LOG.debug("1. sql=\n" + sb.toString());
		LOG.debug("2. param=" + meal);

		// param
		Object[] args = { meal.getId(), meal.getDate()};
		outList = this.jdbcTemplate.query(sb.toString(), new RowMapper<MealVO>() {

			@Override
			public MealVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				MealVO out = new MealVO();
				
				out.setId(rs.getString("m_id"));
				out.setDate(rs.getString("m_date"));
				out.setDiv(rs.getString("m_div"));
				out.setSeq(rs.getInt("m_seq"));
				out.setCode(rs.getString("f_code"));

				return out;
			}

		}, args);

		LOG.debug("3.outVO:" + outList);
		
		return outList;
	}

	// 회원ID, 날짜, 식사구분까지 선택 후 식단 조회
	@Override
	public List<MealVO> getSeqFoodCode(MealVO meal) throws SQLException {
		List<MealVO> outList = new ArrayList<>();

		StringBuilder sb = new StringBuilder(200);
		sb.append(" SELECT             \n");
		sb.append("     m_id,          \n");
		sb.append("     m_date,        \n");
		sb.append("     m_div,         \n");
		sb.append("     m_seq,         \n");
		sb.append("     f_code         \n");
		sb.append(" FROM               \n");
		sb.append("     meal           \n");
		sb.append(" WHERE              \n");
		sb.append("         m_id =   ? \n");
		sb.append("     AND m_date = ? \n");
		sb.append("     AND m_div =  ? \n");

		LOG.debug("1. sql=\n" + sb.toString());
		LOG.debug("2. param=" + meal);

		// param
		Object[] args = { meal.getId(), meal.getDate(), meal.getDiv() };
		outList = this.jdbcTemplate.query(sb.toString(), new RowMapper<MealVO>() {

			@Override
			public MealVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				MealVO out = new MealVO();

				out.setId(rs.getString("m_id"));
				out.setDate(rs.getString("m_date"));
				out.setDiv(rs.getString("m_div"));
				out.setSeq(rs.getInt("m_seq"));
				out.setCode(rs.getString("f_code"));

				return out;
			}

		}, args);

		return outList;
	}

	// 회원ID, 날짜, 식사구분, SEQ까지 선택 후 식단 조회 (단 건 조회)
	@Override
	public MealVO getFoodCode(MealVO meal) throws ClassNotFoundException, SQLException {
		MealVO outVO = null;

		StringBuilder sb = new StringBuilder(200);
		sb.append(" SELECT             \n");
		sb.append("     m_id,          \n");
		sb.append("     m_date,        \n");
		sb.append("     m_div,         \n");
		sb.append("     m_seq,         \n");
		sb.append("     f_code         \n");
		sb.append(" FROM               \n");
		sb.append("     meal           \n");
		sb.append(" WHERE              \n");
		sb.append("         m_id =   ? \n");
		sb.append("     AND m_date = ? \n");
		sb.append("     AND m_div =  ? \n");
		sb.append("     AND m_seq =  ? \n");

		LOG.debug("1. sql=\n" + sb.toString());
		LOG.debug("2. param=" + meal);

		// param
		Object[] args = { meal.getId(), meal.getDate(), meal.getDiv(), meal.getSeq() };
		outVO = this.jdbcTemplate.queryForObject(sb.toString(), new RowMapper<MealVO>() {

			@Override
			public MealVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				MealVO out = new MealVO();

				out.setId(rs.getString("m_id"));
				out.setDate(rs.getString("m_date"));
				out.setDiv(rs.getString("m_div"));
				out.setSeq(rs.getInt("m_seq"));
				out.setCode(rs.getString("f_code"));

				return out;
			}

		}, args);

		LOG.debug("3.outVO:" + outVO);

		return outVO;
	}

	// 삭제
	@Override
	public int deleteOne(final MealVO meal) throws SQLException {
		int flag = 0;

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
	@Override
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
