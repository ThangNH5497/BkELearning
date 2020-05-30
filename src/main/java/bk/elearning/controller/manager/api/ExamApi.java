package bk.elearning.controller.manager.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import bk.elearning.entity.Exam;
import bk.elearning.entity.dto.PaginationResult;
import bk.elearning.service.IExamService;
import bk.elearning.utils.Constant;
import bk.elearning.utils.Message;

@RestController("managerExamApi")
@RequestMapping(path = "manager/api/exams")
public class ExamApi {
	@Autowired
	IExamService examService;

	@GetMapping("/codes/{code}")
	public Exam getByCode(@PathVariable String code) {
		return examService.getByCode(code);

	}

	@GetMapping("/{id}")
	public Exam getById(@PathVariable Integer id) {
		return examService.getById(id);

	}

	@GetMapping("/page/courses/{courseId}")
	public PaginationResult<Exam> getByUserAndSubject(@PathVariable int courseId, @RequestParam int page,
			@RequestParam int size) {
		return examService.getPageByCourse(courseId, page, size);

	}

	@PostMapping
	public Message createExam(@RequestBody Exam exam) {

		if (examService.save(exam) == 1) {
			return new Message(Constant.STATUS_SUCCESS, "Thêm Thành Công !");
		}

		return new Message(Constant.STATUS_ERROR, "Thêm Thất Bại . Thử Lại Sau !");

	}
	
	@PutMapping
	public Message updateExam(@RequestBody Exam exam) {

		if (examService.update(exam) == 1) {
			return new Message(Constant.STATUS_SUCCESS, "Cập Nhật Thành Công !");
		}

		return new Message(Constant.STATUS_ERROR, "Cập Nhật Thất Bại . Thử Lại Sau !");

	}
}
