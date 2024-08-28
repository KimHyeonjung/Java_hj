<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>게시글 목록</title>
</head>
<body>
	<h1>게시글 목록</h1>
	<div class="mt-2">
		<c:forEach items="${list }" var="co">
			<c:choose>
				<c:when test="${co.co_num eq pm.cri.co_num }">
					<a href="<c:url value="/post/list?co_num=${co.co_num }"/>"
						class="btn btn-outline-info">${co.co_name}</a>
				</c:when>
				<c:otherwise>
					<a href="<c:url value="/post/list?co_num=${co.co_num }"/>"
						class="btn btn-outline-info">${co.co_name}</a>
				</c:otherwise>
			</c:choose>
		</c:forEach>
	</div>
	<c:if test="${pm.cri.co_num ne 0 }">
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
						<td><a href="#">${po.po_title}</a></td>
						<td><fmt:formatDate value="${po.po_date}"
								pattern="yyyy-MM-dd." /></td>
						<td><c:url var="url" value="/post/list">
								<c:param name="co_num" value="${pm.cri.co_num }" />
								<c:param name="type" value="id" />
								<c:param name="search" value="${po.po_me_id}" />
							</c:url> <a href="${url}">${po.po_me_id}</a></td>
						<td>0</td>
						<td>${po.po_views}</td>
					</tr>
				</c:forEach>
				<c:if test="${pl.size() == 0 }">
					<tr>
						<th colspan="6" class="text-center">등록된 게시글이 없습니다.</th>
					</tr>
				</c:if>
			</tbody>
		</table>
		<c:if test="${pm.totalCount ne 0 }">
			<ul class="pagination justify-content-center">
				<c:if test="${pm.prev }">
					<c:url var="url" value="/post/list">
						<c:param name="co_num" value="${pm.cri.co_num }" />
						<c:param name="page" value="${pm.startPage - 1 }" />
						<c:param name="type" value="${pm.cri.type }" />
						<c:param name="search" value="${pm.cri.search }" />
					</c:url>
					<li class="page-item"><a class="page-link" href="${url}">이전</a>
					</li>
				</c:if>
				<c:forEach begin="${pm.startPage }" end="${pm.endPage }" var="i">
					<c:url var="url" value="/post/list">
						<c:param name="co_num" value="${pm.cri.co_num }" />
						<c:param name="page" value="${i}" />
						<c:param name="type" value="${pm.cri.type }" />
						<c:param name="search" value="${pm.cri.search }" />
					</c:url>
					<c:choose>
						<c:when test="${pm.cri.page eq i }">
							<c:set var="active" value="active" />
						</c:when>
						<c:otherwise>
							<c:set var="active" value="" />
						</c:otherwise>
					</c:choose>
					<li class="page-item ${active }"><a class="page-link"
						href="${url}">${i }</a></li>
				</c:forEach>
				<c:if test="${pm.next }">
					<c:url var="url" value="/post/list">
						<c:param name="co_num" value="${pm.cri.co_num }" />
						<c:param name="page" value="${pm.endPage + 1 }" />
						<c:param name="type" value="${pm.cri.type }" />
						<c:param name="search" value="${pm.cri.search }" />
					</c:url>
					<li class="page-item"><a class="page-link" href="${url}">다음</a>
					</li>
				</c:if>
			</ul>
		</c:if>
		<form action="<c:url value="/post/list"/>">
			<input type="hidden" name="co_num" value="${pm.cri.co_num }">
			<div class="input-group mb-3">
				<select class="form-control" name="type">
					<option value="all"
						<c:if test="${pm.cri.type eq 'all' }">selected</c:if>>전체</option>
					<option value="title"
						<c:if test="${pm.cri.type eq 'title' }">selected</c:if>>제목</option>
					<option value="id"
						<c:if test="${pm.cri.type eq 'id' }">selected</c:if>>작성자</option>
				</select> <input type="text" class="form-control" placeholder="검색어"
					name="search" value="${pm.cri.search }">
				<div class="input-group-append">
					<button type="submit" class="btn btn-outline-info col-12">검색</button>
				</div>
			</div>
		</form>
		<a href="<c:url value="/post/insert?co_num=${pm.cri.co_num }"/>"
			class="btn btn-outline-success btn-insert">게시글 등록</a>
	</c:if>
	<script type="text/javascript">
		$('.btn-insert').click(function(e){
			if('' != '${user.me_id}'){
				return;
			}
			e.preventDefault();
			if(confirm("로그인이 필요한 서비스입니다.\n로그인 하시겠습니까?")){
				location.href="<c:url value="/login"/>";
			}
		});
</script>
</body>
</html>
