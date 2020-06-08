package bk.elearning.repository;

import java.util.ArrayList;
import java.util.Set;

import bk.elearning.entity.Question;
import bk.elearning.entity.dto.CustomUserDetails;
import bk.elearning.entity.dto.PaginationResult;
import bk.elearning.entity.dto.QuestionFilter;
import bk.elearning.entity.relationship.ExamFilter;

public interface IQuestionRepository extends IGenericRepository<Question>,ISubjectComponentRepository<Question>{

	PaginationResult<Question> searchByTeacherAndFilter(String teacherId, String subjectId, String type, String level, String key,
			int start, int size);

	PaginationResult<Question> getByTeacherAndFilter(String teacherId, String subjectId, String type, String level, int start,
			int size);
	

	PaginationResult<Question> getPublicQuestion(String subjectId, String type, String level, int i, int size);

	PaginationResult<Question> searchPublicQuestion(String subjectId, String type, String level, String key, int i,
			int size);


	ArrayList<Question> getRandomQuestion(QuestionFilter filter, CustomUserDetails user);

	
	

}
