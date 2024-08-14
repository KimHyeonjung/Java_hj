<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 상세</title>
<jsp:include page="/WEB-INF/views/common/head.jsp"/>
<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.js"></script>
<style type="text/css">
	.hide{display: none;}
	
	.comment-list{
		list-style: none; padding: 0; 
	}
	.comment-list>.comment-item{
		margin-bottom: 20px;
	}
	.comment-list>.comment-item.reply{
		padding-left: 50px;
	}
	.title{
		font-size: 30px; margin-top: 40px;
	}
</style>
</head>
<body>
<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<div class="container">
		<%-- <div class="form-group">
		  <label for="title">제목:</label>
		  <input type="text" class="form-control"  value="${post.po_title }">
		</div>
		<div class="form-group">
		  <label for="title">작성자:</label>
		  <input type="text" class="form-control"  value="${post.po_me_id }">
		</div>
		<div class="form-group">
		  <label for="title">작성일:</label>
		  <input type="text" class="form-control"  value="<fmt:formatDate value="${post.po_date }" pattern="yyyy-MM-dd HH:mm:ss"/>">
		</div>
		<div class="form-group">
		  <label for="title">조회수:</label>
		  <input type="text" class="form-control"  value="${post.po_views }">
		</div> --%>
		
		<div class="form-group title">
			${post.po_title }
		</div>
		<hr>
		<div class="form-group">
	  		<label for="title">작성자: ${post.po_me_id }</label>
	   		 / <label for="title">작성일: <fmt:formatDate value="${post.po_date }" pattern="yyyy-MM-dd HH:mm:ss"/></label>
			 / <label for="title">조회수: ${post.po_views }</label>
		</div>
		
		<div class="form-group">
		  <div class="form-control" style="min-height: 320px">${post.po_sub}</div>
		</div>
		<div class="text-center">
			<a href="#" data-state="1"
			class="btn-up btn btn<c:if test="${reco.re_state ne 1}">-outline</c:if>-primary"> 👍 추천(<span>${post.po_up}</span>)</a>
			<span> / </span>
			<a href="#" data-state="-1"
			class="btn-down btn btn<c:if test="${reco.re_state ne -1}">-outline</c:if>-danger"> 🤬 비추천(<span>${post.po_down}</span>)</a>
		</div>
		<hr>
		<div class="form-group">
			<h4>댓글 목록</h4>
			 <ul class="comment-list">
			 	<li class="comment-item">
			 		<div>작성자 아이디(시간)</div>
			 		<div>댓글 내용</div>
			 	</li>
			 	<li class="comment-item reply">
			 		<div>작성자 아이디(시간)</div>
			 		<div>대댓글 내용</div>
			 	</li>
			 </ul>
			 <div class="comment-pagination"></div>
			 <div class="comment-insert-box">
			 	<textarea rows="" cols="" class="col-12 input-comment-insert"></textarea>
			 	<div class="text-right">
			 		<button class="btn btn-outline-success btn-comment-insert">등록</button>
			 	</div>
			 </div>
		</div>
		<a href="<c:url value="/post/list?co_num=${post.po_co_num}"/>" class="btn btn-outline-primary">목록</a>
		<c:if test="${user ne null && post.po_me_id eq user.me_id}">
			<a href="<c:url value="/post/update?po_num=${post.po_num}"/>" class="btn btn-outline-primary">수정</a>
			<a href="" id="delete" class="btn btn-outline-danger">삭제</a>
			<input type="password" class="hide" name="pw"  style="min-width: 50px"/>
			<!-- <c:url value="/post/delete?co_num=${post.po_co_num}&po_num=${post.po_num}"/> -->
		</c:if>
	</div>
	
	
<script type="text/javascript">

	var cri = {
		po_num : '${post.po_num}',
		page : 1
	}
	
	getCommentList(cri);
	

	function checkLogin(){
		if('${user.me_id}' == ''){
			if(confirm('로그인이 필요한 서비스입니다.\n로그인 페이지로 이동하시겠습니까?')){
				location.href = '<c:url value="/login"/>';
				return false;
			}else {
				return false;
			}
		}
		return true;
	}
	//댓글 수정 버튼 이벤트
	$(document).on('click', ".btn-comment-update", function(e){
		$('.comment-update-box').remove();
		let cm_num = $(this).data('num');
		let cm_content = $(this).parent().next().text();
		//수정 입력창과 수정완료 버튼을 추가
		var str = `
		<div class="comment-update-box">
		 	<textarea rows="" cols="" class="col-12 input-comment-update">\${cm_content}</textarea>
		 	<div class="text-right">
		 		<button class="btn btn-outline-success btn-comment-update-complete" data-num="\${cm_num}">수정완료</button>
		 		<button class="btn btn-outline-danger btn-comment-update-cancel">취소</button>
		 	</div>
		 </div>
		 `
		$('.comment-insert-box').after(str);
		//댓글 등록 입력창과 등록 버튼을 감춤
		$('.comment-insert-box').hide();
		
		
	});
	
	//댓글 수정취소 버튼 이벤트
	$(document).on('click', ".btn-comment-update-cancel", function(e){
		$('.comment-insert-box').show();
		$('.comment-update-box').remove();
	});
	//댓글 수정완료 버튼 이벤트
	$(document).on('click', ".btn-comment-update-complete", function(e){
		let cm_num = $(this).data('num');
		let cm_content = $('.input-comment-update').val();
		let obj = {
				cm_num : cm_num,
				cm_content : cm_content
		}
		$.ajax({
			url : '<c:url value="/comment/update"/>',
			method : "post", //원하는 방식 선택
			data : obj,
			success : function(data){
				if(data.result){
					alert('댓글을 수정했습니다..');
					getCommentList(cri);
				} else {
					alert('댓글을 수정하지 못했습니다.');
				}
				$('.comment-insert-box').show();
				$('.comment-update-box').remove();
			}, 
			error : function(xhr, status, error){
				console.log("error");
				console.log(xhr);
			}
		}); 
	});
	//댓글 삭제 버튼 이벤트
	$(document).on('click', ".btn-comment-delete", function(e){
		let obj = {
			cm_num : $(this).data('num')
		}
		$.ajax({
			url : '<c:url value="/comment/delete"/>',
			method : "post", //원하는 방식 선택
			data : obj,
			success : function(data){
				if(data.result){
					alert('댓글을 삭제했습니다.');
					cri.page = 1;
					getCommentList(cri);
				} else {
					alert('댓글을 삭제하지 못했습니다.');
				}
			}, 
			error : function(xhr, status, error){
				console.log("error");
				console.log(xhr);
			}
		});
	});
	
	//댓글 등록
	$('.btn-comment-insert').click(function(e){
		//로그인 안한 비회원을 위한 안내 작업
		if(!checkLogin()){
			return;
		}
		//댓글, 댓글 번호
		let content = $('.input-comment-insert').val();
		let po_num = '${post.po_num}';
		let ori_num = 0;
		
		if(content.trim() == ''){
			alert('댓글을 입력하세요');
			$('.input-comment-insert').focus();
			return;
		}
		
		let obj = {
				cm_content : content,
				cm_po_num : po_num,
				cm_ori_num : ori_num
		};
		$.ajax({
			url : '<c:url value="/comment/insert"/>',
			method : "post", //원하는 방식 선택
			data : obj,
			success : function(data){
				if(data.result){
					alert('댓글을 등록했습니다.');
					cri.page = 1;
					getCommentList(cri);
				} else {
					alert('댓글을 등록하지 못했습니다.');
				}
				$('.input-comment-insert').val('');
			}, 
			error : function(xhr, status, error){
				console.log("error");
				console.log(xhr);
			}
		});
	});
	//추천/비추천 버튼 클릭 이벤트
	$('.btn-up, .btn-down').click(function(e){
		e.preventDefault();
		
		if(!checkLogin()){
			return;
		}
		
		let state = $(this).data('state');
		let num = '${post.po_num}';
		$.ajax({
			url : '<c:url value="/post/recommend"/>',
			method : "get", //원하는 방식 선택
			data : { //보낸 데이터를 객체로
				state : state,
				po_num : num
			},
			success : function(data){
				let res = data.result;
				if(res == 1){
					alert('추천 했습니다.');
				}else if(res == -1){
					alert('비추천 했습니다.');
				}else{
					alert(`\${state == 1? '추천' : '비추천'}을 취소했습니다.`);
				}
				checkRecommendBtns(res);
				let post = JSON.parse(data.post);
				$('.btn-up span').text(post.po_up);
				$('.btn-down span').text(post.po_down);
			}, 
			error : function(xhr, status, error){
				//xhr : XHLHttpRequest 객체, 요청과 관련된 정보를 제공
				//status :HTTP 상태 코드, 요청이 실패한 원인
				//error : 에러에 대한 추가 정보
				console.log("error");
			}
		});
	});
	//댓글 페이지네이션 클릭 이벤트
	$(document).on('click', ".pagination .page-item", function(){
		if($(this).hasClass('disabled')){
			return;
		}
		let page = $(this).data('page');
		cri.page = page;
		getCommentList(cri);
	});
	
	//해당 게시글이 추천/비추천에 따라 각 버튼의 색상을 채워주는 함수
	function checkRecommendBtns(state){
		$('.btn-up').removeClass('btn-primary');
		$('.btn-down').removeClass('btn-danger');
		$('.btn-up').addClass('btn-outline-primary');
		$('.btn-down').addClass('btn-outline-danger');
		if(state != 0){
			if(state==1){
				$('.btn-up').removeClass('btn-outline-primary');
				$('.btn-up').addClass('btn-primary');
			} else {
				$('.btn-down').removeClass('btn-outline-danger');
				$('.btn-down').addClass('btn-danger');
			}
		}
	}
	
	//댓글 목록 가져오기
	
	function getCommentList(cri){
		//로그인 안하면 댓글등록화면 안보이게
		/*if('${user.me_id}' == ''){
			$('.comment-insert-box').addClass('hide');
		}else {
			$('.comment-insert-box').removeClass('hide');
		}*/
		$.ajax({
			url : '<c:url value="/comment/list"/>',
			method : "post", //원하는 방식 선택
			data : cri,
			success : function(data){
				let list = data.list;
				displayCommentList(list);
				let pm = JSON.parse(data.pm);
				displayPagination(pm);
			}, 
			error : function(xhr, status, error){
				//xhr : XHLHttpRequest 객체, 요청과 관련된 정보를 제공
				//status :HTTP 상태 코드, 요청이 실패한 원인
				//error : 에러에 대한 추가 정보
				console.log("에러 발생");
				console.log(xhr);
			}
		});
	}
	function displayPagination(pm){
		if(pm.totalCount == 0){
			return;
		}
		str = `
		<ul class="pagination justify-content-center">`
		var disabled = pm.prev ? '' : 'disabled';
			str +=
			`<li class="page-item \${disabled}" data-page="\${pm.startPage-1}">
	   			<a class="page-link" href="javascript:void(0);">이전</a>
		    </li>`;
		
		for(var i = pm.startPage; i<= pm.endPage; i++){
			var active = pm.cri.page == i ? 'active' : '';
			str +=
			`<li class="page-item \${active}" data-page="\${i}">
	   	 		<a class="page-link" href="javascript:void(0);">\${i}</a>
		    </li>`;
		}
		var disabled = pm.next ? '' : 'disabled';
			str +=
			`<li class="page-item \${disabled}" data-page="\${pm.endPage+1}">
   		 		<a class="page-link" href="javascript:void(0);">다음</a>
	    	</li>`;
		str +=		    
	  	`</ul>`;
	 	$('.comment-pagination').html(str);
	}
	
	function displayCommentList(list){
		var str = '';
		
		if(list.length == 0){
			str = `<li>등록된 댓글이 없습니다.</li>`;
			$('.comment-list').html(str);
			return;
		}
		
		for(co of list){
			var btns = '';
			if('${user.me_id}' == co.cm_me_id){
				btns += `<a href="javascript:void(0);" class="btn-comment-delete" data-num="\${co.cm_num}">x</a>`;
				btns += ` <a href="javascript:void(0);" class="btn-comment-update" data-num="\${co.cm_num}">수정</a>`;
			}
				//댓글이면
				if(co.cm_num == co.cm_ori_num){
					str +=`
					<li class="comment-item">
						<div>
							<span>\${co.cm_me_id} (\${co.cm_date})</span> 
							\${btns}
						</div>
				 		<div class="cm_content">\${co.cm_content}</div>
				 	</li>`;
				} 
				//대댓이면
				else {
					str +=`				
				 	<li class="comment-item reply">
						<div>
							<span>\${co.cm_me_id} (\${co.cm_date})</span> 
							\${btns}
						</div>
				 		<div class="cm_content">\${co.cm_content}</div>
				 	</li>
				 	`;
				}
			
		}
		$('.comment-list').html(str);
	}
	
	// 삭제시 비번 입력 받음
	$('#delete').click(function(e){
		e.preventDefault();
		var pw = $('[name=pw]').val();
		if(pw.length != 0){
			$('#delete').prop('href','<c:url value="/post/delete?co_num=${post.po_co_num}&po_num=${post.po_num}&pw='+pw+'"/>' );
			
		} else {
		$('[name=pw]').toggleClass('hide');
		}
	});
		
		
</script>
</body>
</html>