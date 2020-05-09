package bk.elearning.controller.manager.api;

import java.util.ArrayList;

import javax.persistence.criteria.CriteriaBuilder.In;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import bk.elearning.entity.Category;
import bk.elearning.entity.dto.PaginationResult;
import bk.elearning.service.ICategoryService;
import bk.elearning.utils.Message;

@RestController("managerCategoryApi")
@RequestMapping(path = "manager/api/categorys")
public class CategoryApi {

	@Autowired
	private ICategoryService categoryService;

	// get page by subject
	@GetMapping(path = "/page/subjects/{subjectId}")
	public PaginationResult<Category> getPageBySubject(@PathVariable int subjectId, @RequestParam int page,
			@RequestParam int size) {

		try {
			return categoryService.getBySubject(subjectId, page, size);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	// get all by subject
	@GetMapping(path = "/subjects/{subjectId}")
	public ArrayList<Category> getBySubject(@PathVariable int subjectId) {

		try {
			return categoryService.getBySubject(subjectId);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	// get page by subject
	@GetMapping(path = "/search/subjects/{subjectId}")
	public PaginationResult<Category> searchBySubject(@PathVariable int subjectId, @RequestParam(name = "q") String key,
			@RequestParam int page, @RequestParam int size) {

		try {
			return categoryService.searchBySubject(subjectId, key, page, size);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	@PostMapping
	public Message createCategory(@RequestBody Category category) {

		try {
			if (categoryService.save(category) == 1)
				return new Message("Thêm Thành Công !");
		} catch (Exception e) {
			// TODO: handle exception
		}
		return new Message("Thêm Thất Bại. Vui Lòng Thử Lại Sau !");
	}

	@PutMapping
	public Message editCategory(@RequestBody Category category) {

		try {
			if (categoryService.update(category) == 1)
				return new Message("Update Thành Công !");
		} catch (Exception e) {
			// TODO: handle exception
		}
		return new Message("Update Thất Bại. Vui Lòng Thử Lại Sau !");
	}

	@DeleteMapping(path = "/multiple")
	public Message deleteCategory(@RequestBody ArrayList<Integer> ids) {

		try {
			int success = categoryService.deleteMultiple(ids);
			return new Message("Đã Xóa " + success + " Thành Công !");
		} catch (Exception e) {
			// TODO: handle exception
		}
		return new Message("Xóa Thất Bại. Vui Lòng Thử Lại Sau !");
	}

}
