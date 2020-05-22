$(document).ready(function() {
	obj=new Question();
	// use small pagination for modal
	$('#modal-select-subject ul.pagination').addClass('pagination-sm');
	$('#sidebar .active').removeClass('active');
	$('#menu-item-exampaper').addClass('active');
	$('#submenu-exampaper').collapse('show');
	$('#exampaper-list').addClass('text-primary');
	
	init();
	tableDataEvents();
	filterEventHandle();
	searchSubject();
	handleEvents();
	addQuestionSelectedEvents();
	saveExamPaperEvent();
	shuffleQuestionEvents();
});
var obj;
var examPaper={};
function handleEvents()
{
	var check=true;
	var deleteId;
	// btn view
	$(document).on('click', '#table-data-body .btn-view-inside', function() {
		var questionId = $(this).parents('[dataId]').attr('dataId');
		check=true;
		$('#modal-select-question').modal('hide');
		var question = obj.getQuestionById(questionId);
		if(question!=null&&question!=undefined&&question!="") {
			viewQuestion(question);
		}
		else alert('Có Lỗi Xảy Ra. Xin Thử Lại Sau ! ');
		$(document).on('hidden.bs.modal', '#modal-select-question', function (e){
			if(check==true) $('#modal-view').modal('show');
		});
		
		$(document).on('hidden.bs.modal', '#modal-view', function (e){
			if(check==true) $('#modal-select-question').modal('show');
			check=false;
		});
	});
	$(document).on('show.bs.modal', '#modal-select-question', function (e){
		$('#modal-select-question .subject-info').text(examPaper.subject.code+'-'+examPaper.subject.subjectName);
		$('#modal-select-question .teacher-info').text(examPaper.user.fullName);
	});
	//view question of exampaper
	// btn view
	$(document).on('click', '#question-list-container .btn-view', function() {
		var questionId = $(this).parents('[dataId]').attr('dataId');
		
		var question = null;
		for (var i = 0; i < examPaper.examPaperQuestions.length; i++) {
			if(examPaper.examPaperQuestions[i].question.id==questionId)
			{
				question=examPaper.examPaperQuestions[i].question;
				break;
			}
		}
		if(question!=null&&question!=undefined&&question!="") {
			viewQuestion(question);
			$('#modal-view').modal('show');
		}
		else alert('Có Lỗi Xảy Ra. Xin Thử Lại Sau ! ');
	});
	
	//delete question from exampaper
	$(document).on('click', '.btn-delete', function() {
		deleteId=$(this).parents('[dataId]').attr('dataId');
		$('#modal-delete-alert').modal('show');
	});
	$(document).on('click', '#modal-delete-alert .btn-submit', function() {
		$('[dataId='+deleteId+']').remove();
		
		for (var i = 0; i < examPaper.examPaperQuestions.length; i++) {
			if(examPaper.examPaperQuestions[i].question.id==deleteId)
			{
				examPaper.examPaperQuestions.splice(i,1);
				break;
			}
		}
		
		$('#modal-delete-alert').modal('hide');
	});
}
// init
function init()
{
	var id=obj.getParam('id');
	examPaper=obj.getExamPaperById(id);
	if(examPaper==""||examPaper==null||examPaper==undefined)
	{
		alert('Lỗi Khi lấy Dữ Liệu . Vui Lòng Thử Lại !');
	}
	else
	{
		
		$('#input-subject').val(examPaper.subject.code+'-'+examPaper.subject.subjectName);
		$('#input-subject').attr('subjectId',examPaper.subject.id);
		$('#input-code').val(examPaper.code);
		$('#input-name').val(examPaper.name);
		$('#input-time').val(examPaper.time);
		$('#input-descriptor').val(examPaper.descriptor);
		$(document).on('click', '.btn-select-question', function (e) {
			e.stopPropagation();
		    e.preventDefault();
			$('#modal-select-question').modal('show');
		});
		$(document).on('click', '.btn-random-question', function (e) {
			e.stopPropagation();
		    e.preventDefault();
			$('#modal-random-question').modal('show');
		});
		teacherId=userLoged.id;
		filterSubject=examPaper.subject.id;
		filterType='ALL';
		filterLevel='ALL'
		$('#filter-subject input').attr('val',filterSubject);
		$('#filter-type input').attr('val','ALL');
		$('#filter-level input').attr('val','ALL');
		
		$('#filter-subject input').val(examPaper.subject.code+'-'+examPaper.subject.subjectName);
		$('#filter-type input').val("Tất Cả");
		$('#filter-level input').val("Tất Cả");
		
		//modal random question
		$('#modal-random-question .subject').text(examPaper.subject.code+'-'+examPaper.subject.subjectName);
		$('#modal-random-question .user').text(userLoged.fullName);
		
		//show category filter
		showCategory(examPaper.subject.id);
		
		getRandomQuestion();
		
		checkboxControl();
		// init url api
		rootApiGet='manager/api/questions/page/teachers/'+teacherId+'/subjects/'+filterSubject+'/types/'+filterType+'/levels/'+filterLevel+'?'
		rootApiSearch='manager/api/questions/search/teachers/'+teacherId+'/subjects/'+filterSubject+'/types/'+filterType+'/levels/'+filterLevel+'?'
		// lay du lieu trang va phan trang
		handlePagination('pagination','table-data-body','row-data-container',rootApiGet,replaceImg);
		// lay du lieu trang va phan trang tim kiem mon hoc cho filter
		handlePagination('pagination-subject','table-data-body-subject','row-data-container-subject','api/subjects/page?');
		searchEvents('key-search','btn-search',rootApiSearch);
		
		for (var i = 0; i < examPaper.examPaperQuestions.length; i++) {
			addQuestionToView(examPaper.examPaperQuestions[i].question);
		}
	}
}


