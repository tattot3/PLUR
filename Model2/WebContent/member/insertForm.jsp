<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<!-- form 제어 함수 -->
<script type="text/javascript">
/* 아이디 중복확인 */
function idcheck(){
	var fid=document.fr.id.value;
	if(fid==""){
		alert("아이디를 입력하세요.");
		a.id.focus();
		return false;
	}
	window.open("member/join_idcheck.jsp?userid="+fid, "아이디 중복확인", 
			"width=300,height=200,top=350,left=700,scrollbars=yes,resizable=yes,menubar=yes");
}

/* 필수선택사항 */
function check(){
	a=document.fr;
	if(a.idcheck1.value==""){
		alert("아이디 중복확인 해주세요.");
		a.id.focus();
		return false;
	}
	if(a.pass.value==""){
		alert("비밀번호를 입력해주세요");
		a.pass.focus();
		return false;
	}
	/* if(a.pass.value!==a.pass2.value){
		alert("비밀번호가 일치하지 않습니다.");
		a.pass.focus();
		return false;
	} */
	if(a.name.value==""){
		alert("성함을 입력해주세요.");
		a.name.focus();
		return false;
	}
}
</script>
</head>
<body>
<h1>WebContent/member/insertForm.jsp</h1>
<form action="./MemberInsertAction.me" method="post" name="fr" onsubmit="return check()">
아이디 : <input type="text"  name="id" id="id">
<input type="button" value="중복확인" class="dup" onclick="idcheck()">
<input type="text" name="idcheck1" readonly style="width:130px;background-color:white;border:none;color:grey;"><br>
비밀번호 : <input type="password"  name="pass" id="pass"><br>
이름 : <input type="text"  name="name" id="name"><br>
<input type="submit" value="회원가입">
</form>
</body>
</html>











