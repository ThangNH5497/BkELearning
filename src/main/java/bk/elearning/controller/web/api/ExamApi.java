package bk.elearning.controller.web.api;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import bk.elearning.entity.dto.ExamDTO;
import bk.elearning.entity.dto.PaginationResult;
import bk.elearning.service.IExamService;

@RestController
@RequestMapping(path = "api/exams")
public class ExamApi {

	@Autowired
	IExamService examService;
	@GetMapping("/page/students/{studentId}")
	public PaginationResult<ExamDTO> getByCode(@PathVariable Integer studentId,@RequestParam int page,@RequestParam int size) {
		try {
			return examService.getByStudent(studentId,page,size);
		} catch (Exception e) {

		}
		return null;

	}
	
	@GetMapping("/dto/id/{examId}")
	public ExamDTO getById(@PathVariable Integer examId) {
		try {
			return examService.getExamDTOById(examId);
		} catch (Exception e) {

		}
		return null;

	}
	
	@GetMapping("/id/{examId}/students/{studentId}")
	public ExamDTO doExam(@PathVariable Integer examId,@PathVariable Integer studentId,HttpServletRequest request) {
		try {
			return examService.doExam(examId,studentId,request);
		} catch (Exception e) {

		}
		return null;

	}
}
