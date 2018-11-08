<%@page import="net.member.db.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>아이디 중복확인</title>
<script type="text/javascript">
function result(){
	// 창을 연 페이지 window 내장객체 속성 window.opner
	// join 페이지 id 상자 = 열린창페이지 id 상자값
	opener.document.fr.id.value = document.wfr.userid.value;
	opener.document.fr.idcheck1.value = "중복확인 완료";
	window.close();
}
</script>
</head>
<body>
<%
request.setCharacterEncoding("utf-8");
String userid = request.getParameter("userid");

MemberDAO mdao = new MemberDAO();
boolean result = mdao.idCheck(userid);

if(result == true){
	%>이미 사용중인 아이디 입니다.<%}
if (result == false){
	%> <%=userid%> 사용가능한 아이디 입니다.
	<input type="button" value="사용하기" onclick="result()">
	<%
}%>
<form action="join_idcheck.jsp" method="post" name="wfr">
<table border="1">
	<tr><td><input type="text" name="userid" value="<%=userid%>"></td></tr>
	<tr><td><input type="submit" name="submit" value="중복확인"></td></tr>
</table>
</form>

</body>
</html>