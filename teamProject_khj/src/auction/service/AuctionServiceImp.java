package auction.service;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import auction.dao.AuctionDAO;
import auction.model.vo.AuctionVO;

public class AuctionServiceImp implements AuctionService{
	
	AuctionDAO auctionDao;
	
	public AuctionServiceImp() {
		String resource = "auction/config/mybatis-config.xml";
		InputStream inputStream;
		SqlSession session;
		try {
			inputStream = Resources.getResourceAsStream(resource);
			SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			session = sessionFactory.openSession(true);
			auctionDao = session.getMapper(AuctionDAO.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public boolean insertAuction(AuctionVO auction) {
		if(auction == null) {
			return false;
		}
		return auctionDao.insertAuctionStart(auction);
	}
}
