package servlet1.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import servlet1.dao.PostDAO;
import servlet1.model.vo.CommunityVO;
import servlet1.model.vo.PostVO;

public class PostServiceImp implements PostService{

	private PostDAO postDao;
	
	public PostServiceImp() {
		String resource = "servlet1/config/mybatis-config.xml";
		InputStream inputStream;
		SqlSession session;
		try {
			inputStream = Resources.getResourceAsStream(resource);
			SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			session = sessionFactory.openSession(true);
			postDao = session.getMapper(PostDAO.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<CommunityVO> getCommunityList() {
		
		return postDao.selectCommunityList();
	}

	@Override
	public CommunityVO getCommunity(int coNum) {
		if(coNum <= 0) {
			return null;
		}
		return postDao.selectCommunity(coNum);
	}

	@Override
	public List<PostVO> getPostList(int co_num) {
		if(co_num <= 0) {
			return null;
		}
		return postDao.selectPostList(co_num);
	}
}
