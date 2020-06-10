<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="/WEB-INF/views/commons/header.jsp"></jsp:include>
<link rel="stylesheet"
	href="<c:url value='/resources/web/css/home.css' />">

<!--bat dau body-->

<div class="mt-4">
	<div class="carousel slide" id="main-carousel" data-ride="carousel">
		<ol class="carousel-indicators">
			<li data-target="#main-carousel" data-slide-to="0" class="active"></li>
			<li data-target="#main-carousel" data-slide-to="1"></li>
			<li data-target="#main-carousel" data-slide-to="2"></li>
			<li data-target="#main-carousel" data-slide-to="3"></li>
		</ol>
		<!-- /.carousel-indicators -->

		<div class="carousel-inner">
			<div class="carousel-item active">
				<img class="d-block img-fluid"
					src="<c:url value='/resources/commons/image/login/slide-1.jpg' />"
					alt="">
				<div class="carousel-caption d-none d-md-block">
					<h1>HUST</h1>
				</div>
			</div>
			<div class="carousel-item">
				<img class="d-block img-fluid"
					src="<c:url value='/resources/commons/image/login/slide-2.jpg' />"
					alt="">
				<div class="carousel-caption d-none d-md-block">
					<h3>HUST</h3>
					<p>Lời Giới Thiệu</p>
				</div>
			</div>
			<div class="carousel-item">
				<img class="d-block img-fluid"
					src="<c:url value='/resources/commons/image/login/slide-1.jpg' />"
					alt="">
				<div class="carousel-caption d-none d-md-block">
					<h3>HUST</h3>
					<p>Lời Giới Thiệu</p>
				</div>
			</div>
			<div class="carousel-item">
				<img
					src="<c:url value='/resources/commons/image/login/slide-2.jpg' />"
					alt="" class="d-block img-fluid">
				<div class="carousel-caption d-none d-md-block">
					<h3>HUST</h3>
					<p>Lời Giới Thiệu</p>
				</div>
			</div>
		</div>
		<!-- /.carousel-inner -->

		<a href="#main-carousel" class="carousel-control-prev"
			data-slide="prev"> <span class="carousel-control-prev-icon"></span>
			<span class="sr-only" aria-hidden="true">Prev</span>
		</a> <a href="#main-carousel" class="carousel-control-next"
			data-slide="next"> <span class="carousel-control-next-icon"></span>
			<span class="sr-only" aria-hidden="true">Next</span>
		</a>
	</div>
	<!-- /.carousel -->
</div>
<!-- /.container -->



<nav
	class="navbar navbar-expand-sm bg-light navbar-light menu text-light mt-4 mb-4 justify-content-end">
	<div class="col-3 ">
		<nav class="navbar navbar-expand-sm justify-content-end">
			<ul class="navbar-nav ml-auto">
				<!-- Nav Item - User Information -->
				<li class="nav-item dropdown no-arrow"><a
					class="nav-link dropdown-toggle" href="#" 
					data-toggle="dropdown"> <span class="mr-2  text-gray-600">Bộ
							Lọc</span><i class="fas fa-filter"></i>
				</a>
					<div id="filterDropdown"
						class="dropdown-menu dropdown-menu-right shadow animated--grow-in"
						aria-labelledby="filterDropdown">
						<a class="dropdown-item" href="#" value='all'> <i
							class="fas fa-pen-square mr-2 fa-sm fa-fw text-gray-400"
							id="filter-all"></i>Tất Cả
						</a>
						<div class="dropdown-divider"></div>
						<a class="dropdown-item" href="#" value='continue'> <i
							class="fas fa-pen-square mr-2 fa-sm fa-fw text-gray-400"
							id="filter-continue"></i>Ngắt Quãnng
						</a>
						<div class="dropdown-divider"></div>
						<a class="dropdown-item" href="#" value='open'> <i
							class="fas fa-pen-square mr-2 fa-sm fa-fw text-gray-400"
							id="filter-open"></i>Đang Mở
						</a>
						<div class="dropdown-divider"></div>
						<a class="dropdown-item" href="#" value='close'> <i
							class="fas fa-window-close fa-sm fa-fw mr-2 text-gray-400"
							id="filter-close"></i> Đã Đóng
						</a>
						<div class="dropdown-divider"></div>
						<a class="dropdown-item" href="#" value='complete'> <i
							class="fas fa-check-circle fa-sm fa-fw mr-2 text-gray-400"
							id="filter-complete"></i> Đã Hoàn Thành
						</a>
					</div></li>

			</ul>
		</nav>
	</div>
