<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>게시글 등록</title>
	<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.js"></script>
</head>
<body>
<h1>게시글 등록</h1>
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
			<label>첨부파일:</label>
			<label for="file01" style="border: 1px solid blue; border-radius: 5px; line-height: 20px; cursor: pointer;">파일골라</label>
			<input type="file" class="form-control" name="fileList" id="file01" style="display: none;">
			<input type="file" class="form-control" name="fileList">
			<input type="file" class="form-control" name="fileList">
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
