package servlet1.service;

import servlet1.model.dto.LoginDTO;
import servlet1.model.vo.MemberVO;

public interface MemberService {

	boolean signup(LoginDTO member);

	MemberVO login(LoginDTO member);

}
