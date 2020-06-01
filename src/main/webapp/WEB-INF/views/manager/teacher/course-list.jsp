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
	href="<c:url value='/resources/manager/commons/css/course-list.css' />">
<!-- Phan noi dung trang -->
<div class="wrapper d-flex align-items-stretch">

	<jsp:include page="/WEB-INF/views/manager/teacher/menu.jsp"></jsp:include>
	<!-- Page Content  -->
	<div id="content" class="mb-5">
		<jsp:include page="/WEB-INF/views/manager/commons/topbar.jsp"></jsp:include>
		<div class=" container-fluid content-container">
			<div class=" container-fluid d-flex pl-0 ml-0 bg-light">
				<nav class="navbar navbar-light " style="font-weight: 600;"
					id="link-current">

					<span class="navbar-text"> <i class="fas fa-link"></i> <a
						class='text-primary' href="<c:url value='/teacher/ql-lop-hoc' />">Danh
							Sách Lớp</a></span>

				</nav>
				<nav class="navbar navbar-light bg-light hidden"
					style="font-weight: 600;" id='link-back-search'>
					<span class="navbar-text"> <i class="fas fa-link"></i><a
						class='text-primary' href="javascript:location.reload(true)">Danh
							Sách Lớp</a><span> / Tìm Kiếm</span>
					</span>
				</nav>
			</div>

			<h5 id='data-empty-alert'
				class="hidden mt-3 mb-3 w-100 d-flex justify-content-center font-weight-bold text-primary">Không
				Có Dữ Liệu</h5>
			<div class="row " id="table-data-body">
				<!-- Area Chart -->
				<div class="col-xl-12 col-lg-12  mb-4 hidden"
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
										<p field="code" class="pl-4"></p>
									</span>
								</div>
								<div class="d-flex justify-content-end col-6">
									<a href="#"
										class="btn btn-success btn-circle btn-sm mr-3 btn-student-management">
										<i class="fas fa-user-cog"></i>
									</a> <a href="#" class="btn btn-info btn-circle btn-sm mr-3 btn-exam-management"> <i
										class="fas fa-sliders-h"></i>
									</a>

								</div>
							</div>

							<div class="border-top my-3"></div>
							<div class="row">
								<div class="col-6 d-block">
									<div class="row">
										<div class="col-md-4 font-weight-bold">
											<label>Mã Học Phần </label>
										</div>
										<div class="col-md-8">

											<p class="text-primary" field="subject.code"></p>
										</div>
									</div>
									<div class="row">
										<div class="col-md-4 font-weight-bold">
											<label>Tên Lớp</label>
										</div>
										<div class="col-md-8">

											<p class="text-primary" field="courseName"></p>
										</div>
									</div>


								</div>
								<div class="col-6 d-block">
									<div class="row">
										<div class="col-md-4 font-weight-bold">
											<label>Tên Học Phần </label>
										</div>
										<div class="col-md-8">

											<p class="text-primary" field="subject.subjectName"></p>
										</div>
									</div>
									<div class="row">
										<div class="col-md-4 font-weight-bold">
											<label>Mô Tả Lớp</label>
										</div>
										<div class="col-md-8">

											<p class="text-primary" field="descriptor"></p>
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

<div class="modal modal-reset fade" id="modal-edit" tabindex="-1"
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

	</div>

</div>

<jsp:include page="/WEB-INF/views/commons/lib.jsp"></jsp:include>
<script src="<c:url value='/resources/commons/js/base.js' />"></script>
<script src="<c:url value='/resources/manager/commons/js/search.js' />"></script>
<script src="<c:url value='/resources/commons/js/pagination.js' />"></script>
<script
	src="<c:url value='/resources/manager/teacher/js/course-list.js' />"></script>
<!-- Footer -->


<jsp:include page="/WEB-INF/views/manager/commons/footer.jsp"></jsp:include>