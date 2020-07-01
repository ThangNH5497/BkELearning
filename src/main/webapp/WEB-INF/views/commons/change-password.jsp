<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="_csrf" content="${_csrf.token}" />
<!-- default header name is X-CSRF-TOKEN -->
<meta name="_csrf_header" content="${_csrf.headerName}" />
<title>BKELearning</title>
<link rel="stylesheet"
	href="<c:url value='/resources/commons/lib/bootstrap-4.4.1/css/bootstrap.min.css' /> ">
<link rel="stylesheet"
	href="<c:url value='/resources/commons/lib/fontawesome5.12.0/css/all.css' />">
<link rel="stylesheet"
	href="<c:url value='/resources/manager/commons/css/style.css' />">
<script>
	var userLoged = {
		role : "${user.role}",
		fullName : "${user.fullName}",
		id : "${user.id}",
		code : "${user.code}",
		image : "${user.image}"
	};
</script>
</head>
<body>

	<div id="content" class="mb-5">

		<nav
			class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">

			<span class="navbar-text"> <i class="fas fa-link mr-2"></i><a
				class='text-primary' href="<c:url value='trang-chu' />">Trang
					Chủ</a>
			</span>

			<ul class="navbar-nav ml-auto">

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
						</a> <a class="dropdown-item" href="#" id="btn-change-password"> <i
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
		<div class="container">

			<div class="card shadow mb-4">
				<div class="card-header py-3">
					<h6 class="m-0 font-weight-bold text-primary">Đổi mật khẩu</h6>
				</div>
				<div class="card-body p-4">
					<div class="form-container">
						<div class="row">

							<div class="form-v4 col-sm-12 col-md-12">
								<div class="form-v4-content">
									<form class="form-detail" action="#" method="post"
										id="form-edit">

										<div class="form-row">
											<label for="password">Mật khẩu hiện tại (*)</label> <input
												type="password" id="password" name="password" class="input-text" required>
											<label name="password-error" class="error hidden">Mật
												khẩu chưa chính xác</label>
										</div>
										<div class="form-row">
											<label for="new-password">Mật khẩu mới (*)</label> <input
												type="password" name="newPassword" id="new-password" class="input-text" required>
											<label name="new-password-error" class="error hidden">Mật khẩu không hợp lệ</label>
										</div>
										<div class="form-row">
											<label for="new-password">Xác nhận mật khẩu mới (*)</label> <input
												type="password" id="confirm-password" class="input-text" required>
											<label name="confirm-password-error" class="error hidden">Xác nhận không khớp</label>
										</div>
									</form>
								</div>
							</div>
						</div>

						<!--Footer-->
						<div class="border-top my-3"></div>
						<div class='row d-flex justify-content-center mt-4'>
							<button type="button" class="btn btn-primary btn-submit"
								id="btn-submit-form">Cập Nhật</button>
						</div>
					</div>
				</div>
			</div>



			<!-- table -->


			<!-- end table -->
		</div>

	</div>
	<!-- modal alert -->
	<div class="modal modal-reset fade" id="modal-alert" tabindex="-1"
		role="dialog" aria-labelledby="edit" aria-hidden="true">
		<div class="modal-dialog modal-sm">
			<div class="modal-content">
				<!--Header-->
				<div class="modal-header bg-success">
					<p class="heading lead">Xác Nhận</p>

					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true" class="white-text">×</span>
					</button>
				</div>

				<!--Body-->
				<div class="modal-body border-top my-3">
					<div class="message">Bạn Có Muốn Tiếp Tục Lưu Thay Đổi ?</div>

					<div class="border-top my-3"></div>
					<div class="d-flex justify-content-center">
						<a type="button" class="btn  waves-effect btn-cancel"
							data-dismiss="modal">Hủy</a><a type="button"
							class="btn  waves-effect btn-submit">Lưu</a>
					</div>
				</div>

			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->

	</div>

	<!-- end modal alert -->
	<jsp:include page="/WEB-INF/views/commons/lib.jsp"></jsp:include>
	<script src="<c:url value='/resources/commons/js/base.js' />"></script>
	<script src="<c:url value='/resources/commons/js/change-password.js' />"></script>
</body>
</html>