$(document).ready(function() {

	obj=new Home();

	var studentId=userLoged.id;
	handlePagination('pagination','table-data-body','row-data-container','api/exams/page/students/'+studentId+'?');	
	
});
var obj;
class Home extends Base {
	
    constructor() {
    	super();
    }
 
}