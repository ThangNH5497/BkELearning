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
	
	initEditor(FLAG_EDIT,'manager/api/questions/file-multimedia');
	
	uploadQuestionHandle("PUT",'manager/api/questions',function(){
		window.location.href=rootLocation+'teacher/ql-cau-hoi?subject=ALL&level=ALL&type=ALL';
	});
});

var obj;
class Question extends Base {
	
    constructor() {
    	super();

    }
    
    
}