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
	href="<c:url value='/resources/web/css/style.css' />">
	<link rel="stylesheet"
	href="<c:url value='/resources/web/css/do-exam.css' />">

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
<body style="background: #f0f3f7;">
	<div class="loader row justify-content-end"
		<%-- style='background: url("<c:url value='/resources/commons/image/hust-bg.jpg' />") no-repeat center ; background-size:100% 100%;' --%>>
		<div class="align-self-center w-100">
			<img class="d-block img-fluid"
				src="<c:url value='/resources/commons/image/icon/load-icon.gif' />"
				alt="" style="margin: auto; width: 48px; height: 48px;">
		</div>

	</div>
	<div class=" hidden" id="main-content">
		<div class="row m-0 p-0">
			<div class="col-2 px-1 position-fixed h-100 pt-4" id="sidebar">
				<div class="card shadow mt-4">
					<div class="card-body">

						<div class="mt-4 mb-4 pl-2 border-bottom pb-2" id="student-info">
							<div class="student-name"></div>
							<div class="student-code"></div>
						</div>

						<div class="row no-gutters align-items-center">
							<div class="container-grid" id="pagination-question"></div>

						</div>

						<div id='time-counter'
							class="d-flex mb-4 mt-4 text-lg border p-2 border-info rounded">
							<div class="col-lg-2 col-md-12">
								<i class="far fa-clock text-info"></i>
							</div>
							<div class="minute col-lg-4 col-md-5 text-center text-primary"></div>
							<div class="col-lg-1 col-md-2 text-center text-primary">:</div>
							<div class="second col-lg-5 col-md-5 text-center text-primary"></div>
						</div>
						
						

						<div class="my-4 justify-content-center row border-top pt-4">
							<a href="#" class="btn btn-success btn-icon-split btn-sm " id="finish-attempt"> <span
								class="text">Nộp Bài</span> <span class="icon text-white-50">
									<i class="fas fa-check"></i>
							</span>
							</a>
						</div>
					

					</div>
				</div>

			</div>

			<div class="w-80 offset-2 container mt-4 mb-4" id="main">
				<div class='question'>
					<div id='question-row-sample' class='hidden'>
						<div class=' col-2 '>
							<div>

								<div class="card border-left-primary shadow">
									<div class="card-body">
										<div class="row no-gutters align-items-center">
											<div class="col">
												<div
													class="font-weight-bold text-primary  mb-1 question-index"></div>

											</div>

										</div>
									</div>
								</div>
							</div>
						</div>
						<div class='question-content col-10'>
							<div class="card shadow h-100 py-2">
								<div class="card-body">
									<div class="row no-gutters align-items-center">
										<div class="col mr-2">
											<div field='content'></div>
											<div class='answer border-top mt-4 pt-4'>
												<div field="answer.content"></div>
											</div>
										</div>
									</div>

								</div>
							</div>
						</div>
					</div>

				</div>
				<div id="question-container"></div>

			</div>
		</div>
	</div>
<!-- modal alert -->
<div class="modal modal-reset fade" id="modal-alert"
	tabindex="-1" role="dialog" aria-labelledby="edit" aria-hidden="true">
	<div class="modal-dialog modal-sm">
		<div class="modal-content">
			<!--Header-->
			<div class="modal-header bg-warning">
				<p class="heading lead">Cảnh Báo</p>

				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true" class="white-text">×</span>
				</button>
			</div>

			<!--Body-->
			<div class="modal-body border-top my-3">
				<div class="message">Bạn Có Muốn Tiếp Nộp Bài ?</div>

				<div class="border-top my-3"></div>
				<div class="d-flex justify-content-center">
					<a type="button" class="btn  waves-effect btn-cancel"
						data-dismiss="modal">Hủy</a><a type="button"
						class="btn  waves-effect btn-submit">Nộp Bài</a>
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
	<script src="<c:url value='/resources/web/js/do-exam.js' />"></script>
	<script src="<c:url value='/resources/commons/lib/websocket/sockjs.min.js' />"></script>
	<script src="<c:url value='/resources/commons/lib/websocket/stomp.min.js' />"></script>
	<script src="<c:url value='/resources/web/js/websocket-process.js' />"></script>
	<script src="<c:url value='/resources/commons/lib/jspdf/jspdf.min.js' />"></script>
</body>
</html>