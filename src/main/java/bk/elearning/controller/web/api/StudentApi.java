package bk.elearning.controller.web.api;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import bk.elearning.entity.Student;
import bk.elearning.entity.dto.PaginationResult;
import bk.elearning.service.IStudentService;
import bk.elearning.utils.Message;

@RestController
@RequestMapping(path = "/api/students")
@Transactional
public class StudentApi {

	@Autowired
	private IStudentService studentService;

	// lay tat ca
	@GetMapping
	public List<Student> getAllStudent() {
		List<Student> students = null;
		try {
			students = studentService.getAll();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return students;
	}

	// lay theo id
	@GetMapping(path = "/{id}")
	public Student getStudentById(@PathVariable int id) {
		Student students = null;
		try {
			students = studentService.getById(id);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return students;
	}

	// lay theo code
	@GetMapping(path = "/code/{code}")
	public Student getStudentByCode(@PathVariable String code) {
		Student student = null;
		try {
			student = studentService.getByCode(code);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return student;
	}

	// lay theo cuserName
	@GetMapping(path = "/username/{username}")
	public Student getStudentByUsername(@PathVariable String username) {
		Student student = null;
		try {
			student = studentService.getByUsername(username);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return student;
	}

	// update
	@PutMapping(path = "/{id}")
	public Message updateStudent(@PathVariable int id,@RequestPart("student") Student student,
			@RequestPart(name = "file", required = false) MultipartFile file) {
			student.setId(id);
		try {
			if (studentService.update(student, file) == 1)
				return new Message("Cập Nhật Thành Công ");
		} catch (Exception e) {

		}

		return new Message("Cập Nhật Thất Bại. Xin Thử Lại!");

	}

	// phan trang tat ca student
	@GetMapping("/page")
	public PaginationResult<Student> getPage(@RequestParam int page, int size) {
		try {
			return studentService.getPage(page, size);
		} catch (Exception e) {
		}
		return null;

	}

	// phan trang theo mã lớp
	@GetMapping("/page/courses/{id}")
	public PaginationResult<Student> getPageByCourse(@PathVariable(name="id") int courseId, @RequestParam int page, int size) {
		try {
			return studentService.getPageByCourse(courseId, page, size);
		} catch (Exception e) {
			System.out.println("ex1 " + e.toString());
		}
		return null;

	}

	// lay du lieu tim kiem va phan trang
	@GetMapping("/search")
	public PaginationResult<Student> searchStudents(@RequestParam(name="q") String key, @RequestParam int page, int size) {
		try {
			return studentService.getSearchPage(key, page, size);
		} catch (Exception e) {

		}
		return null;

	}

	// tim kiem theo lop
	@GetMapping("/courses/{id}/search")
	public PaginationResult<Student> searchByCourse(@PathVariable(name="id") int courseId, @RequestParam(name="q") String key,
			@RequestParam int page, int size) {
		try {
			return studentService.searchByCourse(courseId, key, page, size);
		} catch (Exception e) {

		}
		return null;

	}
}
