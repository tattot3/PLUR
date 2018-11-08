package net.order.action;

import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.dbcp.pool2.BaseKeyedPooledObjectFactory;

import net.admin.goods.db.GoodsBean;
import net.basket.db.BasketBean;
import net.basket.db.BasketDAO;
import net.goods.db.GoodsDAO;
import net.order.db.OrderBean;
import net.order.db.OrderDAO;

public class OrderAdd implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
			System.out.println("OrderAdd execute()");
			ActionForward forward = new ActionForward();
			HttpSession session = request.getSession();
			String id = (String)session.getAttribute("id");
		
			//  주문한 금액 결제 =>  LG유플러스 전자결제서비스  이용
			//      http://ecredit.uplus.co.kr/
		
			// 장바구니 정보 => //  getBasketList(id) 호출 주문테이블 저장 
			BasketDAO bdao = new BasketDAO();
			Vector vector = bdao.getBasketList(id);
			BasketBean basketbean = (BasketBean)vector.get(0);
			GoodsBean goodsbean = (GoodsBean)vector.get(0);
			
			// 주문한아이디, 배송지정보, 결제정보 => // OrderBean ob 저장 
			OrderBean orderbean = new OrderBean();
			/*orderbean.setO_m_id(id);*/
			orderbean.setO_receive_addr1(request.getParameter("o_receive_addr1"));
			orderbean.setO_receive_addr2(request.getParameter("o_receive_addr2"));
			orderbean.setO_memo(request.getParameter("o_memo"));
			/*orderbean.setO_g_amount(basketbean.getB_g_amount());
			orderbean.setO_g_color(basketbean.getB_g_color());
			orderbean.setO_g_name(goodsbean.getName());
			orderbean.setO_g_num(goodsbean.getNum());
			orderbean.setO_g_size(basketbean.getB_g_size());*/
			
			
			//  주문테이블 저장		
			//  basketList, goodsList, orderBean
			/*request.setAttribute("basketList", basketList);
			request.setAttribute("goodsList", goodsList);
			request.setAttribute("orderbean", orderbean);*/
		    // OrderDAO odao 객체생성 
			OrderDAO odao = new OrderDAO();
			// addOrder(orderbean,basketList,goodsList)메서드호출
			/*odao.addOrder(orderbean, basketList, goodsList);*/
			
			// 상품테이블에서  총수량  - 주문수량  차감 
			// GoodsDAO  updateAmount(basketList) 메서드호출
			GoodsDAO gdao = new GoodsDAO();
			/*gdao.updateAmount(basketList);*/
			
			// 주문완료시  => 사용자 장바구니 정보 삭제   
			// BasketDAO deleteBasket(String id)
			bdao.deleteBasket(id);
			
			
			//이동   ./OrderList.or
			forward.setRedirect(true);
			forward.setPath("./OrderList.or");
			
		return forward;
	}

}
