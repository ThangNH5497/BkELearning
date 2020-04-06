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
	href="<c:url value='/resources/manager/commons/css/subject-management.css' />">

<!-- Phan noi dung trang -->
<div class="wrapper d-flex align-items-stretch">

	<jsp:include page="/WEB-INF/views/manager/admin/menu.jsp"></jsp:include>
	<!-- Page Content  -->
	<div id="content" class="mb-5">

		<!-- Topbar -->
		<nav
			class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">

			<button type="button" id="sidebarCollapse" class="btn btn-primary">
				<i class="fa fa-bars"></i> <span class="sr-only">Toggle Menu</span>
			</button>
			<!-- Sidebar Toggle (Topbar) -->
			<button id="sidebarToggleTop"
				class="btn btn-link d-md-none rounded-circle mr-3">
				<i class="fa fa-bars"></i>
			</button>
			<!-- Topbar Search -->

			<form
				class="d-flex d-sm-inline-block form-inline  ml-md-3 my-2 my-md-0 mw-100 navbar-search">

				<div class="input-group ">
					<div class="input-group-append" id='search-filter'>

						<div class="select-style d-flex">
							<select>
								<option value='code'>Mã Học Phần</option>
								<option value='subjectName'>Tên Học Phần</option>
							</select> <i class="fas fa-caret-down"
								style="margin-top: 10px; padding-right: 10px;"></i>
						</div>

					</div>

					<input type="text" class="form-control bg-light  small"
						placeholder="Search for..." aria-label="Search"
						aria-describedby="basic-addon2" id='key-search'>



					<div class="input-group-append">
						<button class="btn btn-primary" type="button" id='btn-search'>
							<i class="fas fa-search fa-sm"></i>
						</button>
					</div>
				</div>

			</form>
			<!-- type search radio -->
			<!-- end type search radio -->
			<!-- Topbar Navbar -->
			<ul class="navbar-nav ml-auto">
				<li class="nav-item dropdown no-arrow d-sm-none"><a
					class="nav-link dropdown-toggle" href="#" id="searchDropdown"
					role="button" data-toggle="dropdown" aria-haspopup="true"
					aria-expanded="false"> <i class="fas fa-search fa-fw"></i>
				</a> <!-- Dropdown - Messages -->
					<div
						class="dropdown-menu dropdown-menu-right p-3 shadow animated--grow-in"
						aria-labelledby="searchDropdown">
						<form class="form-inline mr-auto w-100 navbar-search">
							<div class="input-group">
								<input type="text" class="form-control bg-light border-0 small"
									placeholder="Search for..." aria-label="Search"
									aria-describedby="basic-addon2">
								<div class="input-group-append">
									<button class="btn btn-primary" type="button">
										<i class="fas fa-search fa-sm"></i>
									</button>
								</div>
							</div>
						</form>
					</div></li>

				<!-- Nav Item - Alerts -->
				<li class="nav-item dropdown no-arrow mx-1"><a
					class="nav-link dropdown-toggle" href="#" id="alertsDropdown"
					role="button" data-toggle="dropdown" aria-haspopup="true"
					aria-expanded="false"> <i class="fas fa-bell fa-fw"></i> <!-- Counter - Alerts -->
						<span class="badge badge-danger badge-counter">3+</span>
				</a> <!-- Dropdown - Alerts -->
					<div
						class="dropdown-list dropdown-menu dropdown-menu-right shadow animated--grow-in"
						aria-labelledby="alertsDropdown">
						<h6 class="dropdown-header">Alerts Center</h6>
						<a class="dropdown-item d-flex align-items-center" href="#">
							<div class="mr-3">
								<div class="icon-circle bg-primary">
									<i class="fas fa-file-alt text-white"></i>
								</div>
							</div>
							<div>
								<div class="small text-gray-500">December 12, 2019</div>
								<span class="font-weight-bold">A new monthly report is
									ready to download!</span>
							</div>
						</a> <a class="dropdown-item d-flex align-items-center" href="#">
							<div class="mr-3">
								<div class="icon-circle bg-success">
									<i class="fas fa-donate text-white"></i>
								</div>
							</div>
							<div>
								<div class="small text-gray-500">December 7, 2019</div>
								$290.29 has been deposited into your account!
							</div>
						</a> <a class="dropdown-item d-flex align-items-center" href="#">
							<div class="mr-3">
								<div class="icon-circle bg-warning">
									<i class="fas fa-exclamation-triangle text-white"></i>
								</div>
							</div>
							<div>
								<div class="small text-gray-500">December 2, 2019</div>
								Spending Alert: We've noticed unusually high spending for your
								account.
							</div>
						</a> <a class="dropdown-item text-center small text-gray-500" href="#">Show
							All Alerts</a>
					</div></li>

				<!-- Nav Item - Messages -->
				<li class="nav-item dropdown no-arrow mx-1"><a
					class="nav-link dropdown-toggle" href="#" id="messagesDropdown"
					role="button" data-toggle="dropdown" aria-haspopup="true"
					aria-expanded="false"> <i class="fas fa-envelope fa-fw"></i> <!-- Counter - Messages -->
						<span class="badge badge-danger badge-counter">7</span>
				</a> <!-- Dropdown - Messages --></li>
			</ul>
			<div class="topbar-divider d-none d-sm-block"></div>
		</nav>
		<!-- End of Topbar -->


		<div class=" container-fluid ">
			<nav class="navbar navbar-light bg-light hidden"
				style="font-weight: 600;" id='link-back-search'>
				<span class="navbar-text"> <i class="fas fa-link"></i> <a
					class='text-primary' href="<c:url value='/admin/ql-mon-hoc' />">Quản
						Lý Môn Học</a><span> / Tìm Kiếm</span>
				</span>
			</nav>
			<div class="row ">

				<!-- Area Chart -->
				<div class="col-xl-12 col-lg-12 card-container">
					<div class="card shadow h-100">
						<!-- Card Header - Dropdown -->
						<div
							class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
							<h6 class="m-0 font-weight-bold text-primary">Danh Sách Môn
								Học</h6>

							<div class="d-flex">
								<button class="btn btn-add text-success btn-control"
									data-toggle="modal" data-target="#modal-add-new">
									<i class="far fa-plus-square"></i>
								</button>
								<button class="btn btn-edit text-primary disabled  btn-control">
									<i class="fas fa-edit"></i>
								</button>
								<button class="btn btn-delete text-danger">
									<i class="fas fa-trash"></i>
								</button>
								<button class="btn btn-refresh text-primary">
									<i class="fas fa-sync"></i>
								</button>
								<nav aria-label="Page navigation">

									<ul class="pagination justify-content-end">

										<li class="page-item disabled"><a class="page-link"
											id="pagination-title">Hiển Thị 1-10/20</a></li>
										<li class="page-item disabled" id="prePage"><a
											class="page-link" tabindex="-1">Previous</a></li>
										<div id="pagination" style="display: flex;">
											<li class="page-item page-item-number active"><a
												class="page-link">1</a></li>
											<li class="page-item page-item-number "><a
												class="page-link">2</a></li>
											<li class="page-item page-item-number "><a
												class="page-link">3</a></li>

											<li class="page-item page-item-number "><a
												class="page-link">4</a></li>
											<li class="page-item page-item-number "><a
												class="page-link">5</a></li>
										</div>

										<li class="page-item disabled" id="nextPage"><a
											class="page-link">Next</a></li>
									</ul>
								</nav>
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
															<th class="table-cell column2">Mã Học Phần</th>
															<th class="table-cell column3">Tên Học Phần</th>
															<th class="table-cell column4">Mô Tả</th>
															<th class="table-cell column5">Tùy Chọn</th>

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
															<td class="table-cell column3" field="subjectName"></td>
															<td class="table-cell column4" field="descriptor"></td>
															<td class="table-cell column5">
																<button
																	class="btn btn-add text-secondary btn-subject-detail d-flex justify-content-start"
																	style="width: 100%; box-shadow: none;">
																	<i style="font-size: 20px;" class="fas fa-cog"></i>
																</button>
															</td>

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

			</div>
		</div>
	</div>

