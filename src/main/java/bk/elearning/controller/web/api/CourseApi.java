package bk.elearning.controller.web.api;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import bk.elearning.entity.Course;
import bk.elearning.entity.dto.CustomUserDetails;
import bk.elearning.entity.dto.PaginationResult;
import bk.elearning.service.ICourseService;

@RestController("CourseApi")
@RequestMapping(path = "/api/courses")
@Transactional
public class CourseApi {

	@Autowired
	private ICourseService courseService;

	// lay tat ca
	@GetMapping
	public List<Course> getAllCourse() {
		List<Course> courses = null;
		try {
			courses = courseService.getAll();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return courses;
	}

	// lay theo id
	@GetMapping(path = "/{id}")
	public Course getCourseById(@PathVariable int id) {
		Course courses = null;
		try {
			courses = courseService.getById(id);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return courses;
	}

	// lay theo code
	@GetMapping(path = "/code/{code}")
	public Course getCourseByCode(@PathVariable String code) {
		Course course = null;
		try {
			course = courseService.getByCode(code);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return course;
	}

	// lay du lieu tim kiem va phan trang
	@GetMapping("/subjects/{id}/search")
	public PaginationResult<Course> searchCoursesBySubject(@PathVariable(name = "id") int subjectId,
			@RequestParam(name = "q") String key, @RequestParam int page, int size) {
		try {
			return courseService.searchBySubject(subjectId, key, page, size);
		} catch (Exception e) {

		}
		return null;

	}

	// phan trang tat ca course
	@GetMapping("/page/subjects/{id}")
	public PaginationResult<Course> getPageBySubject(@PathVariable(name = "id") int subjectId, @RequestParam int page,
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
	// phan trang tat ca course
	@GetMapping("/page/teachers/{id}")
	public PaginationResult<Course> getPageByTeacher(@PathVariable int id,@RequestParam int page, @RequestParam int size) {
		try {
			// return courseService.getPage(page, size);
			//CustomUserDetails user = null;
			//user = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			return courseService.getPageByTeacher(id, page, size);

		} catch (Exception e) {
		}
		return null;

	}

	// phan trang tat ca course
	@GetMapping("/teachers/{id}/search")
	public PaginationResult<Course> searchCoursesByTeacher(@PathVariable int id,@RequestParam(name="q") String key, @RequestParam int page, int size) {
		try {
			return courseService.searchByTeacher(id, key, page, size);
		} catch (Exception e) {

		}
		return null;
	}

}
