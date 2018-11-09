package net.order.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.order.db.OrderDAO;

public class OrderList implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("OrderList execute()");
		ActionForward forward = new ActionForward();
		//세션값 
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		
		// OrderDAO odao 객체생성
		OrderDAO odao = new OrderDAO();
		//orderList = getOrderList(id) 호출
		List orderList = odao.getOrderList(id);
        
		// 저장 orderList
		request.setAttribute("orderList", orderList);
		//    =>   ./goods_order/order_list.jsp
		forward.setRedirect(false);
		forward.setPath("./goods_order/order_list.jsp");
		return forward;
	}

}
