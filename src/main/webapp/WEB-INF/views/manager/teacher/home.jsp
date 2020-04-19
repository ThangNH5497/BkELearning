<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="/WEB-INF/views/commons/header.jsp"></jsp:include>
<link rel="stylesheet"
	href="<c:url value='/resources/commons/css/style.css' />">
<link rel="stylesheet"
	href="<c:url value='/resources/manager/commons/css/style.css' />">
<link rel="stylesheet"
	href="<c:url value='/resources/manager/commons/css/dashboard.css' />">

<!-- Phan noi dung trang -->
<div class="wrapper d-flex align-items-stretch">
	<jsp:include page="/WEB-INF/views/manager/teacher/menu.jsp"></jsp:include>

	<!-- Page Content  -->
	<div id="content" class="mb-5"><jsp:include
			page="/WEB-INF/views/manager/commons/topbar.jsp"></jsp:include></div>

	<!-- end content -->
</div>

<jsp:include page="/WEB-INF/views/commons/lib.jsp"></jsp:include>
<!-- Footer -->
<script src="<c:url value='/resources/commons/js/base.js' />"></script>
<script
	src="<c:url value='/resources/manager/teacher/js/home.js' />"></script>

<jsp:include page="/WEB-INF/views/commons/footer.jsp"></jsp:include>