package bk.elearning.controller.manager.teacher.api;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import bk.elearning.entity.Course;
import bk.elearning.service.ICourseService;
import bk.elearning.utils.Message;

@RestController("teacherCourseApi")
@RequestMapping(path = "/teacher/api/courses")
public class CourseApi {

	@Autowired
	private ICourseService courseService;

	// update
	@PutMapping
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
	@PutMapping("/{courseId}/students/{studentId}")
	public Message addStudent(@PathVariable int courseId, @PathVariable int studentId) {
		try {

			return new Message(courseService.addStudent(courseId, studentId));

		} catch (Exception e) {

		}
		return new Message("Đã Xảy Ra Lỗi !");

	}

	// add student from file
	@PutMapping("/{id}/students/file")
	public Message addStudentFromFile(@PathVariable(name="id") int courseId, @RequestPart MultipartFile file) {
		try {
			int result[]= {0,0};
			result=courseService.addStudent(courseId, file);
			return new Message("Thêm Thành Công "+result[0]+", Thất Bại "+result[1]);

		} catch (Exception e) {

		}
		return new Message("Đã Xảy Ra Lỗi !");

	}

	// remove student from course
	@DeleteMapping(path = "/{courseId}/students")
	public Message removeStudents(@PathVariable int courseId, @RequestBody ArrayList<Integer> ids) {
		return new Message(courseService.removeStudents(courseId, ids));
	}

	
}
