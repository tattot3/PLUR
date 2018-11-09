package net.admin.order.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.admin.order.db.AdminOrderDAO;

public class AdminOrderDetail implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("AdminOrderDetail execute()");
		ActionForward forward = new ActionForward();
		// trade_num 파라미터 가져오기
		String trade_num=request.getParameter("trade_num");
		
		//AdminOrderDAO aodao 객체생성	
		AdminOrderDAO adao = new AdminOrderDAO();
		//  adminOrderDetail =getAdminOrderDetail(trade_num)메서드호출
		List adminOrderDetail = adao.getAdminOrderDetail(trade_num);
		// 저장 adminOrderDetail
		request.setAttribute("adminOrderDetail", adminOrderDetail);
		//  ./adminorder/admin_order_modify.jsp
		forward.setRedirect(false);
		forward.setPath("./adminorder/admin_order_modify.jsp");
		return forward;
	}

}
