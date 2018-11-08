package net.member.Action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.member.controller.Action;
import net.member.controller.ActionForward;
import net.member.db.MemberBean;
import net.member.db.MemberDAO;

public class MemberUpdateAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("MemberUpdateAction execute()");
		ActionForward forward = new ActionForward();
		request.setCharacterEncoding("utf-8");
		
		String id = request.getParameter("id");
		String pass = request.getParameter("pass");
		String name = request.getParameter("name");
		
		MemberBean mb = new MemberBean();
		mb.setId(id);
		mb.setPass(pass);
		mb.setName(name);
		MemberDAO mdao = new MemberDAO();
		
		int result = mdao.userCheck(id, pass);
		if(result == 1){
			mdao.updateMember(mb);
			ActionForward foward = new ActionForward();
			foward.setRedirect(true);
			foward.setPath("./MemberInfo.me");
			return foward;
		} if(result == 0 || result == -1){
			mdao.updateMember(mb);
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('아이디 또는 비밀번호가 일치하지 않습니다');");
			out.println("history.back();");
			out.println("</script>");
			out.close();
			return null;
		}
		return forward;
	}

}
