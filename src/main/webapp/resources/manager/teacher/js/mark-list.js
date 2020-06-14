$(document).ready(function() {

	obj=new Mark();
	$('#sidebar .active').removeClass('active');
	$('#menu-item-course').addClass('active');
	var examId=obj.getParam('examId');
	var courseId=obj.getParam('courseId');
	var rootApiGet='manager/api/studentexams/page/waitresult/exams/'+examId+'/courses/'+courseId+'?';
	handlePagination('pagination','table-data-body','row-data-container',rootApiGet,null);
	$('#back-link').attr('href',rootLocation+'teacher/ql-lop-hoc/ql-bai-thi?course='+courseId);

	//view mark click
	$(document).on('click', '.btn-mark', function () {
		var seId=$(this).parents('[dataId]').attr('dataId');
		window.location.href = rootLocation+'teacher/ql-lop-hoc/ql-bai-thi/cham-diem?id='+seId+'&examId='+examId+'&courseId='+courseId;
	});

});


class Mark extends Base {
	
    constructor() {
    	super();
    }
 
}