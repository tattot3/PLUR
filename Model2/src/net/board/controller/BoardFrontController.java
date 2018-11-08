package net.board.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.board.action.BoardContentAction;
import net.board.action.BoardDelete;
import net.board.action.BoardDeleteAction;
import net.board.action.BoardFWriteAction;
import net.board.action.BoardListAction;
import net.board.action.BoardUpdate;
import net.board.action.BoardUpdateAction;
import net.board.action.BoardWriteAction;
import net.board.action.BaordRewriteAction;


public class BoardFrontController extends HttpServlet{

	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		System.out.println("BoardFrontController doProcess()메서드");
		String requestURI=request.getRequestURI();
		String contextPath=request.getContextPath();
		String command=requestURI.substring(contextPath.length());
		System.out.println("뽑아온 가상주소 : "+command);
		ActionForward forward = null;
		Action action=null;
		if(command.equals("/BoardWrite.bo")){
			// ./board/writeForm.jsp이동
			// ActionForward 이동 정보 저장
			forward=new ActionForward();
			forward.setRedirect(false);
			forward.setPath("./board/fwriteForm.jsp");
		} else if(command.equals("/BoardWriteAction.bo")){
			//BoardWriteAction 자바 파일 만들기 <= Action틀 적용
			//execute() 강제적으로 메서드 오버라이딩, 틀 강제적으로 적용
			//부모 = BoardWriteAction객체 생성
			//메서드 호출한 곳에서 예외처리
			//forward 이동정보에 저장 = execute()메서드 호출
			 action = new BoardFWriteAction();
			try{
				forward = action.execute(request, response);
			}catch(Exception e/*매개변수 e라서 형을 생략할 수 없다*/){ e.printStackTrace(); }
		} else if(command.equals("/BoardList.bo")){
			//BoardListAction 파일 Action인터페이스 상속
			//execute() 강제적으로 메서드 오버라이딩, 틀 강제적으로 허용
			//execute() 호출
			action = new BoardListAction();
			try{
				forward = action.execute(request, response);
			}catch(Exception e){e.printStackTrace();}
			
		} else if(command.equals("/BoardContent.bo")){
			action = new BoardContentAction();
			try{
				forward = action.execute(request, response);
			}catch(Exception e){e.printStackTrace();}
		} else if(command.equals("/BoardUpdate.bo")){
			action = new BoardUpdate();
			try{
				forward=action.execute(request, response);
			}catch(Exception e){e.printStackTrace();}
		} else if(command.equals("/BoardUpdateAction.bo")){
			action = new BoardUpdateAction();
			try{
				forward=action.execute(request, response);
			}catch(Exception e){e.printStackTrace();}
		} else if(command.equals("/BoardDelete.bo")){
			action = new BoardDelete();
			try{
				forward = action.execute(request, response);
			}catch(Exception e){e.printStackTrace();}
		} else if(command.equals("/BoardDeleteAction.bo")){
			action = new BoardDeleteAction();
			try{
				forward = action.execute(request, response);
			}catch(Exception e){e.printStackTrace();}
		} else if(command.equals("/BoardreWrite.bo")){
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("./board/reWriteForm.jsp");
		} else if(command.equals("/RewriteAction.bo")){
			action = new BaordRewriteAction();
			try{
				forward = action.execute(request, response);
			}catch(Exception e){e.printStackTrace();}
		}
	
	//이동
		if(forward != null){
			if(forward.isRedirect()){
				response.sendRedirect(forward.getPath());
				
			} else {
				RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
				
			}
		}
	}
	
	
		
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doget");
		doProcess(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("dopost");
		doProcess(request, response);
	}
	
	
	

}
