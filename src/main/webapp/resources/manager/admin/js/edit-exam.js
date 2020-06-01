$(document).ready(function() {
	obj=new Question();
	// use small pagination for modal
	$('#modal-select-subject ul.pagination').addClass('pagination-sm');
	$('#sidebar .active').removeClass('active');
	$('#menu-item-course').addClass('active');
	
	init();
	dateTimePicker();
	tableDataEvents();
	filterEventHandle();
	searchSubject();
	handleEvents();
	addQuestionSelectedEvents();
	saveExamEvent("admin/ql-mon-hoc/ql-bai-thi?subject="+exam.subject.id);
});
function init()
{
	var id=obj.getParam('examId');
	exam=obj.getExamById(id);
	if(exam==""||exam==null||exam==undefined)
	{
		alert('Lỗi Khi lấy Dữ Liệu . Vui Lòng Thử Lại !');
	}
	else
	{
		
		$('#input-subject').val(exam.subject.code+'-'+exam.subject.subjectName);
		$('#input-subject').attr('subjectId',exam.subject.id);
		$('#input-code').val(exam.code);
		
		$('#input-name').val(exam.name);
		$('#input-time').val(exam.time);
		$('#input-descriptor').val(exam.descriptor);
		$('#input-time-open').val(exam.timeOpen);
		$('#input-time-close').val(exam.timeClose);
		var count=0;
		for (var i = 0; i < exam.examFilters.length; i++) {
			count+=parseInt(exam.examFilters[i].number);
			switch (exam.examFilters[i].level) {
			case 0:
			{
				$('#input-question-easy').val(exam.examFilters[i].number);
				
				break;
			}
			case 1:
			{
				$('#input-question-medium').val(exam.examFilters[i].number);
				break;
			}
			case 2:
			{
				$('#input-question-hard').val(exam.examFilters[i].number);
				break;
			}
				

			default:
				break;
			}
		}
		
		$('#input-question-count').val(count);
		$(document).on('click', '.btn-select-question', function (e) {
			e.stopPropagation();
		    e.preventDefault();
			$('#modal-select-question').modal('show');
		});
		$(document).on('click', '.btn-random-question', function (e) {
			e.stopPropagation();
		    e.preventDefault();
			$('#modal-random-question').modal('show');
		});
		teacherId=userLoged.id;
		filterSubject=exam.subject.id;
		filterType='ALL';
		filterLevel='ALL'
		$('#filter-subject input').attr('val',filterSubject);
		$('#filter-type input').attr('val','ALL');
		$('#filter-level input').attr('val','ALL');
		
		$('#filter-subject input').val(exam.subject.code+'-'+exam.subject.subjectName);
		$('#filter-type input').val("Tất Cả");
		$('#filter-level input').val("Tất Cả");
		
		//modal random question
		$('#modal-random-question .subject').text(exam.subject.code+'-'+exam.subject.subjectName);
		$('#modal-random-question .user').text(userLoged.fullName);
		
		//show category filter
		showCategory(exam.subject.id);
		
		getRandomQuestion();
		
		checkboxControl();
		// init url api
		rootApiGet='manager/api/questions/page/subjects/'+filterSubject+'/types/'+filterType+'/levels/'+filterLevel+'?'
		rootApiSearch='manager/api/questions/search/subjects/'+filterSubject+'/types/'+filterType+'/levels/'+filterLevel+'?'
		// lay du lieu trang va phan trang
		handlePagination('pagination','table-data-body','row-data-container',rootApiGet,replaceDataView);
		// lay du lieu trang va phan trang tim kiem mon hoc cho filter
		handlePagination('pagination-subject','table-data-body-subject','row-data-container-subject','api/subjects/page?');
		searchEvents('key-search','btn-search',rootApiSearch);
		
		for (var i = 0; i < exam.examQuestions.length; i++) {
			addQuestionToView(exam.examQuestions[i].question,exam.examQuestions[i].grade);
		}
	}
}

