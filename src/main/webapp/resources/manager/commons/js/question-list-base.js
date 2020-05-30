var data = [];
var obj;
var teacherId;
var filterSubject;
var filterType;
var filterLevel;
var FILTER_ALL = "ALL";
var rootApiGet;
var rootApiSearch;
// init

// tìm kiếm môn học
// tìm kiếm môn học
function searchSubject() {
	// get key
	$(document).on(
			'click',
			'#btn-search-subject',
			function() {
				var key = $('#key-search-subject').val();

				url = 'api/subjects/search?q=' + key + '&';
				handlePagination('pagination-subject',
						'table-data-body-subject',
						'row-data-container-subject', url);
			});
}
function searchTeacher() {
	// get key
	$(document).on(
			'click',
			'#btn-search-teacher',
			function() {
				var key = $('#key-search-teacher').val();

				url = 'api/teachers/search?q=' + key + '&';
				handlePagination('pagination-teacher',
						'table-data-body-teacher',
						'row-data-container-teacher', url);
			});
}
// them cac su kien trong table data
function tableDataEvents() {
	// btn refresh
	$(document).on('click', '.btn-refresh', function() {
		location.reload();
	});
	// click table
	$(document).on('click', '#table-data-body [dataId]', function() {
		$('.btn-edit').removeClass('disabled');
		$('#table-data-body .selected').removeClass('selected');
		var id = $(this).attr("dataId");
		$(this).addClass('selected');

	});
	// table select subject click
	$(document).on('click', '#table-data-body-subject [dataId]', function() {
		$('#table-data-body-subject .selected').removeClass('selected');
		var id = $(this).attr("dataId");
		$(this).addClass('selected');
		$('#modal-select-subject .btn-submit').removeClass('disabled');
	});

	// table select subject click
	$(document).on('click', '#table-data-body-teacher [dataId]', function() {
		$('#table-data-body-teacher .selected').removeClass('selected');
		var id = $(this).attr("dataId");
		$(this).addClass('selected');
		$('#modal-select-teacher .btn-submit').removeClass('disabled');
	});
	// btn view
	$(document).on('click', '#table-data-body .btn-view', function() {
		var questionId = $(this).parents('[dataId]').attr('dataId');
		var question = obj.getQuestionById(questionId);
		if(question!=null&&question!=undefined&&question!="") {
			viewQuestion(question);
			$('#modal-view').modal('show');
		}
		
	});

}
function viewQuestion(question) {
	// btn view
	try {
		
		var container = $('#question-detail-container');
		var fields = $('#question-detail-container [field]');

		// reset and append row sample for answer
		var answerHtmlContainer = $('#answer-sample').prop('outerHTML');
		var answerHtml = $('#answer-sample').html();
		$('#question-detail-answer').empty();

		// init question data
		for (var i = 0; i < fields.length; i++) {
			var fieldName = $(fields[i]).attr('field');
			var value = obj.resolve(fieldName, question);
			if (fieldName == 'level') {
				switch (parseInt(value)) {
				case 0: {
					value = 'Dễ'
					break;
				}
				case 1: {
					value = 'Trung Bình'
					break;
				}
				case 2: {
					value = 'Khó'
					break;
				}

				default:
					break;
				}
				$(fields[i]).html(value);
			} else if (fieldName != 'answer') {
				$(fields[i]).html(value);
			}

			// init answer
			else {
				for (var j = 0; j < question.answers.length; j++) {
					$('#question-detail-answer').append(answerHtml);
					$($('[answerField=content]')[j]).html(
							question.answers[j].content);
					if (question.answers[j].correct == true) {
						$($('[answerField=correct]')[j]).removeClass('hidden');
					}
				}
			}
		}
		$('#question-detail-answer').append(answerHtmlContainer);

	} catch (e) {
		// TODO: handle exception

	}
}
// replace all image of question content in preview table
function replaceDataView() {
	$('#table-data-body [field=level]:contains("0")').text('Dễ');
	$('#table-data-body [field=level]:contains("1")').text('TB');
	$('#table-data-body [field=level]:contains("2")').text('Khó');
	
	$('#table-data-body [field=type]:contains("ONE_CHOICE")').text('Một Đáp ÁN');
	$('#table-data-body [field=type]:contains("MULTIPLE_CHOICE")').text('Nhiều Đáp ÁN');
	$('#table-data-body [field=type]:contains("FILL_WORD")').text('Điền Từ');
	
	$('#table-data-body [field=content] img').replaceWith(
			'<i class="ml-2 mr-2 h4 fas fa-images"></i>');
	$('#table-data-body [field=content] iframe').replaceWith(
			'<i class="ml-2 mr-2 h4 fas fa-file-video"></i>');
	$('#table-data-body [field=content] audio').replaceWith(
			'<i class="ml-2 mr-2 h4 fas fa-volume-up"></i>');
	$(
			'#table-data-body [field=content] table,#table-data-body [field=content] ul,#table-data-body [field=content] ol')
			.replaceWith('<i class="ml-2 mr-2 h4 fas fa-table"></i>');
	// truncate text
}
