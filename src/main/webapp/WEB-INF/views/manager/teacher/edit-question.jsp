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
	href="<c:url value='/resources/commons/lib/summernote/summernote-audio.css' />">
<link rel="stylesheet"
	href="<c:url value='/resources/commons/lib/summernote/summernote-bs4.min.css' />">
<link rel="stylesheet"
	href="<c:url value='/resources/manager/commons/css/add-question.css' />">

<!-- Phan noi dung trang -->
<div class="wrapper d-flex align-items-stretch">
	<jsp:include page="/WEB-INF/views/manager/teacher/menu.jsp"></jsp:include>

	<!-- Page Content  -->
	<div id="content" class="mb-5">
		<nav
			class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">

			<!-- Sidebar Toggle (Topbar) -->
			<button id="sidebarToggleTop"
				class="btn btn-link d-md-none rounded-circle mr-3">
				<i class="fa fa-bars"></i>
			</button>
			<!-- Topbar Search -->
			<!-- Topbar Navbar -->
			<ul class="navbar-nav ml-auto">

				<!-- Nav Item - Search Dropdown (Visible Only XS) -->
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
						<a class="dropdown-item" href="#" data-toggle="modal"
							data-target="#logoutModal"> <i
							class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
							Logout
						</a>
					</div></li>

			</ul>

		</nav>
		<!-- End of Topbar -->
		<div class="container-fluid">
			<div
				class="d-sm-flex align-items-center justify-content-between mb-4">
				<h1 class="h4 mb-0 text-gray-600">Chỉnh Sửa Câu Hỏi</h1>
				<a href="#" class="btn btn-reload btn-primary btn-icon-split btn-sm">
                    <span class="icon text-white-50">
                     <i class="fas fa-redo"></i>
                    </span>
                    <span class="text">Reset</span>
                  </a>
			</div>
			<!-- filter -->
			<div class="card mb-4 py-3 border-left-info">
				<div class="card-body">
					<h5 class="mb-4 font-weight-bold text-primary col-12 text-center">Thông
						Tin Câu Hỏi</h5>
					<div id="question-infor">
						<div class="row">
							<div class='col-2'>
								<label>Môn Học</label>
							</div>

							<div class="input-group col-9 mt-3">
								<input class="form-control" type="text" placeholder="Không Chọn"
									subjectId='' id="question-subject" readonly>
								<div class="input-group-append border-0">
									<button class="btn btn-outline-primary btn-select-subject"
										type="button">Chọn</button>
								</div>
							</div>
							<div class="col-1"></div>

						</div>
						<div class="row mt-3">
							<div class='col-2'>
								<label>Loại Câu Hỏi</label>
							</div>

							<div class="form-group col-9">
								<select class="form-control" id="question-type">
									<option value='ONE_CHOICE'>Một Đáp Án</option>
									<option value='MULTIPLE_CHOICE'>Nhiều Đáp Án</option>
									<option value='FILL_WORD'>Điền Từ</option>
								</select>
							</div>
						</div>

						<div class="row">
							<div class='col-2'>
								<label>Độ Khó</label>
							</div>

							<div class="form-group col-9">
								<select class="form-control" id="question-level">
									<option value='0'>Dễ</option>
									<option value='1' selected>Trung Bình</option>
									<option value='2'>Khó</option>
								</select>
							</div>
						</div>
						<!--  
				<form>
					<div class="form-group row">
						<label for="inputPassword" class="col-2 col-form-label">Mã
							Câu Hỏi</label>
						<div class="col-9">
							<input type="text" class="form-control" id="inputCode" required
								placeholder="code">
						</div>
						<div class="col-1"></div>
					</div>
				</form>-->
						<form>

							<div class="form-group row">
								<label for="inputName" class="col-2 col-form-label">Tên
									Câu Hỏi</label>
								<div class="col-9">
									<input type="text" class="form-control" id="inputName" required
										placeholder="name">
								</div>
								<div class="col-1"></div>
							</div>
						</form>
					</div>
				</div>
			</div>


			<!-- end filter -->

			<div class="card mb-4 py-3">
				<div class="card-body pl-4">
					<h5 class="mb-4 font-weight-bold text-primary col-12 text-center">Nội
						Dung Câu Hỏi</h5>
					<div class="question-wrap mt-3">

						<div class="d-flex row question">
							<label class="col-2 text-lg">Câu Hỏi</label>
							<div class="col-9 wrap-editor ">
								<div id="question-editor"></div>
							</div>
						</div>

						<div id='wrap-answer'>
							<div id='answer-sample' class='hidden'>
								<jsp:include
									page="/WEB-INF/views/manager/teacher/input-question-tag.jsp"></jsp:include>
							</div>

						</div>

						<!-- bottom -->
						<div class="d-flex row mt-4">
							<div class='col-11'></div>
							<div class="col-1 justify-content-start">

								<button
									class="btn text-success btn-control p-0 m-0 btn-add-answer">
									<i class="fas fa-plus-square"></i>
								</button>
							</div>
						</div>


					</div>
				</div>
			</div>

			<!-- table -->

			<div class='row d-flex justify-content-center mt-4'>
				<button type="button" class="btn btn-success btn-submit"
					id="btn-submit-question">Lưu Câu Hỏi</button>
			</div>
			<!-- end table -->
		</div>

	</div>

