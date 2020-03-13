<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html style="height: 100%;">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="_csrf" content="${_csrf.token}" />
<!-- default header name is X-CSRF-TOKEN -->
<meta name="_csrf_header" content="${_csrf.headerName}" />
<title>Bk ELearning</title>
<link rel="stylesheet"
	href="<c:url value='/resources/commons/lib/bootstrap-4.4.1/css/bootstrap.min.css' /> ">
<link rel="stylesheet"
	href="<c:url value='/resources/commons/lib/fontawesome5.12.0/css/all.css' />">
<link rel="stylesheet"
	href="<c:url value='/resources/manager/commons/css/test.css' />">
</head>
<body style="height: 100%;">

	<div class="wrapper d-flex align-items-stretch h-100" >
		<nav id="sidebar">
			<div class="custom-menu">
				<button type="button" id="sidebarCollapse" class="btn btn-primary">
					<i class="fa fa-bars"></i> <span class="sr-only">Toggle Menu</span>
				</button>
			</div>
			<div class="p-4">
				<h1>
					<a href="index.html" class="logo">Portfolic <span>Portfolio
							Agency</span></a>
				</h1>
				<ul class="list-unstyled components mb-5">
					<li class="active"><a href="#"><span
							class="fa fa-home mr-3"></span> Home</a></li>
					<li><a href="#"><span class="fa fa-user mr-3"></span>
							About</a></li>
					<li><a href="#"><span class="fa fa-briefcase mr-3"></span>
							Works</a></li>
					<li><a href="#"><span class="fa fa-sticky-note mr-3"></span>
							Blog</a></li>
					<li><a href="#"><span class="fa fa-suitcase mr-3"></span>
							Gallery</a></li>
					<li><a href="#"><span class="fa fa-cogs mr-3"></span>
							Services</a></li>
					<li><a href="#"><span class="fa fa-paper-plane mr-3"></span>
							Contacts</a></li>
				</ul>

				<div class="mb-5">
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
				</div>

				<div class="footer">
					<p>
						<!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
						Copyright &copy;
						<script>
							document.write(new Date().getFullYear());
						</script>
						All rights reserved | This template is made with <i
							class="icon-heart" aria-hidden="true"></i> by <a
							href="https://colorlib.com" target="_blank">Colorlib.com</a>
						<!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
					</p>
				</div>

			</div>
		</nav>

		<!-- Page Content  -->
		<div id="content" class="p-4 p-md-5 pt-5">
			<h2 class="mb-4">Sidebar #05</h2>
			<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed
				do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut
				enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi
				ut aliquip ex ea commodo consequat. Duis aute irure dolor in
				reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla
				pariatur. Excepteur sint occaecat cupidatat non proident, sunt in
				culpa qui officia deserunt mollit anim id est laborum.</p>
			<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed
				do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut
				enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi
				ut aliquip ex ea commodo consequat. Duis aute irure dolor in
				reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla
				pariatur. Excepteur sint occaecat cupidatat non proident, sunt in
				culpa qui officia deserunt mollit anim id est laborum.</p>
		</div>
	</div>
	<jsp:include page="/WEB-INF/views/commons/lib.jsp"></jsp:include>
	<!-- Footer -->
	<script type="text/javascript">
	(function($) {

		"use strict";

		var fullHeight = function() {

			$('.js-fullheight').css('height', $(window).height());
			$(window).resize(function(){
				$('.js-fullheight').css('height', $(window).height());
			});

		};
		fullHeight();

		$('#sidebarCollapse').on('click', function () {
	      $('#sidebar').toggleClass('active');
	  });

	})(jQuery);
	</script>
</body>
</html>