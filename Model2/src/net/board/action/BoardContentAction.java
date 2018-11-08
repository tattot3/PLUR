package net.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.board.controller.Action;
import net.board.controller.ActionForward;
import net.board.db.BoardBean;
import net.board.db.BoardDAO;

public class BoardContentAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		String pageNum = request.getParameter("pageNum");
		String num2 = request.getParameter("num");
		int num = Integer.parseInt(num2);


		BoardDAO bdao = new BoardDAO();
		bdao.updateReadcount(num);
		BoardBean bb = bdao.getContent(num);
		
		ActionForward forward = new ActionForward();
		
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("num", num);
		request.setAttribute("bb", bb);
		
		forward.setRedirect(false);
		forward.setPath("./board/content.jsp");
		
		
		return forward;
	}

}
