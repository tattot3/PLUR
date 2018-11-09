package net.admin.order.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminOrderFrontController extends HttpServlet{
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 가상주소 뽑아오기
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = requestURI.substring(contextPath.length());
		System.out.println("뽑아온 가상주소 : "+command);
		// 주소비교
		ActionForward forward = null;
		Action action = null;
		if(command.equals("/AdminOrderList.ao")){
//	      "/AdminOrderList.ao"
			// AdminOrderList 자바파일 
			// //getAdminOrderList() 메서드호출
			//  ./adminorder/admin_order_list.jsp
			// 이동
			action=new AdminOrderList();
			try{
				forward=action.execute(request, response);
			}catch(Exception e){e.printStackTrace();}
		}else if(command.equals("/AdminOrderDetail.ao")){
			// /AdminOrderDetail.ao 
			//  AdminOrderDetail 자바파일
			//  //getAdminOrderDetail(trade_num)메서드호출
			//  ./adminorder/admin_order_modify.jsp
			action=new AdminOrderDetail();
			try{
				forward=action.execute(request, response);
			}catch(Exception e){e.printStackTrace();}
		}else if(command.equals("/AdminOrderModify.ao")){
			//  /AdminOrderModify.ao
			//  AdminOrderModify 자바파일
			//  updateOrder(orderbean)함수호출
			//  ./AdminOrderList.ao 이동
			action=new AdminOrderModify();
			try{
				forward=action.execute(request, response);
			}catch(Exception e){e.printStackTrace();}
		}
		
		//이동
		if(forward!=null){
			if(forward.isRedirect()){
				response.sendRedirect(forward.getPath());
			}else{
				RequestDispatcher dispatcher
				=request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
			}
		}
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
