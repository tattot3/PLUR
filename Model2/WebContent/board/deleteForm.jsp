<%@page import="net.board.db.BoardBean"%>
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
String pageNum = request.getParameter("pageNum");
int num = (Integer)request.getAttribute("num");
BoardBean bb = (BoardBean)request.getAttribute("bb");
%>
<h1>WebContent/board/deleteForm.jsp</h1>
<h1>게시판 글삭제</h1>
<form action="./BoardDeleteAction.bo?pageNum=<%=pageNum%>" method="post">
<input type="hidden" name="num" value="<%=num%>">
<table border="1">
<tr>
	<td>글제목</td><td><%=bb.getSubject() %></td>
</tr>
<tr>
	<td>작성자</td><td><%=bb.getName() %></td>
</tr>
<tr>
	<td>비밀번호</td><td><input type="password" name="pass"><input type="submit" value="삭제하기"></td>
</tr>

</table>
</form>
</body>
</html>