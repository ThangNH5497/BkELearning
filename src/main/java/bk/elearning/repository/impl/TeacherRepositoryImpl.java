package bk.elearning.repository.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import bk.elearning.entity.Teacher;
import bk.elearning.repository.ITeacherRepository;
@Repository
public class TeacherRepositoryImpl extends GeneralRepositoryImpl<Teacher> implements ITeacherRepository{

	
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

}
