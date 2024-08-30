<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>게시글 수정</title>
	<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.js"></script>
</head>
<body>
<h1>게시글 수정</h1>
	<form action="<c:url value="/post/update"/>" method="post" enctype="multipart/form-data">
		<div class="form-group">
			<label for="title">제목:</label>
			<input type="text" class="form-control" id="title" name="po_title" value="${post.po_title }">
		</div>
		<div class="form-group">
			<label for="content">내용:</label>
			<textarea class="form-control" id="sub" name="po_sub">${post.po_sub }</textarea>
		</div>
		<div class="form-group file-container">
			<label>첨부파일:</label>
			<c:forEach items="${fl }" var="file">
				<div class="form-control" >
					<span>${file.fi_ori_name }</span>
					<span class="btn-del" style="color: red; cursor: pointer;" data-num="${file.fi_num }">&times;</span>
				</div>
			</c:forEach>
			<c:forEach begin="1" end="${3 - fl.size() }">
				<input type="file" class="form-control" name="fileList">
			</c:forEach>
		</div>
		<input type="hidden" name="po_num" value="${post.po_num}">	
		<input type="hidden" name="co_num" value="${cri.co_num}">	
		<input type="hidden" name="page" value="${cri.page}">	
		<input type="hidden" name="search" value="${cri.search}">	
		<input type="hidden" name="type" value="${cri.type}">	
		<button type="submit" class="btn btn-outline-info col-12 mb-3">게시글 수정</button>
	</form>
	<script>
      $('#sub').summernote({
        placeholder: '내용을 작성하세요',
        tabsize: 2,
        height: 320
      });
      $('.btn-del').click(function(){
    	 var fi_num = $(this).data('num');
    	 $(this).parent().remove();
    	 var input = `<input type="hidden" name="fi_nums" value="\${fi_num}">`
    	 var file = `<input type="file" class="form-control" name="fileList">`;
    	 $('.file-container').append(input);
    	 $('.file-container').append(file);
      });
    </script>
</body>
</html>
