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
							khoản</span> </a> <!-- Dropdown - User Information -->
					<div
						class="dropdown-menu dropdown-menu-right shadow animated--grow-in"
						aria-labelledby="userDropdown">
						<a class="dropdown-item" href="#" id="btn-edit-profile"> <i
							class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i>Cập nhật
							thông tin
						</a> <a class="dropdown-item" href="#" id="btn-change-password"> <i
							class="fas fa-cogs fa-sm fa-fw mr-2 text-gray-400"></i> Đổi mật
							khẩu
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
			<nav class="navbar navbar-light " style="font-weight: 600;">

				<span class="navbar-text"> <i class="fas fa-arrow-left"></i><a
					class='text-primary' href="#" id="back-link">Quay lại danh sách bài thi</a></span>

			</nav>
			<!-- content -->
			<div class=" " id="main-content">
				<div class="row m-0 p-0">
					<div class="container-fluid mt-4 mb-4" id="main">
						<div class='question'>
							<div id='question-row-sample' class='hidden'>
								<div class=' col-2 '>
									<div>

										<div class="card border-left-primary shadow">
											<div class="card-body">
												<div class="row no-gutters align-items-center">
													<div class="col">
														<div
															class="font-weight-bold text-primary  mb-1 question-index"></div>

													</div>

												</div>
											</div>
										</div>
									</div>
								</div>
								<div class='question-content col-10'>
									<div class="card shadow h-100 py-2">
										<div class="card-body">
											<div class="row no-gutters align-items-center">
												<div class="col mr-2">
													<div field='content'></div>
													<div class='answer border-top mt-4 pt-4'>
														<div field="answer.content">
															<div class="py-4 ml-1">Câu trả lời của sinh viên</div>
															<div class="input-group fill-word">
																<textarea readonly class="form-control"
																	aria-label="With textarea"></textarea>

															</div>
															<div class="pt-4 ml-1 border-top">Chấm điểm</div>
															<div class='row mx-0 p-0 mt-4'>
																<form>
																	<div class="row">
																		<div class="col">
																			<input type="text" class="form-control student-grade"
																				value="0">
																		</div>
																		<div class="col">
																			<input type="text" class="form-control max-grade"
																				readonly>
																		</div>
																	</div>
																</form>
															</div>
														</div>
													</div>
												</div>
											</div>

										</div>
									</div>
								</div>
							</div>

						</div>
						<div id="question-container"></div>

					</div>
				</div>

				<div class='row mt-4 mb-4 justify-content-center border-top pt-4'>
					<a href="#" class="btn btn-success btn-icon-split mt-4"
						id='btn-submit'> <span class="text">Lưu</span> <span
						class="icon text-white-50"> <i class="fas fa-arrow-right"></i>
					</span>
					</a>
				</div>
			</div>
			<!-- end content -->

		</div>

	</div>

</div>
<!-- modal alert -->
<div class="modal modal-reset fade" id="modal-alert" tabindex="-1"
	role="dialog" aria-labelledby="edit" aria-hidden="true">
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
				<div class="message">Xác nhận lưu thay đổi?</div>

				<div class="border-top my-3"></div>
				<div class="d-flex justify-content-center">
					<a type="button" class="btn  waves-effect btn-cancel"
						data-dismiss="modal">Hủy</a><a type="button"
						class="btn  waves-effect btn-submit">Xác nhận</a>
				</div>
			</div>

		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->

</div>


<jsp:include page="/WEB-INF/views/commons/lib.jsp"></jsp:include>
<script
	src="<c:url value='/resources/commons/lib/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js' />"></script>
<script src="<c:url value='/resources/commons/js/base.js' />"></script>
<script src="<c:url value='/resources/commons/js/pagination.js' />"></script>
<script src="<c:url value='/resources/manager/commons/js/search.js' />"></script>
<script src="<c:url value='/resources/manager/teacher/js/mark.js' />"></script>
<!-- Footer -->

<jsp:include page="/WEB-INF/views/manager/commons/footer.jsp"></jsp:include>