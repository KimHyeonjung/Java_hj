<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 상세</title>
<jsp:include page="/WEB-INF/views/common/head.jsp"/>
<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.js"></script>
<style type="text/css">
	.pw{display: none;}
</style>
</head>
<body>
<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<div class="container">
	<h1>게시글 상세</h1>
		<div class="form-group">
		  <label for="title">제목:</label>
		  <input type="text" class="form-control"  value="${post.po_title }">
		</div>
		<div class="form-group">
		  <label for="title">작성자:</label>
		  <input type="text" class="form-control"  value="${post.po_me_id }">
		</div>
		<div class="form-group">
		  <label for="title">작성일:</label>
		  <input type="text" class="form-control"  value="<fmt:formatDate value="${post.po_date }" pattern="yyyy-MM-dd HH:mm:ss"/>">
		</div>
		<div class="form-group">
		  <label for="title">조회수:</label>
		  <input type="text" class="form-control"  value="${post.po_views }">
		</div>
		<div class="form-group">
		  <label for="content">내용:</label>
		  <div class="form-control" style="min-height: 300px">${post.po_sub}</div>
		</div>
		<a href="<c:url value="/post/list?co_num=${post.po_co_num}"/>" class="btn btn-outline-primary">목록</a>
		<c:if test="${user ne null && post.po_me_id eq user.me_id}">
			<a href="<c:url value="/post/update?po_num=${post.po_num}"/>" class="btn btn-outline-primary">수정</a>
			<a href="#" id="delete" class="btn btn-outline-danger">삭제</a>
			<input type="password" class="pw" name="pw"  style="min-width: 50px"/>
			<!-- <c:url value="/post/delete?co_num=${post.po_co_num}&po_num=${post.po_num}"/> -->
		</c:if>
	</div>
	<script type="text/javascript">
		$('#delete').click(()=>{
			$('.pw').toggleClass('pw');
		});
	</script>
</body>
</html>