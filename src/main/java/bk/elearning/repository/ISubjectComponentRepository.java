package bk.elearning.repository;

import bk.elearning.entity.dto.PaginationResult;

public interface ISubjectComponentRepository<T> {

	PaginationResult<T> findByTeacher(int teacherId, int start, int size);

	PaginationResult<T> findBySubject(int subjectId, int start, int size);
	

	PaginationResult<T> searchByTeacher(int teacherId, String key, int start, int size);
	

	PaginationResult<T> searchBySubject(int subjectId,  String key, int start, int size);
}
