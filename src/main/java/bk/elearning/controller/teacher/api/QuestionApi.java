package bk.elearning.controller.teacher.api;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import bk.elearning.entity.Question;
import bk.elearning.service.IQuestionService;
import bk.elearning.utils.Constant;
import bk.elearning.utils.Message;

@RestController("teacherQuestionApi")
@RequestMapping(path = "teacher/api/questions")
public class QuestionApi {

	@Autowired
	IQuestionService questionService;

	@PostMapping
	public Message createQuestion(@RequestBody Question question) {
		try {
			if(questionService.save(question)==1)
			return new Message(Constant.STATUS_SUCCESS,"Thêm Thành Công ");
		} catch (Exception e) {

		}
		return new Message(Constant.STATUS_ERROR,"Thêm Thất Bại. Vui Lòng Thử Lại! ");

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
