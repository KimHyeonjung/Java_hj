package db.community.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import db.community.model.vo.CommentVO;
import db.community.model.vo.CommunityVO;
import db.community.model.vo.PostVO;
import db.community.pagination.Criteria;

public interface PostDAO {


	boolean insertCommunity(@Param("co_name")String community);

	CommunityVO selectCommunity(@Param("co_name")String community);

	boolean updateCommunity(@Param("vo")CommunityVO community);

	boolean deleteCommunity(@Param("co_name")String name);

	List<CommunityVO> selectCommunityList();

	boolean insertPost(@Param("post")PostVO post);

	List<PostVO> selectPostList(@Param("cri")Criteria cri);

	PostVO selectPost(@Param("po_num")int poNum);

	int selectPostListCount(@Param("cri")Criteria cri);

	boolean deletePost(@Param("po_num")int po_num);

	boolean updatePost(@Param("po")PostVO post);

	boolean insertComment(@Param("co")CommentVO comment);

	List<CommentVO> selectCommentList(@Param("po_num")int po_num);

	void updatePostView(@Param("po_num")int poNum);


}
