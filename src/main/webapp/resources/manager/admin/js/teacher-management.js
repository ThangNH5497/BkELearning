$(document).ready(function() {

	obj=new TeacherManager();
	$('#sidebar .active').removeClass('active');
	$('#menu-item-teacher').addClass('active');

	addNewUserEvents('teacher','admin/api/teachers');
	
	editUserEvents('teacher','admin/api/teachers');
	
	deleteEvents("admin/api/teachers/multiple");
	
	userDetailEvents('api/teachers/');
	
	//lay du lieu trang va phan trang
	handlePagination('pagination','table-data-body','row-data-container','api/teachers/page?');
	
	//search events
	searchEvents('api/teachers/search?');
	
	tableDataEvents();
	
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