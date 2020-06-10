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

		<div class='row w-100 p-0 m-0'>
			<div class=" col-3">
				<a class="navbar-brand" href="#"> <img
					style="max-height: 64px !important;"
					src="<c:url value='/resources/commons/image/hust.png' />" alt="">
				</a>
			</div>
			<div class="col-6 h-100 row ">
				<div class="input-group w-100 align-self-center">
					<input type="text" class="form-control"
						placeholder="Nhập Mã Hoặc Tên Bài Thi...">
					<div class="input-group-append">
						<button class="btn btn-secondary" type="button">
							<i class="fa fa-search"></i>
						</button>
					</div>
				</div>
			</div>
			<!-- right -->
			<div class="col-3 ">
				<nav class="navbar navbar-expand-sm justify-content-end">
					<ul class="navbar-nav ml-auto">
						<!-- Nav Item - User Information -->
						<li class="nav-item dropdown no-arrow"><a
							class="nav-link dropdown-toggle" href="#" id="userDropdown"
							data-toggle="dropdown"><img
								style="max-height: 48px; max-width: 48px;"
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

				<li class="nav-item"><a class="nav-link main-menu" href="#">
						<span class="menu-button">LỚP HỌC</span>
				</a></li>
				
				<li class="nav-item"><a class="nav-link main-menu" href="#">
						<span class="menu-button">BÀI THI</span>
				</a></li>

				<!-- Dropdown -->
				<!-- <li class="nav-item dropdown"><a
					class="nav-link dropdown-toggle main-menu" href="#" id="navbardrop"
					data-toggle="dropdown"> <span class="menu-button">Tin Tức</span>
				</a>
					<div class="dropdown-menu menu-top-danhmuc"></div></li> -->
			</ul>
		</div>



	</nav>



	<!--ket thuc header-->