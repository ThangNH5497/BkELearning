<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<nav id="sidebar">
	<!--  
	<div class="custom-menu">

		<button type="button" id="sidebarCollapse" class="btn btn-primary">
			<i class="fa fa-bars"></i> <span class="sr-only">Toggle Menu</span>
		</button>

	</div>-->
	<div class="p-4">
		<h1>
			<a href="index.html" class="logo">BK ELearning <span>Admin
					page</span></a>
		</h1>
		<ul class="list-unstyled components mb-5">
			<li id="menu-item-home"><a
				href="<c:url value='/admin/trang-chu'/>"><span
					class="icon fa fa-briefcase mr-3"></span> <span class="redirect">Trang
						Chủ</span></a></li>

			<li id="menu-item-teacher"><a
				href="<c:url value='/admin/ql-giang-vien'/>"><span
					class="icon fas fa-chalkboard-teacher mr-3"></span> <span
					class="redirect">Giảng Viên</span></a></li>

			<li id="menu-item-student"><a
				href="<c:url value='/admin/ql-sinh-vien'/>"><span
					class="icon fas fa-user-graduate mr-3"></span> <span
					class="redirect">Sinh Viên</span></a></li>

			<li id="menu-item-subject"><a
				href="<c:url value='/admin/ql-mon-hoc'/>"><span
					class="icon fas fa-book-open mr-3"></span> <span class="redirect">Môn
						Học</span></a></li>
			<!-- href="<c:url value='/admin/ql-cau-hoi'/>"-->
			<li id="menu-item-question"><a data-toggle="collapse"
				data-target="#pageSubmenu" aria-expanded="false"
				aria-controls="pageSubmenu"><span
					class="icon fas fa-book-open mr-3"></span> <span class="redirect">Câu
						Hỏi <i class="fas fa-caret-down pl-2"></i>
				</span></a></li>

			<!-- collapse -->
			<ul class="collapse list-unstyled bg-white rounded" id="pageSubmenu">
				<li><a href="<c:url value='/admin/ql-cau-hoi/them-cau-hoi'/>"
					class="text-gray-600 text-sm  rounded"><span
						id='menu-add-question' class="redirect ml-4">Thêm Câu Hỏi</span></a></li>
				<li><a
					href="<c:url value='/admin/ql-cau-hoi/kho-chung?subject=ALL&level=ALL&type=ALL'/>"
					id='question-bank-common-menu'
					class="text-gray-600 text-sm  rounded"><span
						class="redirect ml-4">Kho Chung</span></a></li>
				<li><a
					href="<c:url value='/admin/ql-cau-hoi/kho-giang-vien?teacher=ALL&subject=ALL&level=ALL&type=ALL'/>"
					id='question-bank-teacher-menu'
					class="text-gray-600 text-sm  rounded"> <span
						class="redirect ml-4">Kho Giảng Viên</span></a></li>
				<li><a href="<c:url value='/admin/ql-danh-muc'/>"
					class="text-gray-600 text-sm  rounded"> <span
						class="redirect ml-4" id='category-list'>QL Danh Mục</span></a></li>
			</ul>
			<!-- end collapse -->
			
			<li id="menu-item-exampaper"><a data-toggle="collapse"
				data-target="#submenu-exampaper" aria-expanded="false"
				aria-controls="submenu-exampaper"><span
					class="icon fas fa-book-open mr-3"></span> <span class="redirect">Đề Thi <i class="fas fa-caret-down pl-2"></i>
				</span></a></li>
			<!-- collapse -->
			<ul class="collapse list-unstyled bg-white rounded submenu"
				id="submenu-exampaper">
				<li><a href="<c:url value='/admin/ql-de-thi/them-moi'/>"
					class="text-gray-600 text-sm  rounded"><span
						id='menu-add-exampaper' class="redirect ml-4">Thêm Đề Thi</span></a></li>
				<li><a
					href="<c:url value='/admin/ql-de-thi/danh-sach?subject=ALL'/>"
					class="text-gray-600 text-sm  rounded"><span
						id='exampaper-list' class="redirect ml-4">QL Đề Thi</span></a></li>

			</ul>


			<li><a href="#"><span class="icon fa fa-sticky-note mr-3"></span>
					<span class="redirect">Thông Tin</span></a></li>

			<li><a href="#"><span class="icon fa fa-cogs mr-3"></span> <span
					class="redirect">Hệ Thống</span></a></li>
			<li><a href="<c:url value='/logout'/>"><span
					class="icon fas fa-sign-out-alt mr-3"></span> <span
					class="redirect">Đăng Xuất</span></a></li>

		</ul>

		<div class="mb-5">
			<!-- 
				<h3 class="h6 mb-3">Subscribe for newsletter</h3>
				<form action="#" class="subscribe-form">
					<div class="form-group d-flex">
						<div class="icon">
							<span class="icon-paper-plane"></span>
						</div>
						<input type="text" class="form-control"
							placeholder="Enter Email Address">
					</div>
				</form>
				 -->
		</div>

		<div class="footer"></div>

	</div>
</nav>