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
	.list-community{ list-style: none; display: flex; flex-wrap: wrap;}
	.item-community{ width: 33.33%; height: 80px; box-sizing: border-box; padding: 10px;}
	.link-community{
		display: block; border: 1px solid black; box-sizing: border-box;
		height: 100%; text-align: center; line-height: 58px; font-size: 24px;
		text-decoration: none; color: black;
	}
	.link-community:hover{
		text-decoration: none; color: white; background-color: tomato;
	}
</style>
</head>
<body>
<jsp:include page="/WEB-INF/views/common/header.jsp"/>
<div class="container">
	<h1>커뮤니티 목록</h1>
	<ul class="list-community">
		
	<c:forEach items="${list}" var="co" varStatus="vs">
		<c:url var="url" value="/post/list">
			<c:param name="co_num" value="${co.co_num }"/>
		</c:url>
		<li class="item-community">
			<a href="${url}" class="link-community">${co.co_name }</a>		
		</li>
	</c:forEach>		
	</ul>
</div>
</body>
</html>