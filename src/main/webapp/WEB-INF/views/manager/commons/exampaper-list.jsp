<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- Page Content  -->
<div id="content" class="mb-5">
	<jsp:include page="/WEB-INF/views/manager/commons/topbar.jsp"></jsp:include>
	<div class="container-fluid">

		<div
			class="d-flex align-items-center justify-content-between mb-4 row">
			<h1 class="col-8 h5 mb-0 text-gray-600">Ngân hàng đề</h1>
			<a href="#"
				class=" col-1 btn btn-sm btn-primary shadow-sm btn-add-exampaper"><i
				class="fas fa-plus fa-sm text-white-50"></i>Thêm</a>
		</div>
		<!-- filter -->
		<nav class="navbar navbar-expand-lg navbar-light bg-white mb-4"
			id='filter'>

			<div class="custom-menu ">
				<button id="sidebarCollapse"
					class="btn btn-link mr-3 navbar-toggler text-primary border-0"
					data-toggle="collapse" data-target="#navbarSupportedContent"
					aria-controls="navbarSupportedContent" aria-expanded="false"
					aria-label="Toggle navigation">
					<i class="fa fa-bars"></i>
				</button>
			</div>

			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<div class="row d-flex w-100">
					<div class="d-block col-md-12 col-lg-4 filter-item"
						id="filter-subject">
						<h6 class="m-0 font-weight-bold text-primary col-9">Môn học</h6>
						<div class="input-group col-lg-11 col-md-12 d-flex mt-3">
							<input class="form-control" type="text" val='ALL' readonly>
							<div class="input-group-append border-0 dropdown">
								<button
									class="btn btn-outline-primary btn-select-subject dropdown-toggle"
									id="dropdownMenuButton" data-toggle="dropdown"
									aria-haspopup="true" aria-expanded="false" type="button">Chọn</button>
								<div
									class="dropdown-menu dropdown-menu-right dropdown-menu-w-100"
									aria-labelledby="dropdownMenuButton">
									<a class="dropdown-item" href="#" value='ALL'>Tất cả</a>
									<div class="dropdown-divider"></div>
									<a class="dropdown-item" href="#" value=''>Tìm môn học</a>

								</div>
							</div>
						</div>
					</div>
				</div>
		</nav>
		<!-- end filter -->
		<!-- table -->
		<!-- Area Chart -->
		<div class="col-xl-12 col-lg-12 card-container">
			<div class="card shadow h-100">
				<!-- Card Header - Dropdown -->
				<div
					class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
					<h6
						class="m-0 font-weight-bold text-primary text-responsive-lg d-none d-sm-block"">Danh
						sách đề thi</h6>
					<div class="m-0"></div>
					<div class="d-flex">
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
																<a class="dropdown-item" id="select-all">Chọn tất cả</a>
																<a class="dropdown-item" id="deselect-all">Bỏ chọn tất cả</a>
															</div>
														</div>
													</th>
													<th class="table-cell column1">Mã đề</th>
													<th class="table-cell column2">Môn học</th>
													<th class="table-cell column3">Tên đề</th>
													<th class="table-cell column4 ">Mô tả</th>
													<th class="table-cell column5 ">Thời gian</th>
													<th class="table-cell column6">Ngày tạo</th>
													<th class="table-cell column7">Ngày sửa</th>
													<th class="table-cell column8">Tùy chọn</th>

												</tr>

											</thead>
										</table>
									</div>

									<div class="table-data-body js-pscroll">
										<table>
											<tbody id="table-data-body">

												<tr class="table-row body hidden" id="row-data-container">

													<td class="table-cell column0" field='checkBox'><div
															class="custom-control custom-checkbox">
															<input type="checkbox" class="custom-control-input"
																id="check-1"> <label
																class="custom-control-label" for="check-1"></label>
														</div></td>
													<td class="table-cell column1" field="code"></td>
													<td class="table-cell column2" field="subject.subjectName"></td>
													<td class="table-cell column3" field="name"></td>
													<td class="table-cell column4" field="descriptor"></td>
													<td class="table-cell column5" field="time"></td>
													<td class="table-cell column6" field="createAt"></td>
													<td class="table-cell column7" field="updateAt"></td>
													<td class="table-cell column8">
														<button
															class="btn btn-download text-success justify-content-center"
															style="box-shadow: none;">
															<i class="fas fa-file-download"></i>
														</button>
														<button
															class="btn btn-edit text-primary justify-content-center"
															style="box-shadow: none;">
															<i class="far fa-edit"></i>
														</button>
													</td>

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

		<!-- end table -->
	</div>

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
				<div class="message">Bạn có muốn tiếp tục xóa ?</div>

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

