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
	<nav id="sidebar">
		<div class="custom-menu">

			<button type="button" id="sidebarCollapse" class="btn btn-primary">
				<i class="fa fa-bars"></i> <span class="sr-only">Toggle Menu</span>
			</button>

		</div>
		<div class="p-4">
			<h1>
				<a href="index.html" class="logo">BK ELearning <span>Admin
						page</span></a>
			</h1>
			<ul class="list-unstyled components mb-5">
				<li class="active"><a href="#"><span
						class="icon fa fa-briefcase mr-3"></span> <span class="redirect">Trang
							Chủ</span></a></li>

				<li><a href="#"><span class="icon fa fa-cogs mr-3"></span>
						<span class="redirect">Tài Khoản</span></a></li>


				<li><a href="#"><span class="icon fa fa-briefcase mr-3"></span>
						<span class="redirect">Môn Học</span></a></li>
				<li><a href="#"><span class="icon fa fa-sticky-note mr-3"></span>
						<span class="redirect">Thông Tin</span></a></li>

				<li><a href="#"><span class="icon fa fa-cogs mr-3"></span>
						<span class="redirect">Hệ Thống</span></a></li>
				<li><a href="#"><span class="icon fa fa-cogs mr-3"></span>
						<span class="redirect">Đăng Xuất</span></a></li>



			</ul>

			<div class="mb-5">
				<!-- 
				<h3 class="h6 mb-3">Subscribe for newsletter</h3>
				<form action="#" class="subscribe-form">
					<div class="form-group d-flex">
						<div class="icon">
							<span class="icon-paper-plane"></span>
						</div>
						<input type="text" class="form-control"
							placeholder="Enter Email Address">
					</div>
				</form>
				 -->
			</div>

			<div class="footer"></div>

		</div>
	</nav>

	<!-- Page Content  -->
	<div id="content">

		<!-- Topbar -->
		<nav
			class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">

			<!-- Sidebar Toggle (Topbar) -->
			<button id="sidebarToggleTop"
				class="btn btn-link d-md-none rounded-circle mr-3">
				<i class="fa fa-bars"></i>
			</button>

			<!-- Topbar Search -->
			<form
				class="d-none d-sm-inline-block form-inline mr-auto ml-md-3 my-2 my-md-0 mw-100 navbar-search">
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

			<!-- Content Row -->

			<div class="row ">

				<!-- Area Chart -->
				<div class="col-xl-9 col-lg-8 card-container">
					<div class="card shadow mb-4 h-100">
						<!-- Card Header - Dropdown -->
						<div
							class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
							<h6 class="m-0 font-weight-bold text-primary">Danh Sách
								Giảng Viên</h6>
							<div class="d-flex">
								<button class="btn btn-add text-success"
									style="margin-right: 10px;">
									<i style="font-size: 20px;" class="far fa-plus-square"></i>
								</button>
								<button class="btn btn-edit text-primary"
									style="margin-right: 10px;">
									<i style="font-size: 20px;" class="fas fa-edit"></i>
								</button>
								<button class="btn btn-delete text-danger"
									style="margin-right: 10px;">
									<i style="font-size: 20px;" class="fas fa-trash"></i>
								</button>
								<nav aria-label="Page navigation">

									<ul class="pagination justify-content-end">

										<li class="page-item disabled"><a class="page-link"
											id="pagination-title">Hiển Thị : </a></li>
										<li class="page-item disabled" id="prePage"><a
											class="page-link" tabindex="-1">Previous</a></li>
										<div id="pagination" style="display: flex;"></div>

										<li class="page-item disabled" id="nextPage"><a
											class="page-link">Next</a></li>
									</ul>
								</nav>
							</div>


						</div>
						<!-- Card Body -->
						<div class="card-body">
							<div class="wrap-table100 h-100">
								<div class="table100  m-b-110">
									<div class="table100-head">
										<table>
											<thead>
												<tr class="row100 head">
													<th class="cell100 column1">Chọn</th>
													<th class="cell100 column2">Mã Số</th>
													<th class="cell100 column3">Họ Tên</th>
													<th class="cell100 column4">Bộ Môn</th>
													<th class="cell100 column5">Chức Vụ</th>
													<th class="cell100 column6">Ngày Sinh</th>

												</tr>
											</thead>
										</table>
									</div>

									<div class="table100-body js-pscroll">
										<table>
											<tbody id="table-data-body">
												<tr class="row100 body hidden" id="row-data-container">

													<td class="cell100 column1"><input type="checkbox"
														class="checkthis" /></td>
													<td class="cell100 column2" field="code">Like a
														butterfly</td>
													<td class="cell100 column3" field="fullName">Boxing</td>
													<td class="cell100 column4" field="department">9:00 AM
														- 11:00 AM</td>
													<td class="cell100 column5" field=position>Aaron
														Chapman</td>
													<td class="cell100 column6" field="dateOfBirth">10</td>
												</tr>


											</tbody>
										</table>
									</div>
								</div>
							</div>
						</div>

					</div>
				</div>

				<!-- Pie Chart -->
				<div class="col-xl-3 col-lg-4 card-container">
					<div class="card shadow mb-4 h-100">
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
											<img class='hidden' field="image" style="width: 100%; max-height: 20vh;"
												src="" alt="" />
										</div>
									</div>
								</div>
								<div class="tab-content profile-tab" id="myTabContent">
									<div class="tab-pane fade show active" id="user-detail"
										role="tabpanel" aria-labelledby="home-tab">
										<div id="user-detail-row" class="hidden">
										<div >
											<div class="border-top my-3"></div>
											<div class="row">
												<div class="col-md-4">
													<label>Mã GV</label>
												</div>
												<div class="col-md-8">
													<p field="code">KTMT1</p>
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
													<p field="fullName">Kshiti Ghelani</p>
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
													<p field="email">kshitighelani@gmail.com</p>
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
													<p field="phoneNumber">123 456 7890</p>
												</div>
											</div>
										</div>
										<div>
											<div class="border-top my-3"></div>
											<div class="row">
												<div class="col-md-4">
													<label>Bộ Môn</label>
												</div>
												<div class="col-md-8">
													<p field="department">Web Developer and Designer</p>
												</div>
											</div>
										</div>
										<div>
											<div class="border-top my-3"></div>
											<div class="row">
												<div class="col-md-4">
													<label>Chức Vụ</label>
												</div>
												<div class="col-md-8">
													<p field="position">Web Developer and Designer</p>
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
													<p field="addr">Web Developer and Designer</p>
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
													<p field="dateOfBirth">Web Developer and Designer</p>
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
</div>
<jsp:include page="/WEB-INF/views/commons/lib.jsp"></jsp:include>
<script src="<c:url value='/resources/commons/js/base.js' />"></script>
<script src="<c:url value='/resources/manager/admin/js/teacher-manager.js' />"></script>
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