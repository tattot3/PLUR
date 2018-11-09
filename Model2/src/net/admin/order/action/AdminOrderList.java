package net.admin.order.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.admin.order.db.AdminOrderDAO;

public class AdminOrderList implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("AdminOrderList execute()");
		ActionForward forward = new ActionForward();
		String trade_num = request.getParameter("trade_num");
		
		//AdminOrderDAO aodao 객체생성
		AdminOrderDAO adao = new AdminOrderDAO();
		//  adminOrderList  = getAdminOrderList() 메서드호출
		List adminOrderList = adao.getAdminOrderList();
		// 저장 adminOrderList
		request.setAttribute("adminOrderList", adminOrderList);
		//  ./adminorder/admin_order_list.jsp
		// 이동
		forward.setRedirect(false);
		forward.setPath("./adminorder/admin_order_list.jsp");
		return forward;
	}

}
