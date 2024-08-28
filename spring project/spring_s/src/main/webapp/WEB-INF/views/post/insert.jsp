<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>게시글 등록</title>
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
			<textarea class="form-control" id="content" name="po_sub"></textarea>
		</div>
		<div class="form-group">
			<label>첨부파일:</label>
			<input type="file" class="form-control" name="fileList">
			<input type="file" class="form-control" name="fileList">
			<input type="file" class="form-control" name="fileList">
		</div>
		<input type="hidden" name="po_co_num" value="${co_num}">	
		<button type="submit" class="btn btn-outline-info col-12 mb-3">게시글 등록</button>
	</form>
</body>
</html>
