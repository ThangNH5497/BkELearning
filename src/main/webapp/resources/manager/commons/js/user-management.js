/* phần thêm mới user */
//kiem tra cac input bat buoc khi them moi
function validInputs(userType)
{
	var check=obj.validInputs('form-add-step-one');
		
		if(check==true)
		{
			var checkDataExis;
			// kiem tra ma code ton tai
			if(userType=="teacher")
			{
				var code=$('#add-one-tab input[name="code"]').val();
				checkDataExis=obj.getTeacherByCode(code);
			}
			else if(userType=="student")
			{
				checkDataExis=obj.getStudentByCode($('#add-one-tab input[name="code"]').val());
			}
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
			if(userType=="teacher")
			{
				checkDataExis=obj.getStudentByUsername($('#add-one-tab input[name="username"]').val());
			}
			else if(userType=="student")
			{
				checkDataExis=obj.getStudentByUsername($('#add-one-tab input[name="username"]').val());
			}
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
			if(validInputs(paramName))
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
	       
	        obj.saveOrUpdate(formData,"POST",urlApi);
	        location.reload(true);
		} catch (e) {
			// TODO: handle exception
			alert("Lỗi : "+err);
		}
	
	
	});
	// dat lai mau border cho input khi click
	$('#modal-add-new input').click(this,function(){		
		$(this).removeClass('border-danger');
	});
	// display filename exel when selected
	 $('#input-file-exel').on('change',this,function(){
         // get the file name
         var fileName = $(this).val();
         // replace the "Choose a file" label
         $(this).next('#modal-add-new .file-exel-name').html(fileName);
     });
	 // prevew image when selected file
	 $('#modal-add-new .input-file-avatar').on('change',this,function(event){
		 var reader = new FileReader();
		 try 
		 {
			 if (this.files && this.files[0]) {
				    var reader = new FileReader();
				    reader.onload = function(e) {
				      $('#modal-add-new .image-preview').attr('src', e.target.result);
				      $('#modal-add-new .image-preview').hide();
				      $('#modal-add-new .image-preview').fadeIn(650);
				    }
				    reader.readAsDataURL(this.files[0]);
				  }
		} catch (e) {
			// TODO: handle exception
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
			var message="";
			try {				
				var formData = new FormData();	
				//them file vao data
		        formData.append('file', $('#modal-add-new #input-file-exel')[0].files[0]);		    
		        obj.saveOrUpdate(formData,"Post",urlApi+'/file');
				location.reload(true);
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
		$('#modal-add-new a[data-toggle="tab"]').on('shown.bs.tab', function (e) {
			resetForm();
		});
		
		// event modal add-new close, reset input,reset actionAdd
		$(document).on('hidden.bs.modal', '#modal-add-new', function(e) {
			resetForm();
		});
		
}
function showMessage(message)
{
	$('#modal-message .message').text(message);
	$('#modal-message img').addClass('hidden');
	$('#modal-message .message').removeClass('hidden');
	$('#modal-message .btn-ok').removeClass('hidden');
	$('#modal-message .btn-ok').click(this,function(){
		//refresh page
		location.reload(true);
	});
}

//dat lai cac gia tri ban dau cho form them moi
function resetForm()
{
	$('.modal input').val("");
	$('.modal input').removeClass('border-danger');
	$('#input-file-exel').next('#modal-add-new .file-exel-name').html("Chọn File");
	$('.modal .image-preview').attr('src',rootLocation+"resources/commons/image/user/default-user.jpg");
	$('.modal .error').addClass('hidden');
	$('#form-container-one').removeClass('hidden');
	$('#form-container-two').addClass('hidden');
	$('.modal .image-preview').attr('src',
			rootLocation + "resources/commons/image/user/default-user.jpg");
}
/*hết phần thêm mới user*/
/* Phần chỉnh sửa user*/
//xu ly cac su kien trong edit user, paramName- ten parametter doi tuong json
//urlApi-dia chi url cua api,userType loai doi tuong (teacher, student)
function editUserEvents(paramName, urlApi) {
	var user;
	// event modal open
	$(document).on('show.bs.modal', '#modal-edit', function(e) {
		user = initFormEdit(paramName);
	});
	// event modal edit closed, reset input of form
	$(document).on('hidden.bs.modal', '#modal-edit', function(e) {
		resetForm();
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
			 obj.saveOrUpdate(formData,"PUT",urlApi);
			 location.reload(true);
		}
		catch (err) {
			alert("Đã Xảy Ra Lỗi : "+err);
		}
	});
	// dat lai mau border cho input khi click
	$('#modal-edit input').click(this, function() {
		$(this).removeClass('border-danger');
	});
	// prevew image when selected file
	$('#modal-edit .input-file-avatar').on('change', this, function(event) {
		try {
			var reader = new FileReader();
			if (this.files && this.files[0]) {
				var reader = new FileReader();
				reader.onload = function(e) {
					$('#modal-edit .image-preview').attr('src', e.target.result);
					$('#modal-edit .image-preview').hide();
					$('#modal-edit .image-preview').fadeIn(650);
				}
				reader.readAsDataURL(this.files[0]);
			}
		} catch (e) {
			// TODO: handle exception
			alert("Lỗi : "+err)
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
		obj.getDataAsync("GET",urlApi+id,showUserDetail);
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