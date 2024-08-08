package servlet1.dao;

import org.apache.ibatis.annotations.Param;

import servlet1.model.dto.LoginDTO;
import servlet1.model.vo.MemberVO;

public interface MemberDAO {

	boolean insertMember(@Param("me")LoginDTO member);

	MemberVO selectMember(@Param("id")String id);

}
