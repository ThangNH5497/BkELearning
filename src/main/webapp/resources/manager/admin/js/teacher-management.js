$(document).ready(function() {

	obj=new TeacherManager();
	$('#sidebar .active').removeClass('active');
	$('#menu-item-teacher').addClass('active');

	addNewUserEvents('teacher','admin/api/teacher/add');
	
	editUserEvents('teacher','admin/api/teacher/update');
	
	deleteEvents("admin/api/teacher/delete/multiple");
	
	userDetailEvents('admin/api/teacher/id/');
	
	tableDataEvents();
	
	//lay du lieu trang va phan trang
	handlePagination($('#pagination'),'admin/api/teacher/page?');
	
	//search events
	searchEvents('admin/api/teacher/search?');
	
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