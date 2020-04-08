package bk.elearning.service;

import java.util.ArrayList;

import org.springframework.web.multipart.MultipartFile;

import bk.elearning.entity.Course;
import bk.elearning.entity.dto.PaginationResult;

public interface ICourseService extends IGenericService<Course> {

	public PaginationResult<Course> getPageBySubject(int subjectId, int page, int size);

	public PaginationResult<Course> getPageByTeacher(int teacherId, int page, int size);

	public PaginationResult<Course> searchBySubject(int subjectId, String key, int page, int size);

	public PaginationResult<Course> searchByTeacher(int teacherId, String key, int page, int size);

	String addStudent(int courseId,int studentId);

	public int[] addStudent( int courseId,MultipartFile file);

	public String removeStudents(int courseId,ArrayList<Integer> ids);

}
