$(document).ready(function() {

	obj=new CourseManagement();
	$('#sidebar .active').removeClass('active');
	$('#menu-item-subject').addClass('active');
	tableDataEvents();
	//lay du lieu trang va phan trang
	handlePagination($('#pagination'),'api/teacher/course/page/teacher?');
	//search event , file search.js
	searchEvents('api/teacher/course/search/teacher?');
	
});

var data=[];
var obj;
var teacher={};
// kiem tra du lieu form add-new

// them cac su kien trong table data
function tableDataEvents()
{
	$(document).on('click', '.btn-refresh', function () {
		location.reload();
	});
	$(document).on('click', '#btn-search', function () {
		$('#link-back-search').removeClass('hidden');
		$('#link-current').addClass('hidden');
	});
}
function getParametter(paramName)
{
	const queryString = window.location.search;
	const urlParams = new URLSearchParams(queryString);
	var param = urlParams.get(paramName);
	return param;
}

class CourseManagement extends Base {
	
    constructor() {
    	super();

    }
    
    
}