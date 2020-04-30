$(document).ready(function() {
	obj=new Question();
	//use small pagination for modal
	$('#modal-select-subject ul.pagination').addClass('pagination-sm');
	$('#sidebar .active').removeClass('active');
	$('#menu-item-exam-paper').addClass('active');
	//search events
	searchEvents('key-search','btn-search','api/subjects/search?');
	
	uploadEvents();
});
var obj;
class Question extends Base {
	
    constructor() {
    	super();

    }
    
    
}
function validInputs()
{
	var inputs=$('#exampaper-info input[required]');
	var check=true;
	for (var i = 0; i < inputs.length; i++) {
		if($(inputs[i]).val()=="")
		{
			$(inputs[i]).addClass('border-danger');
			check=false;
		}
		else $(inputs[i]).removeClass('border-danger');
	}
	if(check==true)
	{
		var code=$("#input-code").val();
		var examPaper=obj.getExamPaperByCode(code);
		if(examPaper!=""&&examPaper!=undefined&&examPaper!=null)
		{
			check=false;
			$("#input-code").addClass('border-danger');
			alert('Mã Đề Thi Đã Tồn Tại!');
		}
		else $("#input-code").removeClass('border-danger');
		
	}
	return check;
}

function uploadEvents()
{
	
	//click btn select subject
	$(document).on('click', '.btn-select-subject', function () {
		//open modal select subject
		$(document).off('click', '#modal-select-subject .btn-submit', function () {});
		
		$('#modal-select-subject').modal('show');
		//lay du lieu trang va phan trang
		handlePagination('pagination','table-data-body','row-data-container','api/subjects/page?');
		//add event button
	});
	$(document).on('click', '#modal-select-subject #btn-submit-subject', function () {
		//show info subject selected
		$('#input-subject').val($('#table-data-body .selected [field=code]').text()+' - '
								  +$('#table-data-body .selected [field=subjectName]').text());
		$('#input-subject').attr('subjectId',$('#table-data-body .selected').attr('dataId'));
		//close modal
		$('#modal-select-subject').modal('hide');
	});
	
	//table select subject click
	$(document).on('click', '#table-data-body [dataId]', function () {
		$('#table-data-body .selected').removeClass('selected');
		var id=$(this).attr("dataId");
		$(this).addClass('selected');
		$('#modal-select-subject .btn-submit').removeClass('disabled');
	});
	
	$(document).on('click', '#btn-submit-exampaper', function () {
		if(validInputs()==true)
		{
			var data={
				subject:{
					id:$('#input-subject').attr('subjectId')
				},
				code:$('#input-code').val(),
				name:$('#input-name').val(),
				descriptor:$('#input-descriptor').val()
			}
			var message=obj.ajaxCall('POST',false,'manager/api/exampapers',data,null);
			if(message.status==STATUS_SUCCESS)
			{
				alert('Thêm Thành Công !');
				//redirect to add question page
				window.location.href = rootLocation+"teacher/ql-de-thi/chinh-sua?id="+message.data;
			}
			else alert('Thêm Thất Bại !');
		}
	});
	
	
}