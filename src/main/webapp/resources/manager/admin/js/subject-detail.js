$(document).ready(function() {

	obj=new SubjectManagement();
	$('#sidebar .active').removeClass('active');
	$('#menu-item-subject').addClass('active');
	
	tableDataEvents();
	
	var subjectId=getParametter('id');
	subject['id']=subjectId;
	//lay du lieu trang va phan trang
	handlePagination($('#pagination'),'api/admin/course/page/subject?subjectId='+subjectId+'&');
	//search event , file search.js
	searchEvents('api/admin/course/search/subject?subjectId='+subjectId+'&');
	addNewCourseEvents();
	
	editCourseEvents('api/admin/course/update');
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
}
function getParametter(paramName)
{
	const queryString = window.location.search;
	const urlParams = new URLSearchParams(queryString);
	var param = urlParams.get(paramName);
	return param;
}
function validForm(formId,course)
{
	var checkValidInput=true;
		 var inputs=$('#'+formId+' input[required]');
		// kiem tra input null
		for(var i=0;i<inputs.length;i++)
		{
			if($(inputs[i]).val()=="") 
			{
				$(inputs[i]).addClass('border-danger');
				checkValidInput=false;
			}
			else $(inputs[i]).removeClass('border-danger');
		}
		if(checkValidInput==true)
		{
			var checkDataExis;
			// kiem tra ma code ton tai
			checkDataExis=obj.getCourseByCode($('#'+formId+' input[name="code"]').val());
			if(checkDataExis!=""&&checkDataExis.code!=course.code)
			{
				checkValidInput=false;
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
			
	return checkValidInput;
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
		        obj.saveOrUpdate(formData,"POST","api/admin/course/add");
		        location.reload(true);
			} catch (e) {
				// TODO: handle exception
				alert("Lỗi : "+err);
			}
			
		}
		
	});
	// dat lai mau border cho input khi click
	$('#modal-add-new input').click(this,function(){		
		$(this).removeClass('border-danger');
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
	// event modal edit closed, reset input of form
	$(document).on('hidden.bs.modal', '#modal-edit', function(e) {
		resetFormEdit();
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
		        obj.saveOrUpdate(formData,"PUT","api/admin/course/update");
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
	// dat lai mau border cho input khi click
	$('#modal-edit input').click(this, function() {
		$(this).removeClass('border-danger');
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
// dat lai cac gia tri ban dau cho form them moi
function resetForm() {
	$('.modal input').val("");
	$('.modal input').removeClass('border-danger');
	$('.modal .error').addClass('hidden');
}

function onDeleteEvents()
{
	$(document).on('click', '#table-data-body .btn-delete', function () {
		$('#modal-delete-alert').modal('show');
		var courseId=$(this).parents('[dataId]').attr('dataId');
		$(document).on('click', '#modal-delete-alert .btn-delete-alert-ok', function () {		
		
			$.ajax({
	        	method : "DELETE",
	            url : rootLocation+"api/admin/course/delete/"+courseId,
	            data : "",
	            dataType : "text",
				contentType : "application/json; charset=utf-8",
	            success : function(data) {
	                console.log('delete success : '+data);
	            },
	            errorr : function(err) {
	            	console.log('delete error : '+err);
	            }
	        });
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
		course = obj.getCourseById(courseId);
		if(course.teacher!=null&&course.teacher!={}&&course.teacher!=undefined)
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
	});
	// event modal edit closed, reset input of form
	$(document).on('hidden.bs.modal', '#modal-teacher', function(e) {
		resetFormEdit();
	});
	//teacher code
	$('#modal-teacher .teacher-code').on('input',function(e){
	    if($('#modal-edit .teacher-code').val()!="")
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
	        teacher=obj.getDataAsync("GET",'api/admin/teacher/code/'+teacherCode,function(data){
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
		        obj.saveOrUpdate(formData,"PUT","api/admin/course/update");
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
	// dat lai mau border cho input khi click
	$('#modal-teacher input').click(this, function() {
		$(this).removeClass('border-danger');
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
				 obj.saveOrUpdate(formData,"PUT","api/admin/course/update");
			     location.reload(true);
			} catch (e) {
				// TODO: handle exception
				alert('Có Lỗi, Thử Lại Sau!');
			}
			$('#modal-delete-alert').modal('hide');
		});
		
	});
}
class SubjectManagement extends Base {
	
    constructor() {
    	super();

    }
    
    
}