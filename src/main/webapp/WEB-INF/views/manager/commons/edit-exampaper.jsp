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
						</div>
						<div class="col-1"></div>

					</div>

					<form class=" ">
						<div class="form-group row mt-3">
							<label for="inputName" class="col-2 col-form-label">Mã Đề
								Thi (*)</label>
							<div class="col-9">
								<input type="text" class="form-control" id="input-code" readonly
									required>
							</div>
							<div class="col-1"></div>
						</div>
					</form>

					<form class=" ">
						<div class="form-group row mt-3">
							<label for="inputName" class="col-2 col-form-label">Tên
								Đề Thi (*)</label>
							<div class="col-9">
								<input type="text" class="form-control" id="input-name" required>
							</div>
							<div class="col-1"></div>
						</div>
					</form>

					<form>
						<div class="form-group row mt-3">
							<label for="inputName" class="col-2 col-form-label">Mô Tả</label>
							<div class="col-9">
								<textarea class="form-control" id="input-descriptor
								"
									rows="3"></textarea>
							</div>
							<div class="col-1"></div>
						</div>
					</form>

				</div>
			</div>
		</div>
		<!-- table -->

		<div>
			<div class="card mb-4">
				<div class="card-body ">
					<h5 class="h6 mb-0 text-gray-600 mb-4">Trang 1</h5>

					<div id="question-list-container">
						<div class='hidden' id='question-item-sample'>
							<div class="card mt-3">
								<div class="card-body p-2 mt-1 mb-1">
									<div class='row d-flex'>
										<div class="d-flex col-lg-10 col-md-9 align-self-center">
											<div class="text-truncate d-flex question-content"></div>
											<div class="pl-1 div-truncate" style="font-weight: bold;">
												<span>...</span>
											</div>
										</div>
										<div class="row col-lg-2 col-md-3 justify-content-between d-flex">
											<button class="m-0 btn btn-view text-info btn-control col-3">
												<i class="far fa-eye"></i>
											</button>
											<button
												class="m-0 btn btn-delete text-danger btn-control col-3">
												<i class="fas fa-trash"></i>
											</button>
											<div class="form-group m-0 col-6">
												<div>
													<input class="form-control" type="text">
												</div>
											</div>

										</div>
									</div>

								</div>
							</div>
						</div>
					</div>

					<!-- button add question -->
					<div class="d-flex w-100 justify-content-between mt-4">
						<a href="#" class="btn btn-primary btn-icon-split btn-sm"> <span
							class="icon text-white-50"> <i class="fas fa-cut"></i>
						</span> <span class="text">Trang Mới</span>
						</a>
						<div class="dropdown">
							<button class="btn btn-success dropdown-toggle btn-sm"
								type="button" id="dropdownMenuButton" data-toggle="dropdown"
								aria-haspopup="true" aria-expanded="false">Thêm Câu Hỏi</button>
							<div class="dropdown-menu dropdown-menu-right"
								aria-labelledby="dropdownMenuButton">
								<a class="dropdown-item" href="#">Thêm Mới</a> <a
									class="dropdown-item btn-select-question" href="#">Ngân
									Hàng Câu hỏi</a> <a class="dropdown-item" href="#">Ngẫu Nhiên</a>
							</div>
						</div>

					</div>

					<!-- end button add question -->

				</div>
			</div>
		</div>
		<div class='row d-flex justify-content-center mt-4'>
			<button type="button" class="btn btn-primary btn-submit"
				id="btn-submit-exampaper">Lưu</button>
		</div>
		<!-- end table -->
	</div>

</div>

