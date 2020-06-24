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
	href="<c:url value='/resources/manager/teacher/css/exam-result.css' />">
<!-- Phan noi dung trang -->
<div class="wrapper d-flex align-items-stretch">
	<jsp:include page="/WEB-INF/views/manager/teacher/menu.jsp"></jsp:include>
	<div id="content" class="mb-5">
		<jsp:include page="/WEB-INF/views/manager/commons/topbar.jsp"></jsp:include>
		<div class="container-fluid">
			<nav class="navbar navbar-light " style="font-weight: 600;">

				<span class="navbar-text"> <i class="fas fa-arrow-left mr-2"></i><a
					class='text-primary' href="#" id="back-link">Quay Lại Danh Sách
						Bài Thi</a></span>

			</nav>
			<div class="card shadow mb-4 mt-4">
				<div
					class="card-header py-3 row m-0 px-4 d-flex justify-content-between">
					<h6 class="m-0 font-weight-bold text-primary">Kết Quả Bài Thi</h6>
					<div class="download">

						<a href="#" class="btn btn-success btn-icon-split btn-sm btn-download-excel"> <span
							class="icon text-white-50"> <i class="fas fa-file-excel"></i>
						</span> <span class="text">EXCEL</span>
						</a> <a href="#" class="btn btn-warning btn-icon-split btn-sm btn-download-pdf"> <span
							class="icon text-white-50"> <i class="fas fa-file-pdf"></i>
						</span> <span class="text">PDF</span>
						</a>
					</div>
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
												<th class="sorting_asc font-weight-bold" tabindex="0"
													aria-controls="dataTable" rowspan="1" colspan="1"
													aria-sort="ascending"
													aria-label="Name: activate to sort column descending">STT</th>
												<th class="sorting font-weight-bold" tabindex="0"
													aria-controls="dataTable" rowspan="1" colspan="1"
													aria-label="Position: activate to sort column ascending">Mã
													Sinh Viên</th>
												<th class="sorting font-weight-bold" tabindex="0"
													aria-controls="dataTable" rowspan="1" colspan="1"
													aria-label="Office: activate to sort column ascending">Họ
													Và Tên</th>
												<th class="sorting font-weight-bold" tabindex="0"
													aria-controls="dataTable" rowspan="1" colspan="1"
													aria-label="Office: activate to sort column ascending">Kết
													Quả</th>

											</tr>
										</thead>
										<tfoot>
											<tr>
												<th rowspan="1" colspan="1" class="font-weight-bold">STT</th>
												<th rowspan="1" colspan="1" class="font-weight-bold">Mã
													Sinh Viên</th>
												<th rowspan="1" colspan="1" class="font-weight-bold">Họ
													Và Tên</th>
												<th rowspan="1" colspan="1" class="font-weight-bold">Kết
													Quả</th>

											</tr>
										</tfoot>
										<tbody id='table-data-body'>
											<tr role="row" class="odd hidden" id='row-data-container'>
												<td class="sorting_1" field="index"></td>
												<td field="studentCode"></td>
												<td field="studentName"></td>
												<td field="studentGrade"></td>

											</tr>

										</tbody>
									</table>
								</div>
							</div>
							<%-- <div class="row m-0 p-0 justify-content-end">
								<div class="p-4" id='pagination'>
									<jsp:include page="/WEB-INF/views/commons/pagination.jsp"></jsp:include>
								</div>
							</div> --%>
						</div>
					</div>


				</div>
			</div>

		</div>
	</div>
</div>

<jsp:include page="/WEB-INF/views/commons/lib.jsp"></jsp:include>
<script src="<c:url value='/resources/commons/js/base.js' />"></script>
<script
	src="<c:url value='/resources/manager/teacher/js/exam-result.js' />"></script>
<!-- Footer -->

<jsp:include page="/WEB-INF/views/manager/commons/footer.jsp"></jsp:include>