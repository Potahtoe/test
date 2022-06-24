<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/setting.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>LOGIN</title>
<script>
	$(function(){
		$("#signIn").click(function(){
			alert("회원가입")
			location.href="${path}/signIn.do";
		});
		
		/* $("#login").click(function(){
			alert("로그인")
			document.form.action="${path}/loginAction.do";
			document.form.submit();
		}) */
	})
</script>
</head>
<body>
	<h3 align="center">로그인</h3>
	<form method="post" name="form" action="${path}/loginAction.do">
		<table align="center">
			<tr align="center">
				<th>
					<c:choose>
						<c:when test="${selectCnt==-1}">
							비밀번호 불일치
						</c:when>
						<c:when test="${selectCnt==0}">
							존재하지 않는 아이디
						</c:when>
						<c:when test="${selectCnt==1}">
							회원가입 성공, 로그인하세요.
						</c:when>
						<c:otherwise>
							환영합니다.
						</c:otherwise>
					</c:choose>
				</th>
			</tr>
		
			<tr>
				<th>아이디</th>
				<td><input type="text" class="input" name="mem_id" size="30" placeholder="아이디를 입력하세요." autofocus required></td>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td><input type="password" class="input" name="mem_pwd" size="30" placeholder="비밀번호를 입력하세요." required></td>
			</tr>
			<tr>
				<td colspan="2">
					<div align="right">
						<input type="submit" name="login" value="로그인">
						<input type="button" id="signIn" value="회원가입">
					</div>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>