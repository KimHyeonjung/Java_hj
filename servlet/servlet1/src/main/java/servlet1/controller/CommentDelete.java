package servlet1.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import servlet1.model.vo.MemberVO;
import servlet1.service.PostService;
import servlet1.service.PostServiceImp;

/**
 * Servlet implementation class CommentDelete
 */
@WebServlet("/comment/delete")
public class CommentDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private PostService postService = new PostServiceImp();   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String num = request.getParameter("cm_num");
		JSONObject jobj = new JSONObject();	
		try {
			int cm_num = Integer.parseInt(num);
			MemberVO user = (MemberVO)request.getSession().getAttribute("user");
			boolean res = postService.deleteComment(cm_num, user);
			jobj.put("result", res);
		} catch (Exception e) {
			e.printStackTrace();
		}
		response.setContentType("application/json; charset=utf-8");
		response.getWriter().print(jobj);
	}

}
