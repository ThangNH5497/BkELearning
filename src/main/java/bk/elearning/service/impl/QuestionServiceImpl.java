package bk.elearning.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import bk.elearning.entity.Question;
import bk.elearning.service.IQuestionService;

@Service
public class QuestionServiceImpl implements IQuestionService{

	@Override
	public Question getById(int id) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<Question> getAll() {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public int save(Question t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Question t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Question getByCode(String code) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int deleteMultiple(ArrayList<Integer> ids) {
		// TODO Auto-generated method stub
		return 0;
	}



}