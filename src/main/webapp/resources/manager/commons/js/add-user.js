//kiem tra cac input bat buoc khi them moi
function validFormAdd(userType)
{
	var checkValidInput=true;
		 var inputs=$('#form-add-step-one input');
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
			if(userType=="teacher")
			{
				checkDataExis=obj.getTeacherByCode($('#add-one-tab input[name="code"]').val());
			}
			else if(userType=="student")
			{
				checkDataExis=obj.getStudentByCode($('#add-one-tab input[name="code"]').val());
			}
			if(checkDataExis!="")
			{
				checkValidInput=false;
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
				checkValidInput=false;
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
				checkValidInput=false;
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
				
				checkValidInput=false;
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
		
		
	return checkValidInput;
}
//xu ly cac su kien trong them moi user, paramName- ten parametter doi tuong json
//urlApi-dia chi url cua api
function addNewUserEvents(paramName,urlApi)
{
	// btn-next step add user
	$('#modal-add-new .btn-next').click(this,function(){
		try {
			if(validFormAdd(paramName))
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
		        $.ajax({
		        	method : "POST",
		            url : rootLocation+urlApi+'/file',
		            data : formData,
		            processData : false,
		            contentType : false,
		            async:true,
		            success : function(data) {
		            	showMessage(data[0]+ " Thành Công, "+data[1]+" Thất Bại.");
		            },
		            error : function(err) {
		            	showMessage("Lỗi : " +err);
		            }
		        });
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
			resetFormAdd();
		});
		
		// event modal add-new close, reset input,reset actionAdd
		$(document).on('hidden.bs.modal', '#modal-add-new', function(e) {
			resetFormAdd();
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
function resetFormAdd()
{
	$('#modal-add-new input').val("");
	$('#modal-add-new input').removeClass('border-danger');
	$('#input-file-exel').next('#modal-add-new .file-exel-name').html("Chọn File");
	$('#modal-add-new .image-preview').attr('src',rootLocation+"resources/commons/image/user/default-user.jpg");
	$('#modal-add-new .error').addClass('hidden');
	$('#form-container-one').removeClass('hidden');
	$('#form-container-two').addClass('hidden');
}
