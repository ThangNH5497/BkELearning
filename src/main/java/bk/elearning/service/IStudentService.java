package bk.elearning.service;

import bk.elearning.entity.Student;
import bk.elearning.entity.dto.PaginationResult;

public interface IStudentService extends IUserGenericService<Student>, IPaginationResultService<Student> {

	PaginationResult<Student> getPageByCourse(int courseId, int page, int size);
	
	PaginationResult<Student> searchByCourse(int courseId,String key, int page, int size);

	

}
