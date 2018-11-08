package net.admin.goods.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminGoodsFrontController extends HttpServlet{
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//가상주소 뽑아오기
				// http://localhost:8080/Model2/GoodsAdd.ag
				String requestURI=request.getRequestURI();
				//  /Model2/GoodsAdd.ag
				String contextPath=request.getContextPath();
				//  /Model2
				String command=requestURI.substring(contextPath.length());
				//  /GoodsAdd.ag
				System.out.println("뽑아온 가상주소 : "+command);
		//주소비교
				ActionForward forward=null;
				Action action=null;
				if(command.equals("/GoodsAdd.ag")){
					// 이동  ./admingoods/admin_goods_write.jsp
					forward=new ActionForward();
					forward.setRedirect(false);
					forward.setPath("./admingoods/admin_goods_write.jsp");
				}else if(command.equals("/GoodsAddAction.ag")){
					action=new GoodsAddAction();
					try {
						forward=action.execute(request, response);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}else if(command.equals("/GoodsList.ag")){
					action=new GoodsListAction();
					try {
						forward=action.execute(request, response);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}else if(command.equals("/GoodsModify.ag")){
					action=new GoodsModify();
					try {
						forward=action.execute(request, response);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}else if(command.equals("/GoodsModifyAction.ag")){
					action=new GoodsModifyAction();
					try {
						forward=action.execute(request, response);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}else if(command.equals("/GoodsDelete.ag")){
					action=new GoodsDeleteAction();
					try {
						forward=action.execute(request, response);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
		//이동
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
