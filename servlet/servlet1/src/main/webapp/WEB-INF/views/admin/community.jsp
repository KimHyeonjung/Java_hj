<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>커뮤니티</title>
<jsp:include page="/WEB-INF/views/common/head.jsp"/>
<style type="text/css">
	.list-community{ list-style: none; display: flex; flex-wrap: wrap; margin-top: 40px;}
	.item-community{ width: 33.33%; box-sizing: border-box; padding: 5px;}
	.link-community{
		display: block; border: 1px solid black; box-sizing: border-box;
		text-align: center; line-height: 40px; font-size: 24px;
		text-decoration: none; color: black; padding-bottom: 10px;
	}
	.link-community:hover{
		text-decoration: none; color: white; background-color: tomato;
	}
	.text-center{margin: 30px;}
	
	.input-group{justify-content: center;}
	.inner-community{display: block;}
</style>
</head>
<body>
<jsp:include page="/WEB-INF/views/common/header.jsp"/>
<div class="container">
	<h1 class="text-center">커뮤니티 목록</h1>
	<ul class="list-community">
		
	<c:forEach items="${list}" var="co" varStatus="vs">
		<c:url var="url" value="/post/list">
			<c:param name="co_num" value="${co.co_num }"/>
		</c:url>
		<li class="item-community">
			<span class="link-community">
				<span class="inner-community"> ${co.co_name }</span>
				<button class="btn btn-outline-danger btn-update" data-num="${co.co_num }">수정</button>	
				<a class="btn btn-outline-dark btn-delete" 
				href="<c:url value="/admin/community/delete?co_num=${co.co_num}"/>">삭제</a>	
			</span>	
		</li>
	</c:forEach>		
	</ul>
	<form class="input-group mb-3" action="<c:url value="/admin/community/insert"/>" method="post" id="form_insert">
	    <input type="text" class="community-input" name="co_name" style="width: 200px">
	    <div class="input-group-append">
	    	<button type="submit" class="btn btn-outline-success">등록</button>
	    </div>
	</form>
</div>
<script type="text/javascript">
$('.btn-delete').click(function(e){
	if(!confirm('해당 커뮤니티를 삭제하시겠습니까?')){
	e.preventDefault();
	return;
	}
});
$('.btn-update').click(function(e){
	$('#form_update').remove();
	var num = $(this).data('num');
	var name = $(this).prev().text();
	var str = `
	<form class="input-group mb-3" action="<c:url value="/admin/community/update"/>" method="post" id="form_update">
	    <input type="text" class="community-input" name="co_name" style="width: 200px" value="\${name}">
	    <div class="input-group-append">
	    	<button type="submit" class="btn btn-outline-danger">수정</button>
	    </div>
	    <input type="hidden" name="co_num" value="\${num}">
	</form>
	`;
	$('#form_insert').after(str);
	$('#form_insert').hide();
	
});

</script>
</body>
</html>


