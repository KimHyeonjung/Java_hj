package kr.kh.app.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.kh.app.model.vo.MemberVO;
import kr.kh.app.service.MemberService;
import kr.kh.app.service.MemberServiceImp;

/**
 * Servlet implementation class Logout
 */
@WebServlet("/logout")
public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private MemberService memberService = new MemberServiceImp();   
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//회원의 쿠키 정보를 업데이트 null로
		MemberVO user = (MemberVO)request.getSession().getAttribute("user");
		if(user != null) {
			user.setMe_cookie(null);
			memberService.updateMemberCookie(user);
		}
		request.getSession().removeAttribute("user");
		
		request.setAttribute("url", "/");
		request.setAttribute("msg", "로그아웃을 했습니다.");
		request.getRequestDispatcher("/WEB-INF/views/message.jsp").forward(request, response);
	}


}
