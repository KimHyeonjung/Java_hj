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
<style type="text/css">
.menu-search{}
</style>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp" />
	<div class="container">
		<h3>게시글 상세</h3>
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
	</div>	
	<div class="container" style="min-height: calc(100vh - 240px)"></div>
	<jsp:include page="/WEB-INF/views/common/footer.jsp" />
	
	
</body>
</html>