// xử lý các sự kiện chọn bộ lọc
function filterEventHandle()
{
	// stop a link of filter
	$(document).on('click', '#filter a.dropdown-item', function (e) {
		e.preventDefault();
		event.stopPropagation();
	});
	// filter subject
	$(document).on('click', '#filter-subject a', function (e) {
		var value=$(this).attr('value');
		if(value=="ALL")
		{
			$('#filter-subject input').attr('val','ALL');
			filterData();
		}
		else
		{
			$('#modal-select-subject').modal('show');
		}
		
		
	});
	// filter type,level
	$(document).on('click', '#filter-type a,#filter-level a', function (e) {
		var value=$(this).attr('value');
		var input=$(this).parents('.filter-item').find('input');
		$(input).attr('val',value);
		
		filterData();
	});
	
	// select subject
	
	$(document).on('click', '#modal-select-subject .btn-submit', function (e) {
		if(!($(this).hasClass( "disabled" )))
		{
			var subject=$('#modal-select-subject .selected').attr('dataId');
			$('#filter-subject input').attr('val',subject);
			filterData();
		}
	});
}
function filterData()
{
	filterType=$('#filter-type input').attr('val');
	filterLevel=$('#filter-level input').attr('val');
	
	$('#filter-type input').attr('val',filterType);
	$('#filter-level input').attr('val',filterLevel);
	
	switch (filterType) {
		case "ALL":
		{
			$('#filter-type input').val("Tất Cả");
			
			break;
		}
		case "ONE_CHOICE":
		{
			$('#filter-type input').val("Một Đáp Án");
			
			break;
		}	
		case "MULTIPLE_CHOICE":
		{
			$('#filter-type input').val("Nhiều Đáp Án");
			
			break;
		}
		case "FILL_WORD":
		{
			$('#filter-type input').val("Điền Từ");
			
			break;
		}
		default:
		{
			break;
		}
	}
	switch (filterLevel) {
		case "ALL":
		{
			$('#filter-level input').val("Tất Cả");
			
			break;
		}
		case "0":
		{
			$('#filter-level input').val("Dễ");
			
			break;
		}	
		case "1":
		{
			$('#filter-level input').val("Trung Bình");
			
			break;
		}
		case "2":
		{
			$('#filter-level input').val("Khó");
			
			break;
		}
		default:
		{
			break;
		}
	}
	
	rootApiGet='manager/api/questions/page/teachers/'+teacherId+'/subjects/'+examPaper.subject.id+'/types/'+filterType+'/levels/'+filterLevel+'?';
	rootApiSearch='manager/api/questions/search/teachers/'+teacherId+'/subjects/'+examPaper.subject.id+'/types/'+filterType+'/levels/'+filterLevel+'?';
	// lay du lieu trang va phan trang
	
	handlePagination('pagination','table-data-body','row-data-container',rootApiGet,replaceImg);
	searchEvents('key-search','btn-search',rootApiSearch);
}
//them cac cau hoi da chon trong ngan hang cau hoi
function addQuestionSelectedEvents()
{
	$(document).on('click', '#btn-submit-question-bank', function (e) {
		var rowChecked=$('#modal-select-question tr.checked');
		if(rowChecked.length<=0)
		{
			alert('Không Có Mục Được Chọn !');
		}
		else
		{
			var i=0;
			for (i = 0; i < rowChecked.length; i++) {
				var question=obj.getQuestionById($(rowChecked[i]).attr('dataId'));
				 examPaper.examPaperQuestions.push({
					  question:question,
					  examPaperQuestionAnswers:[]
				  });
				addQuestionToView(question);
			}
			alert('Đã Thêm '+i);
		}
	});
}
//them question view vao danh sach cau hoi
function addQuestionToView(question)
{
	
	var rowData=$('#question-item-sample').html();
	try {
		rowData='<div dataId="'+question.id+'">'+rowData+'</div>';
		$('#question-list-container').append(rowData);
		$('#question-list-container [dataId='+question.id+'] .question-content').html(question.content);
		//replace img,video ,audio tag
		$('#question-list-container [dataId='+question.id+'] .question-content img').replaceWith(
		'<i class="ml-2 mr-2 h4 fas fa-images"></i>');
		$('#question-list-container [dataId='+question.id+'] .question-content iframe').replaceWith(
		'<i class="ml-2 mr-2 h4 fas fa-file-video"></i>');
		$('#question-list-container [dataId='+question.id+'] .question-content audio').replaceWith(
		'<i class="ml-2 mr-2 h4 fas fa-volume-up"></i>');
		$('#question-list-container [dataId='+question.id+'] .question-content table,#question-list-container [dataId='+question.id+'] .question-content ul,#question-list-container [dataId='+question.id+'] .question-content ol')
		.replaceWith('<i class="ml-2 mr-2 h4 fas fa-table"></i>');
	} catch (e) {
		// TODO: handle exception
	}
	
}

