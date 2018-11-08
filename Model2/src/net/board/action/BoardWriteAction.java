package net.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.board.controller.Action;
import net.board.controller.ActionForward;
import net.board.db.BoardBean;
import net.board.db.BoardDAO;

public class BoardWriteAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("utf-8");
		
		String name=request.getParameter("name");
		String pass=request.getParameter("pass");
		String subject=request.getParameter("subject");
		String content=request.getParameter("content");
		
		BoardBean bb = new BoardBean();
		bb.setName(name);
		bb.setPass(pass);
		bb.setSubject(subject);
		bb.setContent(content);
		
		BoardDAO bdao = new BoardDAO();
		bdao.insertBoard(bb);
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(true);
		forward.setPath("./BoardList.bo");
		
		
		return forward;
	}
	

}
