package bk.elearning.repository;

import bk.elearning.entity.Student;
import bk.elearning.entity.dto.PaginationResult;

public interface IStudentRepository extends IUserGenericRepository<Student>{

	PaginationResult<Student> searchByCourse(int courseId, String key, int start, int size);

	PaginationResult<Student> getPageByCourse(int courseId, int start, int size);



}
