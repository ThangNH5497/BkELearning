$(document).ready(function() {
	obj=new ExamManagement();
	//use small pagination for modal
	$('#modal-select-subject ul.pagination').addClass('pagination-sm');
	$('#sidebar .active').removeClass('active');
	$('#menu-item-course').addClass('active');
	init();
	deleteEvents("manager/api/exams/multiple");

});
var courseId;
function init()
{	
	courseId=obj.getParam('course');
	searchEvents('key-search','btn-search','manager/api/exams/search/courses/'+courseId+'?',function(){
		handleLock();
	});
	handlePagination('pagination','table-data-body','row-data-container',
			'manager/api/exams/page/courses/'+courseId+'?',null);
	
	$(document).on('click', '.btn-add', function () {
		window.location.href = rootLocation+"teacher/ql-lop-hoc/ql-bai-thi/them-moi?course="+courseId;
	});
	
	$(document).on('click', '.btn-edit', function () {
		var examId=$(this).parents('[dataId]').attr('dataId');
		window.location.href = rootLocation+"teacher/ql-lop-hoc/ql-bai-thi/cap-nhat?examId="+examId;
	});
	
	// click table
	$(document).on('click', '#table-data-body [dataId]', function() {
		$('.btn-edit').removeClass('disabled');
		$('#table-data-body .selected').removeClass('selected');
		var id = $(this).attr("dataId");
		$(this).addClass('selected');

	});
	
	 //check-all
	 $('#select-all').click(function(){
		$('#table-data-body [field="user.role"]:contains("Chung")').parents('tr[dataId]').removeClass('checked');
    });
	 //btn cham diem
	// click table
	$(document).on('click', '#table-data-body .btn-notify', function() {
		var examId=$(this).parents('[dataId]').attr('dataId');
		var value=$(this).find('span').text();
		if(parseInt(value)>0)
		{
			window.location.href = rootLocation+"teacher/ql-lop-hoc/ql-bai-thi/danh-sach-cham-diem?examId="+examId+"&courseId="+courseId;
		}
		
	}); 
	
	//xem yeu cau cua sinh vien
	 //btn cham diem
	// click table
	$(document).on('click', '#table-data-body .btn-student-request', function() {
		var examId=$(this).parents('[dataId]').attr('dataId');
		var value=$(this).find('span').text();
		if(parseInt(value)>0)
		{
			window.location.href = rootLocation+"teacher/ql-lop-hoc/ql-bai-thi/yeu-cau-sinh-vien?examId="+examId+"&courseId="+courseId;
		}
		
	}); 
	
	$(document).on('click', '.btn-result', function() {
		 if ($(this).hasClass('disabled')) {
			 	alert('Chọn Bài Thi Trước !');
		      
		    } 
		 //redirect to result view
		 else {
			 var selected=$('#table-data-body .selected');
			 if($(selected).find('[field="status"]').text()=="Kết Thúc")
			 {
				 var eId=$(selected).attr('dataId');
				 window.location.href = rootLocation+"teacher/ql-lop-hoc/ql-bai-thi/ket-qua?examId="+eId+"&courseId="+courseId;
			 }
			 else alert('bài Thi Chưa Kết Thúc');
		 }

	});
	
	//delete
	$(document).on('click', '.btn-delete', function() {
		
		
	}); 
}
function handleLock()
{
	try {
		var roles=$('#table-data-body [field="role"]');
		for (var i = 0; i < roles.length; i++) {
			if($(roles[i]).text()=='ROLE_TEACHER')
			{
				$(roles[i]).text('Riêng');
			}
			else if($(roles[i]).text()=='ROLE_ADMIN')
			{
				$(roles[i]).text('Chung');
				$(roles[i]).addClass('text-danger');
				var tr=$(roles[i]).parents('tr[dataId]');
				$(tr).find('[field=checkBox]').html('');
				$(tr).find('[field=control]').html('');
			}
		}
	} catch (e) {
		// TODO: handle exception
		alert(e);
	}
}
class ExamManagement extends Base {
	
