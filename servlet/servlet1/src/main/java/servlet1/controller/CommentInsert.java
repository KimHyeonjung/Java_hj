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
 * Servlet implementation class CommentInsert
 */
@WebServlet("/comment/insert")
public class CommentInsert extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private PostService postService = new PostServiceImp();    
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String po_numStr = request.getParameter("cm_po_num");
		String cm_content = request.getParameter("cm_content");
		String ori_numStr = request.getParameter("cm_ori_num");
		JSONObject jobj = new JSONObject();
		
		try {
			int cm_po_num = Integer.parseInt(po_numStr);
			int cm_ori_num = Integer.parseInt(ori_numStr);
			//회원 정보 가져옴
			MemberVO user = (MemberVO)request.getSession().getAttribute("user");
			String cm_me_id = user.getMe_id();
			CommentVO comment = new CommentVO(cm_po_num, cm_ori_num, cm_me_id, cm_content);
			boolean res = postService.insertComment(comment);
			jobj.put("result", res);
		} catch (Exception e) {
			e.printStackTrace();
		}
		response.setContentType("application/json; charset=utf-8");
		response.getWriter().print(jobj);
	}

}
