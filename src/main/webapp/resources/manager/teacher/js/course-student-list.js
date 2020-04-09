$(document).ready(function() {

	obj=new StudentManagement();
	$('#sidebar .active').removeClass('active');
	$('#menu-item-course').addClass('active');
	//file add-user.js
	var courseId=obj.getParam('courseId');
	course=obj.getCourseById(courseId);
	$('.course-title').text(course.courseName+'-'+course.code);
	userDetailEvents('api/students/');

	tableDataEvents();
	//lay du lieu trang va phan trang
	handlePagination($('#pagination'),'api/students/page/courses/'+courseId+'?');
	
	//search events
	searchEvents('api/students/courses/'+courseId+'/search?');
	removeTeacherEvents();
	addStudentToCourse();
	
});
var obj;
var course;
// them cac su kien trong table data
function tableDataEvents()
{
	//btn refresh
	$(document).on('click', '.btn-refresh', function () {
		location.reload();
	});

	//show back link
	$(document).on('click', '#btn-search', function () {
		$('#link-back-search').removeClass('hidden');
		$('#link-current').addClass('hidden');
	});
}
function addStudentToCourse()
{
	var check=true;
	var student;
	
	$("#modal-add-student  .student-code").bind("input propertychange", function (evt) {
	    // If it's the propertychange event, make sure it's the value that changed.
	    if (window.event && event.type == "propertychange" && event.propertyName != "value")
	    {
	    	return;
	    }
	    // Clear any previously set timer before setting a fresh one
	    window.clearTimeout($(this).data("timeout"));
	    $(this).data("timeout", setTimeout(function () {
	        var studentCode=$("#modal-add-student  .student-code").val();
	        //open waiting process
	        $('#form-student .waiting-process').removeClass('hidden');
	        $('#preview-student-container').addClass('hidden');
	        //get teacher asyn
	        student=obj.ajaxCall("GET",true,'api/students/code/'+studentCode,null,function(data){
	        	$('#form-student .waiting-process').addClass('hidden');
	        	$('#preview-student-container').removeClass('hidden');
	        	if(data!=""&&data!=undefined)
	        	{
	        		$("#modal-add-student  .student-code").removeClass('border-danger');
	        		$("#student-code-error").addClass('hidden');
	        		obj.initData('preview-student-container','preview-student-row',[data]);
	        		$('#modal-add-student  .btn-submit').removeClass('disabled');
	        		student=data;
	        		check= true;
	        	}
	        	else
	        	{
	        		$("#modal-add-student  .student-code").addClass('border-danger');
	        		$("#student-code-error").removeClass('hidden');
	        		obj.initData('preview-student-container','preview-student-row',[{}]);
	        		check= false;
	        	}
	        });
	        
	    }, 1500));
	});
	// event submit form
	$('#modal-add-student  .btn-submit').click(this,function(){
		if(check==true&&student!=null&&student!={}&&student!=undefined&&student!="")
		{
			try {
				var data=obj.saveOrUpdate('PUT',false,'teacher/api/courses/'+course.id+'/students/'+student.id,null,null);
				alert(data.msg);
            	location.reload(true);	        
			} catch (e) {
				// TODO: handle exception
				alert("Lỗi : "+err);
			}
			
	        //close modal
	        $('#modal-add-student ').modal('hide');
		}
		else if(check==false)
		{
			alert("Giảng Viên Không Tồn Tại!")
		}
	});
	 //upload from file exel
	 $(document).on('click', '#modal-add-student .btn-submit-file-exel', function () {
		 //check file not empty
		if ($('#input-file-exel').get(0).files.length === 0) {
			   $('#alert-file-exel').removeClass('hidden');
		}
		else
		{
			$('#modal-add-student').modal('hide');
			$('#modal-message').modal({backdrop: 'static', keyboard: false}) ;
			var message="";
			try {				
				var formData = new FormData();	
				//them file vao data
		        formData.append('file', $('#modal-add-student #input-file-exel')[0].files[0]);		    
		        obj.saveOrUpdate("PUT",true,'teacher/api/courses/'+course.id+'/students/file',formData, showMessage);
				
			} catch (e) {
				// TODO: handle exception
				showMessage("Lỗi : " +e);
			}
		}
	 });
}
//remove student form course
function removeTeacherEvents()
{
	//set color for row checked by add class checked
	$(document).on('click', '#table-data-body input[type="checkbox"]', function () {
		 var a=$(this).parents('tr');
         if($(this).prop("checked") == true){
             $(this).parents('tr').addClass('checked');
         }
         else if($(this).prop("checked") == false){
        	 $(this).parents('tr').removeClass('checked');
         }
     });
	 //select-all
	 $('#select-all').click(function(){
		 $('#table-data-body tr[dataId] input[type="checkbox"]').prop('checked',true);
		 $('#table-data-body tr[dataId]').addClass('checked');
     });
	 //de select-all
	 $('#deselect-all').click(function(){
		 $('#table-data-body input[type="checkbox"]').prop('checked',false);
		 $('#table-data-body tr').removeClass('checked');
     });
	// btn-delete click
	$('.btn-delete').on('click', function() {

	    if ($(this).hasClass('disabled')) {
	        return false;
	    } else {
	    	
	    	//get all input checked
	    	var items=$('#table-data-body tr input[type="checkbox"]:checked');
			
	    	//no selected
	    	if(items.length<=0)
			{
				$('#modal-delete-alert .btn-delete-alert-ok').click(this,function(){
					$('#modal-delete-alert').modal('hide');
				});
				$('#modal-delete-alert .message').text("Không Có Mục Được Chọn");
				$("#modal-delete-alert").modal('show');//show modal
			}
			else
			{
				$('#modal-delete-alert .message').text("Xóa "+items.length+" Mục Đã Chọn ?");
				$("#modal-delete-alert").modal('show');//show modal
				
				//event click ok
				$('#modal-delete-alert .btn-delete-alert-ok').click(this,function(){
					//close modal
					$('#modal-delete-alert').modal('hide');
					//list ids to delete
					var ids=[];
					var rowChecked=$('#table-data-body tr.checked');
					for(var i=0;i<rowChecked.length;i++)
					{
						ids.push($(rowChecked[i]).attr('dataId'));
					}
					var data=obj.ajaxCall('DELETE',false,'teacher/api/courses/'+course.id+'/students',ids,null);
					alert(data.msg);
					location.reload(true);
				});
			}
	    }
	});
}

class StudentManagement extends Base {
	
    constructor() {
    	super();

    }
    
    
}