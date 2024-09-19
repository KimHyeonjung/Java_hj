package kr.kh.spring3.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import kr.kh.spring3.dao.Person;
import kr.kh.spring3.model.vo.CommunityVO;
import kr.kh.spring3.model.vo.PostVO;
import kr.kh.spring3.pagination.PageMaker;
import kr.kh.spring3.pagination.PostCriteria;
import kr.kh.spring3.service.PostService;

@RequestMapping("/react")
@RestController
public class ReactController {
	
	@Autowired
	PostService postService;
	

	@ResponseBody
	@GetMapping("/community/list")
	public List<CommunityVO> communityList() {
		
		return postService.getCommunityList();
	}
	
	@GetMapping("/post/list/{co_num}")
	public Map<String, Object> postList(@PathVariable("co_num")int co_num, Model model, PostCriteria cri) {
		cri.setCo_num(co_num);
		cri.setPerPageNum(4);
		
		List<PostVO> list = postService.getPostList(cri);
		//페이지 메이커
		PageMaker pm = postService.getPageMaker(cri);
		//확인
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("list", list);
		map.put("pm", pm);
		return map;
	}
	
	@GetMapping("/get/str")
	public String getStr() {
		return "Hello";
	}
	@GetMapping("/get/obj")
	public Person getObj() {
//		HashMap<String, Object> map = new HashMap<String, Object>();
//		map.put("person",  new Person("홍길동", 20));
		return new Person("홍길동", 20);
	}
	@PostMapping("/send/person")
	public String sendPerson(@RequestParam("name") String name, @RequestParam("age") int age) {
		System.out.println(name);
		System.out.println(age);
		return "OK";
	}
	@PostMapping("/send/person2")
	public String sendPerson2(@RequestBody Person person) {
		System.out.println(person);
		return "OK";
	}
	
	@GetMapping("/post/detail/{po_num}")
	public PostVO postDetail(@PathVariable("po_num") int po_num) {
		PostVO post = postService.getPost(po_num);
		return post;
	}

}
