<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>community</title>
</head>
<body>
<h1>게시글 목록</h1>
	<div class="mt-2">
		<c:forEach items="${list }" var="co">
			<c:choose>
				<c:when test="${co.co_num eq pm.cri.co_num }">
					<a href="<c:url value="/post/list?co_num=${co.co_num }"/>" class="btn btn-outline-info">${co.co_name}</a>
				</c:when>
				<c:otherwise>
					<a href="<c:url value="/post/list?co_num=${co.co_num }"/>" class="btn btn-outline-info">${co.co_name}</a>
				</c:otherwise>
			</c:choose>
		</c:forEach>
	</div>
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
				<c:forEach items="${pl}" var="po">
					<tr>
						<td>${po.po_num}</td>
						<td>
							<a href="#">${po.po_title}</a>
						</td>
						<td><fmt:formatDate value="${po.po_date}" pattern="yyyy-MM-dd." />
						</td>
						<td>							
							<a  href="#">${po.po_me_id}</a>
						</td>
						<td>0</td>
						<td>${po.po_views}</td>
					</tr>
				</c:forEach>
				<c:if test="${pl.size() == 0 }">
					<tr>
						<th colspan="6" class="text-center"> 등록된 게시글이 없습니다.</th>
					</tr>
				</c:if>
			</tbody>
		</table>
</body>
</html>
