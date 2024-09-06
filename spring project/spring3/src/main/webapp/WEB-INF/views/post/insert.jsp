<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>게시글 등록</title>
	<link href="<c:url value="/resources/summernote/"/>summernote-bs4.min.css" rel="stylesheet">
    <script src="<c:url value="/resources/summernote/"/>summernote-bs4.min.js"></script>
<style type="text/css">
</style>
</head>
<body>
<h4 class="text-center">게시글 등록</h4>
	<form action="<c:url value="/post/insert"/>" method="post" enctype="multipart/form-data">
		<div class="form-group">
			<label for="title">제목:</label>
			<input type="text" class="form-control" id="title" name="po_title">
		</div>
		<div class="form-group">
			<label for="content">내용:</label>
			<textarea class="form-control" id="sub" name="po_sub"></textarea>
		</div>
		<div class="form-group">
			<label>파일 첨부:</label>
			<div>
		<!-- 		<label for="file01" class="btn btn-outline-dark">파일1</label>
				<label for="file02" class="btn btn-outline-dark">파일2</label>
				<label for="file03" class="btn btn-outline-dark">파일3</label> -->
				<input type="file" class="form-control" name="fileList">
				<input type="file" class="form-control" name="fileList">
				<input type="file" class="form-control" name="fileList">
			</div>
		</div>
		<input type="hidden" name="po_co_num" value="${co_num}">	
		<button type="submit" class="btn btn-outline-info col-12 mb-3">게시글 등록</button>
	</form>
	<script>
      $('#sub').summernote({
        placeholder: '내용을 작성하세요',
        tabsize: 2,
        height: 320
      });
    </script>
</body>
</html>
