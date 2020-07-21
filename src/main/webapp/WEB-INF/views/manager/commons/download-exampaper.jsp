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
	href="<c:url value='/resources/commons/css/download-exampaper.css' />">
<style type="text/css">
#main-content {
	/* height: 3000px; */
	
}

.answer-container div {
	font-size: 11pt !important;
}

.answer-sheet-item {
	width: 5%;
	box-sizing: border-box;
}

#answer-sheet .answer-sheet-item div {
	min-height: 25px;
}

.hidden {
	display: none;
}
p{
margin-bottom: 8px;
}
</style>
</head>
<body>

	<div id="main-content" class="mb-4">
		<div class="container mt-1">
			<div class="header border mb-4 pt-4">
				<div class='row header m-0 p-0 justify-content-center d-block'>
					<h5 class="text-center font-weight-bold" field='title'>Đề Thi
						kết thúc học phần toán rời rạc</h5>

					<div class="text-center font-weight-bold time w-100" field='time'>Thời
						Gian : 90 phút</div>

				</div>
				<div class="exam-info row m-0 p-0 d-flex mt-2 mb-4">
					<div class='col-6 d-flex'>
						<div class="col-4">Học phần :</div>
						<div class="col-6" field='subject-name'>............................</div>
					</div>
					<div class='col-6 d-flex m-0 p-0 justify-content-end'>
						<div class="col-4">Mã học phần :</div>
						<div class="col-6" field='subject-code'>.............................</div>
					</div>
				</div>

				<div class="exam-info row m-0 p-0 d-flex mt-2 mb-4">
					<div class='col-6 d-flex'>
						<div class="col-4">MSSV :</div>
						<div class="col-6" field='student-code'>............................</div>
					</div>
					<div class='col-6 d-flex m-0 p-0 justify-content-end'>
						<div class="col-4">Họ và tên :</div>
						<div class="col-6" field='student-name'>.............................</div>
					</div>
				</div>
			</div>

			<div class="grid mb-4">
				<div class="row border m-0 p-0 d-flex" id="answer-sheet">
					<div id="answer-sheet-item-sample" class="hidden">
						<div class="border m-0 p-0
					answer-sheet-item">
							<div class="border-bottom text-center index">1</div>
							<div></div>
						</div>
					</div>
				</div>
			</div>

			<div id="data-container" class="row m-0 p-0">
				<div id="row-data-sample" style="display: none;">
					<div class="q-container">
						<div field="q-content"></div>
						<div field="q-answers" class="row d-flex m-0 p-0 answer-container"></div>
					</div>

				</div>
			</div>

		</div>
	</div>


	<jsp:include page="/WEB-INF/views/commons/lib.jsp"></jsp:include>
	<script src="<c:url value='/resources/commons/js/base.js' />"></script>
	<script
		src="<c:url value='/resources/manager/commons/js/download-exampaper.js' />"></script>
	<script
		src="<c:url value='/resources/commons/lib/jspdf/jspdf.min.js' />"></script>
	<script src="https://html2canvas.hertzen.com/dist/html2canvas.js"></script>
</body>
</html>