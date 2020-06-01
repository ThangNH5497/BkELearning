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
	href="<c:url value='/resources/web/css/header.css' />">
<link rel="stylesheet"
	href="<c:url value='/resources/web/css/footer.css' />">
</head>
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
<!-- lay user da dang nhap -->

<body>
	<!-- hop thoai thong baso tuy chinh -->
	<div class="container-fluid header">

		<div class="left ">
			<a class="navbar-brand" href="#"> <img
				style="max-height: 64px !important;"
				src="<c:url value='/resources/commons/image/hust.png' />" alt="">
			</a>

			<div class="tablet">
				<nav class="navbar navbar-expand-sm bg-light justify-content-center">
					<ul class="navbar-nav container-fluid">
						<li class="nav-item col-md-2 col-sm-2 "><a
							class="nav-link d-flex h-100" href="#"><i
								class="fab fa-facebook align-self-center"></i></a></li>
						<li class="nav-item col-md-2 col-sm-2"><a
							class="nav-link d-flex h-100" href="#"><i
								class="fab fa-google-plus-g align-self-center"></i></a></li>
						<li class="nav-item dropdown col-md-8 col-sm-8 "><a
							class="nav-link d-flex h-100" href="#"
							style="display: flex; width: 100%; padding: 0px; margin: 0px;"
							id="navbardrop" data-toggle="dropdown"> <i
								class="fa fa-user-circle align-self-center"></i><span
								class="account-info align-self-center"
								style="padding-left: 10px;"> Tài Khoản</span>
						</a>
							<div class="dropdown-menu login-dropdown-menu">
								<a class="dropdown-item " style="width: 100%; padding: 5px;"><button
										type="button"
										class="btn btn-success btn-block btn-authentication modal-authentication-open">Đăng
										nhập</button></a>
								<div class="dropdown-divider"></div>
								<a class="dropdown-item" href="register"
									style="width: 100%; padding: 5px;"><button type="button"
										class="btn btn-light btn-block">Đăng ký</button></a>
							</div></li>


					</ul>
				</nav>
			</div>
		</div>
		<div class="center row  ">
			<div class="input-group align-self-center">
				<form class="form-inline" method="GET" action="tim-kiem">
					<input type="text" class="form-control" placeholder="Tìm kiếm"
						aria-label="Search" name="search" style="width: 90%;">
					<div class="input-group-append " style="width: 10%; padding: 0px;">
						<button class="btn btn-outline-secondary" type="submit">
							<i class="fas fa-search" aria-hidden="true"></i>
						</button>
					</div>
				</form>
			</div>
		</div>
		<!-- right -->
		<div class="right ">
			<nav class="navbar navbar-expand-sm bg-light justify-content-center">
				<!-- <ul class="navbar-nav">
					<li class="nav-item col-md-2 col-sm-2 "><a
						class="nav-link d-flex h-100" href="#"><i
							class="fab fa-facebook align-self-center"></i></a></li>
					<li class="nav-item col-md-2 col-sm-2"><a
						class="nav-link d-flex h-100" href="#"><i
							class="fab fa-google-plus-g align-self-center"></i></a></li>
					<li class="nav-item dropdown col-md-8 col-sm-8 "><a
						class="nav-link d-flex h-100" href="#"
						style="display: flex; width: 100%; padding: 0px; margin: 0px;"
						id="navbardrop" data-toggle="dropdown"> <i
							class="fa fa-user-circle align-self-center"></i><span
							class="account-info align-self-center"
							style="padding-left: 10px;"> Tài Khoản</span>
					</a>
						<div class="dropdown-menu login-dropdown-menu">
							<a class="dropdown-item " style="width: 100%; padding: 5px;"><button
									type="button"
									class="btn btn-success btn-block btn-authentication modal-authentication-open">Đăng
									nhập</button></a>
							<div class="dropdown-divider"></div>
							<a class="dropdown-item" href="register"
								style="width: 100%; padding: 5px;"><button type="button"
									class="btn btn-light btn-block">Đăng ký</button></a>
						</div></li>


				</ul> -->
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
		</div>

	</div>

	<nav
		class="navbar navbar-expand-sm bg-success navbar-light menu text-light"
		id="menu-top" style="margin-top: 20px;">
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#collapsibleNavbar">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="collapsibleNavbar">
			<ul class="navbar-nav justify-content-center">
				<li class="nav-item"><a class="nav-link active main-menu home">
						<span class="menu-button">TRANG CHỦ</span>
				</a></li>
				<li class="nav-item"><a class="nav-link main-menu" href="#">
						<span class="menu-button">GIỚI THIỆU</span>
				</a></li>



				<!-- Dropdown -->
				<li class="nav-item dropdown"><a
					class="nav-link dropdown-toggle main-menu" href="#" id="navbardrop"
					data-toggle="dropdown"> <span class="menu-button">SẢN
							PHẨM</span>
				</a>
					<div class="dropdown-menu menu-top-danhmuc"></div></li>
		</div>

		</ul>
		</div>
	</nav>



	<!--ket thuc header-->