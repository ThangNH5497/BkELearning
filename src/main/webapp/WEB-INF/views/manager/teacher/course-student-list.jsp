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

	<jsp:include page="/WEB-INF/views/manager/teacher/menu.jsp"></jsp:include>
	<!-- Page Content  -->
	<div id="content" class="mb-5">

		<jsp:include page="/WEB-INF/views/manager/commons/topbar.jsp"></jsp:include>

		<div class=" container-fluid ">

			<nav class="col-10 navbar navbar-light bg-light"
				style="font-weight: 600;" id="link-current">
				<span class="navbar-text"> <i class="fas fa-link"></i> <a
					class='text-primary' href="<c:url value='/teacher/ql-lop-hoc' />">Danh
						sách lớp</a><span> / Danh sách sinh viên</span>
				</span>
			</nav>
			<nav class="col-10 navbar navbar-light bg-light hidden"
				style="font-weight: 600;" id='link-back-search'>
				<span class="navbar-text"> <i class="fas fa-link"></i> <a
					class='text-primary' href="<c:url value='/admin/ql-mon-hoc' />">Danh
						sách lớp</a><a class='text-primary'
					href="javascript:location.reload(true)"> / Danh sách sinh viên</a><span>
						/ Tìm kiếm</span>
				</span>
			</nav>
			<div class="row ">

				<!-- Area Chart -->
				<div class="col-xl-9 col-lg-9 col-md-12 card-container">
					<div class="card shadow h-100">
						<!-- Card Header - Dropdown -->
						<div
							class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
							<h6
								class="m-0 font-weight-bold text-primary text-responsive-lg d-none d-sm-block course-title"></h6>
							<div class="m-0"></div>
							<div class="d-flex">
								<button
									class="btn btn-view text-info btn-control d-block d-lg-none">
									<i class="far fa-eye"></i>
								</button>
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
																		<a class="dropdown-item" id="select-all">Chọn tất cả</a> <a class="dropdown-item" id="deselect-all">Bỏ
																			chọn tất cả</a>
																	</div>
																</div>
															</th>
															<th class="table-cell column1">STT</th>
															<th class="table-cell column2">Mã SV</th>
															<th class="table-cell column3">Họ&Tên</th>
															<th class="table-cell column4">Lớp</th>
															<th class="table-cell column5">Email</th>
															<th class="table-cell column6">Ngày sinh</th>

														</tr>

													</thead>
												</table>
											</div>

											<div class="table-data-body js-pscroll">
												<table>
													<tbody id="table-data-body">
														<h5 id='data-empty-alert'
															class="hidden mt-3 mb-3 w-100 d-flex justify-content-center font-weight-bold text-primary">Không
															có dữ liệu</h5>
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
											<div id='pagination' class='bg-white p-2 border-top'><jsp:include
													page="/WEB-INF/views/commons/pagination.jsp"></jsp:include>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>

					</div>
				</div>

				<!-- Pie Chart -->
				<div
					class="col-xl-3 col-lg-3 d-none d-lg-block card-container card-detail"
					id='card-detail'>
					<div class="card shadow h-100">
						<!-- Card Header - Dropdown -->
						<div
							class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
							<h6 class="m-0 font-weight-bold text-primary">Chi tiết</h6>
							
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
														<label>Địa chỉ</label>
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
														<label>Ngày sinh</label>
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
				<p class="heading lead">Thêm sinh viên vào lớp</p>

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
							thủ công</a></li>
					<li class="nav-item"><a class="nav-link" id="add-exel-tab-btn"
						data-toggle="tab" href="#add-exel-tab" role="tab"
						aria-controls="profile" aria-selected="false">Import file Excel</a></li>

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
										<h2>Thông tin sinh viên</h2>
										<div class="form-row">
											<label for="code">Mã sinh viên</label> <input type="text"
												class=" input-text student-code" name="teacher.code">
											<label name="code-error" id="student-code-error"
												class="error hidden">Sinh viên không tồn tại</label>

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
															<label>Ngày sinh</label>
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
										<h2>Thêm sinh viên từ file Excel</h2>

										<div
											class="hidden alert alert-danger alert-dismissible fade show"
											role="alert" id='alert-file-exel'>
											<strong>Cảnh báo!</strong> Bạn chưa chọn file.
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
									class="btn  waves-effect btn-submit-file-exel">Tải lên</a>
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
				<p class="heading lead">Cảnh báo</p>

				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true" class="white-text">×</span>
				</button>
			</div>

			<!--Body-->
			<div class="modal-body border-top my-3">
				<div class="message">Bạn có muốn tiếp tục xóa?</div>

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
				<p class="heading lead">Đang xử lý...</p>

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
<div class="modal fade" id="modal-view" tabindex="-1" role="dialog"
	aria-labelledby="edit" aria-hidden="true">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<!--Header-->
			<div class="modal-header">
				<p class="heading lead">Thông tin giảng viên</p>

				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true" class="white-text">×</span>
				</button>
			</div>

			<!--Body-->
			<div class="modal-body">
				<div class="form-container">
					<div class="row">
						<div class="container-fluid user-profile h-100"
							style="max-height: none !important;">
							<div class="row ">
								<div class="col-md-12">
									<div class="profile-img">
										<img class='hidden' field="image" id="user-detail-img-lg"
											style="width: 100%; max-height: 20vh;" src="" alt="" />
									</div>
								</div>
							</div>
							<div class="tab-content profile-tab">
								<div class="tab-pane fade show active" id="user-detail-lg"
									role="tabpanel" aria-labelledby="home-tab">
									<div id="user-detail-row-lg" class="hidden">
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
													<label>Địa chỉ</label>
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
													<label>Ngày sinh</label>
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
					</div>
					<!--Footer-->
					<div class="border-top my-3"></div>
					<div class="d-flex justify-content-center">
						<a type="button" class="btn  waves-effect btn-cancel"
							style="margin-left: 10%" data-dismiss="modal">Đóng</a>
					</div>
				</div>
			</div>

		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>

<jsp:include page="/WEB-INF/views/commons/lib.jsp"></jsp:include>
<script src="<c:url value='/resources/commons/js/base.js' />"></script>
<script src="<c:url value='/resources/commons/js/pagination.js' />"></script>
<script
	src="<c:url value='/resources/manager/commons/js/user-management.js' />"></script>
<script src="<c:url value='/resources/manager/commons/js/search.js' />"></script>
<script
	src="<c:url value='/resources/manager/teacher/js/course-student-list.js' />"></script>
<!-- Footer -->


<jsp:include page="/WEB-INF/views/manager/commons/footer.jsp"></jsp:include>