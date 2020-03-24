$(document).ready(function() {

	obj=new TeacherManager();
	//file add-user.js
	addNewUserEvents('teacher','teacher/save');
	
	//file edit-user.js
	editUserEvents('teacher','teacher/update');
	
	//file delete.js
	deleteEvents("teacher/delete/multiple");
	
	tableDataEvents();
	//lay tong so doi tuong teacher trong database, thuc hien phan trang
	var count=obj.getCountTeacher();
	handlePagination($('#pagination'),count,'teacher/limit?');
	
	//search events
	searchEvents('teacher/search');
	
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
		obj.getDataAsync("GET",'teacher/id/'+id,initUserDetail);
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