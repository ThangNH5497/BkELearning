$(document).ready(function() {

	obj=new DoExam();

	
	handleEvent();
	initData();
	
	
});

var timeLeft=0;

function initData()
{
	var studentId=userLoged.id;
	var examId=obj.getParam('id');
	var data=obj.ajaxCall('GET',false,'api/exams/id/'+examId+'/students/'+studentId);
	$('#student-info .student-name').text(userLoged.fullName)
	$('#student-info .student-code').text(userLoged.code)
	try {
		var examPaper=data.studentExam.examPaper;
		examPaper.examPaperQuestions.sort(function(a, b) {
		    return parseInt(a.questionOrder) - parseInt(b.questionOrder);
		});
		var rowQuestion=$('#question-row-sample').html();
		$('#question-container').empty();
		for (var i = 0; i < examPaper.examPaperQuestions.length; i++) {
			var question=examPaper.examPaperQuestions[i].question;
			
			$('#question-container').append('<div class="mt-4 mb-4  row d-flex" dataId="'+question.id+'" id="q-'+question.id+'">'+rowQuestion+'</div>');
			
			$('#question-container [dataId='+question.id+'] [field=content]').html(question.content);
			
			$('#question-container [dataId='+question.id+'] .question-index').text('Câu Hỏi '+(parseInt(i)+1));
			
			var pagination='<a href="#q-'+question.id+'" class="item">'+(parseInt(i)+1)+'</a>';
			$('#pagination-question').append(pagination);
			var answers=examPaper.examPaperQuestions[i].examPaperQuestionAnswers;
			switch (question.type) {
			case "ONE_CHOICE":
			{

				$('#question-container [dataId='+examPaper.examPaperQuestions[i].question.id+'] [field="answer.content"]')
				.append('<div class="py-4 ml-1">Chọn Đáp Án <b>Đúng Nhất</b></div>');
				
				
				answers.sort(function(a, b) {
				    return parseInt(a.answerOrder) - parseInt(b.answerOrder);
				});
				for (var j = 0; j < answers.length; j++) {
					var answerHtml='<div class="row one-choice"><div class="col-1"><input id="checkbox-'+answers[j].id+ '" epqaId="'+answers[j].id+'" class="checkbox-custom"'
									+'  type="checkbox"> <label'// name="checkbox-1"
									+' for="checkbox-'+answers[j].id+ '" class="checkbox-custom-label">'
									+'</label></div><div style="margin-top:3px;" class="answer-content">'+answers[j].answer.content+'</div></div>';
					$('#question-container [dataId='+examPaper.examPaperQuestions[i].question.id+'] [field="answer.content"]')
					.append(answerHtml);
					
					// add to result
					if(answers[j].studentAnswer==CHOOSED)
					{
						$('#checkbox-'+answers[j].id).prop('checked',true);
						$('#pagination-question .item[href="#q-'+question.id+'"]').addClass('bg-secondary');
						$('#pagination-question .item[href="#q-'+question.id+'"]').addClass('text-light');
					}
				}
				
				break;
			}
			case "MULTIPLE_CHOICE":
			{
				$('#question-container [dataId='+examPaper.examPaperQuestions[i].question.id+'] [field="answer.content"]')
				.append('<div class="py-4 ml-1">Chọn <b>Các</b> Đáp Án Đúng</div>');
				for (var j = 0; j < answers.length; j++) {
					var answerHtml='<div class="row multiple-choice"><div class="col-1"><input id="checkbox-'+answers[j].id+ '" epqaId="'+answers[j].id+'" class="checkbox-custom"'
									+'  type="checkbox"> <label'// name="checkbox-1"
									+' for="checkbox-'+answers[j].id+ '" class="checkbox-custom-label">'
									+'</label></div><div style="margin-top:3px;" class="answer-content">'+answers[j].answer.content+'</div></div>';
					$('#question-container [dataId='+examPaper.examPaperQuestions[i].question.id+'] [field="answer.content"]')
					.append(answerHtml);
					if(answers[j].studentAnswer==CHOOSED)
					{
						$('#checkbox-'+answers[j].id).prop('checked',true);
						$('#pagination-question .item[href="#q-'+question.id+'"]').addClass('bg-secondary');
						$('#pagination-question .item[href="#q-'+question.id+'"]').addClass('text-light');
					}
				}
				break;
			}
			case "FILL_WORD":
			{
				$('#question-container [dataId='+examPaper.examPaperQuestions[i].question.id+'] [field="answer.content"]')
				.append('<div class="py-4 ml-1">Điền Câu Trả Lời </div>');
				var answerHtml='<div class="input-group fill-word">'
				  +'<textarea class="form-control" aria-label="With textarea" epqaId="'+examPaper.examPaperQuestions[i].examPaperQuestionAnswers[0].id+'"></textarea></div>'
				$('#question-container [dataId='+examPaper.examPaperQuestions[i].question.id+'] [field="answer.content"]')
				.append(answerHtml);
				
				if(answers[0].studentAnswer!=null&&answers[0].studentAnswer!="")
				{
					$('#question-container [dataId='+examPaper.examPaperQuestions[i].question.id+'] [field="answer.content"] textarea').val(answers[0].studentAnswer);
					$('#pagination-question .item[href="#q-'+question.id+'"]').addClass('bg-secondary');
					$('#pagination-question .item[href="#q-'+question.id+'"]').addClass('text-light');
				}
				
				break;
			}

			default:
				break;
			}
			
		}
		
		
		
		timeLeft=data.studentExam.timeLeft;
	} catch (e) {
		// TODO: handle exception
		alert(e);
	}
	
	
}


