$(document).ready(function(){
	obj=new Exam();
	$('#modal-select-course ul.pagination').addClass('pagination-sm');
	$('#sidebar .active').removeClass('active');
	$('#menu-item-subject').addClass('active');
	init();
	dateTimePicker();
	submitExam();
	eventHandle();
});
var obj;
//date time picker
function dateTimePicker()
{
	 $(".form_datetime").datetimepicker({
			format : "dd/mm/yyyy - hh:ii",
			autoclose : true,
			todayBtn : true,
			pickerPosition : "bottom-middle",
		});
}

function init()
{
	var subjectId=obj.getParam('subject');
	try {
		var subject=obj.getSubjectById(subjectId);
		$('#input-subject').val(subject.code+'-'+subject.subjectName);
		$('#input-subject').attr('subjectId',subject.id);
		//lay du lieu trang va phan trang
		handlePagination('pagination','table-data-body','row-data-container','api/courses/page/subjects/'+subjectId+'?',obj.initData);
		//search event , file search.js
		searchEvents('key-search','btn-search','api/courses/subjects/'+subjectId+'/search?');
	} catch (e) {
		// TODO: handle exception
		alert('Đã Xảy Ra Lỗi . Xin Thử Lại Sau !');
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
	$(document).on('click', '#btn-submit', function () {
		if(validInputs()==true)
		{
			var exam;
			var examCourses=[];
			var courses=$('#course-list-container [dataId]');
			for (var i = 0; i < courses.length; i++) {
				examCourses.push({
					course:{
						id:$(courses[i]).attr('dataId')
					}
					
				})
			}
			
			try {
				exam={
						name:$('#input-name').val(),
						code:$('#input-code').val(),
						time:$('#input-time').val(),
						grade:$('#input-grade').val(),
						descriptor:$('#input-descriptor').val(),
						timeOpen:$("#time-open").data("datetimepicker").getDate(),
						timeClose:$("#time-close").data("datetimepicker").getDate(),
						subject:{
							id:$('#input-subject').attr('subjectId')
						},
						examCourses:examCourses
						
				}
				
				var message=obj.ajaxCall('POST',false,'manager/api/exams',exam,null);
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
		}
		
	});
	
}
function validInputs()
{
	
	var check=true;
	var inputs=$('input[required]');
	for (var i = 0; i < inputs.length; i++) {
		if($(inputs[i]).val()=="")
		{
			check=false;
			$(inputs[i]).addClass('border-danger');
		}
		else $(inputs[i]).removeClass('border-danger');
	}
	if(check==true)
	{
		var exam=obj.getExamByCode($('#input-code').val());
		if(exam!=""&&exam!=null&&exam!=undefined)
		{
			$('#input-code').addClass('border-danger');
			check=false;
			alert('Mã Bài Thi Đã Tồn Tại !');
		}
		else $('#input-code').removeClass('border-danger');
	}
	
	if(check==true)
	{
		var time=$('#input-time').val();
		if($.isNumeric(time)==false)
		{
			$('#input-time').addClass('border-danger');
			check=false;
		}
		else if(parseInt(time)<=0)
		{
			$('#input-time').addClass('border-danger');
			check=false;
		}
		else $('#input-time').removeClass('border-danger');
	}
	
	if(check==true)
	{
		var grade=$('#input-grade').val();
		if($.isNumeric(grade)==false)
		{
			$('#input-grade').addClass('border-danger');
			check=false;
		}
		else if(parseFloat(grade)<=0)
		{
			$('#input-grade').addClass('border-danger');
			check=false;
		}
		else $('#input-grade').removeClass('border-danger');
	}
	
	return check;
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