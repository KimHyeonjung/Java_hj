package servlet1.service;

import java.util.List;

import servlet1.model.vo.CommunityVO;
import servlet1.model.vo.PostVO;

public interface PostService {

	List<CommunityVO> getCommunityList();

	CommunityVO getCommunity(int coNum);

	List<PostVO> getPostList(int co_num);

}
