package bk.elearning.controller.teacher.api;

import java.io.File;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import bk.elearning.entity.Teacher;
import bk.elearning.service.ITeacherService;

@RestController
@RequestMapping(path = "/teacher")
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

	// lay tung khoang
	@GetMapping(path = "/teachers/{start}/{count}")
	public List<Teacher> getTeachersLimit(@PathVariable int start, @PathVariable int count) {
		List<Teacher> teachers = null;
		try {
			teachers = teacherService.getLimit(start, count);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return teachers;
	}

	// lay theo id
	@GetMapping(path = "/id/{id}")
	public List<Teacher> getTeacherById(@PathVariable int id) {
		List<Teacher> teachers = null;
		try {
			teachers = teacherService.getById(id);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return teachers;
	}

	// lay theo code
	@GetMapping(path = "/code/{code}")
	public List<Teacher> getTeacherByCode(@PathVariable String code) {
		List<Teacher> teachers = null;
		try {
			teachers = teacherService.getByCode(code);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return teachers;
	}

	// lay theo cuserName
	@GetMapping(path = "/username/{username}")
	public List<Teacher> getTeacherByUsername(@PathVariable String username) {
		List<Teacher> teachers = null;
		try {
			teachers = teacherService.getByUsername(username);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return teachers;
	}

	@PostMapping(path = "/save")
	public int saveTeacher(@RequestBody Teacher teacher) {
		try {
			System.out.println(teacher.getAddr());
		} catch (Exception e) {
			// TODO: handle exception
		}
		return 0;
	}

	@DeleteMapping(path = "/delete/{id}")
	public int deleteTeacherById(@PathVariable int id) {
		return teacherService.delete(id);
	}

	/*
	 * @PostMapping(path = "/test") public String doUpload(@RequestParam
	 * MultipartFile multipartFile,MultipartHttpServletRequest request) {
	 * 
	 * 
	 * System.out.println("ok"); String t=request.getParameter("jsonObjectData");
	 * System.out.println("in ra t : "+t); File file = new File("D://my-file.jpg");
	 * try { multipartFile.transferTo(file); } catch (IllegalStateException |
	 * IOException e) { // TODO Auto-generated catch block e.printStackTrace(); }
	 * return "Uploaded: " + multipartFile.getSize() + " bytes"; }
	 */
	@PostMapping("/test")
	public String multiUploadFileModel(@ModelAttribute UploadModel model) {

		try {
			String json = model.getExtraField();
			ObjectMapper objectMapper = new ObjectMapper();
			Teacher t = objectMapper.readValue(json, Teacher.class);
			System.out.println("code : "+t.getCode());
			File file = new File("D://my-file.jpg");
			model.getFile().transferTo(file); 
		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return null;

	}
}
