<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="/WEB-INF/views/manager/commons/header.jsp"></jsp:include>>
<link rel="stylesheet"
	href="<c:url value='/resources/commons/css/style.css' />">
<link rel="stylesheet"
	href="<c:url value='/resources/manager/commons/css/style.css' />">
<link rel="stylesheet"
	href="<c:url value='/resources/manager/commons/css/dashboard.css' />">
<link rel="stylesheet"
	href="<c:url value='/resources/manager/commons/css/question-list.css' />">
<!-- Phan noi dung trang -->
<div class="wrapper d-flex align-items-stretch">
	<jsp:include page="/WEB-INF/views/manager/admin/menu.jsp"></jsp:include>

	<!-- Page Content  -->
	<div id="content" class="mb-5">
		<jsp:include page="/WEB-INF/views/manager/commons/topbar.jsp"></jsp:include>
		<div class="container-fluid">

			<div
				class="d-flex align-items-center justify-content-between mb-4 row">
				<h1 class="col-8 h5 mb-0 text-gray-600">Ngân hàng câu hỏi giảng viên</h1>
			</div>
			<!-- filter -->
			<nav class="navbar navbar-expand-lg navbar-light bg-white mb-4"
				id='filetr'>

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
						<div class="d-block col-md-12 col-lg-6 filter-item mt-3"
							id="filter-teacher">
							<h6 class="m-0 font-weight-bold text-primary col-9">Giảng
								viên</h6>
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
										<a class="dropdown-item" href="#" value=''>Tìm giảng viên</a>

									</div>
								</div>
							</div>
						</div>


						<div class="d-block col-md-12 col-lg-6 filter-item mt-3"
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

						<div class="d-block col-md-12 col-lg-6 filter-item mt-3"
							id="filter-type">
							<h6 class="m-0 font-weight-bold text-primary col-9">Loại câu hỏi
								</h6>
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
										<a class="dropdown-item" href="#" value='ONE_CHOICE'>Một
											đáp án</a>
										<div class="dropdown-divider"></div>
										<a class="dropdown-item" href="#" value='MULTIPLE_CHOICE'>Nhiều
											đáp án</a>
										<div class="dropdown-divider"></div>
										<a class="dropdown-item" href="#" value='FILL_WORD'>Điền
											từ</a>
									</div>
								</div>
							</div>
						</div>

						<div class="d-block col-md-12 col-lg-6 filter-item mt-3"
							id="filter-level">
							<h6 class="m-0 font-weight-bold text-primary col-9">Độ khó</h6>
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
										<a class="dropdown-item" href="#" value="ALL">Tất cả</a>
										<div class="dropdown-divider"></div>
										<a class="dropdown-item" href="#" value="0">Dễ</a>
										<div class="dropdown-divider"></div>
										<a class="dropdown-item" href="#" value="1">Trung bình</a>
										<div class="dropdown-divider"></div>
										<a class="dropdown-item" href="#" value="2">Khó</a>
									</div>
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
							sách câu hỏi</h6>
						<div class="m-0"></div>
						<div class="d-flex">
							<button class="btn btn-copy text-success btn-control">
								<i class="fas fa-copy"></i>
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
																	<a class="dropdown-item" id="select-all">Chọn tất
																		cả</a> <a class="dropdown-item" id="deselect-all">Bỏ
																		chọn tất cả</a>
																</div>
															</div>
														</th>
														<th class="table-cell column1">STT</th>
														<th class="table-cell column2">Môn học</th>
														<th class="table-cell column3">Tên câu hỏi</th>
														<th class="table-cell column4 ">Nội dung</th>
														<th class="table-cell column5">Tùy chọn</th>

													</tr>

												</thead>
											</table>
										</div>

										<div class="table-data-body js-pscroll">
											<table>
												<tbody id="table-data-body">
													<h5 id='data-empty-alert'
														class="hidden mt-3 mb-3 w-100 d-flex justify-content-center font-weight-bold text-primary">Không
														Có Dữ Liệu</h5>
													<tr class="table-row body hidden" id="row-data-container">

														<td class="table-cell column0" field='checkBox'><div
																class="custom-control custom-checkbox">
																<input type="checkbox" class="custom-control-input"
																	id="check-1"> <label
																	class="custom-control-label" for="check-1"></label>
															</div></td>
														<td class="table-cell column1" field="index"></td>
														<td class="table-cell column2" field="subject.subjectName"></td>
														<td class="table-cell column3" field="name"></td>
														<td class="table-cell column4">
															<div class="d-flex">
																<div class="text-truncate d-flex" field="content"></div>
																<div class="pl-1 div-truncate"
																	style="font-weight: bold;">
																	<span>...</span>
																</div>
															</div>

														</td>
														<td class="table-cell column5">
															<button
																class="btn btn-view text-success justify-content-center"
																style="box-shadow: none;">
																<i class="far fa-eye"></i>
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

