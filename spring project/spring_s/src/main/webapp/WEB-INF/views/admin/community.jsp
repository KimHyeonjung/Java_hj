<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	
</head>
<body>
<h3 class="mt-3">커뮤니티 관리</h3>
<ul class="list-group mt-3 mb-3">
	<c:forEach items="${list }" var="community">
	  <li class="list-group-item d-flex justify-content-between align-items-center">
	    <span class="co-name">${community.co_name}</span>
	    <span>
	    	<span class="badge badge-primary badge-pill">${community.co_count}</span>
	    	<button class="btn btn-outline-danger btn-co-update" data-num="${community.co_num}">수정</button>
	    	<!-- 커뮤니티 번호를 서버컨트롤러에 전달  -->
	    	<a class="btn btn-outline-dark" href="<c:url value="/admin/community/delete?co_num=${community.co_num }"/>">삭제</a>
	    </span>
	  </li>
	</c:forEach>
</ul>
<form action="<c:url value="/admin/community/insert"/>" method="post" class="insert-co-box">
	<div class="input-group mb-3">
		<input type="text" class="form-control" name="name" placeholder="등록할 커뮤니티명을 입력하세요" required>
		<div class="input-group-append">
			<button type="submit" class="btn btn-outline-info col-12">등록</button>
		</div>
	</div>
</form>
<!--
jsp
1. 수정 버튼에 커뮤니티 번호를 추가
	- data-xxx
2. 수정 버튼 클릭 이벤트를 등록(커뮤니티에 있는 수정 버튼)
	- 앞서 만들어진 커뮤니티 수정창을 제거
	- 커뮤니티 등록창과 형태가 같은 커뮤니티 수정창을 만들고
	- 커뮤니티 등록창 뒤에 추가
	- 커뮤니티 등록창은 감춤
	- 커뮤니티 번호를 수정창 안에 input: hidden으로 추가
3. 수정 버튼 클릭 이벤트를 등록(2번에서 만든 수청장에 있는 수정 버튼)
	- 커뮤니티 번호와 커뮤니티 명을 전송.
	- post로 전송
컨트롤러
1. 메소드를 [추가
	- PostMapping 추가
	- 커뮤니티 번호와 커뮤니티 명을 가져옴
	- 서비스에게 커뮤니티 번호와 커뮤니티 명을 주면서 수정하라고 요청
결과에 따라 알림을 출력
-->
<script type="text/javascript">
	$('.btn-co-update').click(function(){
		$('.update-co-box').remove();
		var co_num = $(this).data('num');
		var co_content = $(this).parents('.list-group-item').find('.co-name').text();
		var str = `
			<form action="<c:url value="/admin/community/update"/>" method="post" class="update-co-box">
				<div class="input-group mb-3">
					<input type="text" class="form-control" name="co_name" value="\${co_content}" required>
					<input type="hidden" class="form-control" name="co_num" value="\${co_num}" required>
					<div class="input-group-append">
						<button type="submit" class="btn btn-outline-info col-12">수정</button>
					</div>
				</div>
			</form>
		`;
		$('.insert-co-box').hide();
		$('.insert-co-box').after(str);
	});
</script>
</body>
</html>
