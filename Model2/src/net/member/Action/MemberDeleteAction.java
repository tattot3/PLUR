package net.member.Action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.member.controller.Action;
import net.member.controller.ActionForward;
import net.member.db.MemberBean;
import net.member.db.MemberDAO;

public class MemberDeleteAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("MemberDeleteAction execute()");
		ActionForward foward = new ActionForward();
		/*HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");*/
		
		String id = request.getParameter("id");
		String pass = request.getParameter("pass");
		
		MemberBean mb = new MemberBean();
		mb.setId(id);
		mb.setPass(pass);
		MemberDAO mdao = new MemberDAO();
		
		int result = mdao.userCheck(id, pass);
		if(result == 1){
			mdao.deleteMember(mb);
			HttpSession session = request.getSession();
			session.invalidate();
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('회원님의 계정이 삭제되었습니다');");
			out.println("location.href='./MemberLogin.me';");
			out.println("</script>");
			out.close();
			/*foward.setRedirect(true);
			foward.setPath("./MemberLogin.me");*/
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
		return foward;
	}

}
