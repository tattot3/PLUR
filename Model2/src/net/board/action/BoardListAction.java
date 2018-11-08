package net.board.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.board.controller.Action;
import net.board.controller.ActionForward;
import net.board.db.BoardBean;
import net.board.db.BoardDAO;

public class BoardListAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		BoardDAO bdao = new BoardDAO();
		int count = bdao.getBoardCount();
		int pageSize = 10;
		String pageNum = request.getParameter("pageNum");
		if(pageNum == null){pageNum="1";}
		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage-1)*pageSize+1;
		int endRow = pageSize*currentPage;
		List<BoardBean> boardList=null;
		
		if(count!=0){
			//list<BoardBean> boardList = getBoardList(시작행, 가져올글개수);
		boardList = bdao.getBoardList(startRow, pageSize);
		}
		
		int pageCount = count/pageSize + ((count%pageSize!=0) ? 1:0);
		int pageBlock = 5;
		int startPage = ((currentPage-1)/pageBlock)*pageBlock+1;
		int endPage = startPage+pageBlock-1;
		if(endPage>pageCount){
			endPage = pageCount;
		}
		
		ActionForward forward = new ActionForward();
		
		//저장 count,pageNum,boardList,pageCount,pageBlock,startPage,
		//	endPage
		request.setAttribute("count", count);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("boardList", boardList);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		
		forward.setRedirect(false);
		forward.setPath("./board/list.jsp");
		
		return forward;
	}

}
