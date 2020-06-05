package bk.elearning.controller.manager.admin.api;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import bk.elearning.entity.Teacher;
import bk.elearning.service.ITeacherService;
import bk.elearning.utils.Message;

@RestController("adminTeacherApi")
@RequestMapping(path = "/admin/api/teachers")
public class TeacherApi {

	@Autowired
	private ITeacherService teacherService;

	// xoa theo id
	@DeleteMapping(path = "/{id}")
	public Message deleteTeacherById(@PathVariable int id) {
		if (teacherService.delete(id) == 1)
			return new Message("Xóa Thành Công");
		return new Message("Xóa Thất Bại!");
	}

	// xoa nhieu 1 luc
	@DeleteMapping(path = "/multiple")
	public Message deleteTeacherByIds(@RequestBody ArrayList<Integer> ids) {
		int success = teacherService.deleteMultiple(ids);
		return new Message("Xóa Thành Công " + success);
	}

	// tao moi giang vien
	@PostMapping
	public Message addNewTeacher(@RequestPart("teacher") Teacher teacher,
			@RequestPart(name = "file", required = false) MultipartFile file) {

		try {
			if (teacherService.save(teacher, file) == 1)
				return new Message("Thêm Thành Công ");
		} catch (Exception e) {

		}

		return new Message("Thêm Thất Bại. Vui Lòng Thử Lại! ");

	}

	// them tu file exel
	@PostMapping("/file")
	public Message addFromFile(@RequestPart(name = "file", required = true) MultipartFile file) {
		// first value is success and second value is error
		int result[] = { 0, 0 };
		try {
			result = teacherService.saveFromFile(file);
		} catch (Exception e) {

		}
		return new Message("Thành Công : " + result[0] + ", Thất Bại : " + result[1]);

	}

	// update
	@PutMapping("/{id}")
	public Message updateTeacher(@PathVariable int id, @RequestPart("teacher") Teacher teacher,
			@RequestPart(name = "file", required = false) MultipartFile file) {
		teacher.setId(id);
		try {
			if (teacherService.update(teacher, file) == 1)
				return new Message("Cập Nhật Thành Công ");
		} catch (Exception e) {

		}

		return  new Message("Cập Nhật Thất Bại. Xin Thử Lại Sau!");

	}

}
