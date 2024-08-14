package servlet1.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import servlet1.model.vo.CommentVO;
import servlet1.model.vo.CommunityVO;
import servlet1.model.vo.PostVO;
import servlet1.model.vo.RecommendVO;
import servlet1.pagination.Criteria;
import servlet1.pagination.PageMaker;

public interface PostDAO {

	List<CommunityVO> selectCommunityList();

	CommunityVO selectCommunity(@Param("co_num")int coNum);

	List<PostVO> selectPostList(@Param("cri")Criteria cri);

	int selectPostTotalCount(@Param("cri")Criteria cri);

	boolean insertPost(@Param("post")PostVO post);

	PostVO selectPost(@Param("po_num")int num);

	void updatePostView(@Param("po_num")int num);

	boolean updatePost(@Param("post")PostVO post);

	boolean deletePost(@Param("po_num")int po_num);

	RecommendVO selectRecommend(@Param("reco")RecommendVO reco);

	void deleteRecommend(@Param("re_num")int re_num);

	void insertRecommend(@Param("reco")RecommendVO reco);

	List<CommentVO> selectCommentList(@Param("cri")Criteria cri);

	int selectCommentTotalCount(@Param("cri")Criteria cri);

	boolean insertComment(@Param("cm")CommentVO comment);

	CommentVO selectComment(@Param("cm_num")int cm_num);

	boolean deleteComment(@Param("cm_num")int cm_num);

	boolean updateComment(@Param("cm")CommentVO comment);
}
