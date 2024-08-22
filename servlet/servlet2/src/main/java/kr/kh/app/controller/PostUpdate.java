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
 * Servlet implementation class PostUpdate
 */
@WebServlet("/post/update")
public class PostUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PostService postService = new PostServiceImp();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String po_num = request.getParameter("po_num");
		PostVO post = postService.getPostDetail(po_num);
		request.setAttribute("post", post);
		request.getRequestDispatcher("/WEB-INF/views/post/update.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String num = request.getParameter("po_num");
		String title = request.getParameter("title");
		String sub = request.getParameter("sub");
		MemberVO user = (MemberVO)request.getSession().getAttribute("user");
		try {
			int po_num = Integer.parseInt(num);
			PostVO post = new PostVO(po_num, title, sub);

			boolean res = postService.updatePost(post, user);
			if(res) {
				request.setAttribute("msg", "게시글을 수정하였습니다.");
			}else {
				throw new RuntimeException();
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "게시글 수정에 실패하였습니다.");
		}

		request.setAttribute("url", "/post/detail?po_num="+num);
		request.getRequestDispatcher("/WEB-INF/views/message.jsp").forward(request, response);
	}
}
