$(document).ready(function(){
	obj=new Exam();
	$('#modal-select-course ul.pagination').addClass('pagination-sm');
	$('#sidebar .active').removeClass('active');
	$('#menu-item-subject').addClass('active');
	init();
	submitExam();
	eventHandle();
});
var obj;
var exam;
//date time picker


function init()
{
	var examId=obj.getParam('examId');
	try {
		exam=obj.getExamById(examId);
		for (var i = 0; i < exam.examCourses.length; i++) {
			addCourseToView(exam.examCourses[i].course);
		}
		//lay du lieu trang va phan trang
		handlePagination('pagination','table-data-body','row-data-container','api/courses/page/subjects/'+exam.subject.id+'?',obj.initData);
		//search event , file search.js
		searchEvents('key-search','btn-search','api/courses/subjects/'+exam.subject.id+'/search?');
	} catch (e) {
		// TODO: handle exception
		//alert('Đã Xảy Ra Lỗi . Xin Thử Lại Sau !');
		alert(e);
	}
}

//xu ly cac su kien trong trang
function eventHandle()
{
	$(document).on('click', '.btn-select-course', function () {
		$('#modal-select-course').modal('show');
	});
	
	//selected course
	$(document).on('click', '#modal-select-course tr', function () {
		$('#modal-select-course .selected').removeClass('selected');
		$(this).addClass('selected');
	});
	
	 //check-all
	$(document).on('click', '#modal-select-course #check-all', function() {
		$('#modal-select-course input[type=checkbox]').prop('checked',true);
		$('#modal-select-course tr').addClass('checked');
	});
	//uncheck all
	 //check-all
	$(document).on('click', '#modal-select-course #un-check-all', function() {
		$('#modal-select-course input[type=checkbox]').prop('checked',false);
		$('#modal-select-course tr').removeClass('checked');
	});
	//checkbox

	$(document).on('change', '#modal-select-course input[type=checkbox]', function() {
		var tr=$(this).parents('tr');
	
	    if($(this).prop('checked')==false) {
	      // checkbox is checked
	    	//$('#'+ulContainerId+' input[type=checkbox]').prop('checked',true);
	    	$(tr).removeClass('checked');
	    }
	    else
	    {
	    	$(tr).addClass('checked');
	    }
		
	});
	$(document).on('click', '#modal-select-course .btn-submit', function() {
		var courses=$('#modal-select-course .checked[dataId]');
		var success=0;
		for (var i = 0; i < courses.length; i++) {
			var course={
					id:$(courses[i]).attr('dataId'),
					courseName:$(courses[i]).children('[field=courseName]').text(),
					code:$(courses[i]).children('[field=code]').text()		
			}
			var checkExis=$('#course-list-container [dataId='+course.id+']');
			if(checkExis.length==0) {
				addCourseToView(course);
				success++;
			}
			else continue;
			
		}
		alert('Thêm Thành Công '+success+' / '+courses.length+' Lớp.');
	});
	
	//delete question from exam
	var deleteId;
	$(document).on('click', '.btn-delete', function() {
		deleteId=$(this).parents('[dataId]').attr('dataId');
		$('#modal-delete-alert').modal('show');
	});
	$(document).on('click', '#modal-delete-alert .btn-submit', function() {
		$('#course-list-container [dataId='+deleteId+']').remove();
		$('#modal-delete-alert').modal('hide');
	});
}
function submitExam()
{
	$(document).on('click', '#btn-submit-exam', function () {
		
		try {
			var examCourses=[];
			var courses=$('#course-list-container [dataId]');
			for (var i = 0; i < courses.length; i++) {
				examCourses.push({
					course:{
						id:$(courses[i]).attr('dataId')
					}
					
				})
			}
			
			
			exam.examCourses=examCourses;
			var message=obj.ajaxCall('PUT',false,'manager/api/exams/courses',exam,null);
			alert(message.msg)
			if(message.status==STATUS_SUCCESS)
			{
				window.location.href = rootLocation+"admin/ql-mon-hoc/ql-bai-thi?subject="+exam.subject.id;
			}
			//	var message=obj.ajaxCall('POST',false,'manager/api/exams',exam,null);
	
		} catch (e) {
			// TODO: handle exception
			alert(e);
		}
			//var message=obj.ajaxCall('POST',false,'manager/api/exams',exam,null);	
	});
	
}

function addCourseToView(course)
{
	var rowData=$('#course-item-sample').html();
	try {
		rowData='<div dataId="'+course.id+'">'+rowData+'</div>';
		$('#course-list-container').append(rowData);
		$('#course-list-container [dataId='+course.id+'] .course-code').html(course.code);
		$('#course-list-container [dataId='+course.id+'] .course-name').html(course.courseName);
		
	} catch (e) {
		// TODO: handle exception
	}
}
class Exam extends Base {
	
    constructor() {
    	super();

    }
    
}