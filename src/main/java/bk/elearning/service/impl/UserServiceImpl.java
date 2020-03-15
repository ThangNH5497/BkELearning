package bk.elearning.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import bk.elearning.entity.User;
import bk.elearning.service.IUserService;

@Service
public class UserServiceImpl implements IUserService{

	@Override
	public List<User> getById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> getByIds(int[] ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> getLimit(int start, int count) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int save(User t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Class<User> clazz, int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(User t) {
		// TODO Auto-generated method stub
		return 0;
	}

	
}
