$(document).ready(function() {

	obj=new StudentManagement();
	$('#sidebar .active').removeClass('active');
	$('#menu-item-student').addClass('active');
	//file add-user.js
	addNewUserEvents('student','admin/api/student/add');
	
	//file edit-user.js
	editUserEvents('student','api/student/update');
	
	userDetailEvents('api/student/id/');
	deleteEvents("admin/api/student/delete/multiple");

	tableDataEvents();
	//lay du lieu trang va phan trang
	handlePagination($('#pagination'),'api/student/page?');
	
	//search events
	searchEvents('api/student/search?');
	
});
var obj;
// them cac su kien trong table data
function tableDataEvents()
{
	//btn refresh
	$(document).on('click', '.btn-refresh', function () {
		location.reload();
	});
	//mo form edit
	$(document).on('click', '.btn-edit', function () {
		if ($(this).hasClass('disabled')) {
	        return false;
	    } else {
	    	 $("#modal-edit").modal('show');//show modal
	    }
	});
	
	
	
	
}

class StudentManagement extends Base {
	
    constructor() {
    	super();

    }
    
    
}