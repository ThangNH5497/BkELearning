$(document).ready(function() {

	obj=new StudentManagement();
	$('#sidebar .active').removeClass('active');
	$('#menu-item-student').addClass('active');
	//file add-user.js
	addNewUserEvents('student','api/admin/student/add');
	
	//file edit-user.js
	editUserEvents('student','api/admin/student/update');
	
	userDetailEvents('api/admin/student/id/');
	deleteEvents("api/admin/student/delete/multiple");

	tableDataEvents();
	//lay du lieu trang va phan trang
	handlePagination($('#pagination'),'api/admin/student/page?');
	
	//search events
	searchEvents('api/admin/student/search?');
	
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