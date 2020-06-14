$(document).ready(function() {
	obj=new Question();
	// use small pagination for modal
	$('#modal-select-subject ul.pagination').addClass('pagination-sm');
	$('#sidebar .active').removeClass('active');
	$('#menu-item-exampaper').addClass('active');
	$('#submenu-exampaper').collapse('show');
	$('#exampaper-list').addClass('text-primary');
	
	init();
	tableDataEvents();
	filterEventHandle();
	searchSubject();
	handleEvents();
	addQuestionSelectedEvents();
	saveExamPaperEvent();
	shuffleQuestionEvents();
});

// init
function init()
{
	var id=obj.getParam('id');
	examPaper=obj.getExamPaperById(id);
	if(examPaper==""||examPaper==null||examPaper==undefined)
	{
		alert('Lỗi Khi lấy Dữ Liệu . Vui Lòng Thử Lại !');
	}
	else
	{
		
		$('#input-subject').val(examPaper.subject.code+'-'+examPaper.subject.subjectName);
		$('#input-subject').attr('subjectId',examPaper.subject.id);
		$('#input-code').val(examPaper.code);
		$('#input-name').val(examPaper.name);
		$('#input-time').val(examPaper.time);
		$('#input-descriptor').val(examPaper.descriptor);
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
		adminId=userLoged.id;
		filterSubject=examPaper.subject.id;
		filterType='ALL';
		filterLevel='ALL'
		$('#filter-subject input').attr('val',filterSubject);
		$('#filter-type input').attr('val','ALL');
		$('#filter-level input').attr('val','ALL');
		
		$('#filter-subject input').val(examPaper.subject.code+'-'+examPaper.subject.subjectName);
		$('#filter-type input').val("Tất Cả");
		$('#filter-level input').val("Tất Cả");
		
		//modal random question
		$('#modal-random-question .subject').text(examPaper.subject.code+'-'+examPaper.subject.subjectName);
		$('#modal-random-question .user').text(userLoged.fullName);
		
		//show category filter
		showCategory(examPaper.subject.id);
		
		getRandomQuestion();
		
		checkboxControl();
		
		rootApiGet='manager/api/questions/page/subjects/'+examPaper.subject.id+'/types/'+filterType+'/levels/'+filterLevel+'?';
		rootApiSearch='manager/api/questions/search/subjects/'+examPaper.subject.id+'/types/'+filterType+'/levels/'+filterLevel+'?';
		// lay du lieu trang va phan trang
		
		handlePagination('pagination','table-data-body','row-data-container',rootApiGet,replaceDataView);
		searchEvents('key-search','btn-search',rootApiSearch);
		
		for (var i = 0; i < examPaper.examPaperQuestions.length; i++) {
			addQuestionToView(examPaper.examPaperQuestions[i].question);
		}
	}
}


function filterData()
{
	filterType=$('#filter-type input').attr('val');
	filterLevel=$('#filter-level input').attr('val');
	
	$('#filter-type input').attr('val',filterType);
	$('#filter-level input').attr('val',filterLevel);
	
	switch (filterType) {
		case "ALL":
		{
			$('#filter-type input').val("Tất Cả");
			
			break;
		}
		case "ONE_CHOICE":
		{
			$('#filter-type input').val("Một Đáp Án");
			
			break;
		}	
		case "MULTIPLE_CHOICE":
		{
			$('#filter-type input').val("Nhiều Đáp Án");
			
			break;
		}
		case "FILL_WORD":
		{
			$('#filter-type input').val("Điền Từ");
			
			break;
		}
		default:
		{
			break;
		}
	}
	switch (filterLevel) {
		case "ALL":
		{
			$('#filter-level input').val("Tất Cả");
			
			break;
		}
		case "0":
		{
			$('#filter-level input').val("Dễ");
			
			break;
		}	
		case "1":
		{
			$('#filter-level input').val("Trung Bình");
			
			break;
		}
		case "2":
		{
			$('#filter-level input').val("Khó");
			
			break;
		}
		default:
		{
			break;
		}
	}
	
	rootApiGet='manager/api/questions/page/subjects/'+examPaper.subject.id+'/types/'+filterType+'/levels/'+filterLevel+'?';
	rootApiSearch='manager/api/questions/search/subjects/'+examPaper.subject.id+'/types/'+filterType+'/levels/'+filterLevel+'?';
	// lay du lieu trang va phan trang
	
	handlePagination('pagination','table-data-body','row-data-container',rootApiGet,replaceDataView);
	searchEvents('key-search','btn-search',rootApiSearch);
}



class Question extends Base {
	
    constructor() {
    	super();
    }  
}
