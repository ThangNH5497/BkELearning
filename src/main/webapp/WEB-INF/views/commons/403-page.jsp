<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Từ chối truy cập</title>
<link rel="stylesheet"
	href="<c:url value='/resources/commons/lib/bootstrap-4.4.1/css/bootstrap.min.css' /> ">
<link rel="stylesheet"
	href="<c:url value='/resources/commons/lib/fontawesome5.12.0/css/all.css' />">
</head>
<body>
	<div class="container py-5">
		<div class="row">
			<div class="col-md-2 text-center">
				<p>
					<i class="fa fa-exclamation-triangle fa-5x"></i><br />Status Code:
					403
				</p>
			</div>
			<div class="col-md-10">
				<h3>OPPSSS!!!! Sorry...</h3>
				<p>
					Truy cập bị từ chối do quyền hạn của bạn không cho phép.<br />Xin
					vui lòng trở lại trang trước hoặc nhấn nút dưới để về trang chủ.
				</p>
				<a class="btn btn-danger" href="<c:url value='/trang-chu' />">Trang
					chủ</a>
			</div>
		</div>
	</div>
	<jsp:include page="/WEB-INF/views/commons/lib.jsp"></jsp:include>
	<script src="<c:url value='/resources/commons/js/base.js' />"></script>
</body>
</html>