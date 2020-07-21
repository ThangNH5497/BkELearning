<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="/WEB-INF/views/commons/header.jsp"></jsp:include>
<link rel="stylesheet"
	href="<c:url value='/resources/web/css/home.css' />">

<!--bat dau body-->
<div class="container mt-4 mb-4">
	<div class="card shadow mb-4">
		<div class="card-header py-3">
			<h6 class="m-0 font-weight-bold text-primary">Thông tin bài thi</h6>
		</div>
		<div class="card-body">
			<div class='row'>
				<div class='col-md-6 col-sm-12 d-flex mt-4 mb-4'>
					<div class='col-4 text-primary'>Môn học</div>
					<div class='col-8' field='subject'></div>
				</div>
				<div class='col-md-6 col-sm-12 d-flex mt-4 mb-4'>
					<div class='col-4 text-primary'>Lớp</div>
					<div class='col-8' field='course'></div>
				</div>
			</div>
			<div class='row'>
				<div class='col-md-6 col-sm-12 d-flex mt-4 mb-4'>
					<div class='col-4 text-primary'>Mã bài thi</div>
					<div class='col-8' field='code'></div>
				</div>
				<div class='col-md-6 col-sm-12 d-flex mt-4 mb-4'>
					<div class='col-4 text-primary'>Tên bài thi</div>
					<div class='col-8' field='name'></div>
				</div>
			</div>
			<div class='row'>
				<div class='col-md-6 col-sm-12 d-flex mt-4 mb-4'>
					<div class='col-4 text-primary'>Thời Gian</div>
					<div class='col-8' field='time'></div>
				</div>
				<div class='col-md-6 col-sm-12 d-flex mt-4 mb-4'>
					<div class='col-4 text-primary'>Trạng thái</div>
					<div class='col-8' field='status'></div>
				</div>
			</div>

			
			<div class='row mt-4 mb-4 justify-content-center border-top pt-4'>
				<a href="#" class="btn btn-success btn-icon-split mt-4" id='btn-do-exam'> <span
					class="text">Vào bài thi</span> <span class="icon text-white-50">
						<i class="fas fa-arrow-right"></i>
				</span>
				</a>
			</div>
		</div>
	</div>
</div>


<jsp:include page="/WEB-INF/views/commons/lib.jsp"></jsp:include>
<script src="<c:url value='/resources/commons/js/base.js' />"></script>
<script src="<c:url value='/resources/commons/js/pagination.js' />"></script>
<script src="<c:url value='/resources/manager/commons/js/search.js' />"></script>
<script src="<c:url value='/resources/web/js/start-exam.js' />"></script>

<jsp:include page="/WEB-INF/views/commons/footer.jsp"></jsp:include>