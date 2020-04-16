package bk.elearning.repository;

import bk.elearning.entity.Question;
import bk.elearning.entity.dto.PaginationResult;

public interface IQuestionRepository extends IGenericRepository<Question>,ISubjectComponentRepository<Question>{

	PaginationResult<Question> searchByFilter(int teacherId, String subjectId, String type, String level, String key,
			int start, int size);

	PaginationResult<Question> getByFilter(int teacherId, String subjectId, String type, String level, int start,
			int size);
	
	public void merge(Question q);
	

}
