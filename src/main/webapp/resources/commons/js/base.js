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
    
    getData(ajxMethod,ajaxUrl,ajaxData) {
        var returnData = [];
        $.ajax({
            method: ajxMethod,
            url: rootLocation+ajaxUrl,
            async: false,
            data:JSON.stringify(ajaxData),
            dataType : "json",
			contentType : "application/json; charset=utf-8",
            beforeSend: function () {
               // $(loadData).show();
            },
            success: function (data) {
            	returnData=data;
            },
            error: function (data) {
            }
        });
        return returnData;
    }
    
    //get all teachers
    getAllTeachers()
    {
    	return this.getData('GET','teacher/teachers',"")
    }
    //get teacher by username
    getTeacherByUsername(username)
    {
    	return this.getData('GET','teacher/username/'+username,"");
    }
  //get teacher by code
    getTeacherByCode(code)
    {
    	return this.getData('GET','teacher/code/'+code,"");
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
        		for(var j=0;j<keys.length;j++)
        		{
        			if(keys[j]!="image")
        			{
        				$('#'+containerId+' [dataId='+data[i].id+'] [field='+keys[j]+']').text(data[i][keys[j]]);
        			}
        			else
        			{
        				$('[field=image]').attr("src",rootLocation+data[i].image);
        			}
        		}
        		
        	}
        	
        	$('#'+containerId).append(html);
        	$('#'+rowDataId).addClass('hidden');



    }
    
    //chuyen doi du lieu cac form sang json
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