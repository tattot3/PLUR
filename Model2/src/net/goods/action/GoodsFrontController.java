package net.goods.action;

import java.io.IOException;

import javax.print.attribute.standard.RequestingUserName;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GoodsFrontController extends HttpServlet{
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 가상주소 뽑아오기
		// http://localhost:8080/Model2/GoodsList.go
		String requestURI=request.getRequestURI();
		String contextPath=request.getContextPath();
		String command=requestURI.substring(contextPath.length());
		System.out.println("뽑아온 가상주소: "+command);
		// 주소 비교   
		ActionForward forward = null;
		Action action = null;
		if(command.equals("/GoodsList.go")){
			//  /GoodsList.go 
			//  GoodsList 자바  => ./goods/goods_list.jsp
			//  파라미터 item 없으면  "all"
			action=new GoodsList();
			try{
				forward = action.execute(request, response);
			}catch(Exception e){e.printStackTrace();}
		}else if(command.equals("/GoodsDetail.go")){
			//  /GoodsDetail.go  num 파라미터
			//  GoodsDetail 자바 => ./goods/goods_detail.jsp
			action=new GoodsDetail();
			try{
				forward=action.execute(request, response);
			}catch(Exception e){e.printStackTrace();}
		}
		
		// 이동
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