//handle event for get question random
function getRandomQuestion()
{
	var questions;
	$(document).on('click', '#modal-random-question .btn-submit', function (e){
		if($('#input-question').val()==''||
			$.isNumeric( $('#input-question').val() )==false)
		{
			$('#input-question').addClass('border-danger');
			alert('Số Lượng Câu Hỏi Không Hợp Lệ !');
		}
		else
		{
			$('#input-question').removeClass('border-danger');
			var categoryIds=[];
			var levels=[];
			var types=[];
			var existQuestionIds=[];
			var filter=$('#random-question-filter-level input:checked');
			if(filter.length<=0)
			{
				levels=null;
			}
			else
			{
				for (var i = 0; i < filter.length; i++) {
					if($(filter[i]).val()=="ALL")
					{
						levels=null;
						break;
					}
					levels.push($(filter[i]).val());
				}
			}
			
			
			var filter=$('#random-question-filter-type input:checked');
			if(filter.length<=0)
			{
				types=null;
			}
			else
			{
				for (var i = 0; i < filter.length; i++) {
					if($(filter[i]).val()=="ALL")
					{
						types=null;
						break;
					}
					types.push($(filter[i]).val());
				}
			}
			
			var filter=$('#random-question-filter-category input:checked');
			if(filter.length<=0)
			{
				categoryIds=null;
			}
			else
			{
				for (var i = 0; i < filter.length; i++) {
					if($(filter[i]).val()=="ALL")
					{
						categoryIds=null;
						break;
					}
					categoryIds.push($(filter[i]).val());
				}
			}
			filter=$('#question-list-container [dataId]');
			if(filter.length<=0) existQuestionIds=null;
			else
			{
				for (var i = 0; i < filter.length; i++) {
					
					existQuestionIds.push($(filter[i]).attr('dataId'));
				}
			}
			
			var data={
					subjectId:examPaper.subject.id,
					categoryIds:categoryIds,
					levels:levels,
					types:types,
					count:$('#input-question').val(),
					existQuestionIds:existQuestionIds
			}
			questions=obj.ajaxCall("POST",false,'manager/api/questions/random',data,null);
			for (var i = 0; i < questions.length; i++) {
				addQuestionToView(questions[i]);
				examPaper.examPaperQuestions.push({
					  question:questions[i],
					  examPaperQuestionAnswers:[]
				  });
			}
			alert('Lấy Thành Công '+questions.length+'/'+data.count+' Câu Hỏi')
		}
	});
}
//checkbox of modal random question
function checkboxControl()
{
	$(document).on('click', '#modal-random-question .dropdown-menu', function (e) {
		  e.stopPropagation();
		});
	$(document).on('click', '#modal-random-question input[type="checkbox"]', function () {
       
    });
	 //check-all
	$(document).on('change', '#modal-random-question .check-all', function() {
		var ulContainerId=$(this).parents('ul');
	    if($(this).prop('checked')==true) {
	      // checkbox is checked
	    	//$('#'+ulContainerId+' input[type=checkbox]').prop('checked',true);
	    	$(ulContainerId).find('input[type=checkbox]').prop('checked',true);
	    }
	    else
	    {
	    	$(ulContainerId).find('input[type=checkbox]').prop('checked',false);
	    }
	});
}
//show question of exam
function showQuestion()
{
	
}
function saveExamPaperEvent()
{
	
	$(document).on('click', '#btn-submit-exampaper', function (e) {
		if(validInputs()==true)
		{
			  examPaper.name=$('#input-name').val();
			  examPaper.time=$('#input-time').val();
			  examPaper.descriptor=$('#input-descriptor').val();
			
			  for (var i = 0; i < examPaper.examPaperQuestions.length; i++) {
					  //if question exis , update
					  if(examPaper.examPaperQuestions[i].examPaperQuestionAnswers.length==
						  examPaper.examPaperQuestions[i].question.answers.length)
					  {
						  for (var j = 0; j <  examPaper.examPaperQuestions[i].question.answers.length; j++) {
							  examPaper.examPaperQuestions[i].examPaperQuestionAnswers[j].answer=
								  {
									  id:examPaper.examPaperQuestions[i].question.answers[j].id
								  };
							  examPaper.examPaperQuestions[i].examPaperQuestionAnswers[j].answerOrder=parseInt(j)+1;
					 
						  } 
					  }
					  //create newion 
					  else
					  {
						  for (var j = 0; j <  examPaper.examPaperQuestions[i].question.answers.length; j++) {
							  examPaper.examPaperQuestions[i].examPaperQuestionAnswers.push({
								  answer:{
									  id:examPaper.examPaperQuestions[i].question.answers[j].id
								  },
								  answerOrder:parseInt(j)+1
							  });
						  }
					  }
					  
				
			  }

			
			  var msg=obj.ajaxCall('PUT',false,'manager/api/exampapers',examPaper,null);
			  alert(msg.msg);
		}
		  
	});
}

