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

p {
	margin-bottom: 8px;
}
</style>
</head>
<body>

	<div id="main-content" class="m-4">
		<div class="table-responsive">
			<div id="dataTable_wrapper"
				class="dataTables_wrapper dt-bootstrap4 m-0 p-0"
				style="width: 100%;">

				<div class="row p-0 m-0">
					<div class="col-sm-12">
						<table class="table table-bordered dataTable" id="dataTable"
							cellspacing="0" role="grid" aria-describedby="dataTable_info">
							<thead>
								<tr role="row">
									<th class="sorting_asc font-weight-bold" tabindex="0"
										aria-controls="dataTable" rowspan="1" colspan="1"
										aria-sort="ascending"
										aria-label="Name: activate to sort column descending">STT</th>
									<th class="sorting font-weight-bold" tabindex="0"
										aria-controls="dataTable" rowspan="1" colspan="1"
										aria-label="Position: activate to sort column ascending">Mã
										Sinh Viên</th>
									<th class="sorting font-weight-bold" tabindex="0"
										aria-controls="dataTable" rowspan="1" colspan="1"
										aria-label="Office: activate to sort column ascending">Họ
										Và Tên</th>
									<th class="sorting font-weight-bold" tabindex="0"
										aria-controls="dataTable" rowspan="1" colspan="1"
										aria-label="Office: activate to sort column ascending">Kết
										Quả</th>

								</tr>
							</thead>
							<tfoot>
								<tr>
									<th rowspan="1" colspan="1" class="font-weight-bold">STT</th>
									<th rowspan="1" colspan="1" class="font-weight-bold">Mã
										Sinh Viên</th>
									<th rowspan="1" colspan="1" class="font-weight-bold">Họ Và
										Tên</th>
									<th rowspan="1" colspan="1" class="font-weight-bold">Kết
										Quả</th>

								</tr>
							</tfoot>
							<tbody id='table-data-body'>
								<tr role="row" class="odd hidden" id='row-data-container'>
									<td class="sorting_1" field="index"></td>
									<td field="studentCode"></td>
									<td field="studentName"></td>
									<td field="studentGrade"></td>

								</tr>

							</tbody>
						</table>
					</div>
				</div>

			</div>
		</div>
	</div>


	<jsp:include page="/WEB-INF/views/commons/lib.jsp"></jsp:include>
	<script src="<c:url value='/resources/commons/js/base.js' />"></script>
	<script
		src="<c:url value='/resources/manager/teacher/js/download-exam-result-pdf.js' />"></script>
	<script
		src="<c:url value='/resources/commons/lib/jspdf/jspdf.min.js' />"></script>
	<script src="https://html2canvas.hertzen.com/dist/html2canvas.js"></script>
</body>
</html>