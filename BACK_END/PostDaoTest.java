package com.pcwk.ehr;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hamcrest.core.IsSame;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.pcwk.ehr.PostDao;
import com.pcwk.ehr.PostVO;

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

		postVO = new PostVO("2023060100000077777777777777777777777777", "duck77", "오리는", 20, "꽥꽥");

		post01 = new PostVO("2023060100000077777777777777777777777777", "duck77", "오리는", 20, "꽤액");

	} // setUp()

	@After
	public void tearDown() {
		LOG.debug("--------------");
		LOG.debug("-tearDown-");
		LOG.debug("--------------");
	} // tearDown()

	// 조회
	@Test
	@Ignore
	public void get() throws EmptyResultDataAccessException, ClassNotFoundException, SQLException {
		LOG.debug("=====================");
		LOG.debug("=get()=");
		LOG.debug("=====================");

		// 1. 데이터 삭제
		dao.deleteOne(post01);

		// 2. 추가
		dao.add(post01);

		// 데이터 조회
		PostVO outPost01 = dao.get(post01);

		isSamePost(outPost01, post01);

	} // get()

	// 수정
	@Test
	//@Ignore
	public void update() throws ClassNotFoundException, SQLException {
		LOG.debug("=====================");
		LOG.debug("=update()=");
		LOG.debug("=====================");

		// 1. 데이터 삭제
		dao.deleteOne(post01);

		// getCount 테스트
		// assertEquals();

		// 2. 추가
		dao.add(post01);

		// 3. 등록 데이터 조회
		PostVO getVO = dao.get(post01);

		// 3.1. 데이터 비교
		isSamePost(post01, getVO);

		// 4. 조회 데이터 수정
		String upStr = "_U";
		int upInt = 10;

		// 문자열_U, 숫자 + 10
		getVO.setTitle(getVO.getTitle() + upStr);
//		getVO.setAtchFileId(getVO.getAtchFileId() + upStr);
		getVO.setDiv(getVO.getDiv() + upInt);
		getVO.setContents(getVO.getContents() + upStr);

		// 5. 수정 수행
		int flag = dao.update(getVO);
		assertEquals(flag, 1);

		// 6. 조회
		PostVO fVO = dao.get(getVO);

		// 6.1. 데이터 비교
		isSamePost(fVO, getVO);

	} // update()

	public void isSamePost(PostVO outVO, PostVO postVO) {
		assertEquals(outVO.getSeq(), postVO.getSeq());
		assertEquals(outVO.getId(), postVO.getId());
		assertEquals(outVO.getTitle(), postVO.getTitle());
		assertEquals(outVO.getDiv(), postVO.getDiv());
		assertEquals(outVO.getContents(), postVO.getContents());
	} // isSamePost()

	// 삭제
	@Test
	@Ignore
	public void delete() throws ClassNotFoundException, SQLException {
		LOG.debug("=====================");
		LOG.debug("=delete()=");
		LOG.debug("=====================");

		dao.deleteOne(post01);

	} // delete()

	// 추가
	@Test
	@Ignore
	public void add() throws ClassNotFoundException, SQLException {
		LOG.debug("=====================");
		LOG.debug("=add()=");
		LOG.debug("=====================");

		// 추가
		dao.add(post01);
	} // add()

} // class end