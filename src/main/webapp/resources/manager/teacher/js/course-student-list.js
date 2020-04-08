$(document).ready(function() {

	obj=new StudentManagement();
	$('#sidebar .active').removeClass('active');
	$('#menu-item-course').addClass('active');
	//file add-user.js
	var courseId=obj.getParam('courseId');
	course=obj.getCourseById(courseId);
	$('.course-title').text(course.courseName+'-'+course.code);
	userDetailEvents('api/student/id/');

	tableDataEvents();
	//lay du lieu trang va phan trang
	handlePagination($('#pagination'),'api/student/page/course?courseId='+courseId+'&');
	
	//search events
	searchEvents('api/student/search/course?courseId='+courseId+'&');
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
	
	// event modal edit closed, reset input of form
	$(document).on('hidden.bs.modal', '#modal-add-student ', function(e) {
		resetForm();
	});
	//student code check
	$('#modal-add-student .student-code').on('input',function(e){
	    if($('#modal-add-student  .student-code').val()!="")
	    {
	    	check=false;
	    	$('#modal-add-student  .btn-submit').addClass('disabled');
	    }
	    else
	    {
	    	check=true;
	    	$('#modal-add-student  .btn-submit').removeClass('disabled');
	    }
	});
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
	        student=obj.getDataAsync("GET",'api/student/code/'+studentCode,function(data){
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
				$.ajax({
		            method: 'PUT',
		            url: rootLocation+'teacher/api/course/update/'+course.id+'/student/'+student.id,
		            async: false,
					contentType : "application/json; charset=utf-8",
		            success: function (data) {
		            	alert(data.msg);
		            	location.reload(true);
		            },
		            error: function (data) {
		            	alert("update Thất Bại. Vui Lòng Thử Lại!");
		            }
		        });
		        
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
	/*for exel file*/
	// display filename exel when selected
	$('#input-file-exel').on('change',this,function(){
       // get the file name
       var fileName = $(this).val();
       // replace the "Choose a file" label
       $(this).next('#modal-add-student .file-exel-name').html(fileName);
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
		        obj.saveOrUpdateAsync(formData,"PUT",'teacher/api/course/update/'+course.id+'/student/file', showMessage);
				
			} catch (e) {
				// TODO: handle exception
				showMessage("Lỗi : " +e);
			}
		}
	 });
	 
	 $('#alert-file-exel').on('close.bs.alert', function (event) {
		  event.preventDefault();
		  $(this).addClass('hidden');
	 });

	 // even tab select
		$('#modal-add-student a[data-toggle="tab"]').on('shown.bs.tab', function (e) {
			resetForm();
		});
	// dat lai mau border cho input khi click
	$('#modal-add-student  input').click(this, function() {
		$(this).removeClass('border-danger');
	});
}
//remove teacher phụ trách lớp
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
					$.ajax({
			        	method : "DELETE",
			            url : rootLocation+'teacher/api/course/'+course.id+'/student',
			            data : JSON.stringify(ids),
			            dataType : "json",
			            async:false,
						contentType : "application/json; charset=utf-8",
			            success : function(data) {			               
			            },
			            errorr : function(err) {
			            	alert('Xảy ra lỗi, Vui Lòng Thử Lại Sau!');
			            }
			        });
					//delete row data in table
					//$(rowChecked).remove();
					 location.reload(true);
				});
			}
	    }
	});
}
//dat lai cac gia tri ban dau cho form them moi
function resetForm()
{
	$('.modal input').val("");
	$('.modal input').removeClass('border-danger');
	$('#input-file-exel').next('#modal-add-student .file-exel-name').html("Chọn File");
	$('.modal .image-preview').attr('src',rootLocation+"resources/commons/image/user/default-user.jpg");
	$('.modal .error').addClass('hidden');
	$('.modal .image-preview').attr('src',
			rootLocation + "resources/commons/image/user/default-user.jpg");
}
class StudentManagement extends Base {
	
    constructor() {
    	super();

    }
    
    
}