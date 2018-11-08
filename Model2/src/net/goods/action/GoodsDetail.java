package net.goods.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.admin.goods.db.GoodsBean;
import net.goods.db.GoodsDAO;

public class GoodsDetail implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("GoodsDetail execute()");
		ActionForward forward = new ActionForward();
		//파라미터 가져오기 num
		int num=Integer.parseInt(request.getParameter("num"));
		// GoodsDAO gdao객체생성
		GoodsDAO gdao = new GoodsDAO();
		// gBean = getGoods()메서드호출
		GoodsBean gBean = new GoodsBean();
		gBean = gdao.getGoods(num);
		// 데이터 저장 gBean
		request.setAttribute("gBean", gBean);
		// 이동 ./goods/goods_detail.jsp 
		forward.setRedirect(false);
		forward.setPath("./goods/goods_detail.jsp");
		return forward;
	}

}
