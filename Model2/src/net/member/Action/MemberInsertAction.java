package net.member.Action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.member.controller.Action;
import net.member.controller.ActionForward;
import net.member.db.MemberBean;
import net.member.db.MemberDAO;

public class MemberInsertAction implements Action {
	//재정의 -> alt + shift + s + v
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("MemberInsertAction execute()");
		//request 한글처리
		request.setCharacterEncoding("utf-8");
		//request 저장된 파라미터 가져오기
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String pass = request.getParameter("pass");
		
		//MemberBean 객체 생성 mb
		MemberBean mb = new MemberBean();
		//폼 파라미터 값 => 자바빈 멤버변수 저장
		mb.setId(id);
		mb.setName(name);
		mb.setPass(pass);
		//MemberDAO mdao객체생성
		MemberDAO mdao = new MemberDAO();
		//insertMember 함수호출
		mdao.insertMember(mb);
		//이동 MemberLogin.me
		//이동방식, 이동경로 정해서 저장해서 이동하기
		ActionForward foward = new ActionForward();
		foward.setRedirect(true);
		//"./Model2/MemberLogin.me"
		foward.setPath("./MemberLogin.me");
		return foward;
	}
	
}
