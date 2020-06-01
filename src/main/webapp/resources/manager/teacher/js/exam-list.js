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
	searchEvents('key-search','btn-search','manager/api/exams/search/courses/'+courseId+'?',function(){
		handleLock();
	});
	handlePagination('pagination','table-data-body','row-data-container',
			'manager/api/exams/page/courses/'+courseId+'?',function(){
		handleLock();
	});
	
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
	
	 //check-all
	 $('#select-all').click(function(){
		$('#table-data-body [field="user.role"]:contains("Chung")').parents('tr[dataId]').removeClass('checked');
    });
}
function handleLock()
{
	try {
		var roles=$('#table-data-body [field="user.role"]');
		for (var i = 0; i < roles.length; i++) {
			if($(roles[i]).text()=='ROLE_TEACHER')
			{
				$(roles[i]).text('Riêng');
			}
			else if($(roles[i]).text()=='ROLE_ADMIN')
			{
				$(roles[i]).text('Chung');
				$(roles[i]).addClass('text-danger');
				var tr=$(roles[i]).parents('tr[dataId]');
				$(tr).find('[field=checkBox]').html('');
				$(tr).find('[field=control]').html('');
			}
		}
	} catch (e) {
		// TODO: handle exception
		alert(e);
	}
}
class ExamManagement extends Base {
	
    constructor() {
    	super();
    }
 
}