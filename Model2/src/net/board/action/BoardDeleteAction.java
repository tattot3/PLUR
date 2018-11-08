package net.board.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.board.controller.Action;
import net.board.controller.ActionForward;
import net.board.db.BoardBean;
import net.board.db.BoardDAO;

public class BoardDeleteAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("BoardDeleteAction execute()");
		ActionForward forward = new ActionForward();
		
		String pageNum = request.getParameter("pageNum");
		String num2 = request.getParameter("num");
		String pass = request.getParameter("pass");
		int num = Integer.parseInt(num2);
		
		BoardBean bb = new BoardBean();
		bb.setNum(num);
		bb.setPass(pass);
		
		BoardDAO bdao = new BoardDAO();
		int result = bdao.passCheck(pass, num);
		
		if(result==1){
			bdao.deleteContent(pass, num);
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('삭제완료');");
			out.println("location.href='./BoardList.bo?pageNum="+pageNum+"';");
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
