<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/setting.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 상세</title>
<script>
	$(function(){
		$("#update").click(function(){
			document.form.action="${path}/boardUpdate.do?board_no=${board_no}";
			document.form.submit;
		});
		$("#list").click(function(){
			location.href="${path}/boardList.do"
		});	
	});

</script>
</head>
<body>
	<h3 align="center">게시판 상세</h3>
	<form method="post" name="form">
		<table align="center">
			<c:forEach var="dto" items="${list}">
				<tr>
					<th>제목</th>
					<td><textarea rows="1" cols="100" name="title" readonly>${dto.board_title}</textarea></td>
				</tr>
				<tr>
					<th>내용</th>
					<td><textarea rows="10" cols="100" name="contents" readonly>${dto.board_contents}</textarea></td>
				</tr>
			</c:forEach>
			<tr colspan="2">
				<td>
				<input type="hidden" name="board_no" value="${dto.board_no}"> 
				<input type="button" id="update" value="수정">
				<input type="button" id="list" value="목록">
				<td>
			</tr>
		</table>
	</form>
</body>
</html>