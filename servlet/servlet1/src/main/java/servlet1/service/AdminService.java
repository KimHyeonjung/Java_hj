package servlet1.service;

import servlet1.model.vo.MemberVO;

public interface AdminService {

	boolean insertCommunity(String co_name);

	boolean deleteCommunity(int co_num, MemberVO user);

	boolean updateCommunity(int co_num, String co_name, MemberVO user);

}
