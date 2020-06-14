$(document).ready(function() {

	obj=new Mark();
	$('#sidebar .active').removeClass('active');
	$('#menu-item-course').addClass('active');
	var studentExamId=obj.getParam('id');
	courseId=obj.getParam('courseId');
	examId=obj.getParam('examId');
	$('#back-link').attr('href',rootLocation+'teacher/ql-lop-hoc/ql-bai-thi/danh-sach-cham-diem?examId='+examId+'&courseId='+courseId);
	studentExam=obj.ajaxCall('GET',false,'manager/api/studentexams/'+studentExamId);
	
	try {
		var examPaper=studentExam.examPaper;
		
		var rowQuestion=$('#question-row-sample').html();
		$('#question-container').empty();
		for (var i = 0; i < examPaper.examPaperQuestions.length; i++) {
			var question=examPaper.examPaperQuestions[i].question;
			
		
			switch (question.type) {
			case "FILL_WORD":
			{
				//init html
				$('#question-container').append('<div class="mt-4 mb-4  row d-flex" dataId="'+examPaper.examPaperQuestions[i].id+'" id="q-'+question.id+'">'+rowQuestion+'</div>');
				
				$('#question-container [dataId='+examPaper.examPaperQuestions[i].id+'] [field=content]').html(question.content);
				
				$('#question-container [dataId='+examPaper.examPaperQuestions[i].id+'] .question-index').text('Câu Hỏi '+(parseInt(i)+1));
				
				var pagination='<a href="#q-'+question.id+'" class="item">'+(parseInt(i)+1)+'</a>';
				$('#pagination-question').append(pagination);
				var answers=examPaper.examPaperQuestions[i].examPaperQuestionAnswers;
				//end init html
			
				
				if(answers[0].studentAnswer!=null&&answers[0].studentAnswer!="")
				{
					$('#question-container [dataId='+examPaper.examPaperQuestions[i].id+'] [field="answer.content"] textarea').val(answers[0].studentAnswer);
					$('#pagination-question .item[href="#q-'+examPaper.examPaperQuestions[i].id+'"]').addClass('bg-secondary');
					$('#pagination-question .item[href="#q-'+examPaper.examPaperQuestions[i].id+'"]').addClass('text-light');
					$('#question-container [dataId='+examPaper.examPaperQuestions[i].id+'] [field="answer.content"] .max-grade').val(examPaper.examPaperQuestions[i].questionGrade);
				}
				
				break;
			}

			default:
				break;
			}
			
		}
		
	} catch (e) {
		// TODO: handle exception
		alert(e);
	}
	
	handleEvent();
//	$('#back-link').attr('href',rootLocation+'teacher/ql-lop-hoc/ql-bai-thi/danh-sach-cham-diem?examId='+'&courseId=1');
});
var studentExam;
var courseId;
var examId;
function handleEvent()
{
	$(document).on('click', '#btn-submit', function () {
		$('#modal-alert').modal('show');
	});
	
	$(document).on('click', '#modal-alert .btn-submit', function () {
		//dos
		$('#modal-alert').modal('hide');
		submitData();
	});
}

//valid inputs
function validInput()
{
	var check=true;
	var inputs=$('#question-container [dataId] .student-grade');
	for (var i = 0; i < inputs.length; i++) {
		var val=$(inputs[i]).val();
		var max=$(inputs[i]).parents('form').find('input.max-grade').val();
		
		if($.isNumeric(val)&&$.isNumeric(max))
		{
			val=parseFloat(val);
			max=parseFloat(max);
			if(val<0||val>max)
			{
				check=false;
				$(inputs[i]).addClass('border-danger');
				$(window).scrollTop($(inputs[i]).offset().top-100);
				alert('Gía trị không hợp lệ !')
				break;
			}
			else $(inputs[i]).removeClass('border-danger');
		}
		else 
		{
			check=false;
			$(inputs[i]).addClass('border-danger');
			$(window).scrollTop($(inputs[i]).offset().top-100);
			alert('Gía trị không hợp lệ !')
			break;
		}
		
	}
	return check;
}

//submit data to server
function submitData()
{
	if(validInput()==true)
	{
		try {
			studentExam.examPaper.examPaperQuestions=[];
			var epq=[];
			var data=$('#question-container [dataId]');
			for (var i = 0; i < data.length; i++) {
				epq.push({
					id:$(data[i]).attr('dataId'),
					questionGrade:$(data[i]).find('.student-grade').val()
				});
			}
			studentExam.examPaper.examPaperQuestions=epq;
			
			var message=obj.ajaxCall('PUT',false,'manager/api/studentexams',studentExam,null);
			if(message.status==STATUS_SUCCESS)
			{
				alert(message.msg);
				window.location.href=rootLocation+'teacher/ql-lop-hoc/ql-bai-thi/danh-sach-cham-diem?examId='+examId+'&courseId='+courseId;
				
			}
			else alert(message.msg);
		} catch (e) {
			// TODO: handle exception
			alert(e);
		}
	}
}
class Mark extends Base {
	
    constructor() {
    	super();
    }
 
}