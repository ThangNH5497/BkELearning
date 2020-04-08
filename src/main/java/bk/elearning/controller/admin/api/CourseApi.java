package bk.elearning.controller.admin.api;

import java.util.ArrayList;
import java.util.List;

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

import bk.elearning.entity.Course;
import bk.elearning.entity.dto.PaginationResult;
import bk.elearning.service.ICourseService;

@RestController("adminCourseApi")
@RequestMapping(path = "/admin/api/course")
@Transactional
public class CourseApi {

	@Autowired
	private ICourseService courseService;

	

	// xoa theo id
	@DeleteMapping(path = "/delete/{id}")
	public int deleteCourseById(@PathVariable int id) {
		return courseService.delete(id);
	}

	// xoa nhieu 1 luc
	@DeleteMapping(path = "/delete/multiple")
	public int deleteCourseByIds(@RequestBody ArrayList<Integer> ids) {
		return courseService.deleteMultiple(ids);
	}

	// tao moi course
	@PostMapping("/add")
	public String addNewCourse(@RequestPart("course") Course course) {

		try {
			if (courseService.save(course) == 1)
				return "Thêm Thành Công";
		} catch (Exception e) {

		}
		return "error . Xin Thử Lại Sau !";

	}

	// update
	@PutMapping("/update")
	public String updateCourse(@RequestPart("course") Course course) {

		try {
			if (courseService.update(course) == 1)
				return "Update Thành Công";
		} catch (Exception e) {
			System.out.println("ex : " + e.toString());
		}

		return "error . Xin Thử Lại Sau !";

	}

	// lay du lieu tim kiem va phan trang
	@GetMapping("/search/subject")
	public PaginationResult<Course> searchCoursesBySubject(@RequestParam int subjectId, @RequestParam String key,
			@RequestParam int page, int size) {
		try {
			return courseService.searchBySubject(subjectId, key, page, size);
		} catch (Exception e) {

		}
		return null;

	}

	// phan trang tat ca course
	@GetMapping("/page/subject")
	public PaginationResult<Course> getPageBySubject(@RequestParam int subjectId, @RequestParam int page,
			@RequestParam int size) {
		try {
			// return courseService.getPage(page, size);
			if (subjectId > 0) {
				return courseService.getPageBySubject(subjectId, page, size);
			}
		} catch (Exception e) {
		}
		return null;

	}
	// phan trang tat ca course

}
