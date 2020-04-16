$(document).ready(function() {

	obj=new QuestionManager();
	$('#sidebar .active').removeClass('active');
	$('#menu-item-question').addClass('active');
	$('#modal-select-subject ul.pagination').addClass('pagination-sm');
	init();
	
	tableDataEvents();
	filterEventHandle();
	searchSubject();
});

var data=[];
var obj;
var teacherId;
var filterSubject;
var filterType;
var filterLevel;
var FILTER_ALL="ALL";
var rootApiGet;
var rootAptSearch;
//init 
function init()
{
	teacherId=userLoged.id;
	filterSubject=obj.getParam('subject');
	filterType=obj.getParam('type');
	filterLevel=obj.getParam('level');
	$('#filter-subject input').attr('val',filterSubject);
	$('#filter-type input').attr('val',filterType);
	$('#filter-level input').attr('val',filterLevel);
	switch (filterSubject) {
	case "ALL":
	{
		$('#filter-subject input').val("Tất Cả");
		
		break;
	}		
	default:
	{
		var sub=obj.getSubjectById(filterSubject);
		$('#filter-subject input').val(sub.code+'-'+sub.subjectName);
		break;
	}
	}
	
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
	
	//init url api
	rootApiGet='teacher/api/questions/page/teachers/'+teacherId+'/subjects/'+filterSubject+'/types/'+filterType+'/levels/'+filterLevel+'?'
	rootApiSearch='teacher/api/questions/search/teachers/'+teacherId+'/subjects/'+filterSubject+'/types/'+filterType+'/levels/'+filterLevel+'?'
	//lay du lieu trang va phan trang
	handlePagination('pagination','table-data-body','row-data-container',rootApiGet,replaceImg);
	searchEvents(rootApiSearch);
	//lay du lieu trang va phan trang tim kiem mon hoc cho filter
	handlePagination('pagination-subject','table-data-body-subject','row-data-container-subject','api/subjects/page?');
	deleteEvents("teacher/api/questions/multiple");
}
//tìm kiếm môn học
function searchSubject()
{
	//get key
	$(document).on('click', '#btn-search-subject', function () {
		var key=$('#key-search-subject').val();

		url='api/subjects/search?q='+key+'&';
		handlePagination('pagination-subject','table-data-body-subject','row-data-container-subject',url);
	});
}
// them cac su kien trong table data
function tableDataEvents()
{
	//btn refresh
	$(document).on('click', '.btn-refresh', function () {
		location.reload();
	});
	//click table
	$(document).on('click', '#table-data-body [dataId]', function () {
		$('.btn-edit').removeClass('disabled');
		$('#table-data-body .selected').removeClass('selected');
		var id=$(this).attr("dataId");
		$(this).addClass('selected');
		
	});
	//table select subject click
	$(document).on('click', '#table-data-body-subject [dataId]', function () {
		$('#table-data-body-subject .selected').removeClass('selected');
		var id=$(this).attr("dataId");
		$(this).addClass('selected');
		$('#modal-select-subject .btn-submit').removeClass('disabled');
	});
	//btn view
	$(document).on('click', '.btn-view', function () {
		try {
			var questionId=$(this).parents('[dataId]').attr('dataId');
			var question=obj.getQuestionById(questionId);			
			var container=$('#question-detail-container');
			var fields=$('#question-detail-container [field]');
			
			//reset and append row sample for answer
			var answerHtmlContainer=$('#answer-sample').prop('outerHTML');	
			var answerHtml=$('#answer-sample').html();
			$('#question-detail-answer').empty();
			
			
			//init question data
			for (var i = 0; i < fields.length; i++) {
				var fieldName=$(fields[i]).attr('field');
				var value=obj.resolve(fieldName,question);
				if(fieldName=='level')
				{
					switch (parseInt(value)) {
					case 0:
					{
						value='Dễ'
						break;
					}
					case 1:
					{
						value='Trung Bình'
						break;
					}
					case 2:
					{
						value='Khó'
						break;
					}	

					default:
						break;
					}
					$(fields[i]).html(value);
				}
				else if(fieldName!='answer')
				{
					$(fields[i]).html(value);
				}
				
				//init answer
				else 
				{
					for (var j = 0; j < question.answers.length; j++) {
						$('#question-detail-answer').append(answerHtml);
						$($('[answerField=content]')[j]).html(question.answers[j].content);
						if(question.answers[j].correct==true)
						{
							$($('[answerField=correct]')[j]).removeClass('hidden');
						}
					}
				}
			}
			$('#question-detail-answer').append(answerHtmlContainer);
			$('#modal-view').modal('show');
			
		
		} catch (e) {
			// TODO: handle exception
			
		}	
	});
	//edit btn click
	$(document).on('click', '.btn-edit', function () {
		var qId=$(this).parents('[dataId]').attr('dataId');
		window.location.href = rootLocation+'teacher/ql-cau-hoi/cap-nhat?id='+qId;
	});
	
}

//xử lý các sự kiện chọn bộ lọc
function filterEventHandle()
{
	//stop a link of filter
	$(document).on('click', '#filter a.dropdown-item', function (e) {
		e.preventDefault();
		event.stopPropagation();
	});
	//filter subject
	$(document).on('click', '#filter-subject a', function (e) {
		var value=$(this).attr('value');
		if(value=="ALL")
		{
			$('#filter-subject input').attr('val','ALL');
			filterData();
		}
		else
		{
			$('#modal-select-subject').modal('show');
		}
		
		
	});
	//filter type,level
	$(document).on('click', '#filter-type a,#filter-level a', function (e) {
		var value=$(this).attr('value');
		var input=$(this).parents('.filter-item').find('input');
		$(this).parents('.filter-item').find('input').attr('val',value);
		
		filterData();
	});
	
	//select subject
	
	$(document).on('click', '#modal-select-subject .btn-submit', function (e) {
		if(!($(this).hasClass( "disabled" )))
		{
			var subject=$('#modal-select-subject .selected').attr('dataId');
			$('#filter-subject input').attr('val',subject);
			filterData();
		}
	});
}
function filterData()
{
	var subject=$('#filter-subject input').attr('val');
	if(subject==""||subject==null||subject==undefined) subject='ALL';
	var type=$('#filter-type input').attr('val');
	var level=$('#filter-level input').attr('val');
	window.location.href = rootLocation+'teacher/ql-cau-hoi?subject='+subject+'&level='+level+'&type='+type;
}
//replace all image of question content in preview table
function replaceImg()
{
	$('#table-data-body [field=content] img').replaceWith('<i class="ml-2 mr-2 h4 fas fa-images"></i>');
	$('#table-data-body [field=content] iframe').replaceWith('<i class="ml-2 mr-2 h4 fas fa-file-video"></i>');
	$('#table-data-body [field=content] audio').replaceWith('<i class="ml-2 mr-2 h4 fas fa-volume-up"></i>');
	$('#table-data-body [field=content] table,#table-data-body [field=content] ul,#table-data-body [field=content] ol').replaceWith('<i class="ml-2 mr-2 h4 fas fa-table"></i>');
	//truncate text
}
class QuestionManager extends Base {
	
    constructor() {
    	super();
    }
    
    
    
    
}