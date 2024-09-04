<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>회원가입</title>
<script src="<c:url value="/resources/js/jquery.validate.min.js"/>"></script>
<script src="<c:url value="/resources/js/additional-methods.min"/>"></script>
<style type="text/css">
.error{color: red;}
</style>
</head>
<body>
	<h2 class="text-center mt-5 mb-4">회원 가입</h2>
	<div class="container-sm">
		<form action="<c:url value="/guest/signup"/>" method="post" id="signupform">
			<div class="form-group">
				<label for="id">아이디:</label> 
				<input type="text"
					class="form-control" placeholder="사용할 아이디를 입력하세요" id="id" name="me_id">
			</div>
			<div class="form-group">
				<label for="pw">비번:</label> 
				<input type="password"
					class="form-control" placeholder="비빌번호를 입력하세요" id="pw" name="me_pw">
			</div>
			<div class="form-group">
				<label for="pw2">비번 확인:</label> 
				<input type="password"
					class="form-control" placeholder="비밀번호가 맞는지 확인하세요" id="pw2" name="me_pw2">
			</div>
			<div class="form-group">
			    <label for="email">이메일:</label>
			    <input type="email" class="form-control" placeholder="이메일을 입력하세요" id="email" name="me_email">
		  </div>
		  <div class="text-center">
			<button type="submit" class="btn btn-primary ">회원 가입</button>
		  </div>
		</form>
	</div>
	<script type="text/javascript">
	$('#signupform').validate({
		rules : {
			me_id : {
				required : true,
				regex : /^\w{6,13}$/
			},
			me_pw : {
				required : true,
				regex : /^[a-zA-Z0-9!@#$]{6,15}$/
			},
			me_pw2 : {
				equalTo : pw
			},
			me_email : {
				required : true,
				email : true
			}
		},
		messages : {
			me_id : {
				required : '필수 항목입니다.',
				regex : '아이디는 영어, 숫자만 가능하며, 6~13자이어야 합니다.'
			},
			me_pw : {
				required : '필수 항목입니다.',
				regex : '아이디는 영어, 숫자, 특수문자(!@#$)만 가능하며, 6~15자이어야 합니다.'
			},
			me_pw2 : {
				equalTo : '비번과 일치하지 않습니다.'
			},
			me_email : {
				required : '필수 항목입니다.',
				email : '이메일 형식이 아닙니다'
			}
		},
		submitHandler : function(){
			return true;
		}
	});
	$.validator.addMethod('regex', function(value, element, regex){
		var re = new RegExp(regex);
		return this.optional(element) || re.test(value);
	}, "정규표현식을 확인하세요.");
	</script>
</body>
</html>