</div>


<!-- modal add new subject-->

<div class="modal fade" id="modal-add-new" tabindex="-1" role="dialog"
	aria-labelledby="edit" aria-hidden="true">
	<div class="modal-dialog modal-xl">
		<div class="modal-content">
			<!--Header-->
			<div class="modal-header">
				<p class="heading lead">Thêm Môn Học</p>

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
								<h2>Thông Tin Môn Học</h2>
								<div class="form-group">
									<div class="form-row form-row-1">
										<label for="code">Mã Môn Học (*)</label> <input type="text"
											name="code" class="input-text" required> <label
											name="code-error" class="error hidden">Mã Môn Học Tồn
											Tại</label>
									</div>
									<div class="form-row form-row-1">
										<label for="subjectName">Tên Môn Học (*)</label> <input
											type="text" name="subjectName" class="input-text" required>
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
				<p class="heading lead">Thêm Môn Học</p>

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
								<h2>Sửa Thông Tin Môn Học</h2>
								<div class="form-group">
									<div class="form-row form-row-1">
										<label for="code">Mã Môn Học (*)</label> <input type="text"
											name="code" class="input-text" required> <label
											name="code-error" class="error hidden">Mã Môn Học Tồn
											Tại</label>
									</div>
									<div class="form-row form-row-1">
										<label for="subjectName">Tên Môn Học (*)</label> <input
											type="text" name="subjectName" class="input-text" required>
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
							class="btn  waves-effect btn-submit">Lưu</a>
					</div>
				</div>
			</div>

		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>

<!-- end modal edit -->

<!-- modal alert -->
<div class="modal fade" id="modal-delete-alert" tabindex="-1"
	role="dialog" aria-labelledby="edit" aria-hidden="true">
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
<div class="modal fade" id="modal-message" tabindex="-1" role="dialog"
	aria-labelledby="edit" aria-hidden="true">
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
<script src="<c:url value='/resources/manager/commons/js/delete.js' />"></script>
<script src="<c:url value='/resources/commons/js/pagination.js' />"></script>
<script src="<c:url value='/resources/manager/commons/js/search.js' />"></script>
<script
	src="<c:url value='/resources/manager/admin/js/subject-management.js' />"></script>
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