package kr.kh.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.kh.spring.service.PostService;

//게시글과 관련된 URL을 처리하는 컨트롤러.
//URL 시작이 /post로 시작
@Controller
@RequestMapping("/post")
public class PostController {
	
	@Autowired
	PostService postService;
	
	@GetMapping("/community")
	public String community() {
		
		return "/post/community";
	}
}
