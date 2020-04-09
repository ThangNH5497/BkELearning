/* phần thêm mới user */
//kiem tra cac input bat buoc khi them moi
function validInputs()
{
	var check=obj.validInputs('form-add-step-one');
		
		if(check==true)
		{
			var checkDataExis=obj.getUserByCode($('#add-one-tab input[name="code"]').val());
			
			if(checkDataExis!="")
			{
				check=false;
				//doi mau input
				$('#add-one-tab input[name="code"]').addClass('border-danger');
				//hien canh bao
				$('#add-one-tab label[name="code-error"]').removeClass('hidden');
			}
			else 
			{
				$('#add-one-tab label[name="code-error"]').addClass('hidden');
				$('#add-one-tab input[name="code"]').removeClass('border-danger');
			}
			// kiem tra username ton tai
			checkDataExis=obj.getUserByUsername($('#add-one-tab input[name="username"]').val());
			if(checkDataExis!="")
			{
				check=false;
				//hien canh bao
				$('#add-one-tab label[name="username-error"]').removeClass('hidden');
				$('#add-one-tab input[name="username"]').addClass('border-danger');
			}
			else 
			{
				//xoa canh bao
				$('#add-one-tab label[name="username-error"]').addClass('hidden');
				$('#add-one-tab input[name="username"]').removeClass('border-danger');
			}
			//kiem tra mat khau
			if($('#add-one-tab input[name="password"]').val().length<6)
			{
				check=false;
				//hien canh bao
				$('#add-one-tab label[name="password-error"]').removeClass('hidden');
				$('#add-one-tab input[name="password"]').addClass('border-danger');
			}
			else
			{
				//xoa canh bao
				$('#add-one-tab label[name="password-error"]').addClass('hidden');
				$('#add-one-tab input[name="password"]').removeClass('border-danger');
			}
			// kiem tra password confirm
			if($('#add-one-tab .confirm-password').val()!==$('#add-one-tab input[name="password"]').val())
			{
				
				check=false;
				//hien canh bao
				$('#add-one-tab label[name="confirm-password-error"]').removeClass('hidden');
				$('#add-one-tab .confirm-password').addClass('border-danger');
			}
			else
			{
				//xoa canh bao
				$('#add-one-tab label[name="confirm-password-error"]').addClass('hidden');
				$('#add-one-tab .confirm-password').removeClass('border-danger');
			}
		}
	return check;
}
//xu ly cac su kien trong them moi user, paramName- ten parametter doi tuong json
//urlApi-dia chi url cua api
function addNewUserEvents(paramName,urlApi)
{
	// btn-next step add user
	$('#modal-add-new .btn-next').click(this,function(){
		try {
			if(validInputs())
			{
				$('#form-container-one').addClass('hidden');
				$('#form-container-two').removeClass('hidden');
			}
			
		} catch (e) {
			// TODO: handle exception
			$('#modal-add-new').modal('hide');
		}
		
	});
	// btn-back
	$('#modal-add-new .btn-back').click(this,function(){
		$('#form-container-one').removeClass('hidden');
		$('#form-container-two').addClass('hidden');
	});
	// event submit form
	$('#modal-add-new .btn-submit').click(this,function(){		
		try {
			var form1=$('#form-add-step-one');
			var form2=$('#form-add-step-two');
			var userJson=obj.formToJson([form1,form2]);
			var formData = new FormData();
			
			//them file vao data
	        formData.append('file', $('#modal-add-new .form-img input[type=file]')[0].files[0]);
	        //them doi tuong user json vao form data
	        formData.append(paramName, new Blob([JSON.stringify(userJson)], {
	            type: "application/json"
	        }));
	       
	        var msg=obj.saveOrUpdate("POST",false,urlApi,formData,null);
	        alert(msg.msg);
	        location.reload(true);
		} catch (e) {
			// TODO: handle exception
			alert("Lỗi : "+err);
		}
	
	
	});	 
	 //upload from file exel
	 $(document).on('click', '#modal-add-new .btn-submit-file-exel', function () {
		 //check file not empty
		if ($('#input-file-exel').get(0).files.length === 0) {
			   $('#alert-file-exel').removeClass('hidden');
		}
		else
		{
			$('#modal-add-new').modal('hide');
			$('#modal-message').modal({backdrop: 'static', keyboard: false}) ;
			try {				
				var formData = new FormData();	
				//them file vao data
		        formData.append('file', $('#modal-add-new #input-file-exel')[0].files[0]);		    
		        obj.saveOrUpdate("POST",true,urlApi+'/file',formData,showMessage);		
			} catch (e) {
				// TODO: handle exception
				showMessage({msg:"Lỗi . Xin Vui Lòng Thử Lại!"});
			}
		}
	 });	
		
}
function showMessage(data)
{
	$('#modal-message .message').text(data.msg);
	$('#modal-message img').addClass('hidden');
	$('#modal-message .message').removeClass('hidden');
	$('#modal-message .btn-ok').removeClass('hidden');
	$('#modal-message .btn-ok').click(this,function(){
		//refresh page
		location.reload(true);
	});
}
/*hết phần thêm mới user*/


