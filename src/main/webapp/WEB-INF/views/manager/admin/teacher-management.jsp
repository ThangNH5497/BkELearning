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

<!-- Phan noi dung trang -->
<div class="wrapper d-flex align-items-stretch">
	<jsp:include page="/WEB-INF/views/manager/admin/menu.jsp"></jsp:include>

	<!-- Page Content  -->
	<div id="content" class="mb-5">

		<!-- Topbar -->
		<nav
			class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">
			<button type="button" id="sidebarCollapse" class="btn btn-primary">
				<i class="fa fa-bars"></i> <span class="sr-only">Toggle Menu</span>
			</button>
			<!-- Sidebar Toggle (Topbar) -->
			<button id="sidebarToggleTop"
				class="btn btn-link d-md-none rounded-circle mr-3">
				<i class="fa fa-bars"></i>
			</button>
			<!-- Topbar Search -->

			<form
				class="d-flex d-sm-inline-block form-inline  ml-md-3 my-2 my-md-0 mw-100 navbar-search">

				<div class="input-group ">
					<div class="input-group-append" id='search-filter'>

						<div class="select-style d-flex">
							<select>
								<option value='code'>Mã GV</option>
								<option value='username'>TK</option>
								<option value='fullName'>Tên</option>
							</select> <i class="fas fa-caret-down"
								style="margin-top: 10px; padding-right: 10px;"></i>
						</div>

					</div>

					<input type="text" class="form-control bg-light  small"
						placeholder="Search for..." aria-label="Search"
						aria-describedby="basic-addon2" id='key-search'>



					<div class="input-group-append">
						<button class="btn btn-primary" type="button" id='btn-search'>
							<i class="fas fa-search fa-sm"></i>
						</button>
					</div>
				</div>

			</form>
			<!-- type search radio -->
			<!-- end type search radio -->
			<!-- Topbar Navbar -->
			<ul class="navbar-nav ml-auto">
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

				<!-- Nav Item - Messages -->
				<li class="nav-item dropdown no-arrow mx-1"><a
					class="nav-link dropdown-toggle" href="#" id="messagesDropdown"
					role="button" data-toggle="dropdown" aria-haspopup="true"
					aria-expanded="false"> <i class="fas fa-envelope fa-fw"></i> <!-- Counter - Messages -->
						<span class="badge badge-danger badge-counter">7</span>
				</a> <!-- Dropdown - Messages --></li>
			</ul>
			<div class="topbar-divider d-none d-sm-block"></div>
		</nav>
		<!-- End of Topbar -->


		<div class=" container-fluid ">
			<nav class="navbar navbar-light bg-light hidden"
				style="font-weight: 600;" id='link-back-search'>
				<span class="navbar-text"> <i class="fas fa-link"></i> <a
					class='text-primary' href="ql-giang-vien">Quản Lý Gảng Viên</a><span>
						/ Tìm Kiếm</span>
				</span>
			</nav>
			<div class="row ">

				<!-- Area Chart -->
				<div class="col-xl-9 col-lg-8 card-container">
					<div class="card shadow h-100">
						<!-- Card Header - Dropdown -->
						<div
							class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
							<h6 class="m-0 font-weight-bold text-primary">Danh Sách
								Giảng Viên</h6>

							<div class="d-flex">
								<button class="btn btn-add text-success btn-control"
									data-toggle="modal" data-target="#modal-add-new">
									<i class="far fa-plus-square"></i>
								</button>
								<button class="btn btn-edit text-primary disabled  btn-control">
									<i class="fas fa-edit"></i>
								</button>
								<button class="btn btn-delete text-danger  btn-control">
									<i class="fas fa-trash"></i>
								</button>
								<button class="btn btn-refresh text-primary btn-control">
									<i class="fas fa-sync"></i>
								</button>
								<nav aria-label="Page navigation">

									<ul class="pagination justify-content-end">

										<li class="page-item disabled"><a class="page-link"
											id="pagination-title">Hiển Thị 1-10/20</a></li>
										<li class="page-item disabled" id="prePage"><a
											class="page-link" tabindex="-1">Previous</a></li>
										<div id="pagination" style="display: flex;">
											<li class="page-item page-item-number active"><a
												class="page-link">1</a></li>
											<li class="page-item page-item-number "><a
												class="page-link">2</a></li>
											<li class="page-item page-item-number "><a
												class="page-link">3</a></li>

											<li class="page-item page-item-number "><a
												class="page-link">4</a></li>
											<li class="page-item page-item-number "><a
												class="page-link">5</a></li>
										</div>

										<li class="page-item disabled" id="nextPage"><a
											class="page-link">Next</a></li>
									</ul>
								</nav>
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
															<th class="table-cell column2">Mã GV</th>
															<th class="table-cell column3">Họ&Tên</th>
															<th class="table-cell column4">Bộ Môn</th>
															<th class="table-cell column5">Chức Vụ</th>
															<th class="table-cell column6">Ngày Sinh</th>

														</tr>

													</thead>
												</table>
											</div>

											<div class="table-data-body js-pscroll">
												<table>
													<tbody id="table-data-body">
														<h5 id='data-empty-alert'
															class="hidden mt-3 mb-3 w-100 d-flex justify-content-center font-weight-bold text-primary">Không
															Có Dữ Liệu Khớp</h5>
														<tr class="table-row body hidden border-bottom border-top"
															id="row-data-container">

															<td class="table-cell column0" field='checkBox'><div
																	class="custom-control custom-checkbox">
																	<input type="checkbox" class="custom-control-input"
																		id="check-1"> <label
																		class="custom-control-label" for="check-1"></label>
																</div></td>
															<td class="table-cell column1" field="index">Like a
																butterfly</td>
															<td class="table-cell column2" field="code">Like a
																butterfly</td>
															<td class="table-cell column3" field="fullName">Boxing</td>
															<td class="table-cell column4" field="department">9:00
																AM - 11:00 AM</td>
															<td class="table-cell column5" field=position>Aaron
																Chapman</td>
															<td class="table-cell column6" field="dateOfBirth">10</td>
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

				<!-- Pie Chart -->
				<div class="col-xl-3 col-lg-4 card-container">
					<div class="card shadow h-100">
						<!-- Card Header - Dropdown -->
						<div
							class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
							<h6 class="m-0 font-weight-bold text-primary">Chi Tiết</h6>
							<div class="dropdown no-arrow">
								<a class="dropdown-toggle" href="#" role="button"
									id="dropdownMenuLink" data-toggle="dropdown"
									aria-haspopup="true" aria-expanded="false"> <i
									class="fas fa-ellipsis-v fa-sm fa-fw text-gray-400"></i>
								</a>
								<div
									class="dropdown-menu dropdown-menu-right shadow animated--fade-in"
									aria-labelledby="dropdownMenuLink">
									<div class="dropdown-header">Dropdown Header:</div>
									<a class="dropdown-item" href="#">Action</a> <a
										class="dropdown-item" href="#">Another action</a>
									<div class="dropdown-divider"></div>
									<a class="dropdown-item" href="#">Something else here</a>
								</div>
							</div>
						</div>
						<!-- Card Body -->
						<div class="card-body">
							<!-- profile user -->
							<div class="container-fluid user-profile">
								<div class="row ">
									<div class="col-md-12">
										<div class="profile-img">
											<img class='hidden' field="image" id="user-detail-img"
												style="width: 100%; max-height: 20vh;" src="" alt="" />
										</div>
									</div>
								</div>
								<div class="tab-content profile-tab">
									<div class="tab-pane fade show active" id="user-detail"
										role="tabpanel" aria-labelledby="home-tab">
										<div id="user-detail-row" class="hidden">
											<div>
												<div class="border-top my-3"></div>
												<div class="row">
													<div class="col-md-4">
														<label>Mã GV</label>
													</div>
													<div class="col-md-8">

														<p field="code">KTMT1</p>
													</div>
												</div>
											</div>

											<div>
												<div class="border-top my-3"></div>
												<div class="row">
													<div class="col-md-4">
														<label>Họ&Tên</label>
													</div>
													<div class="col-md-8">
														<p field="fullName">Kshiti Ghelani</p>
													</div>
												</div>
											</div>
											<div>
												<div class="border-top my-3"></div>
												<div class="row">
													<div class="col-md-4">
														<label>Email</label>
													</div>
													<div class="col-md-8">
														<p field="email">kshitighelani@gmail.com</p>
													</div>
												</div>
											</div>
											<div>
												<div class="border-top my-3"></div>
												<div class="row">
													<div class="col-md-4">
														<label>SĐT</label>
													</div>
													<div class="col-md-8">
														<p field="phoneNumber">123 456 7890</p>
													</div>
												</div>
											</div>
											<div>
												<div class="border-top my-3"></div>
												<div class="row">
													<div class="col-md-4">
														<label>Bộ Môn</label>
													</div>
													<div class="col-md-8">
														<p field="department">Web Developer and Designer</p>
													</div>
												</div>
											</div>
											<div>
												<div class="border-top my-3"></div>
												<div class="row">
													<div class="col-md-4">
														<label>Chức Vụ</label>
													</div>
													<div class="col-md-8">
														<p field="position">Web Developer and Designer</p>
													</div>
												</div>
											</div>
											<div>
												<div class="border-top my-3"></div>
												<div class="row">
													<div class="col-md-4">
														<label>Địa Chỉ</label>
													</div>
													<div class="col-md-8">
														<p field="addr">Web Developer and Designer</p>
													</div>
												</div>
											</div>
											<div>
												<div class="border-top my-3"></div>
												<div class="row">
													<div class="col-md-4">
														<label>Ngày Sinh</label>
													</div>
													<div class="col-md-8">
														<p field="dateOfBirth">Web Developer and Designer</p>
													</div>
												</div>
											</div>

										</div>

									</div>

								</div>

							</div>
							<!-- end profile user -->
						</div>
					</div>
				</div>
			</div>

		</div>

	</div>

	<!-- end content -->

