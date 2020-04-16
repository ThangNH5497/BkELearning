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
	<div id="content" class="mb-5">

		<jsp:include page="/WEB-INF/views/manager/commons/topbar.jsp"></jsp:include>

		<div class=" container-fluid ">

			<nav class="col-10 navbar navbar-light bg-light"
				style="font-weight: 600;" id="link-current">
				<span class="navbar-text"> <i class="fas fa-link"></i> <a
					class='text-primary' href="<c:url value='/teacher/ql-lop-hoc' />">Danh
						Sách Lớp</a><span> / Danh Sách Sinh Viên</span>
				</span>
			</nav>
			<nav class="col-10 navbar navbar-light bg-light hidden"
				style="font-weight: 600;" id='link-back-search'>
				<span class="navbar-text"> <i class="fas fa-link"></i> <a
					class='text-primary' href="<c:url value='/admin/ql-mon-hoc' />">Danh
						Sách Lớp</a><a class='text-primary'
					href="javascript:location.reload(true)"> / Danh Sách Sinh Viên</a><span>
						/ Tìm Kiếm</span>
				</span>
			</nav>
			<div class="row ">

				<!-- Area Chart -->
				<div class="col-xl-9 col-lg-8 card-container">
					<div class="card shadow h-100">
						<!-- Card Header - Dropdown -->
						<div
							class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
							<h6 class="m-0 font-weight-bold text-primary course-title"></h6>

							<div class="d-flex">
								<button class="btn btn-add text-success btn-control"
									data-toggle="modal" data-target="#modal-add-student">
									<i class="far fa-plus-square"></i>
								</button>
								<button class="btn btn-delete text-danger btn-control">
									<i class="fas fa-trash"></i>
								</button>
								<button class="btn btn-refresh text-primary btn-control">
									<i class="fas fa-sync"></i>
								</button>
								<div id='pagination'><jsp:include
										page="/WEB-INF/views/commons/pagination.jsp"></jsp:include>
								</div>
							</div>


						</div>
						<!-- Card Body -->
						<div class="card-body">
							<div class="limiter">
								<div class="container-table-data">
									<div class="wrap-table-data">
										<div class="table-data  m-b-110">
											<div class="table-data-head">
												<table>
													<thead>
														<tr class="table-row head ">
															<th class="table-cell column0">
																<div class="dropdown d-flex">

																	<div class="custom-control custom-checkbox">
																		<input type="checkbox" class="custom-control-input">
																		<label class="custom-control-label"></label>

																	</div>
																	<div class="dropdown-toggle" id="dropdownMenuButton"
																		data-toggle="dropdown" aria-haspopup="true"
																		aria-expanded="false"></div>
																	<div class="dropdown-menu"
																		aria-labelledby="dropdownMenuButton">
																		<a class="dropdown-item" id="select-all">Chọn Tất
																			Cả</a> <a class="dropdown-item" id="deselect-all">Bỏ
																			Chọn Tất Cả</a>
																	</div>
																</div>
															</th>
															<th class="table-cell column1">STT</th>
															<th class="table-cell column2">Mã SV</th>
															<th class="table-cell column3">Họ&Tên</th>
															<th class="table-cell column4">Lớp</th>
															<th class="table-cell column5">Email</th>
															<th class="table-cell column6">Ngày Sinh</th>

														</tr>

													</thead>
												</table>
											</div>

											<div class="table-data-body js-pscroll">
												<table>
													<tbody id="table-data-body">
														<h5 id='data-empty-alert'
															class="hidden mt-3 mb-3 w-100 d-flex justify-content-center font-weight-bold text-primary">Không
															Có Dữ Liệu Khớp</h5>
														<tr class="table-row body hidden border-bottom border-top"
															id="row-data-container">

															<td class="table-cell column0" field='checkBox'><div
																	class="custom-control custom-checkbox">
																	<input type="checkbox" class="custom-control-input"
																		id="check-1"> <label
																		class="custom-control-label" for="check-1"></label>
																</div></td>
															<td class="table-cell column1" field="index"></td>
															<td class="table-cell column2" field="code"></td>
															<td class="table-cell column3" field="fullName"></td>
															<td class="table-cell column4" field="className"></td>
															<td class="table-cell column5" field=email></td>
															<td class="table-cell column6" field="dateOfBirth"></td>
														</tr>

													</tbody>
												</table>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>

					</div>
				</div>

				<!-- Pie Chart -->
				<div class="col-xl-3 col-lg-4 card-container">
					<div class="card shadow h-100">
						<!-- Card Header - Dropdown -->
						<div
							class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
							<h6 class="m-0 font-weight-bold text-primary">Chi Tiết</h6>
							<div class="dropdown no-arrow">
								<a class="dropdown-toggle" href="#" role="button"
									id="dropdownMenuLink" data-toggle="dropdown"
									aria-haspopup="true" aria-expanded="false"> <i
									class="fas fa-ellipsis-v fa-sm fa-fw text-gray-400"></i>
								</a>
								<div
									class="dropdown-menu dropdown-menu-right shadow animated--fade-in"
									aria-labelledby="dropdownMenuLink">
									<div class="dropdown-header">Dropdown Header:</div>
									<a class="dropdown-item" href="#">Action</a> <a
										class="dropdown-item" href="#">Another action</a>
									<div class="dropdown-divider"></div>
									<a class="dropdown-item" href="#">Something else here</a>
								</div>
							</div>
						</div>
						<!-- Card Body -->
						<div class="card-body">
							<!-- profile user -->
							<div class="container-fluid user-profile">
								<div class="row ">
									<div class="col-md-12">
										<div class="profile-img">
											<img class='hidden' field="image" id="user-detail-img"
												style="width: 100%; max-height: 20vh;" src="" alt="" />
										</div>
									</div>
								</div>
								<div class="tab-content profile-tab">
									<div class="tab-pane fade show active" id="user-detail"
										role="tabpanel" aria-labelledby="home-tab">
										<div id="user-detail-row" class="hidden">
											<div>
												<div class="border-top my-3"></div>
												<div class="row">
													<div class="col-md-4">
														<label>Mã SV</label>
													</div>
													<div class="col-md-8">

														<p field="code"></p>
													</div>
												</div>
											</div>

											<div>
												<div class="border-top my-3"></div>
												<div class="row">
													<div class="col-md-4">
														<label>Họ&Tên</label>
													</div>
													<div class="col-md-8">
														<p field="fullName"></p>
													</div>
												</div>
											</div>
											<div>
												<div class="border-top my-3"></div>
												<div class="row">
													<div class="col-md-4">
														<label>Email</label>
													</div>
													<div class="col-md-8">
														<p field="email"></p>
													</div>
												</div>
											</div>
											<div>
												<div class="border-top my-3"></div>
												<div class="row">
													<div class="col-md-4">
														<label>SĐT</label>
													</div>
													<div class="col-md-8">
														<p field="phoneNumber"></p>
													</div>
												</div>
											</div>
											<div>
												<div class="border-top my-3"></div>
												<div class="row">
													<div class="col-md-4">
														<label>Lớp </label>
													</div>
													<div class="col-md-8">
														<p field="className"></p>
													</div>
												</div>
											</div>
											<div>
												<div class="border-top my-3"></div>
												<div class="row">
													<div class="col-md-4">
														<label>Địa Chỉ</label>
													</div>
													<div class="col-md-8">
														<p field="addr"></p>
													</div>
												</div>
											</div>
											<div>
												<div class="border-top my-3"></div>
												<div class="row">
													<div class="col-md-4">
														<label>Ngày Sinh</label>
													</div>
													<div class="col-md-8">
														<p field="dateOfBirth"></p>
													</div>
												</div>
											</div>

										</div>

									</div>

								</div>

							</div>
							<!-- end profile user -->
						</div>
					</div>
				</div>
			</div>

		</div>

	</div>

	<!-- end content -->

