$(document).ready(function() {

	obj=new SubjectManagement();
	$('#sidebar .active').removeClass('active');
	$('#menu-item-subject').addClass('active');
	
	tableDataEvents();
	
	var subjectId=getParametter('id');
	subject['id']=subjectId;
	//lay du lieu trang va phan trang
	handlePagination($('#pagination'),'admin/course/page/subject?subjectId='+subjectId+'&');
	//search event , file search.js
	searchEvents('admin/course/search/subject?subjectId='+subjectId+'&');
	addNewCourseEvents();
	
});

var data=[];
var obj;
var subject={};
// kiem tra du lieu form add-new

// them cac su kien trong table data
function tableDataEvents()
{
	$(document).on('click', '.btn-refresh', function () {
		location.reload();
	});
	
}
function getParametter(paramName)
{
	const queryString = window.location.search;
	const urlParams = new URLSearchParams(queryString);
	var param = urlParams.get(paramName);
	return param;
}
function validForm(formId,subject)
{
	var checkValidInput=true;
		 var inputs=$('#'+formId+' input[required]');
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
			checkDataExis=obj.getCourseByCode($('#'+formId+' input[name="code"]').val());
			if(checkDataExis!=""&&checkDataExis.code!=subject.code)
			{
				checkValidInput=false;
				//doi mau input
				$('#'+formId+' input[name="code"]').addClass('border-danger');
				//hien canh bao
				$('#'+formId+' label[name="code-error"]').removeClass('hidden');
			}
			else 
			{
				$('#'+formId+' label[name="code-error"]').addClass('hidden');
				$('#'+formId+' input[name="code"]').removeClass('border-danger');
			}
			
		}
		
		
	return checkValidInput;
}
function addNewCourseEvents()
{
	// event submit form
	$('#modal-add-new .btn-submit').click(this,function(){
		if(validForm('form-add-new',{code:""})==true)
		{
			try {
				var jsonObject=obj.formToJson([$('#form-add-new')]);
				jsonObject['subject']=subject;
				var formData = new FormData();
		        //them doi tuong user json vao form data
		        formData.append("course", new Blob([JSON.stringify(jsonObject)], {
		            type: "application/json"
		        }));
		        obj.saveOrUpdate(formData,"POST","admin/course/add");
		        location.reload(true);
			} catch (e) {
				// TODO: handle exception
				alert("Lỗi : "+err);
			}
			
		}
		
	});
	// dat lai mau border cho input khi click
	$('#modal-add-new input').click(this,function(){		
		$(this).removeClass('border-danger');
	});
}
function editSubjectEvents()
{
	var subject;
	$(document).on('show.bs.modal', '#modal-edit', function(e) {
		subject = initFormEdit();
	});
	// event modal edit closed, reset input of form
	$(document).on('hidden.bs.modal', '#modal-edit', function(e) {
		resetFormEdit();
	});
	// event submit form
	$('#modal-edit .btn-submit').click(this,function(){
		if(validForm('form-edit',subject)==true)
		{
			try {
				var formData = new FormData();
				// them doi tuong json vao form data
				var inputs = $('#form-edit input[name]');
				for (var i = 0; i < inputs.length; i++) 
				{
					var name = $(inputs[i]).attr('name');
					subject[name] = $(inputs[i]).val();
				}
		        //them doi tuong user json vao form data
		        formData.append("subject", new Blob([JSON.stringify(subject)], {
		            type: "application/json"
		        }));
		        obj.saveOrUpdate(formData,"PUT","admin/subject/update");
		     // cap nhat lai bang du lieu
				var fields = $('#table-data-body tr.selected td[field]');
				for (var i = 0; i < fields.length; i++) 
				{
					var name = $(fields[i]).attr('field');
					$(fields[i]).text(subject[name]);
				}
			} catch (e) {
				// TODO: handle exception
				alert("Lỗi : "+err);
			}
			
	        //close modal
	        $('#modal-edit').modal('hide');
		}
	
	});
	// dat lai mau border cho input khi click
	$('#modal-edit input').click(this, function() {
		$(this).removeClass('border-danger');
	});
}
//khoi tao cac gia tri co san cho form
function initFormEdit() {
	var subject;
	try {
		// lay doi tuong dang chon
		
		var subjectId = $('#table-data-body tr.selected').attr('dataId');
		
		subject = obj.getSubjectById(subjectId);
		// init input
		var inputs = $('#form-edit input[name]');
		for (var i = 0; i < inputs.length; i++) {
			var name = $(inputs[i]).attr('name');
			$(inputs[i]).val(subject[name]);
		}
		
	} catch (err) {

	}
	return subject;
}
// dat lai cac gia tri ban dau cho form them moi
function resetForm() {
	$('.modal input').val("");
	$('.modal input').removeClass('border-danger');
	$('.modal .error').addClass('hidden');
}
class SubjectManagement extends Base {
	
    constructor() {
    	super();

    }
    
    
}