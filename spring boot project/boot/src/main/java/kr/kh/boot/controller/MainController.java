package kr.kh.boot.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class MainController {
	
	
	@GetMapping("/")
	public String home(Model model,  @AuthenticationPrincipal UserDetails userDetails) {
		model.addAttribute("name", "홍길동");
		if(userDetails != null) {
			System.out.println(userDetails);
		}
		return "home";
	}
}
