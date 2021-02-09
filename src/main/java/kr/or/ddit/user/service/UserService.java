package kr.or.ddit.user.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.paging.model.PageVo;
import kr.or.ddit.user.model.UserVo;


public interface UserService {
	//로그인 시 입력한 값이 있는지 확인
	int userlogin(UserVo vo);
	
	//전체 회원 리스트
	List<UserVo> allUserList();
	
	//페이징한 사용자 조회
	Map<String, Object> selectPagingUser(PageVo vo);
	
	//아이디로 검색
	Map<String, Object> idFindUser(PageVo vo);
	
	//이름으로 검색
	Map<String, Object> nameFindUser(PageVo vo);
	
	//별명으로 검색
	Map<String, Object> aliasFindUser(PageVo vo);
	
	//선택한 회원 조회
	UserVo selectUser(String userid);
	
	//전체 회원수 카운트
	int countUser();
	
	//회원 등록
	int registUser(UserVo vo);
	
	//회원 수정
	int modifyUser(UserVo vo);
	
	//회원 삭제
	int deleteUser(String userid);
	
}
