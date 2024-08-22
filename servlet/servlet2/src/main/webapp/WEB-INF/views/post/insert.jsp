<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:include page="/WEB-INF/views/common/head.jsp"/>
<title>글 쓰기</title>
 <link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.js"></script>
</head>
<body>
<jsp:include page="/WEB-INF/views/common/header.jsp" />
<div class="container" style="min-height: calc(100vh - 240px)">
	<div class="container">
		<h3 class="title text-center">${co_name} 게시글 등록</h3>
		<form action="<c:url value="/post/insert"/>" method="post">
			<div class="form-group">
				<label>제목</label>
				<input type="text" name="title" class="form-control">
			</div>		
			<div class="form-group">
				<label>내용</label>
				<textarea id="content" name="sub" class="form-control" ></textarea>
				<input type="hidden" name="po_co_num" value="${co_num }">
			</div>	
			<button type="submit" class="btn btn-outline-success">등록</button>
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