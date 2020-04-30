const FLAG_ADD=0;
const FLAG_EDIT=1;
const ONE_CHOICE="ONE_CHOICE";
const MULTIPLE_CHOICE="MULTIPLE_CHOICE";
const FILL_WORD="FILL_WORD";
const FILE_IMAGE="FILE_IMAGE";
const FILE_AUDIO="FILE_AUDIO";
var question;
var teacher={};
var subject={};
function initEditor(flag,urlApi)
{
	if(flag==FLAG_EDIT)
	{
		question=obj.getQuestionById(obj.getParam('id'));
		//init question subject input
		try {
			$('#question-subject').attr('subjectId',question.subject.id);
			$('#question-subject').val(question.subject.code+'-'+question.subject.subjectName);
		} catch (e) {
			// TODO: handle exception
		}
		//init type question
		$('#question-type option').each(function() {
		    if($(this).val() == question.type) {
		        $(this).prop("selected", true);
		    }
		});
		//init level
		$('#question-level option').each(function() {
		    if(parseInt($(this).val()) == question.level) {
		        $(this).prop("selected", true);
		    }
		});
		
		initContentEditor(question.content,urlApi);
		//default show 4 answer first
		for (var i = 0; i < question.answers.length; i++) {
			addAnswerEditor(question.answers[i].content);
		}
		
		//init name
		$('#inputName').val(question.name);
		
		//init choose answer true
		var checkBox=$('.answer .answer-check input[type="checkbox"]');
		var qWeight=$('.answer .answer-weight');
		switch (question.type) {
		
			case 'ONE_CHOICE':
			{
				$('.answer .answer-weight').addClass('hidden');
				$('.answer .answer-check').removeClass('hidden');
				for (var i = 0; i < question.answers.length; i++) {
					if(question.answers[i].correct==true)
						$(checkBox[parseInt(i)+1]).prop('checked',true);
				}
				break;
			}
			case 'MULTIPLE_CHOICE':
			{
				$('.answer .answer-check').addClass('hidden');
				$('.answer .answer-weight').removeClass('hidden');
				for (var i = 0; i < question.answers.length; i++) {
					var o= $(qWeight[parseInt(i)+1]).find('option[value="'+parseFloat(question.answers[i].weight)+'"]');
					 o.prop("selected", true);
				}
				break;
			}

			default:
				break;
		}
	}
	else
	{
		initContentEditor("",urlApi);
		//default show 4 answer first
		for (var i = 0; i < 4; i++) {
			addAnswerEditor("");
		}
	}
	
	// btn view
	//fix responsive editor not work for p tag inside

}
//upload file
function uploadFile(type,files,urlApi)
{
	//$(document).on('change', '.note-image-input', function (e){
	//them file vao data
	for (var i = 0; i < files.length; i++) {
		try {
			var formData = new FormData();
			formData.append('file', files[i]);
			var returnData=obj.saveOrUpdate('POST',false,urlApi,formData,null);
			if(returnData.status==STATUS_SUCCESS)
			{
				var str= rootLocation+returnData.msg;
				if(type==FILE_IMAGE)
				{
					$('#question-editor').summernote('insertImage', rootLocation+returnData.msg);				
				}
				else if(type==FILE_AUDIO)
				{
					// @param {String} HTML string
					var HTMLstring = '<audio controls> <source src="'+rootLocation+returnData.msg+'" type="audio/ogg"></audio>';
					$('#question-editor').summernote('pasteHTML', HTMLstring);
				}
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
function deleteFile(target,urlApi)
{
	for (var i = 0; i < target.length; i++) {
		try {	
			
			var str=target[i].src;
			str=str.replace(rootLocation,"");
			obj.ajaxCall('DELETE',true,urlApi,str,null);
		} catch (e) {
			// TODO: handle exception
			console.log("err : "+e);
		}
				
	}
}

//add answer editor
function addAnswerEditor(content)
{
	var answerHtml=$('#answer-sample').html();
	//$('#wrap-answer').append(answerHtml);
	$(answerHtml).appendTo("#wrap-answer").hide().fadeIn(300);
	updateEditor();
	var numEditor=$('.answer-editor').length;
	initAnswerEditor($($('.answer-editor')[numEditor-1]),content);
}

function removeAnswerEditor()
{
	$(document).on('click', '.btn-remove-answer', function () {
		var answer=$(this).parents('.answer');
		//check total answer than 1
		var numAnswer=parseInt($('.answer').length)-1;
		if(numAnswer>1)
		{
			$(answer).fadeOut("normal", function() {
				$(answer).remove();
				updateEditor();
		    });
		}
		else
		{
			alert('Số Lượng Câu Trả Lời không Thể Bằng Không !');
		}
		
	});
}
function updateEditor()
{
	//update all editor answer
	var answer=$('.answer');
	for (var i = 1; i < answer.length; i++) {
		$($('.answer-index')[i]).text('Đáp Án '+i);
		
		$($('.answer-check input')[i]).attr('id','check-'+i);
		
		$($('.answer-check label')[i]).attr('for','check-'+i);		
		//update id
	//	$($('.answer-editor')[i]).attr('id','answer-editor-'+i);
	}
	
}
//khowir tao editor content
function initContentEditor(content,urlApi)
{
	 //init content
	// $('#question-editor').summernote('pasteHTML', );
	 $('#question-editor').summernote('reset');  // may not be required.
	 $('#question-editor').summernote('destroy');
	 $('#question-editor').html(content);
	 $('#question-editor').summernote({
			height:200,
			callbacks: {
				onImageUpload: function(files) {
					uploadFile(FILE_IMAGE,files,urlApi);
					
				},
				 onMediaDelete : function(target) {
					 console.log('delete');
			         //deleteFile(target);
			     }
				,onAudioUpload:function(files)
				{
					uploadFile(FILE_AUDIO,files,urlApi);
				}
				
					    
			},
			 
			toolbar: [
				['style', ['style']],
				['font', ['bold', 'underline', 'clear']],
				['fontname', ['fontname']],
				['color', ['color']],
				['para', ['ul', 'ol', 'paragraph']],
				['table', ['table']],
				['insert', ['link', 'picture','audio','video']],
				['view', ['fullscreen', 'codeview', 'help']],
			]
		});
	 
}
// editor answer init
function initAnswerEditor(answerEditor,content)
{	
	 $(answerEditor).summernote('destroy');
	 $(answerEditor).html(content);
	 $(answerEditor).summernote({
				height:100,	 
				
				toolbar: [
					['style', ['style']],
					['font', ['bold', 'underline', 'clear']],
					['fontname', ['fontname']],
					['color', ['color']],
					['para', ['ul', 'ol', 'paragraph']],
					['table', ['table']],
					['view', ['fullscreen', 'codeview', 'help']],
				]
			});
		// $(answerEditor).summernote('pasteHTML',content);
}

//vadid input question
function validInput()
{
	
	var inputRequire=$('input[required]');
	var check=true;
	for (var i = 0; i < inputRequire.length; i++) {
		if($(inputRequire[i]).val()=="")
		{
			$(inputRequire[i]).addClass('border-danger');
			$(window).scrollTop($(inputRequire[i]).offset().top-100);
			check=false;
			
			break;
		}
		else $(inputRequire[i]).removeClass('border-danger');
		
	}
	
	if($('#question-subject').attr('subjectId')==''||parseInt($('#question-subject').attr('subjectId'))<=0)
	{
		$(window).scrollTop($('#question-subject').offset().top-100);
		$('#question-subject').addClass('border-danger');
		
		check=false;
	}
	else
	{
		$('#question-subject').removeClass('border-danger');
	}
	
	if(check==true)
	{
		//check content empty
		var type=$("#question-type option:selected").val();
		if (type!=FILL_WORD)
		{
			$('.note-editor').parents('.wrap-editor').removeClass('border-danger');
			var answerEditors=$('.answer-editor');
			//check question empty
			if($('#question-editor').summernote('isEmpty'))
			{
				$(window).scrollTop($('#question-editor').offset().top-100);
				$('.question .note-editor').parents('.wrap-editor').addClass('border-danger');
				alert('Nội Dung Câu Hỏi Trống');
				check=false;
			}
			//check answer 
			else
			{
				$('.question .note-editor').parents('.wrap-editor').removeClass('border-danger');
				for (var i = 1; i < answerEditors.length; i++) {
					if($(answerEditors[i]).summernote('isEmpty'))						
					{
						var container=$(answerEditors[i]).parents('.answer');
						var answerNote=$(container).find('.note-editor');
						$(window).scrollTop($(answerNote).offset().top-100);
						$(answerNote).parents('.wrap-editor').addClass('border-danger');
						check=false;
						alert('Nội Dung Câu Trả Lời Bị Trống');
						break;
						
					}
				}
			}
			//check if answer correct empty
			if(type==ONE_CHOICE&&check==true)
			{
				var checked=$('.answer .answer-check input[type="checkbox"]:checked');
				if(checked.length<=0)
				{
					check=false;
					alert('Bạn Chưa Chọn Đáp Án Đúng Cho Câu Hỏi !');
				}
			}
			else if(check==true)
			{
				var qWeight=$('.answer .answer-weight option:selected');
				var checkAnswerWeigh=false;
				for (var i = 1; i < qWeight.length; i++) {
					if(parseFloat($(qWeight[i]).val())!=0)
					{
						checkAnswerWeigh=true;
						break;
					}
				}
				if(checkAnswerWeigh!=true)
				{
					check=false;
					alert('Chưa Cài Trọng Số Đáp Án Cho Câu Hỏi !')
				}
			}
		}
	}
	else
	{
		alert('Thông Tin Câu Hỏi Chưa Đầy Đủ !');
	}
	return check;
	
}
//upload to server
function uploadQuestionHandle(method,urlApi,successFunction)
{
	if(question==undefined)
	{
		question={};
	}
	
	$(document).on('click', '#btn-submit-question', function () {
		if(validInput()==true)
		{
			$('#modal-alert').modal('show');
		}

	});
	//submit to server
	$(document).on('click', '#modal-alert .btn-submit', function () {
		try {
			$('#modal-alert').modal('hide');
			//question.code=$('#inputCode').val();
			question.name=$('#inputName').val();
			question.level=$("#question-level option:selected").val();
			question.type=$("#question-type option:selected").val();
			//gán môn học
			var subjectId=$('#question-subject').attr('subjectId');
			if(subjectId!=0&&subjectId!=null&&subjectId!=undefined)
			{
				question.subject={id:subjectId};
			}
			//lấy nội dung câu hỏi
			question.content = $('#question-editor').summernote('code');
			question.answers=[];
			if(question.type!=FILL_WORD)
			{
				var answers=$('.answer-editor');
				var checkBox=$('.answer .answer-check input[type="checkbox"]');
				var qWeight=$('.answer .answer-weight option:selected');
				//bỏ qua 1 là phần tử mẫu html
				for (var i = 1; i < answers.length; i++) {
					var answer={
							content : $(answers[i]).summernote('code'),
							correct : false,
							weight : 0
					}
					switch (question.type) {
					case ONE_CHOICE:
					{
						if($(checkBox[i]).prop('checked')==true) 
						{
							answer.correct=true;
							answer.weight=1;
						}
						break;
					}
						
					case MULTIPLE_CHOICE:
					{
						var weight=parseFloat($(qWeight[i]).val());
						if(weight>0)
						{
							answer.correct=true;
						}
						answer.weight=weight;
						break;
					}
					default:
						break;
					}
					question.answers.push(answer);
				}		
			}
			
				
			//	var str = msg.msg.replace(/\\"/g,'\"');
			//save to server
			var message=obj.ajaxCall(method,false,urlApi,question,null);
			alert(message.msg);
			 //window.history.back();
			try {
				if(successFunction!=undefined&&successFunction!=""&&successFunction!=null)
				{
					successFunction();
				}
			} catch (e) {
				// TODO: handle exception
			}
			
			
		} catch (e) {
			// TODO: handle exception
			
			alert('Đã Xảy Ra Lỗi . Xin thử Lại!');
		}
	});
}

function importFromFile(urlApi)
{
	//upload from file exel
	 $(document).on('click', '#modal-file .btn-submit-file-exel', function () {
		 //check file not empty
		if ($('#input-file-exel').get(0).files.length === 0) {
			   $('#alert-file-exel').removeClass('hidden');
		}
		else
		{
			$('#modal-file').modal('hide');
			$('#modal-message').modal({backdrop: 'static', keyboard: false}) ;
			var message="";
			try {				
				var formData = new FormData();	
				//them file vao data
		        formData.append('file', $('#modal-file #input-file-exel')[0].files[0]);	
		        formData.append("subject", new Blob([JSON.stringify(subject)], {
		            type: "application/json"
		        }));
		        obj.saveOrUpdate("POST",true,urlApi,formData, obj.showMessage);
				
			} catch (e) {
				// TODO: handle exception
				obj.showMessage("Lỗi : " +e);
			}
		}
	 });
}

//xử lý các sự kiện trong trang
function eventsHandle()
{
	removeAnswerEditor();
	//add answer editor
	$(document).on('click', '.btn-add-answer', function () {
		addAnswerEditor();
		
	});
	//click btn select subject
	$(document).on('click', '.btn-select-subject', function () {
		$('#modal-select-subject #btn-submit-subject').removeClass('hidden');
		$('#modal-select-subject #btn-select-file').addClass('hidden');
		//$('#modal-select-subject #btn-next-step').addClass('hidden');
		//open modal select subject
		$(document).off('click', '#modal-select-subject .btn-submit', function () {});
		$('#modal-select-subject').modal('show');
		//add event button
	});
	$(document).on('click', '#modal-select-subject #btn-submit-subject', function () {
		//show info subject selected
		$('#question-subject').val($('#table-data-body .selected [field=code]').text()+' - '
								  +$('#table-data-body .selected [field=subjectName]').text());
		$('#question-subject').attr('subjectId',$('#table-data-body .selected').attr('dataId'));
		//close modal
		$('#modal-select-subject').modal('hide');
	});
	//click btn import
	//open modal import
	$(document).on('click', '.btn-import', function () {
		$('#modal-select-subject #btn-submit-subject').addClass('hidden');
		$('#modal-select-subject #btn-select-file').removeClass('hidden');
	//	$('#modal-select-subject #btn-next-step').removeClass('hidden');
		$('#modal-select-subject').modal('show');
	});
	//open modal select file import
	$(document).on('click', '#btn-select-file', function () {
		var id=$('#table-data-body .selected').attr("dataId");
		if(id!=""&&id!=undefined&&id!=null)
		{
			$('#modal-select-subject').modal('hide');
			$('#modal-file').modal('show');
			subject={id:id};
		}
		else alert('Chọn Môn Học Trước !');
		
	});
	$(document).on('click', '.btn-import', function () {
		$('#modal-select-subject').modal('show');
	});
	
	$(document).on('click', '.btn-reload', function () {
		window.location.reload(true);
	});
	//table select subject click
	$(document).on('click', '#table-data-body [dataId]', function () {
		$('#table-data-body .selected').removeClass('selected');
		var id=$(this).attr("dataId");
		$(this).addClass('selected');
		$('#modal-select-subject .btn-submit').removeClass('disabled');
	});
	
	//one-choice check box only one
	$(document).on('click', '.answer-check label', function () {
		$('.answer-check input[type="checkbox"]').prop('checked',false);
	 })
	
	//click option question type
	$('#question-type').change(function() {
	    var val = $("#question-type option:selected").val();
	  
	    switch (val) {
		case ONE_CHOICE:
		{
			$('.answer-check').removeClass('hidden');
			$('.answer-weight').addClass('hidden');
			$('.answer').removeClass('hidden');
			break;
		}
		case MULTIPLE_CHOICE:
		{
			$('.answer-check').addClass('hidden');
			$('.answer-weight').removeClass('hidden');
			$('.answer').removeClass('hidden');
			break;
		}	
		case FILL_WORD:
		{
			//close answer editor
			$('.answer').addClass('hidden');
			break;
		}
		default:
			break;
		}
	});
	
}