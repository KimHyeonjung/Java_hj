package servlet1.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import servlet1.model.vo.CommunityVO;
import servlet1.model.vo.PostVO;
import servlet1.service.PostService;
import servlet1.service.PostServiceImp;

/**
 * Servlet implementation class PostList
 */
@WebServlet("/post/list")
public class PostList extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private PostService postService = new PostServiceImp();
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//화면에서 보낸 커뮤니티 번호를 가져옴
		int coNum = 0; 
		try {
			coNum = Integer.parseInt(request.getParameter("co_num"));
			//서비스에게 커뮤니티 번호를 주면서 커뮤니티 정보를 가져오라고 시킴
			CommunityVO co = postService.getCommunity(coNum);
			//커뮤니티 정보가 없으면 예외를 발생시킴
			if(co == null) {
				throw new NullPointerException();
			}
			//서비스에게 커뮤니티 번호를 주면서 게시글 리스트를 가져오라고 시킴
			List<PostVO> list = postService.getPostList(co.getCo_num());
			//화면에 커뮤니티 정보를 전송
			request.setAttribute("co", co);
			//화면에 가져온 게시글 리스트를 전송
			request.setAttribute("list", list);
			
			request.getRequestDispatcher("/WEB-INF/views/post/list.jsp").forward(request, response);
		} catch (Exception e) {
			request.setAttribute("msg", "잘못된 커뮤니티입니다.");
			request.setAttribute("url", "/community");
			request.getRequestDispatcher("/WEB-INF/views/message.jsp").forward(request, response);
		}
	}

	

}
