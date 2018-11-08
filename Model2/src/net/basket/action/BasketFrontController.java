package net.basket.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BasketFrontController extends HttpServlet{
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//가상주소뽑아오기
		String requestURI=request.getRequestURI();
		String contextPath=request.getContextPath();
		String command=requestURI.substring(contextPath.length());
		System.out.println("뽑아온 가상주소: "+command);
		//주소비교
		ActionForward forward=null;
		Action action=null;
		if(command.equals("/BasketAdd.ba")){
			// /BasketAdd.ba => BasketAdd 자바
			// 세션값 가져오기 없으면 "./MemberLogin.me
			//  b_m_id <= 세션값    자바빈 변수 저장
			//  같은 상품 이면 수량증가 
			//  checkGoods(basketbean)
			//  check 1이 아니면  새로운 상품 추가 basketAdd(basketbean) 
			//  이동 ./BasketList.ba 
			action=new BasketAdd();
			try{
				forward=action.execute(request, response);
			}catch(Exception e){e.printStackTrace();}
		}else if(command.equals("/BasketList.ba")){
			//  /BasketList.ba => BasketList 자바
			//  =>Vector vector=getBasketList(id)메서드
			//List basketList=vector.get(0);
			//List goodsList=vector.get(1);
			//    ./goods_order/goods_basket.jsp
			action=new BasketList();
			try{
				forward=action.execute(request, response);
			}catch(Exception e){e.printStackTrace();}
		}else if(command.equals("/BasketDelete.ba")){
			action=new BasketDelete();
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
