package kr.kh.app.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.kh.app.model.vo.MemberVO;
import kr.kh.app.model.vo.PostVO;
import kr.kh.app.service.PostService;
import kr.kh.app.service.PostServiceImp;

/**
 * Servlet implementation class PostInsert
 */
@WebServlet("/post/insert")
public class PostInsert extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private PostService postService = new PostServiceImp();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String co_num = request.getParameter("co_num");
		String co_name = request.getParameter("co_name");
		
		request.setAttribute("co_num", co_num);
		request.setAttribute("co_name", co_name);
		
		request.getRequestDispatcher("/WEB-INF/views/post/insert.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String co_num = request.getParameter("po_co_num");
		String title = request.getParameter("title");
		String sub = request.getParameter("sub");
		MemberVO user = (MemberVO)request.getSession().getAttribute("user");
		try {
			int po_co_num = Integer.parseInt(co_num);
			PostVO post = new PostVO(po_co_num, title, sub,  user.getMe_id());
			boolean res = postService.insertPost(post);
			
			if(res) {
				request.setAttribute("msg", "게시글을 등록하였습니다.");
			}else {
				throw new RuntimeException();
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "게시글 등록을 실패하였습니다.");
		}
		
		request.setAttribute("url", "/post/list?co_num="+co_num);
		request.getRequestDispatcher("/WEB-INF/views/message.jsp").forward(request, response);
	}
}
