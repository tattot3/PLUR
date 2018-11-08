package net.order.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class OrderList implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//세션값 
		// OrderDAO odao 객체생성
        //orderList = getOrderList(id) 호출
		// 저장 orderList
		//    =>   ./goods_order/order_list.jsp
		return null;
	}

}
