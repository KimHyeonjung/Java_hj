package kr.kh.spring3.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.kh.spring3.dao.PostDAO;
import kr.kh.spring3.model.vo.CommunityVO;
import kr.kh.spring3.model.vo.FileVO;
import kr.kh.spring3.model.vo.PostVO;
import kr.kh.spring3.pagination.Criteria;
import kr.kh.spring3.pagination.PageMaker;

@Service
public class PostService {
	
	@Autowired
	PostDAO postDao;
	
	public List<CommunityVO> getCommunityList() {
		return postDao.selectCommunityList();
	}

	public List<PostVO> getPostList(Criteria cri) {
		if(cri == null) {
			return null;
		}
		return postDao.selectPostList(cri);
	}

	public PageMaker getPageMaker(Criteria cri) {
		if(cri == null) {
			return null;
		}
		int totalCount = postDao.getPostTotalCount(cri);
		return new PageMaker(2, cri, totalCount);
	}

	public PostVO getPost(int po_num) {
		return postDao.selectPost(po_num);
	}

	public void increaseViews(int po_num) {
		postDao.updateViews(po_num);
		
	}

	public List<FileVO> getFileList(int po_num) {

		return postDao.selectFileList(po_num);
	}
}
