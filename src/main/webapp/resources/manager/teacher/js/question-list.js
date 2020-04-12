$(document).ready(function() {

	obj=new QuestionManager();
	$('#sidebar .active').removeClass('active');
	$('#menu-item-teacher').addClass('active');

	
	
	//deleteEvents("admin/api/teachers/multiple");
	
	//userDetailEvents('api/teachers/');
	
	//lay du lieu trang va phan trang
	handlePagination($('#pagination'),'teacher/api/questions/page/teachers/'+userLoged.id+'?',obj.initData);
	
	//search events
	searchEvents('teacher/api/questions/search?');
	
	tableDataEvents();
	
	var img=$('[field=content] img');
	for (var i = 0; i < img.length; i++) {
		var src=$(img[i]).attr('src');
		$(img[i]).attr('src',rootLocation+src);
	}
});

var data=[];
var obj;
// kiem tra du lieu form add-new

// them cac su kien trong table data
function tableDataEvents()
{
	//btn refresh
	$(document).on('click', '.btn-refresh', function () {
		location.reload();
	});
	//btn refresh
	$(document).on('click', '.btn-view', function () {
		var questionId=$(this).parents('[dataId]').attr('dataId');
		var question=obj.getQuestionById(questionId);
		
		$('#question-container').html(question.content);
		var img=$('#question-container img');
		for (var i = 0; i < img.length; i++) {
			var src=$(img[i]).attr('src');
			$(img[i]).attr('src',rootLocation+src);
		}
		$('#modal-view').modal('show');
	});
	//mo form edit
	$('.btn-edit').on('click', function() {

	    if ($(this).hasClass('disabled')) {
	        return false;
	    } else {
	    	 $("#modal-edit").modal('show');//show modal
	    }
	});
	
	
}

class QuestionManager extends Base {
	
    constructor() {
    	super();
    }
    
    showQuestionData()
    {
    	
    }
    
    
}