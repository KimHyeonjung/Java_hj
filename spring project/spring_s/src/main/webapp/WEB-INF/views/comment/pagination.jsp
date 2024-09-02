<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<ul class="comment-list" style="list-style: none; padding: 0">
	<c:forEach items="${list }" var="comment">
	<li class="comment-item">
		<div class="clearfix">
			<span class="float-left cm-id" style="line-height: 36px;">${comment.cm_me_id}</span>
			<c:if test="${comment.cm_me_id eq user.me_id }">
				<div class="float-right">
					<button class="btn btn-outline-info btn-cm-update" data-num="${comment.cm_num }">수정</button>
					<button class="btn btn-outline-danger btn-cm-delete" data-num="${comment.cm_num }">삭제</button>
				</div>
			</c:if>
		</div>
		<div class="cm-content" style="padding-left: 20px; line-height: 38px;">${comment.cm_content}</div>
	</li>
	</c:forEach>
</ul>
<div class="comment-pagination">
	<ul class="pagination justify-content-center">
		<c:if test="${pm.prev }">
			<li class="page-item" data-page="${pm.startPage - 1 }"><a class="page-link"
				href="javascript:void(0);">이전</a></li>
		</c:if>
		<c:forEach begin="${pm.startPage }" end="${pm.endPage }" var="i">
			<c:set var="active" value=""/>
			<c:if test="${pm.cri.page == i }">
				<c:set var="active" value="active"/>
			</c:if>
			<li class="page-item ${active }" data-page="${i }"><a class="page-link"
				href="javascript:void(0);">${i }</a></li>
		</c:forEach>
		<c:if test="${pm.next }">
			<li class="page-item" data-page="${pm.endPage + 1 }"><a class="page-link"
				href="javascript:void(0);">다음</a></li>
		</c:if>
	</ul>
</div>
<div class="comment-input-box">
	<div class="input-group mb-3" id="insert-comment">
		<textarea type="text" class="form-control" id="input-comment"
			placeholder="댓글 입력"></textarea>
		<div class="input-group-append">
			<span class="input-group-text btn-insert" style="cursor: pointer;">등록</span>
		</div>
	</div>
</div>
<script type="text/javascript">
	$('.btn-cm-update').click(function(){
		$('#update-comment').remove();
		var cm_num = $(this).data('num');
		var cm_content = $(this).parents('.comment-item').find('.cm-content').text();
		var str = `
			<div class="input-group mb-3 comment-update-box" id="update-comment">
				<textarea type="text" class="form-control" id="update-input-comment"
					placeholder="댓글 입력">\${cm_content}</textarea>
				<input type="hidden" name="cm_num" value="\${cm_num}">
				<div class="input-group-append">
					<span class="input-group-text btn-update" data-num="\${cm_num}" style="cursor: pointer;">수정 완료</span>
				</div>
			</div>
		`;
		$('#insert-comment').hide();
		$('#insert-comment').after(str);
	});	
	$(document).off('click','.btn-update'); //이걸 추가하던지 detail.jsp로 옮기면 됨
	$(document).on('click','.btn-update', function(){
		var cm_num = $(this).data('num');
		var cm_content = $('#update-input-comment').val();
		let comment = {
			cm_num : cm_num,
			cm_content : cm_content
		} 
		if(cm_content.length == 0){
			alert('댓글을 입력하세요');
			$('#update-input-comment').focus();
			return;
		}
		//ajax로 통신 : json 보내고 object로 받음
		$.ajax({
			async : true, //비동기 : true(비동기), false(동기)
			url : '<c:url value="/comment/update"/>', 
			type : 'post', 
			data : JSON.stringify(comment), 
			contentType : "application/json; charset=utf-8",
			success : function (data){
				if(data){
					alert('댓글을 수정했습니다.');
				}else{
					alert('댓글을 수정하지 못했습니다.');
				}
				getCommentList2(cri);
			}, 
			error : function(jqXHR, textStatus, errorThrown){
				console.log(jqXHR);
			}
		});	
	});
</script>