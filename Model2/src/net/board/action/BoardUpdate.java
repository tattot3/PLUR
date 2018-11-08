package net.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.board.controller.Action;
import net.board.controller.ActionForward;
import net.board.db.BoardBean;
import net.board.db.BoardDAO;

public class BoardUpdate implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("BoardUpdate execute()");
		
		String num2 = request.getParameter("num");
		int num = Integer.parseInt(num2);
		
		ActionForward forward = new ActionForward();
		
		BoardDAO bdao = new BoardDAO();
		BoardBean bb = bdao.getContent(num);
		
		request.setAttribute("num", num);
		request.setAttribute("bb", bb);
		
		forward.setRedirect(false);
		forward.setPath("./board/updateForm.jsp");
		
		return forward;
	}

}
