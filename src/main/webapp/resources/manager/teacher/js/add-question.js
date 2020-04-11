$(document).ready(function() {
	obj=new Question();
	
	initEditor();
	eventsHandle();
	addQuestionEvents();
	


});
//nếu chưa submit thì xóa các file đã tải lên trước khi rời.
var isSubmited=false;
//khowir tao cac editor
function initEditor()
{
	 $('.question-editor').summernote({
			height:200,
			
			callbacks: {
				onImageUpload: function(files) {
					uploadFile(files);
					
				},
				 onMediaDelete : function(target) {
			         deleteFile(target);
			     }
					    
			},
			 
			toolbar: [
				['style', ['style']],
				['font', ['bold', 'underline', 'clear']],
				['fontname', ['fontname']],
				['color', ['color']],
				['para', ['ul', 'ol', 'paragraph']],
				['table', ['table']],
				['insert', ['link', 'picture','audio']],
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
				//	['insert', ['link', 'picture']],
					['view', ['fullscreen', 'codeview', 'help']],
				]
			});
}

//upload file
function uploadFile(files)
{
	//$(document).on('change', '.note-image-input', function (e){
	//them file vao data
	for (var i = 0; i < files.length; i++) {
		try {
			var formData = new FormData();
			formData.append('file', files[i]);
			var returnData=obj.saveOrUpdate('POST',false,'teacher/api/questions/file-multimedia',formData,null);
			if(returnData.status==STATUS_SUCCESS)
			{
				var str= rootLocation+returnData.msg;
				$('.question-editor').summernote('insertImage', rootLocation+returnData.msg);				
				//str=str.replace(rootLocation,"");
			}
			
		} catch (e) {
			// TODO: handle exception
			console.log("err : "+e);
		}
				
	}
	//});
    
}
//delete file
function deleteFile(target)
{
	for (var i = 0; i < target.length; i++) {
		try {	
			
			var str=target[i].src;
			str=str.replace(rootLocation,"");
			obj.ajaxCall('DELETE',true,'teacher/api/questions/file-multimedia',str,null);
		} catch (e) {
			// TODO: handle exception
			console.log("err : "+e);
		}
				
	}
}

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
function addQuestionEvents()
{
	var question={
			code:"",
			name:"",
			content:"",
			answers:[]
	}
	$(document).on('click', '.btn-submit', function () {
		var qCode=$('#inputCode').val();
		var qName=$('#inputName').val();
		if(qCode=="")
		{
			$('#inputCode').addClass('border-danger');
		}
		else if(qName=="")
		{
			$('#inputName').addClass('border-danger');
			$('#inputCode').removeClass('border-danger');
		}
		else
		{
			$('#inputName').removeClass('border-danger');
			try {
				question.code=qCode;
				question.name=qName;
				//sửa đường link ảnh trước khi up
				var images=$('.question img');
				for (var i = 0; i < images.length; i++) {
					var src=$(images[i]).attr('src');
					src=src.replace(rootLocation,"");
					$(images[i]).attr('src',src);
				}
				
				question.content = $('.question-editor').summernote('code');
				var answers=$('.answer-editor');
				//bỏ qua 1 là phần tử mẫu html
				for (var i = 1; i < answers.length; i++) {
					var answer={
							content:$(answers[i]).summernote('code')
					}
					question.answers.push(answer);
				}			
				//	var str = msg.msg.replace(/\\"/g,'\"');
				//save to server
				var message=obj.ajaxCall('POST',false,'teacher/api/questions',question,null);
				
				if(message.status==STATUS_SUCCESS)
				{
					alert(message.msg);
					window.location.reload(true);
				}
				else
				{
					var images=$('.question img');
					for (var i = 0; i < images.length; i++) {
						var src=$(images[i]).attr('src');
						$(images[i]).attr('src',rootLocation+src);
					}
					alert('Đã Xảy Ra Lỗi . Xin thử Lại!');
				}
				
			} catch (e) {
				// TODO: handle exception
				var images=$('.question img');
				for (var i = 0; i < images.length; i++) {
					var src=$(images[i]).attr('src');
					$(images[i]).attr('src',rootLocation+src);
				}
				alert('Đã Xảy Ra Lỗi . Xin thử Lại!');
			}
			//đặt lại image khi save không thành công
			
		}
		
	});
}
var obj;
class Question extends Base {
	
    constructor() {
    	super();

    }
    
    
}