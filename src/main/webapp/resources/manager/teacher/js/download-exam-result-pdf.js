$(document).ready(function() {

	obj=new ExamResult();

	courseId=obj.getParam('courseId');
	examId=obj.getParam('examId');
	result=obj.ajaxCall('GET',false,'manager/api/exams/'+examId+'/courses/'+courseId+'/result');
	obj.initData('table-data-body','row-data-container',result.data);
	window.print();
});
var result;
var courseId;
var examId;


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