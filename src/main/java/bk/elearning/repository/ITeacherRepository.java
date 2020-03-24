package bk.elearning.repository;

import java.util.List;

import bk.elearning.entity.Teacher;

public interface ITeacherRepository extends IGeneralRepository<Teacher>{

	Teacher getByUsername(String username);
	Teacher getByCode(String code);
	List<Teacher> searchTeachers(String type, String key, int start, int count);
	Long searchCountTeachers(String type, String key);
	

}
