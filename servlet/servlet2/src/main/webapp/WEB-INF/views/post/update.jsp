<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:include page="/WEB-INF/views/common/head.jsp"/>
<title>글 수정</title>
 <link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.js"></script>
</head>
<body>
<jsp:include page="/WEB-INF/views/common/header.jsp" />
<div class="container" style="min-height: calc(100vh - 240px)">
	<div class="container">
		<h3 class="title text-center">게시글 수정</h3>
		<form action="<c:url value="/post/update"/>" method="post">
			<input type="hidden" name="po_num" value="${post.po_num }">
			<div class="form-group">
				<label>제목</label>
				<input type="text" value="${post.po_title }" name="title" class="form-control">
			</div>		
			<div class="form-group">
				<label>내용</label>
				<textarea id="content" name="sub" class="form-control" >${post.po_sub }</textarea>
			</div>	
			<button type="submit" class="btn btn-outline-success">수정</button>
			<a href="<c:url value="/post/detail?po_num=${post.po_num}"/>" class="btn btn-outline-dark">취소</a>
		</form>
	</div>	
</div>
<jsp:include page="/WEB-INF/views/common/footer.jsp" />
	<script>
      $('#content').summernote({
        placeholder: 'Hello Bootstrap 4',
        tabsize: 2,
        height: 320
      });
    </script>
</body>
</html>