</nav>
<div class="container-content container-fluid" >

	<div class="card shadow mb-4">
		<div class="card-header py-3">
			<h6 class="m-0 font-weight-bold text-primary">Danh Sách Bài Thi</h6>
		</div>
		<div class="card-body">
			<div class="table-responsive">
				<div id="dataTable_wrapper"
					class="dataTables_wrapper dt-bootstrap4 m-0 p-0"
					style="width: 100%;">
					<!-- <div class="row m-0 p-0 ">
						<div class="col-sm-12 col-md-6">
							<div class="dataTables_length" id="dataTable_length">
								<label>Show <select name="dataTable_length"
									aria-controls="dataTable"
									class="custom-select custom-select-sm form-control form-control-sm"><option
											value="10">10</option>
										<option value="25">25</option>
										<option value="50">50</option>
										<option value="100">100</option></select> entries
								</label>
							</div>
						</div>
						<div class="col-sm-12 col-md-6">
							<div id="dataTable_filter" class="dataTables_filter">
								<label>Search:<input type="search"
									class="form-control form-control-sm" placeholder=""
									aria-controls="dataTable"></label>
							</div>
						</div>
					</div> -->
					<div class="row p-0 m-0">
						<div class="col-sm-12">
							<table class="table table-bordered dataTable" id="dataTable"
								cellspacing="0" role="grid" aria-describedby="dataTable_info">
								<thead>
									<tr role="row">
										<th class="sorting_asc" tabindex="0" aria-controls="dataTable"
											rowspan="1" colspan="1" aria-sort="ascending"
											aria-label="Name: activate to sort column descending">Mã</th>
										<th class="sorting" tabindex="0" aria-controls="dataTable"
											rowspan="1" colspan="1"
											aria-label="Position: activate to sort column ascending">Tên
											Bài Thi</th>
										<th class="sorting" tabindex="0" aria-controls="dataTable"
											rowspan="1" colspan="1"
											aria-label="Office: activate to sort column ascending">Môn
											Học</th>
										<th class="sorting" tabindex="0" aria-controls="dataTable"
											rowspan="1" colspan="1"
											aria-label="Office: activate to sort column ascending">Lớp
											Học</th>
										<th class="sorting" tabindex="0" aria-controls="dataTable"
											rowspan="1" colspan="1"
											aria-label="Age: activate to sort column ascending">Thời
											Gian</th>
										<th class="sorting" tabindex="0" aria-controls="dataTable"
											rowspan="1" colspan="1"
											aria-label="Start date: activate to sort column ascending">Thời
											Gian Mở</th>
										<th class="sorting" tabindex="0" aria-controls="dataTable"
											rowspan="1" colspan="1"
											aria-label="Salary: activate to sort column ascending">Thời
											Gian Đóng</th>
										<th class="sorting" tabindex="0" aria-controls="dataTable"
											rowspan="1" colspan="1"
											aria-label="Salary: activate to sort column ascending">Trạng
											Thái</th>
										<th class="sorting" tabindex="0" aria-controls="dataTable"
											rowspan="1" colspan="1"
											aria-label="Salary: activate to sort column ascending">Kết
											Quả</th>
									</tr>
								</thead>
								<tfoot>
									<tr>
										<th rowspan="1" colspan="1">Mã</th>
										<th rowspan="1" colspan="1">Tên Bài Thi</th>
										<th rowspan="1" colspan="1">Môn Học</th>
										<th rowspan="1" colspan="1">Lớp Học</th>
										<th rowspan="1" colspan="1">Thời Gian</th>
										<th rowspan="1" colspan="1">Thời Gian Mở</th>
										<th rowspan="1" colspan="1">Thời Gian Đóng</th>
										<th rowspan="1" colspan="1">Trạng Thái</th>
										<th rowspan="1" colspan="1">Kết Qủa</th>
									</tr>
								</tfoot>
								<tbody id='table-data-body'>
									<tr role="row" class="odd hidden" id='row-data-container'>
										<td class="sorting_1" field="code"></td>
										<td field="name"></td>
										<td field="examCourse.course.subject.subjectName"></td>
										<td field="examCourse.course.code"></td>
										<td field="time"></td>
										<td field="timeOpen"></td>
										<td field="timeClose"></td>
										<td field="status"></td>
										<td field="result"></td>
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


<jsp:include page="/WEB-INF/views/commons/lib.jsp"></jsp:include>
<script src="<c:url value='/resources/commons/js/base.js' />"></script>
<script src="<c:url value='/resources/commons/js/pagination.js' />"></script>
<script src="<c:url value='/resources/manager/commons/js/search.js' />"></script>
<script src="<c:url value='/resources/web/js/home.js' />"></script>


<jsp:include page="/WEB-INF/views/commons/footer.jsp"></jsp:include>