</div>


<!-- modal view -->
<div class="modal fade" id="modal-view" tabindex="-1" "
	role="dialog"
	aria-labelledby="edit" aria-hidden="true">
	<div class="modal-dialog modal-lg">
		<div class="modal-content" style="min-height: 50vh;">
			<!--Header-->
			<div class="modal-header">
				<p class="heading lead">Chi tiết câu hỏi</p>

				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true" class="white-text">×</span>
				</button>
			</div>

			<!--Body-->
			<div class="modal-body pl-0 pr-0 pt-0">
				<div class="" id='question-detail-container'>
					<div class="descriptor row p-4">
						<div class="col-6 row d-flex">
							<span class='text-gray-600  col-6'>ID câu hỏi</span> <span
								class='text-primary  col-6' field='id'></span>
						</div>
						<div class="col-6 row d-flex">
							<span class='text-gray-600  col-6'>Tên câu hỏi</span> <span
								class='text-primary  col-6' field='name'></span>
						</div>
					</div>
					<div class="descriptor row p-4">
						<div class="col-6 row d-flex">
							<div class="col-6">
								<span class='text-gray-600 '>Môn học</span>
							</div>
							<div class="col-6">
								<span class='text-primary ' field='category.subject.subjectName'></span>
							</div>

						</div>
						<div class="col-6 row d-flex">
							<span class='text-gray-600  col-6'>Người Tạo</span> <span
								class='text-primary  col-6' field='category.user.fullName'></span>
						</div>
					</div>
					<div class="descriptor row p-4">
						<div class="col-6 row d-flex">
							<span class='text-gray-600  col-6'>Độ khó</span> <span
								class='text-primary  col-6' field='level'></span>
						</div>
						<div class="col-6 row d-flex">
							<span class='text-gray-600  col-6'>Loại câu hỏi</span> <span
								class='text-primary  col-6' field='type'></span>
						</div>
					</div>
					<div class="m-3 p-4" style="background: #d2f4fa;">
						<div class=" justify-content-center d-block">
							<div class="w-100 d-block" field='content'></div>
						</div>
						<div class="row mt-3">
							<span class='text-lg' style="font-weight: 900;">Câu trả lời</span>
						</div>

						<div class='w-100' id='question-detail-answer' field='answer'>
							<div class='w-100 hidden' id='answer-sample'>
								<div class='row pl-4 pr-4 mt-3'>
									<div class="col-1 text-primary">
										<i class="fas fa-hand-point-right"></i>
									</div>
									<div class='col-10 d-flex'>

										<div answerField='content' class='align-self-center'></div>
										<div
											class='hidden text-success d-flex justify-content-start pl-3'
											answerField='correct'>
											<i class="fas fa-check align-self-center"></i>
										</div>
									</div>

								</div>
							</div>
						</div>
					</div>

					<!--Footer-->
				</div>
				<div>
					<div class="border-top my-3"></div>
					<div class="d-flex justify-content-center">
						<a type="button" class="btn  waves-effect btn-cancel"
							data-dismiss="modal" style="margin-left: 10% !important;">Đóng</a>
					</div>
				</div>
			</div>

		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>

<!-- modal alert -->
<div class="modal modal-reset fade" id="modal-alert" tabindex="-1"
	role="dialog" aria-labelledby="edit" aria-hidden="true">
	<div class="modal-dialog modal-sm">
		<div class="modal-content">
			<!--Header-->
			<div class="modal-header bg-info">
				<p class="heading lead">Xác nhận</p>

				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true" class="white-text">×</span>
				</button>
			</div>
			<!--Body-->
			<div class="modal-body border-top my-3">
				<div class="message"></div>

				<div class="border-top my-3"></div>
				<div class="d-flex justify-content-center">
					<a type="button" class="btn  waves-effect btn-cancel"
						data-dismiss="modal">Hủy</a><a type="button"
						class="btn  waves-effect btn-alert-ok btn-submit"
						data-dismiss="modal">OK</a>
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
							class="btn  waves-effect btn-submit disabled"
							id='btn-select-subject'>Chọn</a> <a type="button"
							class="btn  waves-effect btn-submit disabled hidden"
							id='btn-select-subject-copy'>Tiếp tục</a>
					</div>
				</div>
			</div>

		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>

