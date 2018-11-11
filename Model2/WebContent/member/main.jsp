<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="css/bootstrap-grid.css" rel="stylesheet">
<!-- <link href="css/bootstrap.css" rel="stylesheet"> -->
<link href="css/bootstrap.min.css" rel="stylesheet">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
</head>
<body>
<%String id=(String)session.getAttribute("id"); %>

<div class="bg-primary" style="height:100vh;">
	<nav class="nav bg-warning" style="height:50px;">
		<ul class="nav">
			<%if(id==null){
				%>
				<li class="text-light ml-3 mr-3">login</li>
				<li class="text-light ml-3 mr-3">join</li>
			<%}else{%>
				<li>
					<a href="img/member/profile_image.JPG"><img src=""></a>
				</li>
				<li class="text-light ml-3 mr-3"><%=id%></li>
			<%}%>
			
		</ul>
	</nav>

</div>
<h1>WebContent/jspdb2/main.jsp</h1>

    



</body>
</html>