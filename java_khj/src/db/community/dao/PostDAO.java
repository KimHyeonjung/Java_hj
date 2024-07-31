package db.community.dao;

import org.apache.ibatis.annotations.Param;

import db.community.model.vo.CommunityVO;

public interface PostDAO {


	boolean insertCommunity(@Param("co_name")String community);

	CommunityVO selectCommunity(@Param("co_name")String community);

	boolean updateCommunity(@Param("vo")CommunityVO community);


}
