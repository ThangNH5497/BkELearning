$(document).ready(function() {

	obj=new DoExam();
	initData();
	$('input[type=checkbox]').attr('disabled',true);
	
});


function initData()
{

	var id=obj.getParam('id');
	var studentExam=obj.ajaxCall('GET',false,'api/exams/studentexams/'+id);
	var countStudentGrade=0;
	var countQuestionGrade=0;
	try {
		var examPaper=studentExam.examPaper;
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
			
			var answers=examPaper.examPaperQuestions[i].examPaperQuestionAnswers;
			
			$('#question-container [dataId='+question.id+'] .student-grade').val(examPaper.examPaperQuestions[i].studentGrade);
			
			$('#question-container [dataId='+question.id+'] .max-grade').val(examPaper.examPaperQuestions[i].questionGrade);
			countStudentGrade+=parseFloat(examPaper.examPaperQuestions[i].studentGrade);
			countQuestionGrade+=parseFloat(examPaper.examPaperQuestions[i].questionGrade);
			switch (question.type) {
			case "ONE_CHOICE":
			{

				$('#question-container [dataId='+examPaper.examPaperQuestions[i].question.id+'] [field="answer.content"]')
				.append('<div class="py-4 ml-1">Chọn Đáp Án <b>Đúng Nhất</b></div>');
				
				
				answers.sort(function(a, b) {
				    return parseInt(a.answerOrder) - parseInt(b.answerOrder);
				});
				for (var j = 0; j < answers.length; j++) {
					var answerHtml='<div class="row one-choice pt-2" answerId="'+answers[j].id+'"><div class="col-1"><input id="checkbox-'+answers[j].id+ '" epqaId="'+answers[j].id+'" class="checkbox-custom"'
									+'  type="checkbox"> <label'// name="checkbox-1"
									+' for="checkbox-'+answers[j].id+ '" class="checkbox-custom-label">'
									+'</label></div><div style="margin-top:3px;" class="answer-content col-10">'+answers[j].answer.content+'</div><div class="col-1">'+parseFloat(answers[j].answer.weight)*100+' %</div></div>';
					$('#question-container [dataId='+examPaper.examPaperQuestions[i].question.id+'] [field="answer.content"]')
					.append(answerHtml);
					
					// add to result
					if(answers[j].studentAnswer==CHOOSED)
					{
						$('#checkbox-'+answers[j].id).prop('checked',true);
						
						if(parseFloat(answers[j].answer.weight)<=0)
						{
							$('#question-container [answerId='+answers[j].id+']').addClass('bg-danger	');
							$('#question-container [answerId='+answers[j].id+']').addClass('text-white	');
						}
						
					}
					if(parseFloat(answers[j].answer.weight)>0)
					{
						$('#question-container [answerId='+answers[j].id+']').addClass('bg-success	');
						$('#question-container [answerId='+answers[j].id+']').addClass('text-white	');
						
					}
				}
				
				break;
			}
			case "MULTIPLE_CHOICE":
			{
				$('#question-container [dataId='+examPaper.examPaperQuestions[i].question.id+'] [field="answer.content"]')
				.append('<div class="py-4 ml-1">Chọn <b>Các</b> Đáp Án Đúng</div>');
				for (var j = 0; j < answers.length; j++) {
					var answerHtml='<div class="row multiple-choice" answerId="'+answers[j].id+'"><div class="col-1"><input id="checkbox-'+answers[j].id+ '" epqaId="'+answers[j].id+'" class="checkbox-custom"'
									+'  type="checkbox"> <label'// name="checkbox-1"
									+' for="checkbox-'+answers[j].id+ '" class="checkbox-custom-label">'
									+'</label></div><div style="margin-top:3px;" class="answer-content col-10">'+answers[j].answer.content+'</div><div class="col-1">'+parseFloat(answers[j].answer.weight)*100+' %</div></div>';
					$('#question-container [dataId='+examPaper.examPaperQuestions[i].question.id+'] [field="answer.content"]')
					.append(answerHtml);
					// add to result
					// add to result
					if(answers[j].studentAnswer==CHOOSED)
					{
						$('#checkbox-'+answers[j].id).prop('checked',true);
						
						if(parseFloat(answers[j].answer.weight)<=0)
						{
							$('#question-container [answerId='+answers[j].id+']').addClass('bg-danger	');
							$('#question-container [answerId='+answers[j].id+']').addClass('text-white	');
						}
						
					}
					if(parseFloat(answers[j].answer.weight)>0)
					{
						$('#question-container [answerId='+answers[j].id+']').addClass('bg-success	');
						$('#question-container [answerId='+answers[j].id+']').addClass('text-white	');
						
					}
				}
				break;
			}
			case "FILL_WORD":
			{
				$('#question-container [dataId='+examPaper.examPaperQuestions[i].question.id+'] [field="answer.content"]')
				.append('<div class="py-4 ml-1">Điền Câu Trả Lời </div>');
				var answerHtml='<div class="input-group fill-word">'
				  +'<textarea readonly class="form-control" aria-label="With textarea" epqaId="'+examPaper.examPaperQuestions[i].examPaperQuestionAnswers[0].id+'"></textarea></div>'
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
		$('#count-student-grade').text('Tổng Điểm Sinh Viên : '+countStudentGrade);
		$('#count-question-grade').text('Tổng Điểm Bài Thi : '+countQuestionGrade);
	} catch (e) {
		// TODO: handle exception
		alert(e);
	}
	
	
}




var obj;
var examPaperQuestionAnswers=[];
const CHOOSED="CHOOSED";

class DoExam extends Base {
	
    constructor() {
    	super();
    }
}