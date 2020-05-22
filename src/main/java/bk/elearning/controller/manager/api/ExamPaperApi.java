package bk.elearning.controller.manager.api;

import java.util.ArrayList;

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
import org.springframework.web.bind.annotation.RestController;

import bk.elearning.entity.ExamPaper;
import bk.elearning.entity.dto.CustomUserDetails;
import bk.elearning.entity.dto.PaginationResult;
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
	@GetMapping("/page/subjects/{subjectFilter}")
	public PaginationResult<ExamPaper> getByUserAndSubject(@PathVariable String subjectFilter,@RequestParam int page,@RequestParam int size)
	{
		return examPaperService.getByUserAndSubject(subjectFilter,page,size);
		
	}
	@GetMapping("/search/subjects/{subjectFilter}")
	public PaginationResult<ExamPaper> searchByUserAndSubject(@PathVariable String subjectFilter,@RequestParam(name="q") String key,@RequestParam int page,@RequestParam int size)
	{
		return examPaperService.searchByUserAndSubject(subjectFilter,key,page,size);
		
	}
	
	@DeleteMapping("/multiple")
	public Message deleteMultiple(@RequestBody ArrayList<Integer> ids)
	{
		int status = Constant.STATUS_SUCCESS;

		try {
			CustomUserDetails user = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication()
					.getPrincipal();
			if (user != null) {
				int success = examPaperService.deleteMultiple(ids);
				return new Message(status, "Xóa Thành Công " + success + " Đề Thi !");
			}

		} catch (Exception e) {

		}

		return new Message(status, "Xóa Thất Bại");
		
	}
}
