package com.pcwk.ehr;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.List;

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
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class) // 스프링 테스트 컨텍스 프레임워크의 JUnit확장 기능 지정
@ContextConfiguration(locations = "/applicationContext.xml") // 테스트 컨텍스트가 자동으로 만들어줄 applicationContext 위치
@FixMethodOrder(MethodSorters.NAME_ASCENDING) // @Test 메소드를 오름차순으로 정렬한 순서대로 실행
public class FoodDaoTest {

	final Logger LOG = LogManager.getLogger(getClass());

	@Autowired
	ApplicationContext context; // 테스트 오브젝트가 만들어지고 나면 스프링 테스트 컨텍스트에 의해 자동으로 갑이 주입된다.

	FoodDao dao;

	FoodVO foodVO;
	MealVO mealVO;

	// 테스트를 수행하는데 필요한 정보나 오브젝트: fixture
	FoodVO food01;

	@Before
	public void setUp() {
		LOG.debug("*************************");
		LOG.debug("*context*" + context);
		LOG.debug("*************************");

		dao = context.getBean("foodDao", FoodDao.class);

		// null이 아니면 성공
		assertNotNull(context);
		assertNotNull(dao);

		foodVO = new FoodVO("D00001", "가자미구이", 200, 314, 3.5, 43.2, 14.2, 0.4, 1331.18, 225.55);
		mealVO = new MealVO("tester00", "20230522", "B", 1, "D00001");

		food01 = new FoodVO("D00001", "가자미구이", 200, 314, 3.5, 43.2, 14.2, 0.4, 1331.18, 225.55);
	}

	// 저번 주 칼로리 (꺾은 선 그래프)
	@Test
	@Ignore
	public void getCalLastWeek() throws ClassNotFoundException, SQLException {
		LOG.debug("=====================");
		LOG.debug("=getCalLastWeek()==");
		LOG.debug("=====================");

		// getCalLastWeek()
		List<FoodVO> list = dao.getCalLastWeek(mealVO, foodVO);

		// 7건 확인
		assertEquals(list.size(), 7);
		for (FoodVO vo : list) {
			LOG.debug(vo.toString());
		}
	}

	// 이번 주 칼로리 (꺾은 선 그래프)
	@Test
	@Ignore
	public void getCalThisWeek() throws ClassNotFoundException, SQLException {
		LOG.debug("=====================");
		LOG.debug("=getCalLastWeek()==");
		LOG.debug("=====================");

		// getCalLastWeek()
		List<FoodVO> list = dao.getCalThisWeek(mealVO, foodVO);

		// 7건 확인
		assertEquals(list.size(), 7);
		for (FoodVO vo : list) {
			LOG.debug(vo.toString());
		}
	}

	// 저번 주 탄/단/지/당/나/콜 (표)
	@Test
	@Ignore
	public void getNutrientLastWeek() throws ClassNotFoundException, SQLException {
		LOG.debug("=======================");
		LOG.debug("=getNutrientLastWeek()=");
		LOG.debug("=======================");

		// getNutrientLastWeek()
		FoodVO outVO = dao.getNutrientLastWeek(foodVO, mealVO);

		// 비교 (delta(오차허용범위): 값들 간의 차이가 0.01 이하인 경우 같은 값으로 간주)
		assertEquals(outVO.getF_carbo(), 1584.26, 0.01);
		assertEquals(outVO.getF_protein(), 2386.39, 0.01);
		assertEquals(outVO.getF_fat(), 1487.7, 0.01);
		assertEquals(outVO.getF_sugar(), 371.3, 0.01);
		assertEquals(outVO.getF_na(), 107359.12, 0.01);
		assertEquals(outVO.getF_cole(), 12164.16, 0.01);
	}

	// 이번 주 탄/단/지/당/나/콜 (표)
	@Test
	@Ignore
	public void getNutrientThisWeek() throws ClassNotFoundException, SQLException {
		LOG.debug("=======================");
		LOG.debug("=getNutrientThisWeek()=");
		LOG.debug("=======================");

		// getNutrientThisWeek()
		FoodVO outVO = dao.getNutrientThisWeek(foodVO, mealVO);

		// 비교 (delta(오차허용범위): 값들 간의 차이가 0.01 이하인 경우 같은 값으로 간주)
		assertEquals(outVO.getF_carbo(), 2255.48, 0.01);
		assertEquals(outVO.getF_protein(), 1484.43, 0.01);
		assertEquals(outVO.getF_fat(), 1096.89, 0.01);
		assertEquals(outVO.getF_sugar(), 351.23, 0.01);
		assertEquals(outVO.getF_na(), 82824.28, 0.01);
		assertEquals(outVO.getF_cole(), 6768.35, 0.01);

	}

	// 저번 주 탄/단/지 (막대 그래프)
	@Test
	@Ignore
	public void getThreeLastWeek() throws ClassNotFoundException, SQLException {
		LOG.debug("====================");
		LOG.debug("=getThreeLastWeek()=");
		LOG.debug("====================");

		// getThreeLastWeek()
		List<FoodVO> list = dao.getThreeLastWeek(foodVO, mealVO);

		// 7건 확인
		assertEquals(list.size(), 7);
		for (FoodVO vo : list) {
			LOG.debug(vo.toString());
		}
	}

	// 이번 주 탄/단/지 (막대 그래프)
	@Test
	@Ignore
	public void getThreeThisWeek() throws ClassNotFoundException, SQLException {
		LOG.debug("====================");
		LOG.debug("=getThreeThisWeek()=");
		LOG.debug("====================");

		// getThreeThisWeek()
		List<FoodVO> list = dao.getThreeThisWeek(foodVO, mealVO);

		// 7건 확인
		assertEquals(list.size(), 7);
		for (FoodVO vo : list) {
			LOG.debug(vo.toString());
		}
	}
	
	//일일  섭취한 총 칼로리/탄/단/지 계산	
	@Test
	//@Ignore
	public void getsumThisDay() throws ClassNotFoundException, SQLException {
		LOG.debug("====================");
		LOG.debug("=getsumThisDay()=");
		LOG.debug("====================");

		// getsumThisDay()
		List<FoodVO> list = dao.getsumThisDay(foodVO, mealVO);

		// 7건 확인
		//assertEquals(list.size(), 7);
		for (FoodVO vo : list) {
			LOG.debug(vo.toString());
		}
	}
	
	//일일 평균 섭취한 칼로리/탄/단/지 계산
	@Test
	@Ignore
	public void getavgThisDay() throws ClassNotFoundException, SQLException {
		LOG.debug("====================");
		LOG.debug("=getavgThisDay()=");
		LOG.debug("====================");

		// getavgThisDay()
		List<FoodVO> list = dao.getavgThisDay(foodVO, mealVO);

		// 7건 확인
		assertEquals(list.size(), 7);
		for (FoodVO vo : list) {
			LOG.debug(vo.toString());
		}
	}
	
	//월별 섭취한 총 칼로리/탄/단/지 계산	
	@Test
	@Ignore
	public void getsumThisMonth() throws ClassNotFoundException, SQLException {
		LOG.debug("====================");
		LOG.debug("=getsumThisMonth()=");
		LOG.debug("====================");

		// getsumThisMonth()
		List<FoodVO> list = dao.getsumThisMonth(foodVO, mealVO);

		// 7건 확인
		assertEquals(list.size(), 7);
		for (FoodVO vo : list) {
			LOG.debug(vo.toString());
		}
	}
	

	@After
	public void tearDown() {
		LOG.debug("--------------");
		LOG.debug("-tearDown-");
		LOG.debug("--------------");
	}

}
