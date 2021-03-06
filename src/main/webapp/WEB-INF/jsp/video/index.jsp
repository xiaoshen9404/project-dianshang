<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@ include file="/WEB-INF/include/header.jsp"%>
</head>
<body>
	<%@ include file="/WEB-INF/include/nav.jsp"%>
	<div class="container">
		
		<div class="row">
			<div class="col-md-9 col-sm-9">
				<div class="panel panel-default">
					<div class="panel-heading">
						<c:forEach items="${types}" var="type">
							<c:choose>
								<c:when test="${type.value==currentType }">
									<a type="button" class="btn btn-primary">${type.value}</a>
								</c:when>
								<c:otherwise>
									<a type="button" class="btn btn-link">${type.value}</a>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</div>
					<div class="panel-body">
						<tags:pager page="${page}" types="${types}"></tags:pager>
					</div>
					<div class="panel-footer"></div>
				</div>
			</div>
			<div class="col-md-3 col-sm-3 col-xs-12">
				<!-- 个人面板 -->
				<c:if test="${not empty sessionScope.user  }">
					<div class="panel panel-default">
						<div class="panel-heading">个人信息</div>
						<div class="panel-body">
							<img src="/wp-content/uploads/2014/06/download.png" 
   								class="img-thumbnail">
   							${sessionScope.user.username}
						</div>
					</div>
				</c:if>
				<div class="panel panel-default">
					<div class="panel-body">
						<a href='<c:url value="/post/create"/>' type="button" class="btn btn-primary">
							发布帖子 </a>
					</div>
				</div>
			</div>
		</div>

	</div>
	
</body>
</html>