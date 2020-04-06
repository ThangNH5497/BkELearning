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
<link rel="stylesheet"
	href="<c:url value='/resources/manager/teacher/css/course-management.css' />">
<!-- Phan noi dung trang -->
<div class="wrapper d-flex align-items-stretch">

	<jsp:include page="/WEB-INF/views/manager/teacher/menu.jsp"></jsp:include>
	<!-- Page Content  -->
	<div id="content" class="mb-5">
		<div class=" container-fluid content-container">
			<div class=" container-fluid d-flex pl-0 ml-0">

				<nav class="navbar navbar-light bg-light pl-0 ml-0">

					<button type="button" id="sidebarCollapse" class="btn btn-primary">
						<i class="fa fa-bars"></i> <span class="sr-only">Toggle
							Menu</span>
					</button>
				</nav>
				<nav class="navbar navbar-light bg-light" style="font-weight: 600;"
					id="link-current">

					<span class="navbar-text"> <i class="fas fa-link"></i> <a
						class='text-primary' href="<c:url value='/admin/ql-mon-hoc' />">Quản
							Lý Môn Học</a><span> / Danh Sách Lớp</span>
					</span>
				</nav>
				<nav class="navbar navbar-light bg-light hidden"
					style="font-weight: 600;" id='link-back-search'>
					<span class="navbar-text"> <i class="fas fa-link"></i> <a
						class='text-primary' href="<c:url value='/admin/ql-mon-hoc' />">Quản
							Lý Môn Học</a><a class='text-primary'
						href="javascript:location.reload(true)"> / Danh Sách Lớp</a><span>
							/ Tìm Kiếm</span>
					</span>
				</nav>
			</div>

			<nav
				class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">

				<!-- Sidebar Toggle (Topbar) -->
				<button id="sidebarToggleTop"
					class="btn btn-link d-md-none rounded-circle mr-3">
					<i class="fa fa-bars"></i>
				</button>
				<!-- Topbar Search -->

				<form style="border: none !important;"
					class="d-flex d-sm-inline-block form-inline  ml-md-3 my-2 my-md-0 mw-100 navbar-search">
					<div class="input-group ">
						<input type="text" class="form-control border-0  small"
							style="border: none !important;" placeholder="Nhập Mã Lớp..."
							aria-label="Search" aria-describedby="basic-addon2"
							id='key-search'>

						<div class="input-group-append border-0">
							<button class="btn btn-primary" type="button" id='btn-search'>
								<i class="fas fa-search fa-sm"></i>
							</button>
						</div>
					</div>

				</form>

				<!-- type search radio -->
				<!-- end type search radio -->
				<div class="d-flex justify-content-end w-100">
					<button class="btn btn-refresh text-primary btn-control">
						<i class="fas fa-sync"></i>
					</button>

				</div>
				<div class="topbar-divider d-none d-sm-block"></div>
			</nav>
			<!-- End of Topbar -->

			<div class="row " id="table-data-body">
				<!-- Area Chart -->
				<div class="col-xl-12 col-lg-12 card-container mb-4 hidden"
					id="row-data-container">
					<div class="card  h-100">
						<!-- Card Header - Dropdown -->
						<div
							class=" card-header py-3 d-flex flex-row align-items-center justify-content-between">
							<h5 class="m-0 font-weight-bold text-primary" field="code"></h5>
							<div class="d-flex justify-content-end w-50">

								<button class="btn btn-add-teacher text-primary btn-control"
									data-toggle="modal" data-target="#modal-teacher">
									<i class="fas fa-sliders-h"></i>
								</button>
								


							</div>
						</div>
						<!-- Card Body -->
						<div class="card-body">
							<div class="container h-100">
								<div class=" row h-25 d-flex">
									<div class="col-2 align-self-center">
										<h6 class="m-0 font-weight-bold text-secondary">Mã Học Phần</h6>
									</div>
									<div class="col-9 align-self-center">
										<h6 class="m-0 font-weight-bold text-primary"
											field="subject.code"></h6>
									</div>
									<div class="col-1 justify-content-center align-self-center">

									</div>
								</div>

								<div class=" row h-25 d-flex">
									<div class="col-2 align-self-center">
										<h6 class="m-0 font-weight-bold text-secondary">Tên Học Phần</h6>
									</div>
									<div class="col-9 align-self-center">
										<h6 class="m-0 font-weight-bold text-primary"
											field="subject.subjectName"></h6>
									</div>
									<div class="col-1 justify-content-center align-self-center">
									</div>
								</div>
								<div class=" row h-25 d-flex">
									<div class="col-2 align-self-center">
										<h6 class="m-0 font-weight-bold text-secondary">Tên lớp</h6>
									</div>
									<div class="col-9 align-self-center">
										<h6 class="m-0 font-weight-bold text-primary"
											field="courseName"></h6>
									</div>
									<div class="col-1 justify-content-center align-self-center">
									</div>
								</div>
								<div class=" row h-25 d-flex">
									<div class="col-2 align-self-center">
										<h6 class="m-0 font-weight-bold text-secondary">Mô Tả</h6>
									</div>
									<div class="col-9 align-self-center">
										<h6 class="m-0 font-weight-bold text-primary"
											field="descriptor"></h6>
									</div>
									<div class="col-1 justify-content-center align-self-center">
									</div>
								</div>
							</div>
						</div>

					</div>
				</div>
			</div>
		</div>
		<div class="row  w-100 justify-content-end">
			<nav aria-label="Page navigation">

				<ul class="pagination justify-content-end">

					<li class="page-item disabled"><a class="page-link"
						id="pagination-title">Hiển Thị 0-0/0</a></li>
					<li class="page-item disabled" id="prePage"><a
						class="page-link" tabindex="-1">Previous</a></li>
					<div id="pagination" style="display: flex;">
						<li class="page-item page-item-number active "><a
							class="page-link">1</a></li>
						<li class="page-item page-item-number hidden"><a
							class="page-link">2</a></li>
						<li class="page-item page-item-number hidden"><a
							class="page-link">3</a></li>

						<li class="page-item page-item-number hidden"><a
							class="page-link">4</a></li>
						<li class="page-item page-item-number hidden"><a
							class="page-link">5</a></li>
					</div>

					<li class="page-item disabled" id="nextPage"><a
						class="page-link">Next</a></li>
				</ul>
			</nav>
		</div>
	</div>

