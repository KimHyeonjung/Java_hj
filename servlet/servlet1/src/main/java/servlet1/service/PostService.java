package servlet1.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Part;

import servlet1.model.vo.CommentVO;
import servlet1.model.vo.CommunityVO;
import servlet1.model.vo.FileVO;
import servlet1.model.vo.MemberVO;
import servlet1.model.vo.PostVO;
import servlet1.model.vo.RecommendVO;
import servlet1.pagination.Criteria;
import servlet1.pagination.PageMaker;

public interface PostService {

	List<CommunityVO> getCommunityList();

	CommunityVO getCommunity(int coNum);

	List<PostVO> getPostList(Criteria cri);

	PageMaker getPageMaker(Criteria cri, int displayPageNum);

	boolean insertPost(PostVO post, ArrayList<Part> files);

	PostVO getPost(int num);

	void updatePostView(int num);

	PostVO getPost(int po_num, MemberVO user);

	boolean updatePost(PostVO post, MemberVO user);

	boolean deletePost(int po_num, MemberVO user);

	int recommend(RecommendVO reco);

	RecommendVO getRecommend(int num, MemberVO user);

	List<CommentVO> getCommntList(Criteria cri);

	PageMaker getCommentPageMaker(Criteria cri);

	boolean insertComment(CommentVO comment);

	boolean deleteComment(int cm_num, MemberVO user);

	boolean updateComment(CommentVO comment, MemberVO user);

	List<FileVO> getFileList(int num);


}
