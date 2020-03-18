$(document).ready(function() {

	obj=new TeacherManager();
	data=obj.getAllTeachers();
	obj.initData('table-data-body','row-data-container',data);
	listennerEvents();
	
	// even tab add teacher select

	$('a[data-toggle="tab"]').on('shown.bs.tab', function (e) {
		$('#modal-add-new input').val("");
		$('#modal-add-new input').removeClass('border-danger');
		$('#customFile').next('.file-exel-name').html("Chọn File");
	});
	
	// event modal add-new close, reset input,reset actionAdd,show form one ,
	// hide form two
	$(document).on('hide.bs.modal', '#modal-add-new', function(e) {
		$('#modal-add-new input').val("");
		$('#form-container-one').removeClass('hidden');
		$('#form-container-two').addClass('hidden');
		$('#customFile').next('.file-exel-name').html("Chọn File");
	});
	
	// btn-next step add teacher
	$('#modal-add-new .btn-next').click(this,function(){
		$('#form-container-one').addClass('hidden');
		$('#form-container-two').removeClass('hidden');
	});
	// btn-back
	$('#modal-add-new .btn-back').click(this,function(){
		$('#form-container-one').removeClass('hidden');
		$('#form-container-two').addClass('hidden');
	});
	// event submit form
	$('#modal-add-new .btn-submit').click(this,function(){		
		// kiem tra du lieu
		// validFormAdd();
		var form1=$('#form-add-step-one');
		var form2=$('#form-add-step-two');
		var form3=$('#form-img');
		var o=obj.formToJson([form1,form2]);
		
		//var jsonText = JSON.stringify();
		var returnData="";
		/*$.ajax({
	    	method : "POST",
	    	url : rootLocation+"teacher/save",
	    	async : false,
	    	data : jsonText,
	    	dataType : "text",
	    	contentType : "application/json; charset=utf-8",

	    	success : function(d) {
	    		returnData=d;
	    			
	    	},
	    	error : function(d) {
	    		
	    	}
	    });*/
	
	            var formData = new FormData();
	            formData.append('file', $('#form-img input[type=file]')[0].files[0]);
	            console.log("form data " + formData);
	            
	            formData.append("extraField", JSON.stringify(o));
	            $.ajax({
	            	method : "POST",
	                url : rootLocation+'teacher/test',
	                data : formData,
	                processData : false,
	                contentType : false,
	                success : function(data) {
	                    alert("success");
	                },
	                error : function(err) {
	                	alert("erro");
	                }
	            });
	        
		
	});
	// dat lai mau border cho input
	// event submit form
	$('input').click(this,function(){		
		// kiem tra du lieu
		$(this).removeClass('border-danger');
	});
	// display filename when selected
	 $('.file-input').on('change',this,function(){
         // get the file name
         var fileName = $(this).val();
         // replace the "Choose a file" label
         $(this).next('.file-exel-name').html(fileName);
     })
	
	
});
var data=[];
var obj;

// kiem tra du lieu form add-new
function validFormAdd()
{
	
	var checkValidInput=true;
		 var inputs=$('#add-one-tab input');
		// kiem tra input trong
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
			var checkData="";
			// kiem tra ma code ton tai
			checkData=obj.getTeacherByCode($('#add-one-tab input[name="code"]').val());
			if(checkData.length>0)
			{
				checkValidInput=false;
				$('#add-one-tab input[name="code"]').addClass('border-danger');
			}
			else 
			{
				$('#add-one-tab input[name="code"]').removeClass('border-danger');
			}
			
			// kiem tra username ton tai
			checkData=obj.getTeacherByUsername($('#add-one-tab input[name="username"]').val());
			if(checkData.length>0)
			{
				checkValidInput=false;
				$('#add-one-tab input[name="username"]').addClass('border-danger');
			}
			else 
			{
				$('#add-one-tab input[name="username"]').removeClass('border-danger');
			}
			// kiem tra password confirm
			if($('.confirm-password').val()!==$('#add-one-tab input[name="password"]').val())
			{
				checkValidInput=false;
				$('.confirm-password').addClass('border-danger');
			}
			else
			{
				$('.confirm-password').removeClass('border-danger');
			}
		}
		
		
	return checkValidInput;
}

// them cac su kien trong table data
function listennerEvents()
{
	$('#table-data-body [dataId]').click(this,function(){
		$('.selected').removeClass('selected');
		var id=$(this).attr("dataId");
		$(this).addClass('selected');
		
		// load anh
		$('.profile-img img').removeClass('hidden');
		if(data[parseInt(id)-1].image!="")
		{
			$('.profile-img img').attr("src",rootLocation+data[parseInt(id)-1].image);
		}
		else $('.profile-img img').attr("src",rootLocation+"resources/commons/image/user-default.jpg");
		// truyen tham so là 1 mang chua 1 phan tu user co chi so -1 do id bat
		// dau bang 1
		obj.initData('user-detail','user-detail-row',[data[parseInt(id)-1]]);
	});
	
	
}

class TeacherManager extends Base {
	
    constructor() {
    	super();

    }
    
    
}