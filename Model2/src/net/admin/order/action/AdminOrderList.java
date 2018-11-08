package net.admin.order.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminOrderList implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//AdminOrderDAO aodao 객체생성
		//  adminOrderList  = getAdminOrderList() 메서드호출
		// 저장 adminOrderList
		//  ./adminorder/admin_order_list.jsp
		// 이동
		return null;
	}

}
