$(document).ready(function() {

	obj=new Profile();
	init();
});
var obj;
var user;
function init()
{
	
	try {
		user=obj.getUserById(obj.getParam('id'));
		$('.image-preview').attr('src',user.image);
		$('#input-email').val(user.email);
		$('#input-addr').val(user.addr);
		$('#input-phone-number').val(user.phoneNumber);
		$('#input-date-of-birth').val(user.dateOfBirth);
		submitData();
	} catch (e) {
		// TODO: handle exception
		alert('Đã xảy ra lỗi. Xin thử lại sau!');
	}
	
}
function validInput()
{
	var check=true;
	var phoneNumber=$('#input-phone-number').val();
	if($.isNumeric(phoneNumber))
	{

		$('#input-phone-number').removeClass('border-danger');
	}
	else
	{
		check=false;
		$('#input-phone-number').addClass('border-danger');
	}
	return check;
}

function submitData()
{
	$('#btn-submit-form').click(this,function() {
		
		if(validInput()==true)
		{
			$('#modal-alert').modal('show');
			
			
		}
		
	});
	
	$(document).on('click', '#modal-alert .btn-submit', function () {
		$('#modal-alert').modal('hide');
		var formData = new FormData();
		try {
			// them file vao data
			formData.append('file',$('input[type=file]')[0].files[0]);
			
			// them doi tuong json vao form data
			var inputs = $('#form-edit input[name]');
			for (var i = 0; i < inputs.length; i++) 
			{
				var name = $(inputs[i]).attr('name');
				user[name] = $(inputs[i]).val();
			}
			formData.append("user", new Blob([ JSON.stringify(user) ], {
								type : "application/json"
				}));
			//update
			 var msg=obj.saveOrUpdate("PUT",false,'api/users/'+user.id,formData,null);
			 alert(msg.msg);
			 if(msg.status==STATUS_SUCCESS)
			 {
				 window.location.href=rootLocation+'trang-chu';
			 }
			// location.reload(true);
		}
		catch (err) {
			alert("Đã Xảy Ra Lỗi : "+err);
		}
    });
}

class Profile extends Base {
	
    constructor() {
    	super();
    }
}