package bk.elearning.controller.manager.admin.api;

import java.util.ArrayList;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import bk.elearning.entity.Student;
import bk.elearning.service.IStudentService;
import bk.elearning.utils.Message;

@RestController("adminStudentApi")
@RequestMapping(path = "/admin/api/students")
@Transactional
public class StudentApi {

	@Autowired
	private IStudentService studentService;

	// xoa theo id
	@DeleteMapping(path = "/{id}")
	public Message deleteStudentById(@PathVariable int id) {
		if( studentService.delete(id)==1)
			return new Message("Xóa Thành Công ");
		return new Message("Xóa Thất Bại.Xin Thử Lại Sau!");
	}

	// xoa nhieu 1 luc
	@DeleteMapping(path = "/multiple")
	public Message deleteStudentByIds(@RequestBody ArrayList<Integer> ids) {
		int success= studentService.deleteMultiple(ids);
		return new Message("Xóa "+success+" Thành Công .");
	}

	// tao moi giang vien
	@PostMapping()
	public Message addNewStudent(@RequestPart("student") Student student,
			@RequestPart(name = "file", required = false) MultipartFile file) {

		try {
			if (studentService.save(student, file) == 1)
				return new Message("Thêm Thành Công ");
		} catch (Exception e) {

		}

		return new Message("Thêm Thất Bại. Vui Lòng Thử Lại Sau!");

	}

	// them tu file exel
	@PostMapping("/file")
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
