package servlet1.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import servlet1.model.vo.MemberVO;
import servlet1.model.vo.PostVO;
import servlet1.model.vo.RecommendVO;
import servlet1.service.PostService;
import servlet1.service.PostServiceImp;

/**
 * Servlet implementation class PostDetail
 */
@WebServlet("/post/detail")
public class PostDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PostService postService = new PostServiceImp();	   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//게시글 번호를 가져옴
		String numStr = request.getParameter("po_num");
		try {
			int num = Integer.parseInt(numStr);
			//조회수 증가
			postService.updatePostView(num);
			//서비스에게 가져온 게시글 번호에 맞는 게시글 정보를 가져오라고 시킴
			PostVO post = postService.getPost(num);
			//게시글을 화면에 전송
			request.setAttribute("post", post);
			
			//로그인한 회원의 추천 정보를 가져옴
			MemberVO user = (MemberVO)request.getSession().getAttribute("user");
			RecommendVO reco = postService.getRecommend(num, user);
			request.setAttribute("reco", reco);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		request.getRequestDispatcher("/WEB-INF/views/post/detail.jsp").forward(request, response);
	}
}