</div>

<!-- modal add student to course -->
<div class="modal modal-reset fade" id="modal-add-student" tabindex="-1"
	role="dialog" aria-labelledby="edit" aria-hidden="true">
	<div class="modal-dialog modal-xl">
		<div class="modal-content">
			<!--Header-->
			<div class="modal-header">
				<p class="heading lead">Thêm Sinh Viên Vào Lớp</p>

				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true" class="white-text">×</span>
				</button>
			</div>

			<!--Body-->
			<div class="modal-body">
				<ul class="nav nav-tabs" id="container-tab-add" role="tablist">
					<li class="nav-item"><a class="nav-link active"
						id="add-one-tab-btn" data-toggle="tab" href="#add-one-tab"
						role="tab" aria-controls="tab-add" aria-selected="true">Thêm
							Thủ Công</a></li>
					<li class="nav-item"><a class="nav-link" id="add-exel-tab-btn"
						data-toggle="tab" href="#add-exel-tab" role="tab"
						aria-controls="profile" aria-selected="false">Import file Exel</a></li>

				</ul>
				<div class="tab-content">
					<!-- them giang vien thu cong -->
					<div class="tab-pane fade show active" id="add-one-tab"
						role="tabpanel" aria-labelledby="home-tab">
						<div class="form-container">
							<div class="form-v4">
								<div class="form-v4-content">
									<form class="form-detail" action="#" method="post"
										id="form-student">
										<h2>Thông Tin Sinh Viên</h2>
										<div class="form-row">
											<label for="code">Mã Sinh Viên</label> <input type="text"
												class=" input-text student-code" name="teacher.code">
											<label name="code-error" id="student-code-error"
												class="error hidden">Mã Sinh Viên Không Tồn Tại</label>

										</div>
										<div
											class="waiting-process d-flex justify-content-center pb-4 hidden">
											<h2 class="message hidden"></h2>
											<img style="max-width: 32px; max-height: 32px;"
												src="<c:url value='/resources/commons/image/icon/load-icon.gif' />">

										</div>
										<!-- student freview -->
										<div class="container-fluid p-0"
											id="preview-student-container">
											<div class="d-flex form-row" style="background: #f8f9fa;"
												id="preview-student-row">
												<div class="col-2 p-0">
													<img field="image"
														style="height: 132px; width: 132px; max-height: 132px; max-width: 132px;"
														src="<c:url value='/resources/commons/image/user/default-user.jpg'/>">
												</div>
												<div class="col-5 pl-3">
													<div class="row h-50">
														<div class="col-md-4 align-self-center">
															<label>Mã SV</label>
														</div>
														<div class="col-md-8 align-self-center">
															<p class="text-primary" field="code"></p>
														</div>
													</div>
													<div class="row h-50">
														<div class="col-md-4 align-self-center">
															<label>Họ&Tên</label>
														</div>
														<div class="col-md-8 align-self-center">
															<p class="text-primary" field="fullName"></p>
														</div>
													</div>
												</div>
												<div class="col-5 pl-3">
													<div class="row h-50">
														<div class="col-md-4 align-self-center">
															<label>Lớp</label>
														</div>
														<div class="col-md-8 align-self-center">

															<p class="text-primary" field="className"></p>
														</div>
													</div>
													<div class="row h-50">
														<div class="col-md-4 align-self-center">
															<label>Ngày Sinh</label>
														</div>
														<div class="col-md-8 align-self-center">

															<p class="text-primary" field="dateOfBirth"></p>
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
					<!-- ket thuc them giang vien thu cong -->
					<!-- them giang vien tu file exel -->
					<div class="tab-pane fade" id="add-exel-tab" role="tabpanel"
						aria-labelledby="profile-tab">

						<div class="form-container">
							<div class="form-v4">
								<div class="form-v4-content">
									<form class="form-detail" id="form-exel-file">
										<h2>Thêm Danh Sách Sinh Viên Từ File Exel</h2>

										<div
											class="hidden alert alert-danger alert-dismissible fade show"
											role="alert" id='alert-file-exel'>
											<strong>Cảnh Báo !</strong> Bạn Chưa Chọn File.
											<button type="button" class="close" data-dismiss="alert"
												aria-label="Close">
												<span aria-hidden="true">&times;</span>
											</button>
										</div>
										<div class="custom-file">
											<input type="file" class="custom-file-input file-input"
												id="input-file-exel"
												accept=".csv, application/vnd.openxmlformats-officedocument.spreadsheetml.sheet, application/vnd.ms-excel">
											<label class="custom-file-label file-exel-name"
												for="customFile">Chọn file</label>
										</div>
									</form>
								</div>
							</div>
							<!--Footer-->
							<div class="border-top my-3"></div>
							<div class="d-flex justify-content-center">
								<a type="button" class="btn  waves-effect btn-cancel"
									data-dismiss="modal">Hủy</a> <a type="button"
									class="btn  waves-effect btn-submit-file-exel">Tải Lên</a>
							</div>
						</div>



					</div>
					<!-- end them giang vien tu file exel -->
				</div>

			</div>


		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>

