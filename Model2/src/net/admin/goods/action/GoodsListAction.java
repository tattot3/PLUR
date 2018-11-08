package net.admin.goods.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.admin.goods.db.AdminGoodsDAO;

public class GoodsListAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("GoodsListAction execute()");
		ActionForward forward = new ActionForward();
		//AdminGoodsDAO agdao 객체생성
		AdminGoodsDAO agdao = new AdminGoodsDAO();
		//메서드호출 // getGoodsList()메서드
		
		// 저장  goodsList
		List goodsList = agdao.getGoodsList();
		// 이동 ./admingoods/admin_goods_list.jsp
		request.setAttribute("goodsList", goodsList);
		forward.setRedirect(false);
		forward.setPath("./admingoods/admin_goods_list.jsp");
		return forward;
	}
}
