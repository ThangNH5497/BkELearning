package bk.elearning.service;

import java.util.List;

import bk.elearning.entity.Teacher;

public interface ITeacherService extends IGeneralService<Teacher>{

	List<Teacher> getByUsername(String username);
	List<Teacher> getByCode(String code);

}
