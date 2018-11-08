package net.board.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.board.controller.Action;
import net.board.controller.ActionForward;
import net.board.db.BoardBean;
import net.board.db.BoardDAO;

public class BoardUpdateAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("BoardUpdateAction execute()");
		ActionForward forward = new ActionForward();
		
		request.setCharacterEncoding("utf-8");
		String content = request.getParameter("content");
		String subject = request.getParameter("subject");
		String pageNum = request.getParameter("pageNum");
		String num2 = request.getParameter("num");
		int num = Integer.parseInt(num2);
		String pass = request.getParameter("pass");
		
		BoardBean bb = new BoardBean();
		bb.setContent(content);
		bb.setSubject(subject);
		bb.setPass(pass);
		bb.setNum(num);
		
		BoardDAO bdao = new BoardDAO();
		int result = bdao.passCheck(pass, num);
		
		if(result==1){
			bdao.updateContent(bb);
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('수정완료');");
			out.println("location.href='./BoardContent.bo?num="+num+"&pageNum="+pageNum+"';");
			out.println("</script>");
			out.close();
			return forward;
			}else if(result==0){
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('비밀번호가 일치하지 않습니다.');");
				out.println("history.back();");
				out.println("</script>");
				out.close();
				return null;
		}
		return forward;
	}

	
	
}
