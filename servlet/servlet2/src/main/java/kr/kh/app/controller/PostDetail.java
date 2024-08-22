package kr.kh.app.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.kh.app.model.vo.PostVO;
import kr.kh.app.service.PostService;
import kr.kh.app.service.PostServiceImp;

/**
 * Servlet implementation class PostDetail
 */
@WebServlet("/post/detail")
public class PostDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PostService postService = new PostServiceImp();
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String po_num = request.getParameter("po_num");
		String page = request.getParameter("page");
		
		//게시글 조회수를 증가
		postService.updatePostView(po_num);
		PostVO post = postService.getPostDetail(po_num);
		
		request.setAttribute("post", post);
		request.setAttribute("page", page);
		
		request.getRequestDispatcher("/WEB-INF/views/post/detail.jsp").forward(request, response);
	}


}
