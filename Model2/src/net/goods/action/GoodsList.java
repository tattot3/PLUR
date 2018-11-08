package net.goods.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.goods.db.GoodsDAO;

public class GoodsList implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("GoodsList execute()");
		ActionForward forward = new ActionForward();
		String item = request.getParameter("item");
		if(item==null){
			item="all";
		}
		// GoodsDAO gdao 객체생성
		GoodsDAO gdao = new GoodsDAO();
		// goodsList =getGoodsList() 메서드호출
		List goodsList = gdao.getGoodsList(item);
		// 저장 goodsList
		request.setAttribute("goodsList", goodsList);
		// 이동 ./goods/goods_list.jsp
		
		forward.setRedirect(false);
		forward.setPath("./goods/goods_list.jsp");
		return forward;
	}

}
