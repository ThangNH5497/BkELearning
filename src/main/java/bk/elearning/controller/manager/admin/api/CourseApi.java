package bk.elearning.controller.manager.admin.api;

import java.util.ArrayList;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

import bk.elearning.entity.Course;
import bk.elearning.service.ICourseService;
import bk.elearning.utils.Message;

@RestController("adminCourseApi")
@RequestMapping(path = "/admin/api/courses")
@Transactional
public class CourseApi {

	@Autowired
	private ICourseService courseService;

	// xoa theo id
	@DeleteMapping(path = "/{id}")
	public Message deleteCourseById(@PathVariable int id) {
		if (courseService.delete(id) == 1)
			return new Message("Xóa Thành Công!");
		return new Message("Xóa Thất Bại. Xin Thử Lại!");
	}

	// xoa nhieu 1 luc
	@DeleteMapping(path = "/multiple")
	public Message deleteCourseByIds(@RequestBody ArrayList<Integer> ids) {
		int success = courseService.deleteMultiple(ids);
		return new Message("Xóa Thành Công " + success + " !");
	}

	// tao moi course
	@PostMapping()
	public Message addNewCourse(@RequestPart("course") Course course) {

		try {
			if (courseService.save(course) == 1)
				return new Message("Thêm Thành Công!");
		} catch (Exception e) {

		}
		return new Message("Thêm Thất Bại. Xin Thử Lại !");

	}

	// update
	@PutMapping("/{id}")
	public Message updateCourse(@PathVariable(name = "id") int courseId, @RequestPart("course") Course course) {
		course.setId(courseId);
		try {
			if (courseService.update(course) == 1)
				return new Message("Cập Nhật Thành Công");
		} catch (Exception e) {
			System.out.println("ex : " + e.toString());
		}

		return new Message("Cập Nhật Thất Bại. Xin Thử Lại Sau!");

	}

	

}
