<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">

<script src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
	<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
  <!-- Brand/logo -->
  	<a class="navbar-brand" href="<c:url value="/"/>">Home</a>
  
  <!-- Links -->
	<ul class="navbar-nav">
		<li class="nav-item">
	    	<a class="nav-link" href="<c:url value="/community"/>">커뮤니티</a>
	    </li>
		<c:choose>
			<c:when test="${user == null}">
			    <li class="nav-item">
			      <a class="nav-link" href="<c:url value="/signup"/>">회원가입</a>
			    </li>
			    <li class="nav-item">
			      <a class="nav-link" href="<c:url value="/login"/>">로그인</a>
			    </li>
		   </c:when>
		    <c:otherwise>
			    <li class="nav-item">
			      <a class="nav-link" href="<c:url value="/logout"/>">로그아웃</a>
			    </li>
		    </c:otherwise>
	    </c:choose>
	</ul>
	</nav>
</body>
</html>