package net.member.Action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.member.controller.Action;
import net.member.controller.ActionForward;
import net.member.db.MemberBean;
import net.member.db.MemberDAO;

public class MemberInfoAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("MemberInfoAction execute()");
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		
		if(id == null){
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('로그인해주세요');");
			out.println("location.href='./MemberLogin.me';");
			out.println("</script>");
			out.close();
			return null;
			}
		
		//MemberDAO 객체 생성
		MemberDAO mdao = new MemberDAO();
		//리턴값을 저장할 변수 = getMember(세션값) 메서드 호출
		MemberBean mb = mdao.getMember(id);
		//ActionFoward 객체생성
		ActionForward forward = new ActionForward();
		//request 멤버정보 저장
		request.setAttribute("mb", mb);
		//이동 ./member/info.jsp
		forward.setRedirect(false);
		forward.setPath("./member/info.jsp");
		
		return forward;
	}
	
	

}
