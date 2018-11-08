package net.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.member.Action.MemberDeleteAction;
import net.member.Action.MemberInfoAction;
import net.member.Action.MemberInsertAction;
import net.member.Action.MemberList;
import net.member.Action.MemberLoginAction;
import net.member.Action.MemberLogoutAction;
import net.member.Action.MemberUpdate;
import net.member.Action.MemberUpdateAction;

public class MemberFrontController extends HttpServlet {
	//부모 서블릿의 기능을 모두 상속받아서 이 자바파일을 서블릿으로 지정해준다.
	// http://localhost:8080/Model2/MemberInsert.me
	// alt + shift + s + v => override/implement 자동생성 창 단축키
	
	// 서버 시작
	// 브라우저 가상주소 http://localhost:8080/Model2/MemberInsert.me
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//super.doGet(request, response); // 부모의 doGet()메서드를 호출하는 코드 필요없으니까 지울것
			System.out.println("MemberFrontController doProcess()메서드");
		// 가상주소 뽑아오기 
		// URI 주소 뽑아오기
		String requestURI = request.getRequestURI();
			System.out.println(requestURI);
		//프로젝트 ContextPath경로
		String ContextPath = request.getContextPath();
			System.out.println(ContextPath);
			System.out.println("context 길이 : "+ContextPath.length());
		// requestURI 에서 Context 길이 부터 끈까지 문자열 뽑아오기 ex)MerberInsert.me / MemberInsertAction.me
		String command=requestURI.substring(ContextPath.length());
			System.out.println(command);
		//가상주소 비교하기 뽑아온 가상의 주소값과  "/MemberInsert.me" 비교
		//두개의 문자가 일치하면 ./member/insertForm.jsp 이동 => '.'은 현재 가상위치를 말함
		ActionForward forward=null;
		// 부모인 action인터페이스 선언
		Action action = null;
		if(command.equals("/MemberInsert.me")){
			//response.sendRedirect("./member/insertForm.jsp");
			//forward 이동방식 으로 가상의주소를 보이게 한다.	A -> B 이동
			//									A에 있는 request정보를 가지고 B로 이동
			//									주소줄에는 A유지 => 실행화면 B가 보이게함
			
			//RequestDispatcher dispatcher = request.getRequestDispatcher("./member/insertForm.jsp");
			//dispatcher.forward(request, response);
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("./member/insertForm.jsp");
		} else if(command.equals("/MemberInsertAction.me")) {
			//회원가입 처리 작업 => java파일 메서드정의 
			// 인터페이스 틀 : java파일을 만들기 위한 틀
			// 추상메서드 정의 : 상속받은 자바파일 메서드 오버라이딩(재정의) 사용
			// java파일(MemberInsertAction) 만들고 인처페이스 상속
			// java파일 부모를 통해서 객체생성, execute()메서드 호출
			action = new MemberInsertAction(); 
			try{
				forward = action.execute(request, response);
			} catch(Exception e){ e.printStackTrace(); }
		} else if(command.equals("/MemberLogin.me")){
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("./member/loginForm.jsp");
		} else if(command.equals("/MemberLoginAction.me")){
			action = new MemberLoginAction();
			try{
				forward = action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
		} else if(command.equals("/Main.me")){
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("./member/main.jsp");
		} else if(command.equals("/MemberLogout.me")){
			// Action 인터페이스 상속받은 MemberLogoutAction 파일 만들고
			// 메서드 호출
			action = new MemberLogoutAction();
			try{
				forward = action.execute(request, response);
			}catch(Exception e){ e.printStackTrace(); }
		} else if(command.equals("/MemberInfo.me")){
			//Action인터페이스 상복받은 MemberInfoAction 파일 만들고
			//메서드 호출
			action = new MemberInfoAction();
			try{
				forward = action.execute(request, response);
			}catch(Exception e){ e.printStackTrace(); }
		} else if(command.equals("/MemberUpdate.me")){
			//MemberUpdate 파일
			action = new MemberUpdate();
			try{
				forward = action.execute(request, response);
			}catch(Exception e){ e.printStackTrace(); }
		} else if(command.equals("/MemberUpdateAction.me")){
			//MemberUpdateAction파일
			action = new MemberUpdateAction();
			try{
				forward = action.execute(request, response);
			}catch(Exception e){ e.printStackTrace(); }
		} else if(command.equals("/MemberDelete.me")){
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("./member/deleteForm.jsp");
		} else if(command.equals("/MemberDeleteAction.me")){
			action = new MemberDeleteAction();
			try{
				forward = action.execute(request, response);
			}catch(Exception e){ e.printStackTrace(); }
		} else if(command.equals("/MemberList.me")){
			action = new MemberList();
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
		//super.doGet(request, response); // 부모의 doGet()메서드를 호출하는 코드 필요없으니까 지울것
		System.out.println("MemberFrontController doGet()메서드");
		doProcess(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("MemberFrontController doPost()메서드");
		doProcess(request, response);
	}

}
