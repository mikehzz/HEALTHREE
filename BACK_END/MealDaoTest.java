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
public class MealDaoTest {

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

		dao = context.getBean("mealDao", MealDao.class);

		// null이 아니면 성공
		assertNotNull(context);
		assertNotNull(dao);

		mealVO = new MealVO("duck77", "20230530", "D", 1, "D00001");

		meal01 = new MealVO("duck77", "20230530", "D", 1, "D00001");
		meal02 = new MealVO("duck77", "20230530", "D", 2, "D00002");
		meal03 = new MealVO("duck77", "20230530", "M", 1, "D00002");
		meal04 = new MealVO("duck77", "20230531", "L", 1, "D00001");
	}

	@After
	public void tearDown() {
		LOG.debug("--------------");
		LOG.debug("-tearDown-");

		LOG.debug("--------------");
	}

	// div+seq+코드 조회
	@Test
	public void getDivSeqFoodCode() throws ClassNotFoundException, SQLException {
		LOG.debug("=====================");
		LOG.debug("=getDivSeqFoodCode()==");
		LOG.debug("=====================");

		List<MealVO> list = dao.getDivSeqFoodCode(mealVO);

		for (MealVO vo : list) {
			LOG.debug(vo.toString());
		}
	}

	// seq+코드 조회
	@Test
	@Ignore
	public void getSeqFoodCode() throws ClassNotFoundException, SQLException {
		LOG.debug("=====================");
		LOG.debug("=getSeqFoodCode()==");
		LOG.debug("=====================");

		List<MealVO> list = dao.getSeqFoodCode(mealVO);

		for (MealVO vo : list) {
			LOG.debug(vo.toString());
		}
	}

	// 코드조회
	@Test
	@Ignore
	public void getFoodCode() throws ClassNotFoundException, SQLException {
		LOG.debug("=====================");
		LOG.debug("=getFoodCode()==");
		LOG.debug("=====================");

		dao.getFoodCode(mealVO);
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
