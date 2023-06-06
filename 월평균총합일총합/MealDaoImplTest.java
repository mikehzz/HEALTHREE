package com.pcwk.ehr;
import static org.junit.Assert.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import com.pcwk.ehr.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
@RunWith(SpringJUnit4ClassRunner.class) // 스프링 테스트 컨텍스 프레임워크의 JUnit확장 기능 지정
@ContextConfiguration(locations = "/applicationContext.xml") // 테스트 컨텍스트가 자동으로 만들어줄 applicationContext 위치
@FixMethodOrder(MethodSorters.NAME_ASCENDING) // @Test 메소드를 오름차순으로 정렬한 순서대로 실행
public class MealDaoImplTest {
	final Logger LOG = LogManager.getLogger(getClass());
	@Autowired
	ApplicationContext context; // 테스트 오브젝트가 만들어지고 나면 스프링 테스트 컨텍스트에 의해 자동으로 갑이 주입된다.
	
	MealDao dao;
	MealVO mealVO;
	// 테스트를 수행하는데 필요한 정보나 오브젝트: fixture
	MealVO meal01;
	MealVO meal02;
	MealVO meal03;
	MealVO meal04;
	MealVO meal05;
	MealVO meal06;
	MealVO meal07;
	MealVO meal08;
	MealVO meal09;
	MealVO meal10;
	@Before
	public void setUp() {
		LOG.debug("*************************");
		LOG.debug("*context*" + context);
		LOG.debug("*************************");
		dao = context.getBean("mealDao", MealDaoImpl.class);
		
		// null이 아니면 성공
		assertNotNull(context);
		assertNotNull(dao);
		mealVO = new MealVO("tester00", "20230522", "B", 1, "D00001");
		meal01 = new MealVO("tester00", "20230522", "B", 1, "D00001");
		meal02 = new MealVO("tester00", "20230522", "B", 2, "D00002");
		meal03 = new MealVO("tester00", "20230522", "B", 3, "D00003");
		meal04 = new MealVO("tester00", "20230522", "D", 1, "D00007");
		meal05 = new MealVO("tester00", "20230522", "D", 2, "D00008");
		meal06 = new MealVO("tester00", "20230522", "D", 3, "D00009");
		meal07 = new MealVO("tester00", "20230522", "L", 1, "D00004");
		meal08 = new MealVO("tester00", "20230522", "L", 2, "D00005");
		meal09 = new MealVO("tester00", "20230522", "L", 3, "D00006");
		meal10 = new MealVO("tester00", "20230522", "S", 1, "D00010");
	}
	@After
	public void tearDown() {
		LOG.debug("--------------");
		LOG.debug("-tearDown-");
		LOG.debug("--------------");
	}
	// 월합
		@Test
		public void testGetMonthStats() throws SQLException {

			// 예상 결과 값
			double expectedTotalCaloriesUser1 = 0; // (100 + 200) + (100 + 300) = 600
			double expectedTotalCarbohydratesUser1 = 0; // (10 + 15) + (10 + 20) = 45
			double expectedTotalProteinUser1 = 0; // (5 + 7) + (5 + 10) = 22
			double expectedTotalFatUser1 = 0; // (2 + 3) + (2 + 5) = 10

			// 테스트 실행
			List<FoodVO> statsListUser1 = dao.getMonthStats("tester00", "B");
			assertEquals(2, statsListUser1.size());

			FoodVO statsUser1 = statsListUser1.get(0);
			assertEquals(expectedTotalCaloriesUser1, statsUser1.getTotalCalories(), 0);
			assertEquals(expectedTotalCarbohydratesUser1, statsUser1.getTotalCarbohydrates(), 0);
			assertEquals(expectedTotalProteinUser1, statsUser1.getTotalProtein(), 0);
			assertEquals(expectedTotalFatUser1, statsUser1.getTotalFat(), 0);
		}
	
