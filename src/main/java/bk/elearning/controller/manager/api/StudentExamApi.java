package bk.elearning.controller.manager.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import bk.elearning.entity.Exam;
import bk.elearning.entity.dto.PaginationResult;
import bk.elearning.entity.dto.StudentExamDTO;
import bk.elearning.entity.relationship.StudentExam;
import bk.elearning.service.IExamService;
import bk.elearning.utils.Constant;
import bk.elearning.utils.Message;

@RestController("managerStudentExamApi")
@RequestMapping(path = "manager/api/studentexams")
public class StudentExamApi {

	@Autowired
	IExamService examService;
	
	@GetMapping("/page/waitresult/exams/{examId}/courses/{courseId}")
	public PaginationResult<StudentExamDTO> getByExamAndCourse(@PathVariable int examId,@PathVariable int courseId, @RequestParam int page,
			@RequestParam int size) {
		return examService.getStudentExamUncomplete(examId,courseId, page, size);

	}
	
	@GetMapping("/{id}")
	public StudentExam getStudentExamUncompleteById(@PathVariable int id) {
		return examService.getStudentExamUncompleteById(id);

	}
	
	@PutMapping
	public Message updateStudentGrade(@RequestBody StudentExam studentExam) {

		if (examService.updateStudentGrade(studentExam) == 1) {
			return new Message(Constant.STATUS_SUCCESS, "Cập Nhật Thành Công !");
		}

		return new Message(Constant.STATUS_ERROR, "Cập Nhật Thất Bại . Thử Lại Sau !");

	}
}
