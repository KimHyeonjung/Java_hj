package auction.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import auction.model.vo.MemberVO;

public interface MemberDAO {

	ArrayList<MemberVO> selectMember(@Param("search")String searchMemberInfo);

	boolean insertMember(@Param("member")MemberVO member);

	String selectMemberId(@Param("memberId")String memberId);

	MemberVO selectMemberById(@Param("memberId")String memberId);

	boolean updateMember(@Param("newMember")MemberVO newMember);

	boolean deleteMember(@Param("memberId")String memberId);

	int selectMemberLoginCheck(@Param("id")String id, @Param("password")String password);

}
