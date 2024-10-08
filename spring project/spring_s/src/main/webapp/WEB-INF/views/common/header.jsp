<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<body>
<nav class="navbar navbar-expand-sm bg-dark navbar-dark" style="justify-content: space-between;">
	<ul class="navbar-nav">
		<li class="nav-item">
			<a class="navbar-brand" href="<c:url value="/"/>">Home</a>
		</li>
		<li class="nav-item">
			<a class="nav-link" href="<c:url value="/post/list"/>">커뮤니티</a>
		</li>
		<li class="nav-item dropdown">
			<a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">
			  커뮤니티
			</a>
			<div class="dropdown-menu" id="community-list">
				
			</div>
		</li>
		<c:if test="${user ne null && user.me_authority eq 'ADMIN'}">
			<li class="nav-item">
				<a class="nav-link" href="<c:url value="/admin/community"/>">커뮤니티 관리</a>
			</li>
		</c:if>
	</ul>
	<ul class="navbar-nav">
		<c:if test="${user == null}">
		    <li class="nav-item">
				<a class="nav-link" href="<c:url value="/signup"/>">회원가입</a>
		    </li>
		    <li class="nav-item">
				<a class="nav-link" href="<c:url value="/login"/>">로그인</a>
		    </li>
		</c:if>
	    <c:if test="${user != null }">
	    	<li class="nav-item">
				<a class="nav-link" href="<c:url value="/mypage"/>">마이페이지</a>
		    </li>
	    	<li class="nav-item">
				<a class="nav-link" href="<c:url value="/logout"/>">로그아웃</a>
		    </li>
	    </c:if>
	</ul>
</nav>
<script type="text/javascript">
	$('#navbardrop').click(function(){
		$.ajax({
			async : true, //비동기 : true(비동기), false(동기)
			url : '<c:url value="/post/community/list"/>', 
			type : 'post', 
			dataType : "json", 
			success : function (data){
				console.log(data);
				var list = data;
				var str ='';				
				for(community of list){
					str += `
						<a href="<c:url value="/post/list?co_num=\${community.co_num}"/>" 
								class="dropdown-item">\${community.co_name}</a>
					`;
				}				
				$('#community-list').html(str);
			}, 
			error : function(jqXHR, textStatus, errorThrown){
		
			}
		});
	});
	
</script>
</body>
</html>