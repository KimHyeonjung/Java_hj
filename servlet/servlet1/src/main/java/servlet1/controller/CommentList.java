package servlet1.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.fasterxml.jackson.databind.ObjectMapper;

import servlet1.model.vo.CommentVO;
import servlet1.pagination.CommentCriteria;
import servlet1.pagination.Criteria;
import servlet1.pagination.PageMaker;
import servlet1.service.PostService;
import servlet1.service.PostServiceImp;

/**
 * Servlet implementation class CommentList
 */
@WebServlet("/comment/list")
public class CommentList extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private PostService postService = new PostServiceImp(); 
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String po_numStr = request.getParameter("po_num");
		String pageStr = request.getParameter("page");
		JSONObject jobj = new JSONObject();	
		ObjectMapper om = new ObjectMapper();
		try {
			int po_num = Integer.parseInt(po_numStr);
			int page = Integer.parseInt(pageStr);
			Criteria cri = new CommentCriteria(page, 4, po_num);
			//댓글 목록
			List<CommentVO> list = postService.getCommntList(cri);
			//댓글 페이지 네이션
			PageMaker pm = postService.getCommentPageMaker(cri);
			jobj.put("list", list);
			jobj.put("pm", om.writeValueAsString(pm));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		response.setContentType("application/json; charset=utf-8");
		response.getWriter().print(jobj);
	}

}
