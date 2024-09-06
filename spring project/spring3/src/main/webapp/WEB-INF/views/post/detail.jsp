<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>게시글 상세</title>
<style type="text/css">
.title {font-size: 30px; font-weight: bold;}
.writer{font-size: 15px; font-weight: bold; margin-top: 10px;}
.sub {width: 170px; font-size: 12px; display: flex; justify-content: space-between; 
	margin-left: 0; }
.content{min-height: 320px; height: auto; margin-top: 15px;}
.cm-id{font-weight: bold;}
.btn{font-size: 15px;}
</style>
</head>
<body>
	<h4 class="text-center mt-2">게시글 상세</h4>
	<c:if test="${post ne null }">
	<div class="mt-5">
		<div class="title">
			<span class="title" id="title">${post.po_title }</span>
		</div>	
		<div>
			<span class="writer" >${post.po_me_id }</span>
		</div>	
		<div class="sub">
			<span class="date" ><fmt:formatDate value="${post.po_date }" pattern="yyyy-MM-dd."/>
			</span>
			<span class="views" >조회수: ${post.po_views }</span>
		</div>
		<hr>
		<div class="content">
			<div class="" >${post.po_sub }</div>
		</div>
		<c:if test="${list.size() != 0 }">
		<div class="file-list">
			<label>첨부파일:</label>
			<c:forEach items="${list }" var="file">
					<a style="margin-left: 5px;" class="" href="<c:url value="/download${file.fi_name }"/>" 
					download="${file.fi_ori_name }">${file.fi_ori_name } </a>
			</c:forEach>
		</div>
		</c:if>
	</div>
	</c:if>
	<c:if test="${post eq null }">
		<h3 class="mt-8">삭제 되거나 잘못된 게시글입니다.</h3>
	</c:if>
	<div class="d-flex justify-content-between">
		<div>
			<c:url var="url" value="/post/list/${cri.co_num }">
				<c:param name="page" value="${cri.page}" />
				<c:param name="type" value="${cri.type }" />
				<c:param name="search" value="${cri.search }" />
			</c:url> 
			<a href="${url }" class="btn btn-outline-dark">목록</a>		
		</div>
		<div>
		<c:if test="${post.po_me_id eq user.me_id }">
			<c:url var="url" value="/post/update/${post.po_num }">
				<c:param name="co_num" value="${cri.co_num}" />
				<c:param name="page" value="${cri.page}" />
				<c:param name="type" value="${cri.type }" />
				<c:param name="search" value="${cri.search }" />
			</c:url> 
			<c:url var="url" value="/post/update/${post.po_num }">
				<c:param name="co_num" value="${cri.co_num}" />
				<c:param name="page" value="${cri.page}" />
				<c:param name="type" value="${cri.type }" />
				<c:param name="search" value="${cri.search }" />
			</c:url> 
			<a href="${url }" class="btn btn-outline-dark">수정</a>
			<c:url var="url" value="/post/delete/${post.po_num }">
				<c:param name="co_num" value="${cri.co_num}" />
				<c:param name="page" value="${cri.page}" />
				<c:param name="type" value="${cri.type }" />
				<c:param name="search" value="${cri.search }" />
			</c:url> 
			<a href="${url }" class="btn btn-outline-danger">삭제</a>
		</c:if>
		</div>
	</div>
	
</body>
</html>