function showCategory(subjectId)
{
	var categorys=obj.getCategoryBySubject(subjectId);
	var html='<li><div class="dropdown-divider"></div></li><li>'+$('#category-item-sample').html()+'</li>';
	$('#category-item-sample').remove();
	try {
		for (var i = 0; i < categorys.length; i++) {
			$('#random-question-filter-category').append(html);
			$('#category-check-sample').attr('value',categorys[i].id);
			$('#category-check-sample').attr('id','category-check-'+i);
			$('#random-question-filter-category label[for=category-check-sample]').text(categorys[i].name);
			$('#random-question-filter-category label[for=category-check-sample]').attr('for','category-check-'+i);
		}
	} catch (e) {
		// TODO: handle exception
	}
}
function shuffleQuestionEvents()
{
	$(document).on('click', '.btn-question-shuffle', function (e) {
		e.stopPropagation();
	    e.preventDefault();
		examPaper.examPaperQuestions=shuffleArray(examPaper.examPaperQuestions);
		//update view question
		var rowData=$('#question-item-sample').prop('outerHTML');
		$('#question-list-container').empty();
		$('#question-list-container').append(rowData);
		for (var i = 0; i < examPaper.examPaperQuestions.length; i++) {
			examPaper.examPaperQuestions[i].questionOrder=parseInt(i)+1;
			addQuestionToView(examPaper.examPaperQuestions[i].question);
			
		}
	});
	$(document).on('click', '.btn-answer-shuffle', function (e) {
		e.stopPropagation();
	    e.preventDefault();
		examPaper.examPaperQuestions=shuffleArray(examPaper.examPaperQuestions);
		//update view question
		for (var i = 0; i < examPaper.examPaperQuestions.length; i++) {
			examPaper.examPaperQuestions[i].question.answers=shuffleArray(examPaper.examPaperQuestions[i].question.answers);
		}
	});
}
//tron ngau nhien phan tu trong mang
function shuffleArray(array) {
    for (var i = array.length - 1; i > 0; i--) {
        var j = Math.floor(Math.random() * (i + 1));
        var temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
    return array;
}

function validInputs()
{
	var inputs=$('#exampaper-info input[required]');
	var check=true;
	for (var i = 0; i < inputs.length; i++) {
		if($(inputs[i]).val()=="")
		{
			$(inputs[i]).addClass('border-danger');
			check=false;
		}
		else $(inputs[i]).removeClass('border-danger');
	}
	
	if(check==true)
	{
		var time=$("#input-time").val();
		if(!($.isNumeric(time)&&parseInt(time)>0))
		{
			check=false;
			$("#input-time").addClass('border-danger');
			alert('Thời Gian Không Hợp Lệ!');
		}
		
	}

	return check;
}
class Question extends Base {
	
    constructor() {
    	super();
    }  
}
