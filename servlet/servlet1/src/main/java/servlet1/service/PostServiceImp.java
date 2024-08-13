package servlet1.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import servlet1.dao.PostDAO;
import servlet1.model.vo.CommentVO;
import servlet1.model.vo.CommunityVO;
import servlet1.model.vo.MemberVO;
import servlet1.model.vo.PostVO;
import servlet1.model.vo.RecommendVO;
import servlet1.pagination.Criteria;
import servlet1.pagination.PageMaker;

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
	public List<PostVO> getPostList(Criteria cri) {
		if(cri == null) {
			throw new RuntimeException();
		}
		return postDao.selectPostList(cri);
	}

	@Override
	public PageMaker getPageMaker(Criteria cri, int displayPageNum) {
		if(cri == null) {
			throw new RuntimeException();
		}
		int totalCount = postDao.selectPostTotalCount(cri);
		return new PageMaker(totalCount, displayPageNum, cri);
	}

	@Override
	public boolean insertPost(PostVO post) {
		if(post == null) {
			return false;
		}
		if(post.getPo_title() == null || post.getPo_title().trim().length() == 0) {
			return false;
		}
		if(post.getPo_sub() == null || post.getPo_sub().trim().length() == 0) {
			return false;
		}
		return postDao.insertPost(post);
	}

	@Override
	public PostVO getPost(int num) {
		return postDao.selectPost(num);
	}

	@Override
	public void updatePostView(int num) {
		postDao.updatePostView(num);
	}

	@Override
	public PostVO getPost(int po_num, MemberVO user) {
		//회원이 null이면 null을 반환
		if(user == null) {
			return null;
		}
		//게시글 번호에 맞는 게시글을 가져옴
		PostVO post = postDao.selectPost(po_num); 
		//게시글이 null이면 null을 반환
		if(post == null) {
			return null;
		}
		//게시글의 작성자와 회원 아이디가 같으면 게시글을 반환
		if(post.getPo_me_id().equals(user.getMe_id())) {
			return post;
		}
		//아니면 null을 반환
		return null;
	}

	@Override
	public boolean updatePost(PostVO post, MemberVO user) {
		if(post == null || user == null) {
			return false;
		}
		if(!checkWriter(post.getPo_num(), user)) {
			return false;
		}
		if(post.getPo_title() == null || post.getPo_title().trim().length() == 0) {
			return false;
		}
		if(post.getPo_sub() == null || post.getPo_sub().trim().length() == 0) {
			return false;
		}
		return postDao.updatePost(post);
	}
	
	
	//게시글 작성자인지 아닌지 확인하는 메소드
	private boolean checkWriter(int po_num, MemberVO user) {
		//회원이 null이면 null을 반환
		if(user == null) {
			return false;
		}
		//게시글 번호에 맞는 게시글을 가져옴
		PostVO post = postDao.selectPost(po_num); 
		//게시글이 null이면 null을 반환
		if(post == null) {
			return false;
		}
		//게시글의 작성자와 회원 아이디가 같으면 true 반환
		if(post.getPo_me_id().equals(user.getMe_id())) {
			return true;
		}
		//아니면 null을 반환
		return false;
	}

	@Override
	public boolean deletePost(int po_num, MemberVO user) {
		if(!checkWriter(po_num, user)) {
			return false;
		}
		return postDao.deletePost(po_num);
	}

	@Override
	public int recommend(RecommendVO reco) {
		if(reco == null) {
			throw new RuntimeException();
		}
		if(reco.getRe_state() > 1 || reco.getRe_state() < -1) {
			throw new RuntimeException();
		}
		//기존의 추천 내용을 확인
		RecommendVO dbReco = postDao.selectRecommend(reco);
		
		//없으면 추가 후 추천상태를 리턴
		if (dbReco == null){
			postDao.insertRecommend(reco);
			return reco.getRe_state();
		}
		//있으면 삭제
		postDao.deleteRecommend(dbReco.getRe_num());
		//기존상태와 새 상태가 같으면(취소)
		if (dbReco.getRe_state() == reco.getRe_state()){
			return 0;
		}
		//기존상태와 새 상태가 다르면(변경)
		postDao.insertRecommend(reco);
		return reco.getRe_state();
	}

	@Override
	public RecommendVO getRecommend(int num, MemberVO user) {
		if(user == null) {
			return null;
		}
		RecommendVO reco = new RecommendVO(num, 0, user.getMe_id());
		return postDao.selectRecommend(reco);
	}

	@Override
	public List<CommentVO> getCommntList(Criteria cri) {
		if(cri == null) {
			return null;
		}
		return postDao.selectCommentList(cri);
	}

	@Override
	public PageMaker getCommentPageMaker(Criteria cri) {
		if(cri == null) {
			return null;
		}
		int totalCount =  postDao.selectCommentTotalCount(cri);
		return new PageMaker(totalCount, 2, cri);
	}
}
