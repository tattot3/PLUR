package net.admin.order.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.admin.order.db.AdminOrderDAO;
import net.order.db.OrderBean;

public class AdminOrderModify implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("AdminOrderModify execute()");
		ActionForward forward = new ActionForward();
		//OrderBean 객체생성 <= 파라미터 정보 저장		
		OrderBean ob = new OrderBean();
		//trade_num  status trans_num 파라미터 정보가져오기
		String trade_num = request.getParameter("trade_num");
		String trans_num = request.getParameter("trans_num");
		int status = Integer.parseInt(request.getParameter("status"));
		
		ob.setO_trans_num(trans_num);
		ob.setO_trade_num(trade_num);
		ob.setO_status(status);
		// AdminOrderDAO aodao 객체생성
		AdminOrderDAO adao = new AdminOrderDAO();
		//  updateOrder(orderbean)함수호출
		adao.updateOrder(ob);
		//  ./AdminOrderList.ao 이동
		forward.setRedirect(true);
		forward.setPath("./AdminOrderList.ao");
		return forward;
	}

}
