<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style type="text/css">
	.modal-container{
		position: fixed; top: 0; bottom: 0; left: 0; right: 0;
		background: rgba(0,0,0, 0.2);
	}
	.modal-email{
		position: fixed; top: 50vh; left: 50vw;
		transform: translate(-50%, -50%);
	}
</style>
</head>
<body>
	<h1>비번찾기</h1>
	<div class="box-find-pw">
		<div class="form-group">
			<label for="id">아이디:</label>
			<input type="text" class="form-control" id="id" name="me_id">
		</div>
		<button type="button" class="btn btn-outline-success col-12 btn-find-pw">비번찾기</button>
	</div>
	
	<script type="text/javascript">
	$('.btn-find-pw').click(function(){
		var id = $('#id').val();
		if(id == ''){
			alert('아이디를 입력하세요.');
			return;
		}
		var str = `
			<div class="modal-container">
				<button class="btn btn-info modal-email">
				  <span class="spinner-border spinner-border-sm"></span>
				  이메일 전송중입니다.
				</button>
			</div>
		`;
		$('.box-find-pw').after(str);
		var res = false;
		setTimeout(() =>{
			$.ajax({
				async : false, //비동기 : true(비동기), false(동기)
				url : '<c:url value="/find/pw"/>', 
				type : 'post', 
				data : {id : id}, 
				success : function (data){
					res = data;
					$('.modal-container').remove();
				}, 
				error : function(jqXHR, textStatus, errorThrown){

				}
			});
			if(res){
				alert("비밀번호가 전송됐습니다.");
			}else{
				alert("비밀번호 찾기에 실패했습니다.");
			}
		}, 100);
		
	});
	</script>
</body>
</html>