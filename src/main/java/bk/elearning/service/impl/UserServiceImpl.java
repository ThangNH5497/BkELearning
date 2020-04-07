package bk.elearning.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import bk.elearning.entity.User;
import bk.elearning.entity.dto.PaginationResult;
import bk.elearning.repository.IUserRepository;
import bk.elearning.service.IUserService;

@Service
public class UserServiceImpl implements IUserService{

	@Autowired
	private IUserRepository userRepository;



	@Override
	public User getById(int id) {
		// TODO Auto-generated method stub
		return userRepository.getById(id);
	
	}

	@Override
	public List<User> getAll() {
		// TODO Auto-generated method stub
		return userRepository.getAll();
		
	}
	@Override
	public User getByUsername(String username) {
		// TODO Auto-generated method stub
		return userRepository.getByUsername(username);
		
	}
	@Override
	public int save(User t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(User t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public User getByCode(String code) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int deleteMultiple(ArrayList<Integer> ids) {
		// TODO Auto-generated method stub
		return 0;
	}

	

	@Override
	public int save(User t, MultipartFile file) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(User t, MultipartFile file) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int[] saveFromFile(MultipartFile file) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PaginationResult<User> getPage(int page, int size) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PaginationResult<User> getSearchPage(String filter, String key, int page, int size) {
		// TODO Auto-generated method stub
		return null;
	}

}
