package bk.elearning.service;

import bk.elearning.entity.Course;
import bk.elearning.entity.dto.PaginationResult;

public interface ICourseService extends IGenericService<Course>,IPaginationResultService<Course>{

	public PaginationResult<Course> getPageBySubject(int subjectId,int page, int size);
	
	public PaginationResult<Course> getPageByTeacher(int teacherId,int page, int size);

	public PaginationResult<Course> searchBySubject(int subjectId, String filter, String key, int page, int size);
}
