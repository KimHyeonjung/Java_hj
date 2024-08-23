package kr.kh.app.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import kr.kh.app.dao.PostDAO;
import kr.kh.app.model.vo.CommunityVO;
import kr.kh.app.model.vo.MemberVO;
import kr.kh.app.model.vo.PostVO;
import kr.kh.app.pagination.Criteria;
import kr.kh.app.pagination.PageMaker;

public class PostServiceImp implements PostService{

	PostDAO postDao;
	
	public PostServiceImp() {
		String resource = "kr/kh/app/config/mybatis-config.xml";
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
	public CommunityVO getCommunity(int co_num) {
		return postDao.selectCommunity(co_num);
	}

	@Override
	public List<PostVO> getPostList(Criteria cri) {
		if(cri == null) {
			return null;
		}
		return postDao.selectPostList(cri);
	}

	@Override
	public PageMaker getPostPageMaker(Criteria cri, int displayPageNum) {
		if(cri == null) {
			return null;
		}
		int totalCount = postDao.selectPostTotalCount(cri);
		return new PageMaker(totalCount, displayPageNum, cri);
	}

	@Override
	public PostVO getPostDetail(String po_num) {
		if(po_num == null) {
			return null;
		}
		return postDao.selectPost(po_num);
	}

	@Override
	public void updatePostView(String po_num) {
		postDao.updatePostViews(po_num);
		
	}

	@Override
	public boolean insertPost(PostVO post) {
		if(post == null) {
			return false;
		}
		if(post.getPo_me_id() == null || post.getPo_title() == null || post.getPo_title().trim().length() == 0) {
			return false;
		}
		try {
			return postDao.insertPost(post);
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean updatePost(PostVO post, MemberVO user) {
		if(post == null) {
			return false;
		}
		if(post.getPo_title() == null || post.getPo_title().trim().length() == 0) {
			return false;
		}
		PostVO dbPost = postDao.selectPost(post.getPo_num()+"");
		if(dbPost == null || user == null || !dbPost.getPo_me_id().equals(user.getMe_id())) {
			return false;
		}
		try {
			return postDao.updatePost(post);
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public int deletePost(String po_num, MemberVO user) {
		if(po_num == null || user == null) {
			return 0;
		}
		PostVO dbPost = postDao.selectPost(po_num);
		if(dbPost == null) {
			return 0;
		}
		if(!dbPost.getPo_me_id().equals(user.getMe_id())) {
			return 0;
		}
		postDao.deletePost(po_num);
		return dbPost.getPo_co_num();
	}

}
