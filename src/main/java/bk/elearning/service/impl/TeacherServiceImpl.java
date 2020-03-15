package bk.elearning.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import bk.elearning.entity.Teacher;
import bk.elearning.service.ITeacherService;
@Service
public class TeacherServiceImpl implements ITeacherService{

	@Override
	public List<Teacher> getById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Teacher> getByIds(int[] ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Teacher> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Teacher> getLimit(int start, int count) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int save(Teacher t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Class<Teacher> clazz, int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Teacher t) {
		// TODO Auto-generated method stub
		return 0;
	}

}
