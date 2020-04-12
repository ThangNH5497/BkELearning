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
	href="<c:url value='/resources/manager/commons/css/question-list.css' />">

<!-- Phan noi dung trang -->
<div class="wrapper d-flex align-items-stretch">
	<jsp:include page="/WEB-INF/views/manager/teacher/menu.jsp"></jsp:include>

	<!-- Page Content  -->
	<div id="content" class="mb-5">
		<jsp:include page="/WEB-INF/views/manager/commons/topbar.jsp"></jsp:include>
		<div class="container-fluid">

			<div
				class="d-sm-flex align-items-center justify-content-between mb-4">
				<h1 class="h3 mb-0 text-gray-800">Ngân Hàng Câu Hỏi</h1>
				<a href="<c:url value='/teacher/them-cau-hoi'/>"
					class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm"><i
					class="fas fa-plus fa-sm text-white-50"></i>Thêm Câu Hỏi</a> <a
					href="#"
					class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm"><i
					class="fas fa-file-import fa-sm text-white-50"></i> Import Câu Hỏi</a>
			</div>
			<!-- table -->
			<!-- Area Chart -->
			<div class="col-xl-12 col-lg-12 card-container">
				<div class="card shadow h-100">
					<!-- Card Header - Dropdown -->
					<div
						class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
						<h6 class="m-0 font-weight-bold text-primary">Danh Sách Câu
							hỏi</h6>

						<div class="d-flex">
							<button class="btn btn-edit text-primary disabled  btn-control">
								<i class="fas fa-edit"></i>
							</button>
							<button class="btn btn-delete text-danger btn-control">
								<i class="fas fa-trash"></i>
							</button>
							<button class="btn btn-refresh text-primary btn-control">
								<i class="fas fa-sync"></i>
							</button>
							<jsp:include page="/WEB-INF/views/commons/pagination.jsp"></jsp:include>
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
														<th class="table-cell column2">Mã Câu Hỏi</th>
														<th class="table-cell column3">Tên Câu Hỏi</th>
														<th class="table-cell column4 ">Nội Dung</th>
														<th class="table-cell column5">Tùy Chọn</th>

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
														<td class="table-cell column1" field="index"></td>
														<td class="table-cell column2" field="code"></td>
														<td class="table-cell column3" field="name"></td>
														<td class="table-cell column4 text-truncate"
															field="content"></td>
														<td class="table-cell column5">
															<button
																class="btn btn-view text-success justify-content-center"
																style="box-shadow: none;">
																<i class="far fa-eye"></i>
															</button>
															<button
																class="btn btn-view text-primary justify-content-center"
																style="box-shadow: none;">
																<i class="far fa-edit"></i>
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

			<!-- end table -->
		</div>

	</div>

</div>


<!-- modal view -->
<div class="modal modal-reset fade" id="modal-view" tabindex="-1"
	role="dialog" aria-labelledby="edit" aria-hidden="true">
	<div class="modal-dialog modal-xl">
		<div class="modal-content">
			<!--Header-->
			<div class="modal-header">
				<p class="heading lead">Chi Tiết Câu Hỏi</p>

				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true" class="white-text">×</span>
				</button>
			</div>

			<!--Body-->
			<div class="modal-body">
				<div class="form-container">
					<div class="" id='question-container'></div>

					<!--Footer-->
					<div class="border-top my-3"></div>
					<div class="d-flex justify-content-center">
						<a type="button" class="btn  waves-effect btn-cancel"
							data-dismiss="modal">Hủy</a><a type="button"
							class="btn  waves-effect btn-submit">Lưu</a>
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
<script
	src="<c:url value='/resources/manager/teacher/js/question-list.js' />"></script>
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