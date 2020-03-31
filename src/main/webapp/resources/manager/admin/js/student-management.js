$(document).ready(function() {

	obj=new StudentManagement();
	$('#sidebar .active').removeClass('active');
	$('#menu-item-student').addClass('active');
	//file add-user.js
	addNewUserEvents('student','admin/student/add');
	
	//file edit-user.js
	editUserEvents('student','admin/student/update');
	
	//file delete.js
	deleteEvents("admin/student/delete/multiple");
	
	tableDataEvents();
	
	//lay du lieu trang va phan trang
	handlePagination($('#pagination'),'admin/student/page?');
	
	//search events
	searchEvents('admin/student/search?');
	
});

var data=[];
var obj;
function t(data)
{
	alert(data);}
// kiem tra du lieu form add-new

// them cac su kien trong table data
function tableDataEvents()
{
	// chi tiet user
	$(document).on('click', '#table-data-body [dataId]', function () {
		$('.btn-edit').removeClass('disabled');
		$('#table-data-body .selected').removeClass('selected');
		var id=$(this).attr("dataId");
		$(this).addClass('selected');
		//lay doi tuong user ko dong bo va xu ly voi ham initUserDetail
		obj.getDataAsync("GET",'admin/student/id/'+id,initUserDetail);
	});
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

class StudentManagement extends Base {
	
    constructor() {
    	super();

    }
    
    
}