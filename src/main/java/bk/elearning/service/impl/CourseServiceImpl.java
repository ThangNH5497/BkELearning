package bk.elearning.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import bk.elearning.entity.Course;
import bk.elearning.entity.Student;
import bk.elearning.entity.dto.PaginationResult;
import bk.elearning.entity.relationship.StudentCourse;
import bk.elearning.repository.ICourseRepository;
import bk.elearning.repository.IStudentRepository;
import bk.elearning.service.ICourseService;
import bk.elearning.utils.Constant;
import bk.elearning.utils.FileUpload;
import bk.elearning.utils.StudentMapperUtil;
import bk.elearning.utils.TeacherMapperUtil;

@Service
public class CourseServiceImpl implements ICourseService {

	@Autowired
	ICourseRepository courseRepository;
	@Autowired
	IStudentRepository studentRepository;

	@Override
	public Course getById(int id) {
		// TODO Auto-generated method stub
		return courseRepository.getById(id);
	}

	@Override
	public List<Course> getAll() {
		// TODO Auto-generated method stub
		return courseRepository.getAll();
	}

	@Override
	public int save(Course t) {
		// TODO Auto-generated method stub

		return courseRepository.save(t);
	}

	@Override
	public int delete(int id) {
		// TODO Auto-generated method stub
		return courseRepository.delete(id);
	}

	@Override
	public int update(Course t) {
		// TODO Auto-generated method stub
		Course courseUpdate = courseRepository.getById(t.getId());
		courseUpdate.setCode(t.getCode());
		courseUpdate.setDescriptor(t.getDescriptor());
		courseUpdate.setCourseName(t.getCourseName());
		courseUpdate.setTeacher(t.getTeacher());
		return courseRepository.update(courseUpdate);
	}

	@Override
	public Course getByCode(String code) {
		// TODO Auto-generated method stub
		return courseRepository.getByCode(code);
	}

	@Override
	public int deleteMultiple(ArrayList<Integer> ids) {
		// TODO Auto-generated method stub
		int checkErro = 0;
		for (Integer integer : ids) {
			try {
				courseRepository.delete(integer);
			} catch (Exception e) {
				// TODO: handle exception
				checkErro++;
			}
		}
		if (checkErro == 0)
			return 1;
		return 1;
	}

	@Override
	public PaginationResult<Course> getPageBySubject(int subjectId, int page, int size) {

		if (page > 0) {
			return courseRepository.findBySubject(subjectId, page - 1, size);
		}
		return null;
	}

	@Override
	public PaginationResult<Course> getPageByTeacher(int teacherId, int page, int size) {
		if (page > 0) {
			return courseRepository.findByTeacher(teacherId, page - 1, size);
		}
		return null;
	}

	@Override
	public PaginationResult<Course> searchBySubject(int subjectId, String key, int page, int size) {
		if (page > 0) {
			return courseRepository.searchBySubject(subjectId, key, page - 1, size);
		}
		return null;
	}

	@Override
	public PaginationResult<Course> searchByTeacher(int teacherId, String key, int page, int size) {
		if (page > 0) {
			return courseRepository.searchByTeacher(teacherId, key, page - 1, size);
		}
		return null;
	}

	@Override
	public String addStudent(int courseId, int studentId) {
		// TODO Auto-generated method stub
		Student student = null;
		// kiểm tra sinh viên đã tồn tại trong lớp
		if (this.isStudentExis(studentId,courseId)) {
			return Constant.MSG_STUDENT_EXIS;
		} else {
			try {
				Course course = courseRepository.getById(courseId);
				student = new Student();
				student.setId(studentId);
				Set<StudentCourse> studentCourses = new HashSet<StudentCourse>();
				studentCourses.add(new StudentCourse(student, course));
				course.setStudentCourses(studentCourses);
				courseRepository.update(course);
				return "Update Thành Công";
			} catch (Exception e) {
				// TODO: handle exception
			}
		}

		return Constant.MSG_ERROR;
	}

	@Override
	public int[] addStudent(int courseId, MultipartFile file) {
		// TODO Auto-generated method stub
		int result[] = { 0, 0 };
		try {
			if (file != null) {
				List<Student> students = FileUpload.processFileExel(file, new StudentMapperUtil());
				for (Student student : students) {
					student=studentRepository.getByCode(student.getCode());
					if(student!=null)
					{
						String returnMsg=this.addStudent(courseId, student.getId());
						if((!(returnMsg.equals(Constant.MSG_STUDENT_EXIS)))&&(!(returnMsg.equals(Constant.MSG_ERROR))))
						{
							result[0]++;
						}
					}
					
				}
				result[1]=students.size()-result[0];
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}

	// remove students from course
	@Override
	public String removeStudents(int courseId, ArrayList<Integer> ids) {
		// TODO Auto-generated method stub
		try {
			int success = courseRepository.removeStudents(courseId, ids);

			return "Xóa Thành Công " + success + " Sinh viên";
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "Đã Xảy Ra Lỗi, Xin Thử Lại Sau!";
	}

	// kiểm tra sinh viên đã tồn tại trong lớp
	public Boolean isStudentExis(int studentId,int courseId) {
		// TODO Auto-generated method stub
		return courseRepository.isStudentExis(studentId,courseId);
	}

}
