package servlet1.service;

import java.util.List;

import servlet1.model.vo.CommunityVO;
import servlet1.model.vo.PostVO;
import servlet1.pagination.Criteria;
import servlet1.pagination.PageMaker;

public interface PostService {

	List<CommunityVO> getCommunityList();

	CommunityVO getCommunity(int coNum);

	List<PostVO> getPostList(Criteria cri);

	PageMaker getPageMaker(Criteria cri, int displayPageNum);

	boolean insertPost(PostVO post);

	PostVO getPost(int num);

	void updatePostView(int num);

}
