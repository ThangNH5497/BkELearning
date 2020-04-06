package bk.elearning.controller.teacher.api;

import java.util.ArrayList;
import java.util.List;

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
import org.springframework.web.multipart.MultipartFile;

import bk.elearning.entity.Teacher;
import bk.elearning.entity.dto.PaginationResult;
import bk.elearning.service.ITeacherService;

@RestController("teacherApi")
@RequestMapping(path = "/teacher/teacher")
@Transactional
public class TeacherApi {

	@Autowired
	private ITeacherService teacherService;
	

	// lay tat ca
	@GetMapping(path = "/teachers")
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
	@GetMapping(path = "/id/{id}")
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

	// xoa theo id
	@DeleteMapping(path = "/delete/{id}")
	public int deleteTeacherById(@PathVariable int id) {
		return teacherService.delete(id);
	}

	// xoa nhieu 1 luc
	@DeleteMapping(path = "/delete/multiple")
	public int deleteTeacherByIds(@RequestBody ArrayList<Integer> ids) {
		return teacherService.deleteMultiple(ids);
	}

	// tao moi giang vien
	@PostMapping("/add")
	public String addNewTeacher(@RequestPart("teacher") Teacher teacher,
			@RequestPart(name = "file", required = false) MultipartFile file) {

		try {
			if (teacherService.save(teacher, file) == 1)
				return "Thêm Thành Công";
		} catch (Exception e) {

		}

		return "error . Xin Thử Lại Sau !";

	}

	// them tu file exel
	@PostMapping("/add/file")
	public int[] addFromFile(@RequestPart(name = "file",required = true) MultipartFile file) {
		//first value is success and second value is error
		int result[]= {0,0};
		try {
			result=teacherService.saveFromFile(file);
		} catch (Exception e) {

		}

		return result;

	}

	// update
	@PutMapping("/update")
	public String updateTeacher(@RequestPart("teacher") Teacher teacher,
			@RequestPart(name = "file", required = false) MultipartFile file) {

		try {
			if (teacherService.update(teacher, file) == 1)
				return "Update Thành Công";
		} catch (Exception e) {

		}

		return "error . Xin Thử Lại Sau !";

	}

	// lay du lieu tim kiem va phan trang
	@GetMapping("/search")
	public PaginationResult<Teacher> searchTeachers(@RequestParam(name = "filter") String filter,
			@RequestParam String key, @RequestParam int page, int size) {
		try {
			return teacherService.getSearchPage(filter, key, page, size);
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
