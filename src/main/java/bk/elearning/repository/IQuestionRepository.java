package bk.elearning.repository;

import bk.elearning.entity.Question;
import bk.elearning.entity.dto.PaginationResult;

public interface IQuestionRepository extends IGenericRepository<Question>,ISubjectComponentRepository<Question>{

	PaginationResult<Question> searchByTeacherAndFilter(String teacherId, String subjectId, String type, String level, String key,
			int start, int size);

	PaginationResult<Question> getByTeacherAndFilter(String teacherId, String subjectId, String type, String level, int start,
			int size);
	

	PaginationResult<Question> getByBankTypeAndFilter(String subjectId, String type, String level, int i, int size);

	PaginationResult<Question> searchByBankTypeAndFilter(String subjectId, String type, String level, String key, int i,
			int size);
	

}
