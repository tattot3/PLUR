package net.board.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.board.controller.Action;
import net.board.controller.ActionForward;
import net.board.db.BoardBean;
import net.board.db.BoardDAO;

public class BaordRewriteAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("RewriteAction execute()");
		ActionForward forward = new ActionForward();
		
		request.setCharacterEncoding("utf-8");
		String re_ref1 =request.getParameter("re_ref");
		String re_lev1 =request.getParameter("re_lev");
		String re_seq1 =request.getParameter("re_seq");
		
		BoardBean bb = new BoardBean();
		bb.setName(request.getParameter("name"));
		bb.setPass(request.getParameter("pass"));
		bb.setSubject(request.getParameter("subject"));
		bb.setContent(request.getParameter("content"));
		bb.setRe_ref(Integer.parseInt(re_ref1));
		bb.setRe_lev(Integer.parseInt(re_lev1));
		bb.setRe_seq(Integer.parseInt(re_seq1));
		
		BoardDAO bdao = new BoardDAO();
		bdao.reinsertBoard(bb);
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<script>");
		out.println("alert('답글 등록완료');");
		out.println("location.href='./BoardList.bo';");
		out.println("</script>");
		out.close();
		
		return forward;
	}

}
