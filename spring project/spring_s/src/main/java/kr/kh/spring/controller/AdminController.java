package kr.kh.spring.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.kh.spring.model.vo.CommunityVO;
import kr.kh.spring.model.vo.MemberVO;
import kr.kh.spring.service.PostService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private PostService postService;
	
	@GetMapping("/community")
	public String community(Model model) {
		List<CommunityVO> list = postService.getCommunityList();
		model.addAttribute("list", list);
		return "/admin/community";
	}
	
	@PostMapping("/community/insert")
	public String communityInsert(Model model, String name, HttpSession session) {
		MemberVO user = (MemberVO)session.getAttribute("user");
		boolean res = false;
		if(user != null && user.getMe_authority().equals("ADMIN")) {
			res = postService.insertCommunity(name);
		}
		if(res) {
			model.addAttribute("url", "/admin/community");
			model.addAttribute("msg", "커뮤니티를 추가했습니다.");
		}else {
			model.addAttribute("url", "/admin/community");
			model.addAttribute("msg", "커뮤니티를 추가하지 못했습니다.");
		}
		return "main/message";
	}

}
