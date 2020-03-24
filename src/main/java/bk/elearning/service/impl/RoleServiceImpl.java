package bk.elearning.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bk.elearning.entity.Role;
import bk.elearning.repository.IRoleRepository;
import bk.elearning.service.IRoleService;

@Service
public class RoleServiceImpl implements IRoleService{

	@Autowired
	IRoleRepository roleRepository;
	@Override
	public Role getByName(String roleName)
	{
		return roleRepository.getByName(roleName);
		
	}

	@Override
	public Role getById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Role> getByIds(int[] ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Role> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Role> getLimit(int start, int count) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int save(Role t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Role t) {
		// TODO Auto-generated method stub
		return 0;
	}
}
