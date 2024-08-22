package kr.kh.app.service;

import java.util.List;

import kr.kh.app.model.vo.CommunityVO;
import kr.kh.app.model.vo.MemberVO;
import kr.kh.app.model.vo.PostVO;
import kr.kh.app.pagination.Criteria;
import kr.kh.app.pagination.PageMaker;

public interface PostService {

	List<CommunityVO> getCommunityList();

	CommunityVO getCommunity(int co_num);

	List<PostVO> getPostList(Criteria cri);

	PageMaker getPostPageMaker(Criteria cri, int displayPageNum);

	PostVO getPostDetail(String po_num);

	void updatePostView(String po_num);

	boolean insertPost(PostVO post);

	boolean updatePost(PostVO post, MemberVO user);

}
