$(document).ready(function() {

	obj=new TeacherManager();
	$('#sidebar .active').removeClass('active');
	$('#menu-item-teacher').addClass('active');

	addNewUserEvents('teacher','api/admin/teacher/add');
	
	editUserEvents('teacher','api/admin/teacher/update');
	
	deleteEvents("api/admin/teacher/delete/multiple");
	
	userDetailEvents('api/admin/teacher/id/');
	
	tableDataEvents();
	
	//lay du lieu trang va phan trang
	handlePagination($('#pagination'),'api/admin/teacher/page?');
	
	//search events
	searchEvents('api/admin/teacher/search?');
	
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
	
	//mo form edit
	$('.btn-edit').on('click', function() {

	    if ($(this).hasClass('disabled')) {
	        return false;
	    } else {
	    	 $("#modal-edit").modal('show');//show modal
	    }
	});
	
	
}

class TeacherManager extends Base {
	
    constructor() {
    	super();

    }
    
    
}