
<%@page import="net.board.db.BoardBean"%>
<%@page import="net.board.db.BoardDAO"%>
<%@page import="com.sun.org.apache.xpath.internal.operations.NotEquals"%>
<%@page import="java.util.List"%>
<%@page import="com.mysql.jdbc.PreparedStatement.ParseInfo"%>
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
//버전이 낮을때 intValue()넣어줘야 인식가능
int count = ((Integer)request.getAttribute("count")).intValue();
List<BoardBean> boardList = (List)request.getAttribute("boardList");
int pageCount = (Integer)request.getAttribute("pageCount");
int pageBlock = (Integer)request.getAttribute("pageBlock");
int startPage = (Integer)request.getAttribute("startPage");
int endPage = (Integer)request.getAttribute("endPage");
String pageNum = (String)request.getAttribute("pageNum"); 
%>

<h1>WebContent/board/list.jsp</h1>
<h2>게시판 글목록[전체글수 : <%=count%> ]</h2>
<h4><a href="./BoardWrite.bo">게시판 글쓰기</a></h4>
<table border="1">
<tr>
<th>번호</th>
<th>제목</th>
<th>작성자</th>
<th>작성일</th>
<th>조회수</th>
</tr>
<%


for(int i=0;i<boardList.size();i++){
	   BoardBean bb=(BoardBean)boardList.get(i);
 %>
 <tr>
 	<td><%=bb.getNum() %></td>
    <td>
    <%
    //답글이면
    int wid = 0;
    if(bb.getRe_lev() != 0){
    	wid = bb.getRe_lev()*10; //이미지 너비 레벨1 10px, 레벨2 20px 계산식
    	%>
    	<img src="./board/level.gif" width="<%=wid%>">
    	<img src="./board/re.gif">
    	<%
    }
    
    %>
    <a href="BoardContent.bo?num=<%=bb.getNum()%>&pageNum=<%=pageNum%>"><%=bb.getSubject() %></a></td>
    <td><%=bb.getName() %></td>
    <td><%=bb.getDate() %></td>
    <td><%=bb.getReadcount() %></td>
</tr> 
    
 <%   	
	 } 
    %>
</table>
<%
//이전
if(startPage>pageBlock){
	%><a href="BoarList.bo?pageNum=<%=startPage-pageBlock%>">[이전]</a><%
}

// 1~10
int i;
for(i=startPage; i<=endPage; i++){
	%><a href="BoardList.bo?pageNum=<%=i%>">[<%=i%>]</a><%
}

//다음
if(endPage<pageCount){
	%><a href="BoardList.bo?pageNum=<%=startPage+pageBlock%>">[다음]</a><%
}

%>


</body>
</html>