	//월 일일평균
	@Test
	public void testGetMonthAvg() throws SQLException {
	    // 예상 결과 값
	    double expectedAverageCaloriesUser1 = 1054.966;
	    double expectedAverageCarbohydratesUser1 = 49.852;
	    double expectedAverageProteinUser1 = 98.094;
	    double expectedAverageFatUser1 = 51.486;
	    // 테스트 실행
	    List<FoodVO> statsListUser1 = dao.getMonthAvg("tester00", "B");
	    assertEquals(2, statsListUser1.size());
	    FoodVO statsUser1 = statsListUser1.get(0);
	    assertEquals(expectedAverageCaloriesUser1, statsUser1.getF_cal(), 0);
	    assertEquals(expectedAverageCarbohydratesUser1, statsUser1.getF_carbo(), 0);
	    assertEquals(expectedAverageProteinUser1, statsUser1.getF_protein(), 0);
	    assertEquals(expectedAverageFatUser1, statsUser1.getF_fat(), 0);
	}
	// 회원ID, 날짜까지 선택 후 식단 조회
	
	
	//일일총합
	@Test
	public void testGetDayStats() {
	    // 예상 결과 값
	    double expectedTotalCaloriesUser1 = 0;//1463.19; // (100 + 200) + (100 + 300) = 600
	    double expectedTotalCarbohydratesUser1 = 0;//5.71; // (10 + 15) + (10 + 20) = 45
	    double expectedTotalProteinUser1 = 0;//164.44; // (5 + 7) + (5 + 10) = 22
	    double expectedTotalFatUser1 = 0; //87.15; // (2 + 3) + (2 + 5) = 10

	    // 테스트 실행
	    List<FoodVO> statsListUser1;
	    try {
	        statsListUser1 = dao.getDayStats("tester00", "B");
	        assertEquals(14, statsListUser1.size());
	        FoodVO statsUser1 = statsListUser1.get(0);
	        assertEquals(expectedTotalCaloriesUser1, statsUser1.getTotalCalories(), 0);
	        assertEquals(expectedTotalCarbohydratesUser1, statsUser1.getTotalCarbohydrates(), 0);
	        assertEquals(expectedTotalProteinUser1, statsUser1.getTotalProtein(), 0);
	        assertEquals(expectedTotalFatUser1, statsUser1.getTotalFat(), 0);
	    } catch (SQLException e) {
	        // SQLException 처리
	        // 예외 처리 로직 작성
	        // ...
	        fail("SQLException occurred during the test: " + e.getMessage());
	    }
	}
	// 회원ID, 날짜까지 선택 후 식단 조회
	@Test
	@Ignore
	public void getDivSeqFoodCode() throws ClassNotFoundException, SQLException {
		LOG.debug("=====================");
		LOG.debug("=getDivSeqFoodCode()==");
		LOG.debug("=====================");
		// getDivSeqFoodCode()
		List<MealVO> list = dao.getDivSeqFoodCode(mealVO);
		
		// 10건 확인
		assertEquals(list.size(), 10);
		for (MealVO vo : list) {
			LOG.debug(vo.toString());
		}
		
		// 비교
		isSameMeal(meal01, list.get(0));
		isSameMeal(meal02, list.get(1));
		isSameMeal(meal03, list.get(2));
		isSameMeal(meal04, list.get(3));
		isSameMeal(meal05, list.get(4));
		isSameMeal(meal06, list.get(5));
		isSameMeal(meal07, list.get(6));
		isSameMeal(meal08, list.get(7));
		isSameMeal(meal09, list.get(8));
		isSameMeal(meal10, list.get(9));
	}
	// 회원ID, 날짜, 식사구분까지 선택 후 식단 조회
	@Test
	@Ignore
	public void getSeqFoodCode() throws ClassNotFoundException, SQLException {
		LOG.debug("=====================");
		LOG.debug("=getSeqFoodCode()==");
		LOG.debug("=====================");
		// getSeqFoodCode()
		List<MealVO> list = dao.getSeqFoodCode(mealVO);
		// 3건 확인
		assertEquals(list.size(), 3);
		for (MealVO vo : list) {
			LOG.debug(vo.toString());
		}
		// 비교
		isSameMeal(meal01, list.get(0));
		isSameMeal(meal02, list.get(1));
		isSameMeal(meal03, list.get(2));
	}
	// 회원ID, 날짜, 식사구분, SEQ까지 선택 후 식단 조회 (단 건 조회)
	@Test
	@Ignore
	public void getFoodCode() throws ClassNotFoundException, SQLException {
		LOG.debug("=====================");
		LOG.debug("=getFoodCode()==");
		LOG.debug("=====================");
		// getFoodCode
		MealVO outMeal01 = dao.getFoodCode(meal01);
		// 비교
		isSameMeal(outMeal01, meal01);
	}
	// 테스트 검증 자동화
	public void isSameMeal(MealVO outVO, MealVO mealVO) {
		assertEquals(outVO.getId(), mealVO.getId());
		assertEquals(outVO.getDate(), mealVO.getDate());
		assertEquals(outVO.getDiv(), mealVO.getDiv());
		assertEquals(outVO.getSeq(), mealVO.getSeq());
		assertEquals(outVO.getCode(), mealVO.getCode());
	}
	// 삭제
	@Test
	@Ignore
	public void delete() throws ClassNotFoundException, SQLException {
		LOG.debug("=====================");
		LOG.debug("=delete()=");
		LOG.debug("=====================");
		// 삭제
		dao.deleteOne(meal01);
		dao.deleteOne(meal02);
		dao.deleteOne(meal03);
	}
	// 추가
	@Test
	@Ignore
	public void add() throws ClassNotFoundException, SQLException {
		LOG.debug("=====================");
		LOG.debug("=add()==");
		LOG.debug("=====================");
		// 삭제
		dao.deleteOne(meal01);
		dao.deleteOne(meal02);
		dao.deleteOne(meal03);
		// 추가
		dao.add(meal01);
		dao.add(meal02);
		dao.add(meal03);
	}
}