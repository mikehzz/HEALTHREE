package com.pcwk.ehr;

import static org.junit.Assert.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.FixMethodOrder;
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
public class PostDaoTest {
	final Logger LOG = LogManager.getLogger(getClass());

	@Autowired
	ApplicationContext context;

	PostDao dao;
	PostVO postVO;

	// 테스트를 수행하는데 필요한 정보나 오브젝트: fixture
	PostVO post01;

	@Before
	public void setUp() {
		LOG.debug("*************************");
		LOG.debug("*context*" + context);
		LOG.debug("*************************");

		dao = context.getBean("postDao", PostDao.class);

		// null이 아니면 성공
		assertNotNull(context);
		assertNotNull(dao);

		postVO = new PostVO();

		post01 = new PostVO();
	}

}
