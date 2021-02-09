package kr.or.ddit.user.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.or.ddit.paging.model.PageVo;
import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.user.repository.UserDao;

@Service("userService")
public class UserServiceImpl implements UserService {
	
	@Resource(name="userDao")
	private UserDao userDao;

	@Override
	public int userlogin(UserVo vo) {
		return userDao.userlogin(vo);
	}

	@Override
	public List<UserVo> allUserList() {
		return userDao.allUserList();
	}

	//페이징 처리
	@Override
	public Map<String, Object> selectPagingUser(PageVo vo) {
		Map<String, Object> map = new HashMap<>();
		List<UserVo> userList = userDao.selectPagingUser(vo);
		map.put("pageVo", vo);
		map.put("userList", userList);
		map.put("pagination", (int)Math.ceil((double)userDao.countUser() /vo.getPageSize()));
		return map;
	}

	//아이디로 검색한 페이징
	@Override
	public Map<String, Object> idFindUser(PageVo vo) {
		Map<String, Object> map = new HashMap<>();
		
		List<UserVo> userList = userDao.idFineUserPaging(vo);
		int userCnt = userDao.idFindUserCount(vo.getVal());
		map.put("pageVo", vo);
		map.put("userList", userList);
		map.put("userCnt", userCnt);
		
		return map;
	}

	//이름으로 검색한 페이징
	@Override
	public Map<String, Object> nameFindUser(PageVo vo) {
	Map<String, Object> map = new HashMap<>();
		
		List<UserVo> userList = userDao.nameFindUserPaging(vo);
		int userCnt = userDao.nameFindUserCount(vo.getVal());
		map.put("pageVo", vo);
		map.put("userList", userList);
		map.put("userCnt", userCnt);
		
		return map;
	}

	//별명으로 검색한 페이징
	@Override
	public Map<String, Object> aliasFindUser(PageVo vo) {
		Map<String, Object> map = new HashMap<>();
		
		List<UserVo> userList = userDao.aliasFindUserPaging(vo);
		int userCnt = userDao.aliasFindUserCount(vo.getVal());
		map.put("pageVo", vo);
		map.put("userList", userList);
		map.put("userCnt", userCnt);
		
		return map;
	}

	//선택한 회원 조회
	@Override
	public UserVo selectUser(String userid) {
		return userDao.selectUser(userid);
	}

	//전체 회원 수 조회
	@Override
	public int countUser() {
		return userDao.countUser();
	}

	//회원 등록
	@Override
	public int registUser(UserVo vo) {
		return userDao.registUser(vo);
	}

	//회원 수정
	@Override
	public int modifyUser(UserVo vo) {
		return userDao.modifyUser(vo);
	}

	//회원 삭제
	@Override
	public int deleteUser(String userid) {
		return userDao.deleteUser(userid);
	}

}
