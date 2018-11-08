package net.member.Action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.member.controller.Action;
import net.member.controller.ActionForward;
import net.member.db.MemberDAO;

public class MemberLoginAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("MemberLoginAction execute()");
		ActionForward forward = new ActionForward();
		//request 한글 처리
		request.setCharacterEncoding("utf-8");
		//request 저장된 파라미터 가져오기
		String id = request.getParameter("id");
		String pass = request.getParameter("pass");
		//memberDAO객체 생성
		MemberDAO mdao = new MemberDAO();
		//userCheck(함수호출)
		int result = mdao.userCheck(id, pass);
		//userCheck 리턴값 비교
		if(result==1){
			System.out.println("login complete");
			//세션값 생성
			HttpSession session = request.getSession();
			session.setAttribute("id", id);
			//Main.me로 이동
			forward.setRedirect(true);
			forward.setPath("./Main.me");
		}else if(result==0) {
			System.out.println("login failed");
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('비밀번호틀림');");
			out.println("history.back();");
			out.println("</script>");
			out.close();
			return null;
		}else if(result==-1){
			System.out.println("login failed");
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('아이디 틀림');");
			out.println("history.back();");
			out.println("</script>");
			out.close();
			return null;
		}
		
		
		return forward;
	}

	
}
