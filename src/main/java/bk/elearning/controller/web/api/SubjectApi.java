package bk.elearning.controller.web.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import bk.elearning.entity.Subject;
import bk.elearning.entity.dto.PaginationResult;
import bk.elearning.service.ISubjectService;
@RestController
@RequestMapping(path = "/api/subjects")
public class SubjectApi {

	@Autowired
	private ISubjectService subjectService;
	// lay theo id
	@GetMapping(path = "/{id}")
	public Subject getSubjectById(@PathVariable int id) {
		Subject subject = null;
		try {
			subject = subjectService.getById(id);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return subject;
	}

	// lay du lieu tim kiem va phan trang
	@GetMapping("/search")
	public PaginationResult<Subject> searchSubjects(@RequestParam(name="q") String key, @RequestParam int page, int size) {
		try {
			return subjectService.getSearchPage(key, page, size);
		} catch (Exception e) {

		}
		return null;

	}

	// lay theo code
	@GetMapping(path = "/code/{code}")
	public Subject getSubjectByCode(@PathVariable String code) {
		Subject subject = null;
		try {
			subject = subjectService.getByCode(code);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return subject;
	}

	// phan trang tat ca student
	@GetMapping("/page")
	public PaginationResult<Subject> getPage(@RequestParam int page, int size) {
		try {
			return subjectService.getPage(page, size);
		} catch (Exception e) {
		}
		return null;

	}
	// phan trang tat ca student
}