<!-- modal select question-->
<div class="modal fade" id="modal-select-question" tabindex="-1"
	role="dialog" aria-labelledby="edit" aria-hidden="true">
	<div class="modal-dialog modal-xl">
		<div class="modal-content">
			<!--Header-->
			<div class="modal-header">
				<p class="heading lead">Chọn Câu Hỏi</p>

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
					<div class="container-fluid">

						<div class="descriptor row p-4">
							<div class="col-5 row d-flex ">
								<div class="col-6">
									<span class=' pl-1 m-0 font-weight-bold text-primary'>Môn
										Học</span>
								</div>
								<div class="col-6">
									<span class='text-primary subject-info'></span>
								</div>

							</div>
							<div class="col-2"></div>
							<div class="col-5 row d-flex pl-4">
								<span class='col-6 pl-4 m-0 font-weight-bold text-primary'>Người
									Tạo</span> <span class='text-primary  col-6 teacher-info'></span>
							</div>
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

									<div class="d-block col-md-5 col-lg-5 filter-item pr-0"
										id="filter-type">
										<h6 class="m-0 font-weight-bold text-primary col-9">Loại
											Câu Hỏi</h6>
										<div class="input-group col-lg-12 col-md-12 d-flex mt-3 pr-0">
											<input class="form-control" type="text" val='ALL' readonly>
											<div class="input-group-append border-0 dropdown">
												<button class="btn btn-outline-primary dropdown-toggle"
													id="dropdownMenuButton" data-toggle="dropdown"
													aria-haspopup="true" aria-expanded="false" type="button">Chọn</button>
												<div
													class="dropdown-menu dropdown-menu-right dropdown-menu-w-100"
													aria-labelledby="dropdownMenuButton">
													<a class="dropdown-item" href="#" value='ALL'>Tất Cả</a>
													<div class="dropdown-divider"></div>
													<a class="dropdown-item" href="#" value='ONE_CHOICE'>Một
														Đáp Án</a>
													<div class="dropdown-divider"></div>
													<a class="dropdown-item" href="#" value='MULTIPLE_CHOICE'>Nhiều
														Đáp Án</a>
													<div class="dropdown-divider"></div>
													<a class="dropdown-item" href="#" value='FILL_WORD'>Điền
														Từ</a>
												</div>
											</div>
										</div>
									</div>
									<div class="d-block col-md-2 col-lg-2"></div>
									<div class="d-block col-md-5 col-lg-5 filter-item pr-0"
										id="filter-level">
										<h6 class="m-0 font-weight-bold text-primary col-9 ">Độ
											Khó</h6>
										<div class="input-group col-lg-12 col-md-12 d-flex mt-3 pr-0">
											<input class="form-control" type="text" val='ALL' readonly>
											<div class="input-group-append border-0 dropdown">
												<button class="btn btn-outline-primary dropdown-toggle"
													id="dropdownMenuButton" data-toggle="dropdown"
													aria-haspopup="true" aria-expanded="false" type="button">Chọn</button>
												<div
													class="dropdown-menu dropdown-menu-right dropdown-menu-w-100"
													aria-labelledby="dropdownMenuButton">
													<a class="dropdown-item" href="#" value="ALL">Tất Cả</a>
													<div class="dropdown-divider"></div>
													<a class="dropdown-item" href="#" value="0">Dễ</a>
													<div class="dropdown-divider"></div>
													<a class="dropdown-item" href="#" value="1">Trung Bình</a>
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
							<div class="card h-100">
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
																				<a class="dropdown-item" id="select-all">Chọn
																					Tất Cả</a> <a class="dropdown-item" id="deselect-all">Bỏ
																					Chọn Tất Cả</a>
																			</div>
																		</div>
																	</th>
																	<th class="table-cell column1">STT</th>
																	<th class="table-cell column2">Môn Học</th>
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
																<h5 id='data-empty-alert'
																	class="hidden mt-3 mb-3 w-100 d-flex justify-content-center font-weight-bold text-primary">Không
																	Có Dữ Liệu</h5>
																<tr class="table-row body hidden"
																	id="row-data-container">

																	<td class="table-cell column0" field='checkBox'><div
																			class="custom-control custom-checkbox">
																			<input type="checkbox" class="custom-control-input"
																				id="check-1"> <label
																				class="custom-control-label" for="check-1"></label>
																		</div></td>
																	<td class="table-cell column1" field="index"></td>
																	<td class="table-cell column2"
																		field="subject.subjectName"></td>
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
																			class="btn btn-view-inside text-success justify-content-center"
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
					<!-- end table -->
					<!-- pagination -->

					<!-- end pagination -->
				</div>
				<div>
					<div class="border-top my-3"></div>
					<div class="d-flex justify-content-center">
						<a type="button" class="btn  waves-effect btn-cancel"
							data-dismiss="modal">Hủy</a><a type="button"
							class="btn waves-effect btn-submit" id='btn-submit-question-bank'>Thêm</a>
					</div>
				</div>
			</div>

		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>
<!-- modal view -->
<div class="modal fade" id="modal-view" tabindex="-1" role="dialog"
	aria-labelledby="edit" aria-hidden="true">
	<div class="modal-dialog modal-lg">
		<div class="modal-content" style="min-height: 50vh;">
			<!--Header-->
			<div class="modal-header">
				<p class="heading lead">Chi Tiết Câu Hỏi</p>

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
							<span class='text-gray-600  col-6'>ID Câu Hỏi</span> <span
								class='text-primary  col-6' field='id'></span>
						</div>
						<div class="col-6 row d-flex">
							<span class='text-gray-600  col-6'>Tên Câu Hỏi</span> <span
								class='text-primary  col-6' field='name'></span>
						</div>
					</div>
					<div class="descriptor row p-4">
						<div class="col-6 row d-flex">
							<div class="col-6">
								<span class='text-gray-600 '>Môn Học</span>
							</div>
							<div class="col-6">
								<span class='text-primary ' field='subject.subjectName'></span>
							</div>

						</div>
						<div class="col-6 row d-flex">
							<span class='text-gray-600  col-6'>Người Tạo</span> <span
								class='text-primary  col-6' field='teacher.fullName'></span>
						</div>
					</div>
					<div class="descriptor row p-4">
						<div class="col-6 row d-flex">
							<span class='text-gray-600  col-6'>Độ Khó</span> <span
								class='text-primary  col-6' field='level'></span>
						</div>
						<div class="col-6 row d-flex">
							<span class='text-gray-600  col-6'>Loại Câu Hỏi</span> <span
								class='text-primary  col-6' field='type'></span>
						</div>
					</div>
					<div class="m-3 p-4" style="background: #d2f4fa;">
						<div class=" justify-content-center d-block">
							<div class="w-100 d-block" field='content'></div>
						</div>
						<div class="row mt-3">
							<span class='text-lg' style="font-weight: 900;">Câu Trả
								Lời</span>
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



