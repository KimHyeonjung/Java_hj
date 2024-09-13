package kr.kh.spring3.controller;


import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.kh.spring3.model.dto.MessageDTO;
import kr.kh.spring3.model.vo.CommunityVO;
import kr.kh.spring3.model.vo.MemberVO;
import kr.kh.spring3.service.MemberService;
import kr.kh.spring3.service.PostService;
import lombok.extern.log4j.Log4j;

@Log4j
@Controller
public class HomeController {
	
	@Autowired
	private MemberService memberService;
	@Autowired
	private PostService postService;
	
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model) {
		log.info("/ : 메인 페이지입니다.");
		
		model.addAttribute("title", "메인");
		return "/main/home";
	}
	
	@GetMapping("/guest/signup")
	public String guestSignup() {
		return "/member/signup";
	}
	@PostMapping("/guest/signup")
	public String guestSignupPost(Model model, MemberVO member) {
		boolean res = memberService.signup(member);
		MessageDTO message;
		if(res) {
			message = new MessageDTO("/","회원가입에 성공했습니다.");
		}else {
			message = new MessageDTO("/guest/signup","회원가입을 하지 못했습니다.");
		}
		model.addAttribute("msg", message);
		
		return "/main/message";
	}
	
	@GetMapping("/guest/login")
	public String guestLogin() {
		return "/member/login";
	}
	@PostMapping("/guest/login")
	public String guestLoginPost(Model model, MemberVO member, HttpSession session) {
		MemberVO user = memberService.login(member);
		MessageDTO message;
		if(user != null) {
			message = new MessageDTO("/", "로그인에 성공했습니다.");
			session.removeAttribute("id");
		} else {
			message = new MessageDTO("/guest/login", "로그인에 실패하였습니다.");
			session.setAttribute("id", member.getMe_id());
		}
		model.addAttribute("msg", message);
		model.addAttribute("user",user);
		return "/main/message";
	}
	
	@GetMapping("/member/logout")
	public String memberLogout(Model model, HttpSession session) {
		log.info("/member/logout:get");
		session.removeAttribute("user");
		MessageDTO message = new MessageDTO("/", "로그아웃 했습니다.");
		model.addAttribute("msg", message);
		return "/main/message";
	}
	
	@ResponseBody
	@GetMapping("/test")
	public String test() {
		return "Hi";
	}
	
	@ResponseBody
	@GetMapping("/test2")
	public List<CommunityVO> test2() {
		
		return postService.getCommunityList();
	}

	
}
