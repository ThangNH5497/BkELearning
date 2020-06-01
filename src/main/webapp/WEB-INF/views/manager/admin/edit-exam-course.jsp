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
	href="<c:url value='/resources/manager/commons/css/add-exam.css' />">
<link rel="stylesheet"
	href="<c:url value='/resources/commons/lib/bootstrap-datetimepicker/css/bootstrap-datetimepicker.css' /> ">

<!-- Phan noi dung trang -->
<div class="wrapper d-flex align-items-stretch">
	<jsp:include page="/WEB-INF/views/manager/admin/menu.jsp"></jsp:include>
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
				<h1 class="h4 mb-0 text-gray-600">Gán Lớp Học</h1>
			</div>
			

			<div>
				<div class="card mb-4">
					<div class="card-body ">
						<h5 class="h6 mb-0 text-gray-600 mb-4">Danh Sách Lớp</h5>

						<div id="course-list-container">
							<div class='hidden' id='course-item-sample'>
								<div class="card mt-3">
									<div class="card-body p-2 mt-1 mb-1">
										<div class='row d-flex'>
											<div class="d-flex col-lg-4 col-md-4 align-self-center">
												<div class='course-code'></div>
											</div>
											<div class="d-flex col-lg-6 col-md-6 align-self-center">
												<div class="text-truncate d-flex course-name"></div>

											</div>

											
											<div
												class="row col-lg-2 col-md-2 justify-content-end d-flex">
												
												<button
													class="m-0 btn btn-delete text-danger btn-control col-3">
													<i class="fas fa-trash"></i>
												</button>
												

											</div>
										</div>

									</div>
								</div>
							</div>
						</div>
						<div class="card mt-3">
							<div class="card-body p-2 mt-1 mb-1">
								<div class='row d-flex'>
									<div class="d-flex col-lg-10 col-md-9 align-self-center"></div>

								</div>

							</div>
						</div>
						<!-- button add question -->
						<div class="d-flex w-100 justify-content-end mt-4">


							<div class='d-flex'>

								<div class="dropdown">
									<button class="btn btn-success btn-select-course btn-sm" type="button">Thêm
										Lớp</button>

								</div>
							</div>

						</div>

						<!-- end button add question -->

					</div>
				</div>
			</div>
		</div>




		<!-- table -->

		<div class='row d-flex justify-content-center mt-4'>
			<button type="button" class="btn btn-primary btn-submit"
				id="btn-submit-exam">Cập Nhật</button>
		</div>
		<!-- end table -->
	</div>

</div>

<!-- modal select course-->
<div class="modal fade" id="modal-select-course" tabindex="-1"
	role="dialog" aria-labelledby="edit" aria-hidden="true">
	<div class="modal-dialog modal-xl">
		<div class="modal-content">
			<!--Header-->
			<div class="modal-header">
				<p class="heading lead">Chọn Lớp Học</p>

				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true" class="white-text">×</span>
				</button>
			</div>

			<!--Body-->
			<div class="modal-body pl-0 pr-0 pt-0">
				<div class="">
					<!-- End of Topbar -->
					<!-- table -->
					<nav
						class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">
						<form
							class="d-none d-sm-inline-block form-inline mr-auto ml-md-3 my-2 my-md-0 mw-100 navbar-search">
							<div class="input-group">
								<input id="key-search" type="text"
									class="form-control bg-light border-0 small"
									placeholder="Nhập Mã Hoặc Tên..." aria-label="Search"
									aria-describedby="basic-addon2">
								<div class="input-group-append">
									<button class="btn " type="button" id="btn-search"
										style="background: #33b5e5; border-color: #33b5e5">
										<i class="fas fa-search fa-sm text-white"></i>
									</button>
								</div>
							</div>
						</form>
					</nav>
					<div class="container-fluid">

						<div class="descriptor row px-4 mb-4">
							<div class="col-6 row d-flex ">
								<div class="col-6">
									<span class=' pl-1 m-0 font-weight-bold text-primary'>Môn
										Học</span>
								</div>
								<div class="col-6">
									<span class='text-primary subject-info'></span>
								</div>

							</div>

						</div>


						<!-- table -->

						<div class="table-responsive pl-3 pr-3  w-100">
							<table class="table table-bordered w-100" id="dataTable"
								cellspacing="0">

								<thead>
									<tr>
										<th class="table-cell column0">
											<div class="dropdown d-flex justify-content-center">
												<div class="custom-control custom-checkbox">
													<input type="checkbox" class="custom-control-input">
													<label class="custom-control-label"></label>

												</div>
												<div class="dropdown-toggle" id="dropdownMenuButton"
													data-toggle="dropdown" aria-haspopup="true"
													aria-expanded="false"></div>
												<div class="dropdown-menu"
													aria-labelledby="dropdownMenuButton">
													<a class="dropdown-item" id="check-all">Chọn Tất Cả</a> <a
														class="dropdown-item" id="un-check-all">Bỏ Chọn Tất Cả</a>
												</div>
											</div>
										</th>
										<th class='column1'>STT</th>
										<th class='column2'>Mã Lớp</th>
										<th class='column3'>Tên Lớp</th>
										<th class='column4'>Mô Tả</th>
									</tr>
								</thead>

								<tbody id="table-data-body">
									<tr id="row-data-container">
										<td class="table-cell column0" field='checkBox'><div
												class="custom-control custom-checkbox">
												<input type="checkbox" class="custom-control-input"
													id="check-1"> <label class="custom-control-label"
													for="check-1"></label>
											</div></td>
										<td class='column1' field='index'></td>
										<td class='column2' field='code'></td>
										<td class='column3' field='courseName'></td>
										<td class='column3' field='descriptor'></td>
									</tr>


								</tbody>
							</table>

						</div>
						<div id='pagination' class='bg-white p-2 border-top'><jsp:include
								page="/WEB-INF/views/commons/pagination.jsp"></jsp:include>
						</div>

						<!-- end table -->
					</div>
					<!-- end table -->
					<!-- pagination -->

					<!-- end pagination -->
				</div>
				<div>
					<div class="border-top my-3"></div>
					<div class="d-flex justify-content-center">
						<a type="button" class="btn  waves-effect btn-cancel"
							data-dismiss="modal">Hủy</a><a type="button"
							class="btn waves-effect btn-submit">Thêm</a>
					</div>
				</div>
			</div>

		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>

<!-- modal-delete-alert -->
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
				<div class="message">Bạn Có Muốn Tiếp Tục Xóa Câu Hỏi Khỏi Đề
					Thi ?</div>

				<div class="border-top my-3"></div>
				<div class="d-flex justify-content-center">
					<a type="button" class="btn  waves-effect btn-cancel"
						data-dismiss="modal">Hủy</a><a type="button"
						class="btn  waves-effect btn-submit">Xóa</a>
				</div>
			</div>

		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->

</div>
<jsp:include page="/WEB-INF/views/commons/lib.jsp"></jsp:include>
<script
	src="<c:url value='/resources/commons/lib/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js' />"></script>
<script src="<c:url value='/resources/commons/js/base.js' />"></script>
<script src="<c:url value='/resources/commons/js/pagination.js' />"></script>
<script src="<c:url value='/resources/manager/commons/js/search.js' />"></script>
<script src="<c:url value='/resources/manager/admin/js/edit-exam-course.js' />"></script>
<!-- Footer -->

<jsp:include page="/WEB-INF/views/manager/commons/footer.jsp"></jsp:include>