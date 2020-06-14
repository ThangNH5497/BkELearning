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

		<div class="container-content container-fluid">
			<nav class="navbar navbar-light " style="font-weight: 600;"
				>

				<span class="navbar-text"> <i class="fas fa-arrow-left"></i><a
					class='text-primary' href="#" id="back-link">Quay Lại Danh Sách Bài Thi</a></span>

			</nav>
			<div class="card shadow mb-4 mt-4">
				<div class="card-header py-3">
					<h6 class="m-0 font-weight-bold text-primary">Danh Sách Bài
						Thi Cần Xác Nhận</h6>
				</div>
				<div class="card-body">
					<div class="table-responsive">
						<div id="dataTable_wrapper"
							class="dataTables_wrapper dt-bootstrap4 m-0 p-0"
							style="width: 100%;">

							<div class="row p-0 m-0">
								<div class="col-sm-12">
									<table class="table table-bordered dataTable" id="dataTable"
										cellspacing="0" role="grid" aria-describedby="dataTable_info">
										<thead>
											<tr role="row">
												<th class="sorting_asc" tabindex="0"
													aria-controls="dataTable" rowspan="1" colspan="1"
													aria-sort="ascending">STT</th>
												<th class="sorting" tabindex="0" aria-controls="dataTable"
													rowspan="1" colspan="1">Mã Đề</th>

												<th class="sorting" tabindex="0" aria-controls="dataTable"
													rowspan="1" colspan="1">Mã Sinh Viên</th>

												<th class="sorting" tabindex="0" aria-controls="dataTable"
													rowspan="1" colspan="1">Tên Sinh Viên</th>

												<th class="sorting" tabindex="0" aria-controls="dataTable"
													rowspan="1" colspan="1">Chấm Bài</th>
											</tr>
										</thead>
										<tfoot>
											<tr>
												<th rowspan="1" colspan="1">STT</th>
												<th rowspan="1" colspan="1">Mã Đề</th>
												<th rowspan="1" colspan="1">Mã Sinh Viên</th>
												<th rowspan="1" colspan="1">Tên Sinh Viên</th>
												<th rowspan="1" colspan="1">Chấm Bài</th>

											</tr>
										</tfoot>
										<tbody id='table-data-body'>
											<tr role="row" class="odd hidden" id='row-data-container'>
												<td field="index"></td>
												<td field="code"></td>

												<td field="studentCode"></td>

												<td field="studentName"></td>

												<td field="control" class='justify-content-center d-flex'><button
														class="btn btn-mark text-primary justify-content-center"
														style="box-shadow: none;">
														Chấm Bài <i class="fas fa-arrow-circle-right"></i>
													</button></td>

											</tr>

										</tbody>
									</table>
								</div>
							</div>
							<div class="row m-0 p-0 justify-content-end">
								<div class="p-4" id='pagination'>
									<jsp:include page="/WEB-INF/views/commons/pagination.jsp"></jsp:include>
								</div>
							</div>
						</div>
					</div>


				</div>
			</div>


		</div>

	</div>

</div>


<jsp:include page="/WEB-INF/views/commons/lib.jsp"></jsp:include>
<script
	src="<c:url value='/resources/commons/lib/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js' />"></script>
<script src="<c:url value='/resources/commons/js/base.js' />"></script>
<script src="<c:url value='/resources/commons/js/pagination.js' />"></script>
<script src="<c:url value='/resources/manager/commons/js/search.js' />"></script>
<script
	src="<c:url value='/resources/manager/teacher/js/mark-list.js' />"></script>
<!-- Footer -->

<jsp:include page="/WEB-INF/views/manager/commons/footer.jsp"></jsp:include>