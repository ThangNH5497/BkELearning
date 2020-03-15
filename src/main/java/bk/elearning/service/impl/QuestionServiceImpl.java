package bk.elearning.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import bk.elearning.entity.Question;
import bk.elearning.service.IQuestionService;

@Service
public class QuestionServiceImpl implements IQuestionService{

	@Override
	public List<Question> getById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Question> getByIds(int[] ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Question> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Question> getLimit(int start, int count) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int save(Question t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Class<Question> clazz, int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Question t) {
		// TODO Auto-generated method stub
		return 0;
	}

}
