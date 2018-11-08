package net.basket.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.basket.db.BasketDAO;

public class BasketDelete implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("BasketDelete execute()");
		ActionForward forward = new ActionForward();
		//세션값 
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		
		// b_num 파라미터
		int b_num = Integer.parseInt(request.getParameter("b_num"));
		// BasketDAO bdao 객체생성 
		BasketDAO bdao = new BasketDAO();
		// basketDelete(b_num)메서드호출
		bdao.basketDelete(b_num);
		// 이동 ./BasketList.ba
		forward.setRedirect(true);
		forward.setPath("./BasketList.ba");
		return forward;
	}

}
