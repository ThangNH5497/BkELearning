<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<nav
	class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">

	<div class="custom-menu">
		<button id="sidebarCollapse" class="btn btn-link rounded-circle mr-3">
			<i class="fa fa-bars"></i>
		</button>
	</div>

	<!-- Topbar Search -->
	<form id='search-form'
		class="d-none d-sm-inline-block form-inline mr-auto ml-md-3 my-2 my-md-0 mw-100 navbar-search">
		<div class="input-group">
			<input id="key-search" type="text"
				class="form-control bg-light border-0 small"
				placeholder="Nhập mã hoặc tên..." aria-label="Search"
				aria-describedby="basic-addon2">
			<div class="input-group-append">
				<button class="btn btn-primary " type="button" id="btn-search">
					<i class="fas fa-search fa-sm"></i>
				</button>
			</div>
		</div>
	</form>

	<!-- Topbar Navbar -->
	<ul class="navbar-nav ml-auto">

		<!-- Nav Item - Search Dropdown (Visible Only XS) -->
		<li class="nav-item dropdown no-arrow d-sm-none" id='search-xs'><a
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
							placeholder="Nhập Mã Hoặc Tên..." aria-label="Search"
							aria-describedby="basic-addon2" id='key-search-xs'>
						<div class="input-group-append">
							<button class="btn btn-primary" type="button" id='btn-search-xs'>
								<i class="fas fa-search fa-sm"></i>
							</button>
						</div>
					</div>
				</form>
			</div></li>

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
<!-- End of Topbar -->