</div>


<!-- modal add new Class-->

<div class="modal fade" id="modal-add-new" tabindex="-1" role="dialog"
	aria-labelledby="edit" aria-hidden="true">
	<div class="modal-dialog modal-xl">
		<div class="modal-content">
			<!--Header-->
			<div class="modal-header">
				<p class="heading lead">Thêm Lớp Học</p>

				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true" class="white-text">×</span>
				</button>
			</div>

			<!--Body-->
			<div class="modal-body">
				<div class="form-container">
					<div class="form-v4">
						<div class="form-v4-content">
							<form class="form-detail" action="#" method="post"
								id="form-add-new">
								<h2>Thông Tin Lớp</h2>
								<div class="form-group">
									<div class="form-row form-row-1">
										<label for="code">Mã Lớp Học (*)</label> <input type="text"
											name="code" class="input-text" required> <label
											name="code-error" class="error hidden">Mã Môn Học Tồn
											Tại</label>
									</div>
									<div class="form-row form-row-1">
										<label for="subjectName">Tên Lớp Học (*)</label> <input
											type="text" name="courseName" class="input-text" required>
									</div>
								</div>

								<div class="form-row">
									<label for="descriptor">Mô Tả</label> <input type="text"
										name="descriptor" class=" input-text">

								</div>

							</form>
						</div>
					</div>
					<!--Footer-->
					<div class="border-top my-3"></div>
					<div class="d-flex justify-content-center">
						<a type="button" class="btn  waves-effect btn-cancel"
							data-dismiss="modal">Hủy</a> <a type="button"
							class="btn  waves-effect btn-submit">Thêm</a>
					</div>
				</div>
			</div>

		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>
<!-- modal edit -->

<div class="modal fade" id="modal-edit" tabindex="-1" role="dialog"
	aria-labelledby="edit" aria-hidden="true">
	<div class="modal-dialog modal-xl">
		<div class="modal-content">
			<!--Header-->
			<div class="modal-header">
				<p class="heading lead">Sửa Thông Tin Lớp</p>

				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true" class="white-text">×</span>
				</button>
			</div>

			<!--Body-->
			<div class="modal-body">
				<div class="form-container">
					<div class="form-v4">
						<div class="form-v4-content">
							<form class="form-detail" action="#" method="post" id="form-edit">
								<h2>Sửa Thông Tin Lớp</h2>
								<div class="form-group">
									<div class="form-row form-row-1">
										<label for="code">Mã Lớp (*)</label> <input type="text"
											name="code" class="input-text" required> <label
											name="code-error" class="error hidden">Mã Lớp Tồn Tại</label>
									</div>
									<div class="form-row form-row-1">
										<label for="subjectName">Tên Lớp (*)</label> <input
											type="text" name="courseName" class="input-text" required>
									</div>
								</div>

								<div class="form-row">
									<label for="descriptor">Mô Tả</label> <input type="text"
										name="descriptor" class=" input-text">
								</div>
							</form>
						</div>
					</div>
					<!--Footer-->
					<div class="border-top my-3"></div>
					<div class="d-flex justify-content-center">
						<a type="button" class="btn  waves-effect btn-cancel"
							data-dismiss="modal">Hủy</a> <a type="button"
							class="btn  waves-effect btn-submit" disabled>Lưu</a>
					</div>
				</div>
			</div>

		</div>
		
	</div>
	
</div>

<jsp:include page="/WEB-INF/views/commons/lib.jsp"></jsp:include>
<script src="<c:url value='/resources/commons/js/base.js' />"></script>
<script src="<c:url value='/resources/manager/commons/js/search.js' />"></script>
<script src="<c:url value='/resources/commons/js/pagination.js' />"></script>
<script
	src="<c:url value='/resources/manager/teacher/js/course-management.js' />"></script>
<!-- Footer -->
<script type="text/javascript">
	(function($) {

		"use strict";

		var fullHeight = function() {

			$('.js-fullheight').css('height', $(window).height());
			$(window).resize(function() {
				$('.js-fullheight').css('height', $(window).height());
			});

		};
		fullHeight();

		$('#sidebarCollapse').on('click', function() {
			$('#sidebar').toggleClass('active');

		});

	})(jQuery);
</script>

<jsp:include page="/WEB-INF/views/commons/footer.jsp"></jsp:include>