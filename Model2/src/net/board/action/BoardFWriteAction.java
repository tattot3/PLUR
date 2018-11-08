package net.board.action;

import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import net.board.controller.Action;
import net.board.controller.ActionForward;
import net.board.db.BoardBean;
import net.board.db.BoardDAO;

public class BoardFWriteAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("BoardFWriteAction execute()");
		ActionForward forward = new ActionForward();
		request.setCharacterEncoding("utf-8");
		
		ServletContext context = request.getServletContext();
		String filePath = context.getRealPath("/upload");
		System.out.println(filePath);
		int maxSize = 5*1024*1024; 
		
		MultipartRequest multi = 
			new MultipartRequest(request,filePath,maxSize,"utf-8",new DefaultFileRenamePolicy());

		BoardBean bb = new BoardBean();
		bb.setContent(multi.getParameter("content"));
		bb.setName(multi.getParameter("name"));
		bb.setPass(multi.getParameter("pass"));
		bb.setSubject(multi.getParameter("subject"));
		bb.setFile(multi.getFilesystemName("file"));

		//파일은 가상 upload폴더에 생기기때문에
		//D:\workspace_jsp2\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\StudyJSP
		//로 이동해서 사진이 있는지 확인하면 됨!

		BoardDAO bdao = new BoardDAO();
		bdao.insertBoard(bb);
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<script>");
		out.println("alert('등록완료');");
		out.println("location.href='./BoardList.bo';");
		out.println("</script>");
		out.close();
		
		return forward;
	}

}
