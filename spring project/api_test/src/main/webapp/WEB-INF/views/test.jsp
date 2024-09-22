<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	test
</h1>
	<form id="form">
		<input type="text" name="prompt" id="prompt">
		<button type="submit">전송</button>
	</form>

<script>
	$('#form').submit(function(){
		var prompt = $('#prompt').val();
		var obj = {
				prompt : prompt
		}
		
		$.ajax({
			async : false, //비동기 : true(비동기), false(동기)
			url : '<c:url value="/openai/generate"/>', 
			type : 'post', 
			data : JSON.stringify(obj), 
			contentType : "application/json; charset=utf-8",
			success : function (data){
				console.log(data);
				alert(data);
			}, 
			error : function(jqXHR, textStatus, errorThrown){
				console.log(jqXHR);
			}
		});
	})
</script>
</body>
</html>
