package bk.elearning.controller.manager.admin.api;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import bk.elearning.entity.Question;
import bk.elearning.entity.dto.PaginationResult;
import bk.elearning.service.IQuestionService;
import bk.elearning.utils.Constant;
import bk.elearning.utils.Message;

@RestController("adminQuestionApi")
@RequestMapping(path = "admin/api/questions")
public class QuestionApi {

	@Autowired
	IQuestionService questionService;

	

	@PostMapping(path = "/copy")
	public Message copyToPublicRepo(@RequestBody ArrayList<Integer> ids) {
		try {
			// user loged (teacher)
			int success=questionService.copyToPublicRepo(ids) ;
			return new Message(Constant.STATUS_SUCCESS, "Sao Chép Thành Công "+String.valueOf(success)+" , Thất Bại "+String.valueOf(ids.size()-success));

		} catch (Exception e) {

		}
		return new Message(Constant.STATUS_ERROR, "Sao Chép Thất Bại. Vui Lòng Thử Lại! ");

	}

}