<!-- modal select category for copy -->
<div class="modal  fade" id="modal-select-category" tabindex="-1"
	role="dialog" aria-labelledby="edit" aria-hidden="true">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<!--Header-->
			<div class="modal-header">
				<p class="heading lead">Chọn danh mục</p>

				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true" class="white-text">×</span>
				</button>
			</div>

			<!--Body-->

			<div class="modal-body p-4">
				<div class="row mt-3">
					<div class='col-2'>
						<label>Danh mục (*)</label>
					</div>

					<div class="form-group col-9">
						<select class="form-control" id="question-category">
							<!-- <option value=''>Chương 1</option> -->
						</select>
					</div>
				</div>
				<div>
					<div class="border-top my-3"></div>
					<div class="d-flex justify-content-center">
						<a type="button" class="btn waves-effect btn-cancel"
							data-dismiss="modal">Hủy</a><a type="button"
							class="btn  waves-effect btn-submit">Xác nhận</a>
					</div>
				</div>
			</div>

		</div>

	</div>
	<!-- /.modal-content -->
</div>
<!-- /.modal-dialog -->
<!-- end modal select category -->

<!-- modal select teacher-->

<!-- modal select subject -->
<div class="modal  fade" id="modal-select-teacher" tabindex="-1"
	role="dialog" aria-labelledby="edit" aria-hidden="true">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<!--Header-->
			<div class="modal-header">
				<p class="heading lead">Chọn giảng viên</p>

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
								<input id="key-search-teacher" type="text"
									class="form-control bg-light border-0 small"
									placeholder="Nhập Mã Hoặc Tên..." aria-label="Search"
									aria-describedby="basic-addon2">
								<div class="input-group-append">
									<button class="btn " type="button" id="btn-search-teacher"
										style="background: #33b5e5; border-color: #33b5e5">
										<i class="fas fa-search fa-sm text-white"></i>
									</button>
								</div>
							</div>
						</form>
					</nav>
					<div class="table-responsive pl-3 pr-3  w-100">
						<table class="table table-bordered w-100" cellspacing="0">
							<!--  
							<thead>
								<tr>
									<th class='column-0'>STT</th>
									<th class='column-1'>Mã Môn Học</th>
									<th class='column-2'>Tên Môn Học</th>
								</tr>
							</thead>
							-->
							<tbody id="table-data-body-teacher">
								<tr id="row-data-container-teacher">
									<td class='column-0' field='index'></td>
									<td class='column-1' field='code'></td>
									<td class='column-2' field='fullName'></td>
								</tr>


							</tbody>
						</table>

					</div>
					<div class="p-4" id='pagination-teacher'>
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
							class="btn  waves-effect btn-submit disabled">Tiếp tục</a>
					</div>
				</div>
			</div>

		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>
<!-- end modal select teacher -->
<!-- modal select category-->
<div class="modal modal-reset fade" id="modal-add-category" tabindex="-1"
	role="dialog" aria-labelledby="edit" aria-hidden="true">
	<div class="modal-dialog modal-xl">
		<div class="modal-content">
			<!--Header-->
			<div class="modal-header">
				<p class="heading lead">Thêm danh mục</p>

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
								<h2>Thông tin danh mục</h2>

								<div class="form-row">
									<label for="code">Tên danh mục (*)</label> <input type="text"
										name="name" class="input-text" required> <label
										name="name-error" class="error hidden">Tên danh mục trống</label>

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

<jsp:include page="/WEB-INF/views/commons/lib.jsp"></jsp:include>
<script src="<c:url value='/resources/commons/js/base.js' />"></script>
<script src="<c:url value='/resources/commons/js/pagination.js' />"></script>
<script src="<c:url value='/resources/manager/commons/js/search.js' />"></script>
<script src="<c:url value='/resources/manager/commons/js/delete.js' />"></script>
<script
	src="<c:url value='/resources/manager/commons/js/question-list-base.js' />"></script>
<script
	src="<c:url value='/resources/manager/admin/js/question-list-teacher.js' />"></script>
<!-- Footer -->
<jsp:include page="/WEB-INF/views/manager/commons/footer.jsp"></jsp:include>