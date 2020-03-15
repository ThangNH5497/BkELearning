package bk.elearning.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import bk.elearning.entity.Exam;
import bk.elearning.service.IExamService;

@Service
public class ExamServiceImpl implements IExamService {

	@Override
	public List<Exam> getById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Exam> getByIds(int[] ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Exam> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Exam> getLimit(int start, int count) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int save(Exam t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Class<Exam> clazz, int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Exam t) {
		// TODO Auto-generated method stub
		return 0;
	}

}
