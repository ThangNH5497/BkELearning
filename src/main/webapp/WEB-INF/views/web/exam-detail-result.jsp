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
<body style="background: #f0f3f7;">
	<div id="main-content">
		<div class="row m-0 p-0">
			<div class=" container mt-4 mb-4" id="main">

				<div class="header border p-4">
					<h4 class="text-center text-primary">Chi tiết bài thi sinh
						viên</h4>
						<h5 class="text-center text-primary" id="count-student-grade"></h5>
						<h5 class="text-center text-primary" id="count-question-grade"></h5>
					<!-- <div class="row d-flex m-0 p-0 mt-4">
						<div class=" m-0 p-0  text-center">
							
						</div>
						<div class="m-0 p-0 text-center">
							
						</div>
					</div> -->
				</div>
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
												<div class="pt-4 ml-1 border-top">Chấm Điểm</div>
												<div class='row mx-0 p-0 mt-4'>
													<form>
														<div class="row">
															<div class="col">
																<input type="text" class="form-control student-grade"
																	value="0" readonly>
															</div>
															<div class="col">
																<input type="text" class="form-control max-grade"
																	readonly>
															</div>
														</div>
													</form>
												</div>
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


	<!-- end modal alert -->

	<jsp:include page="/WEB-INF/views/commons/lib.jsp"></jsp:include>
	<script src="<c:url value='/resources/commons/js/base.js' />"></script>
	<script src="<c:url value='/resources/web/js/exam-detail-result.js' />"></script>

</body>
</html>