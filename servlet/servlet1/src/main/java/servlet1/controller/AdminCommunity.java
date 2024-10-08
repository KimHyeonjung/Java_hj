package servlet1.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import servlet1.model.vo.CommunityVO;
import servlet1.service.PostService;
import servlet1.service.PostServiceImp;

/**
 * Servlet implementation class AdminCommunity
 */
@WebServlet("/admin/community")
public class AdminCommunity extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PostService postService = new PostServiceImp();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//등록된 커뮤니티 리스트를 가져옴
		List<CommunityVO> list = postService.getCommunityList();
		//화면에 커뮤니티 리스트를 전송(/admin/community.jsp)
		request.setAttribute("list", list);
		request.getRequestDispatcher("/WEB-INF/views/admin/community.jsp").forward(request, response);
	}

}
