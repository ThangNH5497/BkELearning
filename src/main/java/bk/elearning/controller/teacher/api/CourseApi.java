package bk.elearning.controller.teacher.api;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

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

import bk.elearning.entity.Course;
import bk.elearning.entity.dto.CustomUserDetails;
import bk.elearning.entity.dto.PaginationResult;
import bk.elearning.service.ICourseService;

@RestController("teacherCourseApi")
@RequestMapping(path = "/api/teacher/course")
@Transactional
public class CourseApi {

	@Autowired
	private ICourseService courseService;

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
	@GetMapping("/search/teacher")
	public PaginationResult<Course> searchCoursesBySubject(@RequestParam(name = "filter") String filter,
			@RequestParam String key, @RequestParam int page, int size) {
		try {
			CustomUserDetails user = null;
			user = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			return courseService.searchByTeacher(user.getId(), filter, key, page, size);
		} catch (Exception e) {

		}
		return null;

	}

	// phan trang tat ca course
	@GetMapping("/page/teacher")
	public PaginationResult<Course> getPageByTeacher(@RequestParam int page, @RequestParam int size) {
		try {
			// return courseService.getPage(page, size);
			CustomUserDetails user = null;
			user = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			return courseService.getPageByTeacher(user.getId(), page, size);

		} catch (Exception e) {
		}
		return null;

	}
	// phan trang tat ca course

}