    constructor() {
    	super();
    }
    
    initData(containerId,rowDataId,data)
    {
    		$('#'+rowDataId).removeClass('hidden');
    		var html=$('#'+rowDataId).prop('outerHTML');
        	$('#'+containerId).empty();
        	// hien thi du lieu
        	if(data.length>0)
        	{
        		$('#data-empty-alert').addClass('hidden');
        		for(var i=0;i<data.length;i++)
            	{
            		$('#'+containerId).append(html);
            		$('#'+rowDataId).attr('dataId',data[i].id);
            		$('#'+rowDataId).removeAttr('id');
            		var keys=Object.keys(data[i]);
            		var fields=$('#'+containerId+' [dataId='+data[i].id+'] [field]');
            		for(var j=0;j<fields.length;j++)
            		{
            			try {
            				var fieldAttr=$(fields[j]).attr('field');
                			var value=obj.resolve(fieldAttr,data[i]);
                			if(fieldAttr=='checkBox')
                			{
                				$(fields[j]).children().children('input').attr('id','check-'+i);
                				$(fields[j]).children().children('label.custom-control-label').attr('for','check-'+i);
                			}
                			else if(fieldAttr=='status')
                			{
                				switch (value) {
								case 'FINISH':
								{
									$(fields[j]).text('Kết Thúc');
									$('#'+containerId+' [dataId='+data[i].id+'] [field=control] .btn-edit').remove();
									break;
								}
								case 'OPEN':
								{
									$(fields[j]).text('Đang Mở');
									$(fields[j]).addClass('text-success');
									$('#'+containerId+' [dataId='+data[i].id+'] [field=checkBox]').html('');
									$('#'+containerId+' [dataId='+data[i].id+'] [field=control] .btn-edit').remove();
									break;
								}
								case 'CLOSE':
								{
									$(fields[j]).text('Đang Đóng')
									break;
								}
								default:
									break;
								}
                			}
                			else if(fieldAttr=='role')
                			{
                				if(value=='ROLE_TEACHER')
                				{
                					$(fields[j]).text('Riêng');
                				}
                				else if(value=='ROLE_ADMIN')
                				{
                					$(fields[j]).text('Chung');
                					$(fields[j]).addClass('text-danger');
                					$('#'+containerId+' [dataId='+data[i].id+'] [field=checkBox]').html('');
                					$('#'+containerId+' [dataId='+data[i].id+'] [field=control] .btn-edit').remove();
                					//$(tr).find('[field=checkBox]').html('');
                					//$(tr).find('[field=control]').html('');
                				}
                			}
                			else if(fieldAttr=='control')
                			{
                				var countExamProcess=data[i].countExamProcess;
                				var countStudentRequest=data[i].countStudentRequest;
                				if(countExamProcess>0)
                				{
                					$('#'+containerId+' [dataId='+data[i].id+'] [field=control] .btn-notify span').text(countExamProcess);
                					$('#'+containerId+' [dataId='+data[i].id+'] [field=control] .btn-notify span').addClass('text-danger');
                				}
                				if(countStudentRequest>0)
                				{
                					$('#'+containerId+' [dataId='+data[i].id+'] [field=control] .btn-student-request span').text(countStudentRequest);
                					$('#'+containerId+' [dataId='+data[i].id+'] [field=control] .btn-student-request span').addClass('text-danger');
                				}
                			
                			}
                			else $(fields[j]).html(value);
						} catch (e) {
							// TODO: handle exception
							console.log(e);
						}
            			
      	 
            		}
            	
            	}
        	}
        	else
        	{
        		$('#data-empty-alert').removeClass('hidden');
        	}
        	
        	$('#'+containerId).append(html);
        	$('#'+rowDataId).addClass('hidden');
    }
 
}