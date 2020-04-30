package bk.elearning.controller.web.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bk.elearning.entity.ExamPaper;
import bk.elearning.service.IExamPaperService;

@RestController
@RequestMapping(path = "api/exampapers")
public class ExamPaperApi {
	@Autowired
	IExamPaperService examPaperService;

	@GetMapping("/{id}")
	public ExamPaper getById(@PathVariable int id) {
		try {
			return examPaperService.getById(id);
		} catch (Exception e) {

		}
		return null;

	}

	@GetMapping("/codes/{code}")
	public ExamPaper getByCode(@PathVariable String code) {
		try {
			return examPaperService.getByCode(code);
		} catch (Exception e) {

		}
		return null;

	}
}
