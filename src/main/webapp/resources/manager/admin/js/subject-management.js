$(document).ready(function() {

	obj=new SubjectManagement();
	$('#sidebar .active').removeClass('active');
	$('#menu-item-subject').addClass('active');
	
	//file delete.js
	deleteEvents("admin/api/subjects/multiple");
	
	tableDataEvents();
	
	//lay du lieu trang va phan trang
	handlePagination('pagination','table-data-body','row-data-container','api/subjects/page?');
	
	//search events
	searchEvents('key-search','btn-search','api/subjects/search?');
	//for xs
	searchEvents('key-search-xs','btn-search-xs','api/subjects/search?');
	//add events
	addNewSubjectEvents();
	//edit events
	editSubjectEvents();
	
});

var data=[];
var obj;

// them cac su kien trong table data
function tableDataEvents()
{
	$(document).on('click', '.btn-refresh', function () {
		location.reload();
	});
	
	//mo form edit
	$('.btn-edit').on('click', function() {

	    if ($(this).hasClass('disabled')) {
	        return false;
	    } else {
	    	 $("#modal-edit").modal('show');//show modal
	    }
	});
	$(document).on('click', '#table-data-body [dataId]', function () {
		$('.btn-edit').removeClass('disabled');
		$('#table-data-body .selected').removeClass('selected');
		var id=$(this).attr("dataId");
		$(this).addClass('selected');
		
	});
	//btn detail subject
	$(document).on('click', '#table-data-body .btn-subject-detail', function () {
		//lay id tu phan tử tr
		var id=$(this).parents('tr[dataId]').attr('dataId');
		location.href = rootLocation+'admin/ql-mon-hoc/danh-sach-lop?id='+id;
	});
	//btn exam list
	$(document).on('click', '#table-data-body .btn-exam-list', function () {
		//lay id tu phan tử tr
		var id=$(this).parents('tr[dataId]').attr('dataId');
		location.href = rootLocation+'admin/ql-mon-hoc/ql-bai-thi?subject='+id;
	});
}
function validForm(formId,subject)
{
	var check=obj.validInputs(formId);
		
	if(check==true)
	{
		var checkDataExis;
		// kiem tra ma code ton tai
		checkDataExis=obj.getSubjectByCode($('#'+formId+' input[name="code"]').val());
		if(checkDataExis!=""&&checkDataExis.code!=subject.code)
		{
			check=false;
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
	return check;
}
function editSubjectEvents()
{
	var subject;
	$(document).on('show.bs.modal', '#modal-edit', function(e) {
		subject = initFormEdit();
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
		        var data=obj.saveOrUpdate("PUT",false,"admin/api/subjects/"+subject.id,formData,null);
		        alert(data.msg);
		        location.reload(true);
		        /* cap nhat lai bang du lieu
				var fields = $('#table-data-body tr.selected td[field]');
				for (var i = 0; i < fields.length; i++) 
				{
					var name = $(fields[i]).attr('field');
					$(fields[i]).text(subject[name]);
				}*/
			} catch (e) {
				// TODO: handle exception
				alert("Lỗi : "+err);
			}
			
	        //close modal
	        $('#modal-edit').modal('hide');
		}
	
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
function addNewSubjectEvents()
{
	// event submit form
	$('#modal-add-new .btn-submit').click(this,function(){
		if(validForm('form-add-new',{code:""})==true)
		{
			try {
				var jsonObject=obj.formToJson([$('#form-add-new')]);
				var formData = new FormData();
		        //them doi tuong user json vao form data
		        formData.append("subject", new Blob([JSON.stringify(jsonObject)], {
		            type: "application/json"
		        }));
		        var data=obj.saveOrUpdate("POST",false,"admin/api/subjects",formData,null);
		        alert(data.msg);
		        location.reload(true);
			} catch (e) {
				// TODO: handle exception
				alert("Lỗi : "+err);
			}
			
		}
		
	});
}
class SubjectManagement extends Base {
	
    constructor() {
    	super();
    }
        
}