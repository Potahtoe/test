<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/setting.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 수정</title>
<script>
	$(function(){
		$("#update").click(function(){
			alert("수정");
			document.form.action="location.href='${path}/boardUpdateAction.do'"
			document.form.submit();
		})
	})
</script>
</head>
<body>
	<h3 align="center">게시판 수정</h3>
	<form method="post" name="form">
		<table align="center">
			<tr>
				<th>제목</th>
				<td><td><textarea rows="1" cols="100" name="title" maxlength="100" autofocus required>${dto.board_title}</textarea></td>
			</tr>
			<tr>
				<th>내용</th>
				<td><textarea style="resize:vertical;" rows="10" cols="100" name="contents" maxlength="2000" required>${board_contents}</textarea></td>
			</tr>
			<tr>
				<td align="right">
					<input type="button" id="update" value="수정">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>