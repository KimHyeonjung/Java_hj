<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>회원가입</title>

<style type="text/css">
.container-sm{width: 500px;}
</style>
</head>
<body>
	<h2 class="text-center mt-5 mb-4">로그인</h2>
	<div class="container-sm">
		<form action="<c:url value="/guest/login"/>" method="post" id="form">
			<div class="form-group">
				<label for="id">아이디:</label> 
				<input type="text"
					class="form-control" placeholder="사용할 아이디를 입력하세요" id="id" name="me_id" value="${id }">
			</div>
			<div class="form-group">
				<label for="pw">비번:</label> 
				<input type="password"
					class="form-control" placeholder="비빌번호를 입력하세요" id="pw" name="me_pw">
			</div>
		  <div class="text-center">
			<button type="submit" class="btn btn-primary ">로그인</button>
		  </div>
		</form>
	</div>
	<script type="text/javascript">
	//아이디가 없으면 아이디입력칸이 활성화되고 아이디가 있으면 비번입력칸이 활성화
		var id = $('#id').val();
		if(id == ''){
			$('#id').focus();
		}else{
			$('#pw').focus();
		}
		
		//아이디나 비번이 입력되지 않으면 알림을 띄우고 해당 창을 활성화
		$('#form').submit(function(){
			var id = $('#id').val();
			var pw = $('#pw').val();
			if(id == ''){
				alert('아이디를 입력하세요.');
				$('#id').focus();
				return false;
			}
			if(pw == ''){
				alert('비번을 입력하세요.');
				$('#pw').focus();
				return false;
			}
			return true;
		});
	</script>
</body>
</html>
