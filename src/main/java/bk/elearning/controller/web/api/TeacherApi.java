package bk.elearning.controller.web.api;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import bk.elearning.entity.Teacher;
import bk.elearning.entity.dto.PaginationResult;
import bk.elearning.service.ITeacherService;

@RestController
@RequestMapping(path = "/api/teachers")
@Transactional
public class TeacherApi {

	@Autowired
	private ITeacherService teacherService;

	// lay tat ca
	@GetMapping()
	public List<Teacher> getAllTeacher() {
		List<Teacher> teachers = null;
		try {
			teachers = teacherService.getAll();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return teachers;
	}

	// lay theo id
	@GetMapping(path = "/{id}")
	public Teacher getTeacherById(@PathVariable int id) {
		Teacher teacher = null;
		try {
			teacher = teacherService.getById(id);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return teacher;
	}

	// lay theo code
	@GetMapping(path = "/code/{code}")
	public Teacher getTeacherByCode(@PathVariable String code) {
		Teacher teacher = null;
		try {
			teacher = teacherService.getByCode(code);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return teacher;
	}

	// lay theo cuserName
	@GetMapping(path = "/username/{username}")
	public Teacher getTeacherByUsername(@PathVariable String username) {
		Teacher teacher = null;
		try {
			teacher = teacherService.getByUsername(username);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return teacher;
	}

	// lay du lieu tim kiem va phan trang
	@GetMapping("/search")
	public PaginationResult<Teacher> searchTeachers(@RequestParam(name = "q") String key, @RequestParam int page,
			int size) {
		try {
			return teacherService.getSearchPage(key, page, size);
		} catch (Exception e) {

		}
		return null;

	}

	// phan trang tat ca teacher
	@GetMapping("/page")
	public PaginationResult<Teacher> getPage(@RequestParam int page, int size) {
		try {
			return teacherService.getPage(page, size);
		} catch (Exception e) {

		}
		return null;

	}
	// phan trang tat ca teacher

}
