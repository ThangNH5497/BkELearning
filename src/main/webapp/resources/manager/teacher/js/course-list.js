$(document).ready(function() {

	obj=new CourseListManagement();
	$('#sidebar .active').removeClass('active');
	$('#menu-item-course').addClass('active');
	tableDataEvents();
	//lay du lieu trang va phan trang
	handlePagination('pagination','table-data-body','row-data-container','api/courses/page/teachers/'+userLoged.id+'?');
	//search event , file search.js
	searchEvents('api/courses/teachers/'+userLoged.id+'/search?');
	
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
	//btn-student management click
	$(document).on('click', '.btn-student-management', function () {
		var courseId=$(this).parents('[dataId]').attr('dataId');
		window.location.href = rootLocation+"teacher/ql-lop-hoc/ql-sinh-vien?courseId="+courseId;
	});
	//selected course
	$(document).on('click', '.card', function () {
		$('.card').removeClass('selected');
		$(this).addClass('selected');
	});
}
function getParametter(paramName)
{
	const queryString = window.location.search;
	const urlParams = new URLSearchParams(queryString);
	var param = urlParams.get(paramName);
	return param;
}

class CourseListManagement extends Base {
	
    constructor() {
    	super();

    }
    
    
}