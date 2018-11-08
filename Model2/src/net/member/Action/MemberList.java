package net.member.Action;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.member.controller.Action;
import net.member.controller.ActionForward;
import net.member.db.MemberBean;
import net.member.db.MemberDAO;

public class MemberList implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("MemberListAction execute()");
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		
		if(id==null || !id.equals("admin")){
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('잘못된 접근입니다');");
			out.println("history.back();");
			out.println("</script>");
			out.close();
			return null;
		}
		
		MemberDAO mdao = new MemberDAO();
		List<MemberBean> memberList = mdao.getMemberList();
		ActionForward forward = new ActionForward();
		request.setAttribute("memberList", memberList);
		forward.setRedirect(false);
		forward.setPath("./member/list.jsp");
		return forward;
	}

}
