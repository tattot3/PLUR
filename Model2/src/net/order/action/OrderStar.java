package net.order.action;

import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.basket.db.BasketDAO;
import net.member.db.MemberBean;
import net.member.db.MemberDAO;

public class OrderStar implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("OrderStar execute()");
		ActionForward forward = new ActionForward();
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		//  //getBasketList(id) 호출
				//장바구니의 정보를 가져온다.
		// getGoodsList(item) 호출
				//장바구니에 담기 상품의 정보를 추가로 가져온다
		BasketDAO bdao = new BasketDAO();
		Vector vector = bdao.getBasketList(id);
		List basketList = (List) vector.get(0);
		List goodsList = (List) vector.get(1);
		//  //getMember(id) 호출 
		//회원 정보를 가져온다
		MemberDAO mdao = new MemberDAO();
		MemberBean memberbean = mdao.getMember(id);
		
		// request.setAttribute("basketList", basketList);
		// request.setAttribute("goodsList", goodsList);
		// request.setAttribute("memberbean", memberbean);
		request.setAttribute("basketList", basketList);
		request.setAttribute("goodsList", goodsList);
		request.setAttribute("memberbean", memberbean);
		//  => ./goods_order/goods_buy.jsp
		forward.setRedirect(false);
		forward.setPath("./goods_order/goods_buy.jsp");
		
		return forward;
	}

}
