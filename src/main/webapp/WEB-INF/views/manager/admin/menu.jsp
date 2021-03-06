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
						chủ</span></a></li>

			<li id="menu-item-teacher"><a
				href="<c:url value='/admin/ql-giang-vien'/>"><span
					class="icon fas fa-chalkboard-teacher mr-3"></span> <span
					class="redirect">Giảng viên</span></a></li>

			<li id="menu-item-student"><a
				href="<c:url value='/admin/ql-sinh-vien'/>"><span
					class="icon fas fa-user-graduate mr-3"></span> <span
					class="redirect">Sinh viên</span></a></li>

			<li id="menu-item-subject"><a
				href="<c:url value='/admin/ql-mon-hoc'/>"><span
					class="icon fas fa-book-open mr-3"></span> <span class="redirect">Môn
						học</span></a></li>
			<!-- href="<c:url value='/admin/ql-cau-hoi'/>"-->
			<li id="menu-item-question"><a data-toggle="collapse"
				data-target="#pageSubmenu" aria-expanded="false"
				aria-controls="pageSubmenu"><span
					class="icon fas fa-book-open mr-3"></span> <span class="redirect">Câu
						hỏi <i class="fas fa-caret-down pl-2"></i>
				</span></a></li>

			<!-- collapse -->
			<ul class="collapse list-unstyled bg-white rounded submenu" id="pageSubmenu">
				<li><a href="<c:url value='/admin/ql-cau-hoi/them-cau-hoi'/>"
					class="text-gray-600 text-sm  rounded"><span
						id='menu-add-question' class="redirect ml-4">Thêm câu hỏi</span></a></li>
				<li><a
					href="<c:url value='/admin/ql-cau-hoi/kho-chung?subject=ALL&level=ALL&type=ALL'/>"
					id='question-bank-common-menu'
					class="text-gray-600 text-sm  rounded"><span
						class="redirect ml-4">Kho chung</span></a></li>
				<li><a
					href="<c:url value='/admin/ql-cau-hoi/kho-giang-vien?teacher=ALL&subject=ALL&level=ALL&type=ALL'/>"
					id='question-bank-teacher-menu'
					class="text-gray-600 text-sm  rounded"> <span
						class="redirect ml-4">Kho giảng viên</span></a></li>
				<li><a href="<c:url value='/admin/ql-danh-muc'/>"
					class="text-gray-600 text-sm  rounded"> <span
						class="redirect ml-4" id='category-list'>QL Danh mục</span></a></li>
			</ul>
			<!-- end collapse -->
			
			<li id="menu-item-exampaper"><a data-toggle="collapse"
				data-target="#submenu-exampaper" aria-expanded="false"
				aria-controls="submenu-exampaper"><span
					class="icon fas fa-book-open mr-3"></span> <span class="redirect">Đề thi <i class="fas fa-caret-down pl-2"></i>
				</span></a></li>
			<!-- collapse -->
			<ul class="collapse list-unstyled bg-white rounded submenu"
				id="submenu-exampaper">
				<li><a href="<c:url value='/admin/ql-de-thi/them-moi'/>"
					class="text-gray-600 text-sm  rounded"><span
						id='menu-add-exampaper' class="redirect ml-4">Thêm đề thi</span></a></li>
				<li><a
					href="<c:url value='/admin/ql-de-thi/danh-sach?subject=ALL'/>"
					class="text-gray-600 text-sm  rounded"><span
						id='exampaper-list' class="redirect ml-4">QL đề thi</span></a></li>

			</ul>


			<li><a href="<c:url value='/logout'/>"><span
					class="icon fas fa-sign-out-alt mr-3"></span> <span
					class="redirect">Đăng xuất</span></a></li>

		</ul>

		<div class="mb-5">
		</div>

		<div class="footer"></div>

	</div>
</nav>