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
		$("#insert").click(function(){
			location.href="${path}/boardInsert.do";
		});
		$("#search").click(function(){
			alert("검색");
			document.form.action="${path}/boardSearch.do";
			document.form.submit();
		});
	});
</script>
</head>
<body>
	<h3 align="center">게시판 목록</h3>
	<form method="post" name="form">
		<table align="center">
			<tr>
				<td colspan="5" align="right">
					<input type="text" name="searchContent" size="30" placeholder="검색어를 입력하세요.">
					<input type="button" id="search" value="검색">
				</td>
			</tr>
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>작성자</th>
				<th>조회수</th>
				<th>작성일</th>
			</tr>
			<c:forEach var="dto" items="${list}">
				<tr>
					<th>${dto.board_no}</th>
					<th><a href="${path}/boardDetail.do?board_no=${dto.board_no}">${dto.board_title}</a></th>
					<th>${dto.board_writer}</th>
					<th>${dto.read_cnt}</th>
					<th>${dto.in_date}</th>
				</tr>
			</c:forEach>
			<%-- <!-- 페이징 -->
			<tr>
				<td colspan="6" align="center">
					<!-- prev -->
					<c:if test="${paging.startPage>10}">
						<a href="${path}/boardList.do?pageNum=${paging.prev}">[이전]</a>
					</c:if>
					<!-- 페이지 번호 처리 -->
					<c:forEach var="board_no" begin="${paging.startPage}" end="${paging.endPage}">
						<a href="${path}/boardList.do?pageNum=${board_no}">${board_no}</a>
					</c:forEach>
					<!-- next -->
					<c:if test="${paging.endPage<paging.pageCount}">
						<a href="${path}/boardList.do?pageNum=${paging.next}">[다음]</a>
					</c:if>
				</td>
			</tr>
			<!-- 페이징 끝 --> --%>
			<tr>
				<td colspan="5" align="right">
					<input type="button" id="insert" value="글쓰기">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>