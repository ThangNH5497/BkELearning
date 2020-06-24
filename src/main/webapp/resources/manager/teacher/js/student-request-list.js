$(document).ready(function() {

	obj=new Mark();
	$('#sidebar .active').removeClass('active');
	$('#menu-item-course').addClass('active');
	var examId=obj.getParam('examId');
	var courseId=obj.getParam('courseId');
	var rootApiGet='manager/api/studentexams/page/lock/exams/'+examId+'/courses/'+courseId;
	//handlePagination('pagination','table-data-body','row-data-container',rootApiGet,null);
	$('#back-link').attr('href',rootLocation+'teacher/ql-lop-hoc/ql-bai-thi?course='+courseId);
	var data=obj.ajaxCall('GET',false,rootApiGet,null,null);
	obj.initData('table-data-body','row-data-container',data.data);
	//view mark click
	$(document).on('click', '.btn-access', function () {
		var dataId=$(this).parents('[dataId]').attr('dataId');
		obj.ajaxCall('PUT',false,'manager/api/studentexams/locks/'+dataId,null,null);
		$(this).parents('[dataId]').remove();
		
	});
	$(document).on('click', '.btn-delete', function () {
		var dataId=$(this).parents('[dataId]').attr('dataId');
		obj.ajaxCall('DELETE',false,'manager/api/studentexams/locks/'+dataId,null,null);
		$(this).parents('[dataId]').remove();
	});

});


class Mark extends Base {
	
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
            		$('#'+rowDataId).attr('dataId',data[i].lock.id);
            		$('#'+rowDataId).removeAttr('id');
            		var keys=Object.keys(data[i]);
            		var fields=$('#'+containerId+' [dataId='+data[i].lock.id+'] [field]');
            		for(var j=0;j<fields.length;j++)
            		{
            			try {
            				var fieldAttr=$(fields[j]).attr('field');
                			var value=this.resolve(fieldAttr,data[i]);
                			if(fieldAttr=="image")
                			{
                				$(fields[j]).attr('src',rootLocation+value);
                			}
                			else if(fieldAttr=="index")
                			{
                				$(fields[j]).text(parseInt(i)+1);
                			}
                			else if(fieldAttr=='checkBox')
                			{
                				$(fields[j]).children().children('input').attr('id','check-'+i);
                				$(fields[j]).children().children('label.custom-control-label').attr('for','check-'+i);
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