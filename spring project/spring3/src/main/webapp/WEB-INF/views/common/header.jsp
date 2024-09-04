<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<body>
	<nav class="navbar navbar-expand-sm bg-dark navbar-dark justify-content-between align-items-center">
		

		<ul class="navbar-nav">
			<li>
			<a class="navbar-brand p-0" href="<c:url value="/"/>"> <img src="<c:url value="/resources/image/google.png"/>" alt="logo"
				style="width: 40px;">
			</a>
			</li>
			<li class="nav-item"><a class="nav-link" href="#">커뮤니티</a></li>
			<li class="nav-item"><a class="nav-link" href="#">Link 2</a></li>
			<li class="nav-item"><a class="nav-link" href="#">Link 3</a></li>
		</ul>
		<ul class="navbar-nav">
			<li class="nav-item"><a class="nav-link" href="<c:url value="/guest/signup"/>">회원가입</a></li>
			<li class="nav-item"><a class="nav-link" href="#">로그인</a></li>
		</ul>
	</nav>
</body>
</html>