function handleEvent()
{

	
	$(document).on('click', '#finish-attempt', function (e) {
		$('#modal-alert').modal('show');
	});
	
	$(document).on('click', '#modal-alert .btn-submit', function (e) {
		finishAttempt();
		$('#modal-alert').modal('hide');
	});
	
	$(document).on('change', '.one-choice input[type=checkbox]', function (e) {
		var container=$(this).parents('.answer');
		
	
		if($(this).is(":checked"))
		{
			$(container).find('input[type=checkbox]').prop('checked',false);
			$(this).prop('checked',true);
			
			
		}
		else 
		{
			$(container).find('input[type=checkbox]').prop('checked',false);
			
		}
		
		
	
	});
	
	$(document).on('change', '.answer input[type=checkbox]', function (e) {
		var container=$(this).parents('.answer');
		var question=$($(this).parents('[dataId]'));
		var inputChecked=$(container).find('input[type=checkbox]:checked');
		if(inputChecked.length>0)
		{
			var qId=$(this).parents('[dataId]').attr('dataId');
			$('#pagination-question .item[href="#q-'+qId+'"]').addClass('bg-secondary');
			$('#pagination-question .item[href="#q-'+qId+'"]').addClass('text-light');
		}
		else
		{
			var qId=$(this).parents('[dataId]').attr('dataId');
			$('#pagination-question .item[href="#q-'+qId+'"]').removeClass('bg-secondary');
			$('#pagination-question .item[href="#q-'+qId+'"]').removeClass('text-light');
		}
		//update
		var checkeds=$(question).find('input[type=checkbox]:checked');
		var uncheckeds=$(question).find('input[type="checkbox"]:not(:checked)');
		var data=[];
		for (var i = 0; i < checkeds.length; i++) {
			data.push({
				examPaperQuestionAnswerId:$(checkeds[i]).attr('epqaId'),
				studentAnswer:CHOOSED
			})
		}
		for (var i = 0; i < uncheckeds.length; i++) {
			data.push({
				examPaperQuestionAnswerId:$(uncheckeds[i]).attr('epqaId'),
				studentAnswer:null
			})
		}
		
		sendMessage(JSON.stringify(data),'UPDATE');
		
	
	});
	
	$(document).on('change', '.answer textarea', function (e) {
		var container=$(this).parents('.answer');

		if($(this).val()!="")
		{
			var qId=$(this).parents('[dataId]').attr('dataId');
			$('#pagination-question .item[href="#q-'+qId+'"]').addClass('bg-secondary');
			$('#pagination-question .item[href="#q-'+qId+'"]').addClass('text-light');
		}
		else
		{
			var qId=$(this).parents('[dataId]').attr('dataId');
			$('#pagination-question .item[href="#q-'+qId+'"]').removeClass('bg-secondary');
			$('#pagination-question .item[href="#q-'+qId+'"]').removeClass('text-light');
		}
		var data=[];
		data.push({
			examPaperQuestionAnswerId:$(this).attr('epqaId'),
			studentAnswer:$(this).val()
		});
		sendMessage(JSON.stringify(data),'UPDATE');
	});
	
}
function timeCount(distance)
{
	// Update the count down every 1 second
	var distance = distance*60*1000;
	var minutes = Math.floor(distance / (1000 * 60));
	var seconds = Math.floor((distance % (1000 * 60)) / 1000);

	$('#time-counter .minute').html(minutes);
	$('#time-counter .second').html(seconds);
	if(minutes<3)
	{
		$('#time-counter .minute').addClass('text-danger');
		$('#time-counter .second').addClass('text-danger');
	}
	setInterval(function () {
		distance=distance-1000;

		var minutes = Math.floor(distance / (1000 * 60));
		var seconds = Math.floor((distance % (1000 * 60)) / 1000);

		$('#time-counter .minute').html(minutes);
		$('#time-counter .second').html(seconds);
		if(minutes<3)
		{
			$('#time-counter .minute').addClass('text-danger');
			$('#time-counter .second').addClass('text-danger');
		}
		if(minutes==0&&seconds==0)
		{
			finishAttempt();
		}

	}, 1000);
	
	
}

function finishAttempt()
{
	sendMessage(JSON.stringify('{}'),'FINISH_ATTEMPT');
	setTimeout(function(){ 
		window.location.href = rootLocation+"hoan-thanh";
	}, 1000);
	
}



var obj;
var examPaperQuestionAnswers=[];
const CHOOSED="CHOOSED";

class DoExam extends Base {
	
    constructor() {
    	super();
    }
}