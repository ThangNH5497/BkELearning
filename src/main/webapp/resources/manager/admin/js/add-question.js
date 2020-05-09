$(document).ready(function() {
	obj=new Question();
	//use small pagination for modal
	$('#modal-select-subject ul.pagination').addClass('pagination-sm');
	$('#sidebar .active').removeClass('active');
	$('#menu-item-question').addClass('active');
	//lay du lieu trang va phan trang
	handlePagination('pagination','table-data-body','row-data-container','api/subjects/page?');
	//search events
	searchEvents('key-search','btn-search','api/subjects/search?');
	//for xs
	searchEvents('key-search-xs','btn-search-xs','api/subjects/search?');
	
	eventsHandle();
	initEditor(FLAG_ADD,'manager/api/questions/file-multimedia');
	importFromFile('manager/api/questions/import');
	uploadQuestionHandle("POST",'manager/api/questions',function(){
		reload();
	});
});
//reload page
function reload()
{
	$('input[required]').val("");
	$('#question-editor').summernote('reset');
	$('#question-editor').summernote('destroy');
	
	var answers=$('.answer');
	
	for (var i = 1; i < answers.length; i++) {
		$(answers[i]).remove();
	}
	$('.answer-editor').summernote('destroy');
	initEditor("",'manager/api/questions/file-multimedia');
	//default show 4 answer first
	for (var i = 0; i < 4; i++) {
		addAnswerEditor("");
	}

}
var obj;
class Question extends Base {
	
    constructor() {
    	super();

    }
    
    
}