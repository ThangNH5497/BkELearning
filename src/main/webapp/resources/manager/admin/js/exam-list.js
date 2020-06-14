$(document).ready(function() {
	obj=new ExamManagement();
	//use small pagination for modal
	$('#modal-select-subject ul.pagination').addClass('pagination-sm');
	$('#sidebar .active').removeClass('active');
	$('#menu-item-subject').addClass('active');
	init();

});
var courseId;
function init()
{	
	subjectId=obj.getParam('subject');
	try {
		var subject=obj.getSubjectById(subjectId);
		$('#table-title').text(subject.code+'-'+subject.subjectName);
	} catch (e) {
		// TODO: handle exception
	}
	searchEvents('key-search','btn-search','manager/api/exams/search/subjects/'+subjectId+'?',handleLock);
	handlePagination('pagination','table-data-body','row-data-container',
			'manager/api/exams/page/subjects/'+subjectId+'?',function(){
		$('.btn-view').html('<i class="fas fa-sliders-h"></i>');
		$('.btn-view').addClass('btn-edit-course');
		$('.btn-view').removeClass('btn-view');
		handleLock();
		
	});
	
	$(document).on('click', '.btn-add', function () {
		window.location.href = rootLocation+"admin/ql-mon-hoc/ql-bai-thi/them-moi?subject="+subjectId;
	});
	
	$(document).on('click', '.btn-edit', function () {
		var examId=$(this).parents('[dataId]').attr('dataId');
		window.location.href = rootLocation+"admin/ql-mon-hoc/ql-bai-thi/cap-nhat?examId="+examId;
	});
	
	$(document).on('click', '.btn-edit-course', function () {
		var examId=$(this).parents('[dataId]').attr('dataId');
		window.location.href = rootLocation+"admin/ql-mon-hoc/ql-bai-thi/cap-nhat-lop?examId="+examId;
	});
	
	// click table
	$(document).on('click', '#table-data-body [dataId]', function() {
		$('.btn-edit').removeClass('disabled');
		$('#table-data-body .selected').removeClass('selected');
		var id = $(this).attr("dataId");
		$(this).addClass('selected');

	});
}
//xu ly cac du lieu khoa chinh suwa/xoa
function handleLock()
{
	/*try {
		var roles=$('#table-data-body [field="user.role"]');
		for (var i = 0; i < roles.length; i++) {
			if($(roles[i]).text()=='ROLE_TEACHER')
			{
				$(roles[i]).text('Riêng');
			}
			else if($(roles[i]).text()=='ROLE_ADMIN')
			{
				$(roles[i]).text('Chung');
			}
		}
		
		$('.btn-notify').remove();
	} catch (e) {
		// TODO: handle exception
		alert(e);
	}*/
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
        	$('.btn-notify').remove();
    }
}