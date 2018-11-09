package net.order.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.order.db.OrderDAO;

public class OrderDetail implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("trade_num execute()");
		ActionForward forward = new ActionForward();
		// trade_num 파라미터 가져오기
		String trade_num = request.getParameter("trade_num");
		System.out.println(trade_num);
		// OrderDAO odao 객체생성
		OrderDAO odao = new OrderDAO();
		// orderDetailList =orderDetail(trade_num)호출
		List orderDetailList = odao.orderDetail(trade_num);
		// 저장 orderDetailList
		request.setAttribute("orderDetailList", orderDetailList);
		//   => ./goods_order/order_detail.jsp
		forward.setRedirect(false);
		forward.setPath("./goods_order/order_detail.jsp");
		
		return forward;
	}

}
