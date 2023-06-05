package com.pcwk.ehr;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

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

import com.pcwk.ehr.MemberDao;
import com.pcwk.ehr.MemberDaoImpl;
import com.pcwk.ehr.MemberVO;

@RunWith(SpringJUnit4ClassRunner.class) // 스프링 테스트 컨텍스 프레임워크의 JUnit확장 기능 지정
@ContextConfiguration(locations = "/applicationContext.xml") // 테스트 컨텍스트가 자동으로 만들어줄 applicationContext 위치
@FixMethodOrder(MethodSorters.NAME_ASCENDING) // @Test 메소드를 오름차순으로 정렬한 순서대로 실행
public class MemberDaoImplTest {
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

		dao = context.getBean("memberDao", MemberDaoImpl.class);

		// null이 아니면 성공
		assertNotNull(context);
		assertNotNull(dao);

		memberVO = new MemberVO("tester00", "181844", "테스터", "greenlight@gmail.com", "F", "2000-01-02", 170, 60, 63, 4, 3);
		
		member01 = new MemberVO("tester00", "181844", "테스터", "greenlight@gmail.com", "F", "2000-01-02", 170, 60, 63, 4, 3);
	}

	@After
	public void tearDown() {
		LOG.debug("--------------");
		LOG.debug("-tearDown-");
		LOG.debug("--------------");
	}

	// 목표체중
	@Test
	public void targetWeight() throws SQLException, ClassNotFoundException {
	    LOG.debug("===================");
	    LOG.debug("=targetWeight()=");
	    LOG.debug("===================");

	    // 1. 데이터 삭제
	    dao.deleteOne(member01);

	    // 1-1. 남은 건수 확인
	    assertEquals(dao.getCount(memberVO), 0);

	    // 2. 데이터 입력
	    dao.add(member01);

	    // 3. 등록 데이터 조회
	    MemberVO getVO = dao.get(member01);

	    // 3.1. 데이터 비교
	    isSameUser(member01, getVO);

	    // 4. 목표 체중 조회
	    double goalWeight = dao.targetWeight(member01);

	    // 4.1. 예상된 목표 체중 값과 비교
	    assertEquals(memberVO.getTargetWeight(), goalWeight, 0.001);

	} // targetWeight()

	
	// 권장 칼로리
	@Test
	@Ignore
	public void myCal() throws SQLException, ClassNotFoundException {
		LOG.debug("===================");
		LOG.debug("=myCal()=");
		LOG.debug("===================");
		
		// 1. 데이터 삭제
		dao.deleteOne(member01);
		
		// 1-1. 남은 건수 확인
		assertEquals(dao.getCount(memberVO), 0);
		
		// 2. 데이터 입력
		dao.add(member01);
		
		// 3. 등록 데이터 조회
		MemberVO getVO = dao.get(member01);
		
		// 3.1. 데이터 비교
		isSameUser(member01, getVO);
		
		// 데이터 조회
		dao.myCalGoal(member01);
		
	
	} // myCal()
	
	
	// 수정
	@Test
	@Ignore
	public void update() throws SQLException, ClassNotFoundException {
		LOG.debug("===================");
		LOG.debug("=update()=");
		LOG.debug("===================");
		
		// 1. 데이터 삭제
		dao.deleteOne(member01);
		
		// 1-1. 남은 건수 확인
		assertEquals(dao.getCount(memberVO), 0);
		
		// 2. 데이터 입력
		dao.add(member01);
		
		// 3. 등록 데이터 조회
		MemberVO getVO = dao.get(member01);
		
		// 3.1. 데이터 비교
		isSameUser(member01, getVO);
		
		// 4. 조회 데이터 수정
		String upStr = "_U";
		int upInt = 5;
		
		// 문자열_U, 숫자 +5(data type 숫자 1자리라서)
		getVO.setPw(getVO.getPw() + upStr);
		getVO.setName(getVO.getName() + upStr);
		getVO.setEmail(getVO.getEmail() + upStr);
		getVO.setGender(getVO.getGender());  	// _U 못 붙임
		getVO.setBirthday(getVO.getBirthday()); // _U 못 붙임
		getVO.setHeight(getVO.getHeight() + upInt);
		getVO.setWeight(getVO.getWeight() + upInt);
		getVO.setTargetWeight(getVO.getTargetWeight() + upInt);
		getVO.setActLevel(getVO.getActLevel() + upInt);
		getVO.setDietGoal(getVO.getDietGoal() + upInt);
		getVO.setId(getVO.getId());
		
		// 5. 수정 수행
		int flag = dao.update(getVO);
		assertEquals(flag, 1);
		
		// 6. 조회
		MemberVO fVO = dao.get(getVO);		
		
		// 6.1. 데이터 비교
		isSameUser(fVO, getVO);
	} // update()
	
	
	public void isSameUser(MemberVO outVO, MemberVO memberVO) {
		// 테스트 검증 자동화
		assertEquals(outVO.getId(), memberVO.getId());
		assertEquals(outVO.getPw(), memberVO.getPw());
		assertEquals(outVO.getName(), memberVO.getName());
		assertEquals(outVO.getEmail(), memberVO.getEmail());
		assertEquals(outVO.getGender(), memberVO.getGender());
		assertEquals(outVO.getBirthday(), memberVO.getBirthday());
	}

	
	// 삭제
	@Test
	@Ignore
	public void delete() throws ClassNotFoundException, SQLException {
		LOG.debug("=====================");
		LOG.debug("=delete()=");
		LOG.debug("=====================");
		
		// 삭제
		dao.deleteOne(member01);		

	} // delete()
	
	
	// 추가
	@Test
	@Ignore
	public void add() throws ClassNotFoundException, SQLException {
		LOG.debug("=====================");
		LOG.debug("=add()=");
		LOG.debug("=====================");
		
		// 삭제
		dao.deleteOne(member01);		
		
		// 추가
		dao.add(member01);
	} // add()
	
	
	// 단건 조회
	@Test
	@Ignore
	public void get() throws ClassNotFoundException, SQLException{
		LOG.debug("=====================");
		LOG.debug("=get()=");
		LOG.debug("=====================");
		
		// 삭제
		dao.deleteOne(member01);
		
		// getCount 테스트
		assertEquals(dao.getCount(memberVO), 0);
		
		// 추가
		dao.add(member01);
		
		// 데이터 조회
		MemberVO outMember01 = dao.get(member01);
		isSameUser(outMember01, member01);
		
	} // get()

} // class end
