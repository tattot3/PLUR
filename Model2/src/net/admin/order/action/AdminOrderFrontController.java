package net.admin.order.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.order.db.OrderBean;

public class AdminOrderFrontController extends HttpServlet{
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 가상주소 뽑아오기
		// 주소비교
		//      "/AdminOrderList.ao"
		// AdminOrderList 자바파일 
		// //getAdminOrderList() 메서드호출
		//  ./adminorder/admin_order_list.jsp
		// 이동
		
		// /AdminOrderDetail.ao 
		//  AdminOrderDetail 자바파일
		//  //getAdminOrderDetail(trade_num)메서드호출
		//  ./adminorder/admin_order_modify.jsp
		
		//  /AdminOrderModify.ao
		//  AdminOrderModify 자바파일
		//  updateOrder(orderbean)함수호출
		//  ./AdminOrderList.ao 이동
	}
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
}
