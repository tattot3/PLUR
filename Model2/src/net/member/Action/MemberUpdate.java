package net.member.Action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.member.controller.Action;
import net.member.controller.ActionForward;
import net.member.db.MemberBean;
import net.member.db.MemberDAO;

public class MemberUpdate implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("MemberUpdate execute()");
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
		
		MemberDAO mdao = new MemberDAO();
		MemberBean mb = mdao.getMember(id);
		ActionForward forward = new ActionForward();
		request.setAttribute("mb", mb);
		
		//이동
		forward.setRedirect(false);
		forward.setPath("./member/updateForm.jsp");
		
		return forward;
	}
	
}
