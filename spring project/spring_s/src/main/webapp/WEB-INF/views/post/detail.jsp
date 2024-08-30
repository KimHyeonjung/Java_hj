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
					<span class="float-left" style="line-height: 38px;">아이디</span>
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
	</script>
</body>
</html>
