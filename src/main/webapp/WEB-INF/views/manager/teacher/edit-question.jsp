<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="/WEB-INF/views/manager/commons/header.jsp"></jsp:include>
<link rel="stylesheet"
	href="<c:url value='/resources/commons/css/style.css' />">
<link rel="stylesheet"
	href="<c:url value='/resources/manager/commons/css/style.css' />">
<link rel="stylesheet"
	href="<c:url value='/resources/manager/commons/css/dashboard.css' />">
<link rel="stylesheet"
	href="<c:url value='/resources/commons/lib/summernote/summernote-audio.css' />">
<link rel="stylesheet"
	href="<c:url value='/resources/commons/lib/summernote/summernote-bs4.min.css' />">
<link rel="stylesheet"
	href="<c:url value='/resources/manager/commons/css/add-question.css' />">

<!-- Phan noi dung trang -->
<div class="wrapper d-flex align-items-stretch">
	<jsp:include page="/WEB-INF/views/manager/teacher/menu.jsp"></jsp:include>
<jsp:include page="/WEB-INF/views/manager/commons/edit-question.jsp"></jsp:include>
<jsp:include page="/WEB-INF/views/commons/lib.jsp"></jsp:include>
<script src="<c:url value='/resources/commons/js/base.js' />"></script>
<script src="<c:url value='/resources/commons/js/pagination.js' />"></script>
<script src="<c:url value='/resources/manager/commons/js/search.js' />"></script>
<script
	src="<c:url value='/resources/commons/lib/summernote/summernote-bs4.min.js' />"></script>
<script
	src="<c:url value='/resources/commons/lib/summernote/summernote-bs4.min.js' />"></script>
<script
	src="<c:url value='/resources/commons/lib/summernote/summernote-audio.js' />"></script>
<script
	src="<c:url value='/resources/manager/commons/js/question-save-update-base.js' />"></script>
<script
	src="<c:url value='/resources/manager/teacher/js/edit-question.js' />"></script>


<!-- Footer -->

<jsp:include page="/WEB-INF/views/manager/commons/footer.jsp"></jsp:include>