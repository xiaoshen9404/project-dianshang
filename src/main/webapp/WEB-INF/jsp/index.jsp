<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@ include file="/include/header.jsp"%>
</head>
<body>
<%@ include file="/include/nav.jsp" %>
<div class="container">
	<div class="jumbotron">
		<h1>welcome to my web</h1>
		<p>这是一个网站</p>
		<p>
			<a class="btn btn-primary btn-large" href='<c:url value="/login" />'>登录</a>
			<a class="btn btn-lg" >注册</a>
		</p>
	</div>
</div>
</body>
</html>