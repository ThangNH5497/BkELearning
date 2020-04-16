$(document).ready(function() {

	obj=new CourseListManagement();
	$('#sidebar .active').removeClass('active');
	$('#menu-item-subject').addClass('active');
	
	tableDataEvents();
	
	var subjectId=obj.getParam('id');
	subject['id']=subjectId;
	//lay du lieu trang va phan trang
	handlePagination('pagination','table-data-body','row-data-container','api/courses/page/subjects/'+subjectId+'?',obj.initData);
	//search event , file search.js
	searchEvents('api/courses/subjects/'+subjectId+'/search?');
	addNewCourseEvents();	
	editCourseEvents('admin/api/courses');
	teacherManagementEvents();
	onDeleteEvents();
	removeTeacherEvents();
	
});

var data=[];
var obj;
var subject={};
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
	
	$(document).on('click', '.card', function () {
		$('.card').removeClass('selected');
		$(this).addClass('selected');
	});
}

function validForm(formId,course)
{
	var check=obj.validInputs(formId);
		 
		if(check==true)
		{
			var checkDataExis;
			// kiem tra ma code ton tai
			checkDataExis=obj.getCourseByCode($('#'+formId+' input[name="code"]').val());
			if(checkDataExis!=""&&checkDataExis.code!=course.code)
			{
				check=false;
				//doi mau input
				$('#'+formId+' input[name="code"]').addClass('border-danger');
				//hien canh bao
				$('#'+formId+' label[name="code-error"]').removeClass('hidden');
			}
			else 
			{
				$('#'+formId+' label[name="code-error"]').addClass('hidden');
				$('#'+formId+' input[name="code"]').removeClass('border-danger');
			}
			
		}
			
	return check;
}
function addNewCourseEvents()
{
	// event submit form
	$('#modal-add-new .btn-submit').click(this,function(){
		if(validForm('form-add-new',{code:""})==true)
		{
			try {
				var jsonObject=obj.formToJson([$('#form-add-new')]);
				jsonObject['subject']=subject;
				var formData = new FormData();
		        //them doi tuong course json vao form data
		        formData.append("course", new Blob([JSON.stringify(jsonObject)], {
		            type: "application/json"
		        }));
		        var data=obj.saveOrUpdate("POST",false,"admin/api/courses",formData,null);
		        alert(data.msg);
		        location.reload(true);
			} catch (e) {
				// TODO: handle exception
				alert("Lỗi : "+err);
			}
			
		}
		
	});
}
function editCourseEvents()
{
	var course;
	var check=true;
	var teacher;
	$(document).on('click', '#table-data-body .btn-edit', function () {
		var courseId=$(this).parents('[dataId]').attr('dataId');
		course = initFormEdit(courseId);
	});
	// event submit form
	$('#modal-edit .btn-submit').click(this,function(){
		if(validForm('form-edit',course)==true&&check==true)
		{
			try {
				var formData = new FormData();
				// them doi tuong json vao form data
				var inputs = $('#form-edit input[name]');
				for (var i = 0; i < inputs.length; i++) 
				{
					var name = $(inputs[i]).attr('name');
					course[name] = $(inputs[i]).val();
				}
		        //them doi tuong  json vao form data
		        formData.append("course", new Blob([JSON.stringify(course)], {
		            type: "application/json"
		        }));
		        var data=obj.saveOrUpdate("PUT",false,"admin/api/courses/"+course.id,formData,null);
		        alert(data.msg);
		        location.reload(true);
			} catch (e) {
				// TODO: handle exception
				alert("Lỗi : "+err);
			}
			
	        //close modal
	        $('#modal-edit').modal('hide');
		}
		else if(check==false)
		{
			alert("Giảng Viên Không Tồn Tại!")
		}
	
	});
}
//khoi tao cac gia tri co san cho form
function initFormEdit(courseId) {
	var course;
	try {
		// lay doi tuong dang chon
		
		course = obj.getCourseById(courseId);
		// init input
		var inputs = $('#form-edit input[name]');
		for (var i = 0; i < inputs.length; i++) {
			var name = $(inputs[i]).attr('name');
			$(inputs[i]).val(course[name]);
		}
		
	} catch (err) {

	}
	return course;
}
function onDeleteEvents()
{
	$(document).on('click', '#table-data-body .btn-delete', function () {
		$('#modal-delete-alert').modal('show');
		var courseId=$(this).parents('[dataId]').attr('dataId');
		$(document).on('click', '#modal-delete-alert .btn-delete-alert-ok', function () {		
		
			var data=obj.ajaxCall('DELETE',false,'admin/api/courses/'+courseId,null,null);
			alert(data.msg);
			location.reload(true);
		});
		
	});
}
//quản lý giảng viên phụ trách lớp
function teacherManagementEvents()
{
	var course;
	var check=true;
	var teacher;
	$(document).on('click', '#table-data-body .btn-add-teacher', function () {
		var courseId=$(this).parents('[dataId]').attr('dataId');
		try {
			course = obj.getCourseById(courseId);
			if(course.teacher!=null&&course.teacher!={}&&course.teacher!=undefined&&course.teacher!="")
			{
				//init input
				$('#modal-teacher input.teacher-code').val(course.teacher.code);
				//init detail infomation
				obj.initData('preview-teacher-container','preview-teacher-row',[course.teacher]);
				teacherCode=course.teacher.code;
			}
			else
			{
				//init input
				$('#modal-teacher input.teacher-code').val("");
				//init detail infomation
				obj.initData('preview-teacher-container','preview-teacher-row',[{}]);
			}
		} catch (e) {
			// TODO: handle exception
		}
		
	});
	//teacher code
	$('#modal-teacher .teacher-code').on('input',function(e){
	    if($('#modal-teacher .teacher-code').val()!="")
	    {
	    	check=false;
	    	$('#modal-teacher .btn-submit').addClass('disabled');
	    }
	    else
	    {
	    	check=true;
	    	$('#modal-teacher .btn-submit').removeClass('disabled');
	    }
	});
	$("#modal-teacher .teacher-code").bind("input propertychange", function (evt) {
	    // If it's the propertychange event, make sure it's the value that changed.
	    if (window.event && event.type == "propertychange" && event.propertyName != "value")
	    {
	    	return;
	    }
	    // Clear any previously set timer before setting a fresh one
	    window.clearTimeout($(this).data("timeout"));
	    $(this).data("timeout", setTimeout(function () {
	        var teacherCode=$("#modal-teacher .teacher-code").val();
	        //open waiting process
	        $('#form-teacher .waiting-process').removeClass('hidden');
	        $('#preview-teacher-container').addClass('hidden');
	        //get teacher asyn
	        teacher=obj.ajaxCall("GET",true,'api/teachers/code/'+teacherCode,null,function(data){
	        	$('#form-teacher .waiting-process').addClass('hidden');
	        	$('#preview-teacher-container').removeClass('hidden');
	        	if(data!=""&&data!=undefined)
	        	{
	        		$("#modal-teacher .teacher-code").removeClass('border-danger');
	        		$("#teacher-code-error").addClass('hidden');
	        		obj.initData('preview-teacher-container','preview-teacher-row',[data]);
	        		$('#modal-teacher .btn-submit').removeClass('disabled');
	        		teacher=data;
	        		check= true;
	        	}
	        	else
	        	{
	        		$("#modal-teacher .teacher-code").addClass('border-danger');
	        		$("#teacher-code-error").removeClass('hidden');
	        		obj.initData('preview-teacher-container','preview-teacher-row',[{}]);
	        		check= false;
	        	}
	        });
	        
	    }, 1500));
	});
	// event submit form
	$('#modal-teacher .btn-submit').click(this,function(){
		if(check==true&&teacher!=null&&teacher!={}&&teacher!=undefined&&teacher!="")
		{
			try {
				var formData = new FormData();
				// them doi tuong json vao form data
				course['teacher']=teacher;
		        //them doi tuong  json vao form data
		        formData.append("course", new Blob([JSON.stringify(course)], {
		            type: "application/json"
		        }));
		        var data=obj.saveOrUpdate("PUT",false,"admin/api/courses/"+course.id,formData,null);
		        alert(data.msg)
		        location.reload(true);
			} catch (e) {
				// TODO: handle exception
				alert("Lỗi : "+err);
			}
			
	        //close modal
	        $('#modal-teacher').modal('hide');
		}
		else if(check==false)
		{
			alert("Giảng Viên Không Tồn Tại!")
		}
	
	});
}
//remove teacher phụ trách lớp
function removeTeacherEvents()
{
	$(document).on('click', '#table-data-body .btn-remove-teacher', function () {
		$('#modal-delete-alert').modal('show');
		var courseId=$(this).parents('[dataId]').attr('dataId');
		$(document).on('click', '#modal-delete-alert .btn-delete-alert-ok', function () {		
			$('#modal-delete-alert').modal('hide');
			try {
				var course=obj.getCourseById(courseId);
				var formData = new FormData();
				course['teacher']=null;
		        //them doi tuong  json vao form data
		        formData.append("course", new Blob([JSON.stringify(course)], {
		            type: "application/json"
		        }));
				 obj.saveOrUpdate("PUT",false,"admin/api/courses/"+course.id,formData,null);
			     location.reload(true);
			} catch (e) {
				// TODO: handle exception
				alert('Có Lỗi, Thử Lại Sau!');
			}
			$('#modal-delete-alert').modal('hide');
		});
		
	});
}
class CourseListManagement extends Base {
	
    constructor() {
    	super();

    }
    
    
}