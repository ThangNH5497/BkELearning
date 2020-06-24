$(document).ready(function() {

	obj=new Home();

	var studentId=userLoged.id;
	handlePagination('pagination','table-data-body','row-data-container','api/exams/page/students/'+studentId+'?filter=all&');	
	 handleEvent();
	 
	 
});

function init()
{
	var filter=obj.getParam('filter');
	if(filter==""||filter==null||filter==undefined) filter="ALL";
}

function handleEvent()
{
	var creatRequestLockId;
	$(document).on('click', '#filterDropdown a', function (e) {
		e.preventDefault();
		alert($(this).attr('value'));
	});
	
	$(document).on('click', '.btn-exam-detail', function (e) {
		e.preventDefault();
		creatRequestLockId=$(this).attr('seId');
		var lock=$(this).attr('lock');
		if(lock=='false')
		{
			
			window.open(rootLocation+'ket-qua?id='+creatRequestLockId);
		}
		else if(lock=='true')
		{
			$('#modal-alert-request-exits').modal('show');
		}
		else if(lock=='create') $('#modal-alert').modal('show');
	});
	
	$(document).on('click', '#modal-alert .btn-submit', function (e) {
		$('#modal-alert').modal('hide');
		setTimeout(function(){$('#modal-request-exam-detail').modal('show');}, 1000);
	});
	//send request exam detail
	$(document).on('click', '#modal-request-exam-detail .btn-submit', function (e) {
		
		var inputReason=$('#input-reason').val();
		if(inputReason==null||inputReason==""||inputReason==undefined)
		{
			$('#input-reason').addClass('border-danger');
		}
		else
		{
			$('#input-reason').removeClass('border-danger');
			$('#modal-request-exam-detail').modal('hide');
			var studentExam={
					id:creatRequestLockId,
					lock:{
						reason:$('#input-reason').val()
					}
			}
			
			var message=obj.ajaxCall('POST',false,'api/exams/studentexams/'+creatRequestLockId+'/lock',studentExam,null);
			alert(message.msg);
			if(message.status=='800') window.location.reload(true);
			
		}
		
	});
	
}

var obj;
class Home extends Base {
	
    constructor() {
    	super();
    }
    //override from base
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
            			var value=this.resolve(fieldAttr,data[i]);

            			if(fieldAttr=='status')
            			{
            				
            				if(data[i].studentExam!=null)
							{
            					if(data[i].studentExam.status=='COMPLETE')
            					{
            						value='<div class="text-success">Hoàn Thành</div>';
									var grade=parseFloat(data[i].studentExam.grade);
									grade=grade*(parseFloat(data[i].grade));
									grade=grade.toFixed(2);
							
									if(grade>parseFloat(data[i].grade)) grade=parseFloat(data[i].grade);
									
									$('#'+containerId+' [dataId='+data[i].id+'] [field=result]').html('<a class="btn-exam-detail" href="#">'+grade+'/'+data[i].grade+'</a>');
									
									if(data[i].studentExam.lock!=null)
									{
										if(data[i].studentExam.lock.lockAccess==true)
										{
											$('#'+containerId+' [dataId='+data[i].id+'] [field=result] a').attr('lock','true');
										}
										else {
											$('#'+containerId+' [dataId='+data[i].id+'] [field=result] a').attr('lock','false');
											$('#'+containerId+' [dataId='+data[i].id+'] [field=result] a').text(grade+'/'+data[i].grade+' ( Chi Tiết )');
										}
									}
									else{
										$('#'+containerId+' [dataId='+data[i].id+'] [field=result] a').attr('lock','create');
										
									}
									$('#'+containerId+' [dataId='+data[i].id+'] [field=result] a').attr('seId',data[i].studentExam.id);
            					
            					}
							}
            				
            				switch (value) {
							case "OPEN":
							{
								if(data[i].studentExam!=null)
								{
									
									switch (data[i].studentExam.status) {
									case "CONTINUE":
									{

										value='<a style="color:#e3ac09;" href="bai-thi?examId='+data[i].id+'&courseId='+data[i].examCourse.course.id+'">Tiếp Tục</a>';
										break;
									}
									
									case "COMPLETE":
									{

										value='<a style="color:#e3ac09;" href="bai-thi?examId='+data[i].id+'&courseId='+data[i].examCourse.course.id+'">Tiếp Tục</a>';
										break;
									}
									
									case "WAIT_RESULT":
									{
										value='<div class="text-info">Chờ Kết Quả</div>';
										/*$('#'+containerId+' [dataId='+data[i].id+'] [field=result]').html('<a href="facebook.com" class="text-success">Xem</a>');*/
										break;
									}

									default:
										break;
									}
									
								}
								else value='<a href="bai-thi?examId='+data[i].id+'&courseId='+data[i].examCourse.course.id+'">Làm Bài</a>';
								break;
							}
							case "CLOSE":
							{
								value='<div style="color:red;">Đóng</div>';
								break;
							}
							case "FINISH":
							{
								value='<div class="text-secondary">Kết Thúc</div>';
								break;
							}
							
							default:
								break;
							}
            				$(fields[j]).html(value);
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
    		alert('Lỗi Xảy Ra Hoặc Bài Thi Không Có Nội Dung !');
    	}
    	
    	$('#'+containerId).append(html);
    	$('#'+rowDataId).addClass('hidden');
    }
}