var rootLocation;

const STATUS_SUCCESS=800;

const STATUS_ERROR=900;

class Base {
	
    constructor() {
    	var getUrl = window.location;
    	// for localhost
    	rootLocation = getUrl .protocol + "//" + getUrl.host + "/" + getUrl.pathname.split('/')[1];
    	rootLocation=rootLocation+"/";
    	this.InitEventsBase();
    	// for real host
    	// rootLocation = getUrl .protocol + "//" + getUrl.host + "/";
    	// init user profile
    	$('#userDropdown .user-full-name').text(userLoged.fullName);
    	$('#userDropdown img').attr("src",rootLocation+userLoged.image);

    }

    InitEventsBase() {

    	$(document).on('click', 'form input', function () {
    		$(this).removeClass('border-danger');
    	});
    	// modal close
    	$(document).on('hidden.bs.modal', '.modal-reset', function (e){
    		 resetForm();
    	});
    	// tab select
    	$(document).on('shown.bs.tab', 'a[data-toggle="tab"]', function (e){
			resetForm();
		});
		// alert close event
    	$(document).on('close.bs.alert', '.alert', function (e){
			  e.preventDefault();
			  $(this).addClass('hidden');
		 });
		// preview file name
    	$(document).on('change', '#input-file-exel', function (e){
	         // get the file name
	         var fileName = $(this).val();
	         // replace the "Choose a file" label
	         $(this).next('.file-exel-name').html(fileName);
	     });
		// prevew image when selected file
    	$(document).on('change', '.input-file-avatar', function (event){
			try {
				var reader = new FileReader();
				var container=$(this).parents('.profile-img');
				if (this.files && this.files[0]) {
					var reader = new FileReader();
					reader.onload = function(e) {
						$(container).children('img.image-preview').attr('src', e.target.result);
						$(container).children('img.image-preview').hide();
						$(container).children('img.image-preview').fadeIn(650);
					}
					reader.readAsDataURL(this.files[0]);
				}
			} catch (e) {
			}
			
		});
    }
   // call ajax
    ajaxCall(method,async,url,data,functionHandle) {
        var returnData="";
        data=JSON.stringify(data);
        $.ajax({
            method: method,
            url: rootLocation+url,
            async: async,
            data:data,
            dataType : "json",
			contentType : "application/json; charset=utf-8",
            success: function (data) {
            	if(async!=false&&functionHandle!=null&&functionHandle!=undefined)
            	{
            		functionHandle(data);
            	}
            	else returnData=data;
            },
            error: function (err) {  
            	if(functionHandle!=null&&functionHandle!=undefined)
            	{
            		functionHandle(err);
            	}
            }
        });
        return returnData;
    }
    
    // save or update with file or no
    saveOrUpdate(method,async,url,data,functionHandle)
    {
    	var returnData="";
 
    	$.ajax({
        	method : method,
            url : rootLocation+url,
            data : data,
            processData : false,
            contentType : false,
            async:async,
            success : function(data) {
            	if(async!=false&&functionHandle!=null&&functionHandle!=undefined)
            	{
            		functionHandle(data);
            	}
            	else returnData=data;
            },
            errorr : function(err) {
            	if(functionHandle!=null&&functionHandle!=undefined)
            	{
            		functionHandle(err);
            	}
            }
        });
    	return returnData;
    }
    
    // for user
    getUserByUsername(username)
    {
    	return this.ajaxCall('GET',false,'api/users/username/'+username);
    }
    getUserByCode(code)
    {
    	return this.ajaxCall('GET',false,'api/users/code/'+code);
    }
    getUserById(id)
    {
    	return this.ajaxCall('GET',false,'api/users/'+id);
    }
    // get all teachers
    getAllTeachers()
    {
    	return this.ajaxCall('GET',false,'api/teachers');
    }
    // get teacher by username
    getTeacherByUsername(username)
    {
    	return this.ajaxCall('GET',false,'api/teachers/username/'+username);
    }
  // get teacher by code
    getTeacherByCode(code)
    {
    	return this.ajaxCall('GET',false,'api/teachers/code/'+code);
    }
  // get teacher by id
    getTeacherById(id)
    {
    	return this.ajaxCall('GET',false,'api/teachers/'+id);
    }
    
	// ///////////////

    // get all student
    getAllStudents()
    {
    	return this.ajaxCall('GET',false,'api/students/students');
    }
    // get student by username
    getStudentByUsername(username)
    {
    	return this.ajaxCall('GET',false,'api/students/username/'+username);
    }
  // get student by code
    getStudentByCode(code)
    {
    	return this.ajaxCall('GET',false,'api/students/code/'+code);
    }
  // get student by id
    getStudentById(id)
    {
    	return this.ajaxCall('GET',false,'api/students/'+id);
    }
    // ////////////////////////////////////////////
  // get subject by code
    getSubjectByCode(code)
    {
    	return this.ajaxCall('GET',false,'api/subjects/code/'+code);
    }
    // get subject by id
    getSubjectById(subjectId)
    {
    	return this.ajaxCall('GET',false,'api/subjects/'+subjectId);
    }
    // /////////////get course////////////////////
    getCourseById(courseId)
    {
    	return this.ajaxCall('GET',false,'api/courses/'+courseId);
    }
    getCourseByCode(code)
    {
    	return this.ajaxCall('GET',false,'api/courses/code/'+code);
    }
    //////////////questions/////////////////////////
    getQuestionById(questionId)
    {
    	return this.ajaxCall('GET',false,'teacher/api/questions/'+questionId);
    }
    // init data for container data
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
    
    // chuyen doi du lieu cac form sang json object
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
 
   
    // valid input
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
    
    // get param from url
    getParam(paramName)
    {
    	const queryString = window.location.search;
    	const urlParams = new URLSearchParams(queryString);
    	var param = urlParams.get(paramName);
    	return param;
    }
  //lan theo vi tri du lieu theo ten
    resolve(path, obj) {
        return path.split('.').reduce(function(prev, curr) {
            return prev ? prev[curr] : null
        }, obj || self)
    }
}

// resrt form
function resetForm()
{
	$('.modal input').val("");
	$('.modal input').removeClass('border-danger');
	$('#input-file-exel').next('#modal-add-new .file-exel-name').html("Chọn File");
	$('.modal .image-preview').attr('src',rootLocation+"resources/commons/image/user/default-user.jpg");
	$('.modal .error').addClass('hidden');
	$('#form-container-one').removeClass('hidden');
	$('#form-container-two').addClass('hidden');
}