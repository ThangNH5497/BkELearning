<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="/WEB-INF/views/manager/commons/header.jsp"></jsp:include>
<link rel="stylesheet"
	href="<c:url value='/resources/commons/css/login.css' />">

<section class="login-block">
	<div class="container">
		<div class="row">
			<div class="col-md-4 login-sec">
				<h2 class="text-center">Đăng Nhập</h2>
				<p class="text-center text-danger">${requestScope.message}</p>
				<form class="login-form" action="<c:url value='j_spring_security_login' />" method='POST'>
					<div class="form-group">
						<label for="exampleInputEmail1" class="text-uppercase">Tài
							Khoản</label> <input type="text" class="form-control" placeholder="" name="username">

					</div>
					<div class="form-group">
						<label for="exampleInputPassword1" class="text-uppercase">Mật
							Khẩu</label> <input type="password" class="form-control" placeholder="" name="password">
					</div>


					<div class="form-check">
						<label class="form-check-label"> <input type="checkbox"
							class="form-check-input"> <small>Ghi nhớ</small>
						</label>
						<button type="submit" class="btn btn-login float-right">Đăng
							Nhập</button>
					</div>
					
					<input type="hidden" name="${_csrf.parameterName}"
						value="${_csrf.token}" />
				</form>
				<div class="copy-text">
					<a href="https://soict.hust.edu.vn/">soict-hust</a>
				</div>
			</div>
			<div class="col-md-8 banner-sec">
				<div id="carouselExampleIndicators" class="carousel slide"
					data-ride="carousel">
					<ol class="carousel-indicators">
						<li data-target="#carouselExampleIndicators" data-slide-to="0"
							class="active"></li>
						<li data-target="#carouselExampleIndicators" data-slide-to="1"></li>

					</ol>
					<div class="carousel-inner" role="listbox">
						<div class="carousel-item active">
							<img class="d-block img-fluid"
								src="<c:url value='/resources/commons/image/login/slide-1.jpg' />"
								alt="First slide">
							<div class="carousel-caption d-none d-md-block">
								<div class="banner-text">
									<h2>Bách Khoa Hà Nội</h2>
									<p>Trường Đại học Bách khoa Hà Nội</p>
								</div>
							</div>
						</div>
						<div class="carousel-item">
							<img class="d-block img-fluid"
								src="<c:url value='/resources/commons/image/login/slide-2.jpg' />"
								alt="Second slide">
							<div class="carousel-caption d-none d-md-block">
								<div class="banner-text">
									<h2>Hệ Thống Thi trực tuyến</h2>
									<p>Hệ Thống thi trực tuyến , trực thuộc Viện Công nghệ
										Thông tin và Truyền Thông , đại học Bách Khoa Hà Nội</p>
								</div>
							</div>
						</div>

					</div>

				</div>
			</div>
		</div>
	</div>
</section>
<jsp:include page="/WEB-INF/views/commons/lib.jsp"></jsp:include>
<jsp:include page="/WEB-INF/views/manager/commons/footer.jsp"></jsp:include>