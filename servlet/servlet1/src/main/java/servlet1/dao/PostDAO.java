package servlet1.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import servlet1.model.vo.CommunityVO;
import servlet1.model.vo.PostVO;
import servlet1.pagination.Criteria;

public interface PostDAO {

	List<CommunityVO> selectCommunityList();

	CommunityVO selectCommunity(@Param("co_num")int coNum);

	List<PostVO> selectPostList(@Param("cri")Criteria cri);

	int selectPostTotalCount(@Param("cri")Criteria cri);

}
