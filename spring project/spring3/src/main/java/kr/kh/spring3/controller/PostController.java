package kr.kh.spring3.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.kh.spring3.model.vo.CommunityVO;
import kr.kh.spring3.model.vo.FileVO;
import kr.kh.spring3.model.vo.PostVO;
import kr.kh.spring3.pagination.PageMaker;
import kr.kh.spring3.pagination.PostCriteria;
import kr.kh.spring3.service.PostService;
import lombok.extern.log4j.Log4j;

@Log4j
@Controller
@RequestMapping("/post")
public class PostController {

	@Autowired
	private PostService postService;
	
	
	@GetMapping("/list/{co_num}")
	public String postList(@PathVariable("co_num")int co_num, Model model, PostCriteria cri) {
		cri.setCo_num(co_num);
		cri.setPerPageNum(4);
		List<CommunityVO> coList = postService.getCommunityList();
		List<PostVO> poList = postService.getPostList(cri);
		//페이지 메이커
		PageMaker pm = postService.getPageMaker(cri);
		//확인
		log.info(pm);
		
		model.addAttribute("poList", poList);
		model.addAttribute("pm", pm);
		model.addAttribute("coList", coList);
		
		return "/post/list";
	}
	
	@GetMapping("/detail/{po_num}")
	public String postDetail(Model model, @PathVariable("po_num")int po_num, PostCriteria cri) {
		//조회수 증가
		postService.increaseViews(po_num);
		//게시글 가져옴
		PostVO post = postService.getPost(po_num);
		//첨부파일 가져옴
		List<FileVO> fileList = postService.getFileList(po_num);
		log.info(fileList);
		//화면에 전달
		model.addAttribute("post", post);
		model.addAttribute("list", fileList);
		model.addAttribute("cri", cri);
		
		return "/post/detail";
	}
}
