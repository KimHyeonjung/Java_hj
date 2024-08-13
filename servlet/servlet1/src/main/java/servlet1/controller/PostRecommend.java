package servlet1.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.fasterxml.jackson.databind.ObjectMapper;

import servlet1.model.vo.MemberVO;
import servlet1.model.vo.PostVO;
import servlet1.model.vo.RecommendVO;
import servlet1.service.PostService;
import servlet1.service.PostServiceImp;

/**
 * Servlet implementation class PostLike
 */
@WebServlet("/post/recommend")
public class PostRecommend extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private PostService postService = new PostServiceImp();   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String poNum = request.getParameter("po_num");
		String stateStr = request.getParameter("state");
		MemberVO user = (MemberVO)request.getSession().getAttribute("user");
		JSONObject jobj = new JSONObject();	
		ObjectMapper om = new ObjectMapper();
		try {
			int po_num = Integer.parseInt(poNum);
			int state = Integer.parseInt(stateStr);
			String me_id =  user.getMe_id();
			RecommendVO reco = new RecommendVO(po_num, state, me_id);
			int res = postService.recommend(reco);
				
			PostVO post = postService.getPost(po_num);
			String postStr = om.writeValueAsString(post);//post객체의 값들을 json형태의 문자열로 변환
			
			jobj.put("result", res);
			jobj.put("post", postStr);
		} catch (Exception e) {
			e.printStackTrace();
			jobj.put("error", "Exception 발생");
		}
		response.setContentType("application/json; charset=utf-8");
		response.getWriter().print(jobj);
	}


}
