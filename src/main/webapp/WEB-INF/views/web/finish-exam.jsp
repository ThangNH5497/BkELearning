<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, minimum-scale=1.0">
<title>BkELearning</title>
<link rel="stylesheet"
	href="<c:url value='/resources/commons/lib/bootstrap-4.4.1/css/bootstrap.min.css' /> ">
<link rel="stylesheet"
	href="<c:url value='/resources/commons/lib/fontawesome5.12.0/css/all.css' />">
<link rel="stylesheet"
	href="<c:url value='/resources/web/css/finish-exam.css' />">
</head>
<body>
	<div id="main-page">
		<div class="main-container">
			<div class="main-header">
				<h2>Hoàn thành bài thi</h2>
			</div>

			<div class='container justify-content-center d-flex'>
				<img class="d-block img-fluid mx-auto"
					src="<c:url value='/resources/commons/image/web/congratulation.svg' />"
					alt="" style="width: 60%; margin-left: 20%;">
			</div>

			<div class='container justify-content-center d-flex mt-4 pt-4'>
				<a href="<c:url value='/trang-chu' />" class="btn btn-primary btn-icon-split"> <span
					class="icon text-white-50"> <i class="fas fa-flag"></i>
				</span> <span class="text">Về trang chủ</span>
				</a>
			</div>

		</div>
	</div>

	<jsp:include page="/WEB-INF/views/commons/lib.jsp"></jsp:include>
	<script src="<c:url value='/resources/commons/js/base.js' />"></script>

</body>
</html>