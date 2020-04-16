package bk.elearning.controller.teacher.api;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
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

import bk.elearning.entity.Question;
import bk.elearning.entity.Subject;
import bk.elearning.entity.Teacher;
import bk.elearning.entity.dto.CustomUserDetails;
import bk.elearning.entity.dto.PaginationResult;
import bk.elearning.service.IQuestionService;
import bk.elearning.utils.Constant;
import bk.elearning.utils.Message;

@RestController("teacherQuestionApi")
@RequestMapping(path = "teacher/api/questions")
public class QuestionApi {

	@Autowired
	IQuestionService questionService;

	// phan trang tat ca teacher
	@GetMapping("/{id}")
	public Question getById(@PathVariable int id) {
		try {
			return questionService.getById(id);
		} catch (Exception e) {

		}
		return null;

	}

	// phan trang tat ca teacher
	@GetMapping("/page/teachers/{id}")
	public PaginationResult<Question> getByTeacher(@PathVariable int id, @RequestParam int page, int size) {
		try {
			return questionService.getByTeacher(id, page, size);
		} catch (Exception e) {

		}
		return null;

	}

	// lấy theo bộ lọc
	@GetMapping("/page/teachers/{teacherId}/subjects/{subjectId}/types/{type}/levels/{level}")
	public PaginationResult<Question> getByFilter(@PathVariable int teacherId, @PathVariable String subjectId,
			@PathVariable String type, @PathVariable String level, @RequestParam int page, int size) {
		try {
			return questionService.getByFilter(teacherId, subjectId, type, level, page, size);
		} catch (Exception e) {

		}
		return null;

	}

	// tìm theo theo bộ lọc
	@GetMapping("/search/teachers/{teacherId}/subjects/{subjectId}/types/{type}/levels/{level}")
	public PaginationResult<Question> searchByFilter(@PathVariable int teacherId, @PathVariable String subjectId,
			@PathVariable String type, @PathVariable String level, @RequestParam(name="q") String key,@RequestParam int page, int size) {
		try {
			return questionService.searchByFilter(teacherId, subjectId, type, level,key, page, size);
		} catch (Exception e) {

		}
		return null;

	}

	@PostMapping
	public Message createQuestion(@RequestBody Question question) {
		try {
			// user loged (teacher)
			CustomUserDetails user = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication()
					.getPrincipal();
			if (user.getRole().equals(Constant.ROLE_TEACHER)) {
				Teacher teacher = new Teacher();
				teacher.setId(user.getId());
				question.setTeacher(teacher);
				if (questionService.save(question) == 1)
					return new Message(Constant.STATUS_SUCCESS, "Thêm Thành Công ");
			}

		} catch (Exception e) {

		}
		return new Message(Constant.STATUS_ERROR, "Thêm Thất Bại. Vui Lòng Thử Lại! ");

	}
	
	@PutMapping
	public Message updateQuestion(@RequestBody Question question) {
		try {
				if (questionService.update(question) == 1)
					return new Message(Constant.STATUS_SUCCESS, "Update Thành Công ");
			
		} catch (Exception e) {

		}
		return new Message(Constant.STATUS_ERROR, "Update Thất Bại. Vui Lòng Thử Lại! ");

	}

	@PostMapping(path = "/file-multimedia")
	public Message uploadFile(@RequestPart(name = "file", required = true) MultipartFile file) {

		try {
			
			String path = questionService.uploadFile(file);
			if (path != null)
				return new Message(Constant.STATUS_SUCCESS, path);
		} catch (Exception e) {

		}
		return new Message(Constant.STATUS_ERROR, "Thêm Thất Bại. Vui Lòng Thử Lại! ");
	}

	/**
	 * import from file exel
	 * 
	 * @param file    : file exel
	 * @param subject : subject of question list
	 * @return message
	 */
	@PostMapping(path = "/import")
	public Message importFromFile(@RequestPart(name = "file", required = true) MultipartFile file,
			@RequestPart(name = "subject", required = false) Subject subject,HttpServletRequest req) {
		int result[] = { 0, 0 };
		try {
			 String rootUrl= req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + req.getContextPath();
			result = questionService.importFromFile(file, subject,rootUrl);
			return new Message(Constant.STATUS_SUCCESS, "Import Thành Công " + result[0] + ", Thất Bại " + result[1]);
		} catch (Exception e) {

		}
		return new Message(Constant.STATUS_ERROR, "Import Thất Bại. Vui Lòng Thử Lại! ");
	}

	@DeleteMapping(path = "/multiple")
	public Message deleteMultiple(@RequestBody ArrayList<Integer> ids) {
		int status = Constant.STATUS_SUCCESS;

		try {
			CustomUserDetails user = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication()
					.getPrincipal();
			if (user != null) {
				int success = questionService.deleteMultiple(ids);
				return new Message(status, "Xóa Thành Công " + success + " Câu Hỏi !");
			}

		} catch (Exception e) {

		}

		return new Message(status, "Xóa Thất Bại");

	}

	@DeleteMapping(path = "/file-multimedia")
	public Message deleteFile(@RequestBody String path) {
		int status = Constant.STATUS_SUCCESS;
		try {
			if (questionService.deleteFile(path) == false)
				status = Constant.STATUS_ERROR;
		} catch (Exception e) {

		}

		return new Message(status, path);

	}
}
