package bk.elearning.controller.admin.api;

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
import bk.elearning.utils.Message;

@RestController("adminStudentApi")
@RequestMapping(path = "/admin/api/student")
@Transactional
public class StudentApi {

	@Autowired
	private IStudentService studentService;

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
	public Message addFromFile(@RequestPart(name = "file", required = true) MultipartFile file) {
		// first value is success and second value is error
		int result[] = { 0, 0 };
		try {
			result = studentService.saveFromFile(file);
		} catch (Exception e) {

		}
		return new Message("Thành Công : "+result[0]+", Thất Bại : "+result[1]);

	}

}
