<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>WebContent/jstl/jstl_core.jsp</h1>
변수 선언 : <c:set var="test" value="Hello"/>
변수 출력 : <c:out value="${test }"></c:out><br>
변수 삭제 <c:remove var="test"/>
변수 출력 : <c:out value="${test }"></c:out><br>
<c:catch var="err">
<%=10/0 %>
</c:catch>
오류메시지 : <c:out value="${err }"></c:out><br>
<c:if test="${5<10 }">
<h3>5는 10보다 작다</h3>
</c:if>
<c:choose>
	<c:when test="${5+10==50 }">
	  <h3>5+10은 50이다</h3>
	</c:when>
	<c:otherwise>
	  <h3>5+10은 50아니다</h3>
	</c:otherwise>
</c:choose>
<c:forEach var="i" begin="1" end="10" step="2">
	${i }<br>
</c:forEach>
<c:forTokens var="data" items="홍길동,김길동,이길동" delims=",">
	${data }<br>
</c:forTokens>

</body>
</html>











