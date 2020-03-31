package bk.elearning.controller.teacher.api;

import java.util.ArrayList;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

import bk.elearning.entity.Subject;
import bk.elearning.entity.dto.PaginationResult;
import bk.elearning.service.ISubjectService;

@RestController
@RequestMapping(path = "/admin/subject")
@Transactional
public class SubjectApi {

	@Autowired
	private ISubjectService subjectService;

	// lay theo id
	@GetMapping(path = "/id/{id}")
	public Subject getSubjectById(@PathVariable int id) {
		Subject subject = null;
		try {
			subject = subjectService.getById(id);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return subject;
	}

	// xoa theo id
	@DeleteMapping(path = "/delete/{id}")
	public int deleteSubjectById(@PathVariable int id) {
		return subjectService.delete(id);
	}

	// xoa nhieu 1 luc
	@DeleteMapping(path = "/delete/multiple")
	public int deleteSubjectByIds(@RequestBody ArrayList<Integer> ids) {
		return subjectService.deleteMultiple(ids);
	}

	// tao moi giang vien
	@PostMapping("/add")
	public String addNewSubject(@RequestPart("subject") Subject subject) {

		try {
			if (subjectService.save(subject) == 1)
				return "Thêm Thành Công";
		} catch (Exception e) {

		}

		return "error . Xin Thử Lại Sau !";

	}

	// update
	@PutMapping("/update")
	public String updateSubject(@RequestPart("subject") Subject subject) {

		try {
			if (subjectService.update(subject) == 1)
				return "Update Thành Công";
		} catch (Exception e) {

		}

		return "error . Xin Thử Lại Sau !";

	}

	// lay du lieu tim kiem va phan trang
	@GetMapping("/search")
	public PaginationResult<Subject> searchSubjects(@RequestParam(name = "filter") String filter,
			@RequestParam String key, @RequestParam int page, int size) {
		try {
			return subjectService.getSearchPage(filter, key, page, size);
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
