package kr.kh.spring3.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kr.kh.spring3.dao.PostDAO;
import kr.kh.spring3.model.vo.CommunityVO;
import kr.kh.spring3.model.vo.FileVO;
import kr.kh.spring3.model.vo.MemberVO;
import kr.kh.spring3.model.vo.PostVO;
import kr.kh.spring3.pagination.Criteria;
import kr.kh.spring3.pagination.PageMaker;
import kr.kh.spring3.utils.UploadFileUtils;

@Service
public class PostService {
	
	@Autowired
	PostDAO postDao;
	
	@Resource
	String uploadPath;
	
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

	public boolean insertPost(PostVO post, MemberVO user, MultipartFile[] fileList) {
		if(post == null || user == null) {
			return false;
		}
		if(post.getPo_title().length() == 0) {
			return false;
		}
		post.setPo_me_id(user.getMe_id());
		boolean res =  postDao.insertPost(post);
		System.out.println(post.getPo_num());
		if(!res) {
			return false;
		}
		if(fileList == null || fileList.length == 0) {
			return true;
		}
		for(MultipartFile file : fileList) {
			res = uploadFile(file, post.getPo_num());
			if(!res) {
				return false;
			}
		}
		return res;
	}

	private boolean uploadFile(MultipartFile file, int po_num) {
		if(file == null || file.getOriginalFilename().length() == 0) {
			return false;
		}
		String fi_ori_name = file.getOriginalFilename();
		try {
			String fi_name = UploadFileUtils.uploadFile(uploadPath, fi_ori_name, file.getBytes());
			FileVO uploadFile = new FileVO(fi_name, fi_ori_name, po_num);
			return postDao.insertFile(uploadFile);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		
	}
}
