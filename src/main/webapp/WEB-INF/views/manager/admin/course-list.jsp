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
	href="<c:url value='/resources/manager/commons/css/course-list.css' />">
<!-- Phan noi dung trang -->
<div class="wrapper d-flex align-items-stretch">

	<jsp:include page="/WEB-INF/views/manager/admin/menu.jsp"></jsp:include>
	<!-- Page Content  -->
	<div id="content" class="mb-5">
		<jsp:include page="/WEB-INF/views/manager/commons/topbar.jsp"></jsp:include>
		<div class=" container-fluid content-container">
			<div class=" container-fluid d-flex pl-0 ml-0 row bg-light">
				<nav class="col-10 navbar navbar-light bg-light"
					style="font-weight: 600; font-size: max(1.2vw,12px);" id="link-current" >

					<span class="navbar-text"> <i class="fas fa-link"></i> <a 
						class='text-primary' href="<c:url value='/admin/ql-mon-hoc' />">Quản
							Lý Môn Học</a><span> / Danh Sách Lớp</span>
					</span>
				</nav>
				<nav class="col-10 navbar navbar-light bg-light hidden"
					style="font-weight: 600;" id='link-back-search'>
					<span class="navbar-text"> <i class="fas fa-link"></i> <a
						class='text-primary' href="<c:url value='/admin/ql-mon-hoc' />">Quản
							Lý Môn Học</a><a class='text-primary'
						href="javascript:location.reload(true)"> / Danh Sách Lớp</a><span>
							/ Tìm Kiếm</span>
					</span>
				</nav>
				<div class="d-flex justify-content-end col-2">
					<button class="btn btn-add text-success btn-control"
						data-toggle="modal" data-target="#modal-add-new">
						<i class="far fa-plus-square"></i>
					</button>
					<button class="btn btn-refresh text-primary btn-control">
						<i class="fas fa-sync"></i>
					</button>

				</div>
				<div class="topbar-divider d-none d-sm-block"></div>
			</div>
			<h5 id='data-empty-alert'
				class="hidden mt-3 mb-3 w-100 d-flex justify-content-center font-weight-bold text-primary">Không
				Có Dữ Liệu</h5>

			<div class="row " id="table-data-body">
				<!-- Area Chart -->
				<div class="col-xl-12 col-lg-12 data-container mb-4 hidden"
					id="row-data-container">
					<div
						class="w-100 card mb-4 py-3 border-left-primary pl-4 pt-0 pb-0"
						style="padding-top: 0 !important;">
						<div class="card-body pb-0 pt-3">
							<div class="row ">
								<div class="col-6  p-0 m-0" style="font-weight: 600;"
									id="link-current">
									<span class="navbar-text text-primary font-weight-bold d-flex">Mã
										Lớp
										<p field="code" class="pl-4">10000</p>
									</span>
								</div>
								<div class="d-flex justify-content-end col-6">

									<button class="btn btn-add-teacher text-success btn-control"
										data-toggle="modal" data-target="#modal-teacher">
										<i class="fas fa-user-plus"></i>
									</button>
									<button class="btn btn-remove-teacher text-danger btn-control">
										<i class="fas fa-user-minus"></i>
									</button>
									<button class="btn btn-edit text-primary btn-control"
										data-toggle="modal" data-target="#modal-edit">
										<i class="fas fa-edit"></i>
									</button>
									<button class="btn btn-delete text-danger btn-control">
										<i class="fas fa-trash"></i>
									</button>


								</div>
							</div>

							<div class="border-top my-3"></div>
							<div class="row">
								<div class="col-6 d-block">
									<div class="row">
										<div class="col-md-4 font-weight-bold">
											<label>Tên Lớp </label>
										</div>
										<div class="col-md-8">

											<p class="text-primary" field="courseName"></p>
										</div>
									</div>
									<div class="row">
										<div class="col-md-4 font-weight-bold">
											<label>Mã GV</label>
										</div>
										<div class="col-md-8">

											<p class="text-primary" field="teacher.code"></p>
										</div>
									</div>


								</div>
								<div class="col-6 d-block">
									<div class="row">
										<div class="col-md-4 font-weight-bold">
											<label>Mô tả </label>
										</div>
										<div class="col-md-8">

											<p class="text-primary" field="descriptor"></p>
										</div>
									</div>
									<div class="row">
										<div class="col-md-4 font-weight-bold">
											<label>Tên GV</label>
										</div>
										<div class="col-md-8">

											<p class="text-primary" field="teacher.fullName"></p>
										</div>
									</div>


								</div>

							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="row  w-100 justify-content-end" id='pagination'>
			<jsp:include page="/WEB-INF/views/commons/pagination.jsp"></jsp:include>
		</div>
	</div>

</div>


