$(document).ready(function() {

	obj=new Password();
	init();
	submitData();
});
var obj;
var user;
function init()
{
	var email="";
	$(document).on('click', '#btn-submit-form-username', function () {
		var username=$('#input-username').val();
		if(username==""||username==null||username==undefined)
		{
			$('#input-username').addClass('border-danger');
		}
		else
		{
			$('#input-username').removeClass('border-danger');
			user=obj.getUserByUsername(username);
			if(user!=null&&user!=undefined&&user!="")
			{
				$('#input-username').removeClass('border-danger');
				$('label[name="username-error]').addClass('hidden');
				$('#form-username').addClass('hidden');			
				email=user.email;
				$('.email-addr').text(email);
				$('#form-email').removeClass('hidden');
				setTimeout(function(){ 
					sentEmailCode(email);
				}, 500);
				
			}
			else
			{
				$('#input-username').addClass('border-danger');
				$('label[name="username-error"]').removeClass('hidden');
			}
		}
	});
	
	$(document).on('click', '.btn-resent-code', function () {
		$('.load-icon').removeClass('hidden');
		setTimeout(function(){ 
			sentEmailCode(email);
			$('.load-icon').addClass('hidden');
		}, 1500);
		
	});
	$(document).on('click', '#btn-submit-form-email', function () {
		var code=$('#input-email-code').val();
		if(code==""||code==null||code==undefined)
		{
			$('#input-email-code').addClass('border-danger');
		}
		else
		{
			$('#input-email-code').removeClass('border-danger');
			var msg=obj.ajaxCall('GET',false,'api/users/check-code?code='+code);
			if(msg.status==STATUS_SUCCESS)
			{
				$('#input-email-code').removeClass('border-danger');
				$('label[name="email-code-error]').addClass('hidden');
				$('#form-email').addClass('hidden');			
				$('#form-password').removeClass('hidden');	
			}
			else
			{
				$('#input-email-code').addClass('border-danger');
				$('label[name="email-code-error"]').removeClass('hidden');
			}
		}
		
	});
	
}

function sentEmailCode(email)
{
	var msg=obj.ajaxCall('POST',false,'api/users/emails/'+email+'/code',null,null);
	if(msg.status==STATUS_SUCCESS)
	{
		
	}
	else
	{
		alert('Khổng thể gửi mã xác nhận email !');
		window.location.href=rootLocation+'login';
	}
}

function validInput()
{
	var check=true;
	var inputs=$('#form-password input[required]');
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
	$('#btn-submit-form-password').click(this,function() {
		
		if(validInput()==true)
		{
			$('#modal-alert').modal('show');
		}
		
	});
	
	$(document).on('click', '#modal-alert .btn-submit', function () {
		$('#modal-alert').modal('hide');
		try {
			var id=user.id;
			user={
					id:id,
					password:$('#new-password').val()
			}
			//update
			 var msg=obj.ajaxCall("PUT",false,'api/users/password',user,null);
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

class Password extends Base {
	
    constructor() {
    	super();
    }
}