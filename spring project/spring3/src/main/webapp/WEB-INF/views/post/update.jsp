<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>게시글 수정</title>
	<link href="<c:url value="/resources/summernote/"/>summernote-bs4.min.css" rel="stylesheet">
    <script src="<c:url value="/resources/summernote/"/>summernote-bs4.min.js"></script>
<style type="text/css">
</style>
</head>
<body>
<h4 class="text-center">게시글 수정</h4>
	<form action="<c:url value="/post/update"/>" method="post" enctype="multipart/form-data">
		<div class="form-group">
			<label for="title">제목:</label>
			<input type="text" class="form-control" id="title" name="po_title" value="${post.po_title }">
		</div>
		<div class="form-group">
			<label for="content">내용:</label>
			<textarea class="form-control" id="sub" name="po_sub">${post.po_sub }</textarea>
		</div>
		<div class="form-group box-att">
			<label>파일 첨부:</label>
			<div>
			<c:forEach items="${list }" var="file">
				<div class="form-control d-flex justify-content-between">
					<span style="margin-left: 5px;" >${file.fi_ori_name } </span>
					<a href="javascript:void(0);" class="btn-del" style="color: red;" data-num="${file.fi_num }">&times;</a>
				</div>
			</c:forEach>
			<c:forEach begin="1" end="${3 - list.size() }">
				<input type="file" class="form-control" name="fileList">
			</c:forEach>
			</div>
		</div>
		<input type="hidden" name="po_num" value="${post.po_num}">	
		<input type="hidden" name="co_num" value="${post.po_co_num}">	
		<input type="hidden" name="page" value="${cri.page}">	
		<input type="hidden" name="type" value="${cri.type }">	
		<input type="hidden" name="search" value="${cri.search }">	
		<button type="submit" class="btn btn-outline-info col-12 mb-3">게시글 수정</button>
	</form>
	<script>
      $('#sub').summernote({
        placeholder: '내용을 작성하세요',
        tabsize: 2,
        height: 320
      });
    </script>
	<script type="text/javascript">
		$('.btn-del').click(function(){
			var fi_num = $(this).data('num')
			var str1 = `<input type="hidden" name="nums" value="\${fi_num}">`;
			var str2 = `<input type="file" class="form-control" name="fileList">`;
			$('.box-att').append(str1);
			$('.box-att').append(str2);
			$(this).parent().remove();
		});
	</script>
</body>
</html>
