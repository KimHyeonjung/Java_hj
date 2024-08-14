<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<jsp:include page="/WEB-INF/views/common/head.jsp"/>
<style type="text/css">
	.container{width: 500px;}
	.btn{margin-top: 20px;}
</style>
</head>
<body>
<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<div class="container">
		<h1 class="mt-3 text-center">로그인</h1>
		<br>
		<form action="<%=request.getContextPath() %>/login" method="post" id="form">
			<div >
				<div class="form-group">
				  <label for="usr">아이디:</label>
				  <input type="text" class="form-control" id="id" name="id">
				</div>
				<div class="form-group">
				  <label for="pwd">비번:</label>
				  <input type="password" class="form-control" id="pwd" name="pwd">
				</div>
				<button type="submit" class="btn btn-outline-success col-12">로그인</button>
			</div>
		</form>
	</div>
</body>
</html>