/* Phần chỉnh sửa user*/
//xu ly cac su kien trong edit user, paramName- ten parametter doi tuong json
//urlApi-dia chi url cua api loai doi tuong (teacher, student)
function editUserEvents(paramName, urlApi) {
	var user;
	// event modal open
	$(document).on('show.bs.modal', '#modal-edit', function(e) {
		user = initFormEdit(paramName);
	});
	// event submit form
	$('#modal-edit .btn-submit').click(this,function() {
		var formData = new FormData();
		try {
			// them file vao data
			formData.append('file',$('#modal-edit .form-img input[type=file]')[0].files[0]);
			
			// them doi tuong json vao form data
			var inputs = $('#form-edit input[name]');
			for (var i = 0; i < inputs.length; i++) 
			{
				var name = $(inputs[i]).attr('name');
				user[name] = $(inputs[i]).val();
			}
			formData.append(paramName, new Blob([ JSON.stringify(user) ], {
								type : "application/json"
				}));
			//update
			 var msg=obj.saveOrUpdate("PUT",false,urlApi+'/'+user.id,formData,null);
			 alert(msg.msg);
			 location.reload(true);
		}
		catch (err) {
			alert("Đã Xảy Ra Lỗi : "+err);
		}
	});
}
//khoi tao cac gia tri co san cho form
function initFormEdit(paramName) {
	var user;
	try {
		// lay doi tuong dang chon		
		var userId = $('#table-data-body tr.selected').attr('dataId');
		if (paramName == 'teacher') {
			user = obj.getTeacherById(userId);
		} else if (paramName == 'student') {
			user = obj.getStudentById(userId);
		}
		// init avatar
		$('#modal-edit .image-preview').attr('src', rootLocation + user.image);
		// init input
		var inputs = $('#form-edit input[name]');
		for (var i = 0; i < inputs.length; i++) {
			var name = $(inputs[i]).attr('name');
			$(inputs[i]).val(user[name]);
		}
		
	} catch (err) {
	}
	return user;
}
/* Hết Phần chỉnh sửa user*/

/*show detail user*/
function userDetailEvents(urlApi) {
	
	// chi tiet user
	$(document).on('click', '#table-data-body [dataId]', function () {
		$('.btn-edit').removeClass('disabled');
		$('#table-data-body .selected').removeClass('selected');
		var id=$(this).attr("dataId");
		$(this).addClass('selected');
		//lay doi tuong user ko dong bo va xu ly voi ham initUserDetail
		obj.ajaxCall("GET",true,urlApi+id,null,showUserDetail);
	});

}

function showUserDetail(user)
{
	try {
		if (user != {}&&user!=undefined&&user!=""&&user!=null&&user!=[]) {
			// load anh
			$('#user-detail-img').removeClass('hidden');
			$('#user-detail-img').attr('src',
					rootLocation + "resources/commons/image/user/default-user.jpg");
			$('#user-detail-img').attr("src", rootLocation + user.image);
			obj.initData('user-detail', 'user-detail-row', [ user ]);
		} else {
			$('#user-detail-img').removeAttr("src");
			$('#user-detail-img').addClass('hidden');

			obj.initData('user-detail', 'user-detail-row', []);
		}
	} catch (e) {
		// TODO: handle exception
	}
}