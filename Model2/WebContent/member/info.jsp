<%@page import="net.member.db.MemberBean"%>
<%@page import="net.member.db.MemberDAO"%>
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
String id=(String)session.getAttribute("id");
MemberBean mb = (MemberBean)request.getAttribute("mb");
%>

<h1>WebContent/jspdb2/info.jsp</h1>
<h3>회원정보조회</h3>
아이디 : <%=mb.getId()%><br>
비밀번호 : <%=mb.getPass()%><br>
이름 : <%=mb.getName()%><br>
가입일자 : <%=mb.getReg_date()%><br>
<a href="./Main.me">메인화면으로 이동</a>
</body>
</html>