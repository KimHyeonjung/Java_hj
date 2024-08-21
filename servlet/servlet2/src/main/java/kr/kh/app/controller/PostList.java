package kr.kh.app.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.kh.app.model.vo.CommunityVO;
import kr.kh.app.model.vo.PostVO;
import kr.kh.app.pagination.Criteria;
import kr.kh.app.pagination.PageMaker;
import kr.kh.app.pagination.PostCriteria;
import kr.kh.app.service.PostService;
import kr.kh.app.service.PostServiceImp;

/**
 * Servlet implementation class PostList
 */
@WebServlet("/post/list")
public class PostList extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private PostService postService = new PostServiceImp();   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String num = request.getParameter("co_num");
		//페이지 번호를 가져옴
		String pageStr = request.getParameter("page"); 
		//검색어를 가져옴
		String search = request.getParameter("search");
		//타입을 가져옴
		String type = request.getParameter("type");
		try {
			int co_num = Integer.parseInt(num);
			int page = 1;
			if(pageStr != null) {
				page = Integer.parseInt(pageStr);
			}
			CommunityVO comu = postService.getCommunity(co_num);
			//현재 페이지 정보를 이용해서 게시글 목록을 가져옴			
			//페이지번호, 검색어, 타입을 이용해서 Criteria 객체를 생성
			Criteria cri = new PostCriteria(page, 4, co_num, search, type);
			//서비스에게 객체를 주면서 게시글 목록을 가져오라고 요청
			PageMaker pm = postService.getPostPageMaker(cri, 5);
			
			List<PostVO> list = postService.getPostList(cri);
			
			
			request.setAttribute("comu", comu);
			request.setAttribute("pm", pm);
			request.setAttribute("list", list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		request.getRequestDispatcher("/WEB-INF/views/post/list.jsp").forward(request, response);
	}


}
