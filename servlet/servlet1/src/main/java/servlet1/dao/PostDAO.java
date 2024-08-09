package servlet1.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import servlet1.model.vo.CommunityVO;
import servlet1.model.vo.PostVO;

public interface PostDAO {

	List<CommunityVO> selectCommunityList();

	CommunityVO selectCommunity(@Param("co_num")int coNum);

	List<PostVO> selectPostList(@Param("co_num")int co_num);

}
