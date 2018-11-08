<%@page import="net.member.db.MemberBean"%>
<%@page import="net.member.db.MemberDAO"%>
<%@page import="java.util.List"%>
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
List<MemberBean> memberList= (List)request.getAttribute("memberList");
%>
<h1>WebContent/member/list.jsp</h1>
<table border="1">
<tr><td>아이디</td><td>비밀번호</td>
    <td>이름</td><td>가입날짜</td></tr>
 <%
   for(int i=0;i<memberList.size();i++){
	   MemberBean mb=memberList.get(i);
 %>
 <tr><td><%=mb.getId() %></td>
     <td><%=mb.getPass() %></td>
    <td><%=mb.getName() %></td>
    <td><%=mb.getReg_date() %></td></tr> 
 <%}%>
</table>
</body>
</html>







