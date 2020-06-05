$(document).ready(function() {

	obj=new DoExam();

	var studentId=userLoged.id;
	handleEvent();
	initData();
	
	timeCount();
});


function initData()
{
	var examPaper=obj.getExamPaperById(obj.getParam('id'));
	$('#student-info .student-name').text(userLoged.fullName)
	$('#student-info .student-code').text(userLoged.code)
	try {
		var rowQuestion=$('#question-row-sample').html();
		$('#question-container').empty();
		for (var i = 0; i < examPaper.examPaperQuestions.length; i++) {
			var question=examPaper.examPaperQuestions[i].question;
			
			$('#question-container').append('<div class="mt-4 mb-4  row d-flex" dataId="'+question.id+'" id="q-'+question.id+'">'+rowQuestion+'</div>');
			
			$('#question-container [dataId='+question.id+'] [field=content]').html(question.content);
			
			$('#question-container [dataId='+question.id+'] .question-index').text('Câu Hỏi '+(parseInt(i)+1));
			
			var pagination='<a href="#q-'+question.id+'" class="item">'+(parseInt(i)+1)+'</a>';
			$('#pagination-question').append(pagination);
			
			switch (question.type) {
			case "ONE_CHOICE":
			{

				$('#question-container [dataId='+examPaper.examPaperQuestions[i].question.id+'] [field="answer.content"]')
				.append('<div class="py-4 ml-1">Chọn Đáp Án <b>Đúng Nhất</b></div>');
				
				var answers=examPaper.examPaperQuestions[i].question.answers;
				for (var j = 0; j < answers.length; j++) {
					var answerHtml='<div class="row one-choice"><div class="col-1"><input id="checkbox-'+answers[j].id+ '" answerId="'+answers[j].id+'" class="checkbox-custom"'
									+'  type="checkbox"> <label'//name="checkbox-1"
									+' for="checkbox-'+answers[j].id+ '" class="checkbox-custom-label">'
									+'</label></div><div style="margin-top:3px;" class="answer-content">'+answers[j].content+'</div></div>';
					$('#question-container [dataId='+examPaper.examPaperQuestions[i].question.id+'] [field="answer.content"]')
					.append(answerHtml);
				
				}
				
				break;
			}
			case "MULTIPLE_CHOICE":
			{
				$('#question-container [dataId='+examPaper.examPaperQuestions[i].question.id+'] [field="answer.content"]')
				.append('<div class="py-4 ml-1">Chọn <b>Các</b> Đáp Án Đúng</div>');
				var answers=examPaper.examPaperQuestions[i].question.answers;
				for (var j = 0; j < answers.length; j++) {
					var answerHtml='<div class="row multiple-choice"><div class="col-1"><input id="checkbox-'+answers[j].id+ '" answerId="'+answers[j].id+'" class="checkbox-custom"'
									+'  type="checkbox"> <label'//name="checkbox-1"
									+' for="checkbox-'+answers[j].id+ '" class="checkbox-custom-label">'
									+'</label></div><div style="margin-top:3px;" class="answer-content">'+answers[j].content+'</div></div>';
					$('#question-container [dataId='+examPaper.examPaperQuestions[i].question.id+'] [field="answer.content"]')
					.append(answerHtml);
				}
				break;
			}
			case "FILL_WORD":
			{
				$('#question-container [dataId='+examPaper.examPaperQuestions[i].question.id+'] [field="answer.content"]')
				.append('<div class="py-4 ml-1">Điền Câu Trả Lời </div>');
				var answerHtml='<div class="input-group fill-word">'
				  +'<textarea class="form-control" aria-label="With textarea"></textarea></div>'
				$('#question-container [dataId='+examPaper.examPaperQuestions[i].question.id+'] [field="answer.content"]')
				.append(answerHtml);
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
	
	$('.loader').addClass('hidden');
	$('#main-content').removeClass('hidden');
}


function handleEvent()
{
	$(document).on('change', '.one-choice input[type=checkbox]', function (e) {
		var container=$(this).parents('.answer');
		
		if($(this).is(":checked"))
		{
			$(container).find('input[type=checkbox]').prop('checked',false);
			$(this).prop('checked',true);
		}
		else $(container).find('input[type=checkbox]').prop('checked',false);
	
	});
	
	$(document).on('change', '.answer input[type=checkbox]', function (e) {
		var container=$(this).parents('.answer');
		
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
	
	});
	
}
function timeCount()
{
	// Update the count down every 1 second
	var distance = 4*60*1000;

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
	}, 1000);
	
	
}
var obj;
class DoExam extends Base {
	
    constructor() {
    	super();
    }
}