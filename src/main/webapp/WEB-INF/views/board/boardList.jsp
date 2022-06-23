<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/setting.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판_목록</title>
<script>
	$(function(){
		$("#delete").click(function(){
			alert("삭제");
			document.form.action="${path}/boardDeleteAction.do";
			document.form.submit();
		});
		$("#insert").click(function(){
			location.href="${path}/boardInsert.do";
		});
	});
</script>
</head>
<body>
	<h3 align="center">게시판 목록</h3>
	<form method="post" name="form">
		<table align="center">
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>내용</th>
				<th>작성자</th>
				<th>조회수</th>
				<th>작성일</th>
				<th>삭제</th>
			</tr>
			<c:forEach var="dto" items="${list}">
				<tr>
					<th>${dto.board_no}</th>
					<th>${dto.board_title}</th>
					<th>${dto.board_contents}</th>
					<th>${dto.board_writer}</th>
					<th>${dto.read_cnt}</th>
					<th>${dto.in_date}</th>
					<th>
						<input type="button" id="delete" value="삭제">
					</th>
				</tr>
			</c:forEach>
			<tr>
				<td colspan="7" align="right">
					<input type="button" id="insert" value="등록">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>