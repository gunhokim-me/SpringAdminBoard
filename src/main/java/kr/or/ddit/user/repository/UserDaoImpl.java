package kr.or.ddit.user.repository;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.or.ddit.paging.model.PageVo;
import kr.or.ddit.user.model.UserVo;

@Repository("userDao")
public class UserDaoImpl implements UserDao{

	@Resource(name="sqlSessionTemplate")
	private SqlSessionTemplate template;
	
	//로그인 할 때 아이디와 비밀번호가 맞는지 확인
	@Override
	public int userlogin(UserVo vo) {
		return template.selectOne("users.select",vo);
	}

	//전체 회원 리스트 가져오기
	@Override
	public List<UserVo> allUserList() {
		return template.selectList("users.userAllList");
	}

	//페이징한 사용자 조회
	@Override
	public List<UserVo> selectPagingUser(PageVo vo) {
		return template.selectList("users.selectPagingUser", vo);
	}

	//아이디로 검색
	@Override
	public List<UserVo> idFindUser(String userid) {
		return template.selectList("users.idFindUser", userid);
	}
	
	@Override
	public List<UserVo> selectLikeUser(String userid) {
		return template.selectList("users.idFindLikeUser",userid);
	}

	//아이디로 검색한 회원 페이징
	@Override
	public List<UserVo> idFineUserPaging(PageVo vo) {
		return template.selectList("users.idFineUserPaging",vo);
	}

	//아이디로 검색 한 회원 수
	@Override
	public int idFindUserCount(String userid) {
		return template.selectOne("users.idFindUserCount", userid);
	}

	//이름으로 검색
	@Override
	public List<UserVo> nameFindUser(String usernm) {
		return template.selectList("users.nameFindUser", usernm);
	}

	//이름으로 검색한 회원 페이징
	@Override
	public List<UserVo> nameFindUserPaging(PageVo vo) {
		return template.selectList("users.nameFindUserPaging",vo);
	}

	//이름으로 검색 한 회원 수
	@Override
	public int nameFindUserCount(String usernm) {
		return template.selectOne("users.nameFindUserCount", usernm);
	}

	//별명으로 검색
	@Override
	public List<UserVo> aliasFindUser(String alias) {
		return template.selectList("users.aliasFindUser", alias);
	}

	//별명으로 검색한 회원 페이징
	@Override
	public List<UserVo> aliasFindUserPaging(PageVo vo) {
		return template.selectList("users.aliasFindUserPaging",vo);
	}

	//별명으로 검색한 회원 수
	@Override
	public int aliasFindUserCount(String alias) {
		return template.selectOne("users.aliasFindUserCount", alias);
	}

	//선택한 회원 조회
	@Override
	public UserVo selectUser(String userid) {
		return template.selectOne("users.selectUser", userid);
	}

	//전체 회원 카운트
	@Override
	public int countUser() {
		return template.selectOne("users.countUser");
	}

	//회원 등록
	@Override
	public int registUser(UserVo vo) {
		return template.insert("users.registUser",vo);
	}

	//회원 수정
	@Override
	public int modifyUser(UserVo vo) {
		return template.update("users.modifyUser",vo);
	}

	//회원 삭제
	@Override
	public int deleteUser(String userid) {
		return template.delete("users.deleteUser",userid);
	}
	
}
