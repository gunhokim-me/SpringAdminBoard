package kr.or.ddit.user.repository;

import java.util.List;

import kr.or.ddit.paging.model.PageVo;
import kr.or.ddit.user.model.UserVo;


public interface UserDao {
	
	//로그인 시 입력한 값이 있는지 확인
	int userlogin(UserVo vo);
	
	//전체 회원 리스트
	List<UserVo> allUserList();
	
	//페이징한 사용자 조회
	List<UserVo> selectPagingUser(PageVo vo);

	//아이디로 검색
	List<UserVo> idFindUser(String userid);
	List<UserVo> idFineUserPaging(PageVo vo);
	int idFindUserCount(String userid);
	
	//이름으로 검색
	List<UserVo> nameFindUser(String usernm);
	List<UserVo> nameFindUserPaging(PageVo vo);
	int nameFindUserCount(String usernm);
	
	//별명으로 검색
	List<UserVo> aliasFindUser(String alias);
	List<UserVo> aliasFindUserPaging(PageVo vo);
	int aliasFindUserCount(String alias);
	
	//선택한 회원 조회
	UserVo selectUser(String userid);
	
	List<UserVo> selectLikeUser(String userid);
	
	//전체 회원수 카운트
	int countUser();
	
	//회원 등록
	int registUser(UserVo vo);
	
	//회원 수정
	int modifyUser(UserVo vo);
	
	//회원 삭제
	int deleteUser(String userid);
	

}
