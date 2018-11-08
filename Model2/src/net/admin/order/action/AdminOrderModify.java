package net.admin.order.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminOrderModify implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
//OrderBean 객체생성 <= 파라미터 정보 저장		
		//trade_num  status trans_num 파라미터 정보가져오기
		// AdminOrderDAO aodao 객체생성
			//  updateOrder(orderbean)함수호출
			//  ./AdminOrderList.ao 이동
		return null;
	}

}
