package bk.elearning.controller.admin.api;

import java.util.ArrayList;

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

import bk.elearning.entity.Subject;
import bk.elearning.entity.dto.PaginationResult;
import bk.elearning.service.ISubjectService;
import bk.elearning.utils.Message;

@RestController("adminSubjectApi")
@RequestMapping(path = "/admin/api/subjects")
public class SubjectApi {

	@Autowired
	private ISubjectService subjectService;

	// xoa theo id
	@DeleteMapping(path = "/{id}")
	public Message deleteSubjectById(@PathVariable int id) {
		if (subjectService.delete(id)==1)
			return new Message("Xóa Thành Công!");
		return new Message("Xóa Thất Bại. Xin Thử Lại!");
	}

	// xoa nhieu 1 luc
	@DeleteMapping(path = "/multiple")
	public int deleteSubjectByIds(@RequestBody ArrayList<Integer> ids) {
		return subjectService.deleteMultiple(ids);
	}

	// tao moi giang vien
	@PostMapping()
	public Message addNewSubject(@RequestPart("subject") Subject subject) {

		try {
			if (subjectService.save(subject) == 1)
				return new Message("Thêm Thành Công!");
		} catch (Exception e) {

		}

		return new Message("Thêm Thất Bại. Xin Thử Lại!");

	}

	// update
	@PutMapping("/{id}")
	public Message updateSubject(@PathVariable(name="id") int subjectId,@RequestPart("subject") Subject subject) {
		
		try {
			subject.setId(subjectId);
			if (subjectService.update(subject) == 1)
				return new Message("Cập Nhật Thành Công!");
		} catch (Exception e) {

		}
		return new Message("Cập Nhật Thất Bại. Xin Thử lại!");
	}

}
