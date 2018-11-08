package net.member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Action {
	//추상메서드 정의 
	//public 리턴할형 항수이름(reqeust, response);
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
