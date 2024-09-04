<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<script>
	var msg = '${msg.msg}';
	if(msg != ''){
		alert(msg);
	}
	location.href='<c:url value="${msg.url}"/>';
</script>
