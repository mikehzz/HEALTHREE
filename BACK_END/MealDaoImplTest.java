package com.pcwk.ehr;

import static org.junit.Assert.*;

import java.sql.ResultSet;
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
import org.springframework.jdbc.core.RowMapper;
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

	@Before
	public void setUp() {
		LOG.debug("*************************");
		LOG.debug("*context*" + context);
		LOG.debug("*************************");

		dao = context.getBean("mealDao", MealDaoImpl.class);

		// null이 아니면 성공
		assertNotNull(context);
		assertNotNull(dao);

		mealVO = new MealVO("duck77", "2023-05-30", "D", 1, "D000237");

		meal01 = new MealVO("duck77", "2023-05-30", "D", 1, "D000237");
		meal02 = new MealVO("duck77", "2023-05-30", "D", 2, "D000474");
		meal03 = new MealVO("duck77", "2023-05-30", "M", 1, "D000474");
		meal04 = new MealVO("duck77", "2023-05-31", "L", 1, "D000237");
	}

	@After
	public void tearDown() {
		LOG.debug("--------------");
		LOG.debug("-tearDown-");

		LOG.debug("--------------");
	}

	// div+seq+코드 조회
	@Test
	@Ignore
	public void getDivSeqFoodCode() throws ClassNotFoundException, SQLException {
		LOG.debug("=====================");
		LOG.debug("=getDivSeqFoodCode()==");
		LOG.debug("=====================");

		// getDivSeqFoodCode()
		List<MealVO> list = dao.getDivSeqFoodCode(mealVO);
		
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

	// seq+코드 조회
	@Test
	//@Ignore
	public void getSeqFoodCode() throws ClassNotFoundException, SQLException {
		LOG.debug("=====================");
		LOG.debug("=getSeqFoodCode()==");
		LOG.debug("=====================");

		// getSeqFoodCode()
		List<MealVO> list = dao.getSeqFoodCode(mealVO);

		// 2건 확인
		assertEquals(list.size(), 2);
		for (MealVO vo : list) {
			LOG.debug(vo.toString());
		}

		// 비교
		isSameMeal(meal01, list.get(0));
		isSameMeal(meal02, list.get(1));


	}

	// 코드조회
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
		dao.deleteOne(meal04);

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
		dao.deleteOne(meal04);

		// 추가
		dao.add(meal01);
		dao.add(meal02);
		dao.add(meal03);
		dao.add(meal04);
	}

}