<!-- modal add new Class-->

<div class="modal modal-reset fade" id="modal-add-new" tabindex="-1"
	role="dialog" aria-labelledby="edit" aria-hidden="true">
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

<div class="modal fade modal-reset" id="modal-edit" tabindex="-1"
	role="dialog" aria-labelledby="edit" aria-hidden="true">
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
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>

<!-- end modal edit -->

<!--  modal add teacher to course -->
<div class="modal fade modal-reset" id="modal-teacher" tabindex="-1"
	role="dialog" aria-labelledby="edit" aria-hidden="true">
	<div class="modal-dialog modal-xl">
		<div class="modal-content">
			<!--Header-->
			<div class="modal-header">
				<p class="heading lead">Quản Lý Giảng Viên Phụ Trách</p>

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
								id="form-teacher">
								<h2>Thông Tin Giảng Viên</h2>
								<div class="form-row">
									<label for="code">Mã Giảng Viên Phụ Trách</label> <input
										type="text" class=" input-text teacher-code"
										name="teacher.code"> <label name="code-error"
										id="teacher-code-error" class="error hidden">Mã Giảng
										Viên Không Tồn Tại</label>

								</div>
								<div
									class="waiting-process d-flex justify-content-center pb-4 hidden">
									<h2 class="message hidden"></h2>
									<img style="max-width: 32px; max-height: 32px;"
										src="<c:url value='/resources/commons/image/icon/load-icon.gif' />">

								</div>
								<!-- teacher freview -->
								<div class="container-fluid p-0" id="preview-teacher-container">
									<div class="d-flex form-row" style="background: #f8f9fa;"
										id="preview-teacher-row">
										<div class="col-2 p-0">
											<img field="image"
												style="height: 132px; width: 132px; max-height: 132px; max-width: 132px;"
												src="<c:url value='/resources/commons/image/user/default-user.jpg'/>">
										</div>
										<div class="col-5 pl-3">
											<div class="row h-50">
												<div class="col-md-4 align-self-center">
													<label>Mã GV</label>
												</div>
												<div class="col-md-8 align-self-center">
													<p class="text-primary" field="code">Không</p>
												</div>
											</div>
											<div class="row h-50">
												<div class="col-md-4 align-self-center">
													<label>Họ&Tên</label>
												</div>
												<div class="col-md-8 align-self-center">
													<p class="text-primary" field="fullName">Không</p>
												</div>
											</div>
										</div>
										<div class="col-5 pl-3">
											<div class="row h-50">
												<div class="col-md-4 align-self-center">
													<label>Chức Vụ</label>
												</div>
												<div class="col-md-8 align-self-center">

													<p class="text-primary" field="position">không</p>
												</div>
											</div>
											<div class="row h-50">
												<div class="col-md-4 align-self-center">
													<label>Bộ Môn</label>
												</div>
												<div class="col-md-8 align-self-center">

													<p class="text-primary" field="department">không</p>
												</div>
											</div>
										</div>
									</div>
								</div>

							</form>
						</div>
					</div>
					<!--Footer-->
					<div class="border-top my-3"></div>
					<div class="d-flex justify-content-center">
						<a type="button" class="btn  waves-effect btn-cancel"
							data-dismiss="modal">Hủy</a> <a type="button"
							class="btn  waves-effect btn-submit disabled">Lưu</a>
					</div>
				</div>
			</div>

		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>


<!-- modal alert -->
<div class="modal fade modal-reset" id="modal-delete-alert"
	tabindex="-1" role="dialog" aria-labelledby="edit" aria-hidden="true">
	<div class="modal-dialog modal-sm">
		<div class="modal-content">
			<!--Header-->
			<div class="modal-header bg-warning">
				<p class="heading lead">Cảnh Báo</p>

				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true" class="white-text">×</span>
				</button>
			</div>

			<!--Body-->
			<div class="modal-body border-top my-3">
				<div class="message">Bạn Có Muốn Tiếp Tục Xóa ?</div>

				<div class="border-top my-3"></div>
				<div class="d-flex justify-content-center">
					<a type="button" class="btn  waves-effect btn-cancel"
						data-dismiss="modal">Hủy</a><a type="button"
						class="btn  waves-effect btn-delete-alert-ok">OK</a>
				</div>
			</div>

		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->

</div>

<!-- end modal alert -->


<jsp:include page="/WEB-INF/views/commons/lib.jsp"></jsp:include>
<script src="<c:url value='/resources/commons/js/base.js' />"></script>
<script src="<c:url value='/resources/manager/commons/js/search.js' />"></script>
<script src="<c:url value='/resources/commons/js/pagination.js' />"></script>
<script
	src="<c:url value='/resources/manager/admin/js/course-list.js' />"></script>
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