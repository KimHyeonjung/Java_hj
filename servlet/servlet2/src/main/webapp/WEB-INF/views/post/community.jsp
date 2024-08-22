<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:include page="/WEB-INF/views/common/head.jsp" />
<title>커뮤니티 </title>
</head>
<body>
<jsp:include page="/WEB-INF/views/common/header.jsp" />
<div class="container" style="min-height: calc(100vh - 240px)">
	<div class="container">
	  <h2 class="title">커뮤니티</h2>
	  <div class="list-group">
	  	<c:forEach items="${list}" var="co">
	  		<c:url var="url" value="/post/list">
	  			<c:param name="co_num">${co.co_num}</c:param>
	  		</c:url>
	    	<a href="${url}" class="list-group-item list-group-item-action">${co.co_name}</a>
	    </c:forEach>
	  </div>
	</div>

</div>
<jsp:include page="/WEB-INF/views/common/footer.jsp" />
</body>
</html>