package kr.kh.spring3.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import kr.kh.spring3.model.dto.MessageDTO;
import kr.kh.spring3.model.vo.CommunityVO;
import kr.kh.spring3.model.vo.FileVO;
import kr.kh.spring3.model.vo.MemberVO;
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
		//화면에 전달
		model.addAttribute("post", post);
		model.addAttribute("list", fileList);
		model.addAttribute("cri", cri);
		
		return "/post/detail";
	}
	
	@GetMapping("/insert/{co_num}")
	public String postInsert(Model model, @PathVariable("co_num")int co_num) {
		
		model.addAttribute("co_num", co_num);
		return "/post/insert";
	}
	@PostMapping("/insert")
	public String postInsertPost(Model model, PostVO post, MultipartFile [] fileList, HttpSession session) {
		MemberVO user = (MemberVO)session.getAttribute("user");
		MessageDTO message;
		
		if(postService.insertPost(post, user, fileList)) {
			message = new MessageDTO("/post/list/"+post.getPo_co_num()+"","게시글이 등록되었습니다.");
		} else {
			message = new MessageDTO("/post/insert/"+post.getPo_co_num()+"","게시글을 등록하지 못했습니다.");
		}
		model.addAttribute("msg", message);
		
		return "/main/message";
	}
	
	@GetMapping("/update/{po_num}")
	public String postUpdate(Model model, @PathVariable("po_num")int po_num, PostCriteria cri) {
		PostVO post = postService.getPost(po_num);
		List<FileVO> fileList = postService.getFileList(po_num);
		model.addAttribute("list", fileList);
		model.addAttribute("post", post);
		model.addAttribute("cri", cri);
		return "/post/update";
	}
	@PostMapping("/update")
	public String postUpdatePost(Model model, PostVO post, PostCriteria cri, HttpSession session
			, MultipartFile [] fileList, int [] nums) {
		MemberVO user = (MemberVO)session.getAttribute("user");
		boolean res = postService.updatePost(post, user, fileList, nums);
		MessageDTO message;
		if(res) {
			message = new MessageDTO("/post/detail/"+post.getPo_num()+"?"+cri.toString(),"게시글을 수정하였습니다.");
		}else {
			message = new MessageDTO("/post/update/"+post.getPo_num()+"?"+cri.toString(),"게시글을 수정하지 못했습니다.");
		}
		model.addAttribute("msg", message);		
		return "/main/message";
	}
	
	@GetMapping("/delete/{po_num}")
	public String postDelete(Model model, @PathVariable("po_num")int po_num, PostCriteria cri, HttpSession session) {
		MemberVO user = (MemberVO)session.getAttribute("user");
		boolean res = postService.deletePost(po_num, user);
		MessageDTO message;
		if(res) {
			message = new MessageDTO("/post/list/"+cri.getCo_num()+"?"+cri.toString(), "게시글을 삭제했습니다.");
		}else {
			message = new MessageDTO("/post/detail/"+po_num+"?"+cri.toString(), "게시글을 삭제하지 못했습니다.");
		}
		model.addAttribute("msg", message);
		return "/main/message";
	}
}
