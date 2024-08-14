package servlet1.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import servlet1.model.vo.CommentVO;
import servlet1.model.vo.MemberVO;
import servlet1.service.PostService;
import servlet1.service.PostServiceImp;

/**
 * Servlet implementation class CommentUpdate
 */
@WebServlet("/comment/update")
public class CommentUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PostService postService = new PostServiceImp();   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String num = request.getParameter("cm_num");
		String cm_content = request.getParameter("cm_content");
		JSONObject jobj = new JSONObject();	
		try {
			int cm_num = Integer.parseInt(num);
			MemberVO user = (MemberVO)request.getSession().getAttribute("user");
			CommentVO comment = new CommentVO(cm_num, cm_content);
			boolean res = postService.updateComment(comment, user);
			jobj.put("result", res);
		} catch (Exception e) {
			e.printStackTrace();
		}
		response.setContentType("application/json; charset=utf-8");
		response.getWriter().print(jobj);
	}

}