</div>

<!-- modal add new teacher -->

<div class="modal fade" id="modal-add-new" tabindex="-1" role="dialog"
	aria-labelledby="edit" aria-hidden="true">
	<div class="modal-dialog modal-xl">
		<div class="modal-content">
			<!--Header-->
			<div class="modal-header">
				<p class="heading lead">Thêm Giảng Viên</p>

				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true" class="white-text">×</span>
				</button>
			</div>

			<!--Body-->
			<div class="modal-body">
				<ul class="nav nav-tabs" id="container-tab-add" role="tablist">
					<li class="nav-item"><a class="nav-link active"
						id="add-one-tab-btn" data-toggle="tab" href="#add-one-tab"
						role="tab" aria-controls="tab-add" aria-selected="true">Thêm
							Thủ Công</a></li>
					<li class="nav-item"><a class="nav-link" id="add-exel-tab-btn"
						data-toggle="tab" href="#add-exel-tab" role="tab"
						aria-controls="profile" aria-selected="false">Thêm từ Exel</a></li>

				</ul>
				<div class="tab-content">
					<!-- them giang vien thu cong -->
					<div class="tab-pane fade show active" id="add-one-tab"
						role="tabpanel" aria-labelledby="home-tab">
						<div class="form-container" id="form-container-one">
							<div class="form-v4">
								<div class="form-v4-content">
									<form class="form-detail" action="#" method="post"
										id="form-add-step-one">
										<h2>Thông Tin Giảng Viên Bắt Buộc</h2>
										<div class="form-group">
											<div class="form-row form-row-1">
												<label for="code">Mã GV (*)</label> <input type="text"
													name="code" class="input-text" required> <label
													name="code-error" class="error hidden">Mã Gv Tồn
													Tại</label>
											</div>

											<div class="form-row form-row-1">
												<label for="fullName">Họ & Tên (*)</label> <input
													type="text" name="fullName" class="input-text" required>
											</div>
										</div>

										<div class="form-row">
											<label for="your_email">Username (*)</label> <input
												type="text" name="username" class="input-text" required>
											<label name="username-error" class="error hidden">Username
												Tồn Tại</label>
										</div>
										<div class="form-group">
											<div class="form-row form-row-1 ">
												<label for="password">Mật Khẩu Từ 6 ký tự (*)</label> <input
													type="password" name="password" class="input-text" required>
												<label name="password-error" class="error hidden">Nhỏ
													hơn 6 ký tự</label>
											</div>
											<div class="form-row form-row-1">
												<label>Xác Nhận Mật Khẩu (*)</label> <input type="password"
													class="input-text confirm-password" required> <label
													name="confirm-password-error" class="error hidden">Xác
													nhận mật khẩu chưa khớp</label>
											</div>
										</div>

									</form>
								</div>
							</div>
							<!--Footer-->
							<div class="border-top my-3"></div>
							<div class="d-flex justify-content-center">
								<a type="button" class="btn  waves-effect btn-cancel"
									data-dismiss="modal">Hủy</a> <a type="button"
									class="btn  waves-effect btn-next">Tiếp Tục</a>
							</div>
						</div>

						<!-- form next step -->
						<div class="hidden form-container" id="form-container-two">
							<div class="row">
								<div class="image-input-container col-sm-2 col-md-2">
									<form class="form-img">
										<div class="profile-img">
											<img class="image-preview"
												src="<c:url value='/resources/commons/image/user/default-user.jpg' />"
												alt="" />
											<div class="file btn btn-lg btn-primary">
												Change Photo <input type="file" name="file"
													class="input-file-avatar" />
											</div>
										</div>
									</form>
								</div>
								<div class="form-v4 col-sm-10 col-md-10">
									<div class="form-v4-content">
										<form class="form-detail" action="#" method="post"
											id="form-add-step-two">

											<div class="form-group">

												<div class="form-row form-row-1 ">
													<label for="department">Email</label> <input type="text"
														name="email" class="input-text">
												</div>
												<div class="form-row form-row-1">
													<label>SĐT</label> <input type="number" name="phoneNumber"
														class="input-text">
												</div>


											</div>

											<div class="form-group">
												<div class="form-row form-row-1 ">
													<label for="department">Địa Chỉ</label> <input type="text"
														name="addr" class="input-text">
												</div>
												<div class="form-row form-row-1">
													<label for="comfirm-password">Ngày Sinh</label> <input
														type="date" value="2011-08-19" name="dateOfBirth"
														class="input-text">
												</div>

											</div>
											<div class="form-group">
												<div class="form-row form-row-1 ">
													<label for="department">Bộ Môn</label> <input type="text"
														name="department" class="input-text">
												</div>
												<div class="form-row form-row-1">
													<label for="comfirm-password">Chức Vụ</label> <input
														type="text" name="position" class="input-text">
												</div>

											</div>

										</form>
									</div>
								</div>
							</div>

							<!--Footer-->
							<div class="border-top my-3"></div>
							<div class="d-flex justify-content-center">
								<a type="button" class="btn  waves-effect btn-cancel"
									data-dismiss="modal">Hủy</a> <a type="button"
									class="btn  waves-effect btn-back">Quay Lại</a> <a
									type="button" class="btn  waves-effect btn-submit">Lưu</a>
							</div>
						</div>
						<!-- end form next step -->
						<!-- alert -->

					</div>
					<!-- ket thuc them giang vien thu cong -->
					<!-- them giang vien tu file exel -->
					<div class="tab-pane fade" id="add-exel-tab" role="tabpanel"
						aria-labelledby="profile-tab">

						<div class="form-container">
							<div class="form-v4">
								<div class="form-v4-content">
									<form class="form-detail" id="form-exel-file">
										<h2>Thêm Danh Sách Giảng Viên Từ File Exel</h2>

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


		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>

