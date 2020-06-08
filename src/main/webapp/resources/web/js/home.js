$(document).ready(function() {

	obj=new Home();

	var studentId=userLoged.id;
	handlePagination('pagination','table-data-body','row-data-container','api/exams/page/students/'+studentId+'?');	
	
});
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
									case "FINISH":
									{
										value='<div class="text-success">Hoàn Thành</div>';
										$('#'+containerId+' [dataId='+data[i].id+'] [field=result]').html('<a href="facebook.com" class="text-success">Xem</a>');
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
    		$('#data-empty-alert').removeClass('hidden');
    	}
    	
    	$('#'+containerId).append(html);
    	$('#'+rowDataId).addClass('hidden');
    }
}