</div>

<!-- modal select subject -->
<div class="modal modal-reset fade" id="modal-select-subject"
	tabindex="-1" role="dialog" aria-labelledby="edit" aria-hidden="true">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<!--Header-->
			<div class="modal-header">
				<p class="heading lead">Chọn Môn Học</p>

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
					<div class="table-responsive pl-3 pr-3  w-100">
						<table class="table table-bordered w-100" id="dataTable"
							cellspacing="0">
							<!--  
							<thead>
								<tr>
									<th class='column-0'>STT</th>
									<th class='column-1'>Mã Môn Học</th>
									<th class='column-2'>Tên Môn Học</th>
								</tr>
							</thead>
							-->
							<tbody id="table-data-body">
								<tr id="row-data-container">
									<td class='column-0' field='index'></td>
									<td class='column-1' field='code'></td>
									<td class='column-2' field='subjectName'></td>
								</tr>


							</tbody>
						</table>

					</div>
					<div class="p-4" id='pagination'>
						<jsp:include page="/WEB-INF/views/commons/pagination.jsp"></jsp:include>
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
							class="btn  waves-effect btn-submit disabled" id='btn-submit-subject'>Chọn</a>
							<a type="button"
							class="btn  waves-effect btn-submit disabled hidden" style="margin-right: 10% ;" id='btn-select-file'>Chọn</a>
							<a type="button"
							class="btn  waves-effect btn-next hidden" id='btn-next-step' >Bỏ Qua</a>
							
					</div>
				</div>
			</div>

		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>
<!-- modal choose file -->

<!-- modal alert -->
<div class="modal modal-reset fade" id="modal-alert"
	tabindex="-1" role="dialog" aria-labelledby="edit" aria-hidden="true">
	<div class="modal-dialog modal-sm">
		<div class="modal-content">
			<!--Header-->
			<div class="modal-header bg-success">
				<p class="heading lead">Xác Nhận</p>

				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true" class="white-text">×</span>
				</button>
			</div>

			<!--Body-->
			<div class="modal-body border-top my-3">
				<div class="message">Bạn Có Muốn Tiếp Tục Lưu Thay Đổi ?</div>

				<div class="border-top my-3"></div>
				<div class="d-flex justify-content-center">
					<a type="button" class="btn  waves-effect btn-cancel"
						data-dismiss="modal">Hủy</a><a type="button"
						class="btn  waves-effect btn-submit">OK</a>
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
<script src="<c:url value='/resources/commons/js/pagination.js' />"></script>
<script src="<c:url value='/resources/manager/commons/js/search.js' />"></script>
<script
	src="<c:url value='/resources/commons/lib/summernote/summernote-bs4.min.js' />"></script>
<script
	src="<c:url value='/resources/commons/lib/summernote/summernote-bs4.min.js' />"></script>
<script
	src="<c:url value='/resources/commons/lib/summernote/summernote-audio.js' />"></script>
<script
	src="<c:url value='/resources/manager/teacher/js/edit-question.js' />"></script>


<!-- Footer -->
<script type="text/javascript">
	$('#sidebar .active').removeClass('active');
	$('#menu-item-home').addClass('active');
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