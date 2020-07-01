<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!-- Page Content  -->
<div id="content" class="mb-5">

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
					<a class="dropdown-item" href="#" id="btn-edit-profile"> <i
						class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i>Cập Nhật
						Thông Tin
					</a> <a class="dropdown-item" href="#"  id="btn-change-password"> <i
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
			<h1 class="h4 mb-0 text-gray-600">Thêm Đề Thi</h1>
		</div>
		<!-- filter -->
		<div class="card mb-4 py-3 border-left-info" id="exampaper-info">
			<div class="card-body">
				<h5 class="mb-4 font-weight-bold text-primary col-12 text-center">Thông
					Tin Đề Thi</h5>
				<div id="question-infor mt-3">
					<div class="row">
						<label class='col-2 col-form-label'>Môn Học (*)</label>
						<div class="input-group col-9">
							<input class="form-control" type="text" subjectId='' value=""
								id="input-subject" readonly required>
							<div class="input-group-append border-0">
								<button class="btn btn-outline-primary btn-select-subject"
									type="button">Chọn</button>
							</div>
						</div>
						<div class="col-1"></div>

					</div>

					<form class=" ">
						<div class="form-group row mt-3">
							<label for="inputName" class="col-2 col-form-label">Mã Đề Thi
								(*)</label>
							<div class="col-9">
								<input type="text" class="form-control" id="input-code" required>
							</div>
							<div class="col-1"></div>
						</div>
					</form>
					
					<form class=" ">
						<div class="form-group row mt-3">
							<label for="inputName" class="col-2 col-form-label">Tên Đề Thi
								(*)</label>
							<div class="col-9">
								<input type="text" class="form-control" id="input-name" required>
							</div>
							<div class="col-1"></div>
						</div>
					</form>
					
					<form class=" ">
						<div class="form-group row mt-3">
							<label for="inputName" class="col-2 col-form-label">Thời Gian
								(*)</label>
							<div class="col-9">
								<input type="text" class="form-control" id="input-time" required>
							</div>
							<div class="col-1"></div>
						</div>
					</form>

					<form>
						<div class="form-group row mt-3">
							<label for="inputName" class="col-2 col-form-label">Mô Tả</label>
							<div class="col-9">
								<textarea class="form-control" id="input-descriptor"
									rows="3"></textarea>
							</div>
							<div class="col-1"></div>
						</div>
					</form>
					
				</div>
			</div>
		</div>




		<!-- table -->

		<div class='row d-flex justify-content-center mt-4'>
			<button type="button" class="btn btn-primary btn-submit"
				id="btn-submit-exampaper">Tạo Đề Thi</button>
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
							id='btn-submit-subject'>Chọn</a>

					</div>
				</div>
			</div>

		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>