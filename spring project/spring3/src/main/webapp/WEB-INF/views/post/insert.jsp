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
.filebox input[type="file"] {
    position: absolute;
    width: 0;
    height: 0;
    padding: 0;
    overflow: hidden;
    border: 0;
}
.filebox label {
    display: inline-block;
    padding: 10px 20px;
    color: #fff;
    vertical-align: middle;
    background-color: #999999;
    cursor: pointer;
    height: 40px;
}
.filebox .upload-name {
    margin-left: 10px;
    display: inline-block;
    height: 40px;
    padding: 0 10px;
    vertical-align: middle;
    border: 1px solid #dddddd;
    width: calc(100% - 125px);
    color: #999999;
}
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
			<div class="">
				<div class="filebox">
				    <label for="file1">파일찾기</label> 
				    <input class="upload-name" value="첨부파일" placeholder="첨부파일">
				    <input type="file" class="form-control" name="fileList" id="file1">
				</div>
				<div class="filebox">
				    <label for="file2">파일찾기</label> 
				    <input class="upload-name" value="첨부파일" placeholder="첨부파일">
				    <input type="file" class="form-control" name="fileList"  id="file2">
				</div>
				<div class="filebox">
				    <label for="file3">파일찾기</label> 
				    <input class="upload-name" value="첨부파일" placeholder="첨부파일">
				    <input type="file" class="form-control" name="fileList"  id="file3">
				</div>
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
    <script type="text/javascript">
    	$('[type=file]').on('change', function(){
    		var fileName = $(this).val();
    		$(this).parent().find('.upload-name').val(fileName);
    	});
    </script>
</body>
</html>
