var rootLocation;

class Base {
	
    constructor() {
    	var getUrl = window.location;
    	// for localhost
    	rootLocation = getUrl .protocol + "//" + getUrl.host + "/" + getUrl.pathname.split('/')[1];
    	rootLocation=rootLocation+"/";
    	// for real host
    	// rootLocation = getUrl .protocol + "//" + getUrl.host + "/";
    	

    }

    InitEventsBase() {
    	
    }
    //thuc hien ajax dong bo
    getData(ajxMethod,ajaxUrl) {
        var returnData = undefined;
        $.ajax({
            method: ajxMethod,
            url: rootLocation+ajaxUrl,
            async: false,
           // dataType : "json",
			contentType : "application/json; charset=utf-8",
            beforeSend: function () {
               // $(loadData).show();
            },
            success: function (data) {
            	returnData=data;
            },
            error: function (data) {
            	alert("Không Thể Nhận Dữ Liệu !");
            }
        });
        return returnData;
    }
    //thuc hien ajax ko dong bo
    getDataAsync(ajxMethod,ajaxUrl,methodHandle) {
        $.ajax({
            method: ajxMethod,
            url: rootLocation+ajaxUrl,
            async: true,
           // dataType : "json",
			contentType : "application/json; charset=utf-8",
            success: function (data) {
            	methodHandle(data);
            },
            error: function (data) {
            	methodHandle(undefined);
            }
        });
    }
    //lay tong so teacher
    getCountTeacher()
    {
    	return this.getData('GET','teacher/count');
    }
    //lay theo tung khoang
    getTeachersLimit(start,count)
    {
    	return this.getData('GET','teacher/teachers/'+start+"/"+count);
    }
    //get all teachers
    getAllTeachers()
    {
    	return this.getData('GET','teacher/teachers');
    }
    //get teacher by username
    getTeacherByUsername(username)
    {
    	return this.getData('GET','teacher/username/'+username);
    }
  //get teacher by code
    getTeacherByCode(code)
    {
    	return this.getData('GET','teacher/code/'+code);
    }
  //get teacher by id
    getTeacherById(id)
    {
    	return this.getData('GET','teacher/id/'+id);
    }

    
    initData(containerId,rowDataId,data)
    {
    		$('#'+rowDataId).removeClass('hidden');
    		var html=$('#'+rowDataId).prop('outerHTML');
        	$('#'+containerId).empty();
        	//hien thi du lieu
        	for(var i=0;i<data.length;i++)
        	{
        		$('#'+containerId).append(html);
        		//dat id du lieu bang id cua data, vi tri 0 la cua hang mau
        		$('#'+rowDataId).attr('dataId',data[i].id);
        		$('#'+rowDataId).removeAttr('id');
        		var keys=Object.keys(data[i]);
        		var fields=$('#'+containerId+' [dataId='+data[i].id+'] [field]');
        		for(var j=0;j<fields.length;j++)
        		{
        			var fieldAttr=$(fields[j]).attr('field');
        			
        			if(fieldAttr=="image")
        			{
        				$(fields[j]).attr('src',data[i][fieldAttr]);
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
        			else $(fields[j]).text(data[i][fieldAttr]);
  	 
        		}
        	
        	}
        	
        	$('#'+containerId).append(html);
        	$('#'+rowDataId).addClass('hidden');



    }
    
    //chuyen doi du lieu cac form sang json object
    formToJson(forms)
    {
    var o = {};
    var dataTmp;
    for(var i=0;i<forms.length;i++)
    {
    	dataTmp=$(forms[i]).serializeArray();
    	 $.each(dataTmp, function() {
    	        if (o[this.name] !== undefined) {
    	            if (!o[this.name].push) {
    	                o[this.name] = [o[this.name]];
    	            }
    	            o[this.name].push(this.value || '');
    	        } else {
    	            o[this.name] = this.value || '';
    	        }
    	    });
    }
    return o;
    };

}