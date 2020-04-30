<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!-- Page Content  -->
<div id="content" class="mb-5 container-fluid">
	<nav
		class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">

		<div class="custom-menu">
			<button id="sidebarCollapse" class="btn btn-link rounded-circle mr-3">
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
	<!-- End of Topbar -->
	<div class="container-fluid">
		<div class="d-sm-flex align-items-center justify-content-between mb-4">
			<h1 class="h4 mb-0 text-gray-600">Thêm Câu Hỏi</h1>

			<div class='justify-content-end d-flex'>
				<!--  	<a
						href="<c:url value='/teacher/ql-cau-hoi?subject=ALL&level=ALL&type=ALL'/>"
						class=" btn btn-sm btn-info shadow-sm btn-import"><i
						class="fas fa-list fa-sm text-white-50"></i> Danh Sách Câu Hỏi</a>-->
				<a href="#" class="ml-3 btn btn-sm btn-primary shadow-sm btn-import"><i
					class="fas fa-file-import fa-sm text-white-50"></i> Import Câu Hỏi</a>
			</div>


		</div>
		<!-- filter -->
		<div class="card mb-4 py-3 border-left-info">
			<div class="card-body">
				<h5 class="mb-4 font-weight-bold text-primary col-12 text-center">Thông
					Tin Câu Hỏi</h5>
				<div id="question-infor mt-3">
					<div class="row">
						<label class='col-2 col-form-label'>Môn Học (*)</label>
						<div class="input-group col-9">
							<input class="form-control" type="text" subjectId=''
								id="question-subject" readonly>
							<div class="input-group-append border-0">
								<button class="btn btn-outline-primary btn-select-subject"
									type="button">Chọn</button>
							</div>
						</div>
						<div class="col-1"></div>

					</div>

					<form class=" ">
						<div class="form-group row mt-3">
							<label for="inputName" class="col-2 col-form-label">Tên
								Câu Hỏi (*)</label>
							<div class="col-9">
								<input type="text" class="form-control" id="inputName" required>
							</div>
							<div class="col-1"></div>
						</div>
					</form>

					<div class="row mt-3">
						<div class='col-2'>
							<label>Loại Câu Hỏi (*)</label>
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
							<label>Độ Khó (*)</label>
						</div>

						<div class="form-group col-9">
							<select class="form-control" id="question-level">
								<option value='0'>Dễ</option>
								<option value='1' selected>Trung Bình</option>
								<option value='2'>Khó</option>
							</select>
						</div>
					</div>


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
							class="btn  waves-effect btn-submit disabled"
							id='btn-submit-subject'>Chọn</a> <a type="button"
							class="btn  waves-effect btn-submit disabled hidden"
							id='btn-select-file'>Chọn</a>
						<!--  <a
							type="button" class="btn  waves-effect btn-next hidden"
							id='btn-next-step'>Bỏ Qua</a> -->

					</div>
				</div>
			</div>

		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>
<!-- modal choose file -->
<!-- modal add student to course -->
<div class="modal modal-reset fade" id="modal-file" tabindex="-1"
	role="dialog" aria-labelledby="edit" aria-hidden="true">
	<div class="modal-dialog modal-xl">
		<div class="modal-content">
			<!--Header-->
			<div class="modal-header">
				<p class="heading lead">Import Câu Hỏi</p>

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
							<form class="form-detail" id="form-exel-file">
								<h2>Import Câu Hỏi Từ File Exel</h2>

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
	<!-- /.modal-dialog -->
</div>

<!-- /.modal-dialog -->
<!-- end modal message -->
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
<!-- modal alert -->
<div class="modal modal-reset fade" id="modal-alert" tabindex="-1"
	role="dialog" aria-labelledby="edit" aria-hidden="true">
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
				<div class="message">Xác Nhận Lưu Câu Hỏi ?</div>

				<div class="border-top my-3"></div>
				<div class="d-flex justify-content-center">
					<a type="button" class="btn  waves-effect btn-cancel"
						data-dismiss="modal">Hủy</a><a type="button"
						class="btn  waves-effect btn-submit">Lưu</a>
				</div>
			</div>

		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->

</div>

<!-- end modal alert -->
