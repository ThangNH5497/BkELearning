package bk.elearning.controller.manager.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bk.elearning.entity.ExamPaper;
import bk.elearning.service.IExamPaperService;
import bk.elearning.utils.Constant;
import bk.elearning.utils.Message;

@RestController("managerExamPaperApi")
@RequestMapping(path = "manager/api/exampapers")
public class ExamPaperApi {

	@Autowired
	IExamPaperService examPaperService;
	
	@PostMapping
	public Message createExampaper(@RequestBody ExamPaper examPaper) {
		try {
			// user loged (teacher)
			int idCreated=examPaperService.save(examPaper);
			if(idCreated>0)
				return new Message(Constant.STATUS_SUCCESS, "Thêm Thành Công ! ",idCreated);
		} catch (Exception e) {

		}
		return new Message(Constant.STATUS_ERROR, "Thêm Thất Bại. Vui Lòng Thử Lại! ");

	}
	
	@PutMapping
	public Message updateExampaper(@RequestBody ExamPaper examPaper) {
		try {
			// user loged (teacher)
			if(examPaperService.update(examPaper)==1)
				return new Message(Constant.STATUS_SUCCESS, "Cập Nhật Thành Công ! ");
		} catch (Exception e) {

		}
		return new Message(Constant.STATUS_ERROR, "Cập Nhật Thất Bại. Vui Lòng Thử Lại! ");

	}
}
