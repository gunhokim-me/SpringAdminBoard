package kr.or.ddit.user.repository;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import kr.or.ddit.paging.model.PageVo;
import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.user.test.config.ModelTestConfig;

public class UserDaoTest extends ModelTestConfig {

	@Resource(name="userDao")
	private UserDao userDao;
	

	@Before
	public void setup() {
		
		UserVo vo = new UserVo("testUser", "테스트사용자", "testUserPass", new Date(),"대덕", "대전 중구 중앙로 76", "4층", "34940","brown.png","uudi-generated-filename.png");
		
		userDao.registUser(vo);
		
		userDao.deleteUser("ddit");
	}
	
	@After
	public void tesrDown() {
		userDao.deleteUser("testUser");
	}
	
	@Test
	public void userLogintest() {
		/***Given***/
		String id = "sally";
		String pass = "sallyPass";

		/***When***/
		UserVo vo = new UserVo();
		vo.setUserid(id);
		vo.setPass(pass);
		/***Then***/
		assertEquals(1, userDao.userlogin(vo));
	}
	
	@Test
	public void allUserTest() {
		/***Given***/
		
		/***When***/
		List<UserVo> list = userDao.allUserList();
		/***Then***/
		assertEquals(16, list.size());
	}
	
	@Test
	public void selectPagingUserTest() {
		/***Given***/
		PageVo vo = new PageVo(1,5);
		
		/***When***/
		List<UserVo> pagingUser = userDao.selectPagingUser(vo);
		
		/***Then***/
		assertNotNull(pagingUser);
		assertEquals(5, pagingUser.size());
	}
	
	@Test
	public void nameFindUserTest() {
		/***Given***/
		String usernm = "브라운";
		
		/***When***/
		List<UserVo> pagingUser = userDao.nameFindUser(usernm);
		
		/***Then***/
		assertEquals(1, pagingUser.size());
	}
	
	@Test
	public void nameFindUserPagingTest() {
		/***Given***/
		PageVo vo = new PageVo(1,5);
		vo.setVal("브라운");
		
		/***When***/
		List<UserVo> pagingUser = userDao.nameFindUserPaging(vo);
		
		/***Then***/
		assertNotNull(pagingUser);
		assertEquals(1, pagingUser.size());
	}
	@Test
	public void aliasFindUserTest() {
		/***Given***/
		String alias = "곰";
		
		/***When***/
		List<UserVo> pagingUser = userDao.aliasFindUser(alias);
		
		/***Then***/
		assertEquals(2, pagingUser.size());
	}
	
	@Test
	public void aliasFindUserPagingTest() {
		/***Given***/
		PageVo vo = new PageVo(1,5);
		vo.setVal("곰");
		
		/***When***/
		List<UserVo> pagingUser = userDao.aliasFindUserPaging(vo);
		
		/***Then***/
		assertNotNull(pagingUser);
		assertEquals(2, pagingUser.size());
	}
	@Test
	public void idFindUserTest() {
		/***Given***/
		String userid = "b";
		
		/***When***/
		List<UserVo> pagingUser = userDao.idFindUser(userid);
		
		/***Then***/
		assertEquals(2, pagingUser.size());
	}
	
	@Test
	public void idFindUserPagingTest() {
		/***Given***/
		PageVo vo = new PageVo(1,5);
		vo.setVal("b");
		
		/***When***/
		List<UserVo> pagingUser = userDao.idFineUserPaging(vo);
		
		/***Then***/
		assertNotNull(pagingUser);
		assertEquals(2, pagingUser.size());
	}
	
	@Test
	public void selectUserCnt() {
		/***Given***/
		
		/***When***/
		int userCnt = userDao.countUser();
		
		/***Then***/
		assertEquals(16, userCnt);
		
	}
	
	@Test
	public void insertUserTest() {
		/***Given***/
		UserVo vo = new UserVo("ddit","대덕인재","dditpass",new Date(),"개발원 m","대전시 중구 중앙로 76","4층 대덕인재개발원","34940","brown.png","uudi-generated-filename.png");
		
		/***When***/
		int cnt = userDao.registUser(vo);
		
		/***Then***/
		assertEquals(1, cnt);
	}
	
	@Test
	public void countUserTest() {
		/***Given***/
		String userid = "moon";
		
		/***When***/
		UserVo vo = userDao.selectUser(userid);
		
		/***Then***/
		assertEquals("문", vo.getUsernm());
	}

	@Test
	public void deleteUserTest() {
		/***Given***/
		String userid = "testUser";
		
		/***When***/
		int cnt = userDao.deleteUser(userid);
		
		/***Then***/
		assertEquals(1, cnt);
	}
	

}
