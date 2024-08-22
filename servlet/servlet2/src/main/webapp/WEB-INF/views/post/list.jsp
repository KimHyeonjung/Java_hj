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
<div class="container" style="min-height: calc(100vh - 240px)">
	<div class="container">
		<h2 class="title text-center">${comu.co_name} 게시글</h2>
		<form  action="">
		<div class="input-group mt-3 mb-3 bar-search">
			<div class="input-group-prepend">
				<select class="form-control menu-search" name="type">
					<option value="all" <c:if test="${pm.cri.type == 'all' }">selected</c:if>>전체</option>
					<option value="title" <c:if test="${pm.cri.type == 'title' }">selected</c:if>>제목</option>
					<option value="id" <c:if test="${pm.cri.type == 'id' }">selected</c:if>>작성자</option>
				</select>	
			</div>	
			<input type="text" class="form-control" name="search" placeholder="Search" value="${pm.cri.search}">
			<input type="hidden" name="co_num" value="${pm.cri.co_num}">
			<div class="input-group-append">
			    <button class="btn btn-outline-success input-group-text" type="submit">
			    	<span >검색</span>
			    </button>
			</div>
		</div>
		</form>

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
						<td>
							<a href="<c:url value="/post/detail?po_num=${po.po_num}"/>">${po.po_title}</a>
						</td>
						<td><fmt:formatDate value="${po.po_date}"
								pattern="yyyy-MM-dd HH:mm:ss" />
						</td>
						<td>
							<c:url var="url" value="/post/list">
								<c:param name="type" value="id"/>
								<c:param name="search" value="${po.po_me_id }"/>
								<c:param name="co_num" value="${pm.cri.co_num }"/>
							</c:url>
							<a  href="${url}">${po.po_me_id}</a>
						</td>
						<td>0</td>
						<td>${po.po_views}</td>
					</tr>
				</c:forEach>
				<c:if test="${list.size() == 0 }">
					<tr>
						<th colspan="6" class="text-center"> 등록된 게시글이 없습니다.</th>
					</tr>
				</c:if>
			</tbody>
		</table>
	</div>
	<c:if test="${pm.endPage ne 0 }">
		<ul class="pagination justify-content-center">
			<!-- 이전 -->
			<c:if test="${!pm.prev}">
				<c:set var="prev" value="disabled" />
			</c:if>
			<c:url var="url" value="/post/list">
				<c:param name="co_num" value="${pm.cri.co_num }" />
				<c:param name="page" value="${pm.startPage - 1 }" />
				<c:param name="type" value="${pm.cri.type }"/>
				<c:param name="search" value="${pm.cri.search }"/>
			</c:url>
			<li class="page-item ${prev }">
				<a class="page-link" href="${url}">이전</a>
			</li>
			<!-- 페이지 -->
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
					<c:param name="type" value="${pm.cri.type }"/>
				<c:param name="search" value="${pm.cri.search }"/>
				</c:url>
				<li class="page-item ${active}">
					<a class="page-link" href="${url }">${i }</a>
				</li>
			</c:forEach>
			<!-- 다음 -->
			<c:if test="${!pm.next }">
				<c:set var="next" value="disabled" />
			</c:if>
			<c:url var="url" value="/post/list">
				<c:param name="co_num" value="${pm.cri.co_num }" />
				<c:param name="page" value="${pm.endPage + 1 }" />
				<c:param name="type" value="${pm.cri.type }"/>
				<c:param name="search" value="${pm.cri.search }"/>
			</c:url>
			<li class="page-item ${next }">
				<a class="page-link" href="${url }">다음</a>
			</li>
		</ul>
	</c:if>
</div>
	<jsp:include page="/WEB-INF/views/common/footer.jsp" />
</body>
</html>