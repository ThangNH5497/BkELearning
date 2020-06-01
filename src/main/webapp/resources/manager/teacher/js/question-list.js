$(document).ready(function() {

	obj=new QuestionManager();
	$('#sidebar .active').removeClass('active');
	$('#menu-item-question').addClass('active');
	$('#modal-select-subject ul.pagination').addClass('pagination-sm');
	$('#pageSubmenu').collapse('show');
	$('#question-list').addClass('text-primary');
	init();
	
	tableDataEvents();
	filterEventHandle();
	
	
	searchSubject();
	
	//edit btn click
	$(document).on('click', '.btn-edit', function () {
		var qId=$(this).parents('[dataId]').attr('dataId');
		window.location.href = rootLocation+'teacher/ql-cau-hoi/cap-nhat?id='+qId;
	});
	
});

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
	rootApiGet='manager/api/questions/page/teachers/'+teacherId+'/subjects/'+filterSubject+'/types/'+filterType+'/levels/'+filterLevel+'?'
	rootApiSearch='manager/api/questions/search/teachers/'+teacherId+'/subjects/'+filterSubject+'/types/'+filterType+'/levels/'+filterLevel+'?'
	//lay du lieu trang va phan trang
	handlePagination('pagination','table-data-body','row-data-container',rootApiGet,replaceDataView);
	//lay du lieu trang va phan trang tim kiem mon hoc cho filter
	handlePagination('pagination-subject','table-data-body-subject','row-data-container-subject','api/subjects/page?');
	deleteEvents("manager/api/questions/multiple");
	searchEvents('key-search','btn-search',rootApiSearch);
	//for xs
	searchEvents('key-search-xs','btn-search-xs',rootApiSearch);
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
class QuestionManager extends Base {
	
    constructor() {
    	super();
    }
 
}