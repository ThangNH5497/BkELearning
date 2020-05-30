$(document).ready(function() {
	obj=new ExamManagement();
	//use small pagination for modal
	$('#modal-select-subject ul.pagination').addClass('pagination-sm');
	$('#sidebar .active').removeClass('active');
	$('#menu-item-course').addClass('active');
	init();

});
var courseId;
function init()
{	
	courseId=obj.getParam('course');
	
	handlePagination('pagination','table-data-body','row-data-container',
			'manager/api/exams/page/courses/'+courseId+'?');
	
	$(document).on('click', '.btn-add', function () {
		window.location.href = rootLocation+"teacher/ql-lop-hoc/ql-bai-thi/them-moi?course="+courseId;
	});
	
	$(document).on('click', '.btn-edit', function () {
		var examId=$(this).parents('[dataId]').attr('dataId');
		window.location.href = rootLocation+"teacher/ql-lop-hoc/ql-bai-thi/cap-nhat?examId="+examId;
	});
	
	// click table
	$(document).on('click', '#table-data-body [dataId]', function() {
		$('.btn-edit').removeClass('disabled');
		$('#table-data-body .selected').removeClass('selected');
		var id = $(this).attr("dataId");
		$(this).addClass('selected');

	});
}
class ExamManagement extends Base {
	
    constructor() {
    	super();
    }
 
}