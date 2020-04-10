$(document).ready(function() {
	obj=new Question();
	 $('.question-editor').summernote({
		height:200,
			 
		toolbar: [
			['style', ['style']],
			['font', ['bold', 'underline', 'clear']],
			['fontname', ['fontname']],
			['color', ['color']],
			['para', ['ul', 'ol', 'paragraph']],
			['table', ['table']],
			['insert', ['link', 'picture']],
			['view', ['fullscreen', 'codeview', 'help']],
		]
	});
	 
	 $('.answer-editor').summernote({
			height:100,
				 
			toolbar: [
				['style', ['style']],
				['font', ['bold', 'underline', 'clear']],
				['fontname', ['fontname']],
				['color', ['color']],
				['para', ['ul', 'ol', 'paragraph']],
				['table', ['table']],
				['insert', ['link', 'picture']],
				['view', ['fullscreen', 'codeview', 'help']],
			]
		});
	 eventsHandle();
	 addQuestion();
});

function eventsHandle()
{
	//remove answer
	$(document).on('click', '.btn-remove-answer', function () {
		var answer=$(this).parents('.answer');
		$(answer).fadeOut("normal", function() {
			$(answer).remove();
	    });
	});
	//add answer
	$(document).on('click', '.btn-add-answer', function () {
		var answerHtml=$('#answer-sample').html();
		//$('#wrap-answer').append(answerHtml);
		$(answerHtml).appendTo("#wrap-answer").hide().fadeIn(300);
	});
	//click
	$(document).on('click', '.answer', function () {
		
	});
}
function addQuestion()
{
	$(document).on('click', '.btn-submit', function () {
		var code = $('.question-editor').summernote('code');
		var msg=obj.ajaxCall('POST',false,'api/users/test',code,null);
		//var a=msg.msg.replace('\\"', '"');
		var str = msg.msg.replace(/\\"/g,'\"');
		alert(str);
	});
}
var obj;
class Question extends Base {
	
    constructor() {
    	super();

    }
    
    
}