package kr.kh.spring3.dao;

import org.apache.ibatis.annotations.Param;

import kr.kh.spring3.model.vo.MemberVO;

public interface MemberDAO {

	boolean insertMember(@Param("me")MemberVO member);

	
}
