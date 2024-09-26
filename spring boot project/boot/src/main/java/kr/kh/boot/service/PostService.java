package kr.kh.boot.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.kh.boot.dao.PostDAO;
import kr.kh.boot.model.vo.CommunityVO;
import kr.kh.boot.model.vo.PostVO;
import kr.kh.boot.pagination.PageMaker;
import kr.kh.boot.pagination.PostCriteria;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PostService {

	private PostDAO postDao;

	public List<PostVO> getPostList(PostCriteria cri) {
		
		return postDao.selectPostList(cri);
	}

	public List<CommunityVO> getCommunityList() {
		return postDao.selectCommunityList();
	}

	public PageMaker getPageMaker(PostCriteria cri) {
		int count = postDao.selectCountPostList(cri);
		return new PageMaker(count, 2, cri);
	}

	public PostVO getPost(int po_num) {
		
		return postDao.selectPost(po_num);
	}

	public CommunityVO getCommunity(int co_num) {
		return postDao.selectCommunity(co_num);
	}

	public boolean insertPost(PostVO post) {
		if(post == null) {
			return false;
		}
		try {
			return postDao.insertPost(post);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean updatePost(PostVO post) {
		if(post == null) {
			return false;
		}
		try {
			return postDao.updatePost(post);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean deletePost(int po_num) {
		
		return postDao.deletePost(po_num);
	}
}
