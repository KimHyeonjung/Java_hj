<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:include page="/WEB-INF/views/common/head.jsp"/>
<title>${comu.co_name}게시글</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp" />

	<div class="container">
		<h2 class="title">${comu.co_name}게시글</h2>

		<table class="table table-striped mt-4">
			<thead>
				<tr>
					<th>번호</th>
					<th>제목</th>
					<th>날짜</th>
					<th>작성자</th>
					<th>추천/비추천</th>
					<th>조회수</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${list}" var="po">
					<tr>
						<td>${po.po_num}</td>
						<td>${po.po_title}</td>
						<td><fmt:formatDate value="${po.po_date}"
								pattern="yyyy-MM-dd HH:mm:ss" /></td>
						<td>${po.po_me_id}</td>
						<td>0</td>
						<td>${po.po_views}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<ul class="pagination justify-content-center">

		<c:if test="${!pm.prev}">
			<c:set var="prev" value="disabled" />
		</c:if>
		<c:url var="url" value="/post/list">
			<c:param name="co_num" value="${pm.cri.co_num }" />
			<c:param name="page" value="${pm.startPage - 1 }" />
		</c:url>
		<li class="page-item ${prev }">
			<a class="page-link" href="${url}">이전</a>
		</li>
		<c:forEach begin="${pm.startPage }" end="${pm.endPage }" var="i">
			<c:if test="${pm.cri.page == i }">
				<c:set var="active" value="active" />
			</c:if>
			<c:if test="${pm.cri.page != i }">
				<c:set var="active" value="" />
			</c:if>
			<c:url var="url" value="/post/list">
				<c:param name="co_num" value="${pm.cri.co_num }" />
				<c:param name="page" value="${i }" />
			</c:url>
			<li class="page-item ${active}">
				<a class="page-link" href="${url }">${i }</a>
			</li>
		</c:forEach>
		<c:if test="${!pm.next }">
			<c:set var="next" value="disabled" />
		</c:if>
		<c:url var="url" value="/post/list">
			<c:param name="co_num" value="${pm.cri.co_num }" />
			<c:param name="page" value="${pm.endPage + 1 }" />
		</c:url>
		<li class="page-item ${next }">
			<a class="page-link" href="${url }">다음</a>
		</li>
	</ul>

	<div class="container" style="min-height: calc(100vh - 240px)"></div>
	<jsp:include page="/WEB-INF/views/common/footer.jsp" />
</body>
</html>