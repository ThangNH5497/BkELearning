package bk.elearning.repository;

import bk.elearning.entity.Answer;

public interface IAnswerRepository extends IGenericRepository<Answer>{

	int deleteByQuestion(int questionId);

}
