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
		
		$("#login").click(function(){
			alert("로그인")
			document.form.action="${path}/loginAction.do";
			document.form.submit;
		});
	});
</script>
</head>
<body>
<!-- 로그인 성공 시 게시판 목록 조회 페이지로 이동 -->
<div>
	<c:if test="${selectCnt==1}">
		<script type="text/javascript">
			window.location="${path}/boardList.do";
		</script>		
	</c:if>
</div>
	<h3 align="center">로그인</h3>
	<form method="post" name="form">
		<c:if test="${sessionScope.sessionID == null}">
			<table align="center">
				<tr>
					<td colspan="4" align="center">
						<c:choose>
							<c:when test="${selectCnt == -1}">
								<h4>비밀번호 오류</h4>
							</c:when>
							<c:when test="${selectCnt == 0}">
								<h4>존재하지 않는 아이디</h4>
							</c:when>
							<c:otherwise>
								<h4>환영합니다.</h4>
							</c:otherwise>
						</c:choose>
					</td>
				</tr>
				<tr>
					<th>아이디</th>
					<td><input type="text" class="input" name="id" size="30" placeholder="아이디를 입력하세요." autofocus required></td>
				</tr>
				<tr>
					<th>비밀번호</th>
					<td><input type="password" class="input" name="password" size="30" placeholder="비밀번호를 입력하세요." required></td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="right">
							<input type="button" id="login" value="로그인">
							<input type="button" id="signIn" value="회원가입">
						</div>
					</td>
				</tr>
			</table>
		</c:if>
	</form>
</body>
</html>