package bk.elearning.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import bk.elearning.entity.Course;
import bk.elearning.service.ICourseService;

@Service
public class CourseServiceImpl implements ICourseService{

	@Override
	public List<Course> getById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Course> getByIds(int[] ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Course> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Course> getLimit(int start, int count) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int save(Course t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Class<Course> clazz, int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Course t) {
		// TODO Auto-generated method stub
		return 0;
	}

}
