package bk.elearning.controller.manager.api;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import bk.elearning.entity.Exam;
import bk.elearning.entity.dto.ExamPageDTO;
import bk.elearning.entity.dto.ExamResultDTO;
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
	public PaginationResult<ExamPageDTO> getByUserAndCourse(@PathVariable int courseId, @RequestParam int page,
			@RequestParam int size) {
		return examService.getPageByCourse(courseId, page, size);

	}
	
	@GetMapping("/page/subjects/{subjectId}")
	public PaginationResult<ExamPageDTO> getByUserAndSubject(@PathVariable int subjectId, @RequestParam int page,
			@RequestParam int size) {
		return examService.getPageBySubject(subjectId, page, size);

	}
	@GetMapping("/search/courses/{courseId}")
	public PaginationResult<ExamPageDTO> searchByUserAndCourse(@PathVariable int courseId, @RequestParam(name="q") String key, @RequestParam int page,
			@RequestParam int size) {
		return examService.searchPageByCourse(courseId,key, page, size);

	}
	
	@GetMapping("/{examId}/courses/{courseId}/result")
	public PaginationResult<ExamResultDTO> getResult(@PathVariable int examId,@PathVariable int courseId) {
		return examService.getResultByCourse(examId,courseId);

	}
	
	
	
	@GetMapping("/search/subjects/{subjectId}")
	public PaginationResult<ExamPageDTO> searchByUserAndSubject(@PathVariable int subjectId,@RequestParam(name="q") String key, @RequestParam int page,
			@RequestParam int size) {
		return examService.searchPageBySubject(subjectId,key, page, size);

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
	@PutMapping("/courses")
	public Message updateExamCourses(@RequestBody Exam exam) {

		if (examService.updateCourses(exam) == 1) {
			return new Message(Constant.STATUS_SUCCESS, "Cập Nhật Thành Công !");
		}

		return new Message(Constant.STATUS_ERROR, "Cập Nhật Thất Bại . Thử Lại Sau !");

	}
	
	@DeleteMapping("/multiple")
	public Message delete(@RequestBody ArrayList<Integer> ids) {

		int success = examService.deleteMultiple(ids);
		return new Message("Xóa Thành Công " + success);

	}
	
	@GetMapping(value = "/{examId}/courses/{courseId}/result/download")
    public ResponseEntity<InputStreamResource> downloadResultExcel(@PathVariable int examId,@PathVariable int courseId) throws IOException {
    ByteArrayInputStream in = examService.downloadResultExcel(examId,courseId);
    // return IOUtils.toByteArray(in);
    
    HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=result.xlsx");
    
     return ResponseEntity
                  .ok()
                  .headers(headers)
                  .body(new InputStreamResource(in));
    }
}
