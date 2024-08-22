<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<nav class="navbar navbar-expand-sm bg-dark navbar-dark" style="justify-content: space-between;">
	<ul class="navbar-nav left-nav">
		<li class="nav-item active"><a class="nav-link"
			href="<c:url value="/"/>">home</a></li>
		<li class="nav-item">
			<a class="nav-link" href="<c:url value="/community"/>">커뮤니티</a>
		</li>
		<li class="dropdown">
			<button type="button" class="btn btn-primary dropdown-toggle"
				data-toggle="dropdown">커뮤니티</button>
			<div class="dropdown-menu" id="community-list"></div>
		</li>
	</ul>
	<ul class="navbar-nav right-nav">
		<c:if test="${user eq null }">
			<li class="nav-item"><a class="nav-link"
				href="<c:url value="/signup"/>">회원가입</a></li>
			<li class="nav-item"><a class="nav-link"
				href="<c:url value="/login"/>">로그인</a></li>
		</c:if>
		<c:if test="${user ne null }">
			<li class="nav-item"><a class="nav-link"
				href="<c:url value="/logout"/>">로그아웃</a></li>
		</c:if>
	</ul>
</nav>
<script>
	$.ajax({
		url : '<c:url value="/community"/>',
		method : 'post',
		success : function(data){
			var str = '';
			var list = data.list;
			
			for(co of list){
				str += `<a class="dropdown-item" href="<c:url value="/post/list?co_num=\${co.co_num}"/>">\${co.co_name}</a>`;
			}
			$("#community-list").html(str);
		},
		error : function(xhr){
			console.log(xhr);
		}
	});
	</script>