<!-- end modal save or update -->



<!-- modal edit -->

<div class="modal fade" id="modal-edit" tabindex="-1" role="dialog"
	aria-labelledby="edit" aria-hidden="true">
	<div class="modal-dialog modal-xl">
		<div class="modal-content">
			<!--Header-->
			<div class="modal-header">
				<p class="heading lead">Sửa Thông Tin Giảng Viên</p>

				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true" class="white-text">×</span>
				</button>
			</div>

			<!--Body-->
			<div class="modal-body">
				<div class="form-container">
					<div class="row">
						<div class="image-input-container col-sm-2 col-md-2">
							<form class="form-img">
								<div class="profile-img">
									<img class="image-preview"
										src="<c:url value='/resources/commons/image/user/default-user.jpg' />"
										alt="" />
									<div class="file btn btn-lg btn-primary">
										Change Photo <input type="file" name="file"
											class="input-file-avatar" />
									</div>
								</div>
							</form>
						</div>
						<div class="form-v4 col-sm-10 col-md-10">
							<div class="form-v4-content">
								<form class="form-detail" action="#" method="post"
									id="form-edit">

									<div class="form-group">

										<div class="form-row form-row-1 ">
											<label for="department">Email</label> <input type="text"
												name="email" class="input-text">
										</div>
										<div class="form-row form-row-1">
											<label>SĐT</label> <input type="number" name="phoneNumber"
												class="input-text">
										</div>


									</div>

									<div class="form-group">
										<div class="form-row form-row-1 ">
											<label for="department">Địa Chỉ</label> <input type="text"
												name="addr" class="input-text">
										</div>
										<div class="form-row form-row-1">
											<label for="comfirm-password">Ngày Sinh</label> <input
												type="date" value="2011-08-19" name="dateOfBirth"
												class="input-text">
										</div>

									</div>
									<div class="form-group">
										<div class="form-row form-row-1 ">
											<label for="department">Bộ Môn</label> <input type="text"
												name="department" class="input-text">
										</div>
										<div class="form-row form-row-1">
											<label for="comfirm-password">Chức Vụ</label> <input
												type="text" name="position" class="input-text">
										</div>

									</div>

								</form>
							</div>
						</div>
					</div>

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

