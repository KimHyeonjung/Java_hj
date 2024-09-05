package kr.kh.spring3.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import kr.kh.spring3.model.vo.MemberVO;

@Component
public class LoginInterceptor extends HandlerInterceptorAdapter{

	@Override
	public void postHandle(
	    HttpServletRequest request, 
	    HttpServletResponse response, 
	    Object handler, 
	    ModelAndView modelAndView)
	    throws Exception {
		HttpSession session = request.getSession();
		
		String prevUrl = request.getHeader("Referer");
		if(prevUrl != null && !prevUrl.contains("/guest/login")) {
			session.removeAttribute("id");
		}
		//회원 정보를 가져옴
		MemberVO user = (MemberVO)modelAndView.getModel().get("user");
		//회원 정보가 없으면 종료
		if(user == null) {
			return;
		}
		//회원 정보가 있으면 세션에 저장
		session.setAttribute("user", user);
		
	}  
	
}
