package bk.elearning.repository;

import java.util.ArrayList;

import bk.elearning.entity.Course;
import bk.elearning.entity.dto.PaginationResult;

public interface ICourseRepository extends IGenericRepository<Course>,ISubjectComponentRepository<Course>{

	Boolean isStudentExis(int studentId,int courseId);

	int removeStudents(int courseId, ArrayList<Integer> ids);
	
	
}