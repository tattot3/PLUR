<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>WebContent/board/writeForm.jsp</h1>
<h1>게시판 글등록</h1>
<form action="./BoardWriteAction.bo" method="post" enctype="multipart/form-data">
<!-- enctype="multipart/form-data"  첨부파일이 없을때는 꼭 빼줘야함. 첨부파일이 없을때 넣으면 form태그가 작동하지 x -->
<table border="1">
<tr><td>글쓴이</td><td><input type="text" name="name"></td></tr>
<tr><td>비밀번호</td><td><input type="password" name="pass"></td></tr>
<tr><td>제목</td><td><input type="text" name="subject"></td></tr>
<tr><td>첨부파일</td><td><input type="file" name="file"></td></tr>
<tr><td>내용</td><td><textarea name="content" rows="10" cols="20"></textarea></td></tr>
<tr><td colspan="2"><input type="submit" value="글쓰기">
					<input type="reset" value="다시쓰기"></td></tr>
</table>
</form>
</body>
</html>