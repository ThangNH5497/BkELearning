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
	href="<c:url value='/resources/manager/commons/css/add-question.css' />">
<link rel="stylesheet"
	href="<c:url value='/resources/commons/lib/bootstrap-datetimepicker/css/bootstrap-datetimepicker.css' /> ">

<!-- Phan noi dung trang -->
<div class="wrapper d-flex align-items-stretch">
	<jsp:include page="/WEB-INF/views/manager/teacher/menu.jsp"></jsp:include>
	<!-- Page Content  -->
	<div id="content" class="mb-5">
		<nav
			class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">

			<div class="custom-menu">
				<button id="sidebarCollapse"
					class="btn btn-link rounded-circle mr-3">
					<i class="fa fa-bars"></i>
				</button>
			</div>
			<ul class="navbar-nav ml-auto">
				<div class="topbar-divider d-none d-sm-block"></div>

				<!-- Nav Item - User Information -->
				<li class="nav-item dropdown no-arrow"><a
					class="nav-link dropdown-toggle" href="#" id="userDropdown"
					data-toggle="dropdown"><img
						style="max-height: 60px; max-width: 60px;"
						class="img-profile rounded-circle"
						src="<c:url value='/resources/commons/image/user/default-user.jpg' />">
						<span
						class="mr-2 d-none d-lg-inline text-gray-600 small user-full-name">Tài
							Khoản</span> </a> <!-- Dropdown - User Information -->
					<div
						class="dropdown-menu dropdown-menu-right shadow animated--grow-in"
						aria-labelledby="userDropdown">
						<a class="dropdown-item" href="#"> <i
							class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i>Cập Nhật
							Thông Tin
						</a> <a class="dropdown-item" href="#"> <i
							class="fas fa-cogs fa-sm fa-fw mr-2 text-gray-400"></i> Đổi Mật
							Khẩu
						</a>
						<div class="dropdown-divider"></div>
						<a class="dropdown-item" href="<c:url value='/logout'/>"> <i
							class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
							Logout
						</a>
					</div></li>

			</ul>
		</nav>

		<div class="container-fluid">
			<div
				class="d-sm-flex align-items-center justify-content-between mb-4">
				<h1 class="h4 mb-0 text-gray-600">Thêm Bài Thi</h1>
			</div>
			<!-- filter -->
			<div class="card mb-4 py-3 border-left-info" id="exampaper-info">
				<div class="card-body">
					<h5 class="mb-4 font-weight-bold text-primary col-12 text-center">Thông
						Tin Bài Thi</h5>
					<div id="">
						<div class="row">
							<label class='col-2 col-form-label'>Môn Học (*)</label>
							<div class="input-group col-9">
								<input class="form-control" type="text" subjectId=''
									id="input-subject" readonly required>

							</div>
						</div>
						<div class="col-1"></div>

					</div>

					<div id="" class="mt-3">
						<div class="row">
							<label class='col-2 col-form-label'>Lớp Học (*)</label>
							<div class="input-group col-9">
								<input class="form-control" type="text" courseId='' value=""
									id="input-course" readonly required>

							</div>
						</div>
						<div class="col-1"></div>

					</div>

					<form class=" ">
						<div class="form-group row mt-3">
							<label for="inputName" class="col-2 col-form-label">Mã
								Bài Thi (*)</label>
							<div class="col-9">
								<input type="text" class="form-control" id="input-code" required>
							</div>
							<div class="col-1"></div>
						</div>
					</form>

					<form class=" ">
						<div class="form-group row mt-3">
							<label for="inputName" class="col-2 col-form-label">Tên
								Bài Thi (*)</label>
							<div class="col-9">
								<input type="text" class="form-control" id="input-name" required>
							</div>
							<div class="col-1"></div>
						</div>
					</form>

					<form class=" ">
						<div class="form-group row mt-3">
							<label for="inputName" class="col-2 col-form-label">Thời
								Gian (*)</label>
							<div class="col-9">
								<input type="text" class="form-control" id="input-time" required>
							</div>
							<div class="col-1"></div>
						</div>
					</form>


					<div class="form-group row">
						<label for="example-datetime-local-input"
							class="col-2 col-form-label">Thời Gian Mở (*)</label>
						<div class="col-9">
							<div class="date form_datetime" id='time-open'>
								<input class="form-control" type="text" id='input-time-open'
									value="" readonly required> <span class="add-on"><i
									class="icon-th"></i></span>
							</div>
						</div>

					</div>
					
					<div class="form-group row">
						<label for="example-datetime-local-input"
							class="col-2 col-form-label">Thời Gian Đóng (*)</label>
						<div class="col-9">
							<div class="date form_datetime" id='time-close'>
								<input class="form-control" type="text" id='input-time-close'
									value="" readonly required> <span class="add-on"><i
									class="icon-th"></i></span>
							</div>
						</div>

					</div>


					<form>
						<div class="form-group row mt-3">
							<label for="inputName" class="col-2 col-form-label">Mô Tả</label>
							<div class="col-9">
								<textarea class="form-control" id="input-descriptor" rows="3"></textarea>
							</div>
							<div class="col-1"></div>
						</div>
					</form>

				</div>
			</div>
		</div>




		<!-- table -->

		<div class='row d-flex justify-content-center mt-4'>
			<button type="button" class="btn btn-primary btn-submit"
				id="btn-submit">Tạo Bài Thi</button>
		</div>
		<!-- end table -->
	</div>

</div>


<jsp:include page="/WEB-INF/views/commons/lib.jsp"></jsp:include>
<script
	src="<c:url value='/resources/commons/lib/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js' />"></script>
<script src="<c:url value='/resources/commons/js/base.js' />"></script>
<script src="<c:url value='/resources/commons/js/pagination.js' />"></script>
<script src="<c:url value='/resources/manager/commons/js/search.js' />"></script>
<script
	src="<c:url value='/resources/manager/teacher/js/add-exam.js' />"></script>
<!-- Footer -->

<jsp:include page="/WEB-INF/views/manager/commons/footer.jsp"></jsp:include>