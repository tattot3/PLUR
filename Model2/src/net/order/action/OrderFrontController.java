package net.order.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class OrderFrontController extends HttpServlet{
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 가상주소뽑아오기
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = requestURI.substring(contextPath.length());
		System.out.println("뽑아온 가상주소: " + command);
		// 주소비교
		ActionForward forward = null;
		Action action = null;
		if (command.equals("/OrderStar.or")) {
			// /OrderStar.or => OrderStar 자바
			// //getBasketList(id) 호출
			// //getMember(id) 호출
			// => ./goods_order/goods_buy.jsp
			action=new OrderStar();
			try{
				forward=action.execute(request, response);
			}catch(Exception e){e.printStackTrace();}
		} else if (command.equals("/OrderAdd.or")) {
			// /OrderAdd.or => OrderAdd 자바
			// 주문한 금액 결제 => LG유플러스 전자결제서비스 이용

			// 장바구니 정보 => 주문테이블 저장
			// 주문한아이디, 배송지정보, 결제정보 => 주문테이블 저장
			// basketList, goodsList, orderBean
			// addOrder(orderbean,basketList,goodsList)

			// 상품테이블에서 총수량 - 주문수량 차감
			// GoodsDAO updateAmount(basketList) 메서드호출

			// 주문완료시 => 사용자 장바구니 정보 삭제
			// BasketDAO deleteBasket(String id)
			action=new OrderAdd();
			try{
				forward=action.execute(request, response);
			}catch(Exception e){e.printStackTrace();}


		} else if (command.equals("/OrderList.or")) {
			// 이동 ./OrderList.or => OrderList 자바파일
			// //getOrderList(id) 호출
			// => ./goods_order/order_list.jsp
			action=new OrderList();
			try{
				forward=action.execute(request, response);
			}catch(Exception e){e.printStackTrace();}

		} else if (command.equals("/OrderDetail.or")) {
			// /OrderDetail.or => OrderDetail자바파일
			// //orderDetail(trade_num)호출
			// => ./goods_order/order_detail.jsp
			action=new OrderDetail();
			try{
				forward=action.execute(request, response);
			}catch(Exception e){e.printStackTrace();}
		}
		// 이동
		if(forward!=null){
			if(forward.isRedirect()){
				//true sendRedirect()
				response.sendRedirect(forward.getPath());
			}else{
				//false forward()
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
