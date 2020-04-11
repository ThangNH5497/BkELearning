package bk.elearning.repository.impl;

import org.springframework.stereotype.Repository;

import bk.elearning.entity.Question;
import bk.elearning.repository.IQuestionRepository;

@Repository
public class QuestionRepositoryImpl extends AbstractGenericRepository<Question> implements IQuestionRepository{

}