<!-- modal alert -->
<div class="modal modal-reset fade" id="modal-delete-alert"
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

<!-- modal message -->
<div class="modal modal-reset fade" id="modal-message" tabindex="-1"
	role="dialog" aria-labelledby="edit" aria-hidden="true">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<!--Header-->
			<div class="modal-header">
				<p class="heading lead">Đang Xử Lý...</p>

			</div>

			<!--Body-->
			<div class="modal-body">
				<div class="form-container message-container">
					<div class="border-top my-3"></div>
					<div class="waiting-process d-flex justify-content-center">
						<h2 class="message hidden"></h2>
						<img style="max-width: 48px; max-height: 48px;"
							src="<c:url value='/resources/commons/image/icon/load-icon.gif' />">

					</div>
					<!--Footer-->
					<div class="border-top my-3"></div>
					<div class="d-flex justify-content-center">
						<a type="button" style="margin: 0 !important;"
							class="btn hidden waves-effect btn-ok">OK</a>
					</div>
				</div>

			</div>

		</div>


	</div>
	<!-- /.modal-content -->
</div>
<!-- /.modal-dialog -->
<!-- end modal message -->
<jsp:include page="/WEB-INF/views/commons/lib.jsp"></jsp:include>
<script src="<c:url value='/resources/commons/js/base.js' />"></script>
<script src="<c:url value='/resources/commons/js/pagination.js' />"></script>
<script
	src="<c:url value='/resources/manager/commons/js/user-management.js' />"></script>
<script src="<c:url value='/resources/manager/commons/js/search.js' />"></script>
<script
	src="<c:url value='/resources/manager/teacher/js/course-student-list.js' />"></script>
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