<!-- end modal edit -->

<!-- modal alert -->
<div class="modal fade" id="modal-delete-alert" tabindex="-1"
	role="dialog" aria-labelledby="edit" aria-hidden="true">
	<div class="modal-dialog modal-sm">
		<div class="modal-content">
			<!--Header-->
			<div class="modal-header bg-warning">
				<p class="heading lead">Cảnh Báo</p>

				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true" class="white-text">×</span>
				</button>
			</div>

			<!--Body-->
			<div class="modal-body border-top my-3">
				<div class="message">Bạn Có Muốn Tiếp Tục Xóa ?</div>

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

<!-- modal message -->
<div class="modal fade" id="modal-message" tabindex="-1" role="dialog"
	aria-labelledby="edit" aria-hidden="true">
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
<jsp:include page="/WEB-INF/views/commons/lib.jsp"></jsp:include>
<script src="<c:url value='/resources/commons/js/base.js' />"></script>
<script src="<c:url value='/resources/manager/commons/js/delete.js' />"></script>
<script src="<c:url value='/resources/commons/js/pagination.js' />"></script>
<script
	src="<c:url value='/resources/manager/commons/js/user-management.js' />"></script>
<script src="<c:url value='/resources/manager/commons/js/search.js' />"></script>
<script
	src="<c:url value='/resources/manager/admin/js/teacher-management.js' />"></script>
<!-- Footer -->
<script type="text/javascript">
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