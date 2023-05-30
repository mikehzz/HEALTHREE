package com.pcwk.ehr;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.sql.SQLException;

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
public class MemberDaoTest {
	final Logger LOG = LogManager.getLogger(getClass());

	@Autowired
	ApplicationContext context; // 테스트 오브젝트가 만들어지고 나면 스프링 테스트 컨텍스트에 의해 자동으로 갑이 주입된다.

	MemberDao dao;
	MemberVO memberVO;

	// 테스트를 수행하는데 필요한 정보나 오브젝트: fixture
	MemberVO member01;

	@Before
	public void setUp() {
		LOG.debug("*************************");
		LOG.debug("*context*" + context);
		LOG.debug("*************************");

		dao = context.getBean("memberDao", MemberDao.class);

		// null이 아니면 성공
		assertNotNull(context);
		assertNotNull(dao);

		memberVO = new MemberVO("kjm", "1234", "정민", "kimjmini1122@gmail.com", "F", "921122", 180, 50, 55, 2, 3);
		
		member01 = new MemberVO("kjm", "1234", "주영", "juyoung@gmail.com", "F", "940405", 185, 60, 65, 3, 3);
	}

	@After
	public void tearDown() {
		LOG.debug("--------------");
		LOG.debug("-tearDown-");
		
		LOG.debug("--------------");
	}
	
	// 수정
	public void update() throws SQLException, ClassNotFoundException {
		LOG.debug("===================");
		LOG.debug("update()");
		LOG.debug("===================");
		
		// 1. 데이터 삭제
		dao.deleteOne(member01);
		
		// 1-1. 남은 건수 확인
		assertEquals(dao.getCount(memberVO), 0);
		
		// 2. 데이터 입력
		dao.add(member01);
		
		// 3. 등록 데이터 조회
		MemberVO getVO = dao.get(member01);
		
//		// 3.1. 데이터 비교
//		isSameUser(member01, getVO);
		
		// 4. 조회 데이터 수정
		String upStr = "_U";
		int upInt = 10;
		
//		// 문자열_U, 숫자+10
//		getVO.setName(getVO.getName() + upStr);
//		getVO.setPasswd(getVO.getPasswd() + upStr);
//		getVO.setLevel(Level.SILVER);
//		getVO.setLogin(getVO.getLogin() + upInt);
//		getVO.setRecommend(getVO.getRecommend() + upInt);
//		getVO.setEmail(getVO.getEmail() + upStr);
		
		// 5. 수정 수행
		int flag = dao.update(getVO);
		assertEquals(flag, 1);
		
//		// 6. 조회
//		UserVO fVO = dao.get(getVO);		
//		
//		// 6.1. 데이터 비교
//		isSameUser(fVO, getVO);
	}
	
	
	// 삭제
	@Test
	@Ignore
	public void delete() throws ClassNotFoundException, SQLException {
		LOG.debug("=====================");
		LOG.debug("=delete()==");
		LOG.debug("=====================");
		
		// 삭제
		dao.deleteOne(member01);		

	}
	
	// 추가
	@Test
	@Ignore
	public void add() throws ClassNotFoundException, SQLException {
		LOG.debug("=====================");
		LOG.debug("=add()==");
		LOG.debug("=====================");
		
		// 삭제
		dao.deleteOne(member01);		
		
		//추가
		dao.add(member01);
	}

}
