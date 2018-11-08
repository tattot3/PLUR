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
<h1>WebContent/board/updateForm.jsp</h1>
<h1>게시판 글수정</h1>
<%
String pageNum =request.getParameter("pageNum");
int num = (Integer)request.getAttribute("num");
BoardBean bb = (BoardBean)request.getAttribute("bb");
%>
<form action="./BoardUpdateAction.bo?pageNum=<%=pageNum %>" method="post">
<input type="hidden" name="num" value="<%=num%>">
<table border="1">
<tr><td>글번호</td><td><%=bb.getNum() %></td>
    <td>조회수</td><td><%=bb.getReadcount() %></td></tr>
<tr><td>작성자</td><td><%=bb.getName() %></td>
    <td>작성일</td><td><%=bb.getDate() %></td></tr>
<tr><td>글제목</td><td colspan="3"><input type="text" name="subject" value=""></td></tr>
<tr><td>글내용</td><td colspan="3"><textarea name="content" rows="10" cols="20"></textarea></td></tr>
<tr><td>비밀번호</td><td colspan="3"><input type="password" name="pass" value=""><input type="submit" name="button" value="수정하기">
</td></tr>
</table>
</form>
</body>
</html>