package bk.elearning.controller.web.api;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bk.elearning.entity.Course;
import bk.elearning.service.ICourseService;

@RestController("CourseApi")
@RequestMapping(path = "/api/course")
@Transactional
public class CourseApi {

	@Autowired
	private ICourseService courseService;

	// lay tat ca
		@GetMapping(path = "/courses")
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
		@GetMapping(path = "/id/{id}")
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
	
}
