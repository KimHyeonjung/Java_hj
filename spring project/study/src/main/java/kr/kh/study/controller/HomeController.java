package kr.kh.study.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import kr.kh.study.dao.PostDAO;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	
	@Autowired
	PostDAO postDao;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@GetMapping("/")
	public String home(Locale locale, Model model) {
		System.out.println(""+postDao.count());
		String str = "abc";
		String enc = passwordEncoder.encode(str);
		System.out.println("암호화 안된 문자열 : " + str);
		System.out.println("암호화 된 문자열 : " + enc);
		System.out.println("암호화된 문자열 == abc : " + passwordEncoder.matches("abc", enc));
		System.out.println("암호화된 문자열 == abd : " + passwordEncoder.matches("abd", enc));
		return "/home";
	}
	
	@GetMapping("/post/list")
	public String postList() {
		return "home";
	}
	@GetMapping("/post/detail")
	public String postDetail() {
		return "home";
	}
	@GetMapping("/post/insert")
	public String postInsert() {
		return "home";
	}
}
