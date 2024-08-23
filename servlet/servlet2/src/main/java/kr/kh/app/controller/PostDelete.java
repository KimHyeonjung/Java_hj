package kr.kh.app.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.kh.app.model.vo.MemberVO;
import kr.kh.app.service.PostService;
import kr.kh.app.service.PostServiceImp;

/**
 * Servlet implementation class PostDelete
 */
@WebServlet("/post/delete")
public class PostDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PostService postService = new PostServiceImp();
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String po_num = request.getParameter("po_num");
		
		MemberVO user = (MemberVO)request.getSession().getAttribute("user");
		
		int coNum = postService.deletePost(po_num, user);
		if(coNum != 0) {
			request.setAttribute("url", "/post/list?co_num="+ coNum);
			request.setAttribute("msg", "게시글을 삭제했습니다.");
		}else {
			request.setAttribute("url", "/post/detail?po_num="+po_num);
			request.setAttribute("msg", "게시글을 삭제하지 못했습니다.");
		}
		
		request.getRequestDispatcher("/WEB-INF/views/message.jsp").forward(request, response);
	}
}
