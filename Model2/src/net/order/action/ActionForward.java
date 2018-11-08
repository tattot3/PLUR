package net.order.action;

public class ActionForward {
	//이동방식 저장  // 약속  true  response ,false  forward
	private boolean isRedirect;
	//이동경로 저장
	private String path;
	//set get 메서드
	public boolean isRedirect() {
		return isRedirect;
	}
	public void setRedirect(boolean isRedirect) {
		this.isRedirect = isRedirect;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	
	
	
}
