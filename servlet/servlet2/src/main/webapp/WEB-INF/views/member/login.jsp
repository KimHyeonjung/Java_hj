<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:include page="/WEB-INF/views/common/head.jsp" />
<title>로그인</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp" />

	<h3 class="text-center title">로그인🔏</h3>
	<div class="container">
		<form action="<c:url value="/login"/>" method="post" id="form">
			<div class="form-group">
				<label for="id">아이디:</label> 
				<input type="text" class="form-control" id="id" name="me_id">
			</div>
			<div class="form-group">
				<label for="pw">비번:</label> 
				<input type="password" class="form-control" id="pw" name="me_pw">
			</div>
			<div class="form-check">
				<label class="form-check-label">
	   			 	<input type="checkbox" class="form-check-input" value="true" name="auto">자동로그인
	  			</label>
  			</div>	
			<button type="submit" class="btn btn-outline-success col-12">로그인</button>
		</form>
	</div>
	<div class="container" style="min-height: calc(100vh - 240px)"></div>
	<jsp:include page="/WEB-INF/views/common/footer.jsp" />

</body>
</html>