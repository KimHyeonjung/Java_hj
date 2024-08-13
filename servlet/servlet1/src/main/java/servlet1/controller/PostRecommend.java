package servlet1.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import servlet1.model.vo.MemberVO;
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
		try {
			int po_num = Integer.parseInt(poNum);
			int state = Integer.parseInt(stateStr);
			String me_id =  user.getMe_id();
			RecommendVO reco = new RecommendVO(po_num, state, me_id);
			int res = postService.recommend(reco);
			if(res == 1) {
				request.setAttribute("msg", "추천했습니다.");
			} else if(res == -1){
				request.setAttribute("msg", "비추천했습니다.");
				
			} else if(state == 1) {
				request.setAttribute("msg", "추천을 취소 했습니다.");
			} else {
				request.setAttribute("msg", "비추천을 취소 했습니다.");
			}
		} catch (Exception e) {
			request.setAttribute("msg", "추천을 하지 못했습니다.");
			e.printStackTrace();
		}
		
		
		request.setAttribute("url", "/post/detail?po_num="+poNum);
		request.getRequestDispatcher("/WEB-INF/views/message.jsp").forward(request, response);
	}


}
