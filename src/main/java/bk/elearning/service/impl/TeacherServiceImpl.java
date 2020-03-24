package bk.elearning.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import bk.elearning.entity.Role;
import bk.elearning.entity.Teacher;
import bk.elearning.repository.ITeacherRepository;
import bk.elearning.service.IRoleService;
import bk.elearning.service.ITeacherService;
import bk.elearning.utils.FileUpload;
import bk.elearning.utils.Util;

@Service
public class TeacherServiceImpl implements ITeacherService {

	@Autowired
	private ITeacherRepository teacherRepository;
	@Autowired
	IRoleService roleService;

	@Override
	public Teacher getById(int id) {
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
	//lay theo tung khoang
	@Override
	public List<Teacher> getLimit(int start, int count) {
		// TODO Auto-generated method stub
		return teacherRepository.getLimit(start, count, Teacher.class);
	}

	@Override
	public int save(Teacher t) {
		// TODO Auto-generated method stub
		Set roles = new HashSet<Role>();
		roles.add(roleService.getByName("ROLE_TEACHER"));
		if (t.getImage() == "")
			t.setImage(Util.DEFAULT_USER_IMAGE);
		t.setCourse(null);
		return teacherRepository.save(t);
	}

	// luu voi anh
	public int save(Teacher t, MultipartFile file) {
		// TODO Auto-generated method stub
		roleService.getByName("ROLE_TEACHER");
		Set roles = new HashSet<Role>();
		roles.add(roleService.getByName("ROLE_TEACHER"));

		t.setCourse(null);
		if (file != null) {
			String filePath = "resources/commons/image/teacher-" + t.getCode() + "."
					+ file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf('.') + 1);
			System.out.println("path : " + filePath);
			if (FileUpload.saveFile(file, filePath)) {
				t.setImage(filePath);
			}
		}
		// dat hinh anh default
		else
			t.setImage(Util.DEFAULT_USER_IMAGE);
		return teacherRepository.save(t);
	}

	@Override
	public int delete(int id) {
		// TODO Auto-generated method stub
		return teacherRepository.delete(Teacher.class, id);
	}

	@Override
	public int update(Teacher t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Teacher teacher, MultipartFile file) {
		Teacher teacherUpdate = teacherRepository.getById(Teacher.class, teacher.getId());
		teacherUpdate.setEmail(teacher.getEmail());
		teacherUpdate.setPhoneNumber(teacher.getPhoneNumber());
		teacherUpdate.setDateOfBirth(teacher.getDateOfBirth());
		teacherUpdate.setDepartment(teacher.getDepartment());
		teacherUpdate.setFullName(teacher.getFullName());
		teacherUpdate.setPosition(teacher.getPosition());
		if (file != null) {
			String filePath = "resources/commons/image/teacher-" + teacher.getCode() + "."
					+ file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf('.') + 1);
			if (FileUpload.saveFile(file, filePath)) {
				teacherUpdate.setImage(filePath);
			}
		}
		return teacherRepository.update(teacherUpdate);
	}

	@Override
	public Teacher getByUsername(String username) {
		// TODO Auto-generated method stub
		return teacherRepository.getByUsername(username);
	}

	@Override
	public Teacher getByCode(String code) {
		// TODO Auto-generated method stub
		return teacherRepository.getByCode(code);
	}

	@Override
	public int deleteMultiple(ArrayList<Integer> ids) {
		// TODO Auto-generated method stub
		int checkErro=0;
		for (Integer integer : ids) {
			try {
				teacherRepository.delete(Teacher.class, integer);
			} catch (Exception e) {
				// TODO: handle exception
				checkErro++;
			}
		}
		if(checkErro==0) return 1;
		return 	1;
	}

	@Override
	public Long getCount() {
		// TODO Auto-generated method stub
		return teacherRepository.getCount(Teacher.class);
	}

	@Override
	public List<Teacher> searchTeachers(String type, String key, int start, int count) {
		// TODO Auto-generated method stub
		return teacherRepository.searchTeachers(type,key,start,count);
	}

	@Override
	public Long searchCountTeachers(String type, String key) {
		// TODO Auto-generated method stub
		return teacherRepository.searchCountTeachers(type,key);
	}

}
