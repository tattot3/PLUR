<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<%
String id = request.getParameter("id");
if(id == null){
%>
	<script type="text/javascript">4
	alert("로그인해주세요.")
	location.href="./MemberLogin.me"
	</script>
<%}%>

<%=id %>님이 로그인 하셨습니다.
<input type="button" value="로그아웃"
 onclick="location.href='./MemberLogout.me'"><br>
<a href="./MemberInfo.me">회원정보보기</a><br> <!-- //나의 정보보기 select -->
<a href="./MemberUpdate.me">회원정보수정</a><br> <!-- //나의 정보수정 update -->
<a href="./MemberDelete.me">회원정보삭제</a><br> <!-- //나의 정보삭제 delete -->

<%
if(id!=null){
	//세션있으면 //관리자 admin이면 회원목록이 보이게 설정
	if(id.equals("admin")){
		%><a href="./MemberList.me">회원목록</a><%
	}
}
%>

</body>
</html>