package kr.kh.boot.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import kr.kh.boot.model.vo.CommunityVO;
import kr.kh.boot.model.vo.PostVO;
import kr.kh.boot.pagination.PageMaker;
import kr.kh.boot.pagination.PostCriteria;
import kr.kh.boot.service.PostService;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class PostController {
	
	
	private PostService postService;
	
	@GetMapping("/post/list/{co_num}") // @PathVariable("co_num") 변수 이름이 같으면 ()생략 가능
	public String postList(Model model, @PathVariable int co_num, PostCriteria cri) {
		cri.setCo_num(co_num);
		cri.setPerPageNum(2);
		//커뮤니티 번호에 맞는 게시글 목록을 가져와서 화면에 전달
		List<PostVO> list = postService.getPostList(cri);
		//커뮤니티 목록을 가져와서 화면에 전달
		List<CommunityVO> communityList = postService.getCommunityList();
		CommunityVO community = postService.getCommunity(co_num);
		//현재 페이지 정보를 이용해서 페이지메이커를 가져와서 화면에 전달
		PageMaker pm = postService.getPageMaker(cri);
		model.addAttribute("list", list);
		model.addAttribute("cList", communityList);
		model.addAttribute("community", community);
		model.addAttribute("pm", pm);
		return "post/list";
	}
	@GetMapping("/post/detail/{po_num}")
	public String postDetail(Model model, @PathVariable int po_num) {
		PostVO post = postService.getPost(po_num);
		model.addAttribute("post", post);
		return "post/detail";
	}
	@GetMapping("/post/insert/{co_num}")
	public String postInsert(Model model, @PathVariable int co_num) {
		CommunityVO community = postService.getCommunity(co_num);
		model.addAttribute("community", community);
		return "post/insert";
	}
	@PostMapping("/post/insert")
	public String postInsertPost(PostVO post) {
		boolean res = postService.insertPost(post);
		if(res) {
			return "redirect:/post/list/"+post.getPo_co_num();
		}else {
			return "redirect:/post/insert/"+post.getPo_co_num();
		}
	}
	@GetMapping("/post/update/{po_num}")
	public String postUpdate(Model model, @PathVariable int po_num) {
		PostVO post = postService.getPost(po_num);
		model.addAttribute("post", post);
		return "post/update";
	}
	@PostMapping("/post/update/{po_num}")
	public String postUpdatePost(@PathVariable int po_num, PostVO post) {
		boolean res = postService.updatePost(post);
		if(res) {
			return "redirect:/post/detail/"+post.getPo_num();
		}else {
			return "redirect:/post/update/"+post.getPo_num();
		}
	}
	@GetMapping("/post/delete/{co_num}/{po_num}")
	public String postDelete(@PathVariable int po_num, @PathVariable int co_num) {
		boolean res = postService.deletePost(po_num);
		if(res) {
			return "redirect:/post/list/"+co_num;
		}else {
			return "redirect:/post/detail/"+po_num;
		}
	}
}
