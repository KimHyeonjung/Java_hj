package kr.kh.app.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.kh.app.model.vo.MemberVO;
import kr.kh.app.service.MemberService;
import kr.kh.app.service.MemberServiceImp;

/**
 * Servlet implementation class Login
 */
@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService = new MemberServiceImp();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//로그인 페이지로 오기 전 URL을 가져옴
		String url = request.getHeader("Referer");
		//URL이 있거나 /login이 아니면 세션에 URL을 저장
		if(url != null && !url.contains("/login")) {
			request.getSession().setAttribute("prevUrl", url);
		}
		
		request.getRequestDispatcher("/WEB-INF/views/member/login.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String me_id = request.getParameter("me_id");
		String me_pw = request.getParameter("me_pw");
		
		MemberVO member = new MemberVO(me_id, me_pw);
		
		MemberVO user = memberService.login(member);
		
		//가져온 회원정보를 세션에 저장
		request.getSession().setAttribute("user", user);
		
		if(user != null) {
			request.setAttribute("url", "/");
			request.setAttribute("msg", "로그인에 성공했습니다.");
			//자동로그인을 체크했으면
			String auto = request.getParameter("auto");
			if(auto != null && auto.equals("true")) {
				//쿠키를 생성하고 DB에 쿠키와 만료시간을 저장
				Cookie cookie = memberService.creatCookie(user, request);
				response.addCookie(cookie);
			}
			
				
		} else {
			request.setAttribute("url", "/login");
			request.setAttribute("msg", "로그인에 실패했습니다.");
		}
		request.getRequestDispatcher("/WEB-INF/views/message.jsp").forward(request, response);
	}

}
