package bk.elearning.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bk.elearning.entity.Role;
import bk.elearning.entity.Teacher;
import bk.elearning.repository.ITeacherRepository;
import bk.elearning.service.IRoleService;
import bk.elearning.service.ITeacherService;
import bk.elearning.utils.Util;
@Service
public class TeacherServiceImpl implements ITeacherService{

	@Autowired
	private ITeacherRepository teacherRepository;
	@Autowired
	IRoleService roleService;
	@Override
	public List<Teacher> getById(int id) {
		// TODO Auto-generated method stub
		return teacherRepository.getById(Teacher.class, id);
	}

	@Override
	public List<Teacher> getByIds(int[] ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Teacher> getAll() {
		// TODO Auto-generated method stub
		return teacherRepository.getAll(Teacher.class);
	}

	@Override
	public List<Teacher> getLimit(int start, int count) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int save(Teacher t) {
		// TODO Auto-generated method stub
		roleService.getByName("ROLE_TEACHER");
		Set roles=new HashSet<Role>(roleService.getByName("ROLE_TEACHER"));
		if(t.getImage()=="") t.setImage(Util.DEFAULT_USER_IMAGE);
		t.setCourse(null);
		return teacherRepository.save(t);
	}

	@Override
	public int delete( int id) {
		// TODO Auto-generated method stub
		return teacherRepository.delete(Teacher.class, id);
	}

	@Override
	public int update(Teacher t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Teacher> getByUsername(String username) {
		// TODO Auto-generated method stub
		return teacherRepository.getByUsername(username);
	}

	@Override
	public List<Teacher> getByCode(String code) {
		// TODO Auto-generated method stub
		return teacherRepository.getByCode(code);
	}

}
