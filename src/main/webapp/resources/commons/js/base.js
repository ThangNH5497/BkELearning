var rootLocation;

class Base {
	
    constructor() {
    	var getUrl = window.location;
    	// for localhost
    	rootLocation = getUrl .protocol + "//" + getUrl.host + "/" + getUrl.pathname.split('/')[1];
    	rootLocation=rootLocation+"/";
    	// for real host
    	// rootLocation = getUrl .protocol + "//" + getUrl.host + "/";
    	//init user profile
    	$('#userDropdown .user-full-name').text(userLoged.fullName);
    	$('#userDropdown img').attr("src",rootLocation+userLoged.image);

    }

    InitEventsBase() {
    	
    }
    //thuc hien ajax dong bo
    getData(ajxMethod,ajaxUrl) {
        var returnData = "";
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
    	return this.getData('GET','admin/api/teacher/count');
    }
    //lay theo tung khoang
    getTeachersLimit(start,count)
    {
    	return this.getData('GET','admin/api/teacher/teachers/'+start+"/"+count);
    }
    //get all teachers
    getAllTeachers()
    {
    	return this.getData('GET','admin/api/teacher/teachers');
    }
    //get teacher by username
    getTeacherByUsername(username)
    {
    	return this.getData('GET','admin/api/teacher/username/'+username);
    }
  //get teacher by code
    getTeacherByCode(code)
    {
    	return this.getData('GET','admin/api/teacher/code/'+code);
    }
  //get teacher by id
    getTeacherById(id)
    {
    	return this.getData('GET','admin/api/teacher/id/'+id);
    }
    
	////////////////////////////////////////

    //get all student
    getAllStudents()
    {
    	return this.getData('GET','api/student/students');
    }
    //get student by username
    getStudentByUsername(username)
    {
    	return this.getData('GET','api/student/username/'+username);
    }
  //get student by code
    getStudentByCode(code)
    {
    	return this.getData('GET','api/student/code/'+code);
    }
  //get student by id
    getStudentById(id)
    {
    	return this.getData('GET','api/student/id/'+id);
    }
    //////////////////////////////////////////////
  //get subject by code
    getSubjectByCode(code)
    {
    	return this.getData('GET','admin/api/subject/code/'+code);
    }
    //get subject by id
    getSubjectById(subjectId)
    {
    	return this.getData('GET','admin/api/subject/id/'+subjectId);
    }
    ///////////////get course////////////////////
    getCourseById(courseId)
    {
    	return this.getData('GET','api/course/id/'+courseId);
    }
    getCourseByCode(code)
    {
    	return this.getData('GET','api/course/code/'+code);
    }
    initData(containerId,rowDataId,data)
    {
    		$('#'+rowDataId).removeClass('hidden');
    		var html=$('#'+rowDataId).prop('outerHTML');
        	$('#'+containerId).empty();
        	//hien thi du lieu
        	if(data.length>0)
        	{
        		$('#data-empty-alert').addClass('hidden');
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
            			else $(fields[j]).text(value);
      	 
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
    resolve(path, obj) {
        return path.split('.').reduce(function(prev, curr) {
            return prev ? prev[curr] : null
        }, obj || self)
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
    //save or update model
    saveOrUpdate(data,method,urlApi)
    {
    	$.ajax({
        	method : method,
            url : rootLocation+urlApi,
            data : data,
            processData : false,
            contentType : false,
            async:false,
            success : function(data) {
                alert(data);
            },
            errorr : function(err) {
            	alert("error : "+err);
            }
        });
    }
    //xu ly khong dong bộ
    saveOrUpdateAsync(data,method,urlApi,functionHandle)
    {
    	$.ajax({
        	method : method,
            url : rootLocation+urlApi,
            data : data,
            processData : false,
            contentType : false,
            async:true,
            success : function(data) {
            	functionHandle(data.msg);
            },
            errorr : function(err) {
            	alert("error : "+err);
            }
        });
    }
    //valid input
    validInputs(formId)
    {
    	var check=true;
    	var inputs=$('#'+formId+' input[required]');
    	for(var i=0;i<inputs.length;i++)
    	{
    		if($(inputs[i]).val()=="")
    		{
    			$(inputs[i]).addClass('border-danger');
    			check=false;
    		}
    		else 
    		{
    			$(inputs[i]).removeClass('border-danger');
    		}
    	}
    	return check;
    }
    
    //get param from url
    getParam(paramName)
    {
    	const queryString = window.location.search;
    	const urlParams = new URLSearchParams(queryString);
    	var param = urlParams.get(paramName);
    	return param;
    }
}