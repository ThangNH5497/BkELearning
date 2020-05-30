$(document).ready(function() {
	obj=new Question();
	// use small pagination for modal
	$('#modal-select-subject ul.pagination').addClass('pagination-sm');
	$('#sidebar .active').removeClass('active');
	$('#menu-item-course').addClass('active');
	
	init();
	dateTimePicker();
	tableDataEvents();
	filterEventHandle();
	searchSubject();
	handleEvents();
	addQuestionSelectedEvents();
	saveExamPaperEvent();
});
var obj;
var exam={};
function dateTimePicker()
{
	 $(".form_datetime").datetimepicker({
			format : "dd/mm/yyyy - hh:ii",
			autoclose : true,
			todayBtn : true,
			pickerPosition : "bottom-middle",
		});
}

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
		$('#modal-select-question .subject-info').text(exam.subject.code+'-'+exam.subject.subjectName);
		$('#modal-select-question .teacher-info').text(userLoged.fullName);
	});
	//view question of exampaper
	// btn view
	$(document).on('click', '#question-list-container .btn-view', function() {
		var questionId = $(this).parents('[dataId]').attr('dataId');
		
		var question = null;
		for (var i = 0; i < exam.examQuestions.length; i++) {
			if(exam.examQuestions[i].question.id==questionId)
			{
				question=exam.examQuestions[i].question;
				break;
			}
		}
		if(question!=null&&question!=undefined&&question!="") {
			viewQuestion(question);
			$('#modal-view').modal('show');
		}
		else alert('Có Lỗi Xảy Ra. Xin Thử Lại Sau ! ');
	});
	
	//delete question from exam
	$(document).on('click', '.btn-delete', function() {
		deleteId=$(this).parents('[dataId]').attr('dataId');
		$('#modal-delete-alert').modal('show');
	});
	$(document).on('click', '#modal-delete-alert .btn-submit', function() {
		$('[dataId='+deleteId+']').remove();
		
		for (var i = 0; i < exam.examQuestions.length; i++) {
			if(exam.examQuestions[i].question.id==deleteId)
			{
				switch (exam.examQuestions[i].question.level) {
				case 0:
				{
					var count=parseInt($('#number-question-easy').val())-1;
					$('#number-question-easy').val(count);
					
					break;
				}
				case 1:
				{
					var count=parseInt($('#number-question-medium').val())-1;
					$('#number-question-medium').val(count);
					
					break;
				}
				case 2:
				{
					var count=parseInt($('#number-question-hard').val())-1;
					$('#number-question-hard').val(count);
					
					break;
				}
					

				default:
					break;
				}
				$('#number-question-total').val(parseInt($('#number-question-total').val())-1);
				exam.examQuestions.splice(i,1);
				break;
			}
		}
		
		$('#modal-delete-alert').modal('hide');
	});
	
	//input filter question
	$(document).on('change', '#exam-filter .exam-filter-input', function() {
		var val=$(this).val()
		if($.isNumeric(val)==true)
		{
			var count=parseInt($('#input-question-easy').val())+parseInt($('#input-question-medium').val())+parseInt($('#input-question-hard').val());
			$('#input-question-count').val(count);
		}
	});
}
// init
function init()
{
	var id=obj.getParam('examId');
	exam=obj.getExamById(id);
	if(exam==""||exam==null||exam==undefined)
	{
		alert('Lỗi Khi lấy Dữ Liệu . Vui Lòng Thử Lại !');
	}
	else
	{
		
		$('#input-subject').val(exam.subject.code+'-'+exam.subject.subjectName);
		$('#input-subject').attr('subjectId',exam.subject.id);
		$('#input-code').val(exam.code);
		
		$('#input-name').val(exam.name);
		$('#input-time').val(exam.time);
		$('#input-descriptor').val(exam.descriptor);
		$('#input-time-open').val(exam.timeOpen);
		$('#input-time-close').val(exam.timeClose);
		var count=0;
		for (var i = 0; i < exam.examFilters.length; i++) {
			count+=parseInt(exam.examFilters[i].number);
			switch (exam.examFilters[i].level) {
			case 0:
			{
				$('#input-question-easy').val(exam.examFilters[i].number);
				
				break;
			}
			case 1:
			{
				$('#input-question-medium').val(exam.examFilters[i].number);
				break;
			}
			case 2:
			{
				$('#input-question-hard').val(exam.examFilters[i].number);
				break;
			}
				

			default:
				break;
			}
		}
		
		$('#input-question-count').val(count);
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
		filterSubject=exam.subject.id;
		filterType='ALL';
		filterLevel='ALL'
		$('#filter-subject input').attr('val',filterSubject);
		$('#filter-type input').attr('val','ALL');
		$('#filter-level input').attr('val','ALL');
		
		$('#filter-subject input').val(exam.subject.code+'-'+exam.subject.subjectName);
		$('#filter-type input').val("Tất Cả");
		$('#filter-level input').val("Tất Cả");
		
		//modal random question
		$('#modal-random-question .subject').text(exam.subject.code+'-'+exam.subject.subjectName);
		$('#modal-random-question .user').text(userLoged.fullName);
		
		//show category filter
		showCategory(exam.subject.id);
		
		getRandomQuestion();
		
		checkboxControl();
		// init url api
		rootApiGet='manager/api/questions/page/teachers/'+teacherId+'/subjects/'+filterSubject+'/types/'+filterType+'/levels/'+filterLevel+'?'
		rootApiSearch='manager/api/questions/search/teachers/'+teacherId+'/subjects/'+filterSubject+'/types/'+filterType+'/levels/'+filterLevel+'?'
		// lay du lieu trang va phan trang
		handlePagination('pagination','table-data-body','row-data-container',rootApiGet,replaceDataView);
		// lay du lieu trang va phan trang tim kiem mon hoc cho filter
		handlePagination('pagination-subject','table-data-body-subject','row-data-container-subject','api/subjects/page?');
		searchEvents('key-search','btn-search',rootApiSearch);
		
		for (var i = 0; i < exam.examQuestions.length; i++) {
			addQuestionToView(exam.examQuestions[i].question,exam.examQuestions[i].grade);
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
	
	rootApiGet='manager/api/questions/page/teachers/'+teacherId+'/subjects/'+exam.subject.id+'/types/'+filterType+'/levels/'+filterLevel+'?';
	rootApiSearch='manager/api/questions/search/teachers/'+teacherId+'/subjects/'+exam.subject.id+'/types/'+filterType+'/levels/'+filterLevel+'?';
	// lay du lieu trang va phan trang
	
	handlePagination('pagination','table-data-body','row-data-container',rootApiGet,replaceDataView);
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
				 exam.examQuestions.push({
					  question:question
				  });
				addQuestionToView(question,0);
			}
			alert('Đã Thêm '+i);
		}
	});
}
//them question view vao danh sach cau hoi
function addQuestionToView(question,grade)
{
	if(grade==null||grade==undefined||grade=="") grade=0;
	var rowData=$('#question-item-sample').html();
	try {
		rowData='<div dataId="'+question.id+'">'+rowData+'</div>';
		$('#question-list-container').append(rowData);
		$('#question-list-container [dataId='+question.id+'] .question-content').html(question.content);
		$('#question-list-container [dataId='+question.id+'] .question-grade').val(grade);
		
		
		switch (question.level) {
		case 0:
		{
			$('#question-list-container [dataId='+question.id+'] .question-level').text('Dễ');
			var count=$('#number-question-easy').val();
			count=parseInt(count)+1;
			$('#number-question-easy').val(count);
			
			break;
		}
		case 1:
		{
			$('#question-list-container [dataId='+question.id+'] .question-level').text('Trung Bình');
			var count=$('#number-question-medium').val();
			count=parseInt(count)+1;
			$('#number-question-medium').val(count);
			
			break;
		}
		case 2:
		{
			$('#question-list-container [dataId='+question.id+'] .question-level').text('Khó');
			var count=$('#number-question-hard').val();
			count=parseInt(count)+1;
			$('#number-question-hard').val(count);
			
			break;
		}	

		default:
			break;
		}
		var countAllQuestion=0;
		countAllQuestion=parseInt($('#number-question-hard').val())
							+parseInt($('#number-question-medium').val())
							+parseInt($('#number-question-easy').val())
		$('#number-question-total').val(countAllQuestion);
		
		switch (question.type) {
		case 'ONE_CHOICE':
		{
			$('#question-list-container [dataId='+question.id+'] .question-type').text('Một Đáp Án');
			break;
		}
		case 'MULTIPLE_CHOICE':
		{
			$('#question-list-container [dataId='+question.id+'] .question-type').text('Nhiều Đáp Án');
			break;
		}
		case 'FILL_WORD':
		{
			$('#question-list-container [dataId='+question.id+'] .question-type').text('Điền Từ');
			break;
		}	

		default:
			break;
		}
		
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
					subjectId:exam.subject.id,
					categoryIds:categoryIds,
					levels:levels,
					types:types,
					count:$('#input-question').val(),
					existQuestionIds:existQuestionIds
			}
			questions=obj.ajaxCall("POST",false,'manager/api/questions/random',data,null);
			for (var i = 0; i < questions.length; i++) {
				addQuestionToView(questions[i]);
				exam.examQuestions.push({
					  question:questions[i]
				  });
			}
			alert('Lấy Thành Công '+questions.length+'/'+data.count+' Câu Hỏi')
		}
	});
}
//checkbox of modal random question
function checkboxControl()
{
	$(document).on('click', '#modal-random-question .dropdown-menu ,#exam-filter .dropdown-menu', function (e) {
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
	
	 //check-all
	$(document).on('change', '#exam-filter .check-all', function() {
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
	
	 //check-all
	$(document).on('change', '#exam-filter .check-all', function() {
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
	//check only one with checkbox level of exam filter
	$(document).on('change', '#exam-filter-level input[type=checkbox]', function() {
		var ulContainerId=$(this).parents('ul');
		$(ulContainerId).find('input[type=checkbox]').prop('checked',false);
	    if($(this).prop('checked')==false) {
	      // checkbox is checked
	    	//$('#'+ulContainerId+' input[type=checkbox]').prop('checked',true);
	    	$(this).prop('checked',true);
	    }
	    else
	    {
	    	$(this).prop('checked',false);
	    }
		
	});
	
	$(document).on('click', 'btn-add-exam-filter', function() {
		addExamFilter();
	});
}

function addExamFilter()
{
	var html=$('#exam-filter-item-sample').html(); 
	html+='<div id='+$('#exam-filter')
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
			try {

				exam.name=$('#input-name').val(),
				exam.time=$('#input-time').val();
				exam.descriptor=$('#input-descriptor').val();
				exam.timeOpen=$("#time-open").data("datetimepicker").getUTCDate();
				exam.timeClose=$("#time-close").data("datetimepicker").getUTCDate();
				if(exam.examFilters.length==0)
				{
					exam.examFilters.push({
						level:0,
						number:$('#input-question-easy').val()
					});
					exam.examFilters.push({
						level:1,
						number:$('#input-question-medium').val()
					});
					exam.examFilters.push({
						level:2,
						number:$('#input-question-hard').val()
					});
				}
				else
				{
					exam.examFilters[0]={
						level:0,
						number:$('#input-question-easy').val()
					};
					exam.examFilters[1]={
						level:1,
						number:$('#input-question-medium').val()
					};
					exam.examFilters[2]={
						level:2,
						number:$('#input-question-hard').val()
					};
				}
				for (var i = 0; i < exam.examQuestions.length; i++) {
					exam.examQuestions[i]['grade']=$('#question-list-container [dataId='+exam.examQuestions[i].question.id+'] .question-grade').val();
				}
				
				var message=obj.ajaxCall('PUT',false,'manager/api/exams',exam,null);
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
	
	if(check==true)
	{
		var inputGrade=$('[dataId] .question-grade');
		for (var i = 0; i < inputGrade.length; i++) {
			if($(inputGrade[i]).val()==""||$.isNumeric($(inputGrade[i]).val())==false||parseFloat($(inputGrade[i]).val())<=0)
			{
				$(inputGrade[i]).addClass('border-danger');
				check=false;
			}
			else $(inputGrade[i]).removeClass('border-danger');
			
		}
		if(check==false) alert('Câu Hỏi Chưa Cài Điểm Số .');
	}
	
	
	//check filter input
	if(check==true)
	{
		var easy=$('#input-question-easy').val();
		var medium=$('#input-question-medium').val();
		var hard=$('#input-question-hard').val();
		if($.isNumeric(easy)==false||(parseInt(easy)>parseInt($('#number-question-easy').val())))
		{
			$('#input-question-easy').addClass('border-danger');
			check=false;
		}
		else $('#input-question-easy').removeClass('border-danger');
		if($.isNumeric(medium)==false||parseInt(medium)>parseInt($('#number-question-medium').val()))
		{
			$('#input-question-medium').addClass('border-danger');
			check=false;
		}
		else $('#input-question-medium').removeClass('border-danger');
		if($.isNumeric(hard)==false||parseInt(hard)>parseInt($('#number-question-hard').val()))
		{
			$('#input-question-hard').addClass('border-danger');
			check=false;
		}
		else $('#input-question-hard').removeClass('border-danger');
	}
	
	return check;
}

//replace img, video ,... question show in table by icon
class Question extends Base {
	
    constructor() {
    	super();
    }  
}
