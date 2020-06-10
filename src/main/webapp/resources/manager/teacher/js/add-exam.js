$(document).ready(function(){
	obj=new Exam();
	init();
	dateTimePicker();
	submitExam();
});
var obj;
//date time picker
function dateTimePicker()
{
	 $(".form_datetime").datetimepicker({
			format : "dd/mm/yyyy - hh:ii",
			autoclose : true,
			todayBtn : true,
			pickerPosition : "bottom-middle",
		});
}

function init()
{
	var courseId=obj.getParam('course');
	try {
		var course=obj.getCourseById(courseId);
		$('#input-subject').val(course.subject.code+'-'+course.subject.subjectName);
		$('#input-subject').attr('subjectId',course.subject.id);
		$('#input-course').val(course.code+'-'+course.courseName);
		$('#input-course').attr('courseId',course.id);
	} catch (e) {
		// TODO: handle exception
		alert('Đã Xảy Ra Lỗi . Xin Thử Lại Sau !');
	}
}
function submitExam()
{
	$(document).on('click', '#btn-submit', function () {
		if(validInputs()==true)
		{
			var exam;
			try {
				exam={
						name:$('#input-name').val(),
						code:$('#input-code').val(),
						time:$('#input-time').val(),
						grade:$('#input-grade').val(),
						descriptor:$('#input-descriptor').val(),
						timeOpen:$("#time-open").data("datetimepicker").getDate(),
						timeClose:$("#time-close").data("datetimepicker").getDate(),
						subject:{
							id:$('#input-subject').attr('subjectId')
						},
						examCourses:[{
							course:{
								id:$('#input-course').attr('courseId')
							}
						}]
				}
				
				var message=obj.ajaxCall('POST',false,'manager/api/exams',exam,null);
				alert(message.msg)
				if(message.status==STATUS_SUCCESS)
				{
					window.location.href = rootLocation+"teacher/ql-lop-hoc/ql-bai-thi?course="+$('#input-course').attr('courseId');
				}
			//	var message=obj.ajaxCall('POST',false,'manager/api/exams',exam,null);
			} catch (e) {
				// TODO: handle exception
				alert(e);
			}
			
			//var message=obj.ajaxCall('POST',false,'manager/api/exams',exam,null);
		}
		
	});
	
}
function validInputs()
{
	
	var check=true;
	var inputs=$('input[required]');
	for (var i = 0; i < inputs.length; i++) {
		if($(inputs[i]).val()=="")
		{
			check=false;
			$(inputs[i]).addClass('border-danger');
		}
		else $(inputs[i]).removeClass('border-danger');
	}
	if(check==true)
	{
		var exam=obj.getExamByCode($('#input-code').val());
		if(exam!=""&&exam!=null&&exam!=undefined)
		{
			$('#input-code').addClass('border-danger');
			check=false;
			alert('Mã Bài Thi Đã Tồn Tại !');
		}
		else $('#input-code').removeClass('border-danger');
	}
	
	if(check==true)
	{
		var time=$('#input-time').val();
		if($.isNumeric(time)==false)
		{
			$('#input-time').addClass('border-danger');
			check=false;
		}
		else if(parseInt(time)<=0)
		{
			$('#input-time').addClass('border-danger');
			check=false;
		}
		else $('#input-time').removeClass('border-danger');
	}
	
	if(check==true)
	{
		var grade=$('#input-grade').val();
		if($.isNumeric(grade)==false)
		{
			$('#input-grade').addClass('border-danger');
			check=false;
		}
		else if(parseFloat(grade)<=0)
		{
			$('#input-grade').addClass('border-danger');
			check=false;
		}
		else $('#input-grade').removeClass('border-danger');
	}
	
	return check;
}
class Exam extends Base {
	
    constructor() {
    	super();

    }
    
}