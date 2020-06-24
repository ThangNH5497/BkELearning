$(document).ready(function() {

	obj=new ExamResult();
	$('#sidebar .active').removeClass('active');
	$('#menu-item-course').addClass('active');
	var studentExamId=obj.getParam('id');
	courseId=obj.getParam('courseId');
	examId=obj.getParam('examId');
	$('#back-link').attr('href',rootLocation+'teacher/ql-lop-hoc/ql-bai-thi?course='+courseId);
	result=obj.ajaxCall('GET',false,'manager/api/exams/'+examId+'/courses/'+courseId+'/result');
	obj.initData('table-data-body','row-data-container',result.data);
	handleEvent();
});
var result;
var courseId;
var examId;
function handleEvent()
{
	$(document).on('click', '#table-data-body tr', function () {
		//dos
		$('.selected').removeClass('selected');
		$(this).addClass('selected');
	});
	//download pdf
	$(document).on('click', '.btn-download-pdf', function () {
		//dos
		window.open(rootLocation+'teacher/ql-lop-hoc/ql-bai-thi/ket-qua/download?examId='+examId+'&courseId='+courseId);
	});
	//download excel
	$(document).on('click', '.btn-download-excel', function () {
		//dos
		window.open(rootLocation+'manager/api/exams/'+examId+'/courses/'+courseId+'/result/download');
	//	window.location.href=rootLocation+'teacher/api/exams/'+examId+'/courses/'+courseId+'/result/download';
		//obj.ajaxCall('GET',false,'teacher/api/exams/'+examId+'/courses/'+courseId+'/result/download',null,null);
		
	});
}

//valid inputs

class ExamResult extends Base {
	
    constructor() {
    	super();
    }
    
    initData(containerId,rowDataId,data)
    {
    	$('#'+rowDataId).removeClass('hidden');
		var html=$('#'+rowDataId).html();
    	$('#'+containerId).empty();
    	// hien thi du lieu
    	if(data.length>0)
    	{
    		for(var i=0;i<data.length;i++)
        	{
    			var htmlAppend='<tr role="row" class="odd" dataId="'+data[i].studentId+'">'+html+'</tr>';
        		$('#'+containerId).append(htmlAppend);
        		
        		var fields=$('#'+containerId+' [dataId='+data[i].studentId+'] [field]');
        		for(var j=0;j<fields.length;j++)
        		{
        			for(var j=0;j<fields.length;j++)
            		{
            			try {
            				
            				var fieldAttr=$(fields[j]).attr('field');
            				var value=data[i][fieldAttr];
                			if(fieldAttr=='index')
                			{
                				
                				$(fields[j]).text(parseInt(i)+1);
                			}
                			else if(fieldAttr=='studentGrade')               				
                			{
                				value=parseFloat(value);
                				value=value.toFixed(2);
                				$(fields[j]).html(value);
                			}
                			else {
                				
                				$(fields[j]).html(value);
                			}
    					} catch (e) {
    						// TODO: handle exception
    						console.log(e);
    					}
            		}
        		}
        	
        	}
    	}
    	else
    	{
    		alert('Lỗi Xảy Ra . Vui Lòng Thử Lại Sau !');
    	}
    }
 
}