<!-- modal select subject -->
<div class="modal  fade" id="modal-select-subject" tabindex="-1"
	role="dialog" aria-labelledby="edit" aria-hidden="true">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<!--Header-->
			<div class="modal-header">
				<p class="heading lead">Chọn môn học</p>

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
								<input id="key-search-subject" type="text"
									class="form-control bg-light border-0 small"
									placeholder="Nhập Mã Hoặc Tên..." aria-label="Search"
									aria-describedby="basic-addon2">
								<div class="input-group-append">
									<button class="btn " type="button" id="btn-search-subject"
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
							<tbody id="table-data-body-subject">
								<tr id="row-data-container-subject">
									<td class='column-0' field='index'></td>
									<td class='column-1' field='code'></td>
									<td class='column-2' field='subjectName'></td>
								</tr>


							</tbody>
						</table>

					</div>
					<div class="p-4" id='pagination-subject'>
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
							class="btn  waves-effect btn-submit disabled">Chọn</a>
					</div>
				</div>
			</div>

		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>
<!-- end modal select subject -->
<!-- modal select download  type -->
<div class="modal  fade" id="modal-download" tabindex="-1" role="dialog"
	aria-labelledby="edit" aria-hidden="true">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<!--Header-->
			<div class="modal-header">
				<p class="heading lead">Tải xuống đề thi</p>

				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true" class="white-text">×</span>
				</button>
			</div>

			<!--Body-->
			<div class="modal-body pl-0 pr-0 pt-0">
				<div class="row m-4 p-4">
					<div class="card col-5 shadow mb-4 exampaper-type" type="basic" border="false">
						<div class="card-header py-3">
							<h6 class="m-0 font-weight-bold text-primary">Kiểu cơ bản</h6>
						</div>
						<div class="card-body">
							<img style="width: 100%;" alt=""
								src="<c:url value='/resources/commons/image/web/exampaper-basic.png' />">
						</div>
					</div>
					<div class="col-2"></div>
					<div class="card col-5 shadow mb-4 exampaper-type" type="double" border="false">
						<div class="card-header py-3">
							<h6 class="m-0 font-weight-bold text-primary">Chia đôi</h6>
						</div>
						<div class="card-body">
							<img style="width: 100%;" alt=""
								src="<c:url value='/resources/commons/image/web/exampaper-double.png' />">
						</div>
					</div>
					
				</div>
				
				<div class="row m-4 p-4">
					<div class="card col-5 shadow mb-4 exampaper-type" type="basic" border="true">
						<div class="card-header py-3">
							<h6 class="m-0 font-weight-bold text-primary">Kiểu cơ bản có viền</h6>
						</div>
						<div class="card-body">
							<img style="width: 100%;" alt=""
								src="<c:url value='/resources/commons/image/web/exampaper-basic-border.png' />">
						</div>
					</div>
					<div class="col-2"></div>
					<div class="card col-5 shadow mb-4 exampaper-type" type="double" border="true">
						<div class="card-header py-3">
							<h6 class="m-0 font-weight-bold text-primary">Chia đôi có viền</h6>
						</div>
						<div class="card-body">
							<img style="width: 100%;" alt=""
								src="<c:url value='/resources/commons/image/web/exampaper-double-border.png' />">
						</div>
					</div>
					
				</div>
				<div>
					<div class="border-top my-3"></div>
					<div class="d-flex justify-content-center">
						<a type="button" class="btn  waves-effect btn-cancel"
							data-dismiss="modal">Hủy</a><a type="button"
							class="btn  waves-effect btn-submit disabled">Tải xuống</a>
					</div>
				</div>
			</div>

		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>


