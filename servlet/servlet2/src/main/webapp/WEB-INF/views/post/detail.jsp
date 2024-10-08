<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:include page="/WEB-INF/views/common/head.jsp"/>
<title>게시글</title>
</head>
<body>
<jsp:include page="/WEB-INF/views/common/header.jsp" />
<div class="container" style="min-height: calc(100vh - 240px)">
	<h3 class="title text-center">게시글 상세</h3>
	<c:if test="${post != null }">
		<div class="form-group">
			<label>제목</label>
			<div class="form-control">${post.po_title }</div>
		</div>		
		<div class="form-group">
			<label>작성자</label>
			<div class="form-control">${post.po_me_id }</div>
		</div>		
		<div class="form-group">
			<label>작성일</label>
			<div class="form-control">
				<fmt:formatDate value="${post.po_date }" pattern="yyyy-MM-dd HH:mm:ss" />
			</div>
		</div>		
		<div class="form-group">
			<label>조회수</label>
			<div class="form-control">${post.po_views }</div>
		</div>		
		<div class="form-group">
			<label>내용</label>
			<div class="form-control" style="min-height: 340px; height: auto">${post.po_sub }</div>
		</div>
		<a href="<c:url value="/post/list?co_num=${post.po_co_num }&page=${page}"/>" class="btn btn-outline-success">목록</a>
		<c:if test="${user.me_id == post.po_me_id }">
			<a href="<c:url value="/post/update?po_num=${post.po_num}"/>" class="btn btn-outline-primary">수정</a>
			<c:url var="url" value="/post/delete">
				<c:param name="po_num" value="${post.po_num }"/>
			</c:url>
			<a href="${url}" class="btn btn-outline-danger">삭제</a>
		</c:if>
	</c:if>
	<c:if test="${post == null }">
		<h4>삭제 되거나 등록되지 않은 게시글입니다.</h4>
	</c:if>
</div>
<jsp:include page="/WEB-INF/views/common/footer.jsp" />
</body>
</html>