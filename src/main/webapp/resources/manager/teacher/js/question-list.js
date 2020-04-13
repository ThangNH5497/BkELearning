$(document).ready(function() {

	obj=new QuestionManager();
	$('#sidebar .active').removeClass('active');
	$('#menu-item-teacher').addClass('active');

	deleteEvents("teacher/api/questions/multiple");
	
	//userDetailEvents('api/teachers/');
	
	//lay du lieu trang va phan trang
	handlePagination($('#pagination'),'teacher/api/questions/page/teachers/'+userLoged.id+'?',replaceImg);
	
	//search events
	searchEvents('teacher/api/questions/search?');
	
	tableDataEvents();
	
});

var data=[];
var obj;
// kiem tra du lieu form add-new

// them cac su kien trong table data
function tableDataEvents()
{
	//btn refresh
	$(document).on('click', '.btn-refresh', function () {
		location.reload();
	});
	//click table
	$(document).on('click', '#table-data-body [dataId]', function () {
		$('.btn-edit').removeClass('disabled');
		$('#table-data-body .selected').removeClass('selected');
		var id=$(this).attr("dataId");
		$(this).addClass('selected');
		
	});
	//btn view
	$(document).on('click', '.btn-view', function () {
		try {
			var questionId=$(this).parents('[dataId]').attr('dataId');
			var question=obj.getQuestionById(questionId);			
			var container=$('#question-detail-container');
			var fields=$('#question-detail-container [field]');
			
			//reset and append row sample for answer
			var answerHtmlContainer=$('#answer-sample').prop('outerHTML');	
			var answerHtml=$('#answer-sample').html();
			$('#question-detail-answer').empty();
			
			
			//init question data
			for (var i = 0; i < fields.length; i++) {
				var fieldName=$(fields[i]).attr('field');
				var value=obj.resolve(fieldName,question);
				if(fieldName=='level')
				{
					switch (parseInt(value)) {
					case 0:
					{
						value='Dễ'
						break;
					}
					case 1:
					{
						value='Trung Bình'
						break;
					}
					case 2:
					{
						value='Khó'
						break;
					}	

					default:
						break;
					}
					$(fields[i]).html(value);
				}
				else if(fieldName!='answer')
				{
					$(fields[i]).html(value);
				}
				
				//init answer
				else 
				{
					for (var j = 0; j < question.answers.length; j++) {
						$('#question-detail-answer').append(answerHtml);
						$($('[answerField=content]')[j]).html(question.answers[j].content);
						if(question.answers[j].correct==true)
						{
							$($('[answerField=correct]')[j]).removeClass('hidden');
						}
					}
				}
			}
			$('#question-detail-answer').append(answerHtmlContainer);
			$('#modal-view').modal('show');
			
		} catch (e) {
			// TODO: handle exception
			alert('Có Lỗi Không Xác Định : '+e);
		}
			
		
		
	});
	//mo form edit
	$('.btn-edit').on('click', function() {

	    if ($(this).hasClass('disabled')) {
	        return false;
	    } else {
	    	 $("#modal-edit").modal('show');//show modal
	    }
	});
	
	
}
//replace all image of question content in preview table
function replaceImg()
{
	$('#table-data-body [field=content] img').replaceWith('<i class="ml-2 mr-2 h4 fas fa-images"></i>');
	$('#table-data-body [field=content] iframe').replaceWith('<i class="ml-2 mr-2 h4 fas fa-file-video"></i>');
	$('#table-data-body [field=content] audio').replaceWith('<i class="ml-2 mr-2 h4 fas fa-volume-up"></i>');
	$('#table-data-body [field=content] table,#table-data-body [field=content] ul,#table-data-body [field=content] ol').replaceWith('<i class="ml-2 mr-2 h4 fas fa-table"></i>');
	//truncate text
}
class QuestionManager extends Base {
	
    constructor() {
    	super();
    }
    
    
    
    
}