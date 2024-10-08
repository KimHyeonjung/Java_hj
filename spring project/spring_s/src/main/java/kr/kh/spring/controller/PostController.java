package kr.kh.spring.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import kr.kh.spring.model.vo.CommunityVO;
import kr.kh.spring.model.vo.FileVO;
import kr.kh.spring.model.vo.MemberVO;
import kr.kh.spring.model.vo.PostVO;
import kr.kh.spring.pagination.PageMaker;
import kr.kh.spring.pagination.PostCriteria;
import kr.kh.spring.service.PostService;

//게시글과 관련된 URL을 처리하는 컨트롤러.
//URL 시작이 /post로 시작
@Controller
@RequestMapping("/post")
public class PostController {
	
	@Autowired
	PostService postService;
	
	@GetMapping("/list")
	public String community(Model model, PostCriteria cri) {
		//커뮤니티 리스트를 가져옴
		List<CommunityVO> list = postService.getCommunityList();
		//현재 페이지 정보를 주면서 게시글 리스트를 가져오라고 시킴
		cri.setPerPageNum(2);
		List<PostVO> postList = postService.getPostList(cri);
		
		//현제 페이지 정보를 주면서 페이지네이션 정보를 가져오라고 시킴
		PageMaker pm = postService.getPageMaker(cri);
		
		//화면에 전송
		model.addAttribute("list", list);
		model.addAttribute("pl", postList);
		model.addAttribute("pm", pm);
		
		return "/post/list";
	}
	@ResponseBody
	@PostMapping("/community/list")
	public List<CommunityVO> community2(){
		return postService.getCommunityList();
	}
	
	@GetMapping("/insert")
	public String postInsert(Model model, Integer co_num) {
		model.addAttribute("co_num", co_num);
		return "/post/insert";
	}
	@PostMapping("/insert")
	public String postInsertPost(Model model, PostVO post, MultipartFile [] fileList, HttpSession session) {
		MemberVO user = (MemberVO)session.getAttribute("user");
		if(postService.insertPost(post, user, fileList)) {
			model.addAttribute("url", "/post/list?co_num="+post.getPo_co_num());
			model.addAttribute("msg", "게시글을 등록했습니다.");
		} else {
			model.addAttribute("url", "/post/insert?co_num="+post.getPo_co_num());
			model.addAttribute("msg", "게시글을 등록하지 못했습니다.");
		}
		return "/main/message";
	}
	
	@GetMapping("/detail")
	public String postDetail(Model model, Integer po_num, PostCriteria cri) {
		postService.updateView(po_num);
		PostVO post = postService.getPost(po_num);	
		List<FileVO> fileList = postService.getFileList(po_num);
		model.addAttribute("post", post);
		model.addAttribute("fl", fileList);
		model.addAttribute("cri", cri);
		return "/post/detail";
	}
	
	@GetMapping("/update")
	public String postUpdate(Model model, Integer po_num, PostCriteria cri) {
		PostVO post = postService.getPost(po_num);
		List<FileVO> fileList = postService.getFileList(po_num);
		model.addAttribute("post", post);
		model.addAttribute("fl", fileList);
		model.addAttribute("cri", cri);
		return "/post/update";
	}
	@PostMapping("/update")
	public String postUpdatePost(Model model, PostVO post, PostCriteria cri,
			int []fi_nums, MultipartFile[] fileList, HttpSession session) {
		
		MemberVO user = (MemberVO)session.getAttribute("user");
		
		if(postService.updatePost(post, fi_nums, fileList, user)) {
			model.addAttribute("url", "/post/detail?po_num="+post.getPo_num() +"&"+ cri.toString());
			model.addAttribute("msg", "게시글을 수정했습니다.");
		}else {
			model.addAttribute("url", "/post/detail?po_num="+post.getPo_num() +"&"+ cri.toString());
			model.addAttribute("msg", "게시글을 수정하지 못했습니다.");
		}
		return "/main/message";
	}
	
	@GetMapping("/delete")
	public String postDelete(Model model, Integer po_num, PostCriteria cri, HttpSession session) {
		MemberVO user = (MemberVO)session.getAttribute("user");
		
		if(postService.deletePost(po_num, user)) {
			model.addAttribute("url", "/post/list?" + cri.toString());
			model.addAttribute("msg", "게시글을 삭제했습니다.");
		}else {
			model.addAttribute("url", "/post/detail?po_num="+po_num +"&"+ cri.toString());
			model.addAttribute("msg", "게시글을 삭제하지 못했습니다.");
		}
		return "/main/message";
	}
	
	
	
	
	
	
	
	
	
}
