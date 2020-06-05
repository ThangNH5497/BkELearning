$(document).ready(function() {

	obj=new StartExam();

	var studentId=userLoged.id;
	var examId=obj.getParam('examId');
	var exam=obj.ajaxCall('GET',false,'api/exams/dto/id/'+examId);
	var course=obj.getCourseById(obj.getParam('courseId'));
	try {
		$('[field=subject]').text(course.subject.code+'-'+course.subject.subjectName);
		$('[field=course]').text(course.code+'-'+course.courseName);
		$('[field=name]').text(exam.name);
		$('[field=code]').text(exam.code);
		$('[field=time]').text(exam.time+' Phút ');
	} catch (e) {
		// TODO: handle exception
		alert('Có Lỗi Xảy Ra . Vui Lòng Thử Lại!')
	}
	
});
var obj;
class StartExam extends Base {
	
    constructor() {
    	super();
    }
}