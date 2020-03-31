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

import bk.elearning.entity.Student;
import bk.elearning.entity.dto.PaginationResult;
import bk.elearning.service.IStudentService;

@RestController
@RequestMapping(path = "/admin/student")
@Transactional
public class StudentApi {

	@Autowired
	private IStudentService studentService;
	

	// lay tat ca
	@GetMapping(path = "/students")
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
	@GetMapping(path = "/id/{id}")
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

	// xoa theo id
	@DeleteMapping(path = "/delete/{id}")
	public int deleteStudentById(@PathVariable int id) {
		return studentService.delete(id);
	}

	// xoa nhieu 1 luc
	@DeleteMapping(path = "/delete/multiple")
	public int deleteStudentByIds(@RequestBody ArrayList<Integer> ids) {
		return studentService.deleteMultiple(ids);
	}

	// tao moi giang vien
	@PostMapping("/add")
	public String addNewStudent(@RequestPart("student") Student student,
			@RequestPart(name = "file", required = false) MultipartFile file) {

		try {
			if (studentService.save(student, file) == 1)
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
			result=studentService.saveFromFile(file);
		} catch (Exception e) {

		}

		return result;

	}

	// update
	@PutMapping("/update")
	public String updateStudent(@RequestPart("student") Student student,
			@RequestPart(name = "file", required = false) MultipartFile file) {

		try {
			if (studentService.update(student, file) == 1)
				return "Update Thành Công";
		} catch (Exception e) {

		}

		return "error . Xin Thử Lại Sau !";

	}

	// lay du lieu tim kiem va phan trang
	@GetMapping("/search")
	public PaginationResult<Student> searchStudents(@RequestParam(name = "filter") String filter,
			@RequestParam String key, @RequestParam int page, int size) {
		try {
			return studentService.getSearchPage(filter, key, page, size);
		} catch (Exception e) {

		}
		return null;

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
	// phan trang tat ca student

}
