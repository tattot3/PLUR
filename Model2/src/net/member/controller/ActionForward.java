package net.member.controller;

public class ActionForward { //캡슐화 하기
	//이동방식 true response.sendRedirect -> 가상경로 /false forward -> .jsp
	private boolean redirect;
	//이동경로
	private String path;
	// alt + shift + s + r
	public boolean isRedirect() {
		return redirect;
	}
	public void setRedirect(boolean redirect) {
		this.redirect = redirect;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	
}
