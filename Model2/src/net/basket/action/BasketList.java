package net.basket.action;

import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sun.corba.se.impl.ior.NewObjectKeyTemplateBase;

import net.basket.db.BasketDAO;

public class BasketList implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("BasketList execute()");
		ActionForward forward = new ActionForward();
		// 세션값 가져오기
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		
		//  =>Vector vector=getBasketList(id)메서드
		BasketDAO bdao = new BasketDAO();
		Vector vector = bdao.getBasketList(id);
		//List basketList=vector.get(0);
		List basketList = (List)vector.get(0);
		//List goodsList=vector.get(1);
		List goodsList= (List)vector.get(1);
		// 데이터 저장 basketList   goodsList
		request.setAttribute("basketList", basketList);
		request.setAttribute("goodsList", goodsList);
		// 이동    ./goods_order/goods_basket.jsp
		forward.setRedirect(false);
		forward.setPath("./goods_order/goods_basket.jsp");
		return forward;
	}

}
