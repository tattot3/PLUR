package net.basket.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.basket.db.BasketBean;
import net.basket.db.BasketDAO;

public class BasketAdd implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("BasketAdd execute()");
		request.setCharacterEncoding("utf-8");
		ActionForward forward = new ActionForward();
		int check=0;
		//  b_m_id <= 세션값
		HttpSession session = request.getSession();
		String b_m_id = (String) session.getAttribute("id");
		
		//id가 없으면 로그인 페이지로 이동
		if(b_m_id==null){
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('로그인해주세요');");
			out.println("location.href='./MemberLogin.me';");
			out.println("</script>");
			out.close();
			return null;
		}
		
		// BasketBean 객체생성 
		BasketBean basketbean = new BasketBean();
		// 자바빈 변수 저장  <= 파라미터  num, amount, size, color
		basketbean.setB_m_id(b_m_id);
		basketbean.setB_g_num(Integer.parseInt(request.getParameter("num")));
		basketbean.setB_g_amount(Integer.parseInt(request.getParameter("amount")));
		basketbean.setB_g_size(request.getParameter("size"));
		basketbean.setB_g_color(request.getParameter("color"));//color가 이상하게 저장됨
		
		//  같은 상품 이면 수량증가  check=1
			// 구현되고있음-> DB단에서 하는것?
		
		// check = checkGoods(basketbean) 같은상품 수량update
		BasketDAO bdao = new BasketDAO();
		check = bdao.checkGoods(basketbean);
		//  check 1이 아니면  새로운 상품 추가 basketAdd(basketbean)
		if(check!=1){
			bdao.basketAdd(basketbean);
		}
		// 이동   /BasketList.ba
		forward.setRedirect(true);
		forward.setPath("./BasketList.ba");
		return forward;
	}

}
