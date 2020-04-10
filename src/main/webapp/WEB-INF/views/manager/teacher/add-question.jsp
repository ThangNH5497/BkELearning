<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="/WEB-INF/views/commons/header.jsp"></jsp:include>
<link rel="stylesheet"
	href="<c:url value='/resources/commons/css/style.css' />">
<link rel="stylesheet"
	href="<c:url value='/resources/manager/commons/css/style.css' />">
<link rel="stylesheet"
	href="<c:url value='/resources/manager/commons/css/dashboard.css' />">

<link rel="stylesheet"
	href="<c:url value='/resources/commons/lib/summernote/summernote-bs4.min.css' />">
<link rel="stylesheet"
	href="<c:url value='/resources/manager/commons/css/add-question.css' />">

<!-- Phan noi dung trang -->
<div class="wrapper d-flex align-items-stretch">
	<jsp:include page="/WEB-INF/views/manager/teacher/menu.jsp"></jsp:include>

	<!-- Page Content  -->
	<div id="content" class="mb-5">
		<jsp:include page="/WEB-INF/views/manager/commons/topbar.jsp"></jsp:include>
		<div class="container-fluid">
			<div
				class="d-sm-flex align-items-center justify-content-between mb-4">
				<h1 class="h4 mb-0 text-gray-600">Thêm Câu Hỏi</h1>

				<a href="#"
					class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm "><i
					class="fas fa-file-import fa-sm text-white-50"></i> Import Câu Hỏi</a>
			</div>
			<!-- table -->
			<div class="question-wrap">
				<div class="d-flex row">
					<label class="col-2 text-lg">Nội Dung Câu Hỏi</label>
					<div class="col-9 wrap-editor">
						<div class="question-editor"></div>
					</div>
				</div>

				<div id='wrap-answer'>
					<div id='answer-sample' class='hidden'>
						<div class="d-flex row mt-4 answer">
							<label class="col-2 answer-number">Câu Trả Lời </label>
							<div class="wrap-editor col-9">
								<div class="answer-editor"></div>
							</div>
							<div class="col-1 justify-content-start">

								<button
									class="btn text-danger btn-control p-0 m-0 btn-remove-answer">
									<i class="fas fa-minus-square"></i>
								</button>
							</div>
						</div>
					</div>
					<div class="d-flex row mt-4 answer">
						<label class="col-2 answer-number">Câu Trả Lời</label>
						<div class="wrap-editor col-9">
							<div class="answer-editor"></div>
						</div>
						<div class="col-1 justify-content-start">

							<button
								class="btn text-danger btn-control p-0 m-0 btn-remove-answer">
								<i class="fas fa-minus-square"></i>
							</button>
						</div>
					</div>
					<div class="d-flex row mt-4 answer">
						<label class="col-2 answer-number">Câu Trả Lời</label>
						<div class="wrap-editor col-9">
							<div class="answer-editor"></div>
						</div>
						<div class="col-1 justify-content-start">

							<button
								class="btn text-danger btn-control p-0 m-0 btn-remove-answer">
								<i class="fas fa-minus-square"></i>
							</button>
						</div>
					</div>
					<div class="d-flex row mt-4 answer">
						<label class="col-2 answer-number">Câu Trả Lời</label>
						<div class="wrap-editor col-9">
							<div class="answer-editor"></div>
						</div>
						<div class="col-1 justify-content-start">

							<button
								class="btn text-danger btn-control p-0 m-0 btn-remove-answer">
								<i class="fas fa-minus-square"></i>
							</button>
						</div>
					</div>
					<div class="d-flex row mt-4 answer">
						<label class="col-2 answer-number">Câu Trả Lời</label>
						<div class="wrap-editor col-9">
							<div class="answer-editor"></div>
						</div>
						<div class="col-1 justify-content-start">

							<button
								class="btn text-danger btn-control p-0 m-0 btn-remove-answer">
								<i class="fas fa-minus-square"></i>
							</button>
						</div>
					</div>
				</div>
				<div class="d-flex row mt-4">
					<div class='col-11'></div>
					<div class="col-1 justify-content-start">

						<button
							class="btn text-success btn-control p-0 m-0 btn-add-answer">
							<i class="fas fa-plus-square"></i>
						</button>
					</div>
				</div>
				<div class='row d-flex justify-content-center'>
					<button type="button" class="btn btn-success btn-submit">Lưu Câu Hỏi</button>
				</div>

			</div>

			<!-- end table -->
		</div>

	</div>

</div>

<jsp:include page="/WEB-INF/views/commons/lib.jsp"></jsp:include>
<script src="<c:url value='/resources/commons/js/base.js' />"></script>
<script
	src="<c:url value='/resources/commons/lib/summernote/summernote-bs4.min.js' />"></script>
<script
	src="<c:url value='/resources/manager/teacher/js/text-editor.js' />"></script>


<!-- Footer -->
<script type="text/javascript">
	$('#sidebar .active').removeClass('active');
	$('#menu-item-home').addClass('active');
	(function($) {

		"use strict";

		var fullHeight = function() {

			$('.js-fullheight').css('height', $(window).height());
			$(window).resize(function() {
				$('.js-fullheight').css('height', $(window).height());
			});

		};
		fullHeight();

		$('#sidebarCollapse').on('click', function() {
			$('#sidebar').toggleClass('active');

		});

	})(jQuery);
</script>

<jsp:include page="/WEB-INF/views/commons/footer.jsp"></jsp:include>