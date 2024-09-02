<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>게시글 상세</title>
<style type="text/css">
.title {font-size: 30px; font-weight: bold;}
.writer{font-size: 15px; font-weight: bold; margin-top: 10px;}
.sub {width: 170px; font-size: 12px; display: flex; justify-content: space-between; 
	margin-left: 0; }
.content{min-height: 320px; height: auto; margin-top: 15px;}
.cm-id{font-weight: bold;}
.btn{font-size: 15px;}
</style>
</head>
<body>
	<c:if test="${post ne null }">
	<div class="mt-5">
		<div class="title">
			<span class="title" id="title">${post.po_title }</span>
		</div>	
		<div>
			<span class="writer" >${post.po_me_id }</span>
		</div>	
		<div class="sub">
			<span class="date" ><fmt:formatDate value="${post.po_date }" pattern="yyyy-MM-dd."/>
			</span>
			<span class="views" >조회수: ${post.po_views }</span>
		</div>
		<hr>
		<div class="content">
			<div class="" >${post.po_sub }</div>
		</div>
		<c:if test="${fl.size() != 0 }">
		<div class="file-list">
			<label>첨부파일:</label>
			<c:forEach items="${fl }" var="file">
					<a style="margin-left: 5px;" class="" href="<c:url value="/download${file.fi_name }"/>" 
					download="${file.fi_ori_name }"> ${file.fi_ori_name } </a>
			</c:forEach>
		</div>
		</c:if>
	</div>
	</c:if>
	<c:if test="${post eq null }">
		<h3 class="mt-8">삭제 되거나 잘못된 게시글입니다.</h3>
	</c:if>
	<c:url var="url" value="/post/list">
		<c:param name="co_num" value="${post.po_co_num }" />
		<c:param name="page" value="${cri.page}" />
		<c:param name="type" value="${cri.type }" />
		<c:param name="search" value="${cri.search }" />
	</c:url> 
	<a href="${url }" class="btn btn-outline-success">목록</a>
	<c:if test="${post.po_me_id eq user.me_id }">
		<c:url var="url" value="/post/update">
			<c:param name="co_num" value="${post.po_co_num }" />
			<c:param name="po_num" value="${post.po_num }" />
			<c:param name="page" value="${cri.page}" />
			<c:param name="type" value="${cri.type }" />
			<c:param name="search" value="${cri.search }" />
		</c:url>
		<a href="${url}" class="btn btn-outline-primary">수정</a>
		<c:url var="url" value="/post/delete">
			<c:param name="co_num" value="${post.po_co_num }" />
			<c:param name="po_num" value="${post.po_num }" />
			<c:param name="page" value="${cri.page}" />
			<c:param name="type" value="${cri.type }" />
			<c:param name="search" value="${cri.search }" />
		</c:url>
		<a href="${url}" class="btn btn-outline-danger">삭제</a>
	</c:if>
	<hr>
	<div class="comment-container">
		<ul class="comment-list" style="list-style: none; padding: 0">
			<li class="comment-item">
				<div class="clearfix">
					<span class="float-left cm-id" style="line-height: 36px;">아이디</span>
					<div class="float-right">
						<button class="btn btn-outline-info">수정</button>
						<button class="btn btn-outline-danger">삭제</button>
					</div>
				</div>
				<div style="padding-left: 20px; line-height: 38px;">댓글내용</div>
			</li>
		</ul>
		<div class="comment-pagination">
			<ul class="pagination justify-content-center">
			    <li class="page-item"><a class="page-link" href="javascript:void(0);">이전</a></li>
			    <li class="page-item"><a class="page-link" href="javascript:void(0);">1</a></li>
			    <li class="page-item"><a class="page-link" href="javascript:void(0);">다음</a></li>
			  </ul>
		</div>
		<div class="comment-input-box">
			<div class="input-group mb-3">
			    <textarea type="text" class="form-control" id="input-comment" placeholder="댓글 입력"></textarea>
			    <div class="input-group-append">
			      <span class="input-group-text btn-insert" style="cursor: pointer;">등록</span>
			    </div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
	let cri = {
		page : 1,
		search : '${post.po_num}'
	}
	function checkLogin(){
		return '${user.me_id}' != '';
	}
	function alertLogin(){
		if(checkLogin()){
			return false;
		}
		if(confirm('로그인이 필요한 서비스입니다.\n로그인 하시겠습니까?')){
			location.href="<c:url value="/login"/>";
		}
		return true;
	}
	//댓글 목록을 가져와서 화면에 뿌려주는 함수
	function getCommentList(cri){
		$.ajax({
			async : true, //비동기 : true(비동기), false(동기)
			url : '<c:url value="/comment/list"/>', 
			type : 'post', 
			data : JSON.stringify(cri),
			contentType : "application/json; charset=utf-8",
			dataType : "json", 
			success : function (data){
				//댓글 목록을 화면에 출력
				displayCommentList(data.list);
				//페이지네이션을 화면에 출력
				displayPagination(data.pm)
			}, 
			error : function(jqXHR, textStatus, errorThrown){
				console.log(jqXHR);
			}
		});
	}
	function getCommentList2(cri){
		$.ajax({
			async : true, //비동기 : true(비동기), false(동기)
			url : '<c:url value="/comment/list2"/>', 
			type : 'post', 
			data : JSON.stringify(cri),
			contentType : "application/json; charset=utf-8",
			success : function (data){
				$('.comment-container').html(data);
			}, 
			error : function(jqXHR, textStatus, errorThrown){
				console.log(jqXHR);
			}
		});
	}	
	//댓글 목록이 주어지면 화면에 출력하는 함수
	function displayCommentList(list){
		if(list == null || list.length == 0){
			$('.comment-list').html('<li class="comment-item display-4">등록된 댓글이 없습니다.</li>');
			return;
		}
		var str = '';
		for(comment of list){
			var btns = '';
			if(comment.cm_me_id == '${user.me_id}'){
				btns = `
					<div class="float-right">
						<button class="btn btn-outline-info">수정</button>
						<button class="btn btn-outline-danger">삭제</button>
					</div>
				`
			}
			str += `
				<li class="comment-item">
					<div class="clearfix">
						<span class="float-left" style="line-height: 38px;">\${comment.cm_me_id}</span>
						\${btns}
					</div>
					<div style="padding-left: 20px; line-height: 38px;">\${comment.cm_content}</div>
				</li>
			`;
		}
		$('.comment-list').html(str);
	}
	//댓글의 페이지네이션을 화면에 출력하는 함수
	function displayPagination(pm){
		if(pm == null || pm.endPage == 0){
			return;
		}
		var str = '';
		if(pm.prev){
			str += `
				<li class="page-item" data-page="\${pm.startPage - 1}"><a class="page-link" href="javascript:void(0);">이전</a></li>
			`
		}
		for(var i = pm.startPage; i <= pm.endPage; i++){
			var active = pm.cri.page == i ? 'active' : '';
			str += `
			    <li class="page-item \${active}" data-page="\${i}"><a class="page-link" href="javascript:void(0);">\${i}</a></li>
			`
		}
		if(pm.next){
			str += `
			    <li class="page-item" data-page="\${pm.endPage + 1}"><a class="page-link" href="javascript:void(0);">다음</a></li>
			`
		}
		$('.comment-pagination>.pagination').html(str);
	}
	$(document).on('click', '.comment-pagination .page-item', function(){
		cri.page = $(this).data('page');
		getCommentList2(cri);
	});
	
	getCommentList2(cri)
	
	//댓글 등록을 클릭하면 댓글을 등록
	$(document).on('click', '.btn-insert', function(){
		//로그인 확인
		if(alertLogin()){
			return;
		}
		//댓글 내용, 게시글 번호
		var cm_content = $('#input-comment').val();
		var cm_po_num = ${post.po_num};
		var comment = {
				cm_content : cm_content,
				cm_po_num : cm_po_num
		}
		if(cm_content.length == 0){
			alert('댓글을 입력하세요');
			$('#input-comment').focus();
			return;
		}
		//서버로 데이터를 전송해서 댓글을 등록하고 알림을 띄움
		$.ajax({
			async : true, //비동기 : true(비동기), false(동기)
			url : '<c:url value="/comment/insert"/>', 
			type : 'post', 
			data : JSON.stringify(comment), 
			contentType : "application/json; charset=utf-8",
			success : function (data){
				if(data){
					alert('댓글을 등록했습니다.');
					$('#input-comment').val('');
				}else {
					alert('댓글을 등록하지 못했습니다.')
				}
				//댓글 목록을 다시 불러옴
				getCommentList2(cri)
			}, 
			error : function(jqXHR, textStatus, errorThrown){
				console.log(jqXHR);
			}
		});
	});
	/*
	detail.jsp
	1. 댓글 번호를 알아야 삭제할 수 있음
	- 삭제 버튼을 눌렀을 때 댓글 번호를 알아야 함
		=> 삭제 버튼에 댓글번호를 추가
		=> data-xxx
	2. 삭제 버튼을 클릭 했을 때 댓글 번호를 서버에(컨트롤러) 주면서 삭제하고 삭제 여부를 알려달라고 요청
		=> 삭제할 댓글 번호를 가져옴. $('삭제버튼 선택자').data('xxx')
		=> ajax로 댓글 번호를 전송
		=> ajax 성공 시 성공 여부에 따라 알림을 출력하고 댓글을 새로고침
		
	컨트롤러
	1. 댓글 삭제를 위한 메소드를 추가
		=> @RequestMapping or @GetMapping or @PostMapping 중 하나를 선택해서 메소드 위에 추가
		=> @ResponseBody를 추가
	2. 메소드에서 기능을 구현
		=> 화면에서 보낸 정보를 가져옴(댓글 번호)
		=> 서비스에게 삭제할 댓글 번호와 로그인한 사용자 정보를 주면서 삭제하라고 요청하고 결과를 저장
		=> 삭제 성공 여부를 화면에 전달
	서비스
	1. 사용자가 없으면 false를 반환
	2. 댓글을 삭제후 결과를 반환
	다오/매퍼
	1. 댓글을 삭제하는 쿼리를 구현
	*/
	$(document).on('click','.btn-cm-delete', function(){
		var cm_num = $(this).data('num');
		commentDel3(cm_num);
		/* 내가 한거
		$.ajax({
			async : true, //비동기 : true(비동기), false(동기)
			url : '<c:url value="/comment/delete"/>', 
			type : 'post', 
			data : {cm_num : cm_num}, 
			success : function (data){
				if(data){
					alert('댓글을 삭제하였습니다.');
				}else {
					alert('댓글을 삭제하지 못했습니다.');
				}
				getCommentList2(cri)
			}, 
			error : function(jqXHR, textStatus, errorThrown){
				console.log(jqXHR);
			}
		});
		 */
	});
	function commentDel1(cm_num){
		//json으로 화면에서 서버로 전송 => 서버에서 화면으로 json으로 전송
		let comment = {
				cm_num : cm_num
		}
		$.ajax({
			async : true, //비동기 : true(비동기), false(동기)
			url : '<c:url value="/comment/delete1"/>', 
			type : 'post', 
			data : JSON.stringify(comment), 
			contentType : "application/json; charset=utf-8",
			dataType : "json", 
			success : function (data){
				if(data.res){
					alert('댓글을 삭제했습니다.');
				}else {
					alert('댓글을 삭제하지 못했습니다.');
				}
				getCommentList2(cri);
			}, 
			error : function(jqXHR, textStatus, errorThrown){

			}
		});
	}
	function commentDel2(cm_num){
		//Object으로 화면에서 서버로 전송 => 서버에서 화면으로 json으로 전송
		let comment = {
				cm_num : cm_num
		}
		$.ajax({
			async : true, //비동기 : true(비동기), false(동기)
			url : '<c:url value="/comment/delete2"/>', 
			type : 'post', 
			data : comment, 
			dataType : "json", 
			success : function (data){
				if(data.res){
					alert('댓글을 삭제했습니다.');
				}else {
					alert('댓글을 삭제하지 못했습니다.');
				}
				getCommentList2(cri);
			}, 
			error : function(jqXHR, textStatus, errorThrown){

			}
		});
	}
	function commentDel3(cm_num){
		let comment = {
			cm_num : cm_num
		}
		$.ajax({
			async : true, //비동기 : true(비동기), false(동기)
			url : '<c:url value="/comment/delete3"/>', 
			type : 'post', 
			data : comment, 
			success : function (data){
				if(data){
					alert('댓글을 삭제했습니다.');
				}else {
					alert('댓글을 삭제하지 못했습니다.');
				}
				getCommentList2(cri);
			}, 
			error : function(jqXHR, textStatus, errorThrown){

			}
		});
	}
	/* 
	pagination.jsp
	1. 수정 버튼에 data-xxx을 추가해서 댓글 번호를 추가
	2. 수정(댓글 옆) 버튼 클릭이벤트를 등록
		- 댓글 번호를 가져옴
		- 현재 선택한 댓글의 댓글 내용을 가져옴
		- 댓글 번호와 댓글 내용을 이용해서 문자열로 된 html코드를 구성
			=> 현재 댓글이 들어간 textarea태그를 포함한 html 코드
		- 기존에 있는 댓글 등록 화면을 감춤
		- 기존에 있는 댓글 등록 화면 뒤에 위에서 만든 html 코드를 추가
	3. 수정(댓글 수정창 옆) 버튼 클릭 이벤트 등록
		- 댓글 번호를 가져옴
		- 댓글 내용을 가져옴
		- 서버에 전송해서 수정을 요청
		- 성공 여부에 따라 알림을 출력
	컨트롤러
	1. 화면에서 보낸 댓글번호, 댓글 내용을 가져옴
	2. 로그인한 사용자를 가져옴
	3. 서비스에게 댓글번호, 내용, 사용자를 주면서 수정하라고 요청
	4. 수정 여부를 화면에 전달
	*/
	
	</script>
</body>
</html>
