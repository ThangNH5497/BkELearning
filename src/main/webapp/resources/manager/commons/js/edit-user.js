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
		resetFormEdit();
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
			$.ajax({
				method : "PUT",
				url : rootLocation + urlApi,
				data : formData,
				async : false,
				processData : false,
				contentType : false,
				success : function(data) {
					alert(data);
				},
				errorr : function(err) {
					alert("error : " + err);
				}
			});
			// cap nhat lai bang du lieu
			var fields = $('#table-data-body tr.selected td[field]');
			for (var i = 0; i < fields.length; i++) 
			{
				var name = $(fields[i]).attr('field');
				$(fields[i]).text(user[name]);
			}
			// cap nhat lai bang chi tiet
			//lay doi tuong user ko dong bo
			obj.getDataAsync("GET",'teacher/id/'+user.id,initUserDetail);
		// close modal
			user = {};
			$('#modal-edit').modal('hide');
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
// khoi tao cac gia tri co san cho form
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
// validate form edit
function validateFormEdit() {

}
// dat lai cac gia tri ban dau cho form them moi
function resetFormEdit() {
	$('#modal-edit input').val("");
	$('#modal-edit input').removeClass('border-danger');
	$('#modal-edit .image-preview').attr('src',
			rootLocation + "resources/commons/image/user/default-user.jpg");
	$('#modal-edit .error').addClass('hidden');
}
