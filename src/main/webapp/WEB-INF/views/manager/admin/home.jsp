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

<!-- Phan noi dung trang -->
<div class="wrapper d-flex align-items-stretch">
	<jsp:include page="/WEB-INF/views/manager/admin/menu.jsp"></jsp:include>

	<!-- Page Content  -->
	<div id="content" class="mb-5">
		<jsp:include page="/WEB-INF/views/manager/commons/topbar.jsp"></jsp:include>
		<div class="container-fluid">
			<h1 class="h3 p-4 mb-1 text-gray-800 text-center">Hệ thống quản
				trị BkELearning</h1>

			<h3 class="h5  mb-1 text-center text-primary">Trang Admin</h3>

			<div class="row p-4">
				<div class="p-4 mb-4 col-md-6 col-sm-12 h-100">
					<div class="card shadow p-4 h-100">
						<div class="card-header py-3">
							<h6 class="m-0 font-weight-bold text-primary">Quản lý giảng
								viên</h6>
						</div>
						<div class="card-body">
							<div class="text-center">
								<img class="img-fluid px-3 px-sm-4 mt-3 mb-4"
									style="width: 25rem; height: 20rem;"
									src="<c:url value='/resources/commons/image/icon/account-1.svg' />"
									alt="">
							</div>
							<p>Quản lý các tài khoản giảng viên trong hệ thống</p>
							<a target="_blank" rel="nofollow"
								href="<c:url value='/admin/ql-giang-vien'/>">Đi đến →</a>
						</div>
					</div>
				</div>

				<div class="p-4 mb-4 col-md-6 col-sm-12 h-100">
					<div class="card shadow p-4 h-100">
						<div class="card-header py-3">
							<h6 class="m-0 font-weight-bold text-primary">Quản lý sinh viên</h6>
						</div>
						<div class="card-body">
							<div class="text-center">
								<img class="img-fluid px-3 px-sm-4 mt-3 mb-4"
									style="width: 25rem; height: 20rem;"
									src="<c:url value='/resources/commons/image/icon/account-2.svg' />"
									alt="">
							</div>
							<p>Quản lý các tài khoản sinh viên trong hệ thống</p>
							<a target="_blank" rel="nofollow"
								href="<c:url value='/admin/ql-sinh-vien'/>">Đi đến →</a>
						</div>
					</div>
				</div>
			</div>
			<div class="row p-4">
				<div class="p-4 mb-4 col-md-6 col-sm-12 h-100">
					<div class="card shadow p-4 h-100">
						<div class="card-header py-3">
							<h6 class="m-0 font-weight-bold text-primary">Quản lý môn học, lớp học, bài thi...</h6>
						</div>
						<div class="card-body">
							<div class="text-center">
								<img class="img-fluid px-3 px-sm-4 mt-3 mb-4"
									style="width: 25rem; height: 20rem;"
									src="<c:url value='/resources/commons/image/icon/exam.svg' />"
									alt="">
							</div>
							<p>Quản các môn học , lớp học, bài thi trong hệ thống</p>
							<a target="_blank" rel="nofollow"
								href="<c:url value='/admin/ql-mon-hoc'/>">Đi đến →</a>
						</div>
					</div>
				</div>

				<div class="p-4 mb-4 col-md-6 col-sm-12 h-100">
					<div class="card shadow p-4 h-100">
						<div class="card-header py-3">
							<h6 class="m-0 font-weight-bold text-primary">Quản hệ thống câu hỏi trong hệ thống</h6>
						</div>
						<div class="card-body">
							<div class="text-center">
								<img class="img-fluid px-3 px-sm-4 mt-3 mb-4"
									style="width: 25rem; height: 20rem;"
									src="<c:url value='/resources/commons/image/icon/question.svg' />"
									alt="">
							</div>
							<p>Quản Lý các kho câu hỏi cho các bài thi và môn học</p>
							<a target="_blank" rel="nofollow"
								href="<c:url value='/admin/ql-cau-hoi/kho-chung?subject=ALL&level=ALL&type=ALL'/>">Đi đến →</a>
						</div>
					</div>
				</div>
			</div>
			<!-- end content -->
		</div>
	</div>
</div>
<jsp:include page="/WEB-INF/views/commons/lib.jsp"></jsp:include>
<!-- Footer -->
<script src="<c:url value='/resources/commons/js/base.js' />"></script>
<script src="<c:url value='/resources/manager/admin/js/home.js' />"></script>


<jsp:include page="/WEB-INF/views/manager/commons/footer.jsp"></jsp:include>