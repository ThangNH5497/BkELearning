$(document).ready(function() {

	obj=new StudentManagement();
	$('#sidebar .active').removeClass('active');
	$('#menu-item-student').addClass('active');
	//file add-user.js
	addNewUserEvents('student','admin/api/students');
	
	//file edit-user.js
	editUserEvents('student','api/students');
	
	userDetailEvents('api/students/');
	deleteEvents("admin/api/students/multiple");

	tableDataEvents();
	//lay du lieu trang va phan trang
	handlePagination('pagination','table-data-body','row-data-container','api/students/page?');
	
	//search events
	searchEvents('key-search','btn-search','api/students/search?');
	//for xs
	searchEvents('key-search-xs','btn-search-xs','api/students/search?');
	
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