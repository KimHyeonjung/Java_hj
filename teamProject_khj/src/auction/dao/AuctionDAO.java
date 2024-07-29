package auction.dao;


import org.apache.ibatis.annotations.Param;

import auction.model.vo.AuctionVO;

public interface AuctionDAO {

	boolean insertAuctionStart(@Param("auction")AuctionVO auction);

}
