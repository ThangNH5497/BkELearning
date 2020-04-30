$(document).ready(function() {

	obj=new QuestionManager();
	$('#sidebar .active').removeClass('active');
	$('#menu-item-question').addClass('active');
	$('#modal-select-subject ul.pagination').addClass('pagination-sm');
	$('#pageSubmenu').collapse('show');
	$('#question-bank-teacher-menu span').addClass('text-primary');
	
	init();
	
	tableDataEvents();
	filterEventHandle();
	searchEvents('key-search','btn-search',rootApiSearch);
	//for xs
	searchEvents('key-search-xs','btn-search-xs',rootApiSearch);
	
	searchSubject();
	
	searchTeacher();
	
	deleteEvents("manager/api/questions/multiple");
	
	copyQuestion();
});
var filterTeacher;
//init 
function init()
{
	filterTeacher=obj.getParam('teacher');
	filterSubject=obj.getParam('subject');
	filterType=obj.getParam('type');
	filterLevel=obj.getParam('level');
	$('#filter-type input').attr('val',filterType);
	$('#filter-level input').attr('val',filterLevel);
	$('#filter-subject input').attr('val',filterSubject);
	$('#filter-teacher input').attr('val',filterTeacher);
	
	switch (filterTeacher) {
		case "ALL":
		{
			$('#filter-teacher input').val("Tất Cả");
			
			break;
		}		
		default:
		{
			try {
				var teacher=obj.getTeacherById(filterTeacher);
				$('#filter-teacher input').val(teacher.code+'-'+teacher.fullName);
				break;
			} catch (e) {
				// TODO: handle exception
			}
			
		}
	}
	
	switch (filterSubject) {
	case "ALL":
	{
		$('#filter-subject input').val("Tất Cả");
		
		break;
	}		
	default:
	{
		try {
			var sub=obj.getSubjectById(filterSubject);
			$('#filter-subject input').val(sub.code+'-'+sub.subjectName);
			break;
		} catch (e) {
			// TODO: handle exception
		}
		
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
	rootApiGet='manager/api/questions/page/teachers/'+filterTeacher+'/subjects/'+filterSubject+'/types/'+filterType+'/levels/'+filterLevel+'?'
	rootApiSearch='manager/api/questions/search/teachers/'+filterTeacher+'/subjects/'+filterSubject+'/types/'+filterType+'/levels/'+filterLevel+'?'
	//lay du lieu trang va phan trang
	handlePagination('pagination','table-data-body','row-data-container',rootApiGet,replaceImg);
	//lay du lieu trang va phan trang tim kiem mon hoc cho filter
	handlePagination('pagination-subject','table-data-body-subject','row-data-container-subject','api/subjects/page?');
	
	handlePagination('pagination-teacher','table-data-body-teacher','row-data-container-teacher','api/teachers/page?');
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
	//filter teacher
	$(document).on('click', '#filter-teacher a', function (e) {
		var value=$(this).attr('value');
		if(value=="ALL")
		{
			$('#filter-teacher input').attr('val','ALL');
			filterData();
		}
		else
		{
			$('#modal-select-teacher').modal('show');
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
	
	$(document).on('click', '#modal-select-teacher .btn-submit', function (e) {
		if(!($(this).hasClass( "disabled" )))
		{
			var teacher=$('#modal-select-teacher .selected').attr('dataId');
			$('#filter-teacher input').attr('val',teacher);
			filterData();
		}
	});
}
function filterData()
{
	var subject=$('#filter-subject input').attr('val');
	if(subject==""||subject==null||subject==undefined) subject='ALL';
	
	var teacher=$('#filter-teacher input').attr('val');
	if(teacher==""||teacher==null||teacher==undefined) teacher='ALL';
	
	var type=$('#filter-type input').attr('val');
	var level=$('#filter-level input').attr('val');
	window.location.href = rootLocation+'admin/ql-cau-hoi/kho-giang-vien?teacher='+teacher+'&subject='+subject+'&level='+level+'&type='+type;
}
function copyQuestion()
{
	$(document).on('click', '.btn-copy', function (e) {
		//get all input checked
		var rowChecked=$('#table-data-body tr.checked');
		
    	//no selected
    	if(rowChecked.length<=0)
		{
			$('#modal-alert .btn--alert-ok').click(this,function(){
				$('#modal-alert').modal('hide');
			});
			$('#modal-alert .message').text("Không Có Mục Được Chọn");
			$("#modal-alert").modal('show');//show modal
		}
		else
		{
			$('#modal-alert .message').text("Sao Chép "+rowChecked.length+" Câu Hỏi Sang Kho Chung ?");
			$("#modal-alert").modal('show');//show modal
			
			//event click ok
			$('#modal-alert .btn-alert-ok').click(this,function(){
				//close modal
				$('#modal-alert').modal('hide');
				//list ids to delete
				var ids=[];
				for(var i=0;i<rowChecked.length;i++)
				{
					ids.push($(rowChecked[i]).attr('dataId'));
				}
				var message=obj.ajaxCall('POST',false,'admin/api/questions/copy',ids,null);
				if(message!="")
				{
					alert(message.msg);
					location.reload(true);
				}
				else alert("Đã Xảy Ra Lỗi !");
			});
		}
		
	});
}
class QuestionManager extends Base {
	
    constructor() {
    	super();
    } 
    
}