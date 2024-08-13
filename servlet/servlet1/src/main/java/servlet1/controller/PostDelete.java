package servlet1.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import servlet1.model.vo.MemberVO;
import servlet1.model.vo.PostVO;
import servlet1.service.PostService;
import servlet1.service.PostServiceImp;

/**
 * Servlet implementation class PostDelete
 */
@WebServlet("/post/delete")
public class PostDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PostService postService = new PostServiceImp();   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//게시글 번호를 받아옴
		String po_numStr = request.getParameter("po_num");	
		String co_numStr = request.getParameter("co_num");	
		String pw = request.getParameter("pw");
		try {
			int po_num = Integer.parseInt(po_numStr);
			//회원 정보를 받아옴
			MemberVO user = (MemberVO)request.getSession().getAttribute("user");
			if(user.getMe_pw().equals(pw)) {
				//서비스에게 게시글 정보와 회원 정보를 주면서 게시글을 삭제하라고 요청
				if(postService.deletePost(po_num, user)) {
					request.setAttribute("msg", "게시글 삭제에 성공했습니다.");
					request.setAttribute("url", "/post/list?co_num="+co_numStr);
				}
				//실패하면 실패 알림
				else {
					throw new RuntimeException();
				}
			} else {
				request.setAttribute("msg", "비빌번호가 잘못되었습니다.");
				request.setAttribute("url", "/post/detail?po_num="+po_numStr);
			}
		} catch (Exception e) {
			request.setAttribute("msg", "게시글 삭제에 실패했습니다.");
			request.setAttribute("url", "/post/list?co_num="+co_numStr);
		}
		request.getRequestDispatcher("/WEB-INF/views/message.jsp").forward(request, response);
	}

}
