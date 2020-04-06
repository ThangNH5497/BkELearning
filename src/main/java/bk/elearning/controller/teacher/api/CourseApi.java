package bk.elearning.controller.teacher.api;

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

@RestController("teacherCourseApi")
@RequestMapping(path = "/api/teacher/course")
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
			System.out.println("ex : "+e.toString());
		}

		return "error . Xin Thử Lại Sau !";

	}

	// lay du lieu tim kiem va phan trang
	@GetMapping("/search/teacher")
	public PaginationResult<Course> searchCoursesBySubject(@RequestParam int teacherId,@RequestParam(name = "filter") String filter,
			@RequestParam String key, @RequestParam int page, int size) {
		try {
			return courseService.searchByTeacher(teacherId,filter, key, page, size);
		} catch (Exception e) {

		}
		return null;

	}

	// phan trang tat ca course
	@GetMapping("/page/teacher")
	public PaginationResult<Course> getPageByTeacher(@RequestParam int teacherId,@RequestParam int page,@RequestParam int size) {
		try {
			//return courseService.getPage(page, size);
			if(teacherId>0)
			{
				return courseService.getPageByTeacher(teacherId, page, size);
			}
		} catch (Exception e) {
		}
		return null;

	}
	// phan trang tat ca course

}
