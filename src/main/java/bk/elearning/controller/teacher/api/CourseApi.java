package bk.elearning.controller.teacher.api;

import java.util.ArrayList;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import bk.elearning.entity.Course;
import bk.elearning.entity.dto.CustomUserDetails;
import bk.elearning.entity.dto.PaginationResult;
import bk.elearning.service.ICourseService;
import bk.elearning.utils.Message;

@RestController("teacherCourseApi")
@RequestMapping(path = "/teacher/api/course")
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

	// add student
	@PutMapping("/update/{courseId}/student/{studentId}")
	public Message addStudent(@PathVariable int courseId, @PathVariable int studentId) {
		try {

			return new Message(courseService.addStudent(courseId, studentId));

		} catch (Exception e) {

		}
		return new Message("Đã Xảy Ra Lỗi !");

	}

	// add student from file
	@PutMapping("/update/{courseId}/student/file")
	public Message addStudentFromFile(@PathVariable int courseId, @RequestPart MultipartFile file) {
		try {
			int result[]= {0,0};
			result=courseService.addStudent(courseId, file);
			return new Message("Thêm Thành Công "+result[0]+", Thất Bại "+result[1]);

		} catch (Exception e) {

		}
		return new Message("Đã Xảy Ra Lỗi !");

	}

	// remove student from course
	@DeleteMapping(path = "/{courseId}/student")
	public Message removeStudents(@PathVariable int courseId, @RequestBody ArrayList<Integer> ids) {
		return new Message(courseService.removeStudents(courseId, ids));
	}

	// lay du lieu tim kiem va phan trang
	@GetMapping("/search/subject")
	public PaginationResult<Course> searchCoursesBySubject(@RequestParam String key, @RequestParam int page, int size) {
		try {
			CustomUserDetails user = null;
			user = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			return courseService.searchByTeacher(user.getId(), key, page, size);
		} catch (Exception e) {

		}
		return null;

	}

	// phan trang tat ca course
	@GetMapping("/page")
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
	@GetMapping("/search")
	public PaginationResult<Course> searchCoursesByTeacher(@RequestParam String key, @RequestParam int page, int size) {
		try {
			CustomUserDetails user = null;
			user = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			return courseService.searchByTeacher(user.getId(), key, page, size);
		} catch (Exception e) {

		}
		return null;

	}
}
