$(document).ready(function() {

	obj=new Password();
	init();
});
var obj;
var user;
const PASSWORD_ERROR=1000;
function init()
{
	
	try {
		user=obj.getUserById(obj.getParam('id'));
		submitData();
	} catch (e) {
		// TODO: handle exception
		alert('Đã xảy ra lỗi. Xin thử lại sau!');
	}
	
}
function validInput()
{
	var check=true;
	var inputs=$('input[required]');
	for (var i = 0; i < inputs.length; i++) {
		var val=$(inputs[i]).val();
		if(val==""||val==undefined||val==null)
		{
			$(inputs[i]).addClass('border-danger');
			check=false;
		}
		else $(inputs[i]).removeClass('border-danger');
	}
	
	if(check==true)
	{
		//check confirm pass word
		var newPass=$('#new-password').val();
		var confirmPass=$('#confirm-password').val();
		if(newPass!==confirmPass){
			check=false;
			$('label[name="confirm-password-error"]').removeClass('hidden');
		}
		else $('label[name="confirm-password-error"]').addClass('hidden');
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
			var passwordDTO={
					userId:user.id,
					oldPassword:$('#password').val(),
					newPassword:$('#new-password').val()
			}
			
			
			//update
			 var msg=obj.ajaxCall("PUT",false,'api/users/'+user.id+'/password',passwordDTO,null);
			 alert(msg.msg);
			 if(msg.status==STATUS_SUCCESS)
			 {
				 window.location.href=rootLocation+'trang-chu';
			 }
			 else if(msg.status==PASSWORD_ERROR)
			 {
				 $('label[name="password-error"]').removeClass('hidden');
			 }
			 else $('label[name="password-error"]').addClass('hidden');
			// location.reload(true);
		}
		catch (err) {
			alert("Đã Xảy Ra Lỗi : "+err);
		}
    });
}

class Password extends Base {
	
    constructor() {